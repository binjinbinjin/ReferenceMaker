package job.apply.reference.backend.service.impl;

import job.apply.reference.backend.service.CoverLetterService;
import job.apply.reference.backend.domain.CoverLetter;
import job.apply.reference.backend.repository.CoverLetterRepository;
import job.apply.reference.backend.service.dto.CoverLetterDTO;
import job.apply.reference.backend.service.mapper.CoverLetterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing CoverLetter.
 */
@Service
@Transactional
public class CoverLetterServiceImpl implements CoverLetterService {

    private final Logger log = LoggerFactory.getLogger(CoverLetterServiceImpl.class);

    private final CoverLetterRepository coverLetterRepository;

    private final CoverLetterMapper coverLetterMapper;

    public CoverLetterServiceImpl(CoverLetterRepository coverLetterRepository, CoverLetterMapper coverLetterMapper) {
        this.coverLetterRepository = coverLetterRepository;
        this.coverLetterMapper = coverLetterMapper;
    }

    /**
     * Save a coverLetter.
     *
     * @param coverLetterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CoverLetterDTO save(CoverLetterDTO coverLetterDTO) {
        log.debug("Request to save CoverLetter : {}", coverLetterDTO);
        CoverLetter coverLetter = coverLetterMapper.toEntity(coverLetterDTO);
        coverLetter = coverLetterRepository.save(coverLetter);
        return coverLetterMapper.toDto(coverLetter);
    }

    /**
     * Get all the coverLetters.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CoverLetterDTO> findAll() {
        log.debug("Request to get all CoverLetters");
        return coverLetterRepository.findAll().stream()
            .map(coverLetterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one coverLetter by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CoverLetterDTO> findOne(Long id) {
        log.debug("Request to get CoverLetter : {}", id);
        return coverLetterRepository.findById(id)
            .map(coverLetterMapper::toDto);
    }

    /**
     * Delete the coverLetter by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CoverLetter : {}", id);
        coverLetterRepository.deleteById(id);
    }
}
