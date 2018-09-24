import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';
import { ReferenceMySuffixService } from './reference-my-suffix.service';
import { ILocationMySuffix } from 'app/shared/model/location-my-suffix.model';
import { LocationMySuffixService } from 'app/entities/location-my-suffix';
import { IResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';
import { ResumeMySuffixService } from 'app/entities/resume-my-suffix';
import { ICoverLetterMySuffix } from 'app/shared/model/cover-letter-my-suffix.model';
import { CoverLetterMySuffixService } from 'app/entities/cover-letter-my-suffix';
import { IReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';
import { ReferenceFileMySuffixService } from 'app/entities/reference-file-my-suffix';
import { IJobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';
import { JobTitleMySuffixService } from 'app/entities/job-title-my-suffix';

@Component({
    selector: 'jhi-reference-my-suffix-update',
    templateUrl: './reference-my-suffix-update.component.html'
})
export class ReferenceMySuffixUpdateComponent implements OnInit {
    private _reference: IReferenceMySuffix;
    isSaving: boolean;

    locations: ILocationMySuffix[];

    resumes: IResumeMySuffix[];

    coverletters: ICoverLetterMySuffix[];

    referencefiles: IReferenceFileMySuffix[];

    jobtitles: IJobTitleMySuffix[];
    applyTime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private referenceService: ReferenceMySuffixService,
        private locationService: LocationMySuffixService,
        private resumeService: ResumeMySuffixService,
        private coverLetterService: CoverLetterMySuffixService,
        private referenceFileService: ReferenceFileMySuffixService,
        private jobTitleService: JobTitleMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ reference }) => {
            this.reference = reference;
        });
        this.locationService.query().subscribe(
            (res: HttpResponse<ILocationMySuffix[]>) => {
                this.locations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.resumeService.query().subscribe(
            (res: HttpResponse<IResumeMySuffix[]>) => {
                this.resumes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.coverLetterService.query().subscribe(
            (res: HttpResponse<ICoverLetterMySuffix[]>) => {
                this.coverletters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.referenceFileService.query().subscribe(
            (res: HttpResponse<IReferenceFileMySuffix[]>) => {
                this.referencefiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.jobTitleService.query().subscribe(
            (res: HttpResponse<IJobTitleMySuffix[]>) => {
                this.jobtitles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.reference.applyTime = moment(this.applyTime, DATE_TIME_FORMAT);
        if (this.reference.id !== undefined) {
            this.subscribeToSaveResponse(this.referenceService.update(this.reference));
        } else {
            this.subscribeToSaveResponse(this.referenceService.create(this.reference));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IReferenceMySuffix>>) {
        result.subscribe((res: HttpResponse<IReferenceMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackLocationById(index: number, item: ILocationMySuffix) {
        return item.id;
    }

    trackResumeById(index: number, item: IResumeMySuffix) {
        return item.id;
    }

    trackCoverLetterById(index: number, item: ICoverLetterMySuffix) {
        return item.id;
    }

    trackReferenceFileById(index: number, item: IReferenceFileMySuffix) {
        return item.id;
    }

    trackJobTitleById(index: number, item: IJobTitleMySuffix) {
        return item.id;
    }
    get reference() {
        return this._reference;
    }

    set reference(reference: IReferenceMySuffix) {
        this._reference = reference;
        this.applyTime = moment(reference.applyTime).format(DATE_TIME_FORMAT);
    }
}
