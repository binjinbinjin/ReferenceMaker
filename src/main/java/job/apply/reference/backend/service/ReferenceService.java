package job.apply.reference.backend.service;

import job.apply.reference.backend.service.dto.ReferenceDTO;
import job.apply.reference.backend.domain.Reference;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Optional;

/**
 * Service Interface for managing Reference.
 */
public interface ReferenceService {

    /**
     * Save a reference.
     *
     * @param referenceDTO the entity to save
     * @return the persisted entity
     */
    ReferenceDTO save(ReferenceDTO referenceDTO);

    /**
     * Save a reference.
     *
     * @param reference the entity to save
     * @return the persisted entity
     */
    Reference save(Reference reference);

    /**
     * Save a reference.
     *
     * @param referenceDTO the entity to save
     * @return the persisted entity
     */
    ReferenceDTO saveWithOutRestriction(ReferenceDTO referenceDTO);

    /**
     * Save a reference.
     *
     * @param reference the entity to save
     * @param time time instance
     * @return the persisted entity
     */
    Reference saveWithOutRestriction(Reference reference,  Instant time);

    /**
     * Get all the references.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ReferenceDTO> findAll(Pageable pageable);

    /**
     * Get all the references with company name that contains input characters.
     *
     * @param characters the characters
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ReferenceDTO> findAllWithCompanyContainsAllLetters(String characters, Pageable pageable);


    /**
     * Get the "id" reference.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReferenceDTO> findOne(Long id);

    /**
     * Delete the "id" reference.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

