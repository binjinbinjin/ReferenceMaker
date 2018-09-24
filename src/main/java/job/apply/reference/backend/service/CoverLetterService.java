package job.apply.reference.backend.service;

import job.apply.reference.backend.service.dto.CoverLetterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CoverLetter.
 */
public interface CoverLetterService {

    /**
     * Save a coverLetter.
     *
     * @param coverLetterDTO the entity to save
     * @return the persisted entity
     */
    CoverLetterDTO save(CoverLetterDTO coverLetterDTO);

    /**
     * Get all the coverLetters.
     *
     * @return the list of entities
     */
    List<CoverLetterDTO> findAll();


    /**
     * Get the "id" coverLetter.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CoverLetterDTO> findOne(Long id);

    /**
     * Delete the "id" coverLetter.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
