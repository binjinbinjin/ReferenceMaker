/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReferenceRecordTestModule } from '../../../test.module';
import { ReferenceFileMySuffixDetailComponent } from 'app/entities/reference-file-my-suffix/reference-file-my-suffix-detail.component';
import { ReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';

describe('Component Tests', () => {
    describe('ReferenceFileMySuffix Management Detail Component', () => {
        let comp: ReferenceFileMySuffixDetailComponent;
        let fixture: ComponentFixture<ReferenceFileMySuffixDetailComponent>;
        const route = ({ data: of({ referenceFile: new ReferenceFileMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [ReferenceFileMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ReferenceFileMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReferenceFileMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.referenceFile).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
