export interface IReferenceFileMySuffix {
    id?: number;
    file?: string;
}

export class ReferenceFileMySuffix implements IReferenceFileMySuffix {
    constructor(public id?: number, public file?: string) {}
}
