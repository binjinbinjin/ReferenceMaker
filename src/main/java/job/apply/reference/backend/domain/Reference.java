package job.apply.reference.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Reference.
 */
@Entity
@Table(name = "reference")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reference implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "company", nullable = false)
    private String company;

    @NotNull
    @Column(name = "apply_time", nullable = false)
    private Instant applyTime;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Location location;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Resume resume;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CoverLetter cover;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ReferenceFile referenceFile;

    @ManyToOne
    @JsonIgnoreProperties("")
    private JobTitle jobTitle;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public Reference company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Instant getApplyTime() {
        return applyTime;
    }

    public Reference applyTime(Instant applyTime) {
        this.applyTime = applyTime;
        return this;
    }

    public void setApplyTime(Instant applyTime) {
        this.applyTime = applyTime;
    }

    public Location getLocation() {
        return location;
    }

    public Reference location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Resume getResume() {
        return resume;
    }

    public Reference resume(Resume resume) {
        this.resume = resume;
        return this;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public CoverLetter getCover() {
        return cover;
    }

    public Reference cover(CoverLetter coverLetter) {
        this.cover = coverLetter;
        return this;
    }

    public void setCover(CoverLetter coverLetter) {
        this.cover = coverLetter;
    }

    public ReferenceFile getReferenceFile() {
        return referenceFile;
    }

    public Reference referenceFile(ReferenceFile referenceFile) {
        this.referenceFile = referenceFile;
        return this;
    }

    public void setReferenceFile(ReferenceFile referenceFile) {
        this.referenceFile = referenceFile;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public Reference jobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reference reference = (Reference) o;
        if (reference.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reference.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Reference{" +
            "id=" + getId() +
            ", company='" + getCompany() + "'" +
            ", applyTime='" + getApplyTime() + "'" +
            "}";
    }
}
