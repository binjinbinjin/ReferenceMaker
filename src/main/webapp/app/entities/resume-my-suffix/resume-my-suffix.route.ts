import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';
import { ResumeMySuffixService } from './resume-my-suffix.service';
import { ResumeMySuffixComponent } from './resume-my-suffix.component';
import { ResumeMySuffixDetailComponent } from './resume-my-suffix-detail.component';
import { ResumeMySuffixUpdateComponent } from './resume-my-suffix-update.component';
import { ResumeMySuffixDeletePopupComponent } from './resume-my-suffix-delete-dialog.component';
import { IResumeMySuffix } from 'app/shared/model/resume-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class ResumeMySuffixResolve implements Resolve<IResumeMySuffix> {
    constructor(private service: ResumeMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((resume: HttpResponse<ResumeMySuffix>) => resume.body));
        }
        return of(new ResumeMySuffix());
    }
}

export const resumeRoute: Routes = [
    {
        path: 'resume-my-suffix',
        component: ResumeMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.resume.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resume-my-suffix/:id/view',
        component: ResumeMySuffixDetailComponent,
        resolve: {
            resume: ResumeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.resume.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resume-my-suffix/new',
        component: ResumeMySuffixUpdateComponent,
        resolve: {
            resume: ResumeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.resume.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resume-my-suffix/:id/edit',
        component: ResumeMySuffixUpdateComponent,
        resolve: {
            resume: ResumeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.resume.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resumePopupRoute: Routes = [
    {
        path: 'resume-my-suffix/:id/delete',
        component: ResumeMySuffixDeletePopupComponent,
        resolve: {
            resume: ResumeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.resume.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
