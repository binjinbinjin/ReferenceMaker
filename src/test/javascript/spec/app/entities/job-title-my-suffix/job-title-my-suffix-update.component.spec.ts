/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReferenceRecordTestModule } from '../../../test.module';
import { JobTitleMySuffixUpdateComponent } from 'app/entities/job-title-my-suffix/job-title-my-suffix-update.component';
import { JobTitleMySuffixService } from 'app/entities/job-title-my-suffix/job-title-my-suffix.service';
import { JobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';

describe('Component Tests', () => {
    describe('JobTitleMySuffix Management Update Component', () => {
        let comp: JobTitleMySuffixUpdateComponent;
        let fixture: ComponentFixture<JobTitleMySuffixUpdateComponent>;
        let service: JobTitleMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [JobTitleMySuffixUpdateComponent]
            })
                .overrideTemplate(JobTitleMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(JobTitleMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobTitleMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new JobTitleMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.jobTitle = entity;
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
                    const entity = new JobTitleMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.jobTitle = entity;
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
