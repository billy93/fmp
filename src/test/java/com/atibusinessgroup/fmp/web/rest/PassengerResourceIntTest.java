package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterPassengerTypeCode;
import com.atibusinessgroup.fmp.repository.PassengerRepository;
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
 * Test class for the PassengerResource REST controller.
 *
 * @see PassengerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class PassengerResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restPassengerMockMvc;

    private AtpcoMasterPassengerTypeCode passenger;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PassengerResource passengerResource = new PassengerResource(passengerRepository);
        this.restPassengerMockMvc = MockMvcBuilders.standaloneSetup(passengerResource)
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
    public static AtpcoMasterPassengerTypeCode createEntity() {
        AtpcoMasterPassengerTypeCode passenger = new AtpcoMasterPassengerTypeCode();
//            .code(DEFAULT_CODE)
//            .name(DEFAULT_NAME)
//            .description(DEFAULT_DESCRIPTION);
        return passenger;
    }

    @Before
    public void initTest() {
        passengerRepository.deleteAll();
        passenger = createEntity();
    }

    @Test
    public void createPassenger() throws Exception {
        int databaseSizeBeforeCreate = passengerRepository.findAll().size();

        // Create the Passenger
        restPassengerMockMvc.perform(post("/api/passengers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passenger)))
            .andExpect(status().isCreated());

        // Validate the Passenger in the database
        List<AtpcoMasterPassengerTypeCode> passengerList = passengerRepository.findAll();
        assertThat(passengerList).hasSize(databaseSizeBeforeCreate + 1);
        AtpcoMasterPassengerTypeCode testPassenger = passengerList.get(passengerList.size() - 1);
//        assertThat(testPassenger.getCode()).isEqualTo(DEFAULT_CODE);
//        assertThat(testPassenger.getName()).isEqualTo(DEFAULT_NAME);
//        assertThat(testPassenger.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createPassengerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = passengerRepository.findAll().size();

        // Create the Passenger with an existing ID
        passenger.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restPassengerMockMvc.perform(post("/api/passengers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passenger)))
            .andExpect(status().isBadRequest());

        // Validate the Passenger in the database
        List<AtpcoMasterPassengerTypeCode> passengerList = passengerRepository.findAll();
        assertThat(passengerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllPassengers() throws Exception {
        // Initialize the database
        passengerRepository.save(passenger);

        // Get all the passengerList
        restPassengerMockMvc.perform(get("/api/passengers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(passenger.getId())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    public void getPassenger() throws Exception {
        // Initialize the database
        passengerRepository.save(passenger);

        // Get the passenger
        restPassengerMockMvc.perform(get("/api/passengers/{id}", passenger.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(passenger.getId()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingPassenger() throws Exception {
        // Get the passenger
        restPassengerMockMvc.perform(get("/api/passengers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePassenger() throws Exception {
        // Initialize the database
        passengerRepository.save(passenger);
        int databaseSizeBeforeUpdate = passengerRepository.findAll().size();

        // Update the passenger
        AtpcoMasterPassengerTypeCode updatedPassenger = passengerRepository.findOne(passenger.getId());
//        updatedPassenger
//            .code(UPDATED_CODE)
//            .name(UPDATED_NAME)
//            .description(UPDATED_DESCRIPTION);

        restPassengerMockMvc.perform(put("/api/passengers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPassenger)))
            .andExpect(status().isOk());

        // Validate the Passenger in the database
        List<AtpcoMasterPassengerTypeCode> passengerList = passengerRepository.findAll();
        assertThat(passengerList).hasSize(databaseSizeBeforeUpdate);
        AtpcoMasterPassengerTypeCode testPassenger = passengerList.get(passengerList.size() - 1);
//        assertThat(testPassenger.getCode()).isEqualTo(UPDATED_CODE);
//        assertThat(testPassenger.getName()).isEqualTo(UPDATED_NAME);
//        assertThat(testPassenger.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingPassenger() throws Exception {
        int databaseSizeBeforeUpdate = passengerRepository.findAll().size();

        // Create the Passenger

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPassengerMockMvc.perform(put("/api/passengers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passenger)))
            .andExpect(status().isCreated());

        // Validate the Passenger in the database
        List<AtpcoMasterPassengerTypeCode> passengerList = passengerRepository.findAll();
        assertThat(passengerList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deletePassenger() throws Exception {
        // Initialize the database
        passengerRepository.save(passenger);
        int databaseSizeBeforeDelete = passengerRepository.findAll().size();

        // Get the passenger
        restPassengerMockMvc.perform(delete("/api/passengers/{id}", passenger.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AtpcoMasterPassengerTypeCode> passengerList = passengerRepository.findAll();
        assertThat(passengerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AtpcoMasterPassengerTypeCode.class);
        AtpcoMasterPassengerTypeCode passenger1 = new AtpcoMasterPassengerTypeCode();
        passenger1.setId("id1");
        AtpcoMasterPassengerTypeCode passenger2 = new AtpcoMasterPassengerTypeCode();
        passenger2.setId(passenger1.getId());
        assertThat(passenger1).isEqualTo(passenger2);
        passenger2.setId("id2");
        assertThat(passenger1).isNotEqualTo(passenger2);
        passenger1.setId(null);
        assertThat(passenger1).isNotEqualTo(passenger2);
    }
}
