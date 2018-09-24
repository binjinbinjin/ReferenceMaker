import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IJobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';
import { Principal } from 'app/core';
import { JobTitleMySuffixService } from './job-title-my-suffix.service';

@Component({
    selector: 'jhi-job-title-my-suffix',
    templateUrl: './job-title-my-suffix.component.html'
})
export class JobTitleMySuffixComponent implements OnInit, OnDestroy {
    jobTitles: IJobTitleMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jobTitleService: JobTitleMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.jobTitleService.query().subscribe(
            (res: HttpResponse<IJobTitleMySuffix[]>) => {
                this.jobTitles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInJobTitles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IJobTitleMySuffix) {
        return item.id;
    }

    registerChangeInJobTitles() {
        this.eventSubscriber = this.eventManager.subscribe('jobTitleListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
