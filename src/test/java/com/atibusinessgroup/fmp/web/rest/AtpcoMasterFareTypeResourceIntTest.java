package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.AtpcoMasterFareType;
import com.atibusinessgroup.fmp.repository.AtpcoMasterFareTypeRepository;
import com.atibusinessgroup.fmp.service.AtpcoMasterFareTypeService;
import com.atibusinessgroup.fmp.web.rest.errors.ExceptionTranslator;

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

import java.util.List;

import static com.atibusinessgroup.fmp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AtpcoMasterFareTypeResource REST controller.
 *
 * @see AtpcoMasterFareTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class AtpcoMasterFareTypeResourceIntTest {

    private static final String DEFAULT_FARE_TYPE_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_FARE_TYPE_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DEFINITION = "AAAAAAAAAA";
    private static final String UPDATED_DEFINITION = "BBBBBBBBBB";

    @Autowired
    private AtpcoMasterFareTypeRepository atpcoMasterFareTypeRepository;

    @Autowired
    private AtpcoMasterFareTypeService atpcoMasterFareTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAtpcoMasterFareTypeMockMvc;

    private AtpcoMasterFareType atpcoMasterFareType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AtpcoMasterFareTypeResource atpcoMasterFareTypeResource = new AtpcoMasterFareTypeResource(atpcoMasterFareTypeService);
        this.restAtpcoMasterFareTypeMockMvc = MockMvcBuilders.standaloneSetup(atpcoMasterFareTypeResource)
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
    public static AtpcoMasterFareType createEntity() {
        AtpcoMasterFareType atpcoMasterFareType = new AtpcoMasterFareType()
            .fareTypeDesignation(DEFAULT_FARE_TYPE_DESIGNATION)
            .typeCode(DEFAULT_TYPE_CODE)
            .definition(DEFAULT_DEFINITION);
        return atpcoMasterFareType;
    }

    @Before
    public void initTest() {
        atpcoMasterFareTypeRepository.deleteAll();
        atpcoMasterFareType = createEntity();
    }

    @Test
    public void createAtpcoMasterFareType() throws Exception {
        int databaseSizeBeforeCreate = atpcoMasterFareTypeRepository.findAll().size();

        // Create the AtpcoMasterFareType
        restAtpcoMasterFareTypeMockMvc.perform(post("/api/atpco-master-fare-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atpcoMasterFareType)))
            .andExpect(status().isCreated());

        // Validate the AtpcoMasterFareType in the database
        List<AtpcoMasterFareType> atpcoMasterFareTypeList = atpcoMasterFareTypeRepository.findAll();
        assertThat(atpcoMasterFareTypeList).hasSize(databaseSizeBeforeCreate + 1);
        AtpcoMasterFareType testAtpcoMasterFareType = atpcoMasterFareTypeList.get(atpcoMasterFareTypeList.size() - 1);
        assertThat(testAtpcoMasterFareType.getFareTypeDesignation()).isEqualTo(DEFAULT_FARE_TYPE_DESIGNATION);
        assertThat(testAtpcoMasterFareType.getTypeCode()).isEqualTo(DEFAULT_TYPE_CODE);
        assertThat(testAtpcoMasterFareType.getDefinition()).isEqualTo(DEFAULT_DEFINITION);
    }

    @Test
    public void createAtpcoMasterFareTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = atpcoMasterFareTypeRepository.findAll().size();

        // Create the AtpcoMasterFareType with an existing ID
        atpcoMasterFareType.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAtpcoMasterFareTypeMockMvc.perform(post("/api/atpco-master-fare-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atpcoMasterFareType)))
            .andExpect(status().isBadRequest());

        // Validate the AtpcoMasterFareType in the database
        List<AtpcoMasterFareType> atpcoMasterFareTypeList = atpcoMasterFareTypeRepository.findAll();
        assertThat(atpcoMasterFareTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAtpcoMasterFareTypes() throws Exception {
        // Initialize the database
        atpcoMasterFareTypeRepository.save(atpcoMasterFareType);

        // Get all the atpcoMasterFareTypeList
        restAtpcoMasterFareTypeMockMvc.perform(get("/api/atpco-master-fare-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(atpcoMasterFareType.getId())))
            .andExpect(jsonPath("$.[*].fareTypeDesignation").value(hasItem(DEFAULT_FARE_TYPE_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].typeCode").value(hasItem(DEFAULT_TYPE_CODE.toString())))
            .andExpect(jsonPath("$.[*].definition").value(hasItem(DEFAULT_DEFINITION.toString())));
    }

    @Test
    public void getAtpcoMasterFareType() throws Exception {
        // Initialize the database
        atpcoMasterFareTypeRepository.save(atpcoMasterFareType);

        // Get the atpcoMasterFareType
        restAtpcoMasterFareTypeMockMvc.perform(get("/api/atpco-master-fare-types/{id}", atpcoMasterFareType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(atpcoMasterFareType.getId()))
            .andExpect(jsonPath("$.fareTypeDesignation").value(DEFAULT_FARE_TYPE_DESIGNATION.toString()))
            .andExpect(jsonPath("$.typeCode").value(DEFAULT_TYPE_CODE.toString()))
            .andExpect(jsonPath("$.definition").value(DEFAULT_DEFINITION.toString()));
    }

    @Test
    public void getNonExistingAtpcoMasterFareType() throws Exception {
        // Get the atpcoMasterFareType
        restAtpcoMasterFareTypeMockMvc.perform(get("/api/atpco-master-fare-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAtpcoMasterFareType() throws Exception {
        // Initialize the database
        atpcoMasterFareTypeService.save(atpcoMasterFareType);

        int databaseSizeBeforeUpdate = atpcoMasterFareTypeRepository.findAll().size();

        // Update the atpcoMasterFareType
        AtpcoMasterFareType updatedAtpcoMasterFareType = atpcoMasterFareTypeRepository.findOne(atpcoMasterFareType.getId());
        updatedAtpcoMasterFareType
            .fareTypeDesignation(UPDATED_FARE_TYPE_DESIGNATION)
            .typeCode(UPDATED_TYPE_CODE)
            .definition(UPDATED_DEFINITION);

        restAtpcoMasterFareTypeMockMvc.perform(put("/api/atpco-master-fare-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAtpcoMasterFareType)))
            .andExpect(status().isOk());

        // Validate the AtpcoMasterFareType in the database
        List<AtpcoMasterFareType> atpcoMasterFareTypeList = atpcoMasterFareTypeRepository.findAll();
        assertThat(atpcoMasterFareTypeList).hasSize(databaseSizeBeforeUpdate);
        AtpcoMasterFareType testAtpcoMasterFareType = atpcoMasterFareTypeList.get(atpcoMasterFareTypeList.size() - 1);
        assertThat(testAtpcoMasterFareType.getFareTypeDesignation()).isEqualTo(UPDATED_FARE_TYPE_DESIGNATION);
        assertThat(testAtpcoMasterFareType.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
        assertThat(testAtpcoMasterFareType.getDefinition()).isEqualTo(UPDATED_DEFINITION);
    }

    @Test
    public void updateNonExistingAtpcoMasterFareType() throws Exception {
        int databaseSizeBeforeUpdate = atpcoMasterFareTypeRepository.findAll().size();

        // Create the AtpcoMasterFareType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAtpcoMasterFareTypeMockMvc.perform(put("/api/atpco-master-fare-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atpcoMasterFareType)))
            .andExpect(status().isCreated());

        // Validate the AtpcoMasterFareType in the database
        List<AtpcoMasterFareType> atpcoMasterFareTypeList = atpcoMasterFareTypeRepository.findAll();
        assertThat(atpcoMasterFareTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteAtpcoMasterFareType() throws Exception {
        // Initialize the database
        atpcoMasterFareTypeService.save(atpcoMasterFareType);

        int databaseSizeBeforeDelete = atpcoMasterFareTypeRepository.findAll().size();

        // Get the atpcoMasterFareType
        restAtpcoMasterFareTypeMockMvc.perform(delete("/api/atpco-master-fare-types/{id}", atpcoMasterFareType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AtpcoMasterFareType> atpcoMasterFareTypeList = atpcoMasterFareTypeRepository.findAll();
        assertThat(atpcoMasterFareTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AtpcoMasterFareType.class);
        AtpcoMasterFareType atpcoMasterFareType1 = new AtpcoMasterFareType();
        atpcoMasterFareType1.setId("id1");
        AtpcoMasterFareType atpcoMasterFareType2 = new AtpcoMasterFareType();
        atpcoMasterFareType2.setId(atpcoMasterFareType1.getId());
        assertThat(atpcoMasterFareType1).isEqualTo(atpcoMasterFareType2);
        atpcoMasterFareType2.setId("id2");
        assertThat(atpcoMasterFareType1).isNotEqualTo(atpcoMasterFareType2);
        atpcoMasterFareType1.setId(null);
        assertThat(atpcoMasterFareType1).isNotEqualTo(atpcoMasterFareType2);
    }
}
