package job.apply.reference.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import job.apply.reference.backend.service.ReferenceService;
import job.apply.reference.backend.web.rest.errors.BadRequestAlertException;
import job.apply.reference.backend.web.rest.util.HeaderUtil;
import job.apply.reference.backend.web.rest.util.PaginationUtil;
import job.apply.reference.backend.service.dto.ReferenceDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Reference.
 */
@RestController
@RequestMapping("/api")
public class ReferenceResource {

    private final Logger log = LoggerFactory.getLogger(ReferenceResource.class);

    private static final String ENTITY_NAME = "reference";

    private final ReferenceService referenceService;

    public ReferenceResource(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    /**
     * POST  /references : Create a new reference.
     *
     * @param referenceDTO the referenceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new referenceDTO, or with status 400 (Bad Request) if the reference has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/references/unrestricted")
    @Timed
    public ResponseEntity<ReferenceDTO> createReferenceWithoutRestriction(@Valid @RequestBody ReferenceDTO referenceDTO) throws URISyntaxException {
        log.debug("REST request to save Reference : {}", referenceDTO);
        if (referenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new reference cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReferenceDTO result = this.referenceService.saveWithOutRestriction(referenceDTO);
        return ResponseEntity.created(new URI("/api/references/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /references : Create a new reference.
     *
     * @param referenceDTO the referenceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new referenceDTO, or with status 400 (Bad Request) if the reference has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/references")
    @Timed
    public ResponseEntity<ReferenceDTO> createReference(@Valid @RequestBody ReferenceDTO referenceDTO) throws URISyntaxException {
        log.debug("REST request to save Reference : {}", referenceDTO);
        if (referenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new reference cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReferenceDTO result = referenceService.saveWithOutRestriction(referenceDTO);
        return ResponseEntity.created(new URI("/api/references/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /references : Updates an existing reference.
     *
     * @param referenceDTO the referenceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated referenceDTO,
     * or with status 400 (Bad Request) if the referenceDTO is not valid,
     * or with status 500 (Internal Server Error) if the referenceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/references")
    @Timed
    public ResponseEntity<ReferenceDTO> updateReference(@Valid @RequestBody ReferenceDTO referenceDTO) throws URISyntaxException {
        log.debug("REST request to update Reference : {}", referenceDTO);
        if (referenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReferenceDTO result = referenceService.saveWithOutRestriction(referenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, referenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /references : get all the references.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of references in body
     */
    @GetMapping("/references")
    @Timed
    public ResponseEntity<List<ReferenceDTO>> getAllReferences(Pageable pageable) {
        log.debug("REST request to get a page of References");
        Page<ReferenceDTO> page = referenceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/references");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /references/:id : get the "id" reference.
     *
     * @param id the id of the referenceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the referenceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/references/{id}")
    @Timed
    public ResponseEntity<ReferenceDTO> getReference(@PathVariable Long id) {
        log.debug("REST request to get Reference : {}", id);
        Optional<ReferenceDTO> referenceDTO = referenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(referenceDTO);
    }

    /**
     * GET  /references/findCompany?companyName=company : get all the references with matched company name. "company" compnay name
     *
     * @param companyName companyName
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of references in body
     */
    @GetMapping("/references/findCompany")
    @Timed
    public ResponseEntity<List<ReferenceDTO>> getAllReferencesWithCompanyMatched(@NotNull @RequestParam String companyName, Pageable pageable) {
        log.debug("REST request to get a page of References");
        Page<ReferenceDTO> page = referenceService.findAllWithCompanyContainsAllLetters(companyName, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/references");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * DELETE  /references/:id : delete the "id" reference.
     *
     * @param id the id of the referenceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/references/{id}")
    @Timed
    public ResponseEntity<Void> deleteReference(@PathVariable Long id) {
        log.debug("REST request to delete Reference : {}", id);
        referenceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
