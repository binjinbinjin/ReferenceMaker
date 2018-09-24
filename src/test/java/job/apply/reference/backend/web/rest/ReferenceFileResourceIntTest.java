package job.apply.reference.backend.web.rest;

import job.apply.reference.backend.ReferenceRecordApp;

import job.apply.reference.backend.domain.ReferenceFile;
import job.apply.reference.backend.repository.ReferenceFileRepository;
import job.apply.reference.backend.service.ReferenceFileService;
import job.apply.reference.backend.service.dto.ReferenceFileDTO;
import job.apply.reference.backend.service.mapper.ReferenceFileMapper;
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
 * Test class for the ReferenceFileResource REST controller.
 *
 * @see ReferenceFileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReferenceRecordApp.class)
public class ReferenceFileResourceIntTest {

    private static final String DEFAULT_FILE = "AAAAAAAAAA";
    private static final String UPDATED_FILE = "BBBBBBBBBB";

    @Autowired
    private ReferenceFileRepository referenceFileRepository;

    @Autowired
    private ReferenceFileMapper referenceFileMapper;
    
    @Autowired
    private ReferenceFileService referenceFileService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReferenceFileMockMvc;

    private ReferenceFile referenceFile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReferenceFileResource referenceFileResource = new ReferenceFileResource(referenceFileService);
        this.restReferenceFileMockMvc = MockMvcBuilders.standaloneSetup(referenceFileResource)
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
    public static ReferenceFile createEntity(EntityManager em) {
        ReferenceFile referenceFile = new ReferenceFile()
            .file(DEFAULT_FILE);
        return referenceFile;
    }

    @Before
    public void initTest() {
        referenceFile = createEntity(em);
    }

