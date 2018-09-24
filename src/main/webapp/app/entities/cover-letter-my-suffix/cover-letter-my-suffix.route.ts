import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CoverLetterMySuffix } from 'app/shared/model/cover-letter-my-suffix.model';
import { CoverLetterMySuffixService } from './cover-letter-my-suffix.service';
import { CoverLetterMySuffixComponent } from './cover-letter-my-suffix.component';
import { CoverLetterMySuffixDetailComponent } from './cover-letter-my-suffix-detail.component';
import { CoverLetterMySuffixUpdateComponent } from './cover-letter-my-suffix-update.component';
import { CoverLetterMySuffixDeletePopupComponent } from './cover-letter-my-suffix-delete-dialog.component';
import { ICoverLetterMySuffix } from 'app/shared/model/cover-letter-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class CoverLetterMySuffixResolve implements Resolve<ICoverLetterMySuffix> {
    constructor(private service: CoverLetterMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((coverLetter: HttpResponse<CoverLetterMySuffix>) => coverLetter.body));
        }
        return of(new CoverLetterMySuffix());
    }
}

export const coverLetterRoute: Routes = [
    {
        path: 'cover-letter-my-suffix',
        component: CoverLetterMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.coverLetter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cover-letter-my-suffix/:id/view',
        component: CoverLetterMySuffixDetailComponent,
        resolve: {
            coverLetter: CoverLetterMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.coverLetter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cover-letter-my-suffix/new',
        component: CoverLetterMySuffixUpdateComponent,
        resolve: {
            coverLetter: CoverLetterMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.coverLetter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cover-letter-my-suffix/:id/edit',
        component: CoverLetterMySuffixUpdateComponent,
        resolve: {
            coverLetter: CoverLetterMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.coverLetter.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const coverLetterPopupRoute: Routes = [
    {
        path: 'cover-letter-my-suffix/:id/delete',
        component: CoverLetterMySuffixDeletePopupComponent,
        resolve: {
            coverLetter: CoverLetterMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.coverLetter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
