package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.State;
import com.atibusinessgroup.fmp.repository.StateRepository;
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
 * Test class for the StateResource REST controller.
 *
 * @see StateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class StateResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restStateMockMvc;

    private State state;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StateResource stateResource = new StateResource(stateRepository);
        this.restStateMockMvc = MockMvcBuilders.standaloneSetup(stateResource)
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
    public static State createEntity() {
        State state = new State()
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION);
        return state;
    }

    @Before
    public void initTest() {
        stateRepository.deleteAll();
        state = createEntity();
    }

    @Test
    public void createState() throws Exception {
        int databaseSizeBeforeCreate = stateRepository.findAll().size();

        // Create the State
        restStateMockMvc.perform(post("/api/states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(state)))
            .andExpect(status().isCreated());

        // Validate the State in the database
        List<State> stateList = stateRepository.findAll();
        assertThat(stateList).hasSize(databaseSizeBeforeCreate + 1);
        State testState = stateList.get(stateList.size() - 1);
        assertThat(testState.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testState.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createStateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stateRepository.findAll().size();

        // Create the State with an existing ID
        state.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restStateMockMvc.perform(post("/api/states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(state)))
            .andExpect(status().isBadRequest());

        // Validate the State in the database
        List<State> stateList = stateRepository.findAll();
        assertThat(stateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllStates() throws Exception {
        // Initialize the database
        stateRepository.save(state);

        // Get all the stateList
        restStateMockMvc.perform(get("/api/states?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(state.getId())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    public void getState() throws Exception {
        // Initialize the database
        stateRepository.save(state);

        // Get the state
        restStateMockMvc.perform(get("/api/states/{id}", state.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(state.getId()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingState() throws Exception {
        // Get the state
        restStateMockMvc.perform(get("/api/states/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateState() throws Exception {
        // Initialize the database
        stateRepository.save(state);
        int databaseSizeBeforeUpdate = stateRepository.findAll().size();

        // Update the state
        State updatedState = stateRepository.findOne(state.getId());
        updatedState
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION);

        restStateMockMvc.perform(put("/api/states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedState)))
            .andExpect(status().isOk());

        // Validate the State in the database
        List<State> stateList = stateRepository.findAll();
        assertThat(stateList).hasSize(databaseSizeBeforeUpdate);
        State testState = stateList.get(stateList.size() - 1);
        assertThat(testState.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testState.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingState() throws Exception {
        int databaseSizeBeforeUpdate = stateRepository.findAll().size();

        // Create the State

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStateMockMvc.perform(put("/api/states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(state)))
            .andExpect(status().isCreated());

        // Validate the State in the database
        List<State> stateList = stateRepository.findAll();
        assertThat(stateList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteState() throws Exception {
        // Initialize the database
        stateRepository.save(state);
        int databaseSizeBeforeDelete = stateRepository.findAll().size();

        // Get the state
        restStateMockMvc.perform(delete("/api/states/{id}", state.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<State> stateList = stateRepository.findAll();
        assertThat(stateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(State.class);
        State state1 = new State();
        state1.setId("id1");
        State state2 = new State();
        state2.setId(state1.getId());
        assertThat(state1).isEqualTo(state2);
        state2.setId("id2");
        assertThat(state1).isNotEqualTo(state2);
        state1.setId(null);
        assertThat(state1).isNotEqualTo(state2);
    }
}
