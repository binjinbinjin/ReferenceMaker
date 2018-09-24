import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReferenceRecordSharedModule } from 'app/shared';
import {
    ReferenceFileMySuffixComponent,
    ReferenceFileMySuffixDetailComponent,
    ReferenceFileMySuffixUpdateComponent,
    ReferenceFileMySuffixDeletePopupComponent,
    ReferenceFileMySuffixDeleteDialogComponent,
    referenceFileRoute,
    referenceFilePopupRoute
} from './';

const ENTITY_STATES = [...referenceFileRoute, ...referenceFilePopupRoute];

@NgModule({
    imports: [ReferenceRecordSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReferenceFileMySuffixComponent,
        ReferenceFileMySuffixDetailComponent,
        ReferenceFileMySuffixUpdateComponent,
        ReferenceFileMySuffixDeleteDialogComponent,
        ReferenceFileMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ReferenceFileMySuffixComponent,
        ReferenceFileMySuffixUpdateComponent,
        ReferenceFileMySuffixDeleteDialogComponent,
        ReferenceFileMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReferenceRecordReferenceFileMySuffixModule {}
