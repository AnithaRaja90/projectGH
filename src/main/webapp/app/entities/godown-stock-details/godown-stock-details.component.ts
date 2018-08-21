import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGodownStockDetails } from 'app/shared/model/godown-stock-details.model';
import { Principal } from 'app/core';
import { GodownStockDetailsService } from './godown-stock-details.service';

@Component({
    selector: 'jhi-godown-stock-details',
    templateUrl: './godown-stock-details.component.html'
})
export class GodownStockDetailsComponent implements OnInit, OnDestroy {
    godownStockDetails: IGodownStockDetails[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private godownStockDetailsService: GodownStockDetailsService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.godownStockDetailsService.query().subscribe(
            (res: HttpResponse<IGodownStockDetails[]>) => {
                this.godownStockDetails = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGodownStockDetails();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGodownStockDetails) {
        return item.id;
    }

    registerChangeInGodownStockDetails() {
        this.eventSubscriber = this.eventManager.subscribe('godownStockDetailsListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
