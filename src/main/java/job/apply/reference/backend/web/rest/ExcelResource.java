package job.apply.reference.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import job.apply.reference.backend.service.excelFileService.ExcelReaderService;
import job.apply.reference.backend.service.dto.UploadResultDTO;
import job.apply.reference.backend.service.impl.ReferenceServiceImpl;
import job.apply.reference.backend.web.rest.errors.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;

@RestController
@RequestMapping("api/excel-document")
public class ExcelResource {

    private final Logger log = LoggerFactory.getLogger(ReferenceServiceImpl.class);

    private final ExcelReaderService readExcelFile;

    public ExcelResource(ExcelReaderService readExcelFile) {
        this.readExcelFile = readExcelFile;
    }

    /**
     * Post /upload?file=file a new excel file that contains the date that want to save into database
     * @param  file a excel file
     * @return the ResponseEntity with status 201 (Created) and with body the new referenceDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * */
    @PostMapping("/upload")
    @Timed
    public ResponseEntity<UploadResultDTO> uploadExcelFile(@RequestParam("file") MultipartFile file) throws URISyntaxException {
        this.log.debug("Get request to save the data in file to database");
        UploadResultDTO dto = this.readExcelFile.read(file);
        if (dto == null) {
            throw new InternalServerErrorException("UploadResultDTO cannot be null");
        }
        return  ResponseEntity.ok().body(dto);
    }

}
