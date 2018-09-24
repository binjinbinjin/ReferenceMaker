/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReferenceRecordTestModule } from '../../../test.module';
import { CoverLetterMySuffixComponent } from 'app/entities/cover-letter-my-suffix/cover-letter-my-suffix.component';
import { CoverLetterMySuffixService } from 'app/entities/cover-letter-my-suffix/cover-letter-my-suffix.service';
import { CoverLetterMySuffix } from 'app/shared/model/cover-letter-my-suffix.model';

describe('Component Tests', () => {
    describe('CoverLetterMySuffix Management Component', () => {
        let comp: CoverLetterMySuffixComponent;
        let fixture: ComponentFixture<CoverLetterMySuffixComponent>;
        let service: CoverLetterMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [CoverLetterMySuffixComponent],
                providers: []
            })
                .overrideTemplate(CoverLetterMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CoverLetterMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoverLetterMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CoverLetterMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.coverLetters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
