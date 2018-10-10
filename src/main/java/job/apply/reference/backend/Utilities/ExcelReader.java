package job.apply.reference.backend.Utilities;

import job.apply.reference.backend.domain.*;
import job.apply.reference.backend.service.ReferenceService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by rajeevkumarsingh on 18/12/17.
 */

@Component
public class ExcelReader {
    public final String SAMPLE_XLSX_FILE_PATH = "/Users/jinbinliu/Desktop/reference/referenceRecord/JobSearchHistory.xlsx";
    public final ReferenceService referenceService;

    public ExcelReader(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    public void read() throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));


        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        boolean passHeader = true;
        for (Row row: sheet) {
            if (passHeader) {
                passHeader = false;
                continue;
            }
            int i = 0;
            Reference reference = new Reference();
            String coverName = " ";
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                if (i == 0) {
                    reference.setCompany(cellValue);
                }
                else if (i == 1) {
                    String dateComponent[] = cellValue.split("/");
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.parseInt(dateComponent[2]) + 2000, Integer.parseInt(dateComponent[0]) - 1, Integer.parseInt(dateComponent[1]));
                    reference.setApplyTime(calendar.toInstant());
                }
                else if (i == 2) {
                    JobTitle jobTitle = new JobTitle();
                    jobTitle.setJobTitle(cellValue);
                    reference.setJobTitle(jobTitle);

                }else if (i == 3) {
                    Location location = new Location();
                    location.setLocation(cellValue);
                    reference.setLocation(location);

                }else if (i == 4) {
                    ReferenceFile referenceFile = new ReferenceFile();
                    referenceFile.setFile(cellValue);
                    reference.setReferenceFile(referenceFile);

                }else if (i == 5) {
                    Resume resume = new Resume();
                    resume.setName(cellValue);
                    reference.setResume(resume);

                }else if (i == 6) {
                    coverName = cellValue;
                }
                i++;
            }

            CoverLetter cover = new CoverLetter();
            cover.setName(coverName);
            reference.setCover(cover);
            this.referenceService.saveWithOutRestriction(reference, reference.getApplyTime());
        }

        workbook.close();
    }

    private static void printCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case STRING:
                System.out.print(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.print(cell.getDateCellValue());
                } else {
                    System.out.print(cell.getNumericCellValue());
                }
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;
            default:
                System.out.print("");
        }

        System.out.print("\t");
    }
}
