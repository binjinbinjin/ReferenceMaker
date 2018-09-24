/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReferenceRecordTestModule } from '../../../test.module';
import { JobTitleMySuffixComponent } from 'app/entities/job-title-my-suffix/job-title-my-suffix.component';
import { JobTitleMySuffixService } from 'app/entities/job-title-my-suffix/job-title-my-suffix.service';
import { JobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';

describe('Component Tests', () => {
    describe('JobTitleMySuffix Management Component', () => {
        let comp: JobTitleMySuffixComponent;
        let fixture: ComponentFixture<JobTitleMySuffixComponent>;
        let service: JobTitleMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [JobTitleMySuffixComponent],
                providers: []
            })
                .overrideTemplate(JobTitleMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(JobTitleMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobTitleMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new JobTitleMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.jobTitles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
