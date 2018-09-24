import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';

@Component({
    selector: 'jhi-resume-my-suffix-detail',
    templateUrl: './resume-my-suffix-detail.component.html'
})
export class ResumeMySuffixDetailComponent implements OnInit {
    resume: IResumeMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resume }) => {
            this.resume = resume;
        });
    }

    previousState() {
        window.history.back();
    }
}
