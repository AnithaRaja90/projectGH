package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.Batch;
import com.niche.ng.repository.BatchRepository;
import com.niche.ng.service.BatchService;
import com.niche.ng.service.dto.BatchDTO;
import com.niche.ng.service.mapper.BatchMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;

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
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.niche.ng.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BatchResource REST controller.
 *
 * @see BatchResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class BatchResourceIntTest {

    private static final String DEFAULT_BATCH_NO = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final String DEFAULT_MOTHER_BED = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_BED = "BBBBBBBBBB";

    private static final Integer DEFAULT_SHOWING_TYPE = 1;
    private static final Integer UPDATED_SHOWING_TYPE = 2;

    private static final LocalDate DEFAULT_SOWING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SOWING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CLOSED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CLOSED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ROUND = 1;
    private static final Integer UPDATED_ROUND = 2;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Long DEFAULT_CREATED_BY = 1L;
    private static final Long UPDATED_CREATED_BY = 2L;

    private static final Long DEFAULT_MODIFIED_BY = 1L;
    private static final Long UPDATED_MODIFIED_BY = 2L;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BatchRepository batchRepository;


    @Autowired
    private BatchMapper batchMapper;
    

    @Autowired
    private BatchService batchService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBatchMockMvc;

    private Batch batch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BatchResource batchResource = new BatchResource(batchService);
        this.restBatchMockMvc = MockMvcBuilders.standaloneSetup(batchResource)
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
    public static Batch createEntity(EntityManager em) {
        Batch batch = new Batch()
            .batchNo(DEFAULT_BATCH_NO)
            .batchName(DEFAULT_BATCH_NAME)
            .quantity(DEFAULT_QUANTITY)
            .motherBed(DEFAULT_MOTHER_BED)
            .showingType(DEFAULT_SHOWING_TYPE)
            .sowingDate(DEFAULT_SOWING_DATE)
            .closedDate(DEFAULT_CLOSED_DATE)
            .round(DEFAULT_ROUND)
            .remarks(DEFAULT_REMARKS)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return batch;
    }

    @Before
    public void initTest() {
        batch = createEntity(em);
    }

    @Test
    @Transactional
    public void createBatch() throws Exception {
        int databaseSizeBeforeCreate = batchRepository.findAll().size();

        // Create the Batch
        BatchDTO batchDTO = batchMapper.toDto(batch);
        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isCreated());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeCreate + 1);
        Batch testBatch = batchList.get(batchList.size() - 1);
        assertThat(testBatch.getBatchNo()).isEqualTo(DEFAULT_BATCH_NO);
        assertThat(testBatch.getBatchName()).isEqualTo(DEFAULT_BATCH_NAME);
        assertThat(testBatch.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testBatch.getMotherBed()).isEqualTo(DEFAULT_MOTHER_BED);
        assertThat(testBatch.getShowingType()).isEqualTo(DEFAULT_SHOWING_TYPE);
        assertThat(testBatch.getSowingDate()).isEqualTo(DEFAULT_SOWING_DATE);
        assertThat(testBatch.getClosedDate()).isEqualTo(DEFAULT_CLOSED_DATE);
        assertThat(testBatch.getRound()).isEqualTo(DEFAULT_ROUND);
        assertThat(testBatch.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testBatch.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBatch.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBatch.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testBatch.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBatch.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createBatchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = batchRepository.findAll().size();

        // Create the Batch with an existing ID
        batch.setId(1L);
        BatchDTO batchDTO = batchMapper.toDto(batch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBatchNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setBatchNo(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBatchNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setBatchName(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setQuantity(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShowingTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setShowingType(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSowingDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setSowingDate(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBatches() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList
        restBatchMockMvc.perform(get("/api/batches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batch.getId().intValue())))
            .andExpect(jsonPath("$.[*].batchNo").value(hasItem(DEFAULT_BATCH_NO.toString())))
            .andExpect(jsonPath("$.[*].batchName").value(hasItem(DEFAULT_BATCH_NAME.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].motherBed").value(hasItem(DEFAULT_MOTHER_BED.toString())))
            .andExpect(jsonPath("$.[*].showingType").value(hasItem(DEFAULT_SHOWING_TYPE)))
            .andExpect(jsonPath("$.[*].sowingDate").value(hasItem(DEFAULT_SOWING_DATE.toString())))
            .andExpect(jsonPath("$.[*].closedDate").value(hasItem(DEFAULT_CLOSED_DATE.toString())))
            .andExpect(jsonPath("$.[*].round").value(hasItem(DEFAULT_ROUND)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    

    @Test
    @Transactional
    public void getBatch() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get the batch
        restBatchMockMvc.perform(get("/api/batches/{id}", batch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(batch.getId().intValue()))
            .andExpect(jsonPath("$.batchNo").value(DEFAULT_BATCH_NO.toString()))
            .andExpect(jsonPath("$.batchName").value(DEFAULT_BATCH_NAME.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.motherBed").value(DEFAULT_MOTHER_BED.toString()))
            .andExpect(jsonPath("$.showingType").value(DEFAULT_SHOWING_TYPE))
            .andExpect(jsonPath("$.sowingDate").value(DEFAULT_SOWING_DATE.toString()))
            .andExpect(jsonPath("$.closedDate").value(DEFAULT_CLOSED_DATE.toString()))
            .andExpect(jsonPath("$.round").value(DEFAULT_ROUND))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBatch() throws Exception {
        // Get the batch
        restBatchMockMvc.perform(get("/api/batches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBatch() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        int databaseSizeBeforeUpdate = batchRepository.findAll().size();

        // Update the batch
        Batch updatedBatch = batchRepository.findById(batch.getId()).get();
        // Disconnect from session so that the updates on updatedBatch are not directly saved in db
        em.detach(updatedBatch);
        updatedBatch
            .batchNo(UPDATED_BATCH_NO)
            .batchName(UPDATED_BATCH_NAME)
            .quantity(UPDATED_QUANTITY)
            .motherBed(UPDATED_MOTHER_BED)
            .showingType(UPDATED_SHOWING_TYPE)
            .sowingDate(UPDATED_SOWING_DATE)
            .closedDate(UPDATED_CLOSED_DATE)
            .round(UPDATED_ROUND)
            .remarks(UPDATED_REMARKS)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        BatchDTO batchDTO = batchMapper.toDto(updatedBatch);

        restBatchMockMvc.perform(put("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isOk());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeUpdate);
        Batch testBatch = batchList.get(batchList.size() - 1);
        assertThat(testBatch.getBatchNo()).isEqualTo(UPDATED_BATCH_NO);
        assertThat(testBatch.getBatchName()).isEqualTo(UPDATED_BATCH_NAME);
        assertThat(testBatch.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testBatch.getMotherBed()).isEqualTo(UPDATED_MOTHER_BED);
        assertThat(testBatch.getShowingType()).isEqualTo(UPDATED_SHOWING_TYPE);
        assertThat(testBatch.getSowingDate()).isEqualTo(UPDATED_SOWING_DATE);
        assertThat(testBatch.getClosedDate()).isEqualTo(UPDATED_CLOSED_DATE);
        assertThat(testBatch.getRound()).isEqualTo(UPDATED_ROUND);
        assertThat(testBatch.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testBatch.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBatch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBatch.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testBatch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBatch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingBatch() throws Exception {
        int databaseSizeBeforeUpdate = batchRepository.findAll().size();

        // Create the Batch
        BatchDTO batchDTO = batchMapper.toDto(batch);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBatchMockMvc.perform(put("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBatch() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        int databaseSizeBeforeDelete = batchRepository.findAll().size();

        // Get the batch
        restBatchMockMvc.perform(delete("/api/batches/{id}", batch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Batch.class);
        Batch batch1 = new Batch();
        batch1.setId(1L);
        Batch batch2 = new Batch();
        batch2.setId(batch1.getId());
        assertThat(batch1).isEqualTo(batch2);
        batch2.setId(2L);
        assertThat(batch1).isNotEqualTo(batch2);
        batch1.setId(null);
        assertThat(batch1).isNotEqualTo(batch2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BatchDTO.class);
        BatchDTO batchDTO1 = new BatchDTO();
        batchDTO1.setId(1L);
        BatchDTO batchDTO2 = new BatchDTO();
        assertThat(batchDTO1).isNotEqualTo(batchDTO2);
        batchDTO2.setId(batchDTO1.getId());
        assertThat(batchDTO1).isEqualTo(batchDTO2);
        batchDTO2.setId(2L);
        assertThat(batchDTO1).isNotEqualTo(batchDTO2);
        batchDTO1.setId(null);
        assertThat(batchDTO1).isNotEqualTo(batchDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(batchMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(batchMapper.fromId(null)).isNull();
    }
}
