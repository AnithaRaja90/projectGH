import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPickList } from 'app/shared/model/pick-list.model';

type EntityResponseType = HttpResponse<IPickList>;
type EntityArrayResponseType = HttpResponse<IPickList[]>;

@Injectable({ providedIn: 'root' })
export class PickListService {
    private resourceUrl = SERVER_API_URL + 'api/pick-lists';

    constructor(private http: HttpClient) {}

    create(pickList: IPickList): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pickList);
        return this.http
            .post<IPickList>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(pickList: IPickList): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pickList);
        return this.http
            .put<IPickList>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPickList>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPickList[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(pickList: IPickList): IPickList {
        const copy: IPickList = Object.assign({}, pickList, {
            createdAt: pickList.createdAt != null && pickList.createdAt.isValid() ? pickList.createdAt.toJSON() : null,
            updatedAt: pickList.updatedAt != null && pickList.updatedAt.isValid() ? pickList.updatedAt.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
        res.body.updatedAt = res.body.updatedAt != null ? moment(res.body.updatedAt) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((pickList: IPickList) => {
            pickList.createdAt = pickList.createdAt != null ? moment(pickList.createdAt) : null;
            pickList.updatedAt = pickList.updatedAt != null ? moment(pickList.updatedAt) : null;
        });
        return res;
    }
}
