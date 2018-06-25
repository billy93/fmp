package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.DFPublishedFareRates;
import com.atibusinessgroup.fmp.repository.DFPublishedFareRatesRepository;
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
 * Test class for the DFPublishedFareRatesResource REST controller.
 *
 * @see DFPublishedFareRatesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class DFPublishedFareRatesResourceIntTest {

    private static final String DEFAULT_POST_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_POST_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_TRAVEL_DATE_FROM = "AAAAAAAAAA";
    private static final String UPDATED_TRAVEL_DATE_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_TRAVEL_DATE_TO = "AAAAAAAAAA";
    private static final String UPDATED_TRAVEL_DATE_TO = "BBBBBBBBBB";

    private static final String DEFAULT_RATE = "AAAAAAAAAA";
    private static final String UPDATED_RATE = "BBBBBBBBBB";

    @Autowired
    private DFPublishedFareRatesRepository dFPublishedFareRatesRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDFPublishedFareRatesMockMvc;

    private DFPublishedFareRates dFPublishedFareRates;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DFPublishedFareRatesResource dFPublishedFareRatesResource = new DFPublishedFareRatesResource(dFPublishedFareRatesRepository);
        this.restDFPublishedFareRatesMockMvc = MockMvcBuilders.standaloneSetup(dFPublishedFareRatesResource)
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
    public static DFPublishedFareRates createEntity() {
        DFPublishedFareRates dFPublishedFareRates = new DFPublishedFareRates()
            .postCountry(DEFAULT_POST_COUNTRY)
            .travelDateFrom(DEFAULT_TRAVEL_DATE_FROM)
            .travelDateTo(DEFAULT_TRAVEL_DATE_TO)
            .rate(DEFAULT_RATE);
        return dFPublishedFareRates;
    }

    @Before
    public void initTest() {
        dFPublishedFareRatesRepository.deleteAll();
        dFPublishedFareRates = createEntity();
    }

    @Test
    public void createDFPublishedFareRates() throws Exception {
        int databaseSizeBeforeCreate = dFPublishedFareRatesRepository.findAll().size();

        // Create the DFPublishedFareRates
        restDFPublishedFareRatesMockMvc.perform(post("/api/df-published-fare-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFPublishedFareRates)))
            .andExpect(status().isCreated());

        // Validate the DFPublishedFareRates in the database
        List<DFPublishedFareRates> dFPublishedFareRatesList = dFPublishedFareRatesRepository.findAll();
        assertThat(dFPublishedFareRatesList).hasSize(databaseSizeBeforeCreate + 1);
        DFPublishedFareRates testDFPublishedFareRates = dFPublishedFareRatesList.get(dFPublishedFareRatesList.size() - 1);
        assertThat(testDFPublishedFareRates.getPostCountry()).isEqualTo(DEFAULT_POST_COUNTRY);
        assertThat(testDFPublishedFareRates.getTravelDateFrom()).isEqualTo(DEFAULT_TRAVEL_DATE_FROM);
        assertThat(testDFPublishedFareRates.getTravelDateTo()).isEqualTo(DEFAULT_TRAVEL_DATE_TO);
        assertThat(testDFPublishedFareRates.getRate()).isEqualTo(DEFAULT_RATE);
    }

    @Test
    public void createDFPublishedFareRatesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dFPublishedFareRatesRepository.findAll().size();

        // Create the DFPublishedFareRates with an existing ID
        dFPublishedFareRates.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDFPublishedFareRatesMockMvc.perform(post("/api/df-published-fare-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFPublishedFareRates)))
            .andExpect(status().isBadRequest());

        // Validate the DFPublishedFareRates in the database
        List<DFPublishedFareRates> dFPublishedFareRatesList = dFPublishedFareRatesRepository.findAll();
        assertThat(dFPublishedFareRatesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDFPublishedFareRates() throws Exception {
        // Initialize the database
        dFPublishedFareRatesRepository.save(dFPublishedFareRates);

        // Get all the dFPublishedFareRatesList
        restDFPublishedFareRatesMockMvc.perform(get("/api/df-published-fare-rates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dFPublishedFareRates.getId())))
            .andExpect(jsonPath("$.[*].postCountry").value(hasItem(DEFAULT_POST_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].travelDateFrom").value(hasItem(DEFAULT_TRAVEL_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].travelDateTo").value(hasItem(DEFAULT_TRAVEL_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE.toString())));
    }

    @Test
    public void getDFPublishedFareRates() throws Exception {
        // Initialize the database
        dFPublishedFareRatesRepository.save(dFPublishedFareRates);

        // Get the dFPublishedFareRates
        restDFPublishedFareRatesMockMvc.perform(get("/api/df-published-fare-rates/{id}", dFPublishedFareRates.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dFPublishedFareRates.getId()))
            .andExpect(jsonPath("$.postCountry").value(DEFAULT_POST_COUNTRY.toString()))
            .andExpect(jsonPath("$.travelDateFrom").value(DEFAULT_TRAVEL_DATE_FROM.toString()))
            .andExpect(jsonPath("$.travelDateTo").value(DEFAULT_TRAVEL_DATE_TO.toString()))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE.toString()));
    }

    @Test
    public void getNonExistingDFPublishedFareRates() throws Exception {
        // Get the dFPublishedFareRates
        restDFPublishedFareRatesMockMvc.perform(get("/api/df-published-fare-rates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDFPublishedFareRates() throws Exception {
        // Initialize the database
        dFPublishedFareRatesRepository.save(dFPublishedFareRates);
        int databaseSizeBeforeUpdate = dFPublishedFareRatesRepository.findAll().size();

        // Update the dFPublishedFareRates
        DFPublishedFareRates updatedDFPublishedFareRates = dFPublishedFareRatesRepository.findOne(dFPublishedFareRates.getId());
        updatedDFPublishedFareRates
            .postCountry(UPDATED_POST_COUNTRY)
            .travelDateFrom(UPDATED_TRAVEL_DATE_FROM)
            .travelDateTo(UPDATED_TRAVEL_DATE_TO)
            .rate(UPDATED_RATE);

        restDFPublishedFareRatesMockMvc.perform(put("/api/df-published-fare-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDFPublishedFareRates)))
            .andExpect(status().isOk());

        // Validate the DFPublishedFareRates in the database
        List<DFPublishedFareRates> dFPublishedFareRatesList = dFPublishedFareRatesRepository.findAll();
        assertThat(dFPublishedFareRatesList).hasSize(databaseSizeBeforeUpdate);
        DFPublishedFareRates testDFPublishedFareRates = dFPublishedFareRatesList.get(dFPublishedFareRatesList.size() - 1);
        assertThat(testDFPublishedFareRates.getPostCountry()).isEqualTo(UPDATED_POST_COUNTRY);
        assertThat(testDFPublishedFareRates.getTravelDateFrom()).isEqualTo(UPDATED_TRAVEL_DATE_FROM);
        assertThat(testDFPublishedFareRates.getTravelDateTo()).isEqualTo(UPDATED_TRAVEL_DATE_TO);
        assertThat(testDFPublishedFareRates.getRate()).isEqualTo(UPDATED_RATE);
    }

    @Test
    public void updateNonExistingDFPublishedFareRates() throws Exception {
        int databaseSizeBeforeUpdate = dFPublishedFareRatesRepository.findAll().size();

        // Create the DFPublishedFareRates

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDFPublishedFareRatesMockMvc.perform(put("/api/df-published-fare-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFPublishedFareRates)))
            .andExpect(status().isCreated());

        // Validate the DFPublishedFareRates in the database
        List<DFPublishedFareRates> dFPublishedFareRatesList = dFPublishedFareRatesRepository.findAll();
        assertThat(dFPublishedFareRatesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDFPublishedFareRates() throws Exception {
        // Initialize the database
        dFPublishedFareRatesRepository.save(dFPublishedFareRates);
        int databaseSizeBeforeDelete = dFPublishedFareRatesRepository.findAll().size();

        // Get the dFPublishedFareRates
        restDFPublishedFareRatesMockMvc.perform(delete("/api/df-published-fare-rates/{id}", dFPublishedFareRates.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DFPublishedFareRates> dFPublishedFareRatesList = dFPublishedFareRatesRepository.findAll();
        assertThat(dFPublishedFareRatesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DFPublishedFareRates.class);
        DFPublishedFareRates dFPublishedFareRates1 = new DFPublishedFareRates();
        dFPublishedFareRates1.setId("id1");
        DFPublishedFareRates dFPublishedFareRates2 = new DFPublishedFareRates();
        dFPublishedFareRates2.setId(dFPublishedFareRates1.getId());
        assertThat(dFPublishedFareRates1).isEqualTo(dFPublishedFareRates2);
        dFPublishedFareRates2.setId("id2");
        assertThat(dFPublishedFareRates1).isNotEqualTo(dFPublishedFareRates2);
        dFPublishedFareRates1.setId(null);
        assertThat(dFPublishedFareRates1).isNotEqualTo(dFPublishedFareRates2);
    }
}
