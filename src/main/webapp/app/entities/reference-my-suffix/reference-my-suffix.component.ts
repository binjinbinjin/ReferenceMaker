import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { ReferenceMySuffixService } from './reference-my-suffix.service';

@Component({
    selector: 'jhi-reference-my-suffix',
    templateUrl: './reference-my-suffix.component.html'
})
export class ReferenceMySuffixComponent implements OnInit, OnDestroy {
    @Input('showReference') showReference: boolean;
    references: IReferenceMySuffix[];
    allReferencesSubscribe: Subscription;
    searchReferenceSubscribe: Subscription;
    allReferences: IReferenceMySuffix[];
    searchReferences: IReferenceMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    queryCount: any;
    reverse: any;
    totalItems: number;

    constructor(
        private referenceService: ReferenceMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private parseLinks: JhiParseLinks,
        private principal: Principal
    ) {
        this.showReference = true;
        this.references = [];
        this.searchReferences = [];
        this.allReferences = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = true;
    }

    searchCompany(search: boolean, value: string) {
        if (search && value.trim().length > 0 ) {
            this.resetAll();
            this.loadSearch(value);
        } else {
            this.reset();
        }
    }

    loadSearch(search: string) {
        this.searchReferenceSubscribe = this.referenceService
            .findAllWithCompanyName(search, {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IReferenceMySuffix[]>) => this.paginateSearchReferences(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadAll() {
        if (!this.showReference) {
            this.references = [];
            return;
        }
        this.allReferencesSubscribe = this.referenceService
            .query({
                page: this.page,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IReferenceMySuffix[]>) => this.paginateReferences(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    reset() {
        this.resetAll();
        this.loadAll();
    }

    resetAll() {
        this.page = 0;
        this.references = [];
        this.searchReferences = [];
        this.allReferences = [];
    }

    loadPage(page) {
        this.page = page;
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInReferences();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IReferenceMySuffix) {
        return item.id;
    }

    registerChangeInReferences() {
        this.eventSubscriber = this.eventManager.subscribe('referenceListModification', response => this.reset());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateReferences(data: IReferenceMySuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            this.allReferences.push(data[i]);
        }
        this.references = this.allReferences;
        if (this.searchReferenceSubscribe) {
            this.searchReferenceSubscribe.unsubscribe();
            this.searchReferenceSubscribe = null;
        }
    }

    private paginateSearchReferences(data: IReferenceMySuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            this.searchReferences.push(data[i]);
        }
        this.references = this.searchReferences;
        if (this.allReferencesSubscribe) {
            this.allReferencesSubscribe.unsubscribe();
            this.allReferencesSubscribe = null;
        }
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
