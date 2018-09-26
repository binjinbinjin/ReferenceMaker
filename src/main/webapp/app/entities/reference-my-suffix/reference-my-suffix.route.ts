import { JobSearchReferenceMakerComponent } from './job-search-reference-maker/job-search-reference-maker.component';
import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';
import { ReferenceMySuffixService } from './reference-my-suffix.service';
import { ReferenceMySuffixComponent } from './reference-my-suffix.component';
import { ReferenceMySuffixDetailComponent } from './reference-my-suffix-detail.component';
import { ReferenceMySuffixUpdateComponent } from './reference-my-suffix-update.component';
import { ReferenceMySuffixDeletePopupComponent } from './reference-my-suffix-delete-dialog.component';
import { IReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';
import { JobSearchComponent } from 'app/entities/reference-my-suffix/job-search/job-search.component';

@Injectable({ providedIn: 'root' })
export class ReferenceMySuffixResolve implements Resolve<IReferenceMySuffix> {
    constructor(private service: ReferenceMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((reference: HttpResponse<ReferenceMySuffix>) => reference.body));
        }
        return of(new ReferenceMySuffix());
    }
}

export const referenceRoute: Routes = [
    {
        path: 'reference-my-suffix/view-references',
        component: ReferenceMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.reference.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reference-my-suffix/:id/view',
        component: ReferenceMySuffixDetailComponent,
        resolve: {
            reference: ReferenceMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.reference.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reference-my-suffix/new',
        component: ReferenceMySuffixUpdateComponent,
        resolve: {
            reference: ReferenceMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.reference.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reference-my-suffix/:id/edit',
        component: ReferenceMySuffixUpdateComponent,
        resolve: {
            reference: ReferenceMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.reference.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reference-my-suffix',
        component: JobSearchComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.reference.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reference-my-suffix/reference-maker',
        component: JobSearchComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.reference.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
];

export const referencePopupRoute: Routes = [
    {
        path: 'reference-my-suffix/:id/delete',
        component: ReferenceMySuffixDeletePopupComponent,
        resolve: {
            reference: ReferenceMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.reference.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
