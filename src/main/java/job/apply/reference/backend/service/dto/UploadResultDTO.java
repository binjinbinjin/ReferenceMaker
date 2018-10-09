package job.apply.reference.backend.service.dto;

import javax.validation.constraints.NotNull;

public class UploadResultDTO {

    @NotNull
    private boolean result;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
