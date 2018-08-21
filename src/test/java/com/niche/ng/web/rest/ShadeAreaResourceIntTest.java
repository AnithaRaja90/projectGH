package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.ShadeArea;
import com.niche.ng.repository.ShadeAreaRepository;
import com.niche.ng.service.ShadeAreaService;
import com.niche.ng.service.dto.ShadeAreaDTO;
import com.niche.ng.service.mapper.ShadeAreaMapper;
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
 * Test class for the ShadeAreaResource REST controller.
 *
 * @see ShadeAreaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class ShadeAreaResourceIntTest {

    private static final Long DEFAULT_NO_OF_SEEDLINGS = 1L;
    private static final Long UPDATED_NO_OF_SEEDLINGS = 2L;

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

    private static final Integer DEFAULT_DAMAGE = 1;
    private static final Integer UPDATED_DAMAGE = 2;

    private static final Integer DEFAULT_SAPLINGS = 1;
    private static final Integer UPDATED_SAPLINGS = 2;

    @Autowired
    private ShadeAreaRepository shadeAreaRepository;


    @Autowired
    private ShadeAreaMapper shadeAreaMapper;
    

    @Autowired
    private ShadeAreaService shadeAreaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restShadeAreaMockMvc;

    private ShadeArea shadeArea;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShadeAreaResource shadeAreaResource = new ShadeAreaResource(shadeAreaService);
        this.restShadeAreaMockMvc = MockMvcBuilders.standaloneSetup(shadeAreaResource)
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
    public static ShadeArea createEntity(EntityManager em) {
        ShadeArea shadeArea = new ShadeArea()
            .noOfSeedlings(DEFAULT_NO_OF_SEEDLINGS)
            .date(DEFAULT_DATE)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .damage(DEFAULT_DAMAGE)
            .saplings(DEFAULT_SAPLINGS);
        return shadeArea;
    }

    @Before
    public void initTest() {
        shadeArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createShadeArea() throws Exception {
        int databaseSizeBeforeCreate = shadeAreaRepository.findAll().size();

        // Create the ShadeArea
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(shadeArea);
        restShadeAreaMockMvc.perform(post("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isCreated());

        // Validate the ShadeArea in the database
        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeCreate + 1);
        ShadeArea testShadeArea = shadeAreaList.get(shadeAreaList.size() - 1);
        assertThat(testShadeArea.getNoOfSeedlings()).isEqualTo(DEFAULT_NO_OF_SEEDLINGS);
        assertThat(testShadeArea.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testShadeArea.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testShadeArea.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testShadeArea.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testShadeArea.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testShadeArea.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testShadeArea.getDamage()).isEqualTo(DEFAULT_DAMAGE);
        assertThat(testShadeArea.getSaplings()).isEqualTo(DEFAULT_SAPLINGS);
    }

    @Test
    @Transactional
    public void createShadeAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shadeAreaRepository.findAll().size();

        // Create the ShadeArea with an existing ID
        shadeArea.setId(1L);
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(shadeArea);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShadeAreaMockMvc.perform(post("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShadeArea in the database
        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNoOfSeedlingsIsRequired() throws Exception {
        int databaseSizeBeforeTest = shadeAreaRepository.findAll().size();
        // set the field null
        shadeArea.setNoOfSeedlings(null);

        // Create the ShadeArea, which fails.
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(shadeArea);

        restShadeAreaMockMvc.perform(post("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isBadRequest());

        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = shadeAreaRepository.findAll().size();
        // set the field null
        shadeArea.setDate(null);

        // Create the ShadeArea, which fails.
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(shadeArea);

        restShadeAreaMockMvc.perform(post("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isBadRequest());

        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllShadeAreas() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList
        restShadeAreaMockMvc.perform(get("/api/shade-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shadeArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].noOfSeedlings").value(hasItem(DEFAULT_NO_OF_SEEDLINGS.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].damage").value(hasItem(DEFAULT_DAMAGE)))
            .andExpect(jsonPath("$.[*].saplings").value(hasItem(DEFAULT_SAPLINGS)));
    }
    

    @Test
    @Transactional
    public void getShadeArea() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get the shadeArea
        restShadeAreaMockMvc.perform(get("/api/shade-areas/{id}", shadeArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shadeArea.getId().intValue()))
            .andExpect(jsonPath("$.noOfSeedlings").value(DEFAULT_NO_OF_SEEDLINGS.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.damage").value(DEFAULT_DAMAGE))
            .andExpect(jsonPath("$.saplings").value(DEFAULT_SAPLINGS));
    }
    @Test
    @Transactional
    public void getNonExistingShadeArea() throws Exception {
        // Get the shadeArea
        restShadeAreaMockMvc.perform(get("/api/shade-areas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShadeArea() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        int databaseSizeBeforeUpdate = shadeAreaRepository.findAll().size();

        // Update the shadeArea
        ShadeArea updatedShadeArea = shadeAreaRepository.findById(shadeArea.getId()).get();
        // Disconnect from session so that the updates on updatedShadeArea are not directly saved in db
        em.detach(updatedShadeArea);
        updatedShadeArea
            .noOfSeedlings(UPDATED_NO_OF_SEEDLINGS)
            .date(UPDATED_DATE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .damage(UPDATED_DAMAGE)
            .saplings(UPDATED_SAPLINGS);
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(updatedShadeArea);

        restShadeAreaMockMvc.perform(put("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isOk());

        // Validate the ShadeArea in the database
        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeUpdate);
        ShadeArea testShadeArea = shadeAreaList.get(shadeAreaList.size() - 1);
        assertThat(testShadeArea.getNoOfSeedlings()).isEqualTo(UPDATED_NO_OF_SEEDLINGS);
        assertThat(testShadeArea.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testShadeArea.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testShadeArea.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testShadeArea.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testShadeArea.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testShadeArea.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testShadeArea.getDamage()).isEqualTo(UPDATED_DAMAGE);
        assertThat(testShadeArea.getSaplings()).isEqualTo(UPDATED_SAPLINGS);
    }

    @Test
    @Transactional
    public void updateNonExistingShadeArea() throws Exception {
        int databaseSizeBeforeUpdate = shadeAreaRepository.findAll().size();

        // Create the ShadeArea
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(shadeArea);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restShadeAreaMockMvc.perform(put("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShadeArea in the database
        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShadeArea() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        int databaseSizeBeforeDelete = shadeAreaRepository.findAll().size();

        // Get the shadeArea
        restShadeAreaMockMvc.perform(delete("/api/shade-areas/{id}", shadeArea.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShadeArea.class);
        ShadeArea shadeArea1 = new ShadeArea();
        shadeArea1.setId(1L);
        ShadeArea shadeArea2 = new ShadeArea();
        shadeArea2.setId(shadeArea1.getId());
        assertThat(shadeArea1).isEqualTo(shadeArea2);
        shadeArea2.setId(2L);
        assertThat(shadeArea1).isNotEqualTo(shadeArea2);
        shadeArea1.setId(null);
        assertThat(shadeArea1).isNotEqualTo(shadeArea2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShadeAreaDTO.class);
        ShadeAreaDTO shadeAreaDTO1 = new ShadeAreaDTO();
        shadeAreaDTO1.setId(1L);
        ShadeAreaDTO shadeAreaDTO2 = new ShadeAreaDTO();
        assertThat(shadeAreaDTO1).isNotEqualTo(shadeAreaDTO2);
        shadeAreaDTO2.setId(shadeAreaDTO1.getId());
        assertThat(shadeAreaDTO1).isEqualTo(shadeAreaDTO2);
        shadeAreaDTO2.setId(2L);
        assertThat(shadeAreaDTO1).isNotEqualTo(shadeAreaDTO2);
        shadeAreaDTO1.setId(null);
        assertThat(shadeAreaDTO1).isNotEqualTo(shadeAreaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(shadeAreaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(shadeAreaMapper.fromId(null)).isNull();
    }
}
