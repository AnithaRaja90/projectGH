import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IBatch } from 'app/shared/model/batch.model';
import { BatchService } from './batch.service';
import { INursery } from 'app/shared/model/nursery.model';
import { NurseryService } from 'app/entities/nursery';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';

@Component({
    selector: 'jhi-batch-update',
    templateUrl: './batch-update.component.html'
})
export class BatchUpdateComponent implements OnInit {
    private _batch: IBatch;
    isSaving: boolean;

    nurseries: INursery[];

    picklistvalues: IPickListValue[];
    sowingDateDp: any;
    closedDateDp: any;
    createdAt: string;
    updatedAt: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private batchService: BatchService,
        private nurseryService: NurseryService,
        private pickListValueService: PickListValueService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ batch }) => {
            this.batch = batch;
        });
        this.nurseryService.query().subscribe(
            (res: HttpResponse<INursery[]>) => {
                this.nurseries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pickListValueService.query().subscribe(
            (res: HttpResponse<IPickListValue[]>) => {
                this.picklistvalues = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.batch.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
        this.batch.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.batch.id !== undefined) {
            this.subscribeToSaveResponse(this.batchService.update(this.batch));
        } else {
            this.subscribeToSaveResponse(this.batchService.create(this.batch));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBatch>>) {
        result.subscribe((res: HttpResponse<IBatch>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackNurseryById(index: number, item: INursery) {
        return item.id;
    }

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }
    get batch() {
        return this._batch;
    }

    set batch(batch: IBatch) {
        this._batch = batch;
        this.createdAt = moment(batch.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(batch.updatedAt).format(DATE_TIME_FORMAT);
    }
}
