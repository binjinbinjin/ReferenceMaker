package job.apply.reference.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import job.apply.reference.backend.service.ReferenceFileService;
import job.apply.reference.backend.web.rest.errors.BadRequestAlertException;
import job.apply.reference.backend.web.rest.util.HeaderUtil;
import job.apply.reference.backend.service.dto.ReferenceFileDTO;
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
 * REST controller for managing ReferenceFile.
 */
@RestController
@RequestMapping("/api")
public class ReferenceFileResource {

    private final Logger log = LoggerFactory.getLogger(ReferenceFileResource.class);

    private static final String ENTITY_NAME = "referenceFile";

    private final ReferenceFileService referenceFileService;

    public ReferenceFileResource(ReferenceFileService referenceFileService) {
        this.referenceFileService = referenceFileService;
    }

    /**
     * POST  /reference-files : Create a new referenceFile.
     *
     * @param referenceFileDTO the referenceFileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new referenceFileDTO, or with status 400 (Bad Request) if the referenceFile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reference-files")
    @Timed
    public ResponseEntity<ReferenceFileDTO> createReferenceFile(@Valid @RequestBody ReferenceFileDTO referenceFileDTO) throws URISyntaxException {
        log.debug("REST request to save ReferenceFile : {}", referenceFileDTO);
        if (referenceFileDTO.getId() != null) {
            throw new BadRequestAlertException("A new referenceFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReferenceFileDTO result = referenceFileService.save(referenceFileDTO);
        return ResponseEntity.created(new URI("/api/reference-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reference-files : Updates an existing referenceFile.
     *
     * @param referenceFileDTO the referenceFileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated referenceFileDTO,
     * or with status 400 (Bad Request) if the referenceFileDTO is not valid,
     * or with status 500 (Internal Server Error) if the referenceFileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reference-files")
    @Timed
    public ResponseEntity<ReferenceFileDTO> updateReferenceFile(@Valid @RequestBody ReferenceFileDTO referenceFileDTO) throws URISyntaxException {
        log.debug("REST request to update ReferenceFile : {}", referenceFileDTO);
        if (referenceFileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReferenceFileDTO result = referenceFileService.save(referenceFileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, referenceFileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reference-files : get all the referenceFiles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of referenceFiles in body
     */
    @GetMapping("/reference-files")
    @Timed
    public List<ReferenceFileDTO> getAllReferenceFiles() {
        log.debug("REST request to get all ReferenceFiles");
        return referenceFileService.findAll();
    }

    /**
     * GET  /reference-files/:id : get the "id" referenceFile.
     *
     * @param id the id of the referenceFileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the referenceFileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/reference-files/{id}")
    @Timed
    public ResponseEntity<ReferenceFileDTO> getReferenceFile(@PathVariable Long id) {
        log.debug("REST request to get ReferenceFile : {}", id);
        Optional<ReferenceFileDTO> referenceFileDTO = referenceFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(referenceFileDTO);
    }

    /**
     * DELETE  /reference-files/:id : delete the "id" referenceFile.
     *
     * @param id the id of the referenceFileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reference-files/{id}")
    @Timed
    public ResponseEntity<Void> deleteReferenceFile(@PathVariable Long id) {
        log.debug("REST request to delete ReferenceFile : {}", id);
        referenceFileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
