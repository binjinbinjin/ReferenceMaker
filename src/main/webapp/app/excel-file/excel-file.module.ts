import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UploadExcelFileComponent } from './upload-excel-file/upload-excel-file.component';
import { DownloadExcelFileComponent } from './download-excel-file/download-excel-file.component';
import { Routes, CanActivate, RouterModule } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { ExcelFileService } from 'app/excel-file/excel-file.service';
const routes: Routes = [
  {
    path: 'excel-file/upload-file',
    component: UploadExcelFileComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Upload-Excel-File'
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'excel-file/download-file',
    component: DownloadExcelFileComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Download-Excel-File'
    },
    canActivate: [UserRouteAccessService],
  }
];

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forChild(routes)
  ],
  declarations: [UploadExcelFileComponent, DownloadExcelFileComponent],
  providers: [ExcelFileService]
})
export class ExcelFileModule { }
