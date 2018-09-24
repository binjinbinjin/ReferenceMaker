/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ReferenceRecordTestModule } from '../../../test.module';
import { ReferenceFileMySuffixDeleteDialogComponent } from 'app/entities/reference-file-my-suffix/reference-file-my-suffix-delete-dialog.component';
import { ReferenceFileMySuffixService } from 'app/entities/reference-file-my-suffix/reference-file-my-suffix.service';

describe('Component Tests', () => {
    describe('ReferenceFileMySuffix Management Delete Component', () => {
        let comp: ReferenceFileMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ReferenceFileMySuffixDeleteDialogComponent>;
        let service: ReferenceFileMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [ReferenceFileMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(ReferenceFileMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReferenceFileMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReferenceFileMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
