package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.RoutingQuery;
import com.atibusinessgroup.fmp.repository.RoutingqueryRepository;
import com.atibusinessgroup.fmp.service.RoutingQueryService;
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
 * Test class for the RoutingqueryResource REST controller.
 *
 * @see RoutingQueryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class RoutingqueryResourceIntTest {

    private static final String DEFAULT_LINK_NO = "AAAAAAAAAA";
    private static final String UPDATED_LINK_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SRC = "AAAAAAAAAA";
    private static final String UPDATED_SRC = "BBBBBBBBBB";

    private static final String DEFAULT_CXR = "AAAAAAAAAA";
    private static final String UPDATED_CXR = "BBBBBBBBBB";

    private static final String DEFAULT_TAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_TAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTING_NO = "AAAAAAAAAA";
    private static final String UPDATED_ROUTING_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EFFECTIVE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_EFFECTIVE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DISCONTINUED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DISCONTINUED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DRV = "AAAAAAAAAA";
    private static final String UPDATED_DRV = "BBBBBBBBBB";

    private static final String DEFAULT_CPI = "AAAAAAAAAA";
    private static final String UPDATED_CPI = "BBBBBBBBBB";

    private static final String DEFAULT_DI = "AAAAAAAAAA";
    private static final String UPDATED_DI = "BBBBBBBBBB";

    private static final String DEFAULT_INT_PT = "AAAAAAAAAA";
    private static final String UPDATED_INT_PT = "BBBBBBBBBB";

    private static final String DEFAULT_UNT_PT = "AAAAAAAAAA";
    private static final String UPDATED_UNT_PT = "BBBBBBBBBB";

    @Autowired
    private RoutingqueryRepository routingqueryRepository;
    
    @Autowired
    private RoutingQueryService routingQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restRoutingqueryMockMvc;

    private RoutingQuery routingquery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RoutingQueryResource routingqueryResource = new RoutingQueryResource(routingQueryService);
        this.restRoutingqueryMockMvc = MockMvcBuilders.standaloneSetup(routingqueryResource)
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
    public static RoutingQuery createEntity() {
        RoutingQuery routingquery = new RoutingQuery()
            .linkNo(DEFAULT_LINK_NO)
            .src(DEFAULT_SRC)
            .cxr(DEFAULT_CXR)
            .tarNo(DEFAULT_TAR_NO)
            .routingNo(DEFAULT_ROUTING_NO)
            .effectiveDate(DEFAULT_EFFECTIVE_DATE)
            .discontinuedDate(DEFAULT_DISCONTINUED_DATE)
            .drv(DEFAULT_DRV)
            .cpi(DEFAULT_CPI)
            .di(DEFAULT_DI)
            .intPt(DEFAULT_INT_PT)
            .untPt(DEFAULT_UNT_PT);
        return routingquery;
    }

    @Before
    public void initTest() {
        routingqueryRepository.deleteAll();
        routingquery = createEntity();
    }

    @Test
    public void createRoutingquery() throws Exception {
        int databaseSizeBeforeCreate = routingqueryRepository.findAll().size();

        // Create the Routingquery
        restRoutingqueryMockMvc.perform(post("/api/routingqueries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(routingquery)))
            .andExpect(status().isCreated());

        // Validate the Routingquery in the database
        List<RoutingQuery> routingqueryList = routingqueryRepository.findAll();
        assertThat(routingqueryList).hasSize(databaseSizeBeforeCreate + 1);
        RoutingQuery testRoutingquery = routingqueryList.get(routingqueryList.size() - 1);
        assertThat(testRoutingquery.getLinkNo()).isEqualTo(DEFAULT_LINK_NO);
        assertThat(testRoutingquery.getSrc()).isEqualTo(DEFAULT_SRC);
        assertThat(testRoutingquery.getCxr()).isEqualTo(DEFAULT_CXR);
        assertThat(testRoutingquery.getTarNo()).isEqualTo(DEFAULT_TAR_NO);
        assertThat(testRoutingquery.getRoutingNo()).isEqualTo(DEFAULT_ROUTING_NO);
        assertThat(testRoutingquery.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
        assertThat(testRoutingquery.getDiscontinuedDate()).isEqualTo(DEFAULT_DISCONTINUED_DATE);
        assertThat(testRoutingquery.getDrv()).isEqualTo(DEFAULT_DRV);
        assertThat(testRoutingquery.getCpi()).isEqualTo(DEFAULT_CPI);
        assertThat(testRoutingquery.getDi()).isEqualTo(DEFAULT_DI);
        assertThat(testRoutingquery.getIntPt()).isEqualTo(DEFAULT_INT_PT);
        assertThat(testRoutingquery.getUntPt()).isEqualTo(DEFAULT_UNT_PT);
    }

    @Test
    public void createRoutingqueryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = routingqueryRepository.findAll().size();

        // Create the Routingquery with an existing ID
        routingquery.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoutingqueryMockMvc.perform(post("/api/routingqueries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(routingquery)))
            .andExpect(status().isBadRequest());

        // Validate the Routingquery in the database
        List<RoutingQuery> routingqueryList = routingqueryRepository.findAll();
        assertThat(routingqueryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllRoutingqueries() throws Exception {
        // Initialize the database
        routingqueryRepository.save(routingquery);

        // Get all the routingqueryList
        restRoutingqueryMockMvc.perform(get("/api/routingqueries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(routingquery.getId())))
            .andExpect(jsonPath("$.[*].linkNo").value(hasItem(DEFAULT_LINK_NO.toString())))
            .andExpect(jsonPath("$.[*].src").value(hasItem(DEFAULT_SRC.toString())))
            .andExpect(jsonPath("$.[*].cxr").value(hasItem(DEFAULT_CXR.toString())))
            .andExpect(jsonPath("$.[*].tarNo").value(hasItem(DEFAULT_TAR_NO.toString())))
            .andExpect(jsonPath("$.[*].routingNo").value(hasItem(DEFAULT_ROUTING_NO.toString())))
            .andExpect(jsonPath("$.[*].effectiveDate").value(hasItem(DEFAULT_EFFECTIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].discontinuedDate").value(hasItem(DEFAULT_DISCONTINUED_DATE.toString())))
            .andExpect(jsonPath("$.[*].drv").value(hasItem(DEFAULT_DRV.toString())))
            .andExpect(jsonPath("$.[*].cpi").value(hasItem(DEFAULT_CPI.toString())))
            .andExpect(jsonPath("$.[*].di").value(hasItem(DEFAULT_DI.toString())))
            .andExpect(jsonPath("$.[*].intPt").value(hasItem(DEFAULT_INT_PT.toString())))
            .andExpect(jsonPath("$.[*].untPt").value(hasItem(DEFAULT_UNT_PT.toString())));
    }

    @Test
    public void getRoutingquery() throws Exception {
        // Initialize the database
        routingqueryRepository.save(routingquery);

        // Get the routingquery
        restRoutingqueryMockMvc.perform(get("/api/routingqueries/{id}", routingquery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(routingquery.getId()))
            .andExpect(jsonPath("$.linkNo").value(DEFAULT_LINK_NO.toString()))
            .andExpect(jsonPath("$.src").value(DEFAULT_SRC.toString()))
            .andExpect(jsonPath("$.cxr").value(DEFAULT_CXR.toString()))
            .andExpect(jsonPath("$.tarNo").value(DEFAULT_TAR_NO.toString()))
            .andExpect(jsonPath("$.routingNo").value(DEFAULT_ROUTING_NO.toString()))
            .andExpect(jsonPath("$.effectiveDate").value(DEFAULT_EFFECTIVE_DATE.toString()))
            .andExpect(jsonPath("$.discontinuedDate").value(DEFAULT_DISCONTINUED_DATE.toString()))
            .andExpect(jsonPath("$.drv").value(DEFAULT_DRV.toString()))
            .andExpect(jsonPath("$.cpi").value(DEFAULT_CPI.toString()))
            .andExpect(jsonPath("$.di").value(DEFAULT_DI.toString()))
            .andExpect(jsonPath("$.intPt").value(DEFAULT_INT_PT.toString()))
            .andExpect(jsonPath("$.untPt").value(DEFAULT_UNT_PT.toString()));
    }

    @Test
    public void getNonExistingRoutingquery() throws Exception {
        // Get the routingquery
        restRoutingqueryMockMvc.perform(get("/api/routingqueries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRoutingquery() throws Exception {
        // Initialize the database
        routingqueryRepository.save(routingquery);
        int databaseSizeBeforeUpdate = routingqueryRepository.findAll().size();

        // Update the routingquery
        RoutingQuery updatedRoutingquery = routingqueryRepository.findOne(routingquery.getId());
        updatedRoutingquery
            .linkNo(UPDATED_LINK_NO)
            .src(UPDATED_SRC)
            .cxr(UPDATED_CXR)
            .tarNo(UPDATED_TAR_NO)
            .routingNo(UPDATED_ROUTING_NO)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .discontinuedDate(UPDATED_DISCONTINUED_DATE)
            .drv(UPDATED_DRV)
            .cpi(UPDATED_CPI)
            .di(UPDATED_DI)
            .intPt(UPDATED_INT_PT)
            .untPt(UPDATED_UNT_PT);

        restRoutingqueryMockMvc.perform(put("/api/routingqueries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRoutingquery)))
            .andExpect(status().isOk());

        // Validate the Routingquery in the database
        List<RoutingQuery> routingqueryList = routingqueryRepository.findAll();
        assertThat(routingqueryList).hasSize(databaseSizeBeforeUpdate);
        RoutingQuery testRoutingquery = routingqueryList.get(routingqueryList.size() - 1);
        assertThat(testRoutingquery.getLinkNo()).isEqualTo(UPDATED_LINK_NO);
        assertThat(testRoutingquery.getSrc()).isEqualTo(UPDATED_SRC);
        assertThat(testRoutingquery.getCxr()).isEqualTo(UPDATED_CXR);
        assertThat(testRoutingquery.getTarNo()).isEqualTo(UPDATED_TAR_NO);
        assertThat(testRoutingquery.getRoutingNo()).isEqualTo(UPDATED_ROUTING_NO);
        assertThat(testRoutingquery.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
        assertThat(testRoutingquery.getDiscontinuedDate()).isEqualTo(UPDATED_DISCONTINUED_DATE);
        assertThat(testRoutingquery.getDrv()).isEqualTo(UPDATED_DRV);
        assertThat(testRoutingquery.getCpi()).isEqualTo(UPDATED_CPI);
        assertThat(testRoutingquery.getDi()).isEqualTo(UPDATED_DI);
        assertThat(testRoutingquery.getIntPt()).isEqualTo(UPDATED_INT_PT);
        assertThat(testRoutingquery.getUntPt()).isEqualTo(UPDATED_UNT_PT);
    }

    @Test
    public void updateNonExistingRoutingquery() throws Exception {
        int databaseSizeBeforeUpdate = routingqueryRepository.findAll().size();

        // Create the Routingquery

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRoutingqueryMockMvc.perform(put("/api/routingqueries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(routingquery)))
            .andExpect(status().isCreated());

        // Validate the Routingquery in the database
        List<RoutingQuery> routingqueryList = routingqueryRepository.findAll();
        assertThat(routingqueryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteRoutingquery() throws Exception {
        // Initialize the database
        routingqueryRepository.save(routingquery);
        int databaseSizeBeforeDelete = routingqueryRepository.findAll().size();

        // Get the routingquery
        restRoutingqueryMockMvc.perform(delete("/api/routingqueries/{id}", routingquery.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RoutingQuery> routingqueryList = routingqueryRepository.findAll();
        assertThat(routingqueryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoutingQuery.class);
        RoutingQuery routingquery1 = new RoutingQuery();
        routingquery1.setId("id1");
        RoutingQuery routingquery2 = new RoutingQuery();
        routingquery2.setId(routingquery1.getId());
        assertThat(routingquery1).isEqualTo(routingquery2);
        routingquery2.setId("id2");
        assertThat(routingquery1).isNotEqualTo(routingquery2);
        routingquery1.setId(null);
        assertThat(routingquery1).isNotEqualTo(routingquery2);
    }
}
