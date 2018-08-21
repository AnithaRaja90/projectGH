package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.NurseryStockDetails;
import com.niche.ng.repository.NurseryStockDetailsRepository;
import com.niche.ng.service.NurseryStockDetailsService;
import com.niche.ng.service.dto.NurseryStockDetailsDTO;
import com.niche.ng.service.mapper.NurseryStockDetailsMapper;
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
 * Test class for the NurseryStockDetailsResource REST controller.
 *
 * @see NurseryStockDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class NurseryStockDetailsResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

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
    private NurseryStockDetailsRepository nurseryStockDetailsRepository;


    @Autowired
    private NurseryStockDetailsMapper nurseryStockDetailsMapper;
    

    @Autowired
    private NurseryStockDetailsService nurseryStockDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNurseryStockDetailsMockMvc;

    private NurseryStockDetails nurseryStockDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NurseryStockDetailsResource nurseryStockDetailsResource = new NurseryStockDetailsResource(nurseryStockDetailsService);
        this.restNurseryStockDetailsMockMvc = MockMvcBuilders.standaloneSetup(nurseryStockDetailsResource)
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
    public static NurseryStockDetails createEntity(EntityManager em) {
        NurseryStockDetails nurseryStockDetails = new NurseryStockDetails()
            .date(DEFAULT_DATE)
            .quantity(DEFAULT_QUANTITY)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return nurseryStockDetails;
    }

    @Before
    public void initTest() {
        nurseryStockDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createNurseryStockDetails() throws Exception {
        int databaseSizeBeforeCreate = nurseryStockDetailsRepository.findAll().size();

        // Create the NurseryStockDetails
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(nurseryStockDetails);
        restNurseryStockDetailsMockMvc.perform(post("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the NurseryStockDetails in the database
        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        NurseryStockDetails testNurseryStockDetails = nurseryStockDetailsList.get(nurseryStockDetailsList.size() - 1);
        assertThat(testNurseryStockDetails.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testNurseryStockDetails.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testNurseryStockDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNurseryStockDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNurseryStockDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testNurseryStockDetails.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testNurseryStockDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testNurseryStockDetails.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createNurseryStockDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nurseryStockDetailsRepository.findAll().size();

        // Create the NurseryStockDetails with an existing ID
        nurseryStockDetails.setId(1L);
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(nurseryStockDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNurseryStockDetailsMockMvc.perform(post("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryStockDetails in the database
        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryStockDetailsRepository.findAll().size();
        // set the field null
        nurseryStockDetails.setDate(null);

        // Create the NurseryStockDetails, which fails.
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(nurseryStockDetails);

        restNurseryStockDetailsMockMvc.perform(post("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryStockDetailsRepository.findAll().size();
        // set the field null
        nurseryStockDetails.setQuantity(null);

        // Create the NurseryStockDetails, which fails.
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(nurseryStockDetails);

        restNurseryStockDetailsMockMvc.perform(post("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetails() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList
        restNurseryStockDetailsMockMvc.perform(get("/api/nursery-stock-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryStockDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    

    @Test
    @Transactional
    public void getNurseryStockDetails() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get the nurseryStockDetails
        restNurseryStockDetailsMockMvc.perform(get("/api/nursery-stock-details/{id}", nurseryStockDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nurseryStockDetails.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingNurseryStockDetails() throws Exception {
        // Get the nurseryStockDetails
        restNurseryStockDetailsMockMvc.perform(get("/api/nursery-stock-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNurseryStockDetails() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        int databaseSizeBeforeUpdate = nurseryStockDetailsRepository.findAll().size();

        // Update the nurseryStockDetails
        NurseryStockDetails updatedNurseryStockDetails = nurseryStockDetailsRepository.findById(nurseryStockDetails.getId()).get();
        // Disconnect from session so that the updates on updatedNurseryStockDetails are not directly saved in db
        em.detach(updatedNurseryStockDetails);
        updatedNurseryStockDetails
            .date(UPDATED_DATE)
            .quantity(UPDATED_QUANTITY)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(updatedNurseryStockDetails);

        restNurseryStockDetailsMockMvc.perform(put("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the NurseryStockDetails in the database
        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeUpdate);
        NurseryStockDetails testNurseryStockDetails = nurseryStockDetailsList.get(nurseryStockDetailsList.size() - 1);
        assertThat(testNurseryStockDetails.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testNurseryStockDetails.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testNurseryStockDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNurseryStockDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNurseryStockDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testNurseryStockDetails.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testNurseryStockDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testNurseryStockDetails.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingNurseryStockDetails() throws Exception {
        int databaseSizeBeforeUpdate = nurseryStockDetailsRepository.findAll().size();

        // Create the NurseryStockDetails
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(nurseryStockDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNurseryStockDetailsMockMvc.perform(put("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryStockDetails in the database
        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNurseryStockDetails() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        int databaseSizeBeforeDelete = nurseryStockDetailsRepository.findAll().size();

        // Get the nurseryStockDetails
        restNurseryStockDetailsMockMvc.perform(delete("/api/nursery-stock-details/{id}", nurseryStockDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryStockDetails.class);
        NurseryStockDetails nurseryStockDetails1 = new NurseryStockDetails();
        nurseryStockDetails1.setId(1L);
        NurseryStockDetails nurseryStockDetails2 = new NurseryStockDetails();
        nurseryStockDetails2.setId(nurseryStockDetails1.getId());
        assertThat(nurseryStockDetails1).isEqualTo(nurseryStockDetails2);
        nurseryStockDetails2.setId(2L);
        assertThat(nurseryStockDetails1).isNotEqualTo(nurseryStockDetails2);
        nurseryStockDetails1.setId(null);
        assertThat(nurseryStockDetails1).isNotEqualTo(nurseryStockDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryStockDetailsDTO.class);
        NurseryStockDetailsDTO nurseryStockDetailsDTO1 = new NurseryStockDetailsDTO();
        nurseryStockDetailsDTO1.setId(1L);
        NurseryStockDetailsDTO nurseryStockDetailsDTO2 = new NurseryStockDetailsDTO();
        assertThat(nurseryStockDetailsDTO1).isNotEqualTo(nurseryStockDetailsDTO2);
        nurseryStockDetailsDTO2.setId(nurseryStockDetailsDTO1.getId());
        assertThat(nurseryStockDetailsDTO1).isEqualTo(nurseryStockDetailsDTO2);
        nurseryStockDetailsDTO2.setId(2L);
        assertThat(nurseryStockDetailsDTO1).isNotEqualTo(nurseryStockDetailsDTO2);
        nurseryStockDetailsDTO1.setId(null);
        assertThat(nurseryStockDetailsDTO1).isNotEqualTo(nurseryStockDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nurseryStockDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nurseryStockDetailsMapper.fromId(null)).isNull();
    }
}
