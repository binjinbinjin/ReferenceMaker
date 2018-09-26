import { FormsModule } from '@angular/forms';
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
import { CompanySearchComponent } from './company-search/company-search.component';
import { DislayReferenceComponent } from './dislay-reference/dislay-reference.component';
import { JobSearchComponent } from './job-search/job-search.component';
import { JobSearchReferenceMakerComponent } from './job-search-reference-maker/job-search-reference-maker.component';

const ENTITY_STATES = [...referenceRoute, ...referencePopupRoute];

@NgModule({
    imports: [ReferenceRecordSharedModule, RouterModule.forChild(ENTITY_STATES), FormsModule],
    declarations: [
        ReferenceMySuffixComponent,
        ReferenceMySuffixDetailComponent,
        ReferenceMySuffixUpdateComponent,
        ReferenceMySuffixDeleteDialogComponent,
        ReferenceMySuffixDeletePopupComponent,
        CompanySearchComponent,
        DislayReferenceComponent,
        JobSearchComponent,
        JobSearchReferenceMakerComponent,

    ],
    entryComponents: [
        ReferenceMySuffixComponent,
        ReferenceMySuffixUpdateComponent,
        ReferenceMySuffixDeleteDialogComponent,
        ReferenceMySuffixDeletePopupComponent,
        JobSearchReferenceMakerComponent,
        JobSearchComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReferenceRecordReferenceMySuffixModule {}
