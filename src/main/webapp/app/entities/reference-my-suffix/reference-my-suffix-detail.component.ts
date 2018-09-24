import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';

@Component({
    selector: 'jhi-reference-my-suffix-detail',
    templateUrl: './reference-my-suffix-detail.component.html'
})
export class ReferenceMySuffixDetailComponent implements OnInit {
    reference: IReferenceMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ reference }) => {
            this.reference = reference;
        });
    }

    previousState() {
        window.history.back();
    }
}
