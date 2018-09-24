import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';
import { ReferenceFileMySuffixService } from './reference-file-my-suffix.service';

@Component({
    selector: 'jhi-reference-file-my-suffix-update',
    templateUrl: './reference-file-my-suffix-update.component.html'
})
export class ReferenceFileMySuffixUpdateComponent implements OnInit {
    private _referenceFile: IReferenceFileMySuffix;
    isSaving: boolean;

    constructor(private referenceFileService: ReferenceFileMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ referenceFile }) => {
            this.referenceFile = referenceFile;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.referenceFile.id !== undefined) {
            this.subscribeToSaveResponse(this.referenceFileService.update(this.referenceFile));
        } else {
            this.subscribeToSaveResponse(this.referenceFileService.create(this.referenceFile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IReferenceFileMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IReferenceFileMySuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get referenceFile() {
        return this._referenceFile;
    }

    set referenceFile(referenceFile: IReferenceFileMySuffix) {
        this._referenceFile = referenceFile;
    }
}
