package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.SystemParameter;
import com.atibusinessgroup.fmp.repository.SystemParameterRepository;
import com.atibusinessgroup.fmp.service.SystemParameterService;
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
 * Test class for the SystemParameterResource REST controller.
 *
 * @see SystemParameterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class SystemParameterResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private SystemParameterRepository systemParameterRepository;

    @Autowired
    private SystemParameterService systemParameterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restSystemParameterMockMvc;

    private SystemParameter systemParameter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SystemParameterResource systemParameterResource = new SystemParameterResource(systemParameterService);
        this.restSystemParameterMockMvc = MockMvcBuilders.standaloneSetup(systemParameterResource)
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
    public static SystemParameter createEntity() {
        SystemParameter systemParameter = new SystemParameter()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE);
        return systemParameter;
    }

    @Before
    public void initTest() {
        systemParameterRepository.deleteAll();
        systemParameter = createEntity();
    }

    @Test
    public void createSystemParameter() throws Exception {
        int databaseSizeBeforeCreate = systemParameterRepository.findAll().size();

        // Create the SystemParameter
        restSystemParameterMockMvc.perform(post("/api/system-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemParameter)))
            .andExpect(status().isCreated());

        // Validate the SystemParameter in the database
        List<SystemParameter> systemParameterList = systemParameterRepository.findAll();
        assertThat(systemParameterList).hasSize(databaseSizeBeforeCreate + 1);
        SystemParameter testSystemParameter = systemParameterList.get(systemParameterList.size() - 1);
        assertThat(testSystemParameter.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSystemParameter.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    public void createSystemParameterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = systemParameterRepository.findAll().size();

        // Create the SystemParameter with an existing ID
        systemParameter.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemParameterMockMvc.perform(post("/api/system-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemParameter)))
            .andExpect(status().isBadRequest());

        // Validate the SystemParameter in the database
        List<SystemParameter> systemParameterList = systemParameterRepository.findAll();
        assertThat(systemParameterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllSystemParameters() throws Exception {
        // Initialize the database
        systemParameterRepository.save(systemParameter);

        // Get all the systemParameterList
        restSystemParameterMockMvc.perform(get("/api/system-parameters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemParameter.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }

    @Test
    public void getSystemParameter() throws Exception {
        // Initialize the database
        systemParameterRepository.save(systemParameter);

        // Get the systemParameter
        restSystemParameterMockMvc.perform(get("/api/system-parameters/{id}", systemParameter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(systemParameter.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    public void getNonExistingSystemParameter() throws Exception {
        // Get the systemParameter
        restSystemParameterMockMvc.perform(get("/api/system-parameters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSystemParameter() throws Exception {
        // Initialize the database
        systemParameterService.save(systemParameter);

        int databaseSizeBeforeUpdate = systemParameterRepository.findAll().size();

        // Update the systemParameter
        SystemParameter updatedSystemParameter = systemParameterRepository.findOne(systemParameter.getId());
        updatedSystemParameter
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);

        restSystemParameterMockMvc.perform(put("/api/system-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSystemParameter)))
            .andExpect(status().isOk());

        // Validate the SystemParameter in the database
        List<SystemParameter> systemParameterList = systemParameterRepository.findAll();
        assertThat(systemParameterList).hasSize(databaseSizeBeforeUpdate);
        SystemParameter testSystemParameter = systemParameterList.get(systemParameterList.size() - 1);
        assertThat(testSystemParameter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSystemParameter.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    public void updateNonExistingSystemParameter() throws Exception {
        int databaseSizeBeforeUpdate = systemParameterRepository.findAll().size();

        // Create the SystemParameter

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSystemParameterMockMvc.perform(put("/api/system-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemParameter)))
            .andExpect(status().isCreated());

        // Validate the SystemParameter in the database
        List<SystemParameter> systemParameterList = systemParameterRepository.findAll();
        assertThat(systemParameterList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteSystemParameter() throws Exception {
        // Initialize the database
        systemParameterService.save(systemParameter);

        int databaseSizeBeforeDelete = systemParameterRepository.findAll().size();

        // Get the systemParameter
        restSystemParameterMockMvc.perform(delete("/api/system-parameters/{id}", systemParameter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SystemParameter> systemParameterList = systemParameterRepository.findAll();
        assertThat(systemParameterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemParameter.class);
        SystemParameter systemParameter1 = new SystemParameter();
        systemParameter1.setId("id1");
        SystemParameter systemParameter2 = new SystemParameter();
        systemParameter2.setId(systemParameter1.getId());
        assertThat(systemParameter1).isEqualTo(systemParameter2);
        systemParameter2.setId("id2");
        assertThat(systemParameter1).isNotEqualTo(systemParameter2);
        systemParameter1.setId(null);
        assertThat(systemParameter1).isNotEqualTo(systemParameter2);
    }
}