    @Test
    @Transactional
    public void createReferenceFile() throws Exception {
        int databaseSizeBeforeCreate = referenceFileRepository.findAll().size();

        // Create the ReferenceFile
        ReferenceFileDTO referenceFileDTO = referenceFileMapper.toDto(referenceFile);
        restReferenceFileMockMvc.perform(post("/api/reference-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenceFileDTO)))
            .andExpect(status().isCreated());

        // Validate the ReferenceFile in the database
        List<ReferenceFile> referenceFileList = referenceFileRepository.findAll();
        assertThat(referenceFileList).hasSize(databaseSizeBeforeCreate + 1);
        ReferenceFile testReferenceFile = referenceFileList.get(referenceFileList.size() - 1);
        assertThat(testReferenceFile.getFile()).isEqualTo(DEFAULT_FILE);
    }

    @Test
    @Transactional
    public void createReferenceFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = referenceFileRepository.findAll().size();

        // Create the ReferenceFile with an existing ID
        referenceFile.setId(1L);
        ReferenceFileDTO referenceFileDTO = referenceFileMapper.toDto(referenceFile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReferenceFileMockMvc.perform(post("/api/reference-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenceFileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReferenceFile in the database
        List<ReferenceFile> referenceFileList = referenceFileRepository.findAll();
        assertThat(referenceFileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFileIsRequired() throws Exception {
        int databaseSizeBeforeTest = referenceFileRepository.findAll().size();
        // set the field null
        referenceFile.setFile(null);

        // Create the ReferenceFile, which fails.
        ReferenceFileDTO referenceFileDTO = referenceFileMapper.toDto(referenceFile);

        restReferenceFileMockMvc.perform(post("/api/reference-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenceFileDTO)))
            .andExpect(status().isBadRequest());

        List<ReferenceFile> referenceFileList = referenceFileRepository.findAll();
        assertThat(referenceFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllReferenceFiles() throws Exception {
        // Initialize the database
        referenceFileRepository.saveAndFlush(referenceFile);

        // Get all the referenceFileList
        restReferenceFileMockMvc.perform(get("/api/reference-files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(referenceFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].file").value(hasItem(DEFAULT_FILE.toString())));
    }
    
    @Test
    @Transactional
    public void getReferenceFile() throws Exception {
        // Initialize the database
        referenceFileRepository.saveAndFlush(referenceFile);

        // Get the referenceFile
        restReferenceFileMockMvc.perform(get("/api/reference-files/{id}", referenceFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(referenceFile.getId().intValue()))
            .andExpect(jsonPath("$.file").value(DEFAULT_FILE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReferenceFile() throws Exception {
        // Get the referenceFile
        restReferenceFileMockMvc.perform(get("/api/reference-files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReferenceFile() throws Exception {
        // Initialize the database
        referenceFileRepository.saveAndFlush(referenceFile);

        int databaseSizeBeforeUpdate = referenceFileRepository.findAll().size();

        // Update the referenceFile
        ReferenceFile updatedReferenceFile = referenceFileRepository.findById(referenceFile.getId()).get();
        // Disconnect from session so that the updates on updatedReferenceFile are not directly saved in db
        em.detach(updatedReferenceFile);
        updatedReferenceFile
            .file(UPDATED_FILE);
        ReferenceFileDTO referenceFileDTO = referenceFileMapper.toDto(updatedReferenceFile);

        restReferenceFileMockMvc.perform(put("/api/reference-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenceFileDTO)))
            .andExpect(status().isOk());

        // Validate the ReferenceFile in the database
        List<ReferenceFile> referenceFileList = referenceFileRepository.findAll();
        assertThat(referenceFileList).hasSize(databaseSizeBeforeUpdate);
        ReferenceFile testReferenceFile = referenceFileList.get(referenceFileList.size() - 1);
        assertThat(testReferenceFile.getFile()).isEqualTo(UPDATED_FILE);
    }

    @Test
    @Transactional
    public void updateNonExistingReferenceFile() throws Exception {
        int databaseSizeBeforeUpdate = referenceFileRepository.findAll().size();

        // Create the ReferenceFile
        ReferenceFileDTO referenceFileDTO = referenceFileMapper.toDto(referenceFile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReferenceFileMockMvc.perform(put("/api/reference-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referenceFileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReferenceFile in the database
        List<ReferenceFile> referenceFileList = referenceFileRepository.findAll();
        assertThat(referenceFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReferenceFile() throws Exception {
        // Initialize the database
        referenceFileRepository.saveAndFlush(referenceFile);

        int databaseSizeBeforeDelete = referenceFileRepository.findAll().size();

        // Get the referenceFile
        restReferenceFileMockMvc.perform(delete("/api/reference-files/{id}", referenceFile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ReferenceFile> referenceFileList = referenceFileRepository.findAll();
        assertThat(referenceFileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReferenceFile.class);
        ReferenceFile referenceFile1 = new ReferenceFile();
        referenceFile1.setId(1L);
        ReferenceFile referenceFile2 = new ReferenceFile();
        referenceFile2.setId(referenceFile1.getId());
        assertThat(referenceFile1).isEqualTo(referenceFile2);
        referenceFile2.setId(2L);
        assertThat(referenceFile1).isNotEqualTo(referenceFile2);
        referenceFile1.setId(null);
        assertThat(referenceFile1).isNotEqualTo(referenceFile2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReferenceFileDTO.class);
        ReferenceFileDTO referenceFileDTO1 = new ReferenceFileDTO();
        referenceFileDTO1.setId(1L);
        ReferenceFileDTO referenceFileDTO2 = new ReferenceFileDTO();
        assertThat(referenceFileDTO1).isNotEqualTo(referenceFileDTO2);
        referenceFileDTO2.setId(referenceFileDTO1.getId());
        assertThat(referenceFileDTO1).isEqualTo(referenceFileDTO2);
        referenceFileDTO2.setId(2L);
        assertThat(referenceFileDTO1).isNotEqualTo(referenceFileDTO2);
        referenceFileDTO1.setId(null);
        assertThat(referenceFileDTO1).isNotEqualTo(referenceFileDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(referenceFileMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(referenceFileMapper.fromId(null)).isNull();
    }
}
