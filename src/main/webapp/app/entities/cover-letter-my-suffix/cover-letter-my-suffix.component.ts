import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICoverLetterMySuffix } from 'app/shared/model/cover-letter-my-suffix.model';
import { Principal } from 'app/core';
import { CoverLetterMySuffixService } from './cover-letter-my-suffix.service';

@Component({
    selector: 'jhi-cover-letter-my-suffix',
    templateUrl: './cover-letter-my-suffix.component.html'
})
export class CoverLetterMySuffixComponent implements OnInit, OnDestroy {
    coverLetters: ICoverLetterMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private coverLetterService: CoverLetterMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.coverLetterService.query().subscribe(
            (res: HttpResponse<ICoverLetterMySuffix[]>) => {
                this.coverLetters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCoverLetters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICoverLetterMySuffix) {
        return item.id;
    }

    registerChangeInCoverLetters() {
        this.eventSubscriber = this.eventManager.subscribe('coverLetterListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
