import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';
import { ReferenceFileMySuffixService } from './reference-file-my-suffix.service';

@Component({
    selector: 'jhi-reference-file-my-suffix-delete-dialog',
    templateUrl: './reference-file-my-suffix-delete-dialog.component.html'
})
export class ReferenceFileMySuffixDeleteDialogComponent {
    referenceFile: IReferenceFileMySuffix;

    constructor(
        private referenceFileService: ReferenceFileMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.referenceFileService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'referenceFileListModification',
                content: 'Deleted an referenceFile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-reference-file-my-suffix-delete-popup',
    template: ''
})
export class ReferenceFileMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ referenceFile }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ReferenceFileMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.referenceFile = referenceFile;
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
