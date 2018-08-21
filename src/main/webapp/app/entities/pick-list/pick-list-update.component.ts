import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPickList } from 'app/shared/model/pick-list.model';
import { PickListService } from './pick-list.service';

@Component({
    selector: 'jhi-pick-list-update',
    templateUrl: './pick-list-update.component.html'
})
export class PickListUpdateComponent implements OnInit {
    private _pickList: IPickList;
    isSaving: boolean;
    createdAt: string;
    updatedAt: string;

    constructor(private pickListService: PickListService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pickList }) => {
            this.pickList = pickList;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.pickList.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
        this.pickList.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.pickList.id !== undefined) {
            this.subscribeToSaveResponse(this.pickListService.update(this.pickList));
        } else {
            this.subscribeToSaveResponse(this.pickListService.create(this.pickList));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPickList>>) {
        result.subscribe((res: HttpResponse<IPickList>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get pickList() {
        return this._pickList;
    }

    set pickList(pickList: IPickList) {
        this._pickList = pickList;
        this.createdAt = moment(pickList.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(pickList.updatedAt).format(DATE_TIME_FORMAT);
    }
}
