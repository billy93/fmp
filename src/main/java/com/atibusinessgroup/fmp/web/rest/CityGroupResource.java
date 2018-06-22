package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.CityGroup;
import com.atibusinessgroup.fmp.domain.State;
import com.atibusinessgroup.fmp.repository.CityGroupRepository;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

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

    /**
     * GET  /city-groups : get all the cityGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cityGroups in body
     */
    @GetMapping("/city-groups")
    @Timed
    public ResponseEntity<List<CityGroup>> getAllCityGroups(Pageable pageable) {
        log.debug("REST request to get a page of CityGroups");
        Page<CityGroup> page = cityGroupRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/city-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
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
