package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.DFOriginDestination;
import com.atibusinessgroup.fmp.repository.DFOriginDestinationRepository;
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
 * Test class for the DFOriginDestinationResource REST controller.
 *
 * @see DFOriginDestinationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class DFOriginDestinationResourceIntTest {

    private static final String DEFAULT_ORIG_AIRPORT = "AAAAAAAAAA";
    private static final String UPDATED_ORIG_AIRPORT = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_AIRPORT = "AAAAAAAAAA";
    private static final String UPDATED_DEST_AIRPORT = "BBBBBBBBBB";

    private static final String DEFAULT_ORIG_CITY = "AAAAAAAAAA";
    private static final String UPDATED_ORIG_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_CITY = "AAAAAAAAAA";
    private static final String UPDATED_DEST_CITY = "BBBBBBBBBB";

    @Autowired
    private DFOriginDestinationRepository dFOriginDestinationRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDFOriginDestinationMockMvc;

    private DFOriginDestination dFOriginDestination;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DFOriginDestinationResource dFOriginDestinationResource = new DFOriginDestinationResource(dFOriginDestinationRepository);
        this.restDFOriginDestinationMockMvc = MockMvcBuilders.standaloneSetup(dFOriginDestinationResource)
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
    public static DFOriginDestination createEntity() {
        DFOriginDestination dFOriginDestination = new DFOriginDestination()
            .origAirport(DEFAULT_ORIG_AIRPORT)
            .destAirport(DEFAULT_DEST_AIRPORT)
            .origCity(DEFAULT_ORIG_CITY)
            .destCity(DEFAULT_DEST_CITY);
        return dFOriginDestination;
    }

    @Before
    public void initTest() {
        dFOriginDestinationRepository.deleteAll();
        dFOriginDestination = createEntity();
    }

    @Test
    public void createDFOriginDestination() throws Exception {
        int databaseSizeBeforeCreate = dFOriginDestinationRepository.findAll().size();

        // Create the DFOriginDestination
        restDFOriginDestinationMockMvc.perform(post("/api/df-origin-destinations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFOriginDestination)))
            .andExpect(status().isCreated());

        // Validate the DFOriginDestination in the database
        List<DFOriginDestination> dFOriginDestinationList = dFOriginDestinationRepository.findAll();
        assertThat(dFOriginDestinationList).hasSize(databaseSizeBeforeCreate + 1);
        DFOriginDestination testDFOriginDestination = dFOriginDestinationList.get(dFOriginDestinationList.size() - 1);
        assertThat(testDFOriginDestination.getOrigAirport()).isEqualTo(DEFAULT_ORIG_AIRPORT);
        assertThat(testDFOriginDestination.getDestAirport()).isEqualTo(DEFAULT_DEST_AIRPORT);
        assertThat(testDFOriginDestination.getOrigCity()).isEqualTo(DEFAULT_ORIG_CITY);
        assertThat(testDFOriginDestination.getDestCity()).isEqualTo(DEFAULT_DEST_CITY);
    }

    @Test
    public void createDFOriginDestinationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dFOriginDestinationRepository.findAll().size();

        // Create the DFOriginDestination with an existing ID
        dFOriginDestination.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDFOriginDestinationMockMvc.perform(post("/api/df-origin-destinations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFOriginDestination)))
            .andExpect(status().isBadRequest());

        // Validate the DFOriginDestination in the database
        List<DFOriginDestination> dFOriginDestinationList = dFOriginDestinationRepository.findAll();
        assertThat(dFOriginDestinationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDFOriginDestinations() throws Exception {
        // Initialize the database
        dFOriginDestinationRepository.save(dFOriginDestination);

        // Get all the dFOriginDestinationList
        restDFOriginDestinationMockMvc.perform(get("/api/df-origin-destinations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dFOriginDestination.getId())))
            .andExpect(jsonPath("$.[*].origAirport").value(hasItem(DEFAULT_ORIG_AIRPORT.toString())))
            .andExpect(jsonPath("$.[*].destAirport").value(hasItem(DEFAULT_DEST_AIRPORT.toString())))
            .andExpect(jsonPath("$.[*].origCity").value(hasItem(DEFAULT_ORIG_CITY.toString())))
            .andExpect(jsonPath("$.[*].destCity").value(hasItem(DEFAULT_DEST_CITY.toString())));
    }

    @Test
    public void getDFOriginDestination() throws Exception {
        // Initialize the database
        dFOriginDestinationRepository.save(dFOriginDestination);

        // Get the dFOriginDestination
        restDFOriginDestinationMockMvc.perform(get("/api/df-origin-destinations/{id}", dFOriginDestination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dFOriginDestination.getId()))
            .andExpect(jsonPath("$.origAirport").value(DEFAULT_ORIG_AIRPORT.toString()))
            .andExpect(jsonPath("$.destAirport").value(DEFAULT_DEST_AIRPORT.toString()))
            .andExpect(jsonPath("$.origCity").value(DEFAULT_ORIG_CITY.toString()))
            .andExpect(jsonPath("$.destCity").value(DEFAULT_DEST_CITY.toString()));
    }

    @Test
    public void getNonExistingDFOriginDestination() throws Exception {
        // Get the dFOriginDestination
        restDFOriginDestinationMockMvc.perform(get("/api/df-origin-destinations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDFOriginDestination() throws Exception {
        // Initialize the database
        dFOriginDestinationRepository.save(dFOriginDestination);
        int databaseSizeBeforeUpdate = dFOriginDestinationRepository.findAll().size();

        // Update the dFOriginDestination
        DFOriginDestination updatedDFOriginDestination = dFOriginDestinationRepository.findOne(dFOriginDestination.getId());
        updatedDFOriginDestination
            .origAirport(UPDATED_ORIG_AIRPORT)
            .destAirport(UPDATED_DEST_AIRPORT)
            .origCity(UPDATED_ORIG_CITY)
            .destCity(UPDATED_DEST_CITY);

        restDFOriginDestinationMockMvc.perform(put("/api/df-origin-destinations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDFOriginDestination)))
            .andExpect(status().isOk());

        // Validate the DFOriginDestination in the database
        List<DFOriginDestination> dFOriginDestinationList = dFOriginDestinationRepository.findAll();
        assertThat(dFOriginDestinationList).hasSize(databaseSizeBeforeUpdate);
        DFOriginDestination testDFOriginDestination = dFOriginDestinationList.get(dFOriginDestinationList.size() - 1);
        assertThat(testDFOriginDestination.getOrigAirport()).isEqualTo(UPDATED_ORIG_AIRPORT);
        assertThat(testDFOriginDestination.getDestAirport()).isEqualTo(UPDATED_DEST_AIRPORT);
        assertThat(testDFOriginDestination.getOrigCity()).isEqualTo(UPDATED_ORIG_CITY);
        assertThat(testDFOriginDestination.getDestCity()).isEqualTo(UPDATED_DEST_CITY);
    }

    @Test
    public void updateNonExistingDFOriginDestination() throws Exception {
        int databaseSizeBeforeUpdate = dFOriginDestinationRepository.findAll().size();

        // Create the DFOriginDestination

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDFOriginDestinationMockMvc.perform(put("/api/df-origin-destinations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dFOriginDestination)))
            .andExpect(status().isCreated());

        // Validate the DFOriginDestination in the database
        List<DFOriginDestination> dFOriginDestinationList = dFOriginDestinationRepository.findAll();
        assertThat(dFOriginDestinationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDFOriginDestination() throws Exception {
        // Initialize the database
        dFOriginDestinationRepository.save(dFOriginDestination);
        int databaseSizeBeforeDelete = dFOriginDestinationRepository.findAll().size();

        // Get the dFOriginDestination
        restDFOriginDestinationMockMvc.perform(delete("/api/df-origin-destinations/{id}", dFOriginDestination.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DFOriginDestination> dFOriginDestinationList = dFOriginDestinationRepository.findAll();
        assertThat(dFOriginDestinationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DFOriginDestination.class);
        DFOriginDestination dFOriginDestination1 = new DFOriginDestination();
        dFOriginDestination1.setId("id1");
        DFOriginDestination dFOriginDestination2 = new DFOriginDestination();
        dFOriginDestination2.setId(dFOriginDestination1.getId());
        assertThat(dFOriginDestination1).isEqualTo(dFOriginDestination2);
        dFOriginDestination2.setId("id2");
        assertThat(dFOriginDestination1).isNotEqualTo(dFOriginDestination2);
        dFOriginDestination1.setId(null);
        assertThat(dFOriginDestination1).isNotEqualTo(dFOriginDestination2);
    }
}
