export interface IResumeMySuffix {
    id?: number;
    name?: string;
}

export class ResumeMySuffix implements IResumeMySuffix {
    constructor(public id?: number, public name?: string) {}
}
