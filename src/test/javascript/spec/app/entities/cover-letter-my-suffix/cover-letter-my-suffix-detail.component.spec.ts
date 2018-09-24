/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferenceRecordTestModule } from '../../../test.module';
import { CoverLetterMySuffixDetailComponent } from 'app/entities/cover-letter-my-suffix/cover-letter-my-suffix-detail.component';
import { CoverLetterMySuffix } from 'app/shared/model/cover-letter-my-suffix.model';

describe('Component Tests', () => {
    describe('CoverLetterMySuffix Management Detail Component', () => {
        let comp: CoverLetterMySuffixDetailComponent;
        let fixture: ComponentFixture<CoverLetterMySuffixDetailComponent>;
        const route = ({ data: of({ coverLetter: new CoverLetterMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [CoverLetterMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CoverLetterMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CoverLetterMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.coverLetter).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
