/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReferenceRecordTestModule } from '../../../test.module';
import { ReferenceFileMySuffixUpdateComponent } from 'app/entities/reference-file-my-suffix/reference-file-my-suffix-update.component';
import { ReferenceFileMySuffixService } from 'app/entities/reference-file-my-suffix/reference-file-my-suffix.service';
import { ReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';

describe('Component Tests', () => {
    describe('ReferenceFileMySuffix Management Update Component', () => {
        let comp: ReferenceFileMySuffixUpdateComponent;
        let fixture: ComponentFixture<ReferenceFileMySuffixUpdateComponent>;
        let service: ReferenceFileMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [ReferenceFileMySuffixUpdateComponent]
            })
                .overrideTemplate(ReferenceFileMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReferenceFileMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReferenceFileMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ReferenceFileMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.referenceFile = entity;
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
                    const entity = new ReferenceFileMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.referenceFile = entity;
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
