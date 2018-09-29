import { Component, OnInit, Input, ViewChild, TemplateRef } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-modal-alert',
  templateUrl: './modal-alert.component.html',
  styles: [],
  providers: [NgbActiveModal, NgbModal]
})
export class ModalAlertComponent implements OnInit {

  private isOpen: boolean;
  private closeModalTime: number;
  private displayText: string;
  private autoClose: boolean;
  private timeCountDown = 1000;
  private timeCountReference: any;
  setCloseTime(time) {
    if (time > 0) this.closeModalTime = time;
  }

  @ViewChild('content') content: any;
  constructor(public activeModal: NgbActiveModal, public modalService: NgbModal) {
    this.isOpen = false;
    this.closeModalTime = 0;
    this.autoClose = false;
    this.displayText = 'Please input the dispaly message using attriubte "displayText"';
  }

  ngOnInit() {
  }

  setModalInfoAndOpen(textToDisplay: string = 'click', isAutoClose: boolean = true, setCloseTime: number = 1000) {
    this.displayText = textToDisplay;
    this.autoClose = isAutoClose;
    this.setCloseTime(setCloseTime);
    this.open();
  }

  open() {
    if (this.isOpen) return;
    this.isOpen = true;
    this.activeModal = this.modalService.open(this.content);
    if (this.autoClose) {
      setTimeout(() => this.close(), this.closeModalTime);
      this.timeCountReference = setInterval(() => this.closeCountDown(this.timeCountDown), this.timeCountDown);

    }
  }

  close() {
    if (!this.isOpen) return;
    this.activeModal.dismiss();
    this.isOpen = false;
  }

  closeCountDown(time: number) {
    this.closeModalTime -= time;
    if (this.closeModalTime < 0) {
      this.closeModalTime = 0;
      clearInterval(this.timeCountReference);
    }
  }

  get closeTime() {
    return this.closeModalTime;
  }

}
