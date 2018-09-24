import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';

type EntityResponseType = HttpResponse<IReferenceFileMySuffix>;
type EntityArrayResponseType = HttpResponse<IReferenceFileMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class ReferenceFileMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/reference-files';

    constructor(private http: HttpClient) {}

    create(referenceFile: IReferenceFileMySuffix): Observable<EntityResponseType> {
        return this.http.post<IReferenceFileMySuffix>(this.resourceUrl, referenceFile, { observe: 'response' });
    }

    update(referenceFile: IReferenceFileMySuffix): Observable<EntityResponseType> {
        return this.http.put<IReferenceFileMySuffix>(this.resourceUrl, referenceFile, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IReferenceFileMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IReferenceFileMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
