import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICoverLetterMySuffix } from 'app/shared/model/cover-letter-my-suffix.model';
import { CoverLetterMySuffixService } from './cover-letter-my-suffix.service';

@Component({
    selector: 'jhi-cover-letter-my-suffix-update',
    templateUrl: './cover-letter-my-suffix-update.component.html'
})
export class CoverLetterMySuffixUpdateComponent implements OnInit {
    private _coverLetter: ICoverLetterMySuffix;
    isSaving: boolean;

    constructor(private coverLetterService: CoverLetterMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ coverLetter }) => {
            this.coverLetter = coverLetter;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.coverLetter.id !== undefined) {
            this.subscribeToSaveResponse(this.coverLetterService.update(this.coverLetter));
        } else {
            this.subscribeToSaveResponse(this.coverLetterService.create(this.coverLetter));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICoverLetterMySuffix>>) {
        result.subscribe((res: HttpResponse<ICoverLetterMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get coverLetter() {
        return this._coverLetter;
    }

    set coverLetter(coverLetter: ICoverLetterMySuffix) {
        this._coverLetter = coverLetter;
    }
}
