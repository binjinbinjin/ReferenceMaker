package job.apply.reference.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import job.apply.reference.backend.service.CoverLetterService;
import job.apply.reference.backend.web.rest.errors.BadRequestAlertException;
import job.apply.reference.backend.web.rest.util.HeaderUtil;
import job.apply.reference.backend.service.dto.CoverLetterDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CoverLetter.
 */
@RestController
@RequestMapping("/api")
public class CoverLetterResource {

    private final Logger log = LoggerFactory.getLogger(CoverLetterResource.class);

    private static final String ENTITY_NAME = "coverLetter";

    private final CoverLetterService coverLetterService;

    public CoverLetterResource(CoverLetterService coverLetterService) {
        this.coverLetterService = coverLetterService;
    }

    /**
     * POST  /cover-letters : Create a new coverLetter.
     *
     * @param coverLetterDTO the coverLetterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coverLetterDTO, or with status 400 (Bad Request) if the coverLetter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cover-letters")
    @Timed
    public ResponseEntity<CoverLetterDTO> createCoverLetter(@Valid @RequestBody CoverLetterDTO coverLetterDTO) throws URISyntaxException {
        log.debug("REST request to save CoverLetter : {}", coverLetterDTO);
        if (coverLetterDTO.getId() != null) {
            throw new BadRequestAlertException("A new coverLetter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoverLetterDTO result = coverLetterService.save(coverLetterDTO);
        return ResponseEntity.created(new URI("/api/cover-letters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cover-letters : Updates an existing coverLetter.
     *
     * @param coverLetterDTO the coverLetterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coverLetterDTO,
     * or with status 400 (Bad Request) if the coverLetterDTO is not valid,
     * or with status 500 (Internal Server Error) if the coverLetterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cover-letters")
    @Timed
    public ResponseEntity<CoverLetterDTO> updateCoverLetter(@Valid @RequestBody CoverLetterDTO coverLetterDTO) throws URISyntaxException {
        log.debug("REST request to update CoverLetter : {}", coverLetterDTO);
        if (coverLetterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CoverLetterDTO result = coverLetterService.save(coverLetterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coverLetterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cover-letters : get all the coverLetters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of coverLetters in body
     */
    @GetMapping("/cover-letters")
    @Timed
    public List<CoverLetterDTO> getAllCoverLetters() {
        log.debug("REST request to get all CoverLetters");
        return coverLetterService.findAll();
    }

    /**
     * GET  /cover-letters/:id : get the "id" coverLetter.
     *
     * @param id the id of the coverLetterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coverLetterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cover-letters/{id}")
    @Timed
    public ResponseEntity<CoverLetterDTO> getCoverLetter(@PathVariable Long id) {
        log.debug("REST request to get CoverLetter : {}", id);
        Optional<CoverLetterDTO> coverLetterDTO = coverLetterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coverLetterDTO);
    }

    /**
     * DELETE  /cover-letters/:id : delete the "id" coverLetter.
     *
     * @param id the id of the coverLetterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cover-letters/{id}")
    @Timed
    public ResponseEntity<Void> deleteCoverLetter(@PathVariable Long id) {
        log.debug("REST request to delete CoverLetter : {}", id);
        coverLetterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
