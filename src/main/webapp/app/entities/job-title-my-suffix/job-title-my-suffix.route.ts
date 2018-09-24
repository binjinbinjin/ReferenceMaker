import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { JobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';
import { JobTitleMySuffixService } from './job-title-my-suffix.service';
import { JobTitleMySuffixComponent } from './job-title-my-suffix.component';
import { JobTitleMySuffixDetailComponent } from './job-title-my-suffix-detail.component';
import { JobTitleMySuffixUpdateComponent } from './job-title-my-suffix-update.component';
import { JobTitleMySuffixDeletePopupComponent } from './job-title-my-suffix-delete-dialog.component';
import { IJobTitleMySuffix } from 'app/shared/model/job-title-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class JobTitleMySuffixResolve implements Resolve<IJobTitleMySuffix> {
    constructor(private service: JobTitleMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((jobTitle: HttpResponse<JobTitleMySuffix>) => jobTitle.body));
        }
        return of(new JobTitleMySuffix());
    }
}

export const jobTitleRoute: Routes = [
    {
        path: 'job-title-my-suffix',
        component: JobTitleMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.jobTitle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-title-my-suffix/:id/view',
        component: JobTitleMySuffixDetailComponent,
        resolve: {
            jobTitle: JobTitleMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.jobTitle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-title-my-suffix/new',
        component: JobTitleMySuffixUpdateComponent,
        resolve: {
            jobTitle: JobTitleMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.jobTitle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-title-my-suffix/:id/edit',
        component: JobTitleMySuffixUpdateComponent,
        resolve: {
            jobTitle: JobTitleMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.jobTitle.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jobTitlePopupRoute: Routes = [
    {
        path: 'job-title-my-suffix/:id/delete',
        component: JobTitleMySuffixDeletePopupComponent,
        resolve: {
            jobTitle: JobTitleMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'referenceRecordApp.jobTitle.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
