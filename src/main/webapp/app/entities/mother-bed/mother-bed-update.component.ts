import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IMotherBed } from 'app/shared/model/mother-bed.model';
import { MotherBedService } from './mother-bed.service';
import { INursery } from 'app/shared/model/nursery.model';
import { NurseryService } from 'app/entities/nursery';

@Component({
    selector: 'jhi-mother-bed-update',
    templateUrl: './mother-bed-update.component.html'
})
export class MotherBedUpdateComponent implements OnInit {
    private _motherBed: IMotherBed;
    isSaving: boolean;

    nurseries: INursery[];
    createdAt: string;
    updatedAt: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private motherBedService: MotherBedService,
        private nurseryService: NurseryService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ motherBed }) => {
            this.motherBed = motherBed;
        });
        this.nurseryService.query().subscribe(
            (res: HttpResponse<INursery[]>) => {
                this.nurseries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.motherBed.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
        this.motherBed.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.motherBed.id !== undefined) {
            this.subscribeToSaveResponse(this.motherBedService.update(this.motherBed));
        } else {
            this.subscribeToSaveResponse(this.motherBedService.create(this.motherBed));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMotherBed>>) {
        result.subscribe((res: HttpResponse<IMotherBed>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get motherBed() {
        return this._motherBed;
    }

    set motherBed(motherBed: IMotherBed) {
        this._motherBed = motherBed;
        this.createdAt = moment(motherBed.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(motherBed.updatedAt).format(DATE_TIME_FORMAT);
    }
}
