import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';

type EntityResponseType = HttpResponse<IReferenceMySuffix>;
type EntityArrayResponseType = HttpResponse<IReferenceMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class ReferenceMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/references';

    constructor(private http: HttpClient) {}

    create(reference: IReferenceMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(reference);
        return this.http
            .post<IReferenceMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(reference: IReferenceMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(reference);
        return this.http
            .put<IReferenceMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IReferenceMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IReferenceMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(reference: IReferenceMySuffix): IReferenceMySuffix {
        const copy: IReferenceMySuffix = Object.assign({}, reference, {
            applyTime: reference.applyTime != null && reference.applyTime.isValid() ? reference.applyTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.applyTime = res.body.applyTime != null ? moment(res.body.applyTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((reference: IReferenceMySuffix) => {
            reference.applyTime = reference.applyTime != null ? moment(reference.applyTime) : null;
        });
        return res;
    }
}
