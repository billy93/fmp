package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.Clipboard;
import com.atibusinessgroup.fmp.repository.ClipboardRepository;
import com.atibusinessgroup.fmp.service.ClipboardService;
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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.atibusinessgroup.fmp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ClipboardResource REST controller.
 *
 * @see ClipboardResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class ClipboardResourceIntTest {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PAGE = "AAAAAAAAAA";
    private static final String UPDATED_PAGE = "BBBBBBBBBB";

    private static final Instant DEFAULT_COPY_DATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_COPY_DATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ClipboardRepository clipboardRepository;

    @Autowired
    private ClipboardService clipboardService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restClipboardMockMvc;

    private Clipboard clipboard;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClipboardResource clipboardResource = new ClipboardResource(clipboardService, null);
        this.restClipboardMockMvc = MockMvcBuilders.standaloneSetup(clipboardResource)
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
    public static Clipboard createEntity() {
        Clipboard clipboard = new Clipboard()
            .username(DEFAULT_USERNAME)
            .page(DEFAULT_PAGE)
            .copyDateTime(DEFAULT_COPY_DATE_TIME);
        return clipboard;
    }

    @Before
    public void initTest() {
        clipboardRepository.deleteAll();
        clipboard = createEntity();
    }

    @Test
    public void createClipboard() throws Exception {
        int databaseSizeBeforeCreate = clipboardRepository.findAll().size();

        // Create the Clipboard
        restClipboardMockMvc.perform(post("/api/clipboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clipboard)))
            .andExpect(status().isCreated());

        // Validate the Clipboard in the database
        List<Clipboard> clipboardList = clipboardRepository.findAll();
        assertThat(clipboardList).hasSize(databaseSizeBeforeCreate + 1);
        Clipboard testClipboard = clipboardList.get(clipboardList.size() - 1);
        assertThat(testClipboard.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testClipboard.getPage()).isEqualTo(DEFAULT_PAGE);
        assertThat(testClipboard.getCopyDateTime()).isEqualTo(DEFAULT_COPY_DATE_TIME);
    }

    @Test
    public void createClipboardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clipboardRepository.findAll().size();

        // Create the Clipboard with an existing ID
        clipboard.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restClipboardMockMvc.perform(post("/api/clipboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clipboard)))
            .andExpect(status().isBadRequest());

        // Validate the Clipboard in the database
        List<Clipboard> clipboardList = clipboardRepository.findAll();
        assertThat(clipboardList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllClipboards() throws Exception {
        // Initialize the database
        clipboardRepository.save(clipboard);

        // Get all the clipboardList
        restClipboardMockMvc.perform(get("/api/clipboards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clipboard.getId())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].page").value(hasItem(DEFAULT_PAGE.toString())))
            .andExpect(jsonPath("$.[*].copyDateTime").value(hasItem(DEFAULT_COPY_DATE_TIME.toString())));
    }

    @Test
    public void getClipboard() throws Exception {
        // Initialize the database
        clipboardRepository.save(clipboard);

        // Get the clipboard
        restClipboardMockMvc.perform(get("/api/clipboards/{id}", clipboard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(clipboard.getId()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.page").value(DEFAULT_PAGE.toString()))
            .andExpect(jsonPath("$.copyDateTime").value(DEFAULT_COPY_DATE_TIME.toString()));
    }

    @Test
    public void getNonExistingClipboard() throws Exception {
        // Get the clipboard
        restClipboardMockMvc.perform(get("/api/clipboards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateClipboard() throws Exception {
        // Initialize the database
        clipboardService.save(clipboard);

        int databaseSizeBeforeUpdate = clipboardRepository.findAll().size();

        // Update the clipboard
        Clipboard updatedClipboard = clipboardRepository.findOne(clipboard.getId());
        updatedClipboard
            .username(UPDATED_USERNAME)
            .page(UPDATED_PAGE)
            .copyDateTime(UPDATED_COPY_DATE_TIME);

        restClipboardMockMvc.perform(put("/api/clipboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedClipboard)))
            .andExpect(status().isOk());

        // Validate the Clipboard in the database
        List<Clipboard> clipboardList = clipboardRepository.findAll();
        assertThat(clipboardList).hasSize(databaseSizeBeforeUpdate);
        Clipboard testClipboard = clipboardList.get(clipboardList.size() - 1);
        assertThat(testClipboard.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testClipboard.getPage()).isEqualTo(UPDATED_PAGE);
        assertThat(testClipboard.getCopyDateTime()).isEqualTo(UPDATED_COPY_DATE_TIME);
    }

    @Test
    public void updateNonExistingClipboard() throws Exception {
        int databaseSizeBeforeUpdate = clipboardRepository.findAll().size();

        // Create the Clipboard

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restClipboardMockMvc.perform(put("/api/clipboards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clipboard)))
            .andExpect(status().isCreated());

        // Validate the Clipboard in the database
        List<Clipboard> clipboardList = clipboardRepository.findAll();
        assertThat(clipboardList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteClipboard() throws Exception {
        // Initialize the database
        clipboardService.save(clipboard);

        int databaseSizeBeforeDelete = clipboardRepository.findAll().size();

        // Get the clipboard
        restClipboardMockMvc.perform(delete("/api/clipboards/{id}", clipboard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Clipboard> clipboardList = clipboardRepository.findAll();
        assertThat(clipboardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Clipboard.class);
        Clipboard clipboard1 = new Clipboard();
        clipboard1.setId("id1");
        Clipboard clipboard2 = new Clipboard();
        clipboard2.setId(clipboard1.getId());
        assertThat(clipboard1).isEqualTo(clipboard2);
        clipboard2.setId("id2");
        assertThat(clipboard1).isNotEqualTo(clipboard2);
        clipboard1.setId(null);
        assertThat(clipboard1).isNotEqualTo(clipboard2);
    }
}
