package job.apply.reference.backend.service.util;

import job.apply.reference.backend.service.impl.ReferenceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(ReferenceServiceImpl.class);

    private FileUtil(){}

    public static File covertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        log.debug("\n\n Covert file: {} ",multipartFile.getOriginalFilename());
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        log.debug("\n\n Create file");

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        log.debug("\n\n Open stream");

        fileOutputStream.write(multipartFile.getBytes());
        log.debug("\n\n Write stream");

        fileOutputStream.close();
//        log.debug("\n\n create file" );
//        multipartFile.transferTo(file);
        log.debug("\n\nConvert successful" );

        return file;
    }

}
