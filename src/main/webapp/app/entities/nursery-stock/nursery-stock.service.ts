import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INurseryStock } from 'app/shared/model/nursery-stock.model';

type EntityResponseType = HttpResponse<INurseryStock>;
type EntityArrayResponseType = HttpResponse<INurseryStock[]>;

@Injectable({ providedIn: 'root' })
export class NurseryStockService {
    private resourceUrl = SERVER_API_URL + 'api/nursery-stocks';

    constructor(private http: HttpClient) {}

    create(nurseryStock: INurseryStock): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nurseryStock);
        return this.http
            .post<INurseryStock>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(nurseryStock: INurseryStock): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nurseryStock);
        return this.http
            .put<INurseryStock>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INurseryStock>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INurseryStock[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(nurseryStock: INurseryStock): INurseryStock {
        const copy: INurseryStock = Object.assign({}, nurseryStock, {
            createdAt: nurseryStock.createdAt != null && nurseryStock.createdAt.isValid() ? nurseryStock.createdAt.toJSON() : null,
            updatedAt: nurseryStock.updatedAt != null && nurseryStock.updatedAt.isValid() ? nurseryStock.updatedAt.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
        res.body.updatedAt = res.body.updatedAt != null ? moment(res.body.updatedAt) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((nurseryStock: INurseryStock) => {
            nurseryStock.createdAt = nurseryStock.createdAt != null ? moment(nurseryStock.createdAt) : null;
            nurseryStock.updatedAt = nurseryStock.updatedAt != null ? moment(nurseryStock.updatedAt) : null;
        });
        return res;
    }
}
