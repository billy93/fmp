package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.RbdColorMapping;
import com.atibusinessgroup.fmp.repository.RbdColorMappingRepository;
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
 * Test class for the RbdColorMappingResource REST controller.
 *
 * @see RbdColorMappingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class RbdColorMappingResourceIntTest {

    private static final String DEFAULT_RBD = "AAAAAAAAAA";
    private static final String UPDATED_RBD = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    @Autowired
    private RbdColorMappingRepository rbdColorMappingRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restRbdColorMappingMockMvc;

    private RbdColorMapping rbdColorMapping;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RbdColorMappingResource rbdColorMappingResource = new RbdColorMappingResource(rbdColorMappingRepository);
        this.restRbdColorMappingMockMvc = MockMvcBuilders.standaloneSetup(rbdColorMappingResource)
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
    public static RbdColorMapping createEntity() {
        RbdColorMapping rbdColorMapping = new RbdColorMapping()
            .rbd(DEFAULT_RBD)
            .color(DEFAULT_COLOR);
        return rbdColorMapping;
    }

    @Before
    public void initTest() {
        rbdColorMappingRepository.deleteAll();
        rbdColorMapping = createEntity();
    }

    @Test
    public void createRbdColorMapping() throws Exception {
        int databaseSizeBeforeCreate = rbdColorMappingRepository.findAll().size();

        // Create the RbdColorMapping
        restRbdColorMappingMockMvc.perform(post("/api/rbd-color-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rbdColorMapping)))
            .andExpect(status().isCreated());

        // Validate the RbdColorMapping in the database
        List<RbdColorMapping> rbdColorMappingList = rbdColorMappingRepository.findAll();
        assertThat(rbdColorMappingList).hasSize(databaseSizeBeforeCreate + 1);
        RbdColorMapping testRbdColorMapping = rbdColorMappingList.get(rbdColorMappingList.size() - 1);
        assertThat(testRbdColorMapping.getRbd()).isEqualTo(DEFAULT_RBD);
        assertThat(testRbdColorMapping.getColor()).isEqualTo(DEFAULT_COLOR);
    }

    @Test
    public void createRbdColorMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rbdColorMappingRepository.findAll().size();

        // Create the RbdColorMapping with an existing ID
        rbdColorMapping.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restRbdColorMappingMockMvc.perform(post("/api/rbd-color-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rbdColorMapping)))
            .andExpect(status().isBadRequest());

        // Validate the RbdColorMapping in the database
        List<RbdColorMapping> rbdColorMappingList = rbdColorMappingRepository.findAll();
        assertThat(rbdColorMappingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllRbdColorMappings() throws Exception {
        // Initialize the database
        rbdColorMappingRepository.save(rbdColorMapping);

        // Get all the rbdColorMappingList
        restRbdColorMappingMockMvc.perform(get("/api/rbd-color-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rbdColorMapping.getId())))
            .andExpect(jsonPath("$.[*].rbd").value(hasItem(DEFAULT_RBD.toString())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR.toString())));
    }

    @Test
    public void getRbdColorMapping() throws Exception {
        // Initialize the database
        rbdColorMappingRepository.save(rbdColorMapping);

        // Get the rbdColorMapping
        restRbdColorMappingMockMvc.perform(get("/api/rbd-color-mappings/{id}", rbdColorMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rbdColorMapping.getId()))
            .andExpect(jsonPath("$.rbd").value(DEFAULT_RBD.toString()))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR.toString()));
    }

    @Test
    public void getNonExistingRbdColorMapping() throws Exception {
        // Get the rbdColorMapping
        restRbdColorMappingMockMvc.perform(get("/api/rbd-color-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRbdColorMapping() throws Exception {
        // Initialize the database
        rbdColorMappingRepository.save(rbdColorMapping);
        int databaseSizeBeforeUpdate = rbdColorMappingRepository.findAll().size();

        // Update the rbdColorMapping
        RbdColorMapping updatedRbdColorMapping = rbdColorMappingRepository.findOne(rbdColorMapping.getId());
        updatedRbdColorMapping
            .rbd(UPDATED_RBD)
            .color(UPDATED_COLOR);

        restRbdColorMappingMockMvc.perform(put("/api/rbd-color-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRbdColorMapping)))
            .andExpect(status().isOk());

        // Validate the RbdColorMapping in the database
        List<RbdColorMapping> rbdColorMappingList = rbdColorMappingRepository.findAll();
        assertThat(rbdColorMappingList).hasSize(databaseSizeBeforeUpdate);
        RbdColorMapping testRbdColorMapping = rbdColorMappingList.get(rbdColorMappingList.size() - 1);
        assertThat(testRbdColorMapping.getRbd()).isEqualTo(UPDATED_RBD);
        assertThat(testRbdColorMapping.getColor()).isEqualTo(UPDATED_COLOR);
    }

    @Test
    public void updateNonExistingRbdColorMapping() throws Exception {
        int databaseSizeBeforeUpdate = rbdColorMappingRepository.findAll().size();

        // Create the RbdColorMapping

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRbdColorMappingMockMvc.perform(put("/api/rbd-color-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rbdColorMapping)))
            .andExpect(status().isCreated());

        // Validate the RbdColorMapping in the database
        List<RbdColorMapping> rbdColorMappingList = rbdColorMappingRepository.findAll();
        assertThat(rbdColorMappingList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteRbdColorMapping() throws Exception {
        // Initialize the database
        rbdColorMappingRepository.save(rbdColorMapping);
        int databaseSizeBeforeDelete = rbdColorMappingRepository.findAll().size();

        // Get the rbdColorMapping
        restRbdColorMappingMockMvc.perform(delete("/api/rbd-color-mappings/{id}", rbdColorMapping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RbdColorMapping> rbdColorMappingList = rbdColorMappingRepository.findAll();
        assertThat(rbdColorMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RbdColorMapping.class);
        RbdColorMapping rbdColorMapping1 = new RbdColorMapping();
        rbdColorMapping1.setId("id1");
        RbdColorMapping rbdColorMapping2 = new RbdColorMapping();
        rbdColorMapping2.setId(rbdColorMapping1.getId());
        assertThat(rbdColorMapping1).isEqualTo(rbdColorMapping2);
        rbdColorMapping2.setId("id2");
        assertThat(rbdColorMapping1).isNotEqualTo(rbdColorMapping2);
        rbdColorMapping1.setId(null);
        assertThat(rbdColorMapping1).isNotEqualTo(rbdColorMapping2);
    }
}
