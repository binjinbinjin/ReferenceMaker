package job.apply.reference.backend.service;

import job.apply.reference.backend.service.dto.ReferenceFileDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ReferenceFile.
 */
public interface ReferenceFileService {

    /**
     * Save a referenceFile.
     *
     * @param referenceFileDTO the entity to save
     * @return the persisted entity
     */
    ReferenceFileDTO save(ReferenceFileDTO referenceFileDTO);

    /**
     * Get all the referenceFiles.
     *
     * @return the list of entities
     */
    List<ReferenceFileDTO> findAll();


    /**
     * Get the "id" referenceFile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReferenceFileDTO> findOne(Long id);

    /**
     * Delete the "id" referenceFile.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
