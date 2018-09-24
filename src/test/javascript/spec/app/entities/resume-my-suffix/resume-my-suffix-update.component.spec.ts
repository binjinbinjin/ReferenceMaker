/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReferenceRecordTestModule } from '../../../test.module';
import { ResumeMySuffixUpdateComponent } from 'app/entities/resume-my-suffix/resume-my-suffix-update.component';
import { ResumeMySuffixService } from 'app/entities/resume-my-suffix/resume-my-suffix.service';
import { ResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';

describe('Component Tests', () => {
    describe('ResumeMySuffix Management Update Component', () => {
        let comp: ResumeMySuffixUpdateComponent;
        let fixture: ComponentFixture<ResumeMySuffixUpdateComponent>;
        let service: ResumeMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [ResumeMySuffixUpdateComponent]
            })
                .overrideTemplate(ResumeMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ResumeMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResumeMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ResumeMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.resume = entity;
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
                    const entity = new ResumeMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.resume = entity;
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
