package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.DataFeedFareBasisGroupMapping;
import com.atibusinessgroup.fmp.repository.DataFeedFareBasisGroupMappingRepository;
import com.atibusinessgroup.fmp.service.DataFeedFareBasisGroupMappingService;
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
 * Test class for the DataFeedFareBasisGroupMappingResource REST controller.
 *
 * @see DataFeedFareBasisGroupMappingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class DataFeedFareBasisGroupMappingResourceIntTest {

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
    private DataFeedFareBasisGroupMappingRepository dataFeedFareBasisGroupMappingRepository;

    @Autowired
    private DataFeedFareBasisGroupMappingService dataFeedFareBasisGroupMappingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDataFeedFareBasisGroupMappingMockMvc;

    private DataFeedFareBasisGroupMapping dataFeedFareBasisGroupMapping;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DataFeedFareBasisGroupMappingResource dataFeedFareBasisGroupMappingResource = new DataFeedFareBasisGroupMappingResource(dataFeedFareBasisGroupMappingService);
        this.restDataFeedFareBasisGroupMappingMockMvc = MockMvcBuilders.standaloneSetup(dataFeedFareBasisGroupMappingResource)
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
    public static DataFeedFareBasisGroupMapping createEntity() {
        DataFeedFareBasisGroupMapping dataFeedFareBasisGroupMapping = new DataFeedFareBasisGroupMapping()
            .originCity(DEFAULT_ORIGIN_CITY)
            .originCountry(DEFAULT_ORIGIN_COUNTRY)
            .destinationCity(DEFAULT_DESTINATION_CITY)
            .destinationCountry(DEFAULT_DESTINATION_COUNTRY)
            .rbd(DEFAULT_RBD)
            .fareBasisCode(DEFAULT_FARE_BASIS_CODE)
            .fareBasisGroup(DEFAULT_FARE_BASIS_GROUP)
            .priority(DEFAULT_PRIORITY);
        return dataFeedFareBasisGroupMapping;
    }

    @Before
    public void initTest() {
        dataFeedFareBasisGroupMappingRepository.deleteAll();
        dataFeedFareBasisGroupMapping = createEntity();
    }

    @Test
    public void createDataFeedFareBasisGroupMapping() throws Exception {
        int databaseSizeBeforeCreate = dataFeedFareBasisGroupMappingRepository.findAll().size();

        // Create the DataFeedFareBasisGroupMapping
        restDataFeedFareBasisGroupMappingMockMvc.perform(post("/api/data-feed-fare-basis-group-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataFeedFareBasisGroupMapping)))
            .andExpect(status().isCreated());

        // Validate the DataFeedFareBasisGroupMapping in the database
        List<DataFeedFareBasisGroupMapping> dataFeedFareBasisGroupMappingList = dataFeedFareBasisGroupMappingRepository.findAll();
        assertThat(dataFeedFareBasisGroupMappingList).hasSize(databaseSizeBeforeCreate + 1);
        DataFeedFareBasisGroupMapping testDataFeedFareBasisGroupMapping = dataFeedFareBasisGroupMappingList.get(dataFeedFareBasisGroupMappingList.size() - 1);
        assertThat(testDataFeedFareBasisGroupMapping.getOriginCity()).isEqualTo(DEFAULT_ORIGIN_CITY);
        assertThat(testDataFeedFareBasisGroupMapping.getOriginCountry()).isEqualTo(DEFAULT_ORIGIN_COUNTRY);
        assertThat(testDataFeedFareBasisGroupMapping.getDestinationCity()).isEqualTo(DEFAULT_DESTINATION_CITY);
        assertThat(testDataFeedFareBasisGroupMapping.getDestinationCountry()).isEqualTo(DEFAULT_DESTINATION_COUNTRY);
        assertThat(testDataFeedFareBasisGroupMapping.getRbd()).isEqualTo(DEFAULT_RBD);
        assertThat(testDataFeedFareBasisGroupMapping.getFareBasisCode()).isEqualTo(DEFAULT_FARE_BASIS_CODE);
        assertThat(testDataFeedFareBasisGroupMapping.getFareBasisGroup()).isEqualTo(DEFAULT_FARE_BASIS_GROUP);
        assertThat(testDataFeedFareBasisGroupMapping.getPriority()).isEqualTo(DEFAULT_PRIORITY);
    }

    @Test
    public void createDataFeedFareBasisGroupMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataFeedFareBasisGroupMappingRepository.findAll().size();

        // Create the DataFeedFareBasisGroupMapping with an existing ID
        dataFeedFareBasisGroupMapping.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataFeedFareBasisGroupMappingMockMvc.perform(post("/api/data-feed-fare-basis-group-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataFeedFareBasisGroupMapping)))
            .andExpect(status().isBadRequest());

        // Validate the DataFeedFareBasisGroupMapping in the database
        List<DataFeedFareBasisGroupMapping> dataFeedFareBasisGroupMappingList = dataFeedFareBasisGroupMappingRepository.findAll();
        assertThat(dataFeedFareBasisGroupMappingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDataFeedFareBasisGroupMappings() throws Exception {
        // Initialize the database
        dataFeedFareBasisGroupMappingRepository.save(dataFeedFareBasisGroupMapping);

        // Get all the dataFeedFareBasisGroupMappingList
        restDataFeedFareBasisGroupMappingMockMvc.perform(get("/api/data-feed-fare-basis-group-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataFeedFareBasisGroupMapping.getId())))
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
    public void getDataFeedFareBasisGroupMapping() throws Exception {
        // Initialize the database
        dataFeedFareBasisGroupMappingRepository.save(dataFeedFareBasisGroupMapping);

        // Get the dataFeedFareBasisGroupMapping
        restDataFeedFareBasisGroupMappingMockMvc.perform(get("/api/data-feed-fare-basis-group-mappings/{id}", dataFeedFareBasisGroupMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dataFeedFareBasisGroupMapping.getId()))
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
    public void getNonExistingDataFeedFareBasisGroupMapping() throws Exception {
        // Get the dataFeedFareBasisGroupMapping
        restDataFeedFareBasisGroupMappingMockMvc.perform(get("/api/data-feed-fare-basis-group-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDataFeedFareBasisGroupMapping() throws Exception {
        // Initialize the database
        dataFeedFareBasisGroupMappingService.save(dataFeedFareBasisGroupMapping);

        int databaseSizeBeforeUpdate = dataFeedFareBasisGroupMappingRepository.findAll().size();

        // Update the dataFeedFareBasisGroupMapping
        DataFeedFareBasisGroupMapping updatedDataFeedFareBasisGroupMapping = dataFeedFareBasisGroupMappingRepository.findOne(dataFeedFareBasisGroupMapping.getId());
        updatedDataFeedFareBasisGroupMapping
            .originCity(UPDATED_ORIGIN_CITY)
            .originCountry(UPDATED_ORIGIN_COUNTRY)
            .destinationCity(UPDATED_DESTINATION_CITY)
            .destinationCountry(UPDATED_DESTINATION_COUNTRY)
            .rbd(UPDATED_RBD)
            .fareBasisCode(UPDATED_FARE_BASIS_CODE)
            .fareBasisGroup(UPDATED_FARE_BASIS_GROUP)
            .priority(UPDATED_PRIORITY);

        restDataFeedFareBasisGroupMappingMockMvc.perform(put("/api/data-feed-fare-basis-group-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDataFeedFareBasisGroupMapping)))
            .andExpect(status().isOk());

        // Validate the DataFeedFareBasisGroupMapping in the database
        List<DataFeedFareBasisGroupMapping> dataFeedFareBasisGroupMappingList = dataFeedFareBasisGroupMappingRepository.findAll();
        assertThat(dataFeedFareBasisGroupMappingList).hasSize(databaseSizeBeforeUpdate);
        DataFeedFareBasisGroupMapping testDataFeedFareBasisGroupMapping = dataFeedFareBasisGroupMappingList.get(dataFeedFareBasisGroupMappingList.size() - 1);
        assertThat(testDataFeedFareBasisGroupMapping.getOriginCity()).isEqualTo(UPDATED_ORIGIN_CITY);
        assertThat(testDataFeedFareBasisGroupMapping.getOriginCountry()).isEqualTo(UPDATED_ORIGIN_COUNTRY);
        assertThat(testDataFeedFareBasisGroupMapping.getDestinationCity()).isEqualTo(UPDATED_DESTINATION_CITY);
        assertThat(testDataFeedFareBasisGroupMapping.getDestinationCountry()).isEqualTo(UPDATED_DESTINATION_COUNTRY);
        assertThat(testDataFeedFareBasisGroupMapping.getRbd()).isEqualTo(UPDATED_RBD);
        assertThat(testDataFeedFareBasisGroupMapping.getFareBasisCode()).isEqualTo(UPDATED_FARE_BASIS_CODE);
        assertThat(testDataFeedFareBasisGroupMapping.getFareBasisGroup()).isEqualTo(UPDATED_FARE_BASIS_GROUP);
        assertThat(testDataFeedFareBasisGroupMapping.getPriority()).isEqualTo(UPDATED_PRIORITY);
    }

    @Test
    public void updateNonExistingDataFeedFareBasisGroupMapping() throws Exception {
        int databaseSizeBeforeUpdate = dataFeedFareBasisGroupMappingRepository.findAll().size();

        // Create the DataFeedFareBasisGroupMapping

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDataFeedFareBasisGroupMappingMockMvc.perform(put("/api/data-feed-fare-basis-group-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataFeedFareBasisGroupMapping)))
            .andExpect(status().isCreated());

        // Validate the DataFeedFareBasisGroupMapping in the database
        List<DataFeedFareBasisGroupMapping> dataFeedFareBasisGroupMappingList = dataFeedFareBasisGroupMappingRepository.findAll();
        assertThat(dataFeedFareBasisGroupMappingList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDataFeedFareBasisGroupMapping() throws Exception {
        // Initialize the database
        dataFeedFareBasisGroupMappingService.save(dataFeedFareBasisGroupMapping);

        int databaseSizeBeforeDelete = dataFeedFareBasisGroupMappingRepository.findAll().size();

        // Get the dataFeedFareBasisGroupMapping
        restDataFeedFareBasisGroupMappingMockMvc.perform(delete("/api/data-feed-fare-basis-group-mappings/{id}", dataFeedFareBasisGroupMapping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DataFeedFareBasisGroupMapping> dataFeedFareBasisGroupMappingList = dataFeedFareBasisGroupMappingRepository.findAll();
        assertThat(dataFeedFareBasisGroupMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataFeedFareBasisGroupMapping.class);
        DataFeedFareBasisGroupMapping dataFeedFareBasisGroupMapping1 = new DataFeedFareBasisGroupMapping();
        dataFeedFareBasisGroupMapping1.setId("id1");
        DataFeedFareBasisGroupMapping dataFeedFareBasisGroupMapping2 = new DataFeedFareBasisGroupMapping();
        dataFeedFareBasisGroupMapping2.setId(dataFeedFareBasisGroupMapping1.getId());
        assertThat(dataFeedFareBasisGroupMapping1).isEqualTo(dataFeedFareBasisGroupMapping2);
        dataFeedFareBasisGroupMapping2.setId("id2");
        assertThat(dataFeedFareBasisGroupMapping1).isNotEqualTo(dataFeedFareBasisGroupMapping2);
        dataFeedFareBasisGroupMapping1.setId(null);
        assertThat(dataFeedFareBasisGroupMapping1).isNotEqualTo(dataFeedFareBasisGroupMapping2);
    }
}
