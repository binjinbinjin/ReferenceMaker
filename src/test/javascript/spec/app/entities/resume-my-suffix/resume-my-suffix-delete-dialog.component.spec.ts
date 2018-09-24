/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ReferenceRecordTestModule } from '../../../test.module';
import { ResumeMySuffixDeleteDialogComponent } from 'app/entities/resume-my-suffix/resume-my-suffix-delete-dialog.component';
import { ResumeMySuffixService } from 'app/entities/resume-my-suffix/resume-my-suffix.service';

describe('Component Tests', () => {
    describe('ResumeMySuffix Management Delete Component', () => {
        let comp: ResumeMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ResumeMySuffixDeleteDialogComponent>;
        let service: ResumeMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [ResumeMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(ResumeMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ResumeMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResumeMySuffixService);
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
