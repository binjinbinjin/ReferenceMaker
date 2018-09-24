/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferenceRecordTestModule } from '../../../test.module';
import { ReferenceMySuffixDetailComponent } from 'app/entities/reference-my-suffix/reference-my-suffix-detail.component';
import { ReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';

describe('Component Tests', () => {
    describe('ReferenceMySuffix Management Detail Component', () => {
        let comp: ReferenceMySuffixDetailComponent;
        let fixture: ComponentFixture<ReferenceMySuffixDetailComponent>;
        const route = ({ data: of({ reference: new ReferenceMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [ReferenceMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ReferenceMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReferenceMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.reference).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
