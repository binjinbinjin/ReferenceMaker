import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICoverLetterMySuffix } from 'app/shared/model/cover-letter-my-suffix.model';

@Component({
    selector: 'jhi-cover-letter-my-suffix-detail',
    templateUrl: './cover-letter-my-suffix-detail.component.html'
})
export class CoverLetterMySuffixDetailComponent implements OnInit {
    coverLetter: ICoverLetterMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coverLetter }) => {
            this.coverLetter = coverLetter;
        });
    }

    previousState() {
        window.history.back();
    }
}
