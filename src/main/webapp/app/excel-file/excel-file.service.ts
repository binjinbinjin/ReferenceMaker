import { HttpClient, HttpResponse} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { UploadFileInter, DownloadPathInter } from 'app/excel-file/excel-file-model';

@Injectable({
  providedIn: 'root'
})
export class ExcelFileService {
  private resourceUrl = SERVER_API_URL + 'api/excel-document';

  constructor(private httpClient: HttpClient) { }

  uploadExcelFile(file: File): Observable<HttpResponse<UploadFileInter>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<UploadFileInter>(this.resourceUrl + '/upload', formData, {observe: 'response'});

  }

  getExcelFile(): Observable<HttpResponse<DownloadPathInter>> {
    return this.httpClient.get<DownloadPathInter>(this.resourceUrl + '/download', {observe: 'response'});
  }
}
