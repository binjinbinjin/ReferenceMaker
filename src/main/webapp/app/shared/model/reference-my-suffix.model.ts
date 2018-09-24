import { Moment } from 'moment';

export interface IReferenceMySuffix {
    id?: number;
    company?: string;
    applyTime?: Moment;
    locationId?: number;
    resumeId?: number;
    coverId?: number;
    referenceFileId?: number;
    jobTitleId?: number;
}

export class ReferenceMySuffix implements IReferenceMySuffix {
    constructor(
        public id?: number,
        public company?: string,
        public applyTime?: Moment,
        public locationId?: number,
        public resumeId?: number,
        public coverId?: number,
        public referenceFileId?: number,
        public jobTitleId?: number
    ) {}
}
