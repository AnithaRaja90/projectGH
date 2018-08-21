import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IShadeArea } from 'app/shared/model/shade-area.model';
import { ShadeAreaService } from './shade-area.service';
import { IBatch } from 'app/shared/model/batch.model';
import { BatchService } from 'app/entities/batch';

@Component({
    selector: 'jhi-shade-area-update',
    templateUrl: './shade-area-update.component.html'
})
export class ShadeAreaUpdateComponent implements OnInit {
    private _shadeArea: IShadeArea;
    isSaving: boolean;

    batches: IBatch[];
    dateDp: any;
    createdAt: string;
    updatedAt: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private shadeAreaService: ShadeAreaService,
        private batchService: BatchService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ shadeArea }) => {
            this.shadeArea = shadeArea;
        });
        this.batchService.query().subscribe(
            (res: HttpResponse<IBatch[]>) => {
                this.batches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.shadeArea.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
        this.shadeArea.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.shadeArea.id !== undefined) {
            this.subscribeToSaveResponse(this.shadeAreaService.update(this.shadeArea));
        } else {
            this.subscribeToSaveResponse(this.shadeAreaService.create(this.shadeArea));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IShadeArea>>) {
        result.subscribe((res: HttpResponse<IShadeArea>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get shadeArea() {
        return this._shadeArea;
    }

    set shadeArea(shadeArea: IShadeArea) {
        this._shadeArea = shadeArea;
        this.createdAt = moment(shadeArea.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(shadeArea.updatedAt).format(DATE_TIME_FORMAT);
    }
}
