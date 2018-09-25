package job.apply.reference.backend.service;

import job.apply.reference.backend.domain.ReferenceFile;
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
     * Check existence of a file
     *
     * @param name of file
     * @return true iff exist a file with input name
     */
    boolean hasFile(String name);

    /**
     * Get the name of ReferenceFile form database, or create a new name for reference file if not exist.
     *
     * @param name
     * @return ReferenceFile
     */
    ReferenceFile getOrCreate(String name);

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
