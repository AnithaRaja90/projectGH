import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGodownStock } from 'app/shared/model/godown-stock.model';

type EntityResponseType = HttpResponse<IGodownStock>;
type EntityArrayResponseType = HttpResponse<IGodownStock[]>;

@Injectable({ providedIn: 'root' })
export class GodownStockService {
    private resourceUrl = SERVER_API_URL + 'api/godown-stocks';

    constructor(private http: HttpClient) {}

    create(godownStock: IGodownStock): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(godownStock);
        return this.http
            .post<IGodownStock>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(godownStock: IGodownStock): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(godownStock);
        return this.http
            .put<IGodownStock>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGodownStock>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGodownStock[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(godownStock: IGodownStock): IGodownStock {
        const copy: IGodownStock = Object.assign({}, godownStock, {
            createdAt: godownStock.createdAt != null && godownStock.createdAt.isValid() ? godownStock.createdAt.toJSON() : null,
            updatedAt: godownStock.updatedAt != null && godownStock.updatedAt.isValid() ? godownStock.updatedAt.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
        res.body.updatedAt = res.body.updatedAt != null ? moment(res.body.updatedAt) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((godownStock: IGodownStock) => {
            godownStock.createdAt = godownStock.createdAt != null ? moment(godownStock.createdAt) : null;
            godownStock.updatedAt = godownStock.updatedAt != null ? moment(godownStock.updatedAt) : null;
        });
        return res;
    }
}
