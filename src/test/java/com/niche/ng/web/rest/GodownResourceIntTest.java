package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.Godown;
import com.niche.ng.repository.GodownRepository;
import com.niche.ng.service.GodownService;
import com.niche.ng.service.dto.GodownDTO;
import com.niche.ng.service.mapper.GodownMapper;
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
 * Test class for the GodownResource REST controller.
 *
 * @see GodownResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class GodownResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_INCHARGE = "AAAAAAAAAA";
    private static final String UPDATED_INCHARGE = "BBBBBBBBBB";

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
    private GodownRepository godownRepository;


    @Autowired
    private GodownMapper godownMapper;
    

    @Autowired
    private GodownService godownService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGodownMockMvc;

    private Godown godown;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GodownResource godownResource = new GodownResource(godownService);
        this.restGodownMockMvc = MockMvcBuilders.standaloneSetup(godownResource)
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
    public static Godown createEntity(EntityManager em) {
        Godown godown = new Godown()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .incharge(DEFAULT_INCHARGE)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return godown;
    }

    @Before
    public void initTest() {
        godown = createEntity(em);
    }

    @Test
    @Transactional
    public void createGodown() throws Exception {
        int databaseSizeBeforeCreate = godownRepository.findAll().size();

        // Create the Godown
        GodownDTO godownDTO = godownMapper.toDto(godown);
        restGodownMockMvc.perform(post("/api/godowns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownDTO)))
            .andExpect(status().isCreated());

        // Validate the Godown in the database
        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeCreate + 1);
        Godown testGodown = godownList.get(godownList.size() - 1);
        assertThat(testGodown.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGodown.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testGodown.getIncharge()).isEqualTo(DEFAULT_INCHARGE);
        assertThat(testGodown.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGodown.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGodown.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testGodown.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGodown.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createGodownWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = godownRepository.findAll().size();

        // Create the Godown with an existing ID
        godown.setId(1L);
        GodownDTO godownDTO = godownMapper.toDto(godown);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGodownMockMvc.perform(post("/api/godowns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Godown in the database
        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = godownRepository.findAll().size();
        // set the field null
        godown.setName(null);

        // Create the Godown, which fails.
        GodownDTO godownDTO = godownMapper.toDto(godown);

        restGodownMockMvc.perform(post("/api/godowns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownDTO)))
            .andExpect(status().isBadRequest());

        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGodowns() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList
        restGodownMockMvc.perform(get("/api/godowns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godown.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].incharge").value(hasItem(DEFAULT_INCHARGE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    

    @Test
    @Transactional
    public void getGodown() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get the godown
        restGodownMockMvc.perform(get("/api/godowns/{id}", godown.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(godown.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.incharge").value(DEFAULT_INCHARGE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingGodown() throws Exception {
        // Get the godown
        restGodownMockMvc.perform(get("/api/godowns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGodown() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        int databaseSizeBeforeUpdate = godownRepository.findAll().size();

        // Update the godown
        Godown updatedGodown = godownRepository.findById(godown.getId()).get();
        // Disconnect from session so that the updates on updatedGodown are not directly saved in db
        em.detach(updatedGodown);
        updatedGodown
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .incharge(UPDATED_INCHARGE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        GodownDTO godownDTO = godownMapper.toDto(updatedGodown);

        restGodownMockMvc.perform(put("/api/godowns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownDTO)))
            .andExpect(status().isOk());

        // Validate the Godown in the database
        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeUpdate);
        Godown testGodown = godownList.get(godownList.size() - 1);
        assertThat(testGodown.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGodown.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testGodown.getIncharge()).isEqualTo(UPDATED_INCHARGE);
        assertThat(testGodown.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGodown.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGodown.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testGodown.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGodown.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingGodown() throws Exception {
        int databaseSizeBeforeUpdate = godownRepository.findAll().size();

        // Create the Godown
        GodownDTO godownDTO = godownMapper.toDto(godown);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGodownMockMvc.perform(put("/api/godowns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Godown in the database
        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGodown() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        int databaseSizeBeforeDelete = godownRepository.findAll().size();

        // Get the godown
        restGodownMockMvc.perform(delete("/api/godowns/{id}", godown.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Godown.class);
        Godown godown1 = new Godown();
        godown1.setId(1L);
        Godown godown2 = new Godown();
        godown2.setId(godown1.getId());
        assertThat(godown1).isEqualTo(godown2);
        godown2.setId(2L);
        assertThat(godown1).isNotEqualTo(godown2);
        godown1.setId(null);
        assertThat(godown1).isNotEqualTo(godown2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownDTO.class);
        GodownDTO godownDTO1 = new GodownDTO();
        godownDTO1.setId(1L);
        GodownDTO godownDTO2 = new GodownDTO();
        assertThat(godownDTO1).isNotEqualTo(godownDTO2);
        godownDTO2.setId(godownDTO1.getId());
        assertThat(godownDTO1).isEqualTo(godownDTO2);
        godownDTO2.setId(2L);
        assertThat(godownDTO1).isNotEqualTo(godownDTO2);
        godownDTO1.setId(null);
        assertThat(godownDTO1).isNotEqualTo(godownDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(godownMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(godownMapper.fromId(null)).isNull();
    }
}
