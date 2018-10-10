import { ExcelFileModule } from './excel-file.module';

describe('ExcelFileModule', () => {
  let excelFileModule: ExcelFileModule;

  beforeEach(() => {
    excelFileModule = new ExcelFileModule();
  });

  it('should create an instance', () => {
    expect(excelFileModule).toBeTruthy();
  });
});
