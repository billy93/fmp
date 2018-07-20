package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.RbdColorMapping;

import com.atibusinessgroup.fmp.repository.RbdColorMappingRepository;
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
 * REST controller for managing RbdColorMapping.
 */
@RestController
@RequestMapping("/api")
public class RbdColorMappingResource {

    private final Logger log = LoggerFactory.getLogger(RbdColorMappingResource.class);

    private static final String ENTITY_NAME = "rbdColorMapping";

    private final RbdColorMappingRepository rbdColorMappingRepository;

    public RbdColorMappingResource(RbdColorMappingRepository rbdColorMappingRepository) {
        this.rbdColorMappingRepository = rbdColorMappingRepository;
    }

    /**
     * POST  /rbd-color-mappings : Create a new rbdColorMapping.
     *
     * @param rbdColorMapping the rbdColorMapping to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rbdColorMapping, or with status 400 (Bad Request) if the rbdColorMapping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rbd-color-mappings")
    @Timed
    public ResponseEntity<RbdColorMapping> createRbdColorMapping(@RequestBody RbdColorMapping rbdColorMapping) throws URISyntaxException {
        log.debug("REST request to save RbdColorMapping : {}", rbdColorMapping);
        if (rbdColorMapping.getId() != null) {
            throw new BadRequestAlertException("A new rbdColorMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RbdColorMapping result = null;
        try {
        	result = rbdColorMappingRepository.save(rbdColorMapping);
        } catch (Exception e) {
        	if(e.getMessage().contains("rite failed with error code 11000 and error message 'E11000 duplicate key error collection")) {
        		throw new BadRequestAlertException("RBD Color has already been mapped", ENTITY_NAME, "RBDexists");
        	}
        	
        }
        return ResponseEntity.created(new URI("/api/rbd-color-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rbd-color-mappings : Updates an existing rbdColorMapping.
     *
     * @param rbdColorMapping the rbdColorMapping to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rbdColorMapping,
     * or with status 400 (Bad Request) if the rbdColorMapping is not valid,
     * or with status 500 (Internal Server Error) if the rbdColorMapping couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rbd-color-mappings")
    @Timed
    public ResponseEntity<RbdColorMapping> updateRbdColorMapping(@RequestBody RbdColorMapping rbdColorMapping) throws URISyntaxException {
        log.debug("REST request to update RbdColorMapping : {}", rbdColorMapping);
        if (rbdColorMapping.getId() == null) {
            return createRbdColorMapping(rbdColorMapping);
        }
        RbdColorMapping result = null;
        try {
        	result = rbdColorMappingRepository.save(rbdColorMapping);
        } catch (Exception e) {
        	if(e.getMessage().contains("rite failed with error code 11000 and error message 'E11000 duplicate key error collection")) {
        		throw new BadRequestAlertException("RBD Color has already been mapped", ENTITY_NAME, "RBDexists");
        	}
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rbdColorMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rbd-color-mappings : get all the rbdColorMappings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rbdColorMappings in body
     */
    @GetMapping("/rbd-color-mappings")
    @Timed
    public ResponseEntity<List<RbdColorMapping>> getAllRbdColorMappings(Pageable pageable) {
        log.debug("REST request to get a page of RbdColorMappings");
        Page<RbdColorMapping> page = rbdColorMappingRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rbd-color-mappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /rbd-color-mappings/:id : get the "id" rbdColorMapping.
     *
     * @param id the id of the rbdColorMapping to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rbdColorMapping, or with status 404 (Not Found)
     */
    @GetMapping("/rbd-color-mappings/{id}")
    @Timed
    public ResponseEntity<RbdColorMapping> getRbdColorMapping(@PathVariable String id) {
        log.debug("REST request to get RbdColorMapping : {}", id);
        RbdColorMapping rbdColorMapping = rbdColorMappingRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rbdColorMapping));
    }

    /**
     * DELETE  /rbd-color-mappings/:id : delete the "id" rbdColorMapping.
     *
     * @param id the id of the rbdColorMapping to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rbd-color-mappings/{id}")
    @Timed
    public ResponseEntity<Void> deleteRbdColorMapping(@PathVariable String id) {
        log.debug("REST request to delete RbdColorMapping : {}", id);
        rbdColorMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
