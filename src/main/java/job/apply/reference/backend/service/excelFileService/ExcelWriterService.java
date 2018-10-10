package job.apply.reference.backend.service.excelFileService;

import job.apply.reference.backend.config.ExcelFileConfig;
import job.apply.reference.backend.domain.Reference;
import job.apply.reference.backend.service.ReferenceService;
import job.apply.reference.backend.service.impl.ReferenceServiceImpl;
import job.apply.reference.backend.service.util.FileUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class ExcelWriterService {
    private final Logger log = LoggerFactory.getLogger(ReferenceServiceImpl.class);

    public final ReferenceService referenceService;
    public final ExcelFileConfig excelFileConfig;

    public ExcelWriterService(ReferenceService referenceService, ExcelFileConfig excelFileConfig) {
        this.referenceService = referenceService;
        this.excelFileConfig = excelFileConfig;
    }

    public Resource getExcelFile() {
        File file = this.writeFile();
        if (file == null)  throw new InternalError("Fail to writing data in database to Excel file");
        try {
            Path path = file.toPath();
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException();
            }
        }catch (Exception e){
            throw new InternalError("Fail to writing data in database to Excel file");
        }

    }

    public File writeFile () {

        List<Reference> references = this.referenceService.getAllReferences();
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook();     // new HSSFWorkbook() for generating `.xls` file

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Reference");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        this.createHeaders(headerRow, headerCellStyle);

        this.writeReference(references, sheet, workbook);

        // Resize all columns to fit the content size
        for(int i = 0; i < this.excelFileConfig.getHeaders().size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file

        FileOutputStream fileOut = null;
        try {
            File file = new File("src/main/webapp/Job-Reference/AppliedReference.xlsx");
            file.createNewFile();
            fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();

            workbook.close();
            return file;

        } catch (Exception e) {
            throw new InternalError(e.getMessage());
        }finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void writeReference(List<Reference> references, Sheet sheet, Workbook workbook) {
        /* CreationHelper helps us create instances for various things like DataFormat,
           Hyperlink, RichTextString etc in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();
        // Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));
        int rowNum = 1;
        for(Reference reference: references) {
            Row row = sheet.createRow(rowNum++);


            row.createCell(this.excelFileConfig.getHeadersIndex().getResumeName()).setCellValue(reference.getResume().getName());
            row.createCell(this.excelFileConfig.getHeadersIndex().getCompanyName()).setCellValue(reference.getCompany());
            row.createCell(this.excelFileConfig.getHeadersIndex().getCoverLetterName()).setCellValue(reference.getCover().getName());
            row.createCell(this.excelFileConfig.getHeadersIndex().getFileName()).setCellValue(reference.getReferenceFile().getFile());
            row.createCell(this.excelFileConfig.getHeadersIndex().getJobTitle()).setCellValue(reference.getJobTitle().getJobTitle());
            row.createCell(this.excelFileConfig.getHeadersIndex().getLocation()).setCellValue(reference.getLocation().getLocation());
            Cell dateCell = row.createCell(this.excelFileConfig.getHeadersIndex().getAppliedDate());
            dateCell.setCellValue(java.util.Date.from(reference.getApplyTime()));
            dateCell.setCellStyle(dateCellStyle);

        }

    }


    private void createHeaders(Row headerRow, CellStyle headerCellStyle) {
        // Creating cells
        int i = 0;
        for (String column: this.excelFileConfig.getHeaders()) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(column);
            cell.setCellStyle(headerCellStyle);
            i++;
        }
    }
}
