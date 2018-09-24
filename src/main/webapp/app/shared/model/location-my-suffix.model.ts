export interface ILocationMySuffix {
    id?: number;
    location?: string;
}

export class LocationMySuffix implements ILocationMySuffix {
    constructor(public id?: number, public location?: string) {}
}
