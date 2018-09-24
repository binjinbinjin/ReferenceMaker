package job.apply.reference.backend.service;

import job.apply.reference.backend.domain.CoverLetter;
import job.apply.reference.backend.domain.Location;
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
     * Check existence of a cover letter
     *
     * @param String name of cover letter
     * @return true iff exist a cover letter with input name
     */
    boolean hasCoverLetter(String name);

    /**
     * Get the cover letter form database, or create a new cover letter if not exist.
     *
     * @param String name
     * @return CoverLetter
     */
    CoverLetter getOrCreate(String name);

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
