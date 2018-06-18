package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.TariffNumberAddOn;
import com.atibusinessgroup.fmp.repository.TariffNumberAddOnRepository;
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
 * Test class for the TariffNumberAddOnResource REST controller.
 *
 * @see TariffNumberAddOnResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class TariffNumberAddOnResourceIntTest {

    private static final String DEFAULT_TAR_NO_ADD_ON = "AAAAAAAAAA";
    private static final String UPDATED_TAR_NO_ADD_ON = "BBBBBBBBBB";

    private static final String DEFAULT_TAR_CD_ADD_ON = "AAAAAAAAAA";
    private static final String UPDATED_TAR_CD_ADD_ON = "BBBBBBBBBB";

    private static final String DEFAULT_GLOBAL_ADD_ON = "AAAAAAAAAA";
    private static final String UPDATED_GLOBAL_ADD_ON = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_ADD_ON = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_ADD_ON = "BBBBBBBBBB";

    @Autowired
    private TariffNumberAddOnRepository tariffNumberAddOnRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTariffNumberAddOnMockMvc;

    private TariffNumberAddOn tariffNumberAddOn;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TariffNumberAddOnResource tariffNumberAddOnResource = new TariffNumberAddOnResource(tariffNumberAddOnRepository);
        this.restTariffNumberAddOnMockMvc = MockMvcBuilders.standaloneSetup(tariffNumberAddOnResource)
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
    public static TariffNumberAddOn createEntity() {
        TariffNumberAddOn tariffNumberAddOn = new TariffNumberAddOn()
            .tarNoAddOn(DEFAULT_TAR_NO_ADD_ON)
            .tarCdAddOn(DEFAULT_TAR_CD_ADD_ON)
            .globalAddOn(DEFAULT_GLOBAL_ADD_ON)
            .descriptionAddOn(DEFAULT_DESCRIPTION_ADD_ON);
        return tariffNumberAddOn;
    }

    @Before
    public void initTest() {
        tariffNumberAddOnRepository.deleteAll();
        tariffNumberAddOn = createEntity();
    }

    @Test
    public void createTariffNumberAddOn() throws Exception {
        int databaseSizeBeforeCreate = tariffNumberAddOnRepository.findAll().size();

        // Create the TariffNumberAddOn
        restTariffNumberAddOnMockMvc.perform(post("/api/tariff-number-add-ons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tariffNumberAddOn)))
            .andExpect(status().isCreated());

        // Validate the TariffNumberAddOn in the database
        List<TariffNumberAddOn> tariffNumberAddOnList = tariffNumberAddOnRepository.findAll();
        assertThat(tariffNumberAddOnList).hasSize(databaseSizeBeforeCreate + 1);
        TariffNumberAddOn testTariffNumberAddOn = tariffNumberAddOnList.get(tariffNumberAddOnList.size() - 1);
        assertThat(testTariffNumberAddOn.getTarNoAddOn()).isEqualTo(DEFAULT_TAR_NO_ADD_ON);
        assertThat(testTariffNumberAddOn.getTarCdAddOn()).isEqualTo(DEFAULT_TAR_CD_ADD_ON);
        assertThat(testTariffNumberAddOn.getGlobalAddOn()).isEqualTo(DEFAULT_GLOBAL_ADD_ON);
        assertThat(testTariffNumberAddOn.getDescriptionAddOn()).isEqualTo(DEFAULT_DESCRIPTION_ADD_ON);
    }

    @Test
    public void createTariffNumberAddOnWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tariffNumberAddOnRepository.findAll().size();

        // Create the TariffNumberAddOn with an existing ID
        tariffNumberAddOn.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restTariffNumberAddOnMockMvc.perform(post("/api/tariff-number-add-ons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tariffNumberAddOn)))
            .andExpect(status().isBadRequest());

        // Validate the TariffNumberAddOn in the database
        List<TariffNumberAddOn> tariffNumberAddOnList = tariffNumberAddOnRepository.findAll();
        assertThat(tariffNumberAddOnList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTariffNumberAddOns() throws Exception {
        // Initialize the database
        tariffNumberAddOnRepository.save(tariffNumberAddOn);

        // Get all the tariffNumberAddOnList
        restTariffNumberAddOnMockMvc.perform(get("/api/tariff-number-add-ons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tariffNumberAddOn.getId())))
            .andExpect(jsonPath("$.[*].tarNoAddOn").value(hasItem(DEFAULT_TAR_NO_ADD_ON.toString())))
            .andExpect(jsonPath("$.[*].tarCdAddOn").value(hasItem(DEFAULT_TAR_CD_ADD_ON.toString())))
            .andExpect(jsonPath("$.[*].globalAddOn").value(hasItem(DEFAULT_GLOBAL_ADD_ON.toString())))
            .andExpect(jsonPath("$.[*].descriptionAddOn").value(hasItem(DEFAULT_DESCRIPTION_ADD_ON.toString())));
    }

    @Test
    public void getTariffNumberAddOn() throws Exception {
        // Initialize the database
        tariffNumberAddOnRepository.save(tariffNumberAddOn);

        // Get the tariffNumberAddOn
        restTariffNumberAddOnMockMvc.perform(get("/api/tariff-number-add-ons/{id}", tariffNumberAddOn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tariffNumberAddOn.getId()))
            .andExpect(jsonPath("$.tarNoAddOn").value(DEFAULT_TAR_NO_ADD_ON.toString()))
            .andExpect(jsonPath("$.tarCdAddOn").value(DEFAULT_TAR_CD_ADD_ON.toString()))
            .andExpect(jsonPath("$.globalAddOn").value(DEFAULT_GLOBAL_ADD_ON.toString()))
            .andExpect(jsonPath("$.descriptionAddOn").value(DEFAULT_DESCRIPTION_ADD_ON.toString()));
    }

    @Test
    public void getNonExistingTariffNumberAddOn() throws Exception {
        // Get the tariffNumberAddOn
        restTariffNumberAddOnMockMvc.perform(get("/api/tariff-number-add-ons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTariffNumberAddOn() throws Exception {
        // Initialize the database
        tariffNumberAddOnRepository.save(tariffNumberAddOn);
        int databaseSizeBeforeUpdate = tariffNumberAddOnRepository.findAll().size();

        // Update the tariffNumberAddOn
        TariffNumberAddOn updatedTariffNumberAddOn = tariffNumberAddOnRepository.findOne(tariffNumberAddOn.getId());
        updatedTariffNumberAddOn
            .tarNoAddOn(UPDATED_TAR_NO_ADD_ON)
            .tarCdAddOn(UPDATED_TAR_CD_ADD_ON)
            .globalAddOn(UPDATED_GLOBAL_ADD_ON)
            .descriptionAddOn(UPDATED_DESCRIPTION_ADD_ON);

        restTariffNumberAddOnMockMvc.perform(put("/api/tariff-number-add-ons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTariffNumberAddOn)))
            .andExpect(status().isOk());

        // Validate the TariffNumberAddOn in the database
        List<TariffNumberAddOn> tariffNumberAddOnList = tariffNumberAddOnRepository.findAll();
        assertThat(tariffNumberAddOnList).hasSize(databaseSizeBeforeUpdate);
        TariffNumberAddOn testTariffNumberAddOn = tariffNumberAddOnList.get(tariffNumberAddOnList.size() - 1);
        assertThat(testTariffNumberAddOn.getTarNoAddOn()).isEqualTo(UPDATED_TAR_NO_ADD_ON);
        assertThat(testTariffNumberAddOn.getTarCdAddOn()).isEqualTo(UPDATED_TAR_CD_ADD_ON);
        assertThat(testTariffNumberAddOn.getGlobalAddOn()).isEqualTo(UPDATED_GLOBAL_ADD_ON);
        assertThat(testTariffNumberAddOn.getDescriptionAddOn()).isEqualTo(UPDATED_DESCRIPTION_ADD_ON);
    }

    @Test
    public void updateNonExistingTariffNumberAddOn() throws Exception {
        int databaseSizeBeforeUpdate = tariffNumberAddOnRepository.findAll().size();

        // Create the TariffNumberAddOn

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTariffNumberAddOnMockMvc.perform(put("/api/tariff-number-add-ons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tariffNumberAddOn)))
            .andExpect(status().isCreated());

        // Validate the TariffNumberAddOn in the database
        List<TariffNumberAddOn> tariffNumberAddOnList = tariffNumberAddOnRepository.findAll();
        assertThat(tariffNumberAddOnList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTariffNumberAddOn() throws Exception {
        // Initialize the database
        tariffNumberAddOnRepository.save(tariffNumberAddOn);
        int databaseSizeBeforeDelete = tariffNumberAddOnRepository.findAll().size();

        // Get the tariffNumberAddOn
        restTariffNumberAddOnMockMvc.perform(delete("/api/tariff-number-add-ons/{id}", tariffNumberAddOn.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TariffNumberAddOn> tariffNumberAddOnList = tariffNumberAddOnRepository.findAll();
        assertThat(tariffNumberAddOnList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TariffNumberAddOn.class);
        TariffNumberAddOn tariffNumberAddOn1 = new TariffNumberAddOn();
        tariffNumberAddOn1.setId("id1");
        TariffNumberAddOn tariffNumberAddOn2 = new TariffNumberAddOn();
        tariffNumberAddOn2.setId(tariffNumberAddOn1.getId());
        assertThat(tariffNumberAddOn1).isEqualTo(tariffNumberAddOn2);
        tariffNumberAddOn2.setId("id2");
        assertThat(tariffNumberAddOn1).isNotEqualTo(tariffNumberAddOn2);
        tariffNumberAddOn1.setId(null);
        assertThat(tariffNumberAddOn1).isNotEqualTo(tariffNumberAddOn2);
    }
}
