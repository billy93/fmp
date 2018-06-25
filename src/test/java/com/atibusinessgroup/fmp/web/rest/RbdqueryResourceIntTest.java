package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.RbdQuery;
import com.atibusinessgroup.fmp.repository.RbdqueryRepository;
import com.atibusinessgroup.fmp.service.RbdqueryService;
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
 * Test class for the RbdqueryResource REST controller.
 *
 * @see RbdQueryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class RbdqueryResourceIntTest {

    private static final Integer DEFAULT_RECORD_BATCH = 1;
    private static final Integer UPDATED_RECORD_BATCH = 2;

    private static final Integer DEFAULT_RECORD_SEQUENCE = 1;
    private static final Integer UPDATED_RECORD_SEQUENCE = 2;

    private static final Integer DEFAULT_REC_TYPE = 1;
    private static final Integer UPDATED_REC_TYPE = 2;

    private static final String DEFAULT_ACTION = "AAAAAAAAAA";
    private static final String UPDATED_ACTION = "BBBBBBBBBB";

    @Autowired
    private RbdqueryRepository rbdqueryRepository;

    @Autowired
    private RbdqueryService rbdqueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restRbdqueryMockMvc;

    private RbdQuery rbdquery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RbdQueryResource rbdqueryResource = new RbdQueryResource(rbdqueryService);
        this.restRbdqueryMockMvc = MockMvcBuilders.standaloneSetup(rbdqueryResource)
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
    public static RbdQuery createEntity() {
        RbdQuery rbdquery = new RbdQuery()
            .recordBatch(DEFAULT_RECORD_BATCH)
            .recordSequence(DEFAULT_RECORD_SEQUENCE)
            .recType(DEFAULT_REC_TYPE)
            .action(DEFAULT_ACTION);
        return rbdquery;
    }

    @Before
    public void initTest() {
        rbdqueryRepository.deleteAll();
        rbdquery = createEntity();
    }

    @Test
    public void createRbdquery() throws Exception {
        int databaseSizeBeforeCreate = rbdqueryRepository.findAll().size();

        // Create the Rbdquery
        restRbdqueryMockMvc.perform(post("/api/rbdqueries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rbdquery)))
            .andExpect(status().isCreated());

        // Validate the Rbdquery in the database
        List<RbdQuery> rbdqueryList = rbdqueryRepository.findAll();
        assertThat(rbdqueryList).hasSize(databaseSizeBeforeCreate + 1);
        RbdQuery testRbdquery = rbdqueryList.get(rbdqueryList.size() - 1);
        assertThat(testRbdquery.getRecordBatch()).isEqualTo(DEFAULT_RECORD_BATCH);
        assertThat(testRbdquery.getRecordSequence()).isEqualTo(DEFAULT_RECORD_SEQUENCE);
        assertThat(testRbdquery.getRecType()).isEqualTo(DEFAULT_REC_TYPE);
        assertThat(testRbdquery.getAction()).isEqualTo(DEFAULT_ACTION);
    }

    @Test
    public void createRbdqueryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rbdqueryRepository.findAll().size();

        // Create the Rbdquery with an existing ID
        rbdquery.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restRbdqueryMockMvc.perform(post("/api/rbdqueries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rbdquery)))
            .andExpect(status().isBadRequest());

        // Validate the Rbdquery in the database
        List<RbdQuery> rbdqueryList = rbdqueryRepository.findAll();
        assertThat(rbdqueryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllRbdqueries() throws Exception {
        // Initialize the database
        rbdqueryRepository.save(rbdquery);

        // Get all the rbdqueryList
        restRbdqueryMockMvc.perform(get("/api/rbdqueries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rbdquery.getId())))
            .andExpect(jsonPath("$.[*].recordBatch").value(hasItem(DEFAULT_RECORD_BATCH)))
            .andExpect(jsonPath("$.[*].recordSequence").value(hasItem(DEFAULT_RECORD_SEQUENCE)))
            .andExpect(jsonPath("$.[*].recType").value(hasItem(DEFAULT_REC_TYPE)))
            .andExpect(jsonPath("$.[*].action").value(hasItem(DEFAULT_ACTION.toString())));
    }

    @Test
    public void getRbdquery() throws Exception {
        // Initialize the database
        rbdqueryRepository.save(rbdquery);

        // Get the rbdquery
        restRbdqueryMockMvc.perform(get("/api/rbdqueries/{id}", rbdquery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rbdquery.getId()))
            .andExpect(jsonPath("$.recordBatch").value(DEFAULT_RECORD_BATCH))
            .andExpect(jsonPath("$.recordSequence").value(DEFAULT_RECORD_SEQUENCE))
            .andExpect(jsonPath("$.recType").value(DEFAULT_REC_TYPE))
            .andExpect(jsonPath("$.action").value(DEFAULT_ACTION.toString()));
    }

    @Test
    public void getNonExistingRbdquery() throws Exception {
        // Get the rbdquery
        restRbdqueryMockMvc.perform(get("/api/rbdqueries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRbdquery() throws Exception {
        // Initialize the database
        rbdqueryService.save(rbdquery);

        int databaseSizeBeforeUpdate = rbdqueryRepository.findAll().size();

        // Update the rbdquery
        RbdQuery updatedRbdquery = rbdqueryRepository.findOne(rbdquery.getId());
        updatedRbdquery
            .recordBatch(UPDATED_RECORD_BATCH)
            .recordSequence(UPDATED_RECORD_SEQUENCE)
            .recType(UPDATED_REC_TYPE)
            .action(UPDATED_ACTION);

        restRbdqueryMockMvc.perform(put("/api/rbdqueries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRbdquery)))
            .andExpect(status().isOk());

        // Validate the Rbdquery in the database
        List<RbdQuery> rbdqueryList = rbdqueryRepository.findAll();
        assertThat(rbdqueryList).hasSize(databaseSizeBeforeUpdate);
        RbdQuery testRbdquery = rbdqueryList.get(rbdqueryList.size() - 1);
        assertThat(testRbdquery.getRecordBatch()).isEqualTo(UPDATED_RECORD_BATCH);
        assertThat(testRbdquery.getRecordSequence()).isEqualTo(UPDATED_RECORD_SEQUENCE);
        assertThat(testRbdquery.getRecType()).isEqualTo(UPDATED_REC_TYPE);
        assertThat(testRbdquery.getAction()).isEqualTo(UPDATED_ACTION);
    }

    @Test
    public void updateNonExistingRbdquery() throws Exception {
        int databaseSizeBeforeUpdate = rbdqueryRepository.findAll().size();

        // Create the Rbdquery

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRbdqueryMockMvc.perform(put("/api/rbdqueries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rbdquery)))
            .andExpect(status().isCreated());

        // Validate the Rbdquery in the database
        List<RbdQuery> rbdqueryList = rbdqueryRepository.findAll();
        assertThat(rbdqueryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteRbdquery() throws Exception {
        // Initialize the database
        rbdqueryService.save(rbdquery);

        int databaseSizeBeforeDelete = rbdqueryRepository.findAll().size();

        // Get the rbdquery
        restRbdqueryMockMvc.perform(delete("/api/rbdqueries/{id}", rbdquery.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RbdQuery> rbdqueryList = rbdqueryRepository.findAll();
        assertThat(rbdqueryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RbdQuery.class);
        RbdQuery rbdquery1 = new RbdQuery();
        rbdquery1.setId("id1");
        RbdQuery rbdquery2 = new RbdQuery();
        rbdquery2.setId(rbdquery1.getId());
        assertThat(rbdquery1).isEqualTo(rbdquery2);
        rbdquery2.setId("id2");
        assertThat(rbdquery1).isNotEqualTo(rbdquery2);
        rbdquery1.setId(null);
        assertThat(rbdquery1).isNotEqualTo(rbdquery2);
    }
}
