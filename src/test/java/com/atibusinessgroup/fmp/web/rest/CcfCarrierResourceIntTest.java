package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.CcfCarrier;
import com.atibusinessgroup.fmp.repository.CcfCarrierRepository;
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
 * Test class for the CcfCarrierResource REST controller.
 *
 * @see CcfCarrierResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class CcfCarrierResourceIntTest {

    private static final String DEFAULT_SVC_TAGS_PSGDOM = "AAAAAAAAAA";
    private static final String UPDATED_SVC_TAGS_PSGDOM = "BBBBBBBBBB";

    private static final String DEFAULT_FILLER = "AAAAAAAAAA";
    private static final String UPDATED_FILLER = "BBBBBBBBBB";

    private static final String DEFAULT_SVC_TAGS_CARDOM = "AAAAAAAAAA";
    private static final String UPDATED_SVC_TAGS_CARDOM = "BBBBBBBBBB";

    private static final String DEFAULT_CXR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CXR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REC_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_REC_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SVC_TAGS_PSGINT = "AAAAAAAAAA";
    private static final String UPDATED_SVC_TAGS_PSGINT = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_DATE = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_SVG_TAGS_CARINT = "AAAAAAAAAA";
    private static final String UPDATED_SVG_TAGS_CARINT = "BBBBBBBBBB";

    @Autowired
    private CcfCarrierRepository ccfCarrierRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCcfCarrierMockMvc;

    private CcfCarrier ccfCarrier;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CcfCarrierResource ccfCarrierResource = new CcfCarrierResource(ccfCarrierRepository);
        this.restCcfCarrierMockMvc = MockMvcBuilders.standaloneSetup(ccfCarrierResource)
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
    public static CcfCarrier createEntity() {
        CcfCarrier ccfCarrier = new CcfCarrier()
            .svcTagsPsgdom(DEFAULT_SVC_TAGS_PSGDOM)
            .filler(DEFAULT_FILLER)
            .svcTagsCardom(DEFAULT_SVC_TAGS_CARDOM)
            .cxrCode(DEFAULT_CXR_CODE)
            .recType(DEFAULT_REC_TYPE)
            .batchNumber(DEFAULT_BATCH_NUMBER)
            .svcTagsPsgint(DEFAULT_SVC_TAGS_PSGINT)
            .batchDate(DEFAULT_BATCH_DATE)
            .svgTagsCarint(DEFAULT_SVG_TAGS_CARINT);
        return ccfCarrier;
    }

    @Before
    public void initTest() {
        ccfCarrierRepository.deleteAll();
        ccfCarrier = createEntity();
    }

    @Test
    public void createCcfCarrier() throws Exception {
        int databaseSizeBeforeCreate = ccfCarrierRepository.findAll().size();

        // Create the CcfCarrier
        restCcfCarrierMockMvc.perform(post("/api/ccf-carriers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ccfCarrier)))
            .andExpect(status().isCreated());

        // Validate the CcfCarrier in the database
        List<CcfCarrier> ccfCarrierList = ccfCarrierRepository.findAll();
        assertThat(ccfCarrierList).hasSize(databaseSizeBeforeCreate + 1);
        CcfCarrier testCcfCarrier = ccfCarrierList.get(ccfCarrierList.size() - 1);
        assertThat(testCcfCarrier.getSvcTagsPsgdom()).isEqualTo(DEFAULT_SVC_TAGS_PSGDOM);
        assertThat(testCcfCarrier.getFiller()).isEqualTo(DEFAULT_FILLER);
        assertThat(testCcfCarrier.getSvcTagsCardom()).isEqualTo(DEFAULT_SVC_TAGS_CARDOM);
        assertThat(testCcfCarrier.getCxrCode()).isEqualTo(DEFAULT_CXR_CODE);
        assertThat(testCcfCarrier.getRecType()).isEqualTo(DEFAULT_REC_TYPE);
        assertThat(testCcfCarrier.getBatchNumber()).isEqualTo(DEFAULT_BATCH_NUMBER);
        assertThat(testCcfCarrier.getSvcTagsPsgint()).isEqualTo(DEFAULT_SVC_TAGS_PSGINT);
        assertThat(testCcfCarrier.getBatchDate()).isEqualTo(DEFAULT_BATCH_DATE);
        assertThat(testCcfCarrier.getSvgTagsCarint()).isEqualTo(DEFAULT_SVG_TAGS_CARINT);
    }

    @Test
    public void createCcfCarrierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ccfCarrierRepository.findAll().size();

        // Create the CcfCarrier with an existing ID
        ccfCarrier.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCcfCarrierMockMvc.perform(post("/api/ccf-carriers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ccfCarrier)))
            .andExpect(status().isBadRequest());

        // Validate the CcfCarrier in the database
        List<CcfCarrier> ccfCarrierList = ccfCarrierRepository.findAll();
        assertThat(ccfCarrierList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllCcfCarriers() throws Exception {
        // Initialize the database
        ccfCarrierRepository.save(ccfCarrier);

        // Get all the ccfCarrierList
        restCcfCarrierMockMvc.perform(get("/api/ccf-carriers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ccfCarrier.getId())))
            .andExpect(jsonPath("$.[*].svcTagsPsgdom").value(hasItem(DEFAULT_SVC_TAGS_PSGDOM.toString())))
            .andExpect(jsonPath("$.[*].filler").value(hasItem(DEFAULT_FILLER.toString())))
            .andExpect(jsonPath("$.[*].svcTagsCardom").value(hasItem(DEFAULT_SVC_TAGS_CARDOM.toString())))
            .andExpect(jsonPath("$.[*].cxrCode").value(hasItem(DEFAULT_CXR_CODE.toString())))
            .andExpect(jsonPath("$.[*].recType").value(hasItem(DEFAULT_REC_TYPE.toString())))
            .andExpect(jsonPath("$.[*].batchNumber").value(hasItem(DEFAULT_BATCH_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].svcTagsPsgint").value(hasItem(DEFAULT_SVC_TAGS_PSGINT.toString())))
            .andExpect(jsonPath("$.[*].batchDate").value(hasItem(DEFAULT_BATCH_DATE.toString())))
            .andExpect(jsonPath("$.[*].svgTagsCarint").value(hasItem(DEFAULT_SVG_TAGS_CARINT.toString())));
    }

    @Test
    public void getCcfCarrier() throws Exception {
        // Initialize the database
        ccfCarrierRepository.save(ccfCarrier);

        // Get the ccfCarrier
        restCcfCarrierMockMvc.perform(get("/api/ccf-carriers/{id}", ccfCarrier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ccfCarrier.getId()))
            .andExpect(jsonPath("$.svcTagsPsgdom").value(DEFAULT_SVC_TAGS_PSGDOM.toString()))
            .andExpect(jsonPath("$.filler").value(DEFAULT_FILLER.toString()))
            .andExpect(jsonPath("$.svcTagsCardom").value(DEFAULT_SVC_TAGS_CARDOM.toString()))
            .andExpect(jsonPath("$.cxrCode").value(DEFAULT_CXR_CODE.toString()))
            .andExpect(jsonPath("$.recType").value(DEFAULT_REC_TYPE.toString()))
            .andExpect(jsonPath("$.batchNumber").value(DEFAULT_BATCH_NUMBER.toString()))
            .andExpect(jsonPath("$.svcTagsPsgint").value(DEFAULT_SVC_TAGS_PSGINT.toString()))
            .andExpect(jsonPath("$.batchDate").value(DEFAULT_BATCH_DATE.toString()))
            .andExpect(jsonPath("$.svgTagsCarint").value(DEFAULT_SVG_TAGS_CARINT.toString()));
    }

    @Test
    public void getNonExistingCcfCarrier() throws Exception {
        // Get the ccfCarrier
        restCcfCarrierMockMvc.perform(get("/api/ccf-carriers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCcfCarrier() throws Exception {
        // Initialize the database
        ccfCarrierRepository.save(ccfCarrier);
        int databaseSizeBeforeUpdate = ccfCarrierRepository.findAll().size();

        // Update the ccfCarrier
        CcfCarrier updatedCcfCarrier = ccfCarrierRepository.findOne(ccfCarrier.getId());
        updatedCcfCarrier
            .svcTagsPsgdom(UPDATED_SVC_TAGS_PSGDOM)
            .filler(UPDATED_FILLER)
            .svcTagsCardom(UPDATED_SVC_TAGS_CARDOM)
            .cxrCode(UPDATED_CXR_CODE)
            .recType(UPDATED_REC_TYPE)
            .batchNumber(UPDATED_BATCH_NUMBER)
            .svcTagsPsgint(UPDATED_SVC_TAGS_PSGINT)
            .batchDate(UPDATED_BATCH_DATE)
            .svgTagsCarint(UPDATED_SVG_TAGS_CARINT);

        restCcfCarrierMockMvc.perform(put("/api/ccf-carriers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCcfCarrier)))
            .andExpect(status().isOk());

        // Validate the CcfCarrier in the database
        List<CcfCarrier> ccfCarrierList = ccfCarrierRepository.findAll();
        assertThat(ccfCarrierList).hasSize(databaseSizeBeforeUpdate);
        CcfCarrier testCcfCarrier = ccfCarrierList.get(ccfCarrierList.size() - 1);
        assertThat(testCcfCarrier.getSvcTagsPsgdom()).isEqualTo(UPDATED_SVC_TAGS_PSGDOM);
        assertThat(testCcfCarrier.getFiller()).isEqualTo(UPDATED_FILLER);
        assertThat(testCcfCarrier.getSvcTagsCardom()).isEqualTo(UPDATED_SVC_TAGS_CARDOM);
        assertThat(testCcfCarrier.getCxrCode()).isEqualTo(UPDATED_CXR_CODE);
        assertThat(testCcfCarrier.getRecType()).isEqualTo(UPDATED_REC_TYPE);
        assertThat(testCcfCarrier.getBatchNumber()).isEqualTo(UPDATED_BATCH_NUMBER);
        assertThat(testCcfCarrier.getSvcTagsPsgint()).isEqualTo(UPDATED_SVC_TAGS_PSGINT);
        assertThat(testCcfCarrier.getBatchDate()).isEqualTo(UPDATED_BATCH_DATE);
        assertThat(testCcfCarrier.getSvgTagsCarint()).isEqualTo(UPDATED_SVG_TAGS_CARINT);
    }

    @Test
    public void updateNonExistingCcfCarrier() throws Exception {
        int databaseSizeBeforeUpdate = ccfCarrierRepository.findAll().size();

        // Create the CcfCarrier

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCcfCarrierMockMvc.perform(put("/api/ccf-carriers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ccfCarrier)))
            .andExpect(status().isCreated());

        // Validate the CcfCarrier in the database
        List<CcfCarrier> ccfCarrierList = ccfCarrierRepository.findAll();
        assertThat(ccfCarrierList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteCcfCarrier() throws Exception {
        // Initialize the database
        ccfCarrierRepository.save(ccfCarrier);
        int databaseSizeBeforeDelete = ccfCarrierRepository.findAll().size();

        // Get the ccfCarrier
        restCcfCarrierMockMvc.perform(delete("/api/ccf-carriers/{id}", ccfCarrier.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CcfCarrier> ccfCarrierList = ccfCarrierRepository.findAll();
        assertThat(ccfCarrierList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CcfCarrier.class);
        CcfCarrier ccfCarrier1 = new CcfCarrier();
        ccfCarrier1.setId("id1");
        CcfCarrier ccfCarrier2 = new CcfCarrier();
        ccfCarrier2.setId(ccfCarrier1.getId());
        assertThat(ccfCarrier1).isEqualTo(ccfCarrier2);
        ccfCarrier2.setId("id2");
        assertThat(ccfCarrier1).isNotEqualTo(ccfCarrier2);
        ccfCarrier1.setId(null);
        assertThat(ccfCarrier1).isNotEqualTo(ccfCarrier2);
    }
}
