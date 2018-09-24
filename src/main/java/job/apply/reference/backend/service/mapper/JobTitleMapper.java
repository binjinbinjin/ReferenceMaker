package job.apply.reference.backend.service.mapper;

import job.apply.reference.backend.domain.*;
import job.apply.reference.backend.service.dto.JobTitleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JobTitle and its DTO JobTitleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JobTitleMapper extends EntityMapper<JobTitleDTO, JobTitle> {



    default JobTitle fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobTitle jobTitle = new JobTitle();
        jobTitle.setId(id);
        return jobTitle;
    }
}
