import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReferenceRecordSharedModule } from 'app/shared';
import {
    JobTitleMySuffixComponent,
    JobTitleMySuffixDetailComponent,
    JobTitleMySuffixUpdateComponent,
    JobTitleMySuffixDeletePopupComponent,
    JobTitleMySuffixDeleteDialogComponent,
    jobTitleRoute,
    jobTitlePopupRoute
} from './';

const ENTITY_STATES = [...jobTitleRoute, ...jobTitlePopupRoute];

@NgModule({
    imports: [ReferenceRecordSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JobTitleMySuffixComponent,
        JobTitleMySuffixDetailComponent,
        JobTitleMySuffixUpdateComponent,
        JobTitleMySuffixDeleteDialogComponent,
        JobTitleMySuffixDeletePopupComponent
    ],
    entryComponents: [
        JobTitleMySuffixComponent,
        JobTitleMySuffixUpdateComponent,
        JobTitleMySuffixDeleteDialogComponent,
        JobTitleMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReferenceRecordJobTitleMySuffixModule {}
