export interface IJobTitleMySuffix {
    id?: number;
    jobTitle?: string;
}

export class JobTitleMySuffix implements IJobTitleMySuffix {
    constructor(public id?: number, public jobTitle?: string) {}
}
