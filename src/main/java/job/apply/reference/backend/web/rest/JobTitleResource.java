package job.apply.reference.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import job.apply.reference.backend.service.JobTitleService;
import job.apply.reference.backend.web.rest.errors.BadRequestAlertException;
import job.apply.reference.backend.web.rest.util.HeaderUtil;
import job.apply.reference.backend.service.dto.JobTitleDTO;
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
 * REST controller for managing JobTitle.
 */
@RestController
@RequestMapping("/api")
public class JobTitleResource {

    private final Logger log = LoggerFactory.getLogger(JobTitleResource.class);

    private static final String ENTITY_NAME = "jobTitle";

    private final JobTitleService jobTitleService;

    public JobTitleResource(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }

    /**
     * POST  /job-titles : Create a new jobTitle.
     *
     * @param jobTitleDTO the jobTitleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobTitleDTO, or with status 400 (Bad Request) if the jobTitle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-titles")
    @Timed
    public ResponseEntity<JobTitleDTO> createJobTitle(@Valid @RequestBody JobTitleDTO jobTitleDTO) throws URISyntaxException {
        log.debug("REST request to save JobTitle : {}", jobTitleDTO);
        if (jobTitleDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobTitle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobTitleDTO result = jobTitleService.save(jobTitleDTO);
        return ResponseEntity.created(new URI("/api/job-titles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-titles : Updates an existing jobTitle.
     *
     * @param jobTitleDTO the jobTitleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobTitleDTO,
     * or with status 400 (Bad Request) if the jobTitleDTO is not valid,
     * or with status 500 (Internal Server Error) if the jobTitleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-titles")
    @Timed
    public ResponseEntity<JobTitleDTO> updateJobTitle(@Valid @RequestBody JobTitleDTO jobTitleDTO) throws URISyntaxException {
        log.debug("REST request to update JobTitle : {}", jobTitleDTO);
        if (jobTitleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobTitleDTO result = jobTitleService.save(jobTitleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobTitleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-titles : get all the jobTitles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of jobTitles in body
     */
    @GetMapping("/job-titles")
    @Timed
    public List<JobTitleDTO> getAllJobTitles() {
        log.debug("REST request to get all JobTitles");
        return jobTitleService.findAll();
    }

    /**
     * GET  /job-titles/:id : get the "id" jobTitle.
     *
     * @param id the id of the jobTitleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobTitleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-titles/{id}")
    @Timed
    public ResponseEntity<JobTitleDTO> getJobTitle(@PathVariable Long id) {
        log.debug("REST request to get JobTitle : {}", id);
        Optional<JobTitleDTO> jobTitleDTO = jobTitleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobTitleDTO);
    }

    /**
     * DELETE  /job-titles/:id : delete the "id" jobTitle.
     *
     * @param id the id of the jobTitleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-titles/{id}")
    @Timed
    public ResponseEntity<Void> deleteJobTitle(@PathVariable Long id) {
        log.debug("REST request to delete JobTitle : {}", id);
        jobTitleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
