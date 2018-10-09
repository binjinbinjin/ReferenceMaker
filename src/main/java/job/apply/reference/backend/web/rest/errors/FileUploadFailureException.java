package job.apply.reference.backend.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.io.File;

public class FileUploadFailureException  extends AbstractThrowableProblem{

    public FileUploadFailureException(String title,String detail) {
        super (ErrorConstants.FILE_UPLOAD_FAILE_TYPE, title, Status.EXPECTATION_FAILED, detail);

    }

}
