package job.apply.reference.backend.service;

import job.apply.reference.backend.service.dto.JobTitleDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing JobTitle.
 */
public interface JobTitleService {

    /**
     * Save a jobTitle.
     *
     * @param jobTitleDTO the entity to save
     * @return the persisted entity
     */
    JobTitleDTO save(JobTitleDTO jobTitleDTO);

    /**
     * Get all the jobTitles.
     *
     * @return the list of entities
     */
    List<JobTitleDTO> findAll();


    /**
     * Get the "id" jobTitle.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<JobTitleDTO> findOne(Long id);

    /**
     * Delete the "id" jobTitle.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
