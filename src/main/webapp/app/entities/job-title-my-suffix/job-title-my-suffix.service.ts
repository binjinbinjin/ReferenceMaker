import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';

type EntityResponseType = HttpResponse<IJobTitleMySuffix>;
type EntityArrayResponseType = HttpResponse<IJobTitleMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class JobTitleMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/job-titles';

    constructor(private http: HttpClient) {}

    create(jobTitle: IJobTitleMySuffix): Observable<EntityResponseType> {
        return this.http.post<IJobTitleMySuffix>(this.resourceUrl, jobTitle, { observe: 'response' });
    }

    update(jobTitle: IJobTitleMySuffix): Observable<EntityResponseType> {
        return this.http.put<IJobTitleMySuffix>(this.resourceUrl, jobTitle, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IJobTitleMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IJobTitleMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
