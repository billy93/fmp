package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.FareType;
import com.atibusinessgroup.fmp.repository.FareTypeRepository;
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
 * Test class for the FareTypeResource REST controller.
 *
 * @see FareTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class FareTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private FareTypeRepository fareTypeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restFareTypeMockMvc;

    private FareType fareType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FareTypeResource fareTypeResource = new FareTypeResource(fareTypeRepository);
        this.restFareTypeMockMvc = MockMvcBuilders.standaloneSetup(fareTypeResource)
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
    public static FareType createEntity() {
        FareType fareType = new FareType()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .code(DEFAULT_CODE);
        return fareType;
    }

    @Before
    public void initTest() {
        fareTypeRepository.deleteAll();
        fareType = createEntity();
    }

    @Test
    public void createFareType() throws Exception {
        int databaseSizeBeforeCreate = fareTypeRepository.findAll().size();

        // Create the FareType
        restFareTypeMockMvc.perform(post("/api/fare-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fareType)))
            .andExpect(status().isCreated());

        // Validate the FareType in the database
        List<FareType> fareTypeList = fareTypeRepository.findAll();
        assertThat(fareTypeList).hasSize(databaseSizeBeforeCreate + 1);
        FareType testFareType = fareTypeList.get(fareTypeList.size() - 1);
        assertThat(testFareType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFareType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFareType.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    public void createFareTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fareTypeRepository.findAll().size();

        // Create the FareType with an existing ID
        fareType.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restFareTypeMockMvc.perform(post("/api/fare-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fareType)))
            .andExpect(status().isBadRequest());

        // Validate the FareType in the database
        List<FareType> fareTypeList = fareTypeRepository.findAll();
        assertThat(fareTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllFareTypes() throws Exception {
        // Initialize the database
        fareTypeRepository.save(fareType);

        // Get all the fareTypeList
        restFareTypeMockMvc.perform(get("/api/fare-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fareType.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())));
    }

    @Test
    public void getFareType() throws Exception {
        // Initialize the database
        fareTypeRepository.save(fareType);

        // Get the fareType
        restFareTypeMockMvc.perform(get("/api/fare-types/{id}", fareType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fareType.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()));
    }

    @Test
    public void getNonExistingFareType() throws Exception {
        // Get the fareType
        restFareTypeMockMvc.perform(get("/api/fare-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateFareType() throws Exception {
        // Initialize the database
        fareTypeRepository.save(fareType);
        int databaseSizeBeforeUpdate = fareTypeRepository.findAll().size();

        // Update the fareType
        FareType updatedFareType = fareTypeRepository.findOne(fareType.getId());
        updatedFareType
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .code(UPDATED_CODE);

        restFareTypeMockMvc.perform(put("/api/fare-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFareType)))
            .andExpect(status().isOk());

        // Validate the FareType in the database
        List<FareType> fareTypeList = fareTypeRepository.findAll();
        assertThat(fareTypeList).hasSize(databaseSizeBeforeUpdate);
        FareType testFareType = fareTypeList.get(fareTypeList.size() - 1);
        assertThat(testFareType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFareType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFareType.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    public void updateNonExistingFareType() throws Exception {
        int databaseSizeBeforeUpdate = fareTypeRepository.findAll().size();

        // Create the FareType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFareTypeMockMvc.perform(put("/api/fare-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fareType)))
            .andExpect(status().isCreated());

        // Validate the FareType in the database
        List<FareType> fareTypeList = fareTypeRepository.findAll();
        assertThat(fareTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteFareType() throws Exception {
        // Initialize the database
        fareTypeRepository.save(fareType);
        int databaseSizeBeforeDelete = fareTypeRepository.findAll().size();

        // Get the fareType
        restFareTypeMockMvc.perform(delete("/api/fare-types/{id}", fareType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FareType> fareTypeList = fareTypeRepository.findAll();
        assertThat(fareTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FareType.class);
        FareType fareType1 = new FareType();
        fareType1.setId("id1");
        FareType fareType2 = new FareType();
        fareType2.setId(fareType1.getId());
        assertThat(fareType1).isEqualTo(fareType2);
        fareType2.setId("id2");
        assertThat(fareType1).isNotEqualTo(fareType2);
        fareType1.setId(null);
        assertThat(fareType1).isNotEqualTo(fareType2);
    }
}
