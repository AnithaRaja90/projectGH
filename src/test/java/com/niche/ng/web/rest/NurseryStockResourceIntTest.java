package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.NurseryStock;
import com.niche.ng.repository.NurseryStockRepository;
import com.niche.ng.service.NurseryStockService;
import com.niche.ng.service.dto.NurseryStockDTO;
import com.niche.ng.service.mapper.NurseryStockMapper;
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
 * Test class for the NurseryStockResource REST controller.
 *
 * @see NurseryStockResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class NurseryStockResourceIntTest {

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
    private NurseryStockRepository nurseryStockRepository;


    @Autowired
    private NurseryStockMapper nurseryStockMapper;
    

    @Autowired
    private NurseryStockService nurseryStockService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNurseryStockMockMvc;

    private NurseryStock nurseryStock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NurseryStockResource nurseryStockResource = new NurseryStockResource(nurseryStockService);
        this.restNurseryStockMockMvc = MockMvcBuilders.standaloneSetup(nurseryStockResource)
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
    public static NurseryStock createEntity(EntityManager em) {
        NurseryStock nurseryStock = new NurseryStock()
            .currentQuantity(DEFAULT_CURRENT_QUANTITY)
            .addedQuantity(DEFAULT_ADDED_QUANTITY)
            .consumedQuantity(DEFAULT_CONSUMED_QUANTITY)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return nurseryStock;
    }

    @Before
    public void initTest() {
        nurseryStock = createEntity(em);
    }

    @Test
    @Transactional
    public void createNurseryStock() throws Exception {
        int databaseSizeBeforeCreate = nurseryStockRepository.findAll().size();

        // Create the NurseryStock
        NurseryStockDTO nurseryStockDTO = nurseryStockMapper.toDto(nurseryStock);
        restNurseryStockMockMvc.perform(post("/api/nursery-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDTO)))
            .andExpect(status().isCreated());

        // Validate the NurseryStock in the database
        List<NurseryStock> nurseryStockList = nurseryStockRepository.findAll();
        assertThat(nurseryStockList).hasSize(databaseSizeBeforeCreate + 1);
        NurseryStock testNurseryStock = nurseryStockList.get(nurseryStockList.size() - 1);
        assertThat(testNurseryStock.getCurrentQuantity()).isEqualTo(DEFAULT_CURRENT_QUANTITY);
        assertThat(testNurseryStock.getAddedQuantity()).isEqualTo(DEFAULT_ADDED_QUANTITY);
        assertThat(testNurseryStock.getConsumedQuantity()).isEqualTo(DEFAULT_CONSUMED_QUANTITY);
        assertThat(testNurseryStock.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNurseryStock.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNurseryStock.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testNurseryStock.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testNurseryStock.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testNurseryStock.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createNurseryStockWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nurseryStockRepository.findAll().size();

        // Create the NurseryStock with an existing ID
        nurseryStock.setId(1L);
        NurseryStockDTO nurseryStockDTO = nurseryStockMapper.toDto(nurseryStock);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNurseryStockMockMvc.perform(post("/api/nursery-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryStock in the database
        List<NurseryStock> nurseryStockList = nurseryStockRepository.findAll();
        assertThat(nurseryStockList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNurseryStocks() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList
        restNurseryStockMockMvc.perform(get("/api/nursery-stocks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryStock.getId().intValue())))
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
    public void getNurseryStock() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get the nurseryStock
        restNurseryStockMockMvc.perform(get("/api/nursery-stocks/{id}", nurseryStock.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nurseryStock.getId().intValue()))
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
    public void getNonExistingNurseryStock() throws Exception {
        // Get the nurseryStock
        restNurseryStockMockMvc.perform(get("/api/nursery-stocks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNurseryStock() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        int databaseSizeBeforeUpdate = nurseryStockRepository.findAll().size();

        // Update the nurseryStock
        NurseryStock updatedNurseryStock = nurseryStockRepository.findById(nurseryStock.getId()).get();
        // Disconnect from session so that the updates on updatedNurseryStock are not directly saved in db
        em.detach(updatedNurseryStock);
        updatedNurseryStock
            .currentQuantity(UPDATED_CURRENT_QUANTITY)
            .addedQuantity(UPDATED_ADDED_QUANTITY)
            .consumedQuantity(UPDATED_CONSUMED_QUANTITY)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        NurseryStockDTO nurseryStockDTO = nurseryStockMapper.toDto(updatedNurseryStock);

        restNurseryStockMockMvc.perform(put("/api/nursery-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDTO)))
            .andExpect(status().isOk());

        // Validate the NurseryStock in the database
        List<NurseryStock> nurseryStockList = nurseryStockRepository.findAll();
        assertThat(nurseryStockList).hasSize(databaseSizeBeforeUpdate);
        NurseryStock testNurseryStock = nurseryStockList.get(nurseryStockList.size() - 1);
        assertThat(testNurseryStock.getCurrentQuantity()).isEqualTo(UPDATED_CURRENT_QUANTITY);
        assertThat(testNurseryStock.getAddedQuantity()).isEqualTo(UPDATED_ADDED_QUANTITY);
        assertThat(testNurseryStock.getConsumedQuantity()).isEqualTo(UPDATED_CONSUMED_QUANTITY);
        assertThat(testNurseryStock.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNurseryStock.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNurseryStock.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testNurseryStock.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testNurseryStock.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testNurseryStock.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingNurseryStock() throws Exception {
        int databaseSizeBeforeUpdate = nurseryStockRepository.findAll().size();

        // Create the NurseryStock
        NurseryStockDTO nurseryStockDTO = nurseryStockMapper.toDto(nurseryStock);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNurseryStockMockMvc.perform(put("/api/nursery-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryStock in the database
        List<NurseryStock> nurseryStockList = nurseryStockRepository.findAll();
        assertThat(nurseryStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNurseryStock() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        int databaseSizeBeforeDelete = nurseryStockRepository.findAll().size();

        // Get the nurseryStock
        restNurseryStockMockMvc.perform(delete("/api/nursery-stocks/{id}", nurseryStock.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NurseryStock> nurseryStockList = nurseryStockRepository.findAll();
        assertThat(nurseryStockList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryStock.class);
        NurseryStock nurseryStock1 = new NurseryStock();
        nurseryStock1.setId(1L);
        NurseryStock nurseryStock2 = new NurseryStock();
        nurseryStock2.setId(nurseryStock1.getId());
        assertThat(nurseryStock1).isEqualTo(nurseryStock2);
        nurseryStock2.setId(2L);
        assertThat(nurseryStock1).isNotEqualTo(nurseryStock2);
        nurseryStock1.setId(null);
        assertThat(nurseryStock1).isNotEqualTo(nurseryStock2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryStockDTO.class);
        NurseryStockDTO nurseryStockDTO1 = new NurseryStockDTO();
        nurseryStockDTO1.setId(1L);
        NurseryStockDTO nurseryStockDTO2 = new NurseryStockDTO();
        assertThat(nurseryStockDTO1).isNotEqualTo(nurseryStockDTO2);
        nurseryStockDTO2.setId(nurseryStockDTO1.getId());
        assertThat(nurseryStockDTO1).isEqualTo(nurseryStockDTO2);
        nurseryStockDTO2.setId(2L);
        assertThat(nurseryStockDTO1).isNotEqualTo(nurseryStockDTO2);
        nurseryStockDTO1.setId(null);
        assertThat(nurseryStockDTO1).isNotEqualTo(nurseryStockDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nurseryStockMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nurseryStockMapper.fromId(null)).isNull();
    }
}
