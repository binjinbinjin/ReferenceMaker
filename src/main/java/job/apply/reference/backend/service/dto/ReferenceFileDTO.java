package job.apply.reference.backend.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ReferenceFile entity.
 */
public class ReferenceFileDTO implements Serializable {

    private Long id;

    @NotNull
    private String file;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReferenceFileDTO referenceFileDTO = (ReferenceFileDTO) o;
        if (referenceFileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), referenceFileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReferenceFileDTO{" +
            "id=" + getId() +
            ", file='" + getFile() + "'" +
            "}";
    }
}
