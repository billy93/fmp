package com.atibusinessgroup.fmp.web.rest;

import com.atibusinessgroup.fmp.FmpApp;

import com.atibusinessgroup.fmp.domain.City;
import com.atibusinessgroup.fmp.repository.CityRepository;
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
 * Test class for the CityResource REST controller.
 *
 * @see CityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FmpApp.class)
public class CityResourceIntTest {

    private static final String DEFAULT_CITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CITY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CITY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCityMockMvc;

    private City city;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CityResource cityResource = new CityResource(cityRepository);
        this.restCityMockMvc = MockMvcBuilders.standaloneSetup(cityResource)
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
    public static City createEntity() {
        City city = new City()
            .cityName(DEFAULT_CITY_NAME)
            .cityCode(DEFAULT_CITY_CODE)
            .countryName(DEFAULT_COUNTRY_NAME)
            .countryCode(DEFAULT_COUNTRY_CODE);
        return city;
    }

    @Before
    public void initTest() {
        cityRepository.deleteAll();
        city = createEntity();
    }

    @Test
    public void createCity() throws Exception {
        int databaseSizeBeforeCreate = cityRepository.findAll().size();

        // Create the City
        restCityMockMvc.perform(post("/api/cities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(city)))
            .andExpect(status().isCreated());

        // Validate the City in the database
        List<City> cityList = cityRepository.findAll();
        assertThat(cityList).hasSize(databaseSizeBeforeCreate + 1);
        City testCity = cityList.get(cityList.size() - 1);
        assertThat(testCity.getCityName()).isEqualTo(DEFAULT_CITY_NAME);
        assertThat(testCity.getCityCode()).isEqualTo(DEFAULT_CITY_CODE);
        assertThat(testCity.getCountryName()).isEqualTo(DEFAULT_COUNTRY_NAME);
        assertThat(testCity.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
    }

    @Test
    public void createCityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cityRepository.findAll().size();

        // Create the City with an existing ID
        city.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCityMockMvc.perform(post("/api/cities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(city)))
            .andExpect(status().isBadRequest());

        // Validate the City in the database
        List<City> cityList = cityRepository.findAll();
        assertThat(cityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllCities() throws Exception {
        // Initialize the database
        cityRepository.save(city);

        // Get all the cityList
        restCityMockMvc.perform(get("/api/cities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(city.getId())))
            .andExpect(jsonPath("$.[*].cityName").value(hasItem(DEFAULT_CITY_NAME.toString())))
            .andExpect(jsonPath("$.[*].cityCode").value(hasItem(DEFAULT_CITY_CODE.toString())))
            .andExpect(jsonPath("$.[*].countryName").value(hasItem(DEFAULT_COUNTRY_NAME.toString())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE.toString())));
    }

    @Test
    public void getCity() throws Exception {
        // Initialize the database
        cityRepository.save(city);

        // Get the city
        restCityMockMvc.perform(get("/api/cities/{id}", city.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(city.getId()))
            .andExpect(jsonPath("$.cityName").value(DEFAULT_CITY_NAME.toString()))
            .andExpect(jsonPath("$.cityCode").value(DEFAULT_CITY_CODE.toString()))
            .andExpect(jsonPath("$.countryName").value(DEFAULT_COUNTRY_NAME.toString()))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE.toString()));
    }

    @Test
    public void getNonExistingCity() throws Exception {
        // Get the city
        restCityMockMvc.perform(get("/api/cities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCity() throws Exception {
        // Initialize the database
        cityRepository.save(city);
        int databaseSizeBeforeUpdate = cityRepository.findAll().size();

        // Update the city
        City updatedCity = cityRepository.findOne(city.getId());
        updatedCity
            .cityName(UPDATED_CITY_NAME)
            .cityCode(UPDATED_CITY_CODE)
            .countryName(UPDATED_COUNTRY_NAME)
            .countryCode(UPDATED_COUNTRY_CODE);

        restCityMockMvc.perform(put("/api/cities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCity)))
            .andExpect(status().isOk());

        // Validate the City in the database
        List<City> cityList = cityRepository.findAll();
        assertThat(cityList).hasSize(databaseSizeBeforeUpdate);
        City testCity = cityList.get(cityList.size() - 1);
        assertThat(testCity.getCityName()).isEqualTo(UPDATED_CITY_NAME);
        assertThat(testCity.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
        assertThat(testCity.getCountryName()).isEqualTo(UPDATED_COUNTRY_NAME);
        assertThat(testCity.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
    }

    @Test
    public void updateNonExistingCity() throws Exception {
        int databaseSizeBeforeUpdate = cityRepository.findAll().size();

        // Create the City

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCityMockMvc.perform(put("/api/cities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(city)))
            .andExpect(status().isCreated());

        // Validate the City in the database
        List<City> cityList = cityRepository.findAll();
        assertThat(cityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteCity() throws Exception {
        // Initialize the database
        cityRepository.save(city);
        int databaseSizeBeforeDelete = cityRepository.findAll().size();

        // Get the city
        restCityMockMvc.perform(delete("/api/cities/{id}", city.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<City> cityList = cityRepository.findAll();
        assertThat(cityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(City.class);
        City city1 = new City();
        city1.setId("id1");
        City city2 = new City();
        city2.setId(city1.getId());
        assertThat(city1).isEqualTo(city2);
        city2.setId("id2");
        assertThat(city1).isNotEqualTo(city2);
        city1.setId(null);
        assertThat(city1).isNotEqualTo(city2);
    }
}
