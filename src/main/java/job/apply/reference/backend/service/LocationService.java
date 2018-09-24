package job.apply.reference.backend.service;

import job.apply.reference.backend.domain.Location;
import job.apply.reference.backend.domain.Resume;
import job.apply.reference.backend.service.dto.LocationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Location.
 */
public interface LocationService {

    /**
     * Save a location.
     *
     * @param locationDTO the entity to save
     * @return the persisted entity
     */
    LocationDTO save(LocationDTO locationDTO);

    /**
     * Check existence of a location
     *
     * @param String name of location
     * @return true iff exist a location with input name
     */
    boolean hasLocation(String name);

    /**
     * Get the location form database, or create a new location if not exist.
     *
     * @param String name
     * @return Location
     */
    Location getOrCreate(String name);

    /**
     * Get all the locations.
     *
     * @return the list of entities
     */
    List<LocationDTO> findAll();


    /**
     * Get the "id" location.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LocationDTO> findOne(Long id);

    /**
     * Delete the "id" location.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
