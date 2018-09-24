package job.apply.reference.backend.service.mapper;

import job.apply.reference.backend.domain.*;
import job.apply.reference.backend.service.dto.ResumeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Resume and its DTO ResumeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ResumeMapper extends EntityMapper<ResumeDTO, Resume> {



    default Resume fromId(Long id) {
        if (id == null) {
            return null;
        }
        Resume resume = new Resume();
        resume.setId(id);
        return resume;
    }
}
