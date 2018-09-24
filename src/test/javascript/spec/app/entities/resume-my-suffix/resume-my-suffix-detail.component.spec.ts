/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferenceRecordTestModule } from '../../../test.module';
import { ResumeMySuffixDetailComponent } from 'app/entities/resume-my-suffix/resume-my-suffix-detail.component';
import { ResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';

describe('Component Tests', () => {
    describe('ResumeMySuffix Management Detail Component', () => {
        let comp: ResumeMySuffixDetailComponent;
        let fixture: ComponentFixture<ResumeMySuffixDetailComponent>;
        const route = ({ data: of({ resume: new ResumeMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [ResumeMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ResumeMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ResumeMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.resume).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
