import { ReferenceMySuffixService } from 'app/entities/reference-my-suffix/reference-my-suffix.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import * as moment from 'moment';
import { DATE_TIME_FORMAT, JhiAlertErrorComponent } from 'app/shared';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';
import { ModalAlertComponent } from 'app/entities/reference-my-suffix/modal-alert/modal-alert.component';

@Component({
  selector: 'jhi-job-search',
  templateUrl: './job-search.component.html',
  styles: []
})
export class JobSearchComponent implements OnInit {

  applyTime: string;
  search: boolean;
  saveSuccessfully: string;
  @ViewChild('alert') modal: ModalAlertComponent;
  constructor(private referenceService: ReferenceMySuffixService) {
    this.search = false;
    this.saveSuccessfully = '';
  }

  ngOnInit() {
  }

  save(reference) {
    this.applyTime = moment(reference.applyTime).format(DATE_TIME_FORMAT);
    reference.applyTime = moment(this.applyTime, DATE_TIME_FORMAT);
    this.subscribeToSaveResponse(this.referenceService.create(reference));
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IReferenceMySuffix>>) {
    result.toPromise().then((res: HttpResponse<IReferenceMySuffix>) => this.createReferencesuccessfully(res)).catch((res: HttpErrorResponse) => this.createReferenceunsuccessfully(res));
    // result.subscribe((res: HttpResponse<IReferenceMySuffix>) => this.createReferencesuccessfully(res), (res: HttpErrorResponse) => this.createReferenceunsuccessfully(res));
  }

  createReferencesuccessfully(res: HttpResponse<IReferenceMySuffix>) {
    this.saveSuccessfully = 'Reference have been saved';
    this.modal.setModalInfoAndOpen(this.saveSuccessfully);
  }
  createReferenceunsuccessfully(res: any) {
    alert('Faile');
    console.log(res);
  }
}
