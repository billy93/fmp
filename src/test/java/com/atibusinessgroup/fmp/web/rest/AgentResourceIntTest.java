package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.Agent;
import com.atibusinessgroup.fmp.repository.AgentRepository;
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
 * Test class for the AgentResource REST controller.
 *
 * @see AgentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class AgentResourceIntTest {

    private static final String DEFAULT_AGENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_POS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_POS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_IATA_CODE = "AAAAAAAAAA";
    private static final String UPDATED_IATA_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = true;
    private static final Boolean UPDATED_IS_DELETED = false;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAgentMockMvc;

    private Agent agent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AgentResource agentResource = new AgentResource(agentRepository);
        this.restAgentMockMvc = MockMvcBuilders.standaloneSetup(agentResource)
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
    public static Agent createEntity() {
        Agent agent = new Agent()
            .agentName(DEFAULT_AGENT_NAME)
            .agentType(DEFAULT_AGENT_TYPE)
            .agentCategory(DEFAULT_AGENT_CATEGORY)
            .posCity(DEFAULT_POS_CITY)
            .address(DEFAULT_ADDRESS)
            .telephone(DEFAULT_TELEPHONE)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL)
            .contact(DEFAULT_CONTACT)
            .iataCode(DEFAULT_IATA_CODE)
            .isDeleted(DEFAULT_IS_DELETED);
        return agent;
    }

    @Before
    public void initTest() {
        agentRepository.deleteAll();
        agent = createEntity();
    }

    @Test
    public void createAgent() throws Exception {
        int databaseSizeBeforeCreate = agentRepository.findAll().size();

        // Create the Agent
        restAgentMockMvc.perform(post("/api/agents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agent)))
            .andExpect(status().isCreated());

        // Validate the Agent in the database
        List<Agent> agentList = agentRepository.findAll();
        assertThat(agentList).hasSize(databaseSizeBeforeCreate + 1);
        Agent testAgent = agentList.get(agentList.size() - 1);
        assertThat(testAgent.getAgentName()).isEqualTo(DEFAULT_AGENT_NAME);
        assertThat(testAgent.getAgentType()).isEqualTo(DEFAULT_AGENT_TYPE);
        assertThat(testAgent.getAgentCategory()).isEqualTo(DEFAULT_AGENT_CATEGORY);
        assertThat(testAgent.getPosCity()).isEqualTo(DEFAULT_POS_CITY);
        assertThat(testAgent.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testAgent.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testAgent.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testAgent.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAgent.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testAgent.getIataCode()).isEqualTo(DEFAULT_IATA_CODE);
        assertThat(testAgent.getIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    public void createAgentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agentRepository.findAll().size();

        // Create the Agent with an existing ID
        agent.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgentMockMvc.perform(post("/api/agents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agent)))
            .andExpect(status().isBadRequest());

        // Validate the Agent in the database
        List<Agent> agentList = agentRepository.findAll();
        assertThat(agentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAgents() throws Exception {
        // Initialize the database
        agentRepository.save(agent);

        // Get all the agentList
        restAgentMockMvc.perform(get("/api/agents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agent.getId())))
            .andExpect(jsonPath("$.[*].agentName").value(hasItem(DEFAULT_AGENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].agentType").value(hasItem(DEFAULT_AGENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].agentCategory").value(hasItem(DEFAULT_AGENT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].posCity").value(hasItem(DEFAULT_POS_CITY.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].iataCode").value(hasItem(DEFAULT_IATA_CODE.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.toString())));
    }

    @Test
    public void getAgent() throws Exception {
        // Initialize the database
        agentRepository.save(agent);

        // Get the agent
        restAgentMockMvc.perform(get("/api/agents/{id}", agent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(agent.getId()))
            .andExpect(jsonPath("$.agentName").value(DEFAULT_AGENT_NAME.toString()))
            .andExpect(jsonPath("$.agentType").value(DEFAULT_AGENT_TYPE.toString()))
            .andExpect(jsonPath("$.agentCategory").value(DEFAULT_AGENT_CATEGORY.toString()))
            .andExpect(jsonPath("$.posCity").value(DEFAULT_POS_CITY.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT.toString()))
            .andExpect(jsonPath("$.iataCode").value(DEFAULT_IATA_CODE.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.toString()));
    }

    @Test
    public void getNonExistingAgent() throws Exception {
        // Get the agent
        restAgentMockMvc.perform(get("/api/agents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAgent() throws Exception {
        // Initialize the database
        agentRepository.save(agent);
        int databaseSizeBeforeUpdate = agentRepository.findAll().size();

        // Update the agent
        Agent updatedAgent = agentRepository.findOne(agent.getId());
        updatedAgent
            .agentName(UPDATED_AGENT_NAME)
            .agentType(UPDATED_AGENT_TYPE)
            .agentCategory(UPDATED_AGENT_CATEGORY)
            .posCity(UPDATED_POS_CITY)
            .address(UPDATED_ADDRESS)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .contact(UPDATED_CONTACT)
            .iataCode(UPDATED_IATA_CODE)
            .isDeleted(UPDATED_IS_DELETED);

        restAgentMockMvc.perform(put("/api/agents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAgent)))
            .andExpect(status().isOk());

        // Validate the Agent in the database
        List<Agent> agentList = agentRepository.findAll();
        assertThat(agentList).hasSize(databaseSizeBeforeUpdate);
        Agent testAgent = agentList.get(agentList.size() - 1);
        assertThat(testAgent.getAgentName()).isEqualTo(UPDATED_AGENT_NAME);
        assertThat(testAgent.getAgentType()).isEqualTo(UPDATED_AGENT_TYPE);
        assertThat(testAgent.getAgentCategory()).isEqualTo(UPDATED_AGENT_CATEGORY);
        assertThat(testAgent.getPosCity()).isEqualTo(UPDATED_POS_CITY);
        assertThat(testAgent.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testAgent.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testAgent.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testAgent.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAgent.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testAgent.getIataCode()).isEqualTo(UPDATED_IATA_CODE);
        assertThat(testAgent.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    public void updateNonExistingAgent() throws Exception {
        int databaseSizeBeforeUpdate = agentRepository.findAll().size();

        // Create the Agent

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAgentMockMvc.perform(put("/api/agents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agent)))
            .andExpect(status().isCreated());

        // Validate the Agent in the database
        List<Agent> agentList = agentRepository.findAll();
        assertThat(agentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteAgent() throws Exception {
        // Initialize the database
        agentRepository.save(agent);
        int databaseSizeBeforeDelete = agentRepository.findAll().size();

        // Get the agent
        restAgentMockMvc.perform(delete("/api/agents/{id}", agent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Agent> agentList = agentRepository.findAll();
        assertThat(agentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Agent.class);
        Agent agent1 = new Agent();
        agent1.setId("id1");
        Agent agent2 = new Agent();
        agent2.setId(agent1.getId());
        assertThat(agent1).isEqualTo(agent2);
        agent2.setId("id2");
        assertThat(agent1).isNotEqualTo(agent2);
        agent1.setId(null);
        assertThat(agent1).isNotEqualTo(agent2);
    }
}
