import { ReferenceMySuffix } from './../../../shared/model/reference-my-suffix.model';
import { Component, OnInit, Output, EventEmitter, ElementRef } from '@angular/core';
import { coverIntro, coverEnding, coverBody, coverTDDBody, customQualification, customBackground } from './data';
import { createTokenForReference } from '@angular/compiler/src/identifiers';
import { IReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';

@Component({
  selector: 'job-search-reference-maker',
  templateUrl: './job-search-reference-maker.component.html',
  styleUrls: ['./job-search-reference-maker.component.css']
})
export class JobSearchReferenceMakerComponent implements OnInit {

  resumes: string[] = ['resume-backend', 'resume-developer', 'resume-jin', 'resume'];
  covers: string[] = ['cover', 'cover-tdd'];
  locations: string[] = ['Toronto', 'Markham', 'North York', 'Richmond Hill', 'Scarborough', 'Mississauga', 'Thornhill', 'Vaughan'];
  lastCreateReference: IReferenceMySuffix;
  customBackground: string;
  customQualification: string;
  displyCoverLetter: string;
  nameOfReferenceFile: string;
  referencFile: string;
  @Output('createReference') createReference: EventEmitter<IReferenceMySuffix>;

  constructor() {
    this.createReference = new EventEmitter();
  }

  ngOnInit() {
    this.displyCoverLetter = ' ';
    this.nameOfReferenceFile = ' ';
    this.referencFile = '';

    this.customBackground = customBackground;
    this.customQualification = customQualification;
  }

  copyToClipboard(val: string) {
    const selBox = document.createElement('textarea');
    selBox.style.position = 'fixed';
    selBox.style.left = '0';
    selBox.style.top = '0';
    selBox.style.opacity = '0';
    selBox.value = val;
    document.body.appendChild(selBox);
    selBox.focus();
    selBox.select();
    document.execCommand('copy');
    document.body.removeChild(selBox);
  }
  copyToClipboardTag(main) {
    this.copyToClipboard(main.textContent);
  }

  refreshPage() {
    window.location.reload();
  }
  clear() {
    this.ngOnInit();
  }

  create(form) {
    const value = form.value;
    this.displyCoverLetter = this.createCoverLetterIntro(value.jobTitle, value.company);
    this.displyCoverLetter += value.selectCover ?
      this.createCoverLetterBodyFormChoice(value.coverChoice) :
      this.createCoverLetterBodyByCustom(value.background, value.qualification);
    this.displyCoverLetter += this.createCoverLetterEnding();
    this.nameOfReferenceFile = this.createNameOfReferenceFile(value.jobTitle, value.company,
      value.selectResume ?
        value.resumeChoice : value.resumeName,
      value.selectCover ?
        value.coverChoice : value.nameOfCustomCover);
    this.referencFile = this.createReferenceFile(value.jobTitle, value.company);
  }

  createNameOfReferenceFile(jobTitle: string, companyName: string, resumeName: string, coverName: string): string {
    let result: string = companyName.trim();

    for (const each of jobTitle.trim().split(' ')) {
      result = result + '-' + each;
    }
    result = result + '-' + resumeName.trim() + '-' + coverName + '.pdf';
    return result;
  }

  createReferenceFile(jobTitle: string, companyName: string): string {
    let result: string = companyName.trim();

    for (const each of jobTitle.trim().split(' ')) {
      result = result + '-' + each;
    }
    result += '.pdf';
    return result;
  }

  createCoverLetterIntro(jobTitle: string, companyName: string): string {
    return coverIntro(jobTitle, companyName);
  }

  createCoverLetterEnding() {
    return coverEnding();
  }

  createCoverLetterBodyFormChoice(coverType: string): string {
    if (coverType === this.covers[0]) {
      return coverBody;
    }
    if (coverType === this.covers[1]) {
      return coverTDDBody;
    }
    return ' ERROR';

  }

  creteNewReference(form) {
    if (! form || !form.valid) return;
    const value = form.value;
    const newReference: IReferenceMySuffix = {
      company: value.company,
      location: value.selectLocation ? value.locationChoice : value.locationName,
      resume: value.selectResume ? value.resumeChoice : value.resumeName,
      cover: value.selectCover ? value.coverChoice : value.nameOfCustomCover,
      referenceFile: this.createReferenceFile(value.jobTitle, value.company),
      jobTitle: value.jobTitle };

    if (!this.lastCreateReference) {
      this.recordLastEmmitAndEmmit(newReference);
      return;
    }

    for (const each in this.lastCreateReference) {
      if (this.lastCreateReference[each] !== newReference[each]) {
        this.recordLastEmmitAndEmmit(newReference);
        return;
      }
    }

  }

  recordLastEmmitAndEmmit(newReference: any) {
    this.lastCreateReference = newReference;
    this.createReference.emit(newReference);
  }

  createCoverLetterBodyByCustom(customBackgrounds: string, customQualifications: string) {
    let result = '';
    result = `
<p>${customBackground.trim()}</p>

<p>Here are some highlights of qualification:</p> <p>`;
    for (const each of customQualifications.trim().split('\n')) {
      result = result + '\n<br>- &nbsp ' + each;
    }
    result += `</p>
    `;
    return result;
  }
}
