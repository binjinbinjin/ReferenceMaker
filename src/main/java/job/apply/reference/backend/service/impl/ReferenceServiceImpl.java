package job.apply.reference.backend.service.impl;

import job.apply.reference.backend.domain.ReferenceFile;
import job.apply.reference.backend.service.*;
import job.apply.reference.backend.domain.Reference;
import job.apply.reference.backend.repository.ReferenceRepository;
import job.apply.reference.backend.service.dto.ReferenceDTO;
import job.apply.reference.backend.service.mapper.ReferenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

/**
 * Service Implementation for managing Reference.
 */
@Service
@Transactional
public class ReferenceServiceImpl implements ReferenceService {

    private final Logger log = LoggerFactory.getLogger(ReferenceServiceImpl.class);

    private final ReferenceRepository referenceRepository;

    private final ReferenceMapper referenceMapper;

    private final CoverLetterService coverLetterService;

    private final JobTitleService jobTitleService;

    private final LocationService locationService;

    private final ReferenceFileService referenceFileService;

    private final ResumeService resumeService;

    private final int ASCII_CAPITAL_A = 65;

    private final int ASCII_CAPITAL_Z = 90;

    private final int ASCII_LOWER_A = 97;

    private final int ASCII_LOWER_Z = 122;

    public ReferenceServiceImpl(ReferenceRepository referenceRepository,
                                ReferenceMapper referenceMapper,
                                CoverLetterService coverLetterService,
                                JobTitleService jobTitleService,
                                LocationService locationService,
                                ReferenceFileService referenceFileServiceService,
                                ResumeService resumeService) {
        this.referenceRepository = referenceRepository;
        this.referenceMapper = referenceMapper;
        this.coverLetterService = coverLetterService;
        this.jobTitleService = jobTitleService;
        this.locationService = locationService;
        this.referenceFileService = referenceFileServiceService;
        this.resumeService = resumeService;
    }

    /**
     * Save a reference.
     *
     * @param referenceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReferenceDTO save(ReferenceDTO referenceDTO) {
        log.debug("Request to save Reference : {}", referenceDTO);
        Reference reference = referenceMapper.toEntity(referenceDTO);
        reference = referenceRepository.save(reference);
        return referenceMapper.toDto(reference);
    }

    /**
     * Save a reference.
     *
     * @param reference the entity to save
     * @return the persisted entity
     */
    @Override
    public Reference save(Reference reference) {
        return this.referenceRepository.save(reference);
    }

    /**
     * Save a reference.
     *
     * @param referenceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReferenceDTO saveWithOutRestriction(ReferenceDTO referenceDTO) {
        Reference reference = this.referenceMapper.toEntity(referenceDTO);
        return this.referenceMapper.toDto(this.saveWithOutRestriction(reference, null));
    }

    /**
     * Save a reference.
     *
     * @param reference the entity to save
     * @param time time instance
     * @return the persisted entity
     */
    @Override
    public Reference saveWithOutRestriction(Reference reference, Instant time) {
        reference.setCover(this.coverLetterService.getOrCreate(reference.getCover().getName()));
        reference.setJobTitle(this.jobTitleService.getOrCreate(reference.getJobTitle().getJobTitle()));
        reference.setLocation(this.locationService.getOrCreate(reference.getLocation().getLocation()));
        reference.setReferenceFile(this.referenceFileService.getOrCreate(reference.getReferenceFile().getFile()));
        reference.setResume(this.resumeService.getOrCreate(reference.getResume().getName()));
        reference.setApplyTime( time == null ? Instant.now() : time);
        return this.referenceRepository.save(reference);
    }

    /**
     * Get all the references.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReferenceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all References");
        return referenceRepository.findAll(pageable)
            .map(ReferenceDTO::setReferenceValue);
    }

    /**
     * Get all the references with company name that contains input characters.
     *
     * @param characters the characters
     * @param pageable   the pagination information
     * @return the list of entities
     */
    @Override
    public Page<ReferenceDTO> findAllWithCompanyContainsAllLetters(String characters, Pageable pageable) {
        String regex = "";
        for (int i = 0; i < characters.length(); i++) {
            String currentCharacter = characters.substring(i, i + 1);
            regex += "[";
            if ((characters.charAt(i) >= this.ASCII_CAPITAL_A && characters.charAt(i) <= this.ASCII_CAPITAL_Z) ||
                (characters.charAt(i) >= this.ASCII_LOWER_A && characters.charAt(i) <= this.ASCII_LOWER_Z)) {
                regex += currentCharacter.toLowerCase() + currentCharacter.toLowerCase();
            } else {
                regex += characters;
            }
            regex += "].*";
        }
        return referenceRepository.findAllByCompanyMatchesRegex(regex, pageable).map(ReferenceDTO::setReferenceValue);
    }

    /**
     * Get one reference by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReferenceDTO> findOne(Long id) {
        log.debug("Request to get Reference : {}", id);
        return referenceRepository.findById(id)
            .map(referenceMapper::toDto);
    }

    /**
     * Delete the reference by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reference : {}", id);
        referenceRepository.deleteById(id);
    }
}

