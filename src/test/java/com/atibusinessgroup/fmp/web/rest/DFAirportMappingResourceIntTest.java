package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.DFAirportMapping;
import com.atibusinessgroup.fmp.repository.DFAirportMappingRepository;
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
 * Test class for the DFAirportMappingResource REST controller.
 *
 * @see DFAirportMappingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class DFAirportMappingResourceIntTest {

    private static final String DEFAULT_CITY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CITY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_AIRPORT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_AIRPORT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    @Autowired
    private DFAirportMappingRepository dFAirportMappingRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDFAirportMappingMockMvc;

    private DFAirportMapping dFAirportMapping;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DFAirportMappingResource dFAirportMappingResource = new DFAirportMappingResource(dFAirportMappingRepository);
        this.restDFAirportMappingMockMvc = MockMvcBuilders.standaloneSetup(dFAirportMappingResource)
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
    public static DFAirportMapping createEntity() {
        DFAirportMapping dFAirportMapping = new DFAirportMapping()
            .cityCode(DEFAULT_CITY_CODE)
            .airportCode(DEFAULT_AIRPORT_CODE)
            .countryCode(DEFAULT_COUNTRY_CODE);
        return dFAirportMapping;
    }

    @Before
    public void initTest() {
        dFAirportMappingRepository.deleteAll();
        dFAirportMapping = createEntity();
    }

    @Test
    public void createDFAirportMapping() throws Exception {
        int databaseSizeBeforeCreate = dFAirportMappingRepository.findAll().size();

        // Create the DFAirportMapping
        restDFAirportMappingMockMvc.perform(post("/api/df-airport-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFAirportMapping)))
            .andExpect(status().isCreated());

        // Validate the DFAirportMapping in the database
        List<DFAirportMapping> dFAirportMappingList = dFAirportMappingRepository.findAll();
        assertThat(dFAirportMappingList).hasSize(databaseSizeBeforeCreate + 1);
        DFAirportMapping testDFAirportMapping = dFAirportMappingList.get(dFAirportMappingList.size() - 1);
        assertThat(testDFAirportMapping.getCityCode()).isEqualTo(DEFAULT_CITY_CODE);
        assertThat(testDFAirportMapping.getAirportCode()).isEqualTo(DEFAULT_AIRPORT_CODE);
        assertThat(testDFAirportMapping.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
    }

    @Test
    public void createDFAirportMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dFAirportMappingRepository.findAll().size();

        // Create the DFAirportMapping with an existing ID
        dFAirportMapping.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDFAirportMappingMockMvc.perform(post("/api/df-airport-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFAirportMapping)))
            .andExpect(status().isBadRequest());

        // Validate the DFAirportMapping in the database
        List<DFAirportMapping> dFAirportMappingList = dFAirportMappingRepository.findAll();
        assertThat(dFAirportMappingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDFAirportMappings() throws Exception {
        // Initialize the database
        dFAirportMappingRepository.save(dFAirportMapping);

        // Get all the dFAirportMappingList
        restDFAirportMappingMockMvc.perform(get("/api/df-airport-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dFAirportMapping.getId())))
            .andExpect(jsonPath("$.[*].cityCode").value(hasItem(DEFAULT_CITY_CODE.toString())))
            .andExpect(jsonPath("$.[*].airportCode").value(hasItem(DEFAULT_AIRPORT_CODE.toString())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE.toString())));
    }

    @Test
    public void getDFAirportMapping() throws Exception {
        // Initialize the database
        dFAirportMappingRepository.save(dFAirportMapping);

        // Get the dFAirportMapping
        restDFAirportMappingMockMvc.perform(get("/api/df-airport-mappings/{id}", dFAirportMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dFAirportMapping.getId()))
            .andExpect(jsonPath("$.cityCode").value(DEFAULT_CITY_CODE.toString()))
            .andExpect(jsonPath("$.airportCode").value(DEFAULT_AIRPORT_CODE.toString()))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE.toString()));
    }

    @Test
    public void getNonExistingDFAirportMapping() throws Exception {
        // Get the dFAirportMapping
        restDFAirportMappingMockMvc.perform(get("/api/df-airport-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDFAirportMapping() throws Exception {
        // Initialize the database
        dFAirportMappingRepository.save(dFAirportMapping);
        int databaseSizeBeforeUpdate = dFAirportMappingRepository.findAll().size();

        // Update the dFAirportMapping
        DFAirportMapping updatedDFAirportMapping = dFAirportMappingRepository.findOne(dFAirportMapping.getId());
        updatedDFAirportMapping
            .cityCode(UPDATED_CITY_CODE)
            .airportCode(UPDATED_AIRPORT_CODE)
            .countryCode(UPDATED_COUNTRY_CODE);

        restDFAirportMappingMockMvc.perform(put("/api/df-airport-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDFAirportMapping)))
            .andExpect(status().isOk());

        // Validate the DFAirportMapping in the database
        List<DFAirportMapping> dFAirportMappingList = dFAirportMappingRepository.findAll();
        assertThat(dFAirportMappingList).hasSize(databaseSizeBeforeUpdate);
        DFAirportMapping testDFAirportMapping = dFAirportMappingList.get(dFAirportMappingList.size() - 1);
        assertThat(testDFAirportMapping.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
        assertThat(testDFAirportMapping.getAirportCode()).isEqualTo(UPDATED_AIRPORT_CODE);
        assertThat(testDFAirportMapping.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
    }

    @Test
    public void updateNonExistingDFAirportMapping() throws Exception {
        int databaseSizeBeforeUpdate = dFAirportMappingRepository.findAll().size();

        // Create the DFAirportMapping

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDFAirportMappingMockMvc.perform(put("/api/df-airport-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFAirportMapping)))
            .andExpect(status().isCreated());

        // Validate the DFAirportMapping in the database
        List<DFAirportMapping> dFAirportMappingList = dFAirportMappingRepository.findAll();
        assertThat(dFAirportMappingList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDFAirportMapping() throws Exception {
        // Initialize the database
        dFAirportMappingRepository.save(dFAirportMapping);
        int databaseSizeBeforeDelete = dFAirportMappingRepository.findAll().size();

        // Get the dFAirportMapping
        restDFAirportMappingMockMvc.perform(delete("/api/df-airport-mappings/{id}", dFAirportMapping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DFAirportMapping> dFAirportMappingList = dFAirportMappingRepository.findAll();
        assertThat(dFAirportMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DFAirportMapping.class);
        DFAirportMapping dFAirportMapping1 = new DFAirportMapping();
        dFAirportMapping1.setId("id1");
        DFAirportMapping dFAirportMapping2 = new DFAirportMapping();
        dFAirportMapping2.setId(dFAirportMapping1.getId());
        assertThat(dFAirportMapping1).isEqualTo(dFAirportMapping2);
        dFAirportMapping2.setId("id2");
        assertThat(dFAirportMapping1).isNotEqualTo(dFAirportMapping2);
        dFAirportMapping1.setId(null);
        assertThat(dFAirportMapping1).isNotEqualTo(dFAirportMapping2);
    }
}
