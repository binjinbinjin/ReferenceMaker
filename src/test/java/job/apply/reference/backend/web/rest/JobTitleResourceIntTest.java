package job.apply.reference.backend.web.rest;

import job.apply.reference.backend.ReferenceRecordApp;

import job.apply.reference.backend.domain.JobTitle;
import job.apply.reference.backend.repository.JobTitleRepository;
import job.apply.reference.backend.service.JobTitleService;
import job.apply.reference.backend.service.dto.JobTitleDTO;
import job.apply.reference.backend.service.mapper.JobTitleMapper;
import job.apply.reference.backend.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static job.apply.reference.backend.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the JobTitleResource REST controller.
 *
 * @see JobTitleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReferenceRecordApp.class)
public class JobTitleResourceIntTest {

    private static final String DEFAULT_JOB_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_TITLE = "BBBBBBBBBB";

    @Autowired
    private JobTitleRepository jobTitleRepository;

    @Autowired
    private JobTitleMapper jobTitleMapper;
    
    @Autowired
    private JobTitleService jobTitleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJobTitleMockMvc;

    private JobTitle jobTitle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JobTitleResource jobTitleResource = new JobTitleResource(jobTitleService);
        this.restJobTitleMockMvc = MockMvcBuilders.standaloneSetup(jobTitleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JobTitle createEntity(EntityManager em) {
        JobTitle jobTitle = new JobTitle()
            .jobTitle(DEFAULT_JOB_TITLE);
        return jobTitle;
    }

    @Before
    public void initTest() {
        jobTitle = createEntity(em);
    }

    @Test
    @Transactional
    public void createJobTitle() throws Exception {
        int databaseSizeBeforeCreate = jobTitleRepository.findAll().size();

        // Create the JobTitle
        JobTitleDTO jobTitleDTO = jobTitleMapper.toDto(jobTitle);
        restJobTitleMockMvc.perform(post("/api/job-titles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobTitleDTO)))
            .andExpect(status().isCreated());

        // Validate the JobTitle in the database
        List<JobTitle> jobTitleList = jobTitleRepository.findAll();
        assertThat(jobTitleList).hasSize(databaseSizeBeforeCreate + 1);
        JobTitle testJobTitle = jobTitleList.get(jobTitleList.size() - 1);
        assertThat(testJobTitle.getJobTitle()).isEqualTo(DEFAULT_JOB_TITLE);
    }

    @Test
    @Transactional
    public void createJobTitleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jobTitleRepository.findAll().size();

        // Create the JobTitle with an existing ID
        jobTitle.setId(1L);
        JobTitleDTO jobTitleDTO = jobTitleMapper.toDto(jobTitle);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobTitleMockMvc.perform(post("/api/job-titles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobTitleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JobTitle in the database
        List<JobTitle> jobTitleList = jobTitleRepository.findAll();
        assertThat(jobTitleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkJobTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobTitleRepository.findAll().size();
        // set the field null
        jobTitle.setJobTitle(null);

        // Create the JobTitle, which fails.
        JobTitleDTO jobTitleDTO = jobTitleMapper.toDto(jobTitle);

        restJobTitleMockMvc.perform(post("/api/job-titles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobTitleDTO)))
            .andExpect(status().isBadRequest());

        List<JobTitle> jobTitleList = jobTitleRepository.findAll();
        assertThat(jobTitleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJobTitles() throws Exception {
        // Initialize the database
        jobTitleRepository.saveAndFlush(jobTitle);

        // Get all the jobTitleList
        restJobTitleMockMvc.perform(get("/api/job-titles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jobTitle.getId().intValue())))
            .andExpect(jsonPath("$.[*].jobTitle").value(hasItem(DEFAULT_JOB_TITLE.toString())));
    }
    
    @Test
    @Transactional
    public void getJobTitle() throws Exception {
        // Initialize the database
        jobTitleRepository.saveAndFlush(jobTitle);

        // Get the jobTitle
        restJobTitleMockMvc.perform(get("/api/job-titles/{id}", jobTitle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jobTitle.getId().intValue()))
            .andExpect(jsonPath("$.jobTitle").value(DEFAULT_JOB_TITLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJobTitle() throws Exception {
        // Get the jobTitle
        restJobTitleMockMvc.perform(get("/api/job-titles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJobTitle() throws Exception {
        // Initialize the database
        jobTitleRepository.saveAndFlush(jobTitle);

        int databaseSizeBeforeUpdate = jobTitleRepository.findAll().size();

        // Update the jobTitle
        JobTitle updatedJobTitle = jobTitleRepository.findById(jobTitle.getId()).get();
        // Disconnect from session so that the updates on updatedJobTitle are not directly saved in db
        em.detach(updatedJobTitle);
        updatedJobTitle
            .jobTitle(UPDATED_JOB_TITLE);
        JobTitleDTO jobTitleDTO = jobTitleMapper.toDto(updatedJobTitle);

        restJobTitleMockMvc.perform(put("/api/job-titles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobTitleDTO)))
            .andExpect(status().isOk());

        // Validate the JobTitle in the database
        List<JobTitle> jobTitleList = jobTitleRepository.findAll();
        assertThat(jobTitleList).hasSize(databaseSizeBeforeUpdate);
        JobTitle testJobTitle = jobTitleList.get(jobTitleList.size() - 1);
        assertThat(testJobTitle.getJobTitle()).isEqualTo(UPDATED_JOB_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingJobTitle() throws Exception {
        int databaseSizeBeforeUpdate = jobTitleRepository.findAll().size();

        // Create the JobTitle
        JobTitleDTO jobTitleDTO = jobTitleMapper.toDto(jobTitle);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJobTitleMockMvc.perform(put("/api/job-titles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobTitleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JobTitle in the database
        List<JobTitle> jobTitleList = jobTitleRepository.findAll();
        assertThat(jobTitleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJobTitle() throws Exception {
        // Initialize the database
        jobTitleRepository.saveAndFlush(jobTitle);

        int databaseSizeBeforeDelete = jobTitleRepository.findAll().size();

        // Get the jobTitle
        restJobTitleMockMvc.perform(delete("/api/job-titles/{id}", jobTitle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<JobTitle> jobTitleList = jobTitleRepository.findAll();
        assertThat(jobTitleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobTitle.class);
        JobTitle jobTitle1 = new JobTitle();
        jobTitle1.setId(1L);
        JobTitle jobTitle2 = new JobTitle();
        jobTitle2.setId(jobTitle1.getId());
        assertThat(jobTitle1).isEqualTo(jobTitle2);
        jobTitle2.setId(2L);
        assertThat(jobTitle1).isNotEqualTo(jobTitle2);
        jobTitle1.setId(null);
        assertThat(jobTitle1).isNotEqualTo(jobTitle2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobTitleDTO.class);
        JobTitleDTO jobTitleDTO1 = new JobTitleDTO();
        jobTitleDTO1.setId(1L);
        JobTitleDTO jobTitleDTO2 = new JobTitleDTO();
        assertThat(jobTitleDTO1).isNotEqualTo(jobTitleDTO2);
        jobTitleDTO2.setId(jobTitleDTO1.getId());
        assertThat(jobTitleDTO1).isEqualTo(jobTitleDTO2);
        jobTitleDTO2.setId(2L);
        assertThat(jobTitleDTO1).isNotEqualTo(jobTitleDTO2);
        jobTitleDTO1.setId(null);
        assertThat(jobTitleDTO1).isNotEqualTo(jobTitleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(jobTitleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(jobTitleMapper.fromId(null)).isNull();
    }
}
