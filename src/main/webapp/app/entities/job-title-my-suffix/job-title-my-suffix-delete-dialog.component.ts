import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';
import { JobTitleMySuffixService } from './job-title-my-suffix.service';

@Component({
    selector: 'jhi-job-title-my-suffix-delete-dialog',
    templateUrl: './job-title-my-suffix-delete-dialog.component.html'
})
export class JobTitleMySuffixDeleteDialogComponent {
    jobTitle: IJobTitleMySuffix;

    constructor(
        private jobTitleService: JobTitleMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jobTitleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jobTitleListModification',
                content: 'Deleted an jobTitle'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-job-title-my-suffix-delete-popup',
    template: ''
})
export class JobTitleMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobTitle }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(JobTitleMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.jobTitle = jobTitle;
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
