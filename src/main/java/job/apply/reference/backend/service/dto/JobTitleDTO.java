package job.apply.reference.backend.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the JobTitle entity.
 */
public class JobTitleDTO implements Serializable {

    private Long id;

    @NotNull
    private String jobTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobTitleDTO jobTitleDTO = (JobTitleDTO) o;
        if (jobTitleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobTitleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobTitleDTO{" +
            "id=" + getId() +
            ", jobTitle='" + getJobTitle() + "'" +
            "}";
    }
}
