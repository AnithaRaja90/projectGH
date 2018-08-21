import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';

type EntityResponseType = HttpResponse<IPickListValue>;
type EntityArrayResponseType = HttpResponse<IPickListValue[]>;

@Injectable({ providedIn: 'root' })
export class PickListValueService {
    private resourceUrl = SERVER_API_URL + 'api/pick-list-values';

    constructor(private http: HttpClient) {}

    create(pickListValue: IPickListValue): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pickListValue);
        return this.http
            .post<IPickListValue>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(pickListValue: IPickListValue): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pickListValue);
        return this.http
            .put<IPickListValue>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPickListValue>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPickListValue[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    getVariety(pickListId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IPickListValue[]>(`${this.resourceUrl}/variety/${pickListId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    getCategory(pickValueId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IPickListValue[]>(`${this.resourceUrl}/category/${pickValueId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(pickListValue: IPickListValue): IPickListValue {
        const copy: IPickListValue = Object.assign({}, pickListValue, {
            createdAt: pickListValue.createdAt != null && pickListValue.createdAt.isValid() ? pickListValue.createdAt.toJSON() : null,
            updatedAt: pickListValue.updatedAt != null && pickListValue.updatedAt.isValid() ? pickListValue.updatedAt.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
        res.body.updatedAt = res.body.updatedAt != null ? moment(res.body.updatedAt) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((pickListValue: IPickListValue) => {
            pickListValue.createdAt = pickListValue.createdAt != null ? moment(pickListValue.createdAt) : null;
            pickListValue.updatedAt = pickListValue.updatedAt != null ? moment(pickListValue.updatedAt) : null;
        });
        return res;
    }
}
