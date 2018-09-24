package job.apply.reference.backend.service.impl;

import job.apply.reference.backend.service.ResumeService;
import job.apply.reference.backend.domain.Resume;
import job.apply.reference.backend.repository.ResumeRepository;
import job.apply.reference.backend.service.dto.ResumeDTO;
import job.apply.reference.backend.service.mapper.ResumeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Resume.
 */
@Service
@Transactional
public class ResumeServiceImpl implements ResumeService {

    private final Logger log = LoggerFactory.getLogger(ResumeServiceImpl.class);

    private final ResumeRepository resumeRepository;

    private final ResumeMapper resumeMapper;

    public ResumeServiceImpl(ResumeRepository resumeRepository, ResumeMapper resumeMapper) {
        this.resumeRepository = resumeRepository;
        this.resumeMapper = resumeMapper;
    }

    /**
     * Save a resume.
     *
     * @param resumeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResumeDTO save(ResumeDTO resumeDTO) {
        log.debug("Request to save Resume : {}", resumeDTO);
        Resume resume = resumeMapper.toEntity(resumeDTO);
        resume = resumeRepository.save(resume);
        return resumeMapper.toDto(resume);
    }

    /**
     * Check existence of a resume
     *
     * @param name@return true iff exist a resume with input name
     */
    @Override
    public boolean hasResume(String name) {
        return this.resumeRepository.existsResumeByName(name);
    }

    /**
     * Get the resume form database, or create a new resume if not exist.
     *
     * @param name@return Resume
     */
    @Override
    public Resume getOrCreate(String name) {
        Resume resume = this.resumeRepository.getResumeByName(name);
        if (resume != null) return resume;
        resume = new Resume();
        resume.setName(name);
        return this.resumeRepository.save(resume);
    }

    /**
     * Get all the resumes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResumeDTO> findAll() {
        log.debug("Request to get all Resumes");
        return resumeRepository.findAll().stream()
            .map(resumeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one resume by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResumeDTO> findOne(Long id) {
        log.debug("Request to get Resume : {}", id);
        return resumeRepository.findById(id)
            .map(resumeMapper::toDto);
    }

    /**
     * Delete the resume by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Resume : {}", id);
        resumeRepository.deleteById(id);
    }
}
