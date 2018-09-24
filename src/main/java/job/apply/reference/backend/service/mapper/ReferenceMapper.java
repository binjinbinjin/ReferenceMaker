package job.apply.reference.backend.service.mapper;

import job.apply.reference.backend.domain.*;
import job.apply.reference.backend.service.dto.ReferenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Reference and its DTO ReferenceDTO.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, ResumeMapper.class, CoverLetterMapper.class, ReferenceFileMapper.class, JobTitleMapper.class})
public interface ReferenceMapper extends EntityMapper<ReferenceDTO, Reference> {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "resume.id", target = "resumeId")
    @Mapping(source = "cover.id", target = "coverId")
    @Mapping(source = "referenceFile.id", target = "referenceFileId")
    @Mapping(source = "jobTitle.id", target = "jobTitleId")
    ReferenceDTO toDto(Reference reference);

    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "resumeId", target = "resume")
    @Mapping(source = "coverId", target = "cover")
    @Mapping(source = "referenceFileId", target = "referenceFile")
    @Mapping(source = "jobTitleId", target = "jobTitle")
    Reference toEntity(ReferenceDTO referenceDTO);

    default Reference fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reference reference = new Reference();
        reference.setId(id);
        return reference;
    }
}
