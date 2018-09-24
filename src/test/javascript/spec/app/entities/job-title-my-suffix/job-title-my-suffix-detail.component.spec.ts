/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferenceRecordTestModule } from '../../../test.module';
import { JobTitleMySuffixDetailComponent } from 'app/entities/job-title-my-suffix/job-title-my-suffix-detail.component';
import { JobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';

describe('Component Tests', () => {
    describe('JobTitleMySuffix Management Detail Component', () => {
        let comp: JobTitleMySuffixDetailComponent;
        let fixture: ComponentFixture<JobTitleMySuffixDetailComponent>;
        const route = ({ data: of({ jobTitle: new JobTitleMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [JobTitleMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(JobTitleMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(JobTitleMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.jobTitle).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
