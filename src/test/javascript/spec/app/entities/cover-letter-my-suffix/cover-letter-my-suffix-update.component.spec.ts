/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReferenceRecordTestModule } from '../../../test.module';
import { CoverLetterMySuffixUpdateComponent } from 'app/entities/cover-letter-my-suffix/cover-letter-my-suffix-update.component';
import { CoverLetterMySuffixService } from 'app/entities/cover-letter-my-suffix/cover-letter-my-suffix.service';
import { CoverLetterMySuffix } from 'app/shared/model/cover-letter-my-suffix.model';

describe('Component Tests', () => {
    describe('CoverLetterMySuffix Management Update Component', () => {
        let comp: CoverLetterMySuffixUpdateComponent;
        let fixture: ComponentFixture<CoverLetterMySuffixUpdateComponent>;
        let service: CoverLetterMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [CoverLetterMySuffixUpdateComponent]
            })
                .overrideTemplate(CoverLetterMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CoverLetterMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoverLetterMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CoverLetterMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.coverLetter = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CoverLetterMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.coverLetter = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
