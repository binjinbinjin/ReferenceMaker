package job.apply.reference.backend.service.impl;

import job.apply.reference.backend.service.LocationService;
import job.apply.reference.backend.domain.Location;
import job.apply.reference.backend.repository.LocationRepository;
import job.apply.reference.backend.service.dto.LocationDTO;
import job.apply.reference.backend.service.mapper.LocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Location.
 */
@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);

    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    /**
     * Save a location.
     *
     * @param locationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LocationDTO save(LocationDTO locationDTO) {
        log.debug("Request to save Location : {}", locationDTO);
        Location location = locationMapper.toEntity(locationDTO);
        location = locationRepository.save(location);
        return locationMapper.toDto(location);
    }

    /**
     * Check existence of a location
     *
     * @param name@return true iff exist a location with input name
     */
    @Override
    public boolean hasLocation(String name) {
        return this.locationRepository.existsLocationByLocation(name);
    }

    /**
     * Get the location form database, or create a new location if not exist.
     *
     * @param name@return Location
     */
    @Override
    public Location getOrCreate(String name) {
        if (name == null || name.trim().length() == 0) name = " ";
        Location location = this.locationRepository.getLocationByLocation(name);
        if (location != null) return location;
        location = new Location();
        location.setLocation(name);
        return this.locationRepository.save(location);
    }

    /**
     * Get all the locations.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LocationDTO> findAll() {
        log.debug("Request to get all Locations");
        return locationRepository.findAll().stream()
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one location by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LocationDTO> findOne(Long id) {
        log.debug("Request to get Location : {}", id);
        return locationRepository.findById(id)
            .map(locationMapper::toDto);
    }

    /**
     * Delete the location by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Location : {}", id);
        locationRepository.deleteById(id);
    }
}
