package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.CityGroup;
import com.atibusinessgroup.fmp.repository.CityGroupRepository;
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
 * Test class for the CityGroupResource REST controller.
 *
 * @see CityGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class CityGroupResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private CityGroupRepository cityGroupRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCityGroupMockMvc;

    private CityGroup cityGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CityGroupResource cityGroupResource = new CityGroupResource(cityGroupRepository);
        this.restCityGroupMockMvc = MockMvcBuilders.standaloneSetup(cityGroupResource)
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
    public static CityGroup createEntity() {
        CityGroup cityGroup = new CityGroup()
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION);
        return cityGroup;
    }

    @Before
    public void initTest() {
        cityGroupRepository.deleteAll();
        cityGroup = createEntity();
    }

    @Test
    public void createCityGroup() throws Exception {
        int databaseSizeBeforeCreate = cityGroupRepository.findAll().size();

        // Create the CityGroup
        restCityGroupMockMvc.perform(post("/api/city-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cityGroup)))
            .andExpect(status().isCreated());

        // Validate the CityGroup in the database
        List<CityGroup> cityGroupList = cityGroupRepository.findAll();
        assertThat(cityGroupList).hasSize(databaseSizeBeforeCreate + 1);
        CityGroup testCityGroup = cityGroupList.get(cityGroupList.size() - 1);
        assertThat(testCityGroup.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCityGroup.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createCityGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cityGroupRepository.findAll().size();

        // Create the CityGroup with an existing ID
        cityGroup.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCityGroupMockMvc.perform(post("/api/city-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cityGroup)))
            .andExpect(status().isBadRequest());

        // Validate the CityGroup in the database
        List<CityGroup> cityGroupList = cityGroupRepository.findAll();
        assertThat(cityGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllCityGroups() throws Exception {
        // Initialize the database
        cityGroupRepository.save(cityGroup);

        // Get all the cityGroupList
        restCityGroupMockMvc.perform(get("/api/city-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cityGroup.getId())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    public void getCityGroup() throws Exception {
        // Initialize the database
        cityGroupRepository.save(cityGroup);

        // Get the cityGroup
        restCityGroupMockMvc.perform(get("/api/city-groups/{id}", cityGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cityGroup.getId()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingCityGroup() throws Exception {
        // Get the cityGroup
        restCityGroupMockMvc.perform(get("/api/city-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCityGroup() throws Exception {
        // Initialize the database
        cityGroupRepository.save(cityGroup);
        int databaseSizeBeforeUpdate = cityGroupRepository.findAll().size();

        // Update the cityGroup
        CityGroup updatedCityGroup = cityGroupRepository.findOne(cityGroup.getId());
        updatedCityGroup
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION);

        restCityGroupMockMvc.perform(put("/api/city-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCityGroup)))
            .andExpect(status().isOk());

        // Validate the CityGroup in the database
        List<CityGroup> cityGroupList = cityGroupRepository.findAll();
        assertThat(cityGroupList).hasSize(databaseSizeBeforeUpdate);
        CityGroup testCityGroup = cityGroupList.get(cityGroupList.size() - 1);
        assertThat(testCityGroup.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCityGroup.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingCityGroup() throws Exception {
        int databaseSizeBeforeUpdate = cityGroupRepository.findAll().size();

        // Create the CityGroup

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCityGroupMockMvc.perform(put("/api/city-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cityGroup)))
            .andExpect(status().isCreated());

        // Validate the CityGroup in the database
        List<CityGroup> cityGroupList = cityGroupRepository.findAll();
        assertThat(cityGroupList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteCityGroup() throws Exception {
        // Initialize the database
        cityGroupRepository.save(cityGroup);
        int databaseSizeBeforeDelete = cityGroupRepository.findAll().size();

        // Get the cityGroup
        restCityGroupMockMvc.perform(delete("/api/city-groups/{id}", cityGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CityGroup> cityGroupList = cityGroupRepository.findAll();
        assertThat(cityGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CityGroup.class);
        CityGroup cityGroup1 = new CityGroup();
        cityGroup1.setId("id1");
        CityGroup cityGroup2 = new CityGroup();
        cityGroup2.setId(cityGroup1.getId());
        assertThat(cityGroup1).isEqualTo(cityGroup2);
        cityGroup2.setId("id2");
        assertThat(cityGroup1).isNotEqualTo(cityGroup2);
        cityGroup1.setId(null);
        assertThat(cityGroup1).isNotEqualTo(cityGroup2);
    }
}
