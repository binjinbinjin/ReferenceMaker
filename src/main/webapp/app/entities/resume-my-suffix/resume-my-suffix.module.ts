import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReferenceRecordSharedModule } from 'app/shared';
import {
    ResumeMySuffixComponent,
    ResumeMySuffixDetailComponent,
    ResumeMySuffixUpdateComponent,
    ResumeMySuffixDeletePopupComponent,
    ResumeMySuffixDeleteDialogComponent,
    resumeRoute,
    resumePopupRoute
} from './';

const ENTITY_STATES = [...resumeRoute, ...resumePopupRoute];

@NgModule({
    imports: [ReferenceRecordSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResumeMySuffixComponent,
        ResumeMySuffixDetailComponent,
        ResumeMySuffixUpdateComponent,
        ResumeMySuffixDeleteDialogComponent,
        ResumeMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ResumeMySuffixComponent,
        ResumeMySuffixUpdateComponent,
        ResumeMySuffixDeleteDialogComponent,
        ResumeMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReferenceRecordResumeMySuffixModule {}
