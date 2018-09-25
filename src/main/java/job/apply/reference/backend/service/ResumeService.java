package job.apply.reference.backend.service;

import job.apply.reference.backend.domain.Resume;
import job.apply.reference.backend.service.dto.ResumeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Resume.
 */
public interface ResumeService {

    /**
     * Save a resume.
     *
     * @param resumeDTO the entity to save
     * @return the persisted entity
     */
    ResumeDTO save(ResumeDTO resumeDTO);

    /**
     * Check existence of a resume
     *
     * @param name of resume
     * @return true iff exist a resume with input name
     */
    boolean hasResume(String name);

    /**
     * Get the resume form database, or create a new resume if not exist.
     *
     * @param name of resume
     * @return Resume
     */
    Resume getOrCreate(String name);

    /**
     * Get all the resumes.
     *
     * @return the list of entities
     */
    List<ResumeDTO> findAll();


    /**
     * Get the "id" resume.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ResumeDTO> findOne(Long id);

    /**
     * Delete the "id" resume.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
