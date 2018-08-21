import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGodown } from 'app/shared/model/godown.model';
import { GodownService } from './godown.service';

@Component({
    selector: 'jhi-godown-update',
    templateUrl: './godown-update.component.html'
})
export class GodownUpdateComponent implements OnInit {
    private _godown: IGodown;
    isSaving: boolean;
    createdAt: string;
    updatedAt: string;

    constructor(private godownService: GodownService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ godown }) => {
            this.godown = godown;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.godown.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
        this.godown.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.godown.id !== undefined) {
            this.subscribeToSaveResponse(this.godownService.update(this.godown));
        } else {
            this.subscribeToSaveResponse(this.godownService.create(this.godown));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodown>>) {
        result.subscribe((res: HttpResponse<IGodown>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get godown() {
        return this._godown;
    }

    set godown(godown: IGodown) {
        this._godown = godown;
        this.createdAt = moment(godown.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(godown.updatedAt).format(DATE_TIME_FORMAT);
    }
}
