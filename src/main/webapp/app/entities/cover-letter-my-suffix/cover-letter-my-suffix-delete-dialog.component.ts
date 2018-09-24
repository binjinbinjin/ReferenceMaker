import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICoverLetterMySuffix } from 'app/shared/model/cover-letter-my-suffix.model';
import { CoverLetterMySuffixService } from './cover-letter-my-suffix.service';

@Component({
    selector: 'jhi-cover-letter-my-suffix-delete-dialog',
    templateUrl: './cover-letter-my-suffix-delete-dialog.component.html'
})
export class CoverLetterMySuffixDeleteDialogComponent {
    coverLetter: ICoverLetterMySuffix;

    constructor(
        private coverLetterService: CoverLetterMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.coverLetterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'coverLetterListModification',
                content: 'Deleted an coverLetter'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cover-letter-my-suffix-delete-popup',
    template: ''
})
export class CoverLetterMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coverLetter }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CoverLetterMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.coverLetter = coverLetter;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
