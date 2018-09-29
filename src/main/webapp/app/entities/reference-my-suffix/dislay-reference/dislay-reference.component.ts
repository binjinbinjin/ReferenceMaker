import { IReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'jhi-dislay-reference',
  templateUrl: './dislay-reference.component.html',
  styles: []
})
export class DislayReferenceComponent implements OnInit {

  @Input('references') references: IReferenceMySuffix[];
  @Input('links')links: any;
  @Input('page') page: any;
  @Input('predicate')predicate: any;
  @Input('reverse')reverse: any;
  @Output('flip') flip: EventEmitter<number>;
  @Output('loading') loading: EventEmitter<boolean>;
  filePath = 'http://127.0.0.1:8887/';

  constructor() {
    this.flip = new EventEmitter();
    this.loading = new EventEmitter();
    this.page = 0;
   }

  ngOnInit() {

  }

  reset() {
    this.page = 0;
    this.references = [];
    this.loading.emit(true);
  }

  flipPage(pageNum: number) {
    this.page = pageNum;
    this.flip.emit(this.page);
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  trackId(index: number, item: IReferenceMySuffix) {
    return item.id;
  }

  openFile(fileName: string) {
    let path = 'file:///Users/jinbinliu/Desktop/JobSearch/Job%20Reference/';
    for (const each of fileName) {
       if (each === ' ') {
         path += '%20';
       } else {
         path += each;
       }
    }
    return path;
  }

}
