import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISector } from 'app/shared/model/sector.model';

type EntityResponseType = HttpResponse<ISector>;
type EntityArrayResponseType = HttpResponse<ISector[]>;

@Injectable({ providedIn: 'root' })
export class SectorService {
    private resourceUrl = SERVER_API_URL + 'api/sectors';

    constructor(private http: HttpClient) {}

    create(sector: ISector): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sector);
        return this.http
            .post<ISector>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sector: ISector): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sector);
        return this.http
            .put<ISector>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISector>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISector[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(sector: ISector): ISector {
        const copy: ISector = Object.assign({}, sector, {
            createdAt: sector.createdAt != null && sector.createdAt.isValid() ? sector.createdAt.toJSON() : null,
            updatedAt: sector.updatedAt != null && sector.updatedAt.isValid() ? sector.updatedAt.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
        res.body.updatedAt = res.body.updatedAt != null ? moment(res.body.updatedAt) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((sector: ISector) => {
            sector.createdAt = sector.createdAt != null ? moment(sector.createdAt) : null;
            sector.updatedAt = sector.updatedAt != null ? moment(sector.updatedAt) : null;
        });
        return res;
    }
}
