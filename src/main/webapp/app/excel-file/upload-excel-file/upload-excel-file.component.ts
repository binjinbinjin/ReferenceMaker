import { Route, Router } from '@angular/router';
import { ExcelFileService } from './../excel-file.service';
import { HttpResponse} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UploadFileInter } from 'app/excel-file/excel-file-model';

@Component({
  selector: 'jhi-upload-excel-file',
  templateUrl: './upload-excel-file.component.html',
  styles: []
})
export class UploadExcelFileComponent implements OnInit {

  selectedFile: File;
  constructor(private fileService: ExcelFileService, private router: Router) { }

  ngOnInit() {
  }

  selectFile(event) {
    this.selectedFile = event.target.files[0];
  }

  uploadFile() {
    this.fileService.uploadExcelFile(this.selectedFile).toPromise().then((res: HttpResponse<UploadFileInter >) => {
      alert('Message: ' + res.body.message + '  Result: ' + res.body.result);
      this.router.navigate(['']);
    });
  }

}
