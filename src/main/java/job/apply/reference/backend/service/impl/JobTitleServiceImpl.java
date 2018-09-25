package job.apply.reference.backend.service.impl;

import job.apply.reference.backend.service.JobTitleService;
import job.apply.reference.backend.domain.JobTitle;
import job.apply.reference.backend.repository.JobTitleRepository;
import job.apply.reference.backend.service.dto.JobTitleDTO;
import job.apply.reference.backend.service.mapper.JobTitleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing JobTitle.
 */
@Service
@Transactional
public class JobTitleServiceImpl implements JobTitleService {

    private final Logger log = LoggerFactory.getLogger(JobTitleServiceImpl.class);

    private final JobTitleRepository jobTitleRepository;

    private final JobTitleMapper jobTitleMapper;

    public JobTitleServiceImpl(JobTitleRepository jobTitleRepository, JobTitleMapper jobTitleMapper) {
        this.jobTitleRepository = jobTitleRepository;
        this.jobTitleMapper = jobTitleMapper;
    }

    /**
     * Save a jobTitle.
     *
     * @param jobTitleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JobTitleDTO save(JobTitleDTO jobTitleDTO) {
        log.debug("Request to save JobTitle : {}", jobTitleDTO);
        JobTitle jobTitle = jobTitleMapper.toEntity(jobTitleDTO);
        jobTitle = jobTitleRepository.save(jobTitle);
        return jobTitleMapper.toDto(jobTitle);
    }

    /**
     * Check existence of a Job title
     *
     * @param name@return true iff exist a title with input name
     */
    @Override
    public boolean hasJobTitle(String name) {
        return this.jobTitleRepository.existsJobTitleByJobTitle(name);
    }

    /**
     * Get the job title form database, or create a new job title if not exist.
     *
     * @param name@return JobTitle
     */
    @Override
    public JobTitle getOrCreate(String name) {
        if (name.trim().length() == 0) name = " ";
        JobTitle jobTitle = this.jobTitleRepository.getJobTitleByJobTitle(name);
        if (jobTitle != null) return jobTitle;
        jobTitle = new JobTitle();
        jobTitle.setJobTitle(name);
        return this.jobTitleRepository.save(jobTitle);
    }

    /**
     * Get all the jobTitles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<JobTitleDTO> findAll() {
        log.debug("Request to get all JobTitles");
        return jobTitleRepository.findAll().stream()
            .map(jobTitleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one jobTitle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobTitleDTO> findOne(Long id) {
        log.debug("Request to get JobTitle : {}", id);
        return jobTitleRepository.findById(id)
            .map(jobTitleMapper::toDto);
    }

    /**
     * Delete the jobTitle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobTitle : {}", id);
        jobTitleRepository.deleteById(id);
    }
}
