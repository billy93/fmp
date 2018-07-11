package com.atibusinessgroup.fmp.web.rest;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.City;
import com.atibusinessgroup.fmp.domain.CityGroup;
import com.atibusinessgroup.fmp.repository.CityGroupRepository;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing CityGroup.
 */
@RestController
@RequestMapping("/api")
public class CityGroupResource {

    private final Logger log = LoggerFactory.getLogger(CityGroupResource.class);

    private static final String ENTITY_NAME = "cityGroup";

    private final CityGroupRepository cityGroupRepository;

    public CityGroupResource(CityGroupRepository cityGroupRepository) {
        this.cityGroupRepository = cityGroupRepository;
    }

    /**
     * POST  /city-groups : Create a new cityGroup.
     *
     * @param cityGroup the cityGroup to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cityGroup, or with status 400 (Bad Request) if the cityGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/city-groups")
    @Timed
    public ResponseEntity<CityGroup> createCityGroup(@RequestBody CityGroup cityGroup) throws URISyntaxException {
        log.debug("REST request to save CityGroup : {}", cityGroup);
        if (cityGroup.getId() != null) {
            throw new BadRequestAlertException("A new cityGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        CityGroup check = cityGroupRepository.findOneByCode(cityGroup.getCode());
        if(check == null) {
        	CityGroup result = cityGroupRepository.save(cityGroup);
        	
        	return ResponseEntity.created(new URI("/api/city-groups/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                    .body(result);
        }
        else {
        	throw new BadRequestAlertException("A cityGroup with code "+cityGroup.getCode()+" already exist in database", ENTITY_NAME, "idexists");
        }
        
    }

    /**
     * PUT  /city-groups : Updates an existing cityGroup.
     *
     * @param cityGroup the cityGroup to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cityGroup,
     * or with status 400 (Bad Request) if the cityGroup is not valid,
     * or with status 500 (Internal Server Error) if the cityGroup couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/city-groups")
    @Timed
    public ResponseEntity<CityGroup> updateCityGroup(@RequestBody CityGroup cityGroup) throws URISyntaxException {
        log.debug("REST request to update CityGroup : {}", cityGroup);
        if (cityGroup.getId() == null) {
            return createCityGroup(cityGroup);
        }
        CityGroup result = null;
        CityGroup current = cityGroupRepository.findOne(cityGroup.getId());
        if(!current.getCode().contentEquals(cityGroup.getCode())){
        	CityGroup check = cityGroupRepository.findOneByCode(cityGroup.getCode());
        	if(check == null) {
        		//save
        		result = cityGroupRepository.save(cityGroup);
        	}
        	else {
        		throw new BadRequestAlertException("A cityGroup with code "+cityGroup.getCode()+" already exist in database", ENTITY_NAME, "idexists");
        	}
        }
        else {
        	result = cityGroupRepository.save(cityGroup);
        }
        
    	
    	return ResponseEntity.created(new URI("/api/city-groups/" + result.getId()))
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
//        CityGroup check = cityGroupRepository.findOneByCode(cityGroup.getCode());
//        if(check != null) {
//        	CityGroup result = cityGroupRepository.save(cityGroup);
//        	
//        	return ResponseEntity.created(new URI("/api/city-groups/" + result.getId()))
//                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
//                    .body(result);
//        }
//        else {
//        	return createCityGroup(cityGroup);
//        }
    }

    public static class CityGroupFilter{
    	
    	
    	public CityGroupFilter() {
			// TODO Auto-generated constructor stub
		}
    	
		public City cities;

        private String code;
        private String operator;
        private String description;
        
		public City getCities() {
			return cities;
		}
		public void setCities(City cities) {
			this.cities = cities;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		@Override
		public String toString() {
			return "CityGroupFilter [cities=" + cities + ", code=" + code + ", operator=" + operator + ", description="
					+ description + "]";
		}        
    }
    /**
     * GET  /city-groups : get all the cityGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cityGroups in body
     * @throws IllegalAccessException 
     */
    @GetMapping("/city-groups")
    @Timed
    public ResponseEntity<List<CityGroup>> getAllCityGroups(CityGroupFilter filter, Pageable pageable) throws IllegalAccessException {
        log.debug("REST request to get a page of CityGroups {}", filter);
        if(checkNull(filter)) {
        	 Page<CityGroup> page = cityGroupRepository.findAll(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/city-groups");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }else {
        	 Page<CityGroup> page = cityGroupRepository.findCustom(filter, pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/city-groups");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }
       
    }
    
    public boolean checkNull(Object object) throws IllegalAccessException {
        for (Field f : object.getClass().getDeclaredFields()) {
        	f.setAccessible(true);
            if (f.get(object) != null) {
                return false;
            }
        }
        return true;            
    }
    /**
     * GET  /city-groups : get all the city-groups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cities in body
     */
    @GetMapping("/city-groups/getAll")
    @Timed
    public ResponseEntity<List<CityGroup>> getAllCityGroups() {
        log.debug("REST request to get a page of Cities");
        List<CityGroup> page = cityGroupRepository.findAll();
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cities");
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    /**
     * GET  /city-groups/:id : get the "id" cityGroup.
     *
     * @param id the id of the cityGroup to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cityGroup, or with status 404 (Not Found)
     */
    @GetMapping("/city-groups/{id}")
    @Timed
    public ResponseEntity<CityGroup> getCityGroup(@PathVariable String id) {
        log.debug("REST request to get CityGroup : {}", id);
        CityGroup cityGroup = cityGroupRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(cityGroup));
    }

    /**
     * DELETE  /city-groups/:id : delete the "id" cityGroup.
     *
     * @param id the id of the cityGroup to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/city-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteCityGroup(@PathVariable String id) {
        log.debug("REST request to delete CityGroup : {}", id);
        cityGroupRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
