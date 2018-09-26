package job.apply.reference.backend.service.impl;

import job.apply.reference.backend.service.ReferenceFileService;
import job.apply.reference.backend.domain.ReferenceFile;
import job.apply.reference.backend.repository.ReferenceFileRepository;
import job.apply.reference.backend.service.dto.ReferenceFileDTO;
import job.apply.reference.backend.service.mapper.ReferenceFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ReferenceFile.
 */
@Service
@Transactional
public class ReferenceFileServiceImpl implements ReferenceFileService {

    private final Logger log = LoggerFactory.getLogger(ReferenceFileServiceImpl.class);

    private final ReferenceFileRepository referenceFileRepository;

    private final ReferenceFileMapper referenceFileMapper;

    public ReferenceFileServiceImpl(ReferenceFileRepository referenceFileRepository, ReferenceFileMapper referenceFileMapper) {
        this.referenceFileRepository = referenceFileRepository;
        this.referenceFileMapper = referenceFileMapper;
    }

    /**
     * Save a referenceFile.
     *
     * @param referenceFileDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReferenceFileDTO save(ReferenceFileDTO referenceFileDTO) {
        log.debug("Request to save ReferenceFile : {}", referenceFileDTO);
        ReferenceFile referenceFile = referenceFileMapper.toEntity(referenceFileDTO);
        referenceFile = referenceFileRepository.save(referenceFile);
        return referenceFileMapper.toDto(referenceFile);
    }

    /**
     * Check existence of a file
     *
     * @param name@return true iff exist a file with input name
     */
    @Override
    public boolean hasFile(String name) {
        return this.referenceFileRepository.existsReferenceFileByFile(name);
    }

    /**
     * Get the name of ReferenceFile form database, or create a new name for reference file if not exist.
     *
     * @param name@return ReferenceFile
     */
    @Override
    public ReferenceFile getOrCreate(String name) {
        if (name == null || name.trim().length() == 0) name = " ";
        ReferenceFile referenceFile = this.referenceFileRepository.getReferenceFileByFile(name);
        if (referenceFile != null) return referenceFile;
        referenceFile = new ReferenceFile();
        referenceFile.setFile(name);
        return this.referenceFileRepository.save(referenceFile);
    }

    /**
     * Get all the referenceFiles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReferenceFileDTO> findAll() {
        log.debug("Request to get all ReferenceFiles");
        return referenceFileRepository.findAll().stream()
            .map(referenceFileMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one referenceFile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReferenceFileDTO> findOne(Long id) {
        log.debug("Request to get ReferenceFile : {}", id);
        return referenceFileRepository.findById(id)
            .map(referenceFileMapper::toDto);
    }

    /**
     * Delete the referenceFile by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReferenceFile : {}", id);
        referenceFileRepository.deleteById(id);
    }
}
