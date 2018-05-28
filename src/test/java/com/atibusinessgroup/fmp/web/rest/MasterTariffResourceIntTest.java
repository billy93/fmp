package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.MasterTariffDomestic;
import com.atibusinessgroup.fmp.repository.MasterTariffDomesticRepository;
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
 * Test class for the MasterTariffResource REST controller.
 *
 * @see MasterTariffDomesticResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class MasterTariffResourceIntTest {

    private static final String DEFAULT_GFS_GLOBAL_AREA = "AAAAAAAAAA";
    private static final String UPDATED_GFS_GLOBAL_AREA = "BBBBBBBBBB";

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

    @Autowired
    private MasterTariffDomesticRepository masterTariffRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restMasterTariffMockMvc;

    private MasterTariffDomestic masterTariff;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MasterTariffDomesticResource masterTariffResource = new MasterTariffDomesticResource(masterTariffRepository);
        this.restMasterTariffMockMvc = MockMvcBuilders.standaloneSetup(masterTariffResource)
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
//    public static MasterTariffDomestic createEntity() {
//        MasterTariffDomestic masterTariff = new MasterTariffDomestic()
//            .getGfsGlobalArea(DEFAULT_GFS_GLOBAL_AREA)
//            .area(DEFAULT_AREA);
//        return masterTariff;
//    }

//    @Before
//    public void initTest() {
//        masterTariffRepository.deleteAll();
//        masterTariff = createEntity();
//    }

    @Test
    public void createMasterTariff() throws Exception {
        int databaseSizeBeforeCreate = masterTariffRepository.findAll().size();

        // Create the MasterTariff
        restMasterTariffMockMvc.perform(post("/api/master-tariffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masterTariff)))
            .andExpect(status().isCreated());

        // Validate the MasterTariff in the database
        List<MasterTariffDomestic> masterTariffList = masterTariffRepository.findAll();
        assertThat(masterTariffList).hasSize(databaseSizeBeforeCreate + 1);
        MasterTariffDomestic testMasterTariff = masterTariffList.get(masterTariffList.size() - 1);
        assertThat(testMasterTariff.getGfsGlobalArea()).isEqualTo(DEFAULT_GFS_GLOBAL_AREA);
        assertThat(testMasterTariff.getArea()).isEqualTo(DEFAULT_AREA);
    }

    @Test
    public void createMasterTariffWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = masterTariffRepository.findAll().size();

        // Create the MasterTariff with an existing ID
        masterTariff.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restMasterTariffMockMvc.perform(post("/api/master-tariffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masterTariff)))
            .andExpect(status().isBadRequest());

        // Validate the MasterTariff in the database
        List<MasterTariffDomestic> masterTariffList = masterTariffRepository.findAll();
        assertThat(masterTariffList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllMasterTariffs() throws Exception {
        // Initialize the database
        masterTariffRepository.save(masterTariff);

        // Get all the masterTariffList
        restMasterTariffMockMvc.perform(get("/api/master-tariffs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(masterTariff.getId())))
            .andExpect(jsonPath("$.[*].gfsGlobalArea").value(hasItem(DEFAULT_GFS_GLOBAL_AREA.toString())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())));
    }

    @Test
    public void getMasterTariff() throws Exception {
        // Initialize the database
        masterTariffRepository.save(masterTariff);

        // Get the masterTariff
        restMasterTariffMockMvc.perform(get("/api/master-tariffs/{id}", masterTariff.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(masterTariff.getId()))
            .andExpect(jsonPath("$.gfsGlobalArea").value(DEFAULT_GFS_GLOBAL_AREA.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.toString()));
    }

    @Test
    public void getNonExistingMasterTariff() throws Exception {
        // Get the masterTariff
        restMasterTariffMockMvc.perform(get("/api/master-tariffs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMasterTariff() throws Exception {
        // Initialize the database
//        masterTariffRepository.save(masterTariff);
//        int databaseSizeBeforeUpdate = masterTariffRepository.findAll().size();
//
//        // Update the masterTariff
//        MasterTariffDomestic updatedMasterTariff = masterTariffRepository.findOne(masterTariff.getId());
//        updatedMasterTariff
//            .gfsGlobalArea(UPDATED_GFS_GLOBAL_AREA)
//            .area(UPDATED_AREA);
//
//        restMasterTariffMockMvc.perform(put("/api/master-tariffs")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(updatedMasterTariff)))
//            .andExpect(status().isOk());
//
//        // Validate the MasterTariff in the database
//        List<MasterTariffDomestic> masterTariffList = masterTariffRepository.findAll();
//        assertThat(masterTariffList).hasSize(databaseSizeBeforeUpdate);
//        MasterTariffDomestic testMasterTariff = masterTariffList.get(masterTariffList.size() - 1);
//        assertThat(testMasterTariff.getGfsGlobalArea()).isEqualTo(UPDATED_GFS_GLOBAL_AREA);
//        assertThat(testMasterTariff.getArea()).isEqualTo(UPDATED_AREA);
    }

    @Test
    public void updateNonExistingMasterTariff() throws Exception {
        int databaseSizeBeforeUpdate = masterTariffRepository.findAll().size();

        // Create the MasterTariff

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMasterTariffMockMvc.perform(put("/api/master-tariffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masterTariff)))
            .andExpect(status().isCreated());

        // Validate the MasterTariff in the database
        List<MasterTariffDomestic> masterTariffList = masterTariffRepository.findAll();
        assertThat(masterTariffList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteMasterTariff() throws Exception {
        // Initialize the database
        masterTariffRepository.save(masterTariff);
        int databaseSizeBeforeDelete = masterTariffRepository.findAll().size();

        // Get the masterTariff
        restMasterTariffMockMvc.perform(delete("/api/master-tariffs/{id}", masterTariff.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MasterTariffDomestic> masterTariffList = masterTariffRepository.findAll();
        assertThat(masterTariffList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasterTariffDomestic.class);
        MasterTariffDomestic masterTariff1 = new MasterTariffDomestic();
        masterTariff1.setId("id1");
        MasterTariffDomestic masterTariff2 = new MasterTariffDomestic();
        masterTariff2.setId(masterTariff1.getId());
        assertThat(masterTariff1).isEqualTo(masterTariff2);
        masterTariff2.setId("id2");
        assertThat(masterTariff1).isNotEqualTo(masterTariff2);
        masterTariff1.setId(null);
        assertThat(masterTariff1).isNotEqualTo(masterTariff2);
    }
}
