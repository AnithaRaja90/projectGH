import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INursery } from 'app/shared/model/nursery.model';

type EntityResponseType = HttpResponse<INursery>;
type EntityArrayResponseType = HttpResponse<INursery[]>;

@Injectable({ providedIn: 'root' })
export class NurseryService {
    private resourceUrl = SERVER_API_URL + 'api/nurseries';

    constructor(private http: HttpClient) {}

    create(nursery: INursery): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nursery);
        return this.http
            .post<INursery>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(nursery: INursery): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nursery);
        return this.http
            .put<INursery>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INursery>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INursery[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    getNurserys(sectorId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<INursery[]>(`${this.resourceUrl}/sector/${sectorId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(nursery: INursery): INursery {
        const copy: INursery = Object.assign({}, nursery, {
            createdAt: nursery.createdAt != null && nursery.createdAt.isValid() ? nursery.createdAt.toJSON() : null,
            updatedAt: nursery.updatedAt != null && nursery.updatedAt.isValid() ? nursery.updatedAt.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
        res.body.updatedAt = res.body.updatedAt != null ? moment(res.body.updatedAt) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((nursery: INursery) => {
            nursery.createdAt = nursery.createdAt != null ? moment(nursery.createdAt) : null;
            nursery.updatedAt = nursery.updatedAt != null ? moment(nursery.updatedAt) : null;
        });
        return res;
    }
}
