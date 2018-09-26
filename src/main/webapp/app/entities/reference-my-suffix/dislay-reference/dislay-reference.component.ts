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

  trackId(index: number, item: IReferenceMySuffix) {
    return item.id;
  }

}
