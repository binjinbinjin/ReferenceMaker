import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';

@Component({
    selector: 'jhi-job-title-my-suffix-detail',
    templateUrl: './job-title-my-suffix-detail.component.html'
})
export class JobTitleMySuffixDetailComponent implements OnInit {
    jobTitle: IJobTitleMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobTitle }) => {
            this.jobTitle = jobTitle;
        });
    }

    previousState() {
        window.history.back();
    }
}
