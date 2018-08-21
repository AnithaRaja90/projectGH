package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.Sector;
import com.niche.ng.repository.SectorRepository;
import com.niche.ng.service.SectorService;
import com.niche.ng.service.dto.SectorDTO;
import com.niche.ng.service.mapper.SectorMapper;
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
 * Test class for the SectorResource REST controller.
 *
 * @see SectorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class SectorResourceIntTest {

    private static final String DEFAULT_SECTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SECTOR_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_SECTOR_INCHARGE = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR_INCHARGE = "BBBBBBBBBB";

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
    private SectorRepository sectorRepository;


    @Autowired
    private SectorMapper sectorMapper;
    

    @Autowired
    private SectorService sectorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSectorMockMvc;

    private Sector sector;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SectorResource sectorResource = new SectorResource(sectorService);
        this.restSectorMockMvc = MockMvcBuilders.standaloneSetup(sectorResource)
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
    public static Sector createEntity(EntityManager em) {
        Sector sector = new Sector()
            .sectorName(DEFAULT_SECTOR_NAME)
            .sectorAddress(DEFAULT_SECTOR_ADDRESS)
            .sectorIncharge(DEFAULT_SECTOR_INCHARGE)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return sector;
    }

    @Before
    public void initTest() {
        sector = createEntity(em);
    }

    @Test
    @Transactional
    public void createSector() throws Exception {
        int databaseSizeBeforeCreate = sectorRepository.findAll().size();

        // Create the Sector
        SectorDTO sectorDTO = sectorMapper.toDto(sector);
        restSectorMockMvc.perform(post("/api/sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorDTO)))
            .andExpect(status().isCreated());

        // Validate the Sector in the database
        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeCreate + 1);
        Sector testSector = sectorList.get(sectorList.size() - 1);
        assertThat(testSector.getSectorName()).isEqualTo(DEFAULT_SECTOR_NAME);
        assertThat(testSector.getSectorAddress()).isEqualTo(DEFAULT_SECTOR_ADDRESS);
        assertThat(testSector.getSectorIncharge()).isEqualTo(DEFAULT_SECTOR_INCHARGE);
        assertThat(testSector.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSector.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSector.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testSector.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testSector.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createSectorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sectorRepository.findAll().size();

        // Create the Sector with an existing ID
        sector.setId(1L);
        SectorDTO sectorDTO = sectorMapper.toDto(sector);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSectorMockMvc.perform(post("/api/sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sector in the database
        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSectorNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sectorRepository.findAll().size();
        // set the field null
        sector.setSectorName(null);

        // Create the Sector, which fails.
        SectorDTO sectorDTO = sectorMapper.toDto(sector);

        restSectorMockMvc.perform(post("/api/sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorDTO)))
            .andExpect(status().isBadRequest());

        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSectors() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList
        restSectorMockMvc.perform(get("/api/sectors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sector.getId().intValue())))
            .andExpect(jsonPath("$.[*].sectorName").value(hasItem(DEFAULT_SECTOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].sectorAddress").value(hasItem(DEFAULT_SECTOR_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].sectorIncharge").value(hasItem(DEFAULT_SECTOR_INCHARGE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    

    @Test
    @Transactional
    public void getSector() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get the sector
        restSectorMockMvc.perform(get("/api/sectors/{id}", sector.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sector.getId().intValue()))
            .andExpect(jsonPath("$.sectorName").value(DEFAULT_SECTOR_NAME.toString()))
            .andExpect(jsonPath("$.sectorAddress").value(DEFAULT_SECTOR_ADDRESS.toString()))
            .andExpect(jsonPath("$.sectorIncharge").value(DEFAULT_SECTOR_INCHARGE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSector() throws Exception {
        // Get the sector
        restSectorMockMvc.perform(get("/api/sectors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSector() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        int databaseSizeBeforeUpdate = sectorRepository.findAll().size();

        // Update the sector
        Sector updatedSector = sectorRepository.findById(sector.getId()).get();
        // Disconnect from session so that the updates on updatedSector are not directly saved in db
        em.detach(updatedSector);
        updatedSector
            .sectorName(UPDATED_SECTOR_NAME)
            .sectorAddress(UPDATED_SECTOR_ADDRESS)
            .sectorIncharge(UPDATED_SECTOR_INCHARGE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        SectorDTO sectorDTO = sectorMapper.toDto(updatedSector);

        restSectorMockMvc.perform(put("/api/sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorDTO)))
            .andExpect(status().isOk());

        // Validate the Sector in the database
        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeUpdate);
        Sector testSector = sectorList.get(sectorList.size() - 1);
        assertThat(testSector.getSectorName()).isEqualTo(UPDATED_SECTOR_NAME);
        assertThat(testSector.getSectorAddress()).isEqualTo(UPDATED_SECTOR_ADDRESS);
        assertThat(testSector.getSectorIncharge()).isEqualTo(UPDATED_SECTOR_INCHARGE);
        assertThat(testSector.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSector.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSector.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testSector.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testSector.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingSector() throws Exception {
        int databaseSizeBeforeUpdate = sectorRepository.findAll().size();

        // Create the Sector
        SectorDTO sectorDTO = sectorMapper.toDto(sector);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSectorMockMvc.perform(put("/api/sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sector in the database
        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSector() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        int databaseSizeBeforeDelete = sectorRepository.findAll().size();

        // Get the sector
        restSectorMockMvc.perform(delete("/api/sectors/{id}", sector.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sector.class);
        Sector sector1 = new Sector();
        sector1.setId(1L);
        Sector sector2 = new Sector();
        sector2.setId(sector1.getId());
        assertThat(sector1).isEqualTo(sector2);
        sector2.setId(2L);
        assertThat(sector1).isNotEqualTo(sector2);
        sector1.setId(null);
        assertThat(sector1).isNotEqualTo(sector2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SectorDTO.class);
        SectorDTO sectorDTO1 = new SectorDTO();
        sectorDTO1.setId(1L);
        SectorDTO sectorDTO2 = new SectorDTO();
        assertThat(sectorDTO1).isNotEqualTo(sectorDTO2);
        sectorDTO2.setId(sectorDTO1.getId());
        assertThat(sectorDTO1).isEqualTo(sectorDTO2);
        sectorDTO2.setId(2L);
        assertThat(sectorDTO1).isNotEqualTo(sectorDTO2);
        sectorDTO1.setId(null);
        assertThat(sectorDTO1).isNotEqualTo(sectorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sectorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sectorMapper.fromId(null)).isNull();
    }
}
