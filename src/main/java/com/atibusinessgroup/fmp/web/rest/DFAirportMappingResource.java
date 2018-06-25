package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.DFAirportMapping;

import com.atibusinessgroup.fmp.repository.DFAirportMappingRepository;
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
 * REST controller for managing DFAirportMapping.
 */
@RestController
@RequestMapping("/api")
public class DFAirportMappingResource {

    private final Logger log = LoggerFactory.getLogger(DFAirportMappingResource.class);

    private static final String ENTITY_NAME = "dFAirportMapping";

    private final DFAirportMappingRepository dFAirportMappingRepository;

    public DFAirportMappingResource(DFAirportMappingRepository dFAirportMappingRepository) {
        this.dFAirportMappingRepository = dFAirportMappingRepository;
    }

    /**
     * POST  /df-airport-mappings : Create a new dFAirportMapping.
     *
     * @param dFAirportMapping the dFAirportMapping to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dFAirportMapping, or with status 400 (Bad Request) if the dFAirportMapping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/df-airport-mappings")
    @Timed
    public ResponseEntity<DFAirportMapping> createDFAirportMapping(@RequestBody DFAirportMapping dFAirportMapping) throws URISyntaxException {
        log.debug("REST request to save DFAirportMapping : {}", dFAirportMapping);
        if (dFAirportMapping.getId() != null) {
            throw new BadRequestAlertException("A new dFAirportMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DFAirportMapping result = dFAirportMappingRepository.save(dFAirportMapping);
        return ResponseEntity.created(new URI("/api/df-airport-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /df-airport-mappings : Updates an existing dFAirportMapping.
     *
     * @param dFAirportMapping the dFAirportMapping to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dFAirportMapping,
     * or with status 400 (Bad Request) if the dFAirportMapping is not valid,
     * or with status 500 (Internal Server Error) if the dFAirportMapping couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/df-airport-mappings")
    @Timed
    public ResponseEntity<DFAirportMapping> updateDFAirportMapping(@RequestBody DFAirportMapping dFAirportMapping) throws URISyntaxException {
        log.debug("REST request to update DFAirportMapping : {}", dFAirportMapping);
        if (dFAirportMapping.getId() == null) {
            return createDFAirportMapping(dFAirportMapping);
        }
        DFAirportMapping result = dFAirportMappingRepository.save(dFAirportMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dFAirportMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /df-airport-mappings : get all the dFAirportMappings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dFAirportMappings in body
     */
    @GetMapping("/df-airport-mappings")
    @Timed
    public ResponseEntity<List<DFAirportMapping>> getAllDFAirportMappings(Pageable pageable) {
        log.debug("REST request to get a page of DFAirportMappings");
        Page<DFAirportMapping> page = dFAirportMappingRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/df-airport-mappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /df-airport-mappings/:id : get the "id" dFAirportMapping.
     *
     * @param id the id of the dFAirportMapping to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dFAirportMapping, or with status 404 (Not Found)
     */
    @GetMapping("/df-airport-mappings/{id}")
    @Timed
    public ResponseEntity<DFAirportMapping> getDFAirportMapping(@PathVariable String id) {
        log.debug("REST request to get DFAirportMapping : {}", id);
        DFAirportMapping dFAirportMapping = dFAirportMappingRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dFAirportMapping));
    }

    /**
     * DELETE  /df-airport-mappings/:id : delete the "id" dFAirportMapping.
     *
     * @param id the id of the dFAirportMapping to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/df-airport-mappings/{id}")
    @Timed
    public ResponseEntity<Void> deleteDFAirportMapping(@PathVariable String id) {
        log.debug("REST request to delete DFAirportMapping : {}", id);
        dFAirportMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
