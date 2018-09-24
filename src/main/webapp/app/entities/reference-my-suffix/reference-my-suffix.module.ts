import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReferenceRecordSharedModule } from 'app/shared';
import {
    ReferenceMySuffixComponent,
    ReferenceMySuffixDetailComponent,
    ReferenceMySuffixUpdateComponent,
    ReferenceMySuffixDeletePopupComponent,
    ReferenceMySuffixDeleteDialogComponent,
    referenceRoute,
    referencePopupRoute
} from './';

const ENTITY_STATES = [...referenceRoute, ...referencePopupRoute];

@NgModule({
    imports: [ReferenceRecordSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReferenceMySuffixComponent,
        ReferenceMySuffixDetailComponent,
        ReferenceMySuffixUpdateComponent,
        ReferenceMySuffixDeleteDialogComponent,
        ReferenceMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ReferenceMySuffixComponent,
        ReferenceMySuffixUpdateComponent,
        ReferenceMySuffixDeleteDialogComponent,
        ReferenceMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReferenceRecordReferenceMySuffixModule {}
