package job.apply.reference.backend.service;

import job.apply.reference.backend.domain.JobTitle;
import job.apply.reference.backend.domain.Location;
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
     * Check existence of a Job title
     *
     * @param String name of job title
     * @return true iff exist a title with input name
     */
    boolean hasJobTitle(String name);

    /**
     * Get the job title form database, or create a new job title if not exist.
     *
     * @param String name
     * @return JobTitle
     */
    JobTitle getOrCreate(String name);

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
