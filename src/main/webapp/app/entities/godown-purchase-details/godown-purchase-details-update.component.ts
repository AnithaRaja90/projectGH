import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IGodownPurchaseDetails } from 'app/shared/model/godown-purchase-details.model';
import { GodownPurchaseDetailsService } from './godown-purchase-details.service';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';
import { IGodown } from 'app/shared/model/godown.model';
import { GodownService } from 'app/entities/godown';

@Component({
    selector: 'jhi-godown-purchase-details-update',
    templateUrl: './godown-purchase-details-update.component.html'
})
export class GodownPurchaseDetailsUpdateComponent implements OnInit {
    private _godownPurchaseDetails: IGodownPurchaseDetails;
    isSaving: boolean;

    picklistvalues: IPickListValue[];

    godowns: IGodown[];
    dateDp: any;
    createdAt: string;
    updatedAt: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private godownPurchaseDetailsService: GodownPurchaseDetailsService,
        private pickListValueService: PickListValueService,
        private godownService: GodownService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ godownPurchaseDetails }) => {
            this.godownPurchaseDetails = godownPurchaseDetails;
        });
        this.pickListValueService.query().subscribe(
            (res: HttpResponse<IPickListValue[]>) => {
                this.picklistvalues = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.godownService.query().subscribe(
            (res: HttpResponse<IGodown[]>) => {
                this.godowns = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.godownPurchaseDetails.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
        this.godownPurchaseDetails.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.godownPurchaseDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.godownPurchaseDetailsService.update(this.godownPurchaseDetails));
        } else {
            this.subscribeToSaveResponse(this.godownPurchaseDetailsService.create(this.godownPurchaseDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodownPurchaseDetails>>) {
        result.subscribe(
            (res: HttpResponse<IGodownPurchaseDetails>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }

    trackGodownById(index: number, item: IGodown) {
        return item.id;
    }
    get godownPurchaseDetails() {
        return this._godownPurchaseDetails;
    }

    set godownPurchaseDetails(godownPurchaseDetails: IGodownPurchaseDetails) {
        this._godownPurchaseDetails = godownPurchaseDetails;
        this.createdAt = moment(godownPurchaseDetails.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(godownPurchaseDetails.updatedAt).format(DATE_TIME_FORMAT);
    }
}
