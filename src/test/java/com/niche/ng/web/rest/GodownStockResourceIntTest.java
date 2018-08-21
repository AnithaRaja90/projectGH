package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.GodownStock;
import com.niche.ng.repository.GodownStockRepository;
import com.niche.ng.service.GodownStockService;
import com.niche.ng.service.dto.GodownStockDTO;
import com.niche.ng.service.mapper.GodownStockMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.niche.ng.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GodownStockResource REST controller.
 *
 * @see GodownStockResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class GodownStockResourceIntTest {

    private static final Long DEFAULT_CURRENT_QUANTITY = 1L;
    private static final Long UPDATED_CURRENT_QUANTITY = 2L;

    private static final Long DEFAULT_ADDED_QUANTITY = 1L;
    private static final Long UPDATED_ADDED_QUANTITY = 2L;

    private static final Long DEFAULT_CONSUMED_QUANTITY = 1L;
    private static final Long UPDATED_CONSUMED_QUANTITY = 2L;

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
    private GodownStockRepository godownStockRepository;


    @Autowired
    private GodownStockMapper godownStockMapper;
    

    @Autowired
    private GodownStockService godownStockService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGodownStockMockMvc;

    private GodownStock godownStock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GodownStockResource godownStockResource = new GodownStockResource(godownStockService);
        this.restGodownStockMockMvc = MockMvcBuilders.standaloneSetup(godownStockResource)
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
    public static GodownStock createEntity(EntityManager em) {
        GodownStock godownStock = new GodownStock()
            .currentQuantity(DEFAULT_CURRENT_QUANTITY)
            .addedQuantity(DEFAULT_ADDED_QUANTITY)
            .consumedQuantity(DEFAULT_CONSUMED_QUANTITY)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return godownStock;
    }

    @Before
    public void initTest() {
        godownStock = createEntity(em);
    }

    @Test
    @Transactional
    public void createGodownStock() throws Exception {
        int databaseSizeBeforeCreate = godownStockRepository.findAll().size();

        // Create the GodownStock
        GodownStockDTO godownStockDTO = godownStockMapper.toDto(godownStock);
        restGodownStockMockMvc.perform(post("/api/godown-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDTO)))
            .andExpect(status().isCreated());

        // Validate the GodownStock in the database
        List<GodownStock> godownStockList = godownStockRepository.findAll();
        assertThat(godownStockList).hasSize(databaseSizeBeforeCreate + 1);
        GodownStock testGodownStock = godownStockList.get(godownStockList.size() - 1);
        assertThat(testGodownStock.getCurrentQuantity()).isEqualTo(DEFAULT_CURRENT_QUANTITY);
        assertThat(testGodownStock.getAddedQuantity()).isEqualTo(DEFAULT_ADDED_QUANTITY);
        assertThat(testGodownStock.getConsumedQuantity()).isEqualTo(DEFAULT_CONSUMED_QUANTITY);
        assertThat(testGodownStock.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGodownStock.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGodownStock.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGodownStock.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testGodownStock.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGodownStock.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createGodownStockWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = godownStockRepository.findAll().size();

        // Create the GodownStock with an existing ID
        godownStock.setId(1L);
        GodownStockDTO godownStockDTO = godownStockMapper.toDto(godownStock);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGodownStockMockMvc.perform(post("/api/godown-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownStock in the database
        List<GodownStock> godownStockList = godownStockRepository.findAll();
        assertThat(godownStockList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGodownStocks() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList
        restGodownStockMockMvc.perform(get("/api/godown-stocks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godownStock.getId().intValue())))
            .andExpect(jsonPath("$.[*].currentQuantity").value(hasItem(DEFAULT_CURRENT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].addedQuantity").value(hasItem(DEFAULT_ADDED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].consumedQuantity").value(hasItem(DEFAULT_CONSUMED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    

    @Test
    @Transactional
    public void getGodownStock() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get the godownStock
        restGodownStockMockMvc.perform(get("/api/godown-stocks/{id}", godownStock.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(godownStock.getId().intValue()))
            .andExpect(jsonPath("$.currentQuantity").value(DEFAULT_CURRENT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.addedQuantity").value(DEFAULT_ADDED_QUANTITY.intValue()))
            .andExpect(jsonPath("$.consumedQuantity").value(DEFAULT_CONSUMED_QUANTITY.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingGodownStock() throws Exception {
        // Get the godownStock
        restGodownStockMockMvc.perform(get("/api/godown-stocks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGodownStock() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        int databaseSizeBeforeUpdate = godownStockRepository.findAll().size();

        // Update the godownStock
        GodownStock updatedGodownStock = godownStockRepository.findById(godownStock.getId()).get();
        // Disconnect from session so that the updates on updatedGodownStock are not directly saved in db
        em.detach(updatedGodownStock);
        updatedGodownStock
            .currentQuantity(UPDATED_CURRENT_QUANTITY)
            .addedQuantity(UPDATED_ADDED_QUANTITY)
            .consumedQuantity(UPDATED_CONSUMED_QUANTITY)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        GodownStockDTO godownStockDTO = godownStockMapper.toDto(updatedGodownStock);

        restGodownStockMockMvc.perform(put("/api/godown-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDTO)))
            .andExpect(status().isOk());

        // Validate the GodownStock in the database
        List<GodownStock> godownStockList = godownStockRepository.findAll();
        assertThat(godownStockList).hasSize(databaseSizeBeforeUpdate);
        GodownStock testGodownStock = godownStockList.get(godownStockList.size() - 1);
        assertThat(testGodownStock.getCurrentQuantity()).isEqualTo(UPDATED_CURRENT_QUANTITY);
        assertThat(testGodownStock.getAddedQuantity()).isEqualTo(UPDATED_ADDED_QUANTITY);
        assertThat(testGodownStock.getConsumedQuantity()).isEqualTo(UPDATED_CONSUMED_QUANTITY);
        assertThat(testGodownStock.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGodownStock.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGodownStock.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGodownStock.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testGodownStock.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGodownStock.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingGodownStock() throws Exception {
        int databaseSizeBeforeUpdate = godownStockRepository.findAll().size();

        // Create the GodownStock
        GodownStockDTO godownStockDTO = godownStockMapper.toDto(godownStock);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGodownStockMockMvc.perform(put("/api/godown-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownStock in the database
        List<GodownStock> godownStockList = godownStockRepository.findAll();
        assertThat(godownStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGodownStock() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        int databaseSizeBeforeDelete = godownStockRepository.findAll().size();

        // Get the godownStock
        restGodownStockMockMvc.perform(delete("/api/godown-stocks/{id}", godownStock.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GodownStock> godownStockList = godownStockRepository.findAll();
        assertThat(godownStockList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownStock.class);
        GodownStock godownStock1 = new GodownStock();
        godownStock1.setId(1L);
        GodownStock godownStock2 = new GodownStock();
        godownStock2.setId(godownStock1.getId());
        assertThat(godownStock1).isEqualTo(godownStock2);
        godownStock2.setId(2L);
        assertThat(godownStock1).isNotEqualTo(godownStock2);
        godownStock1.setId(null);
        assertThat(godownStock1).isNotEqualTo(godownStock2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownStockDTO.class);
        GodownStockDTO godownStockDTO1 = new GodownStockDTO();
        godownStockDTO1.setId(1L);
        GodownStockDTO godownStockDTO2 = new GodownStockDTO();
        assertThat(godownStockDTO1).isNotEqualTo(godownStockDTO2);
        godownStockDTO2.setId(godownStockDTO1.getId());
        assertThat(godownStockDTO1).isEqualTo(godownStockDTO2);
        godownStockDTO2.setId(2L);
        assertThat(godownStockDTO1).isNotEqualTo(godownStockDTO2);
        godownStockDTO1.setId(null);
        assertThat(godownStockDTO1).isNotEqualTo(godownStockDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(godownStockMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(godownStockMapper.fromId(null)).isNull();
    }
}
