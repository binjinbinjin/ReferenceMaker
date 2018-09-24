/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReferenceRecordTestModule } from '../../../test.module';
import { ReferenceMySuffixUpdateComponent } from 'app/entities/reference-my-suffix/reference-my-suffix-update.component';
import { ReferenceMySuffixService } from 'app/entities/reference-my-suffix/reference-my-suffix.service';
import { ReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';

describe('Component Tests', () => {
    describe('ReferenceMySuffix Management Update Component', () => {
        let comp: ReferenceMySuffixUpdateComponent;
        let fixture: ComponentFixture<ReferenceMySuffixUpdateComponent>;
        let service: ReferenceMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReferenceRecordTestModule],
                declarations: [ReferenceMySuffixUpdateComponent]
            })
                .overrideTemplate(ReferenceMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReferenceMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReferenceMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ReferenceMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.reference = entity;
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
                    const entity = new ReferenceMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.reference = entity;
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
