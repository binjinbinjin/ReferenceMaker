import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';
import { Principal } from 'app/core';
import { ResumeMySuffixService } from './resume-my-suffix.service';

@Component({
    selector: 'jhi-resume-my-suffix',
    templateUrl: './resume-my-suffix.component.html'
})
export class ResumeMySuffixComponent implements OnInit, OnDestroy {
    resumes: IResumeMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private resumeService: ResumeMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.resumeService.query().subscribe(
            (res: HttpResponse<IResumeMySuffix[]>) => {
                this.resumes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInResumes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IResumeMySuffix) {
        return item.id;
    }

    registerChangeInResumes() {
        this.eventSubscriber = this.eventManager.subscribe('resumeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
