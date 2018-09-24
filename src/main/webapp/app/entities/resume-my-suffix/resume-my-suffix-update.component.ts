import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';
import { ResumeMySuffixService } from './resume-my-suffix.service';

@Component({
    selector: 'jhi-resume-my-suffix-update',
    templateUrl: './resume-my-suffix-update.component.html'
})
export class ResumeMySuffixUpdateComponent implements OnInit {
    private _resume: IResumeMySuffix;
    isSaving: boolean;

    constructor(private resumeService: ResumeMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ resume }) => {
            this.resume = resume;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.resume.id !== undefined) {
            this.subscribeToSaveResponse(this.resumeService.update(this.resume));
        } else {
            this.subscribeToSaveResponse(this.resumeService.create(this.resume));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResumeMySuffix>>) {
        result.subscribe((res: HttpResponse<IResumeMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get resume() {
        return this._resume;
    }

    set resume(resume: IResumeMySuffix) {
        this._resume = resume;
    }
}
