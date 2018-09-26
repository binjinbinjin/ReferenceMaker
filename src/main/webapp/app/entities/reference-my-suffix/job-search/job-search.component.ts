import { ReferenceMySuffixService } from 'app/entities/reference-my-suffix/reference-my-suffix.service';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';

@Component({
  selector: 'jhi-job-search',
  templateUrl: './job-search.component.html',
  styles: []
})
export class JobSearchComponent implements OnInit {

  applyTime: string;

  constructor(private referenceService: ReferenceMySuffixService) { }

  ngOnInit() {
  }

  save(reference) {
    this.applyTime = moment(reference.applyTime).format(DATE_TIME_FORMAT);
    reference.applyTime = moment(this.applyTime, DATE_TIME_FORMAT);
    this.subscribeToSaveResponse(this.referenceService.create(reference));
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IReferenceMySuffix>>) {
    result.subscribe((res: HttpResponse<IReferenceMySuffix>) => this.createReferencesuccessfully(), (res: HttpErrorResponse) => this.createReferenceunsuccessfully(res));
  }

  createReferencesuccessfully() {

  }
  createReferenceunsuccessfully(res: any) {
    alert('Faile');
    console.log(res);
  }
}
