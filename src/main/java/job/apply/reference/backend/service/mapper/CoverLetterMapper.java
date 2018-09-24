package job.apply.reference.backend.service.mapper;

import job.apply.reference.backend.domain.*;
import job.apply.reference.backend.service.dto.CoverLetterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CoverLetter and its DTO CoverLetterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoverLetterMapper extends EntityMapper<CoverLetterDTO, CoverLetter> {



    default CoverLetter fromId(Long id) {
        if (id == null) {
            return null;
        }
        CoverLetter coverLetter = new CoverLetter();
        coverLetter.setId(id);
        return coverLetter;
    }
}
