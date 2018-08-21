package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.MotherBed;
import com.niche.ng.domain.Nursery;
import com.niche.ng.repository.MotherBedRepository;
import com.niche.ng.service.MotherBedService;
import com.niche.ng.service.dto.MotherBedDTO;
import com.niche.ng.service.mapper.MotherBedMapper;
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
 * Test class for the MotherBedResource REST controller.
 *
 * @see MotherBedResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class MotherBedResourceIntTest {

    private static final Integer DEFAULT_VALUE = 1;
    private static final Integer UPDATED_VALUE = 2;

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
    private MotherBedRepository motherBedRepository;


    @Autowired
    private MotherBedMapper motherBedMapper;
    

    @Autowired
    private MotherBedService motherBedService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMotherBedMockMvc;

    private MotherBed motherBed;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MotherBedResource motherBedResource = new MotherBedResource(motherBedService);
        this.restMotherBedMockMvc = MockMvcBuilders.standaloneSetup(motherBedResource)
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
    public static MotherBed createEntity(EntityManager em) {
        MotherBed motherBed = new MotherBed()
            .value(DEFAULT_VALUE)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        // Add required entity
        Nursery nursery = NurseryResourceIntTest.createEntity(em);
        em.persist(nursery);
        em.flush();
        motherBed.setNursery(nursery);
        return motherBed;
    }

    @Before
    public void initTest() {
        motherBed = createEntity(em);
    }

    @Test
    @Transactional
    public void createMotherBed() throws Exception {
        int databaseSizeBeforeCreate = motherBedRepository.findAll().size();

        // Create the MotherBed
        MotherBedDTO motherBedDTO = motherBedMapper.toDto(motherBed);
        restMotherBedMockMvc.perform(post("/api/mother-beds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motherBedDTO)))
            .andExpect(status().isCreated());

        // Validate the MotherBed in the database
        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeCreate + 1);
        MotherBed testMotherBed = motherBedList.get(motherBedList.size() - 1);
        assertThat(testMotherBed.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testMotherBed.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMotherBed.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMotherBed.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testMotherBed.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testMotherBed.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createMotherBedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = motherBedRepository.findAll().size();

        // Create the MotherBed with an existing ID
        motherBed.setId(1L);
        MotherBedDTO motherBedDTO = motherBedMapper.toDto(motherBed);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMotherBedMockMvc.perform(post("/api/mother-beds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motherBedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MotherBed in the database
        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = motherBedRepository.findAll().size();
        // set the field null
        motherBed.setValue(null);

        // Create the MotherBed, which fails.
        MotherBedDTO motherBedDTO = motherBedMapper.toDto(motherBed);

        restMotherBedMockMvc.perform(post("/api/mother-beds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motherBedDTO)))
            .andExpect(status().isBadRequest());

        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMotherBeds() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList
        restMotherBedMockMvc.perform(get("/api/mother-beds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(motherBed.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    

    @Test
    @Transactional
    public void getMotherBed() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get the motherBed
        restMotherBedMockMvc.perform(get("/api/mother-beds/{id}", motherBed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(motherBed.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMotherBed() throws Exception {
        // Get the motherBed
        restMotherBedMockMvc.perform(get("/api/mother-beds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMotherBed() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        int databaseSizeBeforeUpdate = motherBedRepository.findAll().size();

        // Update the motherBed
        MotherBed updatedMotherBed = motherBedRepository.findById(motherBed.getId()).get();
        // Disconnect from session so that the updates on updatedMotherBed are not directly saved in db
        em.detach(updatedMotherBed);
        updatedMotherBed
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        MotherBedDTO motherBedDTO = motherBedMapper.toDto(updatedMotherBed);

        restMotherBedMockMvc.perform(put("/api/mother-beds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motherBedDTO)))
            .andExpect(status().isOk());

        // Validate the MotherBed in the database
        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeUpdate);
        MotherBed testMotherBed = motherBedList.get(motherBedList.size() - 1);
        assertThat(testMotherBed.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testMotherBed.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMotherBed.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMotherBed.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testMotherBed.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testMotherBed.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMotherBed() throws Exception {
        int databaseSizeBeforeUpdate = motherBedRepository.findAll().size();

        // Create the MotherBed
        MotherBedDTO motherBedDTO = motherBedMapper.toDto(motherBed);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMotherBedMockMvc.perform(put("/api/mother-beds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motherBedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MotherBed in the database
        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMotherBed() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        int databaseSizeBeforeDelete = motherBedRepository.findAll().size();

        // Get the motherBed
        restMotherBedMockMvc.perform(delete("/api/mother-beds/{id}", motherBed.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MotherBed.class);
        MotherBed motherBed1 = new MotherBed();
        motherBed1.setId(1L);
        MotherBed motherBed2 = new MotherBed();
        motherBed2.setId(motherBed1.getId());
        assertThat(motherBed1).isEqualTo(motherBed2);
        motherBed2.setId(2L);
        assertThat(motherBed1).isNotEqualTo(motherBed2);
        motherBed1.setId(null);
        assertThat(motherBed1).isNotEqualTo(motherBed2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MotherBedDTO.class);
        MotherBedDTO motherBedDTO1 = new MotherBedDTO();
        motherBedDTO1.setId(1L);
        MotherBedDTO motherBedDTO2 = new MotherBedDTO();
        assertThat(motherBedDTO1).isNotEqualTo(motherBedDTO2);
        motherBedDTO2.setId(motherBedDTO1.getId());
        assertThat(motherBedDTO1).isEqualTo(motherBedDTO2);
        motherBedDTO2.setId(2L);
        assertThat(motherBedDTO1).isNotEqualTo(motherBedDTO2);
        motherBedDTO1.setId(null);
        assertThat(motherBedDTO1).isNotEqualTo(motherBedDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(motherBedMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(motherBedMapper.fromId(null)).isNull();
    }
}
