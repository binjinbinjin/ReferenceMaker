import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReferenceRecordSharedModule } from 'app/shared';
import {
    CoverLetterMySuffixComponent,
    CoverLetterMySuffixDetailComponent,
    CoverLetterMySuffixUpdateComponent,
    CoverLetterMySuffixDeletePopupComponent,
    CoverLetterMySuffixDeleteDialogComponent,
    coverLetterRoute,
    coverLetterPopupRoute
} from './';

const ENTITY_STATES = [...coverLetterRoute, ...coverLetterPopupRoute];

@NgModule({
    imports: [ReferenceRecordSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CoverLetterMySuffixComponent,
        CoverLetterMySuffixDetailComponent,
        CoverLetterMySuffixUpdateComponent,
        CoverLetterMySuffixDeleteDialogComponent,
        CoverLetterMySuffixDeletePopupComponent
    ],
    entryComponents: [
        CoverLetterMySuffixComponent,
        CoverLetterMySuffixUpdateComponent,
        CoverLetterMySuffixDeleteDialogComponent,
        CoverLetterMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReferenceRecordCoverLetterMySuffixModule {}
