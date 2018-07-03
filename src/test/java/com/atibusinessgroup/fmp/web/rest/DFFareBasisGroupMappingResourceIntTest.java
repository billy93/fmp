package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.DFFareBasisGroupMapping;
import com.atibusinessgroup.fmp.repository.DFFareBasisGroupMappingRepository;
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
 * Test class for the DFFareBasisGroupMappingResource REST controller.
 *
 * @see DFFareBasisGroupMappingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class DFFareBasisGroupMappingResourceIntTest {

    private static final String DEFAULT_ORIGIN_CITY = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGIN_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_CITY = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_RBD = "AAAAAAAAAA";
    private static final String UPDATED_RBD = "BBBBBBBBBB";

    private static final String DEFAULT_FARE_BASIS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FARE_BASIS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FARE_BASIS_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_FARE_BASIS_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_PRIORITY = "AAAAAAAAAA";
    private static final String UPDATED_PRIORITY = "BBBBBBBBBB";

    @Autowired
    private DFFareBasisGroupMappingRepository dFFareBasisGroupMappingRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDFFareBasisGroupMappingMockMvc;

    private DFFareBasisGroupMapping dFFareBasisGroupMapping;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DFFareBasisGroupMappingResource dFFareBasisGroupMappingResource = new DFFareBasisGroupMappingResource(dFFareBasisGroupMappingRepository);
        this.restDFFareBasisGroupMappingMockMvc = MockMvcBuilders.standaloneSetup(dFFareBasisGroupMappingResource)
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
    public static DFFareBasisGroupMapping createEntity() {
        DFFareBasisGroupMapping dFFareBasisGroupMapping = new DFFareBasisGroupMapping()
            .originCity(DEFAULT_ORIGIN_CITY)
            .originCountry(DEFAULT_ORIGIN_COUNTRY)
            .destinationCity(DEFAULT_DESTINATION_CITY)
            .destinationCountry(DEFAULT_DESTINATION_COUNTRY)
            .rbd(DEFAULT_RBD)
            .fareBasisCode(DEFAULT_FARE_BASIS_CODE)
            .fareBasisGroup(DEFAULT_FARE_BASIS_GROUP)
            .priority(DEFAULT_PRIORITY);
        return dFFareBasisGroupMapping;
    }

    @Before
    public void initTest() {
        dFFareBasisGroupMappingRepository.deleteAll();
        dFFareBasisGroupMapping = createEntity();
    }

    @Test
    public void createDFFareBasisGroupMapping() throws Exception {
        int databaseSizeBeforeCreate = dFFareBasisGroupMappingRepository.findAll().size();

        // Create the DFFareBasisGroupMapping
        restDFFareBasisGroupMappingMockMvc.perform(post("/api/df-fare-basis-group-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFFareBasisGroupMapping)))
            .andExpect(status().isCreated());

        // Validate the DFFareBasisGroupMapping in the database
        List<DFFareBasisGroupMapping> dFFareBasisGroupMappingList = dFFareBasisGroupMappingRepository.findAll();
        assertThat(dFFareBasisGroupMappingList).hasSize(databaseSizeBeforeCreate + 1);
        DFFareBasisGroupMapping testDFFareBasisGroupMapping = dFFareBasisGroupMappingList.get(dFFareBasisGroupMappingList.size() - 1);
        assertThat(testDFFareBasisGroupMapping.getOriginCity()).isEqualTo(DEFAULT_ORIGIN_CITY);
        assertThat(testDFFareBasisGroupMapping.getOriginCountry()).isEqualTo(DEFAULT_ORIGIN_COUNTRY);
        assertThat(testDFFareBasisGroupMapping.getDestinationCity()).isEqualTo(DEFAULT_DESTINATION_CITY);
        assertThat(testDFFareBasisGroupMapping.getDestinationCountry()).isEqualTo(DEFAULT_DESTINATION_COUNTRY);
        assertThat(testDFFareBasisGroupMapping.getRbd()).isEqualTo(DEFAULT_RBD);
        assertThat(testDFFareBasisGroupMapping.getFareBasisCode()).isEqualTo(DEFAULT_FARE_BASIS_CODE);
        assertThat(testDFFareBasisGroupMapping.getFareBasisGroup()).isEqualTo(DEFAULT_FARE_BASIS_GROUP);
        assertThat(testDFFareBasisGroupMapping.getPriority()).isEqualTo(DEFAULT_PRIORITY);
    }

    @Test
    public void createDFFareBasisGroupMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dFFareBasisGroupMappingRepository.findAll().size();

        // Create the DFFareBasisGroupMapping with an existing ID
        dFFareBasisGroupMapping.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDFFareBasisGroupMappingMockMvc.perform(post("/api/df-fare-basis-group-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFFareBasisGroupMapping)))
            .andExpect(status().isBadRequest());

        // Validate the DFFareBasisGroupMapping in the database
        List<DFFareBasisGroupMapping> dFFareBasisGroupMappingList = dFFareBasisGroupMappingRepository.findAll();
        assertThat(dFFareBasisGroupMappingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDFFareBasisGroupMappings() throws Exception {
        // Initialize the database
        dFFareBasisGroupMappingRepository.save(dFFareBasisGroupMapping);

        // Get all the dFFareBasisGroupMappingList
        restDFFareBasisGroupMappingMockMvc.perform(get("/api/df-fare-basis-group-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dFFareBasisGroupMapping.getId())))
            .andExpect(jsonPath("$.[*].originCity").value(hasItem(DEFAULT_ORIGIN_CITY.toString())))
            .andExpect(jsonPath("$.[*].originCountry").value(hasItem(DEFAULT_ORIGIN_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].destinationCity").value(hasItem(DEFAULT_DESTINATION_CITY.toString())))
            .andExpect(jsonPath("$.[*].destinationCountry").value(hasItem(DEFAULT_DESTINATION_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].rbd").value(hasItem(DEFAULT_RBD.toString())))
            .andExpect(jsonPath("$.[*].fareBasisCode").value(hasItem(DEFAULT_FARE_BASIS_CODE.toString())))
            .andExpect(jsonPath("$.[*].fareBasisGroup").value(hasItem(DEFAULT_FARE_BASIS_GROUP.toString())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY.toString())));
    }

    @Test
    public void getDFFareBasisGroupMapping() throws Exception {
        // Initialize the database
        dFFareBasisGroupMappingRepository.save(dFFareBasisGroupMapping);

        // Get the dFFareBasisGroupMapping
        restDFFareBasisGroupMappingMockMvc.perform(get("/api/df-fare-basis-group-mappings/{id}", dFFareBasisGroupMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dFFareBasisGroupMapping.getId()))
            .andExpect(jsonPath("$.originCity").value(DEFAULT_ORIGIN_CITY.toString()))
            .andExpect(jsonPath("$.originCountry").value(DEFAULT_ORIGIN_COUNTRY.toString()))
            .andExpect(jsonPath("$.destinationCity").value(DEFAULT_DESTINATION_CITY.toString()))
            .andExpect(jsonPath("$.destinationCountry").value(DEFAULT_DESTINATION_COUNTRY.toString()))
            .andExpect(jsonPath("$.rbd").value(DEFAULT_RBD.toString()))
            .andExpect(jsonPath("$.fareBasisCode").value(DEFAULT_FARE_BASIS_CODE.toString()))
            .andExpect(jsonPath("$.fareBasisGroup").value(DEFAULT_FARE_BASIS_GROUP.toString()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY.toString()));
    }

    @Test
    public void getNonExistingDFFareBasisGroupMapping() throws Exception {
        // Get the dFFareBasisGroupMapping
        restDFFareBasisGroupMappingMockMvc.perform(get("/api/df-fare-basis-group-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDFFareBasisGroupMapping() throws Exception {
        // Initialize the database
        dFFareBasisGroupMappingRepository.save(dFFareBasisGroupMapping);
        int databaseSizeBeforeUpdate = dFFareBasisGroupMappingRepository.findAll().size();

        // Update the dFFareBasisGroupMapping
        DFFareBasisGroupMapping updatedDFFareBasisGroupMapping = dFFareBasisGroupMappingRepository.findOne(dFFareBasisGroupMapping.getId());
        updatedDFFareBasisGroupMapping
            .originCity(UPDATED_ORIGIN_CITY)
            .originCountry(UPDATED_ORIGIN_COUNTRY)
            .destinationCity(UPDATED_DESTINATION_CITY)
            .destinationCountry(UPDATED_DESTINATION_COUNTRY)
            .rbd(UPDATED_RBD)
            .fareBasisCode(UPDATED_FARE_BASIS_CODE)
            .fareBasisGroup(UPDATED_FARE_BASIS_GROUP)
            .priority(UPDATED_PRIORITY);

        restDFFareBasisGroupMappingMockMvc.perform(put("/api/df-fare-basis-group-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDFFareBasisGroupMapping)))
            .andExpect(status().isOk());

        // Validate the DFFareBasisGroupMapping in the database
        List<DFFareBasisGroupMapping> dFFareBasisGroupMappingList = dFFareBasisGroupMappingRepository.findAll();
        assertThat(dFFareBasisGroupMappingList).hasSize(databaseSizeBeforeUpdate);
        DFFareBasisGroupMapping testDFFareBasisGroupMapping = dFFareBasisGroupMappingList.get(dFFareBasisGroupMappingList.size() - 1);
        assertThat(testDFFareBasisGroupMapping.getOriginCity()).isEqualTo(UPDATED_ORIGIN_CITY);
        assertThat(testDFFareBasisGroupMapping.getOriginCountry()).isEqualTo(UPDATED_ORIGIN_COUNTRY);
        assertThat(testDFFareBasisGroupMapping.getDestinationCity()).isEqualTo(UPDATED_DESTINATION_CITY);
        assertThat(testDFFareBasisGroupMapping.getDestinationCountry()).isEqualTo(UPDATED_DESTINATION_COUNTRY);
        assertThat(testDFFareBasisGroupMapping.getRbd()).isEqualTo(UPDATED_RBD);
        assertThat(testDFFareBasisGroupMapping.getFareBasisCode()).isEqualTo(UPDATED_FARE_BASIS_CODE);
        assertThat(testDFFareBasisGroupMapping.getFareBasisGroup()).isEqualTo(UPDATED_FARE_BASIS_GROUP);
        assertThat(testDFFareBasisGroupMapping.getPriority()).isEqualTo(UPDATED_PRIORITY);
    }

    @Test
    public void updateNonExistingDFFareBasisGroupMapping() throws Exception {
        int databaseSizeBeforeUpdate = dFFareBasisGroupMappingRepository.findAll().size();

        // Create the DFFareBasisGroupMapping

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDFFareBasisGroupMappingMockMvc.perform(put("/api/df-fare-basis-group-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFFareBasisGroupMapping)))
            .andExpect(status().isCreated());

        // Validate the DFFareBasisGroupMapping in the database
        List<DFFareBasisGroupMapping> dFFareBasisGroupMappingList = dFFareBasisGroupMappingRepository.findAll();
        assertThat(dFFareBasisGroupMappingList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDFFareBasisGroupMapping() throws Exception {
        // Initialize the database
        dFFareBasisGroupMappingRepository.save(dFFareBasisGroupMapping);
        int databaseSizeBeforeDelete = dFFareBasisGroupMappingRepository.findAll().size();

        // Get the dFFareBasisGroupMapping
        restDFFareBasisGroupMappingMockMvc.perform(delete("/api/df-fare-basis-group-mappings/{id}", dFFareBasisGroupMapping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DFFareBasisGroupMapping> dFFareBasisGroupMappingList = dFFareBasisGroupMappingRepository.findAll();
        assertThat(dFFareBasisGroupMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DFFareBasisGroupMapping.class);
        DFFareBasisGroupMapping dFFareBasisGroupMapping1 = new DFFareBasisGroupMapping();
        dFFareBasisGroupMapping1.setId("id1");
        DFFareBasisGroupMapping dFFareBasisGroupMapping2 = new DFFareBasisGroupMapping();
        dFFareBasisGroupMapping2.setId(dFFareBasisGroupMapping1.getId());
        assertThat(dFFareBasisGroupMapping1).isEqualTo(dFFareBasisGroupMapping2);
        dFFareBasisGroupMapping2.setId("id2");
        assertThat(dFFareBasisGroupMapping1).isNotEqualTo(dFFareBasisGroupMapping2);
        dFFareBasisGroupMapping1.setId(null);
        assertThat(dFFareBasisGroupMapping1).isNotEqualTo(dFFareBasisGroupMapping2);
    }
}
