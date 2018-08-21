package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.GodownPurchaseDetails;
import com.niche.ng.repository.GodownPurchaseDetailsRepository;
import com.niche.ng.service.GodownPurchaseDetailsService;
import com.niche.ng.service.dto.GodownPurchaseDetailsDTO;
import com.niche.ng.service.mapper.GodownPurchaseDetailsMapper;
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
 * Test class for the GodownPurchaseDetailsResource REST controller.
 *
 * @see GodownPurchaseDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class GodownPurchaseDetailsResourceIntTest {

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_PRICE = 1L;
    private static final Long UPDATED_PRICE = 2L;

    private static final String DEFAULT_OWNED_BY = "AAAAAAAAAA";
    private static final String UPDATED_OWNED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_VENDOR_PHONE = 1L;
    private static final Long UPDATED_VENDOR_PHONE = 2L;

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
    private GodownPurchaseDetailsRepository godownPurchaseDetailsRepository;


    @Autowired
    private GodownPurchaseDetailsMapper godownPurchaseDetailsMapper;
    

    @Autowired
    private GodownPurchaseDetailsService godownPurchaseDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGodownPurchaseDetailsMockMvc;

    private GodownPurchaseDetails godownPurchaseDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GodownPurchaseDetailsResource godownPurchaseDetailsResource = new GodownPurchaseDetailsResource(godownPurchaseDetailsService);
        this.restGodownPurchaseDetailsMockMvc = MockMvcBuilders.standaloneSetup(godownPurchaseDetailsResource)
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
    public static GodownPurchaseDetails createEntity(EntityManager em) {
        GodownPurchaseDetails godownPurchaseDetails = new GodownPurchaseDetails()
            .quantity(DEFAULT_QUANTITY)
            .date(DEFAULT_DATE)
            .price(DEFAULT_PRICE)
            .ownedBy(DEFAULT_OWNED_BY)
            .vendorName(DEFAULT_VENDOR_NAME)
            .vendorAddress(DEFAULT_VENDOR_ADDRESS)
            .vendorPhone(DEFAULT_VENDOR_PHONE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return godownPurchaseDetails;
    }

    @Before
    public void initTest() {
        godownPurchaseDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createGodownPurchaseDetails() throws Exception {
        int databaseSizeBeforeCreate = godownPurchaseDetailsRepository.findAll().size();

        // Create the GodownPurchaseDetails
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);
        restGodownPurchaseDetailsMockMvc.perform(post("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the GodownPurchaseDetails in the database
        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        GodownPurchaseDetails testGodownPurchaseDetails = godownPurchaseDetailsList.get(godownPurchaseDetailsList.size() - 1);
        assertThat(testGodownPurchaseDetails.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testGodownPurchaseDetails.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testGodownPurchaseDetails.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testGodownPurchaseDetails.getOwnedBy()).isEqualTo(DEFAULT_OWNED_BY);
        assertThat(testGodownPurchaseDetails.getVendorName()).isEqualTo(DEFAULT_VENDOR_NAME);
        assertThat(testGodownPurchaseDetails.getVendorAddress()).isEqualTo(DEFAULT_VENDOR_ADDRESS);
        assertThat(testGodownPurchaseDetails.getVendorPhone()).isEqualTo(DEFAULT_VENDOR_PHONE);
        assertThat(testGodownPurchaseDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGodownPurchaseDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGodownPurchaseDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGodownPurchaseDetails.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testGodownPurchaseDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGodownPurchaseDetails.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createGodownPurchaseDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = godownPurchaseDetailsRepository.findAll().size();

        // Create the GodownPurchaseDetails with an existing ID
        godownPurchaseDetails.setId(1L);
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGodownPurchaseDetailsMockMvc.perform(post("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownPurchaseDetails in the database
        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = godownPurchaseDetailsRepository.findAll().size();
        // set the field null
        godownPurchaseDetails.setQuantity(null);

        // Create the GodownPurchaseDetails, which fails.
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);

        restGodownPurchaseDetailsMockMvc.perform(post("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = godownPurchaseDetailsRepository.findAll().size();
        // set the field null
        godownPurchaseDetails.setDate(null);

        // Create the GodownPurchaseDetails, which fails.
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);

        restGodownPurchaseDetailsMockMvc.perform(post("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetails() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList
        restGodownPurchaseDetailsMockMvc.perform(get("/api/godown-purchase-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godownPurchaseDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].ownedBy").value(hasItem(DEFAULT_OWNED_BY.toString())))
            .andExpect(jsonPath("$.[*].vendorName").value(hasItem(DEFAULT_VENDOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].vendorAddress").value(hasItem(DEFAULT_VENDOR_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].vendorPhone").value(hasItem(DEFAULT_VENDOR_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    

    @Test
    @Transactional
    public void getGodownPurchaseDetails() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get the godownPurchaseDetails
        restGodownPurchaseDetailsMockMvc.perform(get("/api/godown-purchase-details/{id}", godownPurchaseDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(godownPurchaseDetails.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.ownedBy").value(DEFAULT_OWNED_BY.toString()))
            .andExpect(jsonPath("$.vendorName").value(DEFAULT_VENDOR_NAME.toString()))
            .andExpect(jsonPath("$.vendorAddress").value(DEFAULT_VENDOR_ADDRESS.toString()))
            .andExpect(jsonPath("$.vendorPhone").value(DEFAULT_VENDOR_PHONE.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingGodownPurchaseDetails() throws Exception {
        // Get the godownPurchaseDetails
        restGodownPurchaseDetailsMockMvc.perform(get("/api/godown-purchase-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGodownPurchaseDetails() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        int databaseSizeBeforeUpdate = godownPurchaseDetailsRepository.findAll().size();

        // Update the godownPurchaseDetails
        GodownPurchaseDetails updatedGodownPurchaseDetails = godownPurchaseDetailsRepository.findById(godownPurchaseDetails.getId()).get();
        // Disconnect from session so that the updates on updatedGodownPurchaseDetails are not directly saved in db
        em.detach(updatedGodownPurchaseDetails);
        updatedGodownPurchaseDetails
            .quantity(UPDATED_QUANTITY)
            .date(UPDATED_DATE)
            .price(UPDATED_PRICE)
            .ownedBy(UPDATED_OWNED_BY)
            .vendorName(UPDATED_VENDOR_NAME)
            .vendorAddress(UPDATED_VENDOR_ADDRESS)
            .vendorPhone(UPDATED_VENDOR_PHONE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(updatedGodownPurchaseDetails);

        restGodownPurchaseDetailsMockMvc.perform(put("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the GodownPurchaseDetails in the database
        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeUpdate);
        GodownPurchaseDetails testGodownPurchaseDetails = godownPurchaseDetailsList.get(godownPurchaseDetailsList.size() - 1);
        assertThat(testGodownPurchaseDetails.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testGodownPurchaseDetails.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testGodownPurchaseDetails.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testGodownPurchaseDetails.getOwnedBy()).isEqualTo(UPDATED_OWNED_BY);
        assertThat(testGodownPurchaseDetails.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testGodownPurchaseDetails.getVendorAddress()).isEqualTo(UPDATED_VENDOR_ADDRESS);
        assertThat(testGodownPurchaseDetails.getVendorPhone()).isEqualTo(UPDATED_VENDOR_PHONE);
        assertThat(testGodownPurchaseDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGodownPurchaseDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGodownPurchaseDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGodownPurchaseDetails.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testGodownPurchaseDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGodownPurchaseDetails.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingGodownPurchaseDetails() throws Exception {
        int databaseSizeBeforeUpdate = godownPurchaseDetailsRepository.findAll().size();

        // Create the GodownPurchaseDetails
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGodownPurchaseDetailsMockMvc.perform(put("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownPurchaseDetails in the database
        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGodownPurchaseDetails() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        int databaseSizeBeforeDelete = godownPurchaseDetailsRepository.findAll().size();

        // Get the godownPurchaseDetails
        restGodownPurchaseDetailsMockMvc.perform(delete("/api/godown-purchase-details/{id}", godownPurchaseDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownPurchaseDetails.class);
        GodownPurchaseDetails godownPurchaseDetails1 = new GodownPurchaseDetails();
        godownPurchaseDetails1.setId(1L);
        GodownPurchaseDetails godownPurchaseDetails2 = new GodownPurchaseDetails();
        godownPurchaseDetails2.setId(godownPurchaseDetails1.getId());
        assertThat(godownPurchaseDetails1).isEqualTo(godownPurchaseDetails2);
        godownPurchaseDetails2.setId(2L);
        assertThat(godownPurchaseDetails1).isNotEqualTo(godownPurchaseDetails2);
        godownPurchaseDetails1.setId(null);
        assertThat(godownPurchaseDetails1).isNotEqualTo(godownPurchaseDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownPurchaseDetailsDTO.class);
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO1 = new GodownPurchaseDetailsDTO();
        godownPurchaseDetailsDTO1.setId(1L);
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO2 = new GodownPurchaseDetailsDTO();
        assertThat(godownPurchaseDetailsDTO1).isNotEqualTo(godownPurchaseDetailsDTO2);
        godownPurchaseDetailsDTO2.setId(godownPurchaseDetailsDTO1.getId());
        assertThat(godownPurchaseDetailsDTO1).isEqualTo(godownPurchaseDetailsDTO2);
        godownPurchaseDetailsDTO2.setId(2L);
        assertThat(godownPurchaseDetailsDTO1).isNotEqualTo(godownPurchaseDetailsDTO2);
        godownPurchaseDetailsDTO1.setId(null);
        assertThat(godownPurchaseDetailsDTO1).isNotEqualTo(godownPurchaseDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(godownPurchaseDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(godownPurchaseDetailsMapper.fromId(null)).isNull();
    }
}
