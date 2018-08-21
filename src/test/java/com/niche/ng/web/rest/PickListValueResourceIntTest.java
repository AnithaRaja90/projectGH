package com.niche.ng.web.rest;

import com.niche.ng.ProjectGhApp;

import com.niche.ng.domain.PickListValue;
import com.niche.ng.repository.PickListValueRepository;
import com.niche.ng.service.PickListValueService;
import com.niche.ng.service.dto.PickListValueDTO;
import com.niche.ng.service.mapper.PickListValueMapper;
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
 * Test class for the PickListValueResource REST controller.
 *
 * @see PickListValueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectGhApp.class)
public class PickListValueResourceIntTest {

    private static final String DEFAULT_PICK_LIST_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_PICK_LIST_VALUE = "BBBBBBBBBB";

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
    private PickListValueRepository pickListValueRepository;


    @Autowired
    private PickListValueMapper pickListValueMapper;
    

    @Autowired
    private PickListValueService pickListValueService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPickListValueMockMvc;

    private PickListValue pickListValue;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PickListValueResource pickListValueResource = new PickListValueResource(pickListValueService);
        this.restPickListValueMockMvc = MockMvcBuilders.standaloneSetup(pickListValueResource)
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
    public static PickListValue createEntity(EntityManager em) {
        PickListValue pickListValue = new PickListValue()
            .pickListValue(DEFAULT_PICK_LIST_VALUE)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return pickListValue;
    }

    @Before
    public void initTest() {
        pickListValue = createEntity(em);
    }

    @Test
    @Transactional
    public void createPickListValue() throws Exception {
        int databaseSizeBeforeCreate = pickListValueRepository.findAll().size();

        // Create the PickListValue
        PickListValueDTO pickListValueDTO = pickListValueMapper.toDto(pickListValue);
        restPickListValueMockMvc.perform(post("/api/pick-list-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListValueDTO)))
            .andExpect(status().isCreated());

        // Validate the PickListValue in the database
        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeCreate + 1);
        PickListValue testPickListValue = pickListValueList.get(pickListValueList.size() - 1);
        assertThat(testPickListValue.getPickListValue()).isEqualTo(DEFAULT_PICK_LIST_VALUE);
        assertThat(testPickListValue.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPickListValue.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPickListValue.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testPickListValue.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPickListValue.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createPickListValueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pickListValueRepository.findAll().size();

        // Create the PickListValue with an existing ID
        pickListValue.setId(1L);
        PickListValueDTO pickListValueDTO = pickListValueMapper.toDto(pickListValue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPickListValueMockMvc.perform(post("/api/pick-list-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PickListValue in the database
        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPickListValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = pickListValueRepository.findAll().size();
        // set the field null
        pickListValue.setPickListValue(null);

        // Create the PickListValue, which fails.
        PickListValueDTO pickListValueDTO = pickListValueMapper.toDto(pickListValue);

        restPickListValueMockMvc.perform(post("/api/pick-list-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListValueDTO)))
            .andExpect(status().isBadRequest());

        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPickListValues() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get all the pickListValueList
        restPickListValueMockMvc.perform(get("/api/pick-list-values?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pickListValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].pickListValue").value(hasItem(DEFAULT_PICK_LIST_VALUE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    

    @Test
    @Transactional
    public void getPickListValue() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get the pickListValue
        restPickListValueMockMvc.perform(get("/api/pick-list-values/{id}", pickListValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pickListValue.getId().intValue()))
            .andExpect(jsonPath("$.pickListValue").value(DEFAULT_PICK_LIST_VALUE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPickListValue() throws Exception {
        // Get the pickListValue
        restPickListValueMockMvc.perform(get("/api/pick-list-values/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePickListValue() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        int databaseSizeBeforeUpdate = pickListValueRepository.findAll().size();

        // Update the pickListValue
        PickListValue updatedPickListValue = pickListValueRepository.findById(pickListValue.getId()).get();
        // Disconnect from session so that the updates on updatedPickListValue are not directly saved in db
        em.detach(updatedPickListValue);
        updatedPickListValue
            .pickListValue(UPDATED_PICK_LIST_VALUE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        PickListValueDTO pickListValueDTO = pickListValueMapper.toDto(updatedPickListValue);

        restPickListValueMockMvc.perform(put("/api/pick-list-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListValueDTO)))
            .andExpect(status().isOk());

        // Validate the PickListValue in the database
        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeUpdate);
        PickListValue testPickListValue = pickListValueList.get(pickListValueList.size() - 1);
        assertThat(testPickListValue.getPickListValue()).isEqualTo(UPDATED_PICK_LIST_VALUE);
        assertThat(testPickListValue.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPickListValue.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPickListValue.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testPickListValue.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPickListValue.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingPickListValue() throws Exception {
        int databaseSizeBeforeUpdate = pickListValueRepository.findAll().size();

        // Create the PickListValue
        PickListValueDTO pickListValueDTO = pickListValueMapper.toDto(pickListValue);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPickListValueMockMvc.perform(put("/api/pick-list-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PickListValue in the database
        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePickListValue() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        int databaseSizeBeforeDelete = pickListValueRepository.findAll().size();

        // Get the pickListValue
        restPickListValueMockMvc.perform(delete("/api/pick-list-values/{id}", pickListValue.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PickListValue.class);
        PickListValue pickListValue1 = new PickListValue();
        pickListValue1.setId(1L);
        PickListValue pickListValue2 = new PickListValue();
        pickListValue2.setId(pickListValue1.getId());
        assertThat(pickListValue1).isEqualTo(pickListValue2);
        pickListValue2.setId(2L);
        assertThat(pickListValue1).isNotEqualTo(pickListValue2);
        pickListValue1.setId(null);
        assertThat(pickListValue1).isNotEqualTo(pickListValue2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PickListValueDTO.class);
        PickListValueDTO pickListValueDTO1 = new PickListValueDTO();
        pickListValueDTO1.setId(1L);
        PickListValueDTO pickListValueDTO2 = new PickListValueDTO();
        assertThat(pickListValueDTO1).isNotEqualTo(pickListValueDTO2);
        pickListValueDTO2.setId(pickListValueDTO1.getId());
        assertThat(pickListValueDTO1).isEqualTo(pickListValueDTO2);
        pickListValueDTO2.setId(2L);
        assertThat(pickListValueDTO1).isNotEqualTo(pickListValueDTO2);
        pickListValueDTO1.setId(null);
        assertThat(pickListValueDTO1).isNotEqualTo(pickListValueDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pickListValueMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pickListValueMapper.fromId(null)).isNull();
    }
}
