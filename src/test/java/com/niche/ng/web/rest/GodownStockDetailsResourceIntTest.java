package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.GodownStockDetails;
import com.niche.ng.repository.GodownStockDetailsRepository;
import com.niche.ng.service.GodownStockDetailsService;
import com.niche.ng.service.dto.GodownStockDetailsDTO;
import com.niche.ng.service.mapper.GodownStockDetailsMapper;
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
 * Test class for the GodownStockDetailsResource REST controller.
 *
 * @see GodownStockDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class GodownStockDetailsResourceIntTest {

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
    private GodownStockDetailsRepository godownStockDetailsRepository;


    @Autowired
    private GodownStockDetailsMapper godownStockDetailsMapper;
    

    @Autowired
    private GodownStockDetailsService godownStockDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGodownStockDetailsMockMvc;

    private GodownStockDetails godownStockDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GodownStockDetailsResource godownStockDetailsResource = new GodownStockDetailsResource(godownStockDetailsService);
        this.restGodownStockDetailsMockMvc = MockMvcBuilders.standaloneSetup(godownStockDetailsResource)
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
    public static GodownStockDetails createEntity(EntityManager em) {
        GodownStockDetails godownStockDetails = new GodownStockDetails()
            .date(DEFAULT_DATE)
            .quantity(DEFAULT_QUANTITY)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return godownStockDetails;
    }

    @Before
    public void initTest() {
        godownStockDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createGodownStockDetails() throws Exception {
        int databaseSizeBeforeCreate = godownStockDetailsRepository.findAll().size();

        // Create the GodownStockDetails
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(godownStockDetails);
        restGodownStockDetailsMockMvc.perform(post("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the GodownStockDetails in the database
        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        GodownStockDetails testGodownStockDetails = godownStockDetailsList.get(godownStockDetailsList.size() - 1);
        assertThat(testGodownStockDetails.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testGodownStockDetails.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testGodownStockDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGodownStockDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGodownStockDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGodownStockDetails.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testGodownStockDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGodownStockDetails.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createGodownStockDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = godownStockDetailsRepository.findAll().size();

        // Create the GodownStockDetails with an existing ID
        godownStockDetails.setId(1L);
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(godownStockDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGodownStockDetailsMockMvc.perform(post("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownStockDetails in the database
        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = godownStockDetailsRepository.findAll().size();
        // set the field null
        godownStockDetails.setDate(null);

        // Create the GodownStockDetails, which fails.
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(godownStockDetails);

        restGodownStockDetailsMockMvc.perform(post("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = godownStockDetailsRepository.findAll().size();
        // set the field null
        godownStockDetails.setQuantity(null);

        // Create the GodownStockDetails, which fails.
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(godownStockDetails);

        restGodownStockDetailsMockMvc.perform(post("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetails() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList
        restGodownStockDetailsMockMvc.perform(get("/api/godown-stock-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godownStockDetails.getId().intValue())))
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
    public void getGodownStockDetails() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get the godownStockDetails
        restGodownStockDetailsMockMvc.perform(get("/api/godown-stock-details/{id}", godownStockDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(godownStockDetails.getId().intValue()))
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
    public void getNonExistingGodownStockDetails() throws Exception {
        // Get the godownStockDetails
        restGodownStockDetailsMockMvc.perform(get("/api/godown-stock-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGodownStockDetails() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        int databaseSizeBeforeUpdate = godownStockDetailsRepository.findAll().size();

        // Update the godownStockDetails
        GodownStockDetails updatedGodownStockDetails = godownStockDetailsRepository.findById(godownStockDetails.getId()).get();
        // Disconnect from session so that the updates on updatedGodownStockDetails are not directly saved in db
        em.detach(updatedGodownStockDetails);
        updatedGodownStockDetails
            .date(UPDATED_DATE)
            .quantity(UPDATED_QUANTITY)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(updatedGodownStockDetails);

        restGodownStockDetailsMockMvc.perform(put("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the GodownStockDetails in the database
        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeUpdate);
        GodownStockDetails testGodownStockDetails = godownStockDetailsList.get(godownStockDetailsList.size() - 1);
        assertThat(testGodownStockDetails.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testGodownStockDetails.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testGodownStockDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGodownStockDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGodownStockDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGodownStockDetails.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testGodownStockDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGodownStockDetails.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingGodownStockDetails() throws Exception {
        int databaseSizeBeforeUpdate = godownStockDetailsRepository.findAll().size();

        // Create the GodownStockDetails
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(godownStockDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGodownStockDetailsMockMvc.perform(put("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownStockDetails in the database
        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGodownStockDetails() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        int databaseSizeBeforeDelete = godownStockDetailsRepository.findAll().size();

        // Get the godownStockDetails
        restGodownStockDetailsMockMvc.perform(delete("/api/godown-stock-details/{id}", godownStockDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownStockDetails.class);
        GodownStockDetails godownStockDetails1 = new GodownStockDetails();
        godownStockDetails1.setId(1L);
        GodownStockDetails godownStockDetails2 = new GodownStockDetails();
        godownStockDetails2.setId(godownStockDetails1.getId());
        assertThat(godownStockDetails1).isEqualTo(godownStockDetails2);
        godownStockDetails2.setId(2L);
        assertThat(godownStockDetails1).isNotEqualTo(godownStockDetails2);
        godownStockDetails1.setId(null);
        assertThat(godownStockDetails1).isNotEqualTo(godownStockDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownStockDetailsDTO.class);
        GodownStockDetailsDTO godownStockDetailsDTO1 = new GodownStockDetailsDTO();
        godownStockDetailsDTO1.setId(1L);
        GodownStockDetailsDTO godownStockDetailsDTO2 = new GodownStockDetailsDTO();
        assertThat(godownStockDetailsDTO1).isNotEqualTo(godownStockDetailsDTO2);
        godownStockDetailsDTO2.setId(godownStockDetailsDTO1.getId());
        assertThat(godownStockDetailsDTO1).isEqualTo(godownStockDetailsDTO2);
        godownStockDetailsDTO2.setId(2L);
        assertThat(godownStockDetailsDTO1).isNotEqualTo(godownStockDetailsDTO2);
        godownStockDetailsDTO1.setId(null);
        assertThat(godownStockDetailsDTO1).isNotEqualTo(godownStockDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(godownStockDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(godownStockDetailsMapper.fromId(null)).isNull();
    }
}
