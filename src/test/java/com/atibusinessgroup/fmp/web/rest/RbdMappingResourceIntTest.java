package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.RbdMapping;
import com.atibusinessgroup.fmp.repository.RbdMappingRepository;
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
 * Test class for the RbdMappingResource REST controller.
 *
 * @see RbdMappingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class RbdMappingResourceIntTest {

    private static final String DEFAULT_OAL_CXR = "AAAAAAAAAA";
    private static final String UPDATED_OAL_CXR = "BBBBBBBBBB";

    private static final String DEFAULT_OAL_CABIN = "AAAAAAAAAA";
    private static final String UPDATED_OAL_CABIN = "BBBBBBBBBB";

    private static final String DEFAULT_OWN_CABIN = "AAAAAAAAAA";
    private static final String UPDATED_OWN_CABIN = "BBBBBBBBBB";

    private static final String DEFAULT_OAL_RBD = "AAAAAAAAAA";
    private static final String UPDATED_OAL_RBD = "BBBBBBBBBB";

    private static final String DEFAULT_OWN_RBD = "AAAAAAAAAA";
    private static final String UPDATED_OWN_RBD = "BBBBBBBBBB";

    @Autowired
    private RbdMappingRepository rbdMappingRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restRbdMappingMockMvc;

    private RbdMapping rbdMapping;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RbdMappingResource rbdMappingResource = new RbdMappingResource(rbdMappingRepository);
        this.restRbdMappingMockMvc = MockMvcBuilders.standaloneSetup(rbdMappingResource)
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
    public static RbdMapping createEntity() {
        RbdMapping rbdMapping = new RbdMapping()
            .oalCxr(DEFAULT_OAL_CXR)
            .oalCabin(DEFAULT_OAL_CABIN)
            .ownCabin(DEFAULT_OWN_CABIN)
            .oalRbd(DEFAULT_OAL_RBD)
            .ownRbd(DEFAULT_OWN_RBD);
        return rbdMapping;
    }

    @Before
    public void initTest() {
        rbdMappingRepository.deleteAll();
        rbdMapping = createEntity();
    }

    @Test
    public void createRbdMapping() throws Exception {
        int databaseSizeBeforeCreate = rbdMappingRepository.findAll().size();

        // Create the RbdMapping
        restRbdMappingMockMvc.perform(post("/api/rbd-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rbdMapping)))
            .andExpect(status().isCreated());

        // Validate the RbdMapping in the database
        List<RbdMapping> rbdMappingList = rbdMappingRepository.findAll();
        assertThat(rbdMappingList).hasSize(databaseSizeBeforeCreate + 1);
        RbdMapping testRbdMapping = rbdMappingList.get(rbdMappingList.size() - 1);
        assertThat(testRbdMapping.getOalCxr()).isEqualTo(DEFAULT_OAL_CXR);
        assertThat(testRbdMapping.getOalCabin()).isEqualTo(DEFAULT_OAL_CABIN);
        assertThat(testRbdMapping.getOwnCabin()).isEqualTo(DEFAULT_OWN_CABIN);
        assertThat(testRbdMapping.getOalRbd()).isEqualTo(DEFAULT_OAL_RBD);
        assertThat(testRbdMapping.getOwnRbd()).isEqualTo(DEFAULT_OWN_RBD);
    }

    @Test
    public void createRbdMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rbdMappingRepository.findAll().size();

        // Create the RbdMapping with an existing ID
        rbdMapping.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restRbdMappingMockMvc.perform(post("/api/rbd-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rbdMapping)))
            .andExpect(status().isBadRequest());

        // Validate the RbdMapping in the database
        List<RbdMapping> rbdMappingList = rbdMappingRepository.findAll();
        assertThat(rbdMappingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllRbdMappings() throws Exception {
        // Initialize the database
        rbdMappingRepository.save(rbdMapping);

        // Get all the rbdMappingList
        restRbdMappingMockMvc.perform(get("/api/rbd-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rbdMapping.getId())))
            .andExpect(jsonPath("$.[*].oalCxr").value(hasItem(DEFAULT_OAL_CXR.toString())))
            .andExpect(jsonPath("$.[*].oalCabin").value(hasItem(DEFAULT_OAL_CABIN.toString())))
            .andExpect(jsonPath("$.[*].ownCabin").value(hasItem(DEFAULT_OWN_CABIN.toString())))
            .andExpect(jsonPath("$.[*].oalRbd").value(hasItem(DEFAULT_OAL_RBD.toString())))
            .andExpect(jsonPath("$.[*].ownRbd").value(hasItem(DEFAULT_OWN_RBD.toString())));
    }

    @Test
    public void getRbdMapping() throws Exception {
        // Initialize the database
        rbdMappingRepository.save(rbdMapping);

        // Get the rbdMapping
        restRbdMappingMockMvc.perform(get("/api/rbd-mappings/{id}", rbdMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rbdMapping.getId()))
            .andExpect(jsonPath("$.oalCxr").value(DEFAULT_OAL_CXR.toString()))
            .andExpect(jsonPath("$.oalCabin").value(DEFAULT_OAL_CABIN.toString()))
            .andExpect(jsonPath("$.ownCabin").value(DEFAULT_OWN_CABIN.toString()))
            .andExpect(jsonPath("$.oalRbd").value(DEFAULT_OAL_RBD.toString()))
            .andExpect(jsonPath("$.ownRbd").value(DEFAULT_OWN_RBD.toString()));
    }

    @Test
    public void getNonExistingRbdMapping() throws Exception {
        // Get the rbdMapping
        restRbdMappingMockMvc.perform(get("/api/rbd-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRbdMapping() throws Exception {
        // Initialize the database
        rbdMappingRepository.save(rbdMapping);
        int databaseSizeBeforeUpdate = rbdMappingRepository.findAll().size();

        // Update the rbdMapping
        RbdMapping updatedRbdMapping = rbdMappingRepository.findOne(rbdMapping.getId());
        updatedRbdMapping
            .oalCxr(UPDATED_OAL_CXR)
            .oalCabin(UPDATED_OAL_CABIN)
            .ownCabin(UPDATED_OWN_CABIN)
            .oalRbd(UPDATED_OAL_RBD)
            .ownRbd(UPDATED_OWN_RBD);

        restRbdMappingMockMvc.perform(put("/api/rbd-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRbdMapping)))
            .andExpect(status().isOk());

        // Validate the RbdMapping in the database
        List<RbdMapping> rbdMappingList = rbdMappingRepository.findAll();
        assertThat(rbdMappingList).hasSize(databaseSizeBeforeUpdate);
        RbdMapping testRbdMapping = rbdMappingList.get(rbdMappingList.size() - 1);
        assertThat(testRbdMapping.getOalCxr()).isEqualTo(UPDATED_OAL_CXR);
        assertThat(testRbdMapping.getOalCabin()).isEqualTo(UPDATED_OAL_CABIN);
        assertThat(testRbdMapping.getOwnCabin()).isEqualTo(UPDATED_OWN_CABIN);
        assertThat(testRbdMapping.getOalRbd()).isEqualTo(UPDATED_OAL_RBD);
        assertThat(testRbdMapping.getOwnRbd()).isEqualTo(UPDATED_OWN_RBD);
    }

    @Test
    public void updateNonExistingRbdMapping() throws Exception {
        int databaseSizeBeforeUpdate = rbdMappingRepository.findAll().size();

        // Create the RbdMapping

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRbdMappingMockMvc.perform(put("/api/rbd-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rbdMapping)))
            .andExpect(status().isCreated());

        // Validate the RbdMapping in the database
        List<RbdMapping> rbdMappingList = rbdMappingRepository.findAll();
        assertThat(rbdMappingList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteRbdMapping() throws Exception {
        // Initialize the database
        rbdMappingRepository.save(rbdMapping);
        int databaseSizeBeforeDelete = rbdMappingRepository.findAll().size();

        // Get the rbdMapping
        restRbdMappingMockMvc.perform(delete("/api/rbd-mappings/{id}", rbdMapping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RbdMapping> rbdMappingList = rbdMappingRepository.findAll();
        assertThat(rbdMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RbdMapping.class);
        RbdMapping rbdMapping1 = new RbdMapping();
        rbdMapping1.setId("id1");
        RbdMapping rbdMapping2 = new RbdMapping();
        rbdMapping2.setId(rbdMapping1.getId());
        assertThat(rbdMapping1).isEqualTo(rbdMapping2);
        rbdMapping2.setId("id2");
        assertThat(rbdMapping1).isNotEqualTo(rbdMapping2);
        rbdMapping1.setId(null);
        assertThat(rbdMapping1).isNotEqualTo(rbdMapping2);
    }
}
