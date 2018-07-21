package com.atibusinessgroup.fmp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

import com.atibusinessgroup.fmp.domain.RbdMapping;
import com.atibusinessgroup.fmp.repository.RbdMappingRepository;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing RbdMapping.
 */
@RestController
@RequestMapping("/api")
public class RbdMappingResource {

    private final Logger log = LoggerFactory.getLogger(RbdMappingResource.class);

    private static final String ENTITY_NAME = "rbdMapping";

    private final RbdMappingRepository rbdMappingRepository;

    public RbdMappingResource(RbdMappingRepository rbdMappingRepository) {
        this.rbdMappingRepository = rbdMappingRepository;
    }

    public static class RbdParam {
    	private List<String> carriers = new ArrayList<>();

    	public RbdParam() {}
    	
		public List<String> getCarriers() {
			return carriers;
		}

		public void setCarriers(List<String> carriers) {
			this.carriers = carriers;
		}
    }
    /**
     * POST  /rbd-mappings : Create a new rbdMapping.
     *
     * @param rbdMapping the rbdMapping to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rbdMapping, or with status 400 (Bad Request) if the rbdMapping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rbd-mappings")
    @Timed
    public ResponseEntity<RbdMapping> createRbdMapping(@RequestBody RbdMapping rbdMapping) throws URISyntaxException {
        log.debug("REST request to save RbdMapping : {}", rbdMapping);
        if (rbdMapping.getId() != null) {
            throw new BadRequestAlertException("A new rbdMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RbdMapping result = null;
        
        try {
        	result = rbdMappingRepository.save(rbdMapping);
		} catch (Exception e) {
			if(e.getMessage().contains("rite failed with error code 11000 and error message 'E11000 duplicate key error collection")) {
        		throw new BadRequestAlertException("RBD has already been mapped", ENTITY_NAME, "RBDexists");
        	}
		}
        
        return ResponseEntity.created(new URI("/api/rbd-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rbd-mappings : Updates an existing rbdMapping.
     *
     * @param rbdMapping the rbdMapping to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rbdMapping,
     * or with status 400 (Bad Request) if the rbdMapping is not valid,
     * or with status 500 (Internal Server Error) if the rbdMapping couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rbd-mappings")
    @Timed
    public ResponseEntity<RbdMapping> updateRbdMapping(@RequestBody RbdMapping rbdMapping) throws URISyntaxException {
        log.debug("REST request to update RbdMapping : {}", rbdMapping);
        if (rbdMapping.getId() == null) {
            return createRbdMapping(rbdMapping);
        }
        RbdMapping result = null;
        try {
        	result = rbdMappingRepository.save(rbdMapping);
		} catch (Exception e) {
			if(e.getMessage().contains("rite failed with error code 11000 and error message 'E11000 duplicate key error collection")) {
        		throw new BadRequestAlertException("RBD has already been mapped", ENTITY_NAME, "RBDexists");
        	}
		}
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rbdMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rbd-mappings : get all the rbdMappings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rbdMappings in body
     */
    @GetMapping("/rbd-mappings")
    @Timed
    public ResponseEntity<List<RbdMapping>> getAllRbdMappings(Pageable pageable) {
        log.debug("REST request to get a page of RbdMappings");
        Page<RbdMapping> page = rbdMappingRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rbd-mappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /rbd-mappings/:id : get the "id" rbdMapping.
     *
     * @param id the id of the rbdMapping to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rbdMapping, or with status 404 (Not Found)
     */
    @GetMapping("/rbd-mappings/{id}")
    @Timed
    public ResponseEntity<RbdMapping> getRbdMapping(@PathVariable String id) {
        log.debug("REST request to get RbdMapping : {}", id);
        RbdMapping rbdMapping = rbdMappingRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rbdMapping));
    }

    /**
     * DELETE  /rbd-mappings/:id : delete the "id" rbdMapping.
     *
     * @param id the id of the rbdMapping to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rbd-mappings/{id}")
    @Timed
    public ResponseEntity<Void> deleteRbdMapping(@PathVariable String id) {
        log.debug("REST request to delete RbdMapping : {}", id);
        rbdMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
    
    
    /**
     * GET  /rbd-mappings/:id : get the "id" rbdMapping.
     *
     * @param id the id of the rbdMapping to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rbdMapping, or with status 404 (Not Found)
     */
    @PostMapping("/rbd-mappings/getByCarriers")
    @Timed
    public ResponseEntity<List<RbdMapping>> getRbdMappingByCarriers(@RequestBody RbdParam param) {
        log.debug("REST request to get RbdMapping : {}", param);
        List<RbdMapping> rbdMapping = rbdMappingRepository.findAllByOalCxrIn(param.getCarriers());
        System.out.println("size :: "+rbdMapping.size());
        return ResponseEntity.ok().body(rbdMapping);
    }
}
