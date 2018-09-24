import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';
import { ResumeMySuffixService } from './resume-my-suffix.service';

@Component({
    selector: 'jhi-resume-my-suffix-delete-dialog',
    templateUrl: './resume-my-suffix-delete-dialog.component.html'
})
export class ResumeMySuffixDeleteDialogComponent {
    resume: IResumeMySuffix;

    constructor(private resumeService: ResumeMySuffixService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.resumeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'resumeListModification',
                content: 'Deleted an resume'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resume-my-suffix-delete-popup',
    template: ''
})
export class ResumeMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resume }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ResumeMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.resume = resume;
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
