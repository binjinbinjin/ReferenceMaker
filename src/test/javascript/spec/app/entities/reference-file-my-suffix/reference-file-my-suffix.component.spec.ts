/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReferenceRecordTestModule } from '../../../test.module';
import { ReferenceFileMySuffixComponent } from 'app/entities/reference-file-my-suffix/reference-file-my-suffix.component';
import { ReferenceFileMySuffixService } from 'app/entities/reference-file-my-suffix/reference-file-my-suffix.service';
import { ReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';

describe('Component Tests', () => {
    describe('ReferenceFileMySuffix Management Component', () => {
        let comp: ReferenceFileMySuffixComponent;
        let fixture: ComponentFixture<ReferenceFileMySuffixComponent>;
        let service: ReferenceFileMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [ReferenceFileMySuffixComponent],
                providers: []
            })
                .overrideTemplate(ReferenceFileMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReferenceFileMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReferenceFileMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ReferenceFileMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.referenceFiles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
