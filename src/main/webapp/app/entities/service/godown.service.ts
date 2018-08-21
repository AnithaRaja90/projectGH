import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGodown } from 'app/shared/model/godown.model';

type EntityResponseType = HttpResponse<IGodown>;
type EntityArrayResponseType = HttpResponse<IGodown[]>;

@Injectable({ providedIn: 'root' })
export class GodownService {
    private resourceUrl = SERVER_API_URL + 'api/godowns';

    constructor(private http: HttpClient) {}

    create(godown: IGodown): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(godown);
        return this.http
            .post<IGodown>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(godown: IGodown): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(godown);
        return this.http
            .put<IGodown>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGodown>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGodown[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(godown: IGodown): IGodown {
        const copy: IGodown = Object.assign({}, godown, {
            createdAt: godown.createdAt != null && godown.createdAt.isValid() ? godown.createdAt.toJSON() : null,
            updatedAt: godown.updatedAt != null && godown.updatedAt.isValid() ? godown.updatedAt.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
        res.body.updatedAt = res.body.updatedAt != null ? moment(res.body.updatedAt) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((godown: IGodown) => {
            godown.createdAt = godown.createdAt != null ? moment(godown.createdAt) : null;
            godown.updatedAt = godown.updatedAt != null ? moment(godown.updatedAt) : null;
        });
        return res;
    }
}
