import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMotherBed } from 'app/shared/model/mother-bed.model';

type EntityResponseType = HttpResponse<IMotherBed>;
type EntityArrayResponseType = HttpResponse<IMotherBed[]>;

@Injectable({ providedIn: 'root' })
export class MotherBedService {
    private resourceUrl = SERVER_API_URL + 'api/mother-beds';

    constructor(private http: HttpClient) {}

    create(motherBed: IMotherBed): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(motherBed);
        return this.http
            .post<IMotherBed>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(motherBed: IMotherBed): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(motherBed);
        return this.http
            .put<IMotherBed>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMotherBed>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMotherBed[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(motherBed: IMotherBed): IMotherBed {
        const copy: IMotherBed = Object.assign({}, motherBed, {
            createdAt: motherBed.createdAt != null && motherBed.createdAt.isValid() ? motherBed.createdAt.toJSON() : null,
            updatedAt: motherBed.updatedAt != null && motherBed.updatedAt.isValid() ? motherBed.updatedAt.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
        res.body.updatedAt = res.body.updatedAt != null ? moment(res.body.updatedAt) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((motherBed: IMotherBed) => {
            motherBed.createdAt = motherBed.createdAt != null ? moment(motherBed.createdAt) : null;
            motherBed.updatedAt = motherBed.updatedAt != null ? moment(motherBed.updatedAt) : null;
        });
        return res;
    }
}
