import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { INurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';
import { NurseryStockDetailsService } from './nursery-stock-details.service';
import { IBatch } from 'app/shared/model/batch.model';
import { BatchService } from 'app/entities/batch';
import { INurseryStock } from 'app/shared/model/nursery-stock.model';
import { NurseryStockService } from 'app/entities/nursery-stock';

@Component({
    selector: 'jhi-nursery-stock-details-update',
    templateUrl: './nursery-stock-details-update.component.html'
})
export class NurseryStockDetailsUpdateComponent implements OnInit {
    private _nurseryStockDetails: INurseryStockDetails;
    isSaving: boolean;

    batches: IBatch[];

    nurserystocks: INurseryStock[];
    dateDp: any;
    createdAt: string;
    updatedAt: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private nurseryStockDetailsService: NurseryStockDetailsService,
        private batchService: BatchService,
        private nurseryStockService: NurseryStockService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nurseryStockDetails }) => {
            this.nurseryStockDetails = nurseryStockDetails;
        });
        this.batchService.query().subscribe(
            (res: HttpResponse<IBatch[]>) => {
                this.batches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.nurseryStockService.query().subscribe(
            (res: HttpResponse<INurseryStock[]>) => {
                this.nurserystocks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.nurseryStockDetails.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
        this.nurseryStockDetails.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.nurseryStockDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.nurseryStockDetailsService.update(this.nurseryStockDetails));
        } else {
            this.subscribeToSaveResponse(this.nurseryStockDetailsService.create(this.nurseryStockDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INurseryStockDetails>>) {
        result.subscribe((res: HttpResponse<INurseryStockDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackBatchById(index: number, item: IBatch) {
        return item.id;
    }

    trackNurseryStockById(index: number, item: INurseryStock) {
        return item.id;
    }
    get nurseryStockDetails() {
        return this._nurseryStockDetails;
    }

    set nurseryStockDetails(nurseryStockDetails: INurseryStockDetails) {
        this._nurseryStockDetails = nurseryStockDetails;
        this.createdAt = moment(nurseryStockDetails.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(nurseryStockDetails.updatedAt).format(DATE_TIME_FORMAT);
    }
}
