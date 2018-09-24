import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ReferenceRecordReferenceMySuffixModule } from './reference-my-suffix/reference-my-suffix.module';
import { ReferenceRecordJobTitleMySuffixModule } from './job-title-my-suffix/job-title-my-suffix.module';
import { ReferenceRecordLocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { ReferenceRecordResumeMySuffixModule } from './resume-my-suffix/resume-my-suffix.module';
import { ReferenceRecordCoverLetterMySuffixModule } from './cover-letter-my-suffix/cover-letter-my-suffix.module';
import { ReferenceRecordReferenceFileMySuffixModule } from './reference-file-my-suffix/reference-file-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ReferenceRecordReferenceMySuffixModule,
        ReferenceRecordJobTitleMySuffixModule,
        ReferenceRecordLocationMySuffixModule,
        ReferenceRecordResumeMySuffixModule,
        ReferenceRecordCoverLetterMySuffixModule,
        ReferenceRecordReferenceFileMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReferenceRecordEntityModule {}
