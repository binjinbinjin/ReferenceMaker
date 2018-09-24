import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';
import { ReferenceFileMySuffixService } from './reference-file-my-suffix.service';
import { ReferenceFileMySuffixComponent } from './reference-file-my-suffix.component';
import { ReferenceFileMySuffixDetailComponent } from './reference-file-my-suffix-detail.component';
import { ReferenceFileMySuffixUpdateComponent } from './reference-file-my-suffix-update.component';
import { ReferenceFileMySuffixDeletePopupComponent } from './reference-file-my-suffix-delete-dialog.component';
import { IReferenceFileMySuffix } from 'app/shared/model/reference-file-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class ReferenceFileMySuffixResolve implements Resolve<IReferenceFileMySuffix> {
    constructor(private service: ReferenceFileMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((referenceFile: HttpResponse<ReferenceFileMySuffix>) => referenceFile.body));
        }
        return of(new ReferenceFileMySuffix());
    }
}

export const referenceFileRoute: Routes = [
    {
        path: 'reference-file-my-suffix',
        component: ReferenceFileMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.referenceFile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reference-file-my-suffix/:id/view',
        component: ReferenceFileMySuffixDetailComponent,
        resolve: {
            referenceFile: ReferenceFileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.referenceFile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reference-file-my-suffix/new',
        component: ReferenceFileMySuffixUpdateComponent,
        resolve: {
            referenceFile: ReferenceFileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.referenceFile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reference-file-my-suffix/:id/edit',
        component: ReferenceFileMySuffixUpdateComponent,
        resolve: {
            referenceFile: ReferenceFileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.referenceFile.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const referenceFilePopupRoute: Routes = [
    {
        path: 'reference-file-my-suffix/:id/delete',
        component: ReferenceFileMySuffixDeletePopupComponent,
        resolve: {
            referenceFile: ReferenceFileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.referenceFile.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
