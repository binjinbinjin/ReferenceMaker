package job.apply.reference.backend.service.mapper;

import job.apply.reference.backend.domain.*;
import job.apply.reference.backend.service.dto.ReferenceFileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ReferenceFile and its DTO ReferenceFileDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReferenceFileMapper extends EntityMapper<ReferenceFileDTO, ReferenceFile> {



    default ReferenceFile fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReferenceFile referenceFile = new ReferenceFile();
        referenceFile.setId(id);
        return referenceFile;
    }
}
