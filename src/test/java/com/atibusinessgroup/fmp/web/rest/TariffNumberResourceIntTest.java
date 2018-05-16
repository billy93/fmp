package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.TariffNumber;
import com.atibusinessgroup.fmp.repository.TariffNumberRepository;
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
 * Test class for the TariffNumberResource REST controller.
 *
 * @see TariffNumberResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class TariffNumberResourceIntTest {

    private static final String DEFAULT_TAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_TAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TAR_CD = "AAAAAAAAAA";
    private static final String UPDATED_TAR_CD = "BBBBBBBBBB";

    private static final String DEFAULT_GLOBAL = "AAAAAAAAAA";
    private static final String UPDATED_GLOBAL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private TariffNumberRepository tariffNumberRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTariffNumberMockMvc;

    private TariffNumber tariffNumber;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TariffNumberResource tariffNumberResource = new TariffNumberResource(tariffNumberRepository);
        this.restTariffNumberMockMvc = MockMvcBuilders.standaloneSetup(tariffNumberResource)
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
    public static TariffNumber createEntity() {
        TariffNumber tariffNumber = new TariffNumber()
            .tarNo(DEFAULT_TAR_NO)
            .tarCd(DEFAULT_TAR_CD)
            .global(DEFAULT_GLOBAL)
            .description(DEFAULT_DESCRIPTION);
        return tariffNumber;
    }

    @Before
    public void initTest() {
        tariffNumberRepository.deleteAll();
        tariffNumber = createEntity();
    }

    @Test
    public void createTariffNumber() throws Exception {
        int databaseSizeBeforeCreate = tariffNumberRepository.findAll().size();

        // Create the TariffNumber
        restTariffNumberMockMvc.perform(post("/api/tariff-numbers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tariffNumber)))
            .andExpect(status().isCreated());

        // Validate the TariffNumber in the database
        List<TariffNumber> tariffNumberList = tariffNumberRepository.findAll();
        assertThat(tariffNumberList).hasSize(databaseSizeBeforeCreate + 1);
        TariffNumber testTariffNumber = tariffNumberList.get(tariffNumberList.size() - 1);
        assertThat(testTariffNumber.getTarNo()).isEqualTo(DEFAULT_TAR_NO);
        assertThat(testTariffNumber.getTarCd()).isEqualTo(DEFAULT_TAR_CD);
        assertThat(testTariffNumber.getGlobal()).isEqualTo(DEFAULT_GLOBAL);
        assertThat(testTariffNumber.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createTariffNumberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tariffNumberRepository.findAll().size();

        // Create the TariffNumber with an existing ID
        tariffNumber.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restTariffNumberMockMvc.perform(post("/api/tariff-numbers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tariffNumber)))
            .andExpect(status().isBadRequest());

        // Validate the TariffNumber in the database
        List<TariffNumber> tariffNumberList = tariffNumberRepository.findAll();
        assertThat(tariffNumberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTariffNumbers() throws Exception {
        // Initialize the database
        tariffNumberRepository.save(tariffNumber);

        // Get all the tariffNumberList
        restTariffNumberMockMvc.perform(get("/api/tariff-numbers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tariffNumber.getId())))
            .andExpect(jsonPath("$.[*].tarNo").value(hasItem(DEFAULT_TAR_NO.toString())))
            .andExpect(jsonPath("$.[*].tarCd").value(hasItem(DEFAULT_TAR_CD.toString())))
            .andExpect(jsonPath("$.[*].global").value(hasItem(DEFAULT_GLOBAL.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    public void getTariffNumber() throws Exception {
        // Initialize the database
        tariffNumberRepository.save(tariffNumber);

        // Get the tariffNumber
        restTariffNumberMockMvc.perform(get("/api/tariff-numbers/{id}", tariffNumber.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tariffNumber.getId()))
            .andExpect(jsonPath("$.tarNo").value(DEFAULT_TAR_NO.toString()))
            .andExpect(jsonPath("$.tarCd").value(DEFAULT_TAR_CD.toString()))
            .andExpect(jsonPath("$.global").value(DEFAULT_GLOBAL.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingTariffNumber() throws Exception {
        // Get the tariffNumber
        restTariffNumberMockMvc.perform(get("/api/tariff-numbers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTariffNumber() throws Exception {
        // Initialize the database
        tariffNumberRepository.save(tariffNumber);
        int databaseSizeBeforeUpdate = tariffNumberRepository.findAll().size();

        // Update the tariffNumber
        TariffNumber updatedTariffNumber = tariffNumberRepository.findOne(tariffNumber.getId());
        updatedTariffNumber
            .tarNo(UPDATED_TAR_NO)
            .tarCd(UPDATED_TAR_CD)
            .global(UPDATED_GLOBAL)
            .description(UPDATED_DESCRIPTION);

        restTariffNumberMockMvc.perform(put("/api/tariff-numbers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTariffNumber)))
            .andExpect(status().isOk());

        // Validate the TariffNumber in the database
        List<TariffNumber> tariffNumberList = tariffNumberRepository.findAll();
        assertThat(tariffNumberList).hasSize(databaseSizeBeforeUpdate);
        TariffNumber testTariffNumber = tariffNumberList.get(tariffNumberList.size() - 1);
        assertThat(testTariffNumber.getTarNo()).isEqualTo(UPDATED_TAR_NO);
        assertThat(testTariffNumber.getTarCd()).isEqualTo(UPDATED_TAR_CD);
        assertThat(testTariffNumber.getGlobal()).isEqualTo(UPDATED_GLOBAL);
        assertThat(testTariffNumber.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingTariffNumber() throws Exception {
        int databaseSizeBeforeUpdate = tariffNumberRepository.findAll().size();

        // Create the TariffNumber

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTariffNumberMockMvc.perform(put("/api/tariff-numbers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tariffNumber)))
            .andExpect(status().isCreated());

        // Validate the TariffNumber in the database
        List<TariffNumber> tariffNumberList = tariffNumberRepository.findAll();
        assertThat(tariffNumberList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTariffNumber() throws Exception {
        // Initialize the database
        tariffNumberRepository.save(tariffNumber);
        int databaseSizeBeforeDelete = tariffNumberRepository.findAll().size();

        // Get the tariffNumber
        restTariffNumberMockMvc.perform(delete("/api/tariff-numbers/{id}", tariffNumber.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TariffNumber> tariffNumberList = tariffNumberRepository.findAll();
        assertThat(tariffNumberList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TariffNumber.class);
        TariffNumber tariffNumber1 = new TariffNumber();
        tariffNumber1.setId("id1");
        TariffNumber tariffNumber2 = new TariffNumber();
        tariffNumber2.setId(tariffNumber1.getId());
        assertThat(tariffNumber1).isEqualTo(tariffNumber2);
        tariffNumber2.setId("id2");
        assertThat(tariffNumber1).isNotEqualTo(tariffNumber2);
        tariffNumber1.setId(null);
        assertThat(tariffNumber1).isNotEqualTo(tariffNumber2);
    }
}
