import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';

@Component({
    selector: 'jhi-reference-file-my-suffix-detail',
    templateUrl: './reference-file-my-suffix-detail.component.html'
})
export class ReferenceFileMySuffixDetailComponent implements OnInit {
    referenceFile: IReferenceFileMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ referenceFile }) => {
            this.referenceFile = referenceFile;
        });
    }

    previousState() {
        window.history.back();
    }
}
