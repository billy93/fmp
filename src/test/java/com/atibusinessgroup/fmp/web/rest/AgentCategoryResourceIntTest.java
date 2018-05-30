package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.AgentCategory;
import com.atibusinessgroup.fmp.repository.AgentCategoryRepository;
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
 * Test class for the AgentCategoryResource REST controller.
 *
 * @see AgentCategoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class AgentCategoryResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private AgentCategoryRepository agentCategoryRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAgentCategoryMockMvc;

    private AgentCategory agentCategory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AgentCategoryResource agentCategoryResource = new AgentCategoryResource(agentCategoryRepository);
        this.restAgentCategoryMockMvc = MockMvcBuilders.standaloneSetup(agentCategoryResource)
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
    public static AgentCategory createEntity() {
        AgentCategory agentCategory = new AgentCategory()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return agentCategory;
    }

    @Before
    public void initTest() {
        agentCategoryRepository.deleteAll();
        agentCategory = createEntity();
    }

    @Test
    public void createAgentCategory() throws Exception {
        int databaseSizeBeforeCreate = agentCategoryRepository.findAll().size();

        // Create the AgentCategory
        restAgentCategoryMockMvc.perform(post("/api/agent-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agentCategory)))
            .andExpect(status().isCreated());

        // Validate the AgentCategory in the database
        List<AgentCategory> agentCategoryList = agentCategoryRepository.findAll();
        assertThat(agentCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        AgentCategory testAgentCategory = agentCategoryList.get(agentCategoryList.size() - 1);
        assertThat(testAgentCategory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAgentCategory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createAgentCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agentCategoryRepository.findAll().size();

        // Create the AgentCategory with an existing ID
        agentCategory.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgentCategoryMockMvc.perform(post("/api/agent-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agentCategory)))
            .andExpect(status().isBadRequest());

        // Validate the AgentCategory in the database
        List<AgentCategory> agentCategoryList = agentCategoryRepository.findAll();
        assertThat(agentCategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAgentCategories() throws Exception {
        // Initialize the database
        agentCategoryRepository.save(agentCategory);

        // Get all the agentCategoryList
        restAgentCategoryMockMvc.perform(get("/api/agent-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agentCategory.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    public void getAgentCategory() throws Exception {
        // Initialize the database
        agentCategoryRepository.save(agentCategory);

        // Get the agentCategory
        restAgentCategoryMockMvc.perform(get("/api/agent-categories/{id}", agentCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(agentCategory.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingAgentCategory() throws Exception {
        // Get the agentCategory
        restAgentCategoryMockMvc.perform(get("/api/agent-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAgentCategory() throws Exception {
        // Initialize the database
        agentCategoryRepository.save(agentCategory);
        int databaseSizeBeforeUpdate = agentCategoryRepository.findAll().size();

        // Update the agentCategory
        AgentCategory updatedAgentCategory = agentCategoryRepository.findOne(agentCategory.getId());
        updatedAgentCategory
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);

        restAgentCategoryMockMvc.perform(put("/api/agent-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAgentCategory)))
            .andExpect(status().isOk());

        // Validate the AgentCategory in the database
        List<AgentCategory> agentCategoryList = agentCategoryRepository.findAll();
        assertThat(agentCategoryList).hasSize(databaseSizeBeforeUpdate);
        AgentCategory testAgentCategory = agentCategoryList.get(agentCategoryList.size() - 1);
        assertThat(testAgentCategory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAgentCategory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingAgentCategory() throws Exception {
        int databaseSizeBeforeUpdate = agentCategoryRepository.findAll().size();

        // Create the AgentCategory

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAgentCategoryMockMvc.perform(put("/api/agent-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agentCategory)))
            .andExpect(status().isCreated());

        // Validate the AgentCategory in the database
        List<AgentCategory> agentCategoryList = agentCategoryRepository.findAll();
        assertThat(agentCategoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteAgentCategory() throws Exception {
        // Initialize the database
        agentCategoryRepository.save(agentCategory);
        int databaseSizeBeforeDelete = agentCategoryRepository.findAll().size();

        // Get the agentCategory
        restAgentCategoryMockMvc.perform(delete("/api/agent-categories/{id}", agentCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AgentCategory> agentCategoryList = agentCategoryRepository.findAll();
        assertThat(agentCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AgentCategory.class);
        AgentCategory agentCategory1 = new AgentCategory();
        agentCategory1.setId("id1");
        AgentCategory agentCategory2 = new AgentCategory();
        agentCategory2.setId(agentCategory1.getId());
        assertThat(agentCategory1).isEqualTo(agentCategory2);
        agentCategory2.setId("id2");
        assertThat(agentCategory1).isNotEqualTo(agentCategory2);
        agentCategory1.setId(null);
        assertThat(agentCategory1).isNotEqualTo(agentCategory2);
    }
}
