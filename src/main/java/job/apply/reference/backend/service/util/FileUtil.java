package job.apply.reference.backend.service.util;

import job.apply.reference.backend.service.impl.ReferenceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(ReferenceServiceImpl.class);

    private FileUtil(){}

    /**
     * Convert the MultipartFile to java.io.File
     *@param multipartFile file that want to be covert.
     *@return a java.io.File that have been converted from input file.
     *@throws IOException if an I/O error occurs.
     * */
    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }

    public static MultipartFile convertFileToMultipartFile(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/xlsx", input);
        return multipartFile;
    }

}
