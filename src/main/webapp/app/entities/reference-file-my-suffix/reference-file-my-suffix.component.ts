import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';
import { Principal } from 'app/core';
import { ReferenceFileMySuffixService } from './reference-file-my-suffix.service';

@Component({
    selector: 'jhi-reference-file-my-suffix',
    templateUrl: './reference-file-my-suffix.component.html'
})
export class ReferenceFileMySuffixComponent implements OnInit, OnDestroy {
    referenceFiles: IReferenceFileMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private referenceFileService: ReferenceFileMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.referenceFileService.query().subscribe(
            (res: HttpResponse<IReferenceFileMySuffix[]>) => {
                this.referenceFiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInReferenceFiles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IReferenceFileMySuffix) {
        return item.id;
    }

    registerChangeInReferenceFiles() {
        this.eventSubscriber = this.eventManager.subscribe('referenceFileListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
