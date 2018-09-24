/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ReferenceRecordTestModule } from '../../../test.module';
import { CoverLetterMySuffixDeleteDialogComponent } from 'app/entities/cover-letter-my-suffix/cover-letter-my-suffix-delete-dialog.component';
import { CoverLetterMySuffixService } from 'app/entities/cover-letter-my-suffix/cover-letter-my-suffix.service';

describe('Component Tests', () => {
    describe('CoverLetterMySuffix Management Delete Component', () => {
        let comp: CoverLetterMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<CoverLetterMySuffixDeleteDialogComponent>;
        let service: CoverLetterMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [CoverLetterMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(CoverLetterMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CoverLetterMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoverLetterMySuffixService);
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
