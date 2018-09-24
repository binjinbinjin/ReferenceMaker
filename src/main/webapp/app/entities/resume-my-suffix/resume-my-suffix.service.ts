import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';

type EntityResponseType = HttpResponse<IResumeMySuffix>;
type EntityArrayResponseType = HttpResponse<IResumeMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class ResumeMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/resumes';

    constructor(private http: HttpClient) {}

    create(resume: IResumeMySuffix): Observable<EntityResponseType> {
        return this.http.post<IResumeMySuffix>(this.resourceUrl, resume, { observe: 'response' });
    }

    update(resume: IResumeMySuffix): Observable<EntityResponseType> {
        return this.http.put<IResumeMySuffix>(this.resourceUrl, resume, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IResumeMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IResumeMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
