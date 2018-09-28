import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReferenceRecordSharedModule } from 'app/shared';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { ReferenceMySuffixComponent, ReferenceMySuffixDeleteDialogComponent, ReferenceMySuffixDeletePopupComponent, ReferenceMySuffixDetailComponent, ReferenceMySuffixUpdateComponent, referencePopupRoute, referenceRoute } from './';
import { CompanySearchComponent } from './company-search/company-search.component';
import { DislayReferenceComponent } from './dislay-reference/dislay-reference.component';
import { DisplayPdfComponent } from './display-pdf/display-pdf.component';
import { JobSearchReferenceMakerComponent } from './job-search-reference-maker/job-search-reference-maker.component';
import { JobSearchComponent } from './job-search/job-search.component';

const ENTITY_STATES = [...referenceRoute, ...referencePopupRoute];

@NgModule({
    imports: [ReferenceRecordSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        FormsModule,
        PdfViewerModule],
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
        DisplayPdfComponent,

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
