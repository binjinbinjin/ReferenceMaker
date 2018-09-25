package job.apply.reference.backend.service.dto;

import job.apply.reference.backend.domain.*;

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

    private String location;

    private String resume;

    private String cover;

    private String referenceFile;

    private String jobTitle;

    public ReferenceDTO (){};

    public static ReferenceDTO setReferenceValue(Long id, String company, Instant applyTime,
                                                 Long locationId, Long resumeId,
                                                 Long coverId, Long referenceFileId,
                                                 Long jobTitleId, String location,
                                                 String resume, String cover,
                                                 String referenceFile, String jobTitle) {
        ReferenceDTO dto = new ReferenceDTO();
        dto.setId(id);
        dto.setCompany(company);
        dto.setApplyTime(applyTime);
        dto.setLocationId(locationId);
        dto.setResumeId(resumeId);
        dto.setCoverId(coverId);
        dto.setReferenceFileId(referenceFileId);
        dto.setJobTitleId(jobTitleId);
        dto.setLocation(location);
        dto.setResume(resume);
        dto.setCover(cover);
        dto.setReferenceFile(referenceFile);
        dto.setJobTitle(jobTitle);
        return dto;

    }

    public static ReferenceDTO setReferenceValue(Long id, String company, Instant applyTime,
                                                 CoverLetter coverLetter, JobTitle jobTitle,
                                                 Location location, ReferenceFile referenceFile,
                                                 Resume resume) {
        return setReferenceValue(id, company, applyTime, location.getId(), resume.getId(),
            coverLetter.getId(), referenceFile.getId(), jobTitle.getId(), location.getLocation(),
            resume.getName(), coverLetter.getName(), referenceFile.getFile(),
            jobTitle.getJobTitle());
    }

    public static ReferenceDTO setReferenceValue(Reference reference) {
        return setReferenceValue(reference.getId(),reference.getCompany(), reference.getApplyTime(),
            reference.getCover(), reference.getJobTitle(), reference.getLocation(),
            reference.getReferenceFile(), reference.getResume() );

    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getReferenceFile() {
        return referenceFile;
    }

    public void setReferenceFile(String referenceFile) {
        this.referenceFile = referenceFile;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

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

