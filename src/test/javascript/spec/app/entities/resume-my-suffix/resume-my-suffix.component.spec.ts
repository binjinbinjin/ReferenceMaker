/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReferenceRecordTestModule } from '../../../test.module';
import { ResumeMySuffixComponent } from 'app/entities/resume-my-suffix/resume-my-suffix.component';
import { ResumeMySuffixService } from 'app/entities/resume-my-suffix/resume-my-suffix.service';
import { ResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';

describe('Component Tests', () => {
    describe('ResumeMySuffix Management Component', () => {
        let comp: ResumeMySuffixComponent;
        let fixture: ComponentFixture<ResumeMySuffixComponent>;
        let service: ResumeMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [ResumeMySuffixComponent],
                providers: []
            })
                .overrideTemplate(ResumeMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ResumeMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResumeMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ResumeMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.resumes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
