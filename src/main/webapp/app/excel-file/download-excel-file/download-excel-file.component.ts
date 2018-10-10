import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ExcelFileService } from 'app/excel-file/excel-file.service';
import { DownloadPath, DownloadPathInter } from 'app/excel-file/excel-file-model';
@Component({
  selector: 'jhi-download-excel-file',
  templateUrl: './download-excel-file.component.html',
  styles: []
})
export class DownloadExcelFileComponent implements OnInit {

  constructor(private excelFileService: ExcelFileService) { }

  ngOnInit() {
  }

  getFile() {
    console.log("ddfdfd");
    this.excelFileService.getExcelFile().subscribe((res) => {
      console.log(res);
      return null;
    });
  }

}
