export interface ICoverLetterMySuffix {
    id?: number;
    name?: string;
}

export class CoverLetterMySuffix implements ICoverLetterMySuffix {
    constructor(public id?: number, public name?: string) {}
}
