import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IJobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';
import { JobTitleMySuffixService } from './job-title-my-suffix.service';

@Component({
    selector: 'jhi-job-title-my-suffix-update',
    templateUrl: './job-title-my-suffix-update.component.html'
})
export class JobTitleMySuffixUpdateComponent implements OnInit {
    private _jobTitle: IJobTitleMySuffix;
    isSaving: boolean;

    constructor(private jobTitleService: JobTitleMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jobTitle }) => {
            this.jobTitle = jobTitle;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.jobTitle.id !== undefined) {
            this.subscribeToSaveResponse(this.jobTitleService.update(this.jobTitle));
        } else {
            this.subscribeToSaveResponse(this.jobTitleService.create(this.jobTitle));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IJobTitleMySuffix>>) {
        result.subscribe((res: HttpResponse<IJobTitleMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get jobTitle() {
        return this._jobTitle;
    }

    set jobTitle(jobTitle: IJobTitleMySuffix) {
        this._jobTitle = jobTitle;
    }
}
