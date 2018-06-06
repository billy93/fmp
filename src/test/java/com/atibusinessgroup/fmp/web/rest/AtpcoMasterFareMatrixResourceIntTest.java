package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.AtpcoMasterFareMatrix;
import com.atibusinessgroup.fmp.repository.AtpcoMasterFareMatrixRepository;
import com.atibusinessgroup.fmp.service.AtpcoMasterFareMatrixService;
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
 * Test class for the AtpcoMasterFareMatrixResource REST controller.
 *
 * @see AtpcoMasterFareMatrixResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class AtpcoMasterFareMatrixResourceIntTest {

    private static final String DEFAULT_CABIN_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_CABIN_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_CABIN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CABIN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FARE_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FARE_TYPE_CODE = "BBBBBBBBBB";

    @Autowired
    private AtpcoMasterFareMatrixRepository atpcoMasterFareMatrixRepository;

    @Autowired
    private AtpcoMasterFareMatrixService atpcoMasterFareMatrixService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAtpcoMasterFareMatrixMockMvc;

    private AtpcoMasterFareMatrix atpcoMasterFareMatrix;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AtpcoMasterFareMatrixResource atpcoMasterFareMatrixResource = new AtpcoMasterFareMatrixResource(atpcoMasterFareMatrixService);
        this.restAtpcoMasterFareMatrixMockMvc = MockMvcBuilders.standaloneSetup(atpcoMasterFareMatrixResource)
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
    public static AtpcoMasterFareMatrix createEntity() {
        AtpcoMasterFareMatrix atpcoMasterFareMatrix = new AtpcoMasterFareMatrix()
            .cabinService(DEFAULT_CABIN_SERVICE)
            .cabinCode(DEFAULT_CABIN_CODE)
            .fareTypeCode(DEFAULT_FARE_TYPE_CODE);
        return atpcoMasterFareMatrix;
    }

    @Before
    public void initTest() {
        atpcoMasterFareMatrixRepository.deleteAll();
        atpcoMasterFareMatrix = createEntity();
    }

    @Test
    public void createAtpcoMasterFareMatrix() throws Exception {
        int databaseSizeBeforeCreate = atpcoMasterFareMatrixRepository.findAll().size();

        // Create the AtpcoMasterFareMatrix
        restAtpcoMasterFareMatrixMockMvc.perform(post("/api/atpco-master-fare-matrices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atpcoMasterFareMatrix)))
            .andExpect(status().isCreated());

        // Validate the AtpcoMasterFareMatrix in the database
        List<AtpcoMasterFareMatrix> atpcoMasterFareMatrixList = atpcoMasterFareMatrixRepository.findAll();
        assertThat(atpcoMasterFareMatrixList).hasSize(databaseSizeBeforeCreate + 1);
        AtpcoMasterFareMatrix testAtpcoMasterFareMatrix = atpcoMasterFareMatrixList.get(atpcoMasterFareMatrixList.size() - 1);
        assertThat(testAtpcoMasterFareMatrix.getCabinService()).isEqualTo(DEFAULT_CABIN_SERVICE);
        assertThat(testAtpcoMasterFareMatrix.getCabinCode()).isEqualTo(DEFAULT_CABIN_CODE);
        assertThat(testAtpcoMasterFareMatrix.getFareTypeCode()).isEqualTo(DEFAULT_FARE_TYPE_CODE);
    }

    @Test
    public void createAtpcoMasterFareMatrixWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = atpcoMasterFareMatrixRepository.findAll().size();

        // Create the AtpcoMasterFareMatrix with an existing ID
        atpcoMasterFareMatrix.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAtpcoMasterFareMatrixMockMvc.perform(post("/api/atpco-master-fare-matrices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atpcoMasterFareMatrix)))
            .andExpect(status().isBadRequest());

        // Validate the AtpcoMasterFareMatrix in the database
        List<AtpcoMasterFareMatrix> atpcoMasterFareMatrixList = atpcoMasterFareMatrixRepository.findAll();
        assertThat(atpcoMasterFareMatrixList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAtpcoMasterFareMatrices() throws Exception {
        // Initialize the database
        atpcoMasterFareMatrixRepository.save(atpcoMasterFareMatrix);

        // Get all the atpcoMasterFareMatrixList
        restAtpcoMasterFareMatrixMockMvc.perform(get("/api/atpco-master-fare-matrices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(atpcoMasterFareMatrix.getId())))
            .andExpect(jsonPath("$.[*].cabinService").value(hasItem(DEFAULT_CABIN_SERVICE.toString())))
            .andExpect(jsonPath("$.[*].cabinCode").value(hasItem(DEFAULT_CABIN_CODE.toString())))
            .andExpect(jsonPath("$.[*].fareTypeCode").value(hasItem(DEFAULT_FARE_TYPE_CODE.toString())));
    }

    @Test
    public void getAtpcoMasterFareMatrix() throws Exception {
        // Initialize the database
        atpcoMasterFareMatrixRepository.save(atpcoMasterFareMatrix);

        // Get the atpcoMasterFareMatrix
        restAtpcoMasterFareMatrixMockMvc.perform(get("/api/atpco-master-fare-matrices/{id}", atpcoMasterFareMatrix.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(atpcoMasterFareMatrix.getId()))
            .andExpect(jsonPath("$.cabinService").value(DEFAULT_CABIN_SERVICE.toString()))
            .andExpect(jsonPath("$.cabinCode").value(DEFAULT_CABIN_CODE.toString()))
            .andExpect(jsonPath("$.fareTypeCode").value(DEFAULT_FARE_TYPE_CODE.toString()));
    }

    @Test
    public void getNonExistingAtpcoMasterFareMatrix() throws Exception {
        // Get the atpcoMasterFareMatrix
        restAtpcoMasterFareMatrixMockMvc.perform(get("/api/atpco-master-fare-matrices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAtpcoMasterFareMatrix() throws Exception {
        // Initialize the database
        atpcoMasterFareMatrixService.save(atpcoMasterFareMatrix);

        int databaseSizeBeforeUpdate = atpcoMasterFareMatrixRepository.findAll().size();

        // Update the atpcoMasterFareMatrix
        AtpcoMasterFareMatrix updatedAtpcoMasterFareMatrix = atpcoMasterFareMatrixRepository.findOne(atpcoMasterFareMatrix.getId());
        updatedAtpcoMasterFareMatrix
            .cabinService(UPDATED_CABIN_SERVICE)
            .cabinCode(UPDATED_CABIN_CODE)
            .fareTypeCode(UPDATED_FARE_TYPE_CODE);

        restAtpcoMasterFareMatrixMockMvc.perform(put("/api/atpco-master-fare-matrices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAtpcoMasterFareMatrix)))
            .andExpect(status().isOk());

        // Validate the AtpcoMasterFareMatrix in the database
        List<AtpcoMasterFareMatrix> atpcoMasterFareMatrixList = atpcoMasterFareMatrixRepository.findAll();
        assertThat(atpcoMasterFareMatrixList).hasSize(databaseSizeBeforeUpdate);
        AtpcoMasterFareMatrix testAtpcoMasterFareMatrix = atpcoMasterFareMatrixList.get(atpcoMasterFareMatrixList.size() - 1);
        assertThat(testAtpcoMasterFareMatrix.getCabinService()).isEqualTo(UPDATED_CABIN_SERVICE);
        assertThat(testAtpcoMasterFareMatrix.getCabinCode()).isEqualTo(UPDATED_CABIN_CODE);
        assertThat(testAtpcoMasterFareMatrix.getFareTypeCode()).isEqualTo(UPDATED_FARE_TYPE_CODE);
    }

    @Test
    public void updateNonExistingAtpcoMasterFareMatrix() throws Exception {
        int databaseSizeBeforeUpdate = atpcoMasterFareMatrixRepository.findAll().size();

        // Create the AtpcoMasterFareMatrix

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAtpcoMasterFareMatrixMockMvc.perform(put("/api/atpco-master-fare-matrices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atpcoMasterFareMatrix)))
            .andExpect(status().isCreated());

        // Validate the AtpcoMasterFareMatrix in the database
        List<AtpcoMasterFareMatrix> atpcoMasterFareMatrixList = atpcoMasterFareMatrixRepository.findAll();
        assertThat(atpcoMasterFareMatrixList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteAtpcoMasterFareMatrix() throws Exception {
        // Initialize the database
        atpcoMasterFareMatrixService.save(atpcoMasterFareMatrix);

        int databaseSizeBeforeDelete = atpcoMasterFareMatrixRepository.findAll().size();

        // Get the atpcoMasterFareMatrix
        restAtpcoMasterFareMatrixMockMvc.perform(delete("/api/atpco-master-fare-matrices/{id}", atpcoMasterFareMatrix.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AtpcoMasterFareMatrix> atpcoMasterFareMatrixList = atpcoMasterFareMatrixRepository.findAll();
        assertThat(atpcoMasterFareMatrixList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AtpcoMasterFareMatrix.class);
        AtpcoMasterFareMatrix atpcoMasterFareMatrix1 = new AtpcoMasterFareMatrix();
        atpcoMasterFareMatrix1.setId("id1");
        AtpcoMasterFareMatrix atpcoMasterFareMatrix2 = new AtpcoMasterFareMatrix();
        atpcoMasterFareMatrix2.setId(atpcoMasterFareMatrix1.getId());
        assertThat(atpcoMasterFareMatrix1).isEqualTo(atpcoMasterFareMatrix2);
        atpcoMasterFareMatrix2.setId("id2");
        assertThat(atpcoMasterFareMatrix1).isNotEqualTo(atpcoMasterFareMatrix2);
        atpcoMasterFareMatrix1.setId(null);
        assertThat(atpcoMasterFareMatrix1).isNotEqualTo(atpcoMasterFareMatrix2);
    }
}
