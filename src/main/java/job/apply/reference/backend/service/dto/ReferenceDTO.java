package job.apply.reference.backend.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Reference entity.
 */
public class ReferenceDTO implements Serializable {

    private Long id;

    @NotNull
    private String company;

    @NotNull
    private Instant applyTime;

    private Long locationId;

    private Long resumeId;

    private Long coverId;

    private Long referenceFileId;

    private Long jobTitleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Instant getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Instant applyTime) {
        this.applyTime = applyTime;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Long getCoverId() {
        return coverId;
    }

    public void setCoverId(Long coverLetterId) {
        this.coverId = coverLetterId;
    }

    public Long getReferenceFileId() {
        return referenceFileId;
    }

    public void setReferenceFileId(Long referenceFileId) {
        this.referenceFileId = referenceFileId;
    }

    public Long getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(Long jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReferenceDTO referenceDTO = (ReferenceDTO) o;
        if (referenceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), referenceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReferenceDTO{" +
            "id=" + getId() +
            ", company='" + getCompany() + "'" +
            ", applyTime='" + getApplyTime() + "'" +
            ", location=" + getLocationId() +
            ", resume=" + getResumeId() +
            ", cover=" + getCoverId() +
            ", referenceFile=" + getReferenceFileId() +
            ", jobTitle=" + getJobTitleId() +
            "}";
    }
}
