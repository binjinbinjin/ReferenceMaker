package job.apply.reference.backend.web.rest;

import job.apply.reference.backend.ReferenceRecordApp;

import job.apply.reference.backend.domain.CoverLetter;
import job.apply.reference.backend.repository.CoverLetterRepository;
import job.apply.reference.backend.service.CoverLetterService;
import job.apply.reference.backend.service.dto.CoverLetterDTO;
import job.apply.reference.backend.service.mapper.CoverLetterMapper;
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
 * Test class for the CoverLetterResource REST controller.
 *
 * @see CoverLetterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReferenceRecordApp.class)
public class CoverLetterResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private CoverLetterRepository coverLetterRepository;

    @Autowired
    private CoverLetterMapper coverLetterMapper;
    
    @Autowired
    private CoverLetterService coverLetterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCoverLetterMockMvc;

    private CoverLetter coverLetter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CoverLetterResource coverLetterResource = new CoverLetterResource(coverLetterService);
        this.restCoverLetterMockMvc = MockMvcBuilders.standaloneSetup(coverLetterResource)
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
    public static CoverLetter createEntity(EntityManager em) {
        CoverLetter coverLetter = new CoverLetter()
            .name(DEFAULT_NAME);
        return coverLetter;
    }

    @Before
    public void initTest() {
        coverLetter = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoverLetter() throws Exception {
        int databaseSizeBeforeCreate = coverLetterRepository.findAll().size();

        // Create the CoverLetter
        CoverLetterDTO coverLetterDTO = coverLetterMapper.toDto(coverLetter);
        restCoverLetterMockMvc.perform(post("/api/cover-letters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverLetterDTO)))
            .andExpect(status().isCreated());

        // Validate the CoverLetter in the database
        List<CoverLetter> coverLetterList = coverLetterRepository.findAll();
        assertThat(coverLetterList).hasSize(databaseSizeBeforeCreate + 1);
        CoverLetter testCoverLetter = coverLetterList.get(coverLetterList.size() - 1);
        assertThat(testCoverLetter.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createCoverLetterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coverLetterRepository.findAll().size();

        // Create the CoverLetter with an existing ID
        coverLetter.setId(1L);
        CoverLetterDTO coverLetterDTO = coverLetterMapper.toDto(coverLetter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoverLetterMockMvc.perform(post("/api/cover-letters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverLetterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoverLetter in the database
        List<CoverLetter> coverLetterList = coverLetterRepository.findAll();
        assertThat(coverLetterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = coverLetterRepository.findAll().size();
        // set the field null
        coverLetter.setName(null);

        // Create the CoverLetter, which fails.
        CoverLetterDTO coverLetterDTO = coverLetterMapper.toDto(coverLetter);

        restCoverLetterMockMvc.perform(post("/api/cover-letters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverLetterDTO)))
            .andExpect(status().isBadRequest());

        List<CoverLetter> coverLetterList = coverLetterRepository.findAll();
        assertThat(coverLetterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCoverLetters() throws Exception {
        // Initialize the database
        coverLetterRepository.saveAndFlush(coverLetter);

        // Get all the coverLetterList
        restCoverLetterMockMvc.perform(get("/api/cover-letters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coverLetter.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getCoverLetter() throws Exception {
        // Initialize the database
        coverLetterRepository.saveAndFlush(coverLetter);

        // Get the coverLetter
        restCoverLetterMockMvc.perform(get("/api/cover-letters/{id}", coverLetter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coverLetter.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCoverLetter() throws Exception {
        // Get the coverLetter
        restCoverLetterMockMvc.perform(get("/api/cover-letters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoverLetter() throws Exception {
        // Initialize the database
        coverLetterRepository.saveAndFlush(coverLetter);

        int databaseSizeBeforeUpdate = coverLetterRepository.findAll().size();

        // Update the coverLetter
        CoverLetter updatedCoverLetter = coverLetterRepository.findById(coverLetter.getId()).get();
        // Disconnect from session so that the updates on updatedCoverLetter are not directly saved in db
        em.detach(updatedCoverLetter);
        updatedCoverLetter
            .name(UPDATED_NAME);
        CoverLetterDTO coverLetterDTO = coverLetterMapper.toDto(updatedCoverLetter);

        restCoverLetterMockMvc.perform(put("/api/cover-letters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverLetterDTO)))
            .andExpect(status().isOk());

        // Validate the CoverLetter in the database
        List<CoverLetter> coverLetterList = coverLetterRepository.findAll();
        assertThat(coverLetterList).hasSize(databaseSizeBeforeUpdate);
        CoverLetter testCoverLetter = coverLetterList.get(coverLetterList.size() - 1);
        assertThat(testCoverLetter.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCoverLetter() throws Exception {
        int databaseSizeBeforeUpdate = coverLetterRepository.findAll().size();

        // Create the CoverLetter
        CoverLetterDTO coverLetterDTO = coverLetterMapper.toDto(coverLetter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoverLetterMockMvc.perform(put("/api/cover-letters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverLetterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoverLetter in the database
        List<CoverLetter> coverLetterList = coverLetterRepository.findAll();
        assertThat(coverLetterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoverLetter() throws Exception {
        // Initialize the database
        coverLetterRepository.saveAndFlush(coverLetter);

        int databaseSizeBeforeDelete = coverLetterRepository.findAll().size();

        // Get the coverLetter
        restCoverLetterMockMvc.perform(delete("/api/cover-letters/{id}", coverLetter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CoverLetter> coverLetterList = coverLetterRepository.findAll();
        assertThat(coverLetterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoverLetter.class);
        CoverLetter coverLetter1 = new CoverLetter();
        coverLetter1.setId(1L);
        CoverLetter coverLetter2 = new CoverLetter();
        coverLetter2.setId(coverLetter1.getId());
        assertThat(coverLetter1).isEqualTo(coverLetter2);
        coverLetter2.setId(2L);
        assertThat(coverLetter1).isNotEqualTo(coverLetter2);
        coverLetter1.setId(null);
        assertThat(coverLetter1).isNotEqualTo(coverLetter2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoverLetterDTO.class);
        CoverLetterDTO coverLetterDTO1 = new CoverLetterDTO();
        coverLetterDTO1.setId(1L);
        CoverLetterDTO coverLetterDTO2 = new CoverLetterDTO();
        assertThat(coverLetterDTO1).isNotEqualTo(coverLetterDTO2);
        coverLetterDTO2.setId(coverLetterDTO1.getId());
        assertThat(coverLetterDTO1).isEqualTo(coverLetterDTO2);
        coverLetterDTO2.setId(2L);
        assertThat(coverLetterDTO1).isNotEqualTo(coverLetterDTO2);
        coverLetterDTO1.setId(null);
        assertThat(coverLetterDTO1).isNotEqualTo(coverLetterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(coverLetterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(coverLetterMapper.fromId(null)).isNull();
    }
}
