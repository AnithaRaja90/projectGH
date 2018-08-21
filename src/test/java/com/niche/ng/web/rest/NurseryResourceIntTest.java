package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.Nursery;
import com.niche.ng.repository.NurseryRepository;
import com.niche.ng.service.NurseryService;
import com.niche.ng.service.dto.NurseryDTO;
import com.niche.ng.service.mapper.NurseryMapper;
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
 * Test class for the NurseryResource REST controller.
 *
 * @see NurseryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class NurseryResourceIntTest {

    private static final String DEFAULT_NURSERY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NURSERY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NURSERY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_NURSERY_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_NURSERY_INCHARGE = "AAAAAAAAAA";
    private static final String UPDATED_NURSERY_INCHARGE = "BBBBBBBBBB";

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
    private NurseryRepository nurseryRepository;


    @Autowired
    private NurseryMapper nurseryMapper;
    

    @Autowired
    private NurseryService nurseryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNurseryMockMvc;

    private Nursery nursery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NurseryResource nurseryResource = new NurseryResource(nurseryService);
        this.restNurseryMockMvc = MockMvcBuilders.standaloneSetup(nurseryResource)
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
    public static Nursery createEntity(EntityManager em) {
        Nursery nursery = new Nursery()
            .nurseryName(DEFAULT_NURSERY_NAME)
            .nurseryAddress(DEFAULT_NURSERY_ADDRESS)
            .nurseryIncharge(DEFAULT_NURSERY_INCHARGE)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return nursery;
    }

    @Before
    public void initTest() {
        nursery = createEntity(em);
    }

    @Test
    @Transactional
    public void createNursery() throws Exception {
        int databaseSizeBeforeCreate = nurseryRepository.findAll().size();

        // Create the Nursery
        NurseryDTO nurseryDTO = nurseryMapper.toDto(nursery);
        restNurseryMockMvc.perform(post("/api/nurseries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryDTO)))
            .andExpect(status().isCreated());

        // Validate the Nursery in the database
        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeCreate + 1);
        Nursery testNursery = nurseryList.get(nurseryList.size() - 1);
        assertThat(testNursery.getNurseryName()).isEqualTo(DEFAULT_NURSERY_NAME);
        assertThat(testNursery.getNurseryAddress()).isEqualTo(DEFAULT_NURSERY_ADDRESS);
        assertThat(testNursery.getNurseryIncharge()).isEqualTo(DEFAULT_NURSERY_INCHARGE);
        assertThat(testNursery.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNursery.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testNursery.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testNursery.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testNursery.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createNurseryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nurseryRepository.findAll().size();

        // Create the Nursery with an existing ID
        nursery.setId(1L);
        NurseryDTO nurseryDTO = nurseryMapper.toDto(nursery);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNurseryMockMvc.perform(post("/api/nurseries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nursery in the database
        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNurseryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryRepository.findAll().size();
        // set the field null
        nursery.setNurseryName(null);

        // Create the Nursery, which fails.
        NurseryDTO nurseryDTO = nurseryMapper.toDto(nursery);

        restNurseryMockMvc.perform(post("/api/nurseries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryDTO)))
            .andExpect(status().isBadRequest());

        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNurseries() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList
        restNurseryMockMvc.perform(get("/api/nurseries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nursery.getId().intValue())))
            .andExpect(jsonPath("$.[*].nurseryName").value(hasItem(DEFAULT_NURSERY_NAME.toString())))
            .andExpect(jsonPath("$.[*].nurseryAddress").value(hasItem(DEFAULT_NURSERY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].nurseryIncharge").value(hasItem(DEFAULT_NURSERY_INCHARGE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    

    @Test
    @Transactional
    public void getNursery() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get the nursery
        restNurseryMockMvc.perform(get("/api/nurseries/{id}", nursery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nursery.getId().intValue()))
            .andExpect(jsonPath("$.nurseryName").value(DEFAULT_NURSERY_NAME.toString()))
            .andExpect(jsonPath("$.nurseryAddress").value(DEFAULT_NURSERY_ADDRESS.toString()))
            .andExpect(jsonPath("$.nurseryIncharge").value(DEFAULT_NURSERY_INCHARGE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingNursery() throws Exception {
        // Get the nursery
        restNurseryMockMvc.perform(get("/api/nurseries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNursery() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        int databaseSizeBeforeUpdate = nurseryRepository.findAll().size();

        // Update the nursery
        Nursery updatedNursery = nurseryRepository.findById(nursery.getId()).get();
        // Disconnect from session so that the updates on updatedNursery are not directly saved in db
        em.detach(updatedNursery);
        updatedNursery
            .nurseryName(UPDATED_NURSERY_NAME)
            .nurseryAddress(UPDATED_NURSERY_ADDRESS)
            .nurseryIncharge(UPDATED_NURSERY_INCHARGE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        NurseryDTO nurseryDTO = nurseryMapper.toDto(updatedNursery);

        restNurseryMockMvc.perform(put("/api/nurseries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryDTO)))
            .andExpect(status().isOk());

        // Validate the Nursery in the database
        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeUpdate);
        Nursery testNursery = nurseryList.get(nurseryList.size() - 1);
        assertThat(testNursery.getNurseryName()).isEqualTo(UPDATED_NURSERY_NAME);
        assertThat(testNursery.getNurseryAddress()).isEqualTo(UPDATED_NURSERY_ADDRESS);
        assertThat(testNursery.getNurseryIncharge()).isEqualTo(UPDATED_NURSERY_INCHARGE);
        assertThat(testNursery.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNursery.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testNursery.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testNursery.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testNursery.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingNursery() throws Exception {
        int databaseSizeBeforeUpdate = nurseryRepository.findAll().size();

        // Create the Nursery
        NurseryDTO nurseryDTO = nurseryMapper.toDto(nursery);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNurseryMockMvc.perform(put("/api/nurseries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nursery in the database
        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNursery() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        int databaseSizeBeforeDelete = nurseryRepository.findAll().size();

        // Get the nursery
        restNurseryMockMvc.perform(delete("/api/nurseries/{id}", nursery.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nursery.class);
        Nursery nursery1 = new Nursery();
        nursery1.setId(1L);
        Nursery nursery2 = new Nursery();
        nursery2.setId(nursery1.getId());
        assertThat(nursery1).isEqualTo(nursery2);
        nursery2.setId(2L);
        assertThat(nursery1).isNotEqualTo(nursery2);
        nursery1.setId(null);
        assertThat(nursery1).isNotEqualTo(nursery2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryDTO.class);
        NurseryDTO nurseryDTO1 = new NurseryDTO();
        nurseryDTO1.setId(1L);
        NurseryDTO nurseryDTO2 = new NurseryDTO();
        assertThat(nurseryDTO1).isNotEqualTo(nurseryDTO2);
        nurseryDTO2.setId(nurseryDTO1.getId());
        assertThat(nurseryDTO1).isEqualTo(nurseryDTO2);
        nurseryDTO2.setId(2L);
        assertThat(nurseryDTO1).isNotEqualTo(nurseryDTO2);
        nurseryDTO1.setId(null);
        assertThat(nurseryDTO1).isNotEqualTo(nurseryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nurseryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nurseryMapper.fromId(null)).isNull();
    }
}
