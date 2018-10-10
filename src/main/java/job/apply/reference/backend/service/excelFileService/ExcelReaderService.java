package job.apply.reference.backend.service.excelFileService;

import job.apply.reference.backend.config.ExcelFileConfig;
import job.apply.reference.backend.domain.*;
import job.apply.reference.backend.service.ReferenceService;
import job.apply.reference.backend.service.dto.UploadResultDTO;
import job.apply.reference.backend.service.impl.ReferenceServiceImpl;
import job.apply.reference.backend.service.util.FileUtil;
import job.apply.reference.backend.web.rest.errors.FileUploadFailureException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Calendar;

/**
 * This code was modified from https://github.com/callicoder/java-read-write-excel-file-using-apache-poi.
 */

@Service
public class ExcelReaderService {

    private final Logger log = LoggerFactory.getLogger(ReferenceServiceImpl.class);

    public final ReferenceService referenceService;
    public final ExcelFileConfig excelFileConfig;


    public ExcelReaderService(ReferenceService referenceService, ExcelFileConfig excelFileConfig) {
        this.referenceService = referenceService;
        this.excelFileConfig = excelFileConfig;
    }

    /**
     * Save the date MultipartFile to database
     *
     * @param multipartFile a single page excel file with required format.
     * @return true iff have successfully saved the date from excel file to database.
     * @throws FileUploadFailureException for any exception that occur.
     */
    public UploadResultDTO read(MultipartFile multipartFile) {
        log.debug("Start to save the data from file to database" );
        Workbook workbook =  null;
        try {
            File file = FileUtil.convertMultipartFileToFile(multipartFile);
            // Creating a Workbook from an Excel file (.xls or .xlsx)
            workbook = WorkbookFactory.create(file);
            // Getting the Sheet at index zero
            Sheet sheet = workbook.getSheetAt(0);
            this.getInforFormSheetAndSave(sheet);

        } catch (Exception e) {
            throw new FileUploadFailureException("faile to save date in Excel sheet to database", e.getMessage());
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        UploadResultDTO dto = new UploadResultDTO();
        dto.setMessage("Saved");
        dto.setResult(true);
        log.debug("Finish to save the data from file to database" );

        return dto;
    }

    /**
     * Get the all date form input sheet and save to database.
     *
     * @param sheet excel sheet
     */
    public void getInforFormSheetAndSave(Sheet sheet) {
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // Or you can use a for-each loop to iterate over the rows and columns
        boolean passHeader = true;
        for (Row row : sheet) {
            if (passHeader) {
                passHeader = false;
                continue;
            }
            int i = 0;
            Reference reference = new Reference();
            String coverName = " ";
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                if (i == this.excelFileConfig.getHeadersIndex().getCompanyName()) {
                    reference.setCompany(cellValue);
                } else if (i == this.excelFileConfig.getHeadersIndex().getAppliedDate()) {
                    Instant instant = this.covertDateToInstant(cellValue);
                    reference.setApplyTime(instant);
                } else if (i == this.excelFileConfig.getHeadersIndex().getJobTitle()) {
                    JobTitle jobTitle = new JobTitle();
                    jobTitle.setJobTitle(cellValue);
                    reference.setJobTitle(jobTitle);

                } else if (i == this.excelFileConfig.getHeadersIndex().getLocation()) {
                    Location location = new Location();
                    location.setLocation(cellValue);
                    reference.setLocation(location);

                } else if (i == this.excelFileConfig.getHeadersIndex().getFileName()) {
                    ReferenceFile referenceFile = new ReferenceFile();
                    referenceFile.setFile(cellValue);
                    reference.setReferenceFile(referenceFile);

                } else if (i == this.excelFileConfig.getHeadersIndex().getResumeName()) {
                    Resume resume = new Resume();
                    resume.setName(cellValue);
                    reference.setResume(resume);

                } else if (i == this.excelFileConfig.getHeadersIndex().getCoverLetterName()) {
                    coverName = cellValue;
                }
                i++;
            }

            CoverLetter cover = new CoverLetter();
            cover.setName(coverName);
            reference.setCover(cover);
            this.referenceService.saveWithOutRestriction(reference, reference.getApplyTime());
        }

    }

    /**
     * Create an Instant object form string.
     *
     * @param date A string of date representation in format of MM/DD/YYYY
     * @return an Instant object the correspond to the input date.
     */
    public Instant covertDateToInstant(String date) {
        String dateComponent[] = date.split(this.excelFileConfig.getDate().getDateSeparator());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(dateComponent[this.excelFileConfig.getDate().getYearIndex()]) + 2000, Integer.parseInt(dateComponent[this.excelFileConfig.getDate().getMonthIndex()]) - 1, Integer.parseInt(dateComponent[this.excelFileConfig.getDate().getDayIndex()]));
        return calendar.toInstant();
    }
}
