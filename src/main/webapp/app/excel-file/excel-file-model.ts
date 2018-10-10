
export interface UploadFileInter {
    result?: boolean;
    message?: string;
}

export class UploadFile implements UploadFileInter {
    constructor(public result?: boolean, public message?: string) {}
}

export interface DownloadPathInter {
    path: string;
}

export class DownloadPath {
    constructor(public path: string) {}
}
