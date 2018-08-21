package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.Damage;
import com.niche.ng.repository.DamageRepository;
import com.niche.ng.service.DamageService;
import com.niche.ng.service.dto.DamageDTO;
import com.niche.ng.service.mapper.DamageMapper;
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
 * Test class for the DamageResource REST controller.
 *
 * @see DamageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class DamageResourceIntTest {

    private static final Long DEFAULT_NO_OF_QUANTITY = 1L;
    private static final Long UPDATED_NO_OF_QUANTITY = 2L;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

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
    private DamageRepository damageRepository;


    @Autowired
    private DamageMapper damageMapper;
    

    @Autowired
    private DamageService damageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDamageMockMvc;

    private Damage damage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DamageResource damageResource = new DamageResource(damageService);
        this.restDamageMockMvc = MockMvcBuilders.standaloneSetup(damageResource)
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
    public static Damage createEntity(EntityManager em) {
        Damage damage = new Damage()
            .noOfQuantity(DEFAULT_NO_OF_QUANTITY)
            .date(DEFAULT_DATE)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return damage;
    }

    @Before
    public void initTest() {
        damage = createEntity(em);
    }

    @Test
    @Transactional
    public void createDamage() throws Exception {
        int databaseSizeBeforeCreate = damageRepository.findAll().size();

        // Create the Damage
        DamageDTO damageDTO = damageMapper.toDto(damage);
        restDamageMockMvc.perform(post("/api/damages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(damageDTO)))
            .andExpect(status().isCreated());

        // Validate the Damage in the database
        List<Damage> damageList = damageRepository.findAll();
        assertThat(damageList).hasSize(databaseSizeBeforeCreate + 1);
        Damage testDamage = damageList.get(damageList.size() - 1);
        assertThat(testDamage.getNoOfQuantity()).isEqualTo(DEFAULT_NO_OF_QUANTITY);
        assertThat(testDamage.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testDamage.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDamage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDamage.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testDamage.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDamage.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createDamageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = damageRepository.findAll().size();

        // Create the Damage with an existing ID
        damage.setId(1L);
        DamageDTO damageDTO = damageMapper.toDto(damage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDamageMockMvc.perform(post("/api/damages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(damageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Damage in the database
        List<Damage> damageList = damageRepository.findAll();
        assertThat(damageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNoOfQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = damageRepository.findAll().size();
        // set the field null
        damage.setNoOfQuantity(null);

        // Create the Damage, which fails.
        DamageDTO damageDTO = damageMapper.toDto(damage);

        restDamageMockMvc.perform(post("/api/damages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(damageDTO)))
            .andExpect(status().isBadRequest());

        List<Damage> damageList = damageRepository.findAll();
        assertThat(damageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = damageRepository.findAll().size();
        // set the field null
        damage.setDate(null);

        // Create the Damage, which fails.
        DamageDTO damageDTO = damageMapper.toDto(damage);

        restDamageMockMvc.perform(post("/api/damages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(damageDTO)))
            .andExpect(status().isBadRequest());

        List<Damage> damageList = damageRepository.findAll();
        assertThat(damageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDamages() throws Exception {
        // Initialize the database
        damageRepository.saveAndFlush(damage);

        // Get all the damageList
        restDamageMockMvc.perform(get("/api/damages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(damage.getId().intValue())))
            .andExpect(jsonPath("$.[*].noOfQuantity").value(hasItem(DEFAULT_NO_OF_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    

    @Test
    @Transactional
    public void getDamage() throws Exception {
        // Initialize the database
        damageRepository.saveAndFlush(damage);

        // Get the damage
        restDamageMockMvc.perform(get("/api/damages/{id}", damage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(damage.getId().intValue()))
            .andExpect(jsonPath("$.noOfQuantity").value(DEFAULT_NO_OF_QUANTITY.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDamage() throws Exception {
        // Get the damage
        restDamageMockMvc.perform(get("/api/damages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDamage() throws Exception {
        // Initialize the database
        damageRepository.saveAndFlush(damage);

        int databaseSizeBeforeUpdate = damageRepository.findAll().size();

        // Update the damage
        Damage updatedDamage = damageRepository.findById(damage.getId()).get();
        // Disconnect from session so that the updates on updatedDamage are not directly saved in db
        em.detach(updatedDamage);
        updatedDamage
            .noOfQuantity(UPDATED_NO_OF_QUANTITY)
            .date(UPDATED_DATE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        DamageDTO damageDTO = damageMapper.toDto(updatedDamage);

        restDamageMockMvc.perform(put("/api/damages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(damageDTO)))
            .andExpect(status().isOk());

        // Validate the Damage in the database
        List<Damage> damageList = damageRepository.findAll();
        assertThat(damageList).hasSize(databaseSizeBeforeUpdate);
        Damage testDamage = damageList.get(damageList.size() - 1);
        assertThat(testDamage.getNoOfQuantity()).isEqualTo(UPDATED_NO_OF_QUANTITY);
        assertThat(testDamage.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testDamage.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDamage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDamage.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testDamage.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDamage.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingDamage() throws Exception {
        int databaseSizeBeforeUpdate = damageRepository.findAll().size();

        // Create the Damage
        DamageDTO damageDTO = damageMapper.toDto(damage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDamageMockMvc.perform(put("/api/damages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(damageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Damage in the database
        List<Damage> damageList = damageRepository.findAll();
        assertThat(damageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDamage() throws Exception {
        // Initialize the database
        damageRepository.saveAndFlush(damage);

        int databaseSizeBeforeDelete = damageRepository.findAll().size();

        // Get the damage
        restDamageMockMvc.perform(delete("/api/damages/{id}", damage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Damage> damageList = damageRepository.findAll();
        assertThat(damageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Damage.class);
        Damage damage1 = new Damage();
        damage1.setId(1L);
        Damage damage2 = new Damage();
        damage2.setId(damage1.getId());
        assertThat(damage1).isEqualTo(damage2);
        damage2.setId(2L);
        assertThat(damage1).isNotEqualTo(damage2);
        damage1.setId(null);
        assertThat(damage1).isNotEqualTo(damage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DamageDTO.class);
        DamageDTO damageDTO1 = new DamageDTO();
        damageDTO1.setId(1L);
        DamageDTO damageDTO2 = new DamageDTO();
        assertThat(damageDTO1).isNotEqualTo(damageDTO2);
        damageDTO2.setId(damageDTO1.getId());
        assertThat(damageDTO1).isEqualTo(damageDTO2);
        damageDTO2.setId(2L);
        assertThat(damageDTO1).isNotEqualTo(damageDTO2);
        damageDTO1.setId(null);
        assertThat(damageDTO1).isNotEqualTo(damageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(damageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(damageMapper.fromId(null)).isNull();
    }
}
