package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.AgentGroups;
import com.atibusinessgroup.fmp.repository.AgentGroupsRepository;
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
 * Test class for the AgentGroupsResource REST controller.
 *
 * @see AgentGroupsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class AgentGroupsResourceIntTest {

    private static final String DEFAULT_AGENCY_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AGENCY_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_NAME = "BBBBBBBBBB";

    @Autowired
    private AgentGroupsRepository agentGroupsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAgentGroupsMockMvc;

    private AgentGroups agentGroups;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
//        final AgentGroupsResource agentGroupsResource = new AgentResource(agentGroupsRepository);
//        this.restAgentGroupsMockMvc = MockMvcBuilders.standaloneSetup(agentGroupsResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AgentGroups createEntity() {
        AgentGroups agentGroups = new AgentGroups();
//            .agencyGroupName(DEFAULT_AGENCY_GROUP_NAME)
//            .agentName(DEFAULT_AGENT_NAME);
        return agentGroups;
    }

    @Before
    public void initTest() {
        agentGroupsRepository.deleteAll();
        agentGroups = createEntity();
    }

    @Test
    public void createAgentGroups() throws Exception {
        int databaseSizeBeforeCreate = agentGroupsRepository.findAll().size();

        // Create the AgentGroups
        restAgentGroupsMockMvc.perform(post("/api/agent-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agentGroups)))
            .andExpect(status().isCreated());

        // Validate the AgentGroups in the database
        List<AgentGroups> agentGroupsList = agentGroupsRepository.findAll();
        assertThat(agentGroupsList).hasSize(databaseSizeBeforeCreate + 1);
        AgentGroups testAgentGroups = agentGroupsList.get(agentGroupsList.size() - 1);
        assertThat(testAgentGroups.getAgencyGroupName()).isEqualTo(DEFAULT_AGENCY_GROUP_NAME);
        assertThat(testAgentGroups.getAgentName()).isEqualTo(DEFAULT_AGENT_NAME);
    }

    @Test
    public void createAgentGroupsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agentGroupsRepository.findAll().size();

        // Create the AgentGroups with an existing ID
        agentGroups.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgentGroupsMockMvc.perform(post("/api/agent-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agentGroups)))
            .andExpect(status().isBadRequest());

        // Validate the AgentGroups in the database
        List<AgentGroups> agentGroupsList = agentGroupsRepository.findAll();
        assertThat(agentGroupsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAgentGroups() throws Exception {
        // Initialize the database
        agentGroupsRepository.save(agentGroups);

        // Get all the agentGroupsList
        restAgentGroupsMockMvc.perform(get("/api/agent-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agentGroups.getId())))
            .andExpect(jsonPath("$.[*].agencyGroupName").value(hasItem(DEFAULT_AGENCY_GROUP_NAME.toString())))
            .andExpect(jsonPath("$.[*].agentName").value(hasItem(DEFAULT_AGENT_NAME.toString())));
    }

    @Test
    public void getAgentGroups() throws Exception {
        // Initialize the database
        agentGroupsRepository.save(agentGroups);

        // Get the agentGroups
        restAgentGroupsMockMvc.perform(get("/api/agent-groups/{id}", agentGroups.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(agentGroups.getId()))
            .andExpect(jsonPath("$.agencyGroupName").value(DEFAULT_AGENCY_GROUP_NAME.toString()))
            .andExpect(jsonPath("$.agentName").value(DEFAULT_AGENT_NAME.toString()));
    }

    @Test
    public void getNonExistingAgentGroups() throws Exception {
        // Get the agentGroups
        restAgentGroupsMockMvc.perform(get("/api/agent-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAgentGroups() throws Exception {
        // Initialize the database
        agentGroupsRepository.save(agentGroups);
        int databaseSizeBeforeUpdate = agentGroupsRepository.findAll().size();

        // Update the agentGroups
//        AgentGroups updatedAgentGroups = agentGroupsRepository.findOne(agentGroups.getId());
//        updatedAgentGroups
//            .agencyGroupName(UPDATED_AGENCY_GROUP_NAME)
//            .agentName(UPDATED_AGENT_NAME);
//
//        restAgentGroupsMockMvc.perform(put("/api/agent-groups")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(updatedAgentGroups)))
//            .andExpect(status().isOk());

        // Validate the AgentGroups in the database
        List<AgentGroups> agentGroupsList = agentGroupsRepository.findAll();
        assertThat(agentGroupsList).hasSize(databaseSizeBeforeUpdate);
        AgentGroups testAgentGroups = agentGroupsList.get(agentGroupsList.size() - 1);
        assertThat(testAgentGroups.getAgencyGroupName()).isEqualTo(UPDATED_AGENCY_GROUP_NAME);
        assertThat(testAgentGroups.getAgentName()).isEqualTo(UPDATED_AGENT_NAME);
    }

    @Test
    public void updateNonExistingAgentGroups() throws Exception {
        int databaseSizeBeforeUpdate = agentGroupsRepository.findAll().size();

        // Create the AgentGroups

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAgentGroupsMockMvc.perform(put("/api/agent-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agentGroups)))
            .andExpect(status().isCreated());

        // Validate the AgentGroups in the database
        List<AgentGroups> agentGroupsList = agentGroupsRepository.findAll();
        assertThat(agentGroupsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteAgentGroups() throws Exception {
        // Initialize the database
        agentGroupsRepository.save(agentGroups);
        int databaseSizeBeforeDelete = agentGroupsRepository.findAll().size();

        // Get the agentGroups
        restAgentGroupsMockMvc.perform(delete("/api/agent-groups/{id}", agentGroups.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AgentGroups> agentGroupsList = agentGroupsRepository.findAll();
        assertThat(agentGroupsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AgentGroups.class);
        AgentGroups agentGroups1 = new AgentGroups();
        agentGroups1.setId("id1");
        AgentGroups agentGroups2 = new AgentGroups();
        agentGroups2.setId(agentGroups1.getId());
        assertThat(agentGroups1).isEqualTo(agentGroups2);
        agentGroups2.setId("id2");
        assertThat(agentGroups1).isNotEqualTo(agentGroups2);
        agentGroups1.setId(null);
        assertThat(agentGroups1).isNotEqualTo(agentGroups2);
    }
}
