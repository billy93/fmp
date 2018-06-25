package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.DFFareBasisGroupMapping;

import com.atibusinessgroup.fmp.repository.DFFareBasisGroupMappingRepository;
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
 * REST controller for managing DFFareBasisGroupMapping.
 */
@RestController
@RequestMapping("/api")
public class DFFareBasisGroupMappingResource {

    private final Logger log = LoggerFactory.getLogger(DFFareBasisGroupMappingResource.class);

    private static final String ENTITY_NAME = "dFFareBasisGroupMapping";

    private final DFFareBasisGroupMappingRepository dFFareBasisGroupMappingRepository;

    public DFFareBasisGroupMappingResource(DFFareBasisGroupMappingRepository dFFareBasisGroupMappingRepository) {
        this.dFFareBasisGroupMappingRepository = dFFareBasisGroupMappingRepository;
    }

    /**
     * POST  /df-fare-basis-group-mappings : Create a new dFFareBasisGroupMapping.
     *
     * @param dFFareBasisGroupMapping the dFFareBasisGroupMapping to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dFFareBasisGroupMapping, or with status 400 (Bad Request) if the dFFareBasisGroupMapping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/df-fare-basis-group-mappings")
    @Timed
    public ResponseEntity<DFFareBasisGroupMapping> createDFFareBasisGroupMapping(@RequestBody DFFareBasisGroupMapping dFFareBasisGroupMapping) throws URISyntaxException {
        log.debug("REST request to save DFFareBasisGroupMapping : {}", dFFareBasisGroupMapping);
        if (dFFareBasisGroupMapping.getId() != null) {
            throw new BadRequestAlertException("A new dFFareBasisGroupMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DFFareBasisGroupMapping result = dFFareBasisGroupMappingRepository.save(dFFareBasisGroupMapping);
        return ResponseEntity.created(new URI("/api/df-fare-basis-group-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /df-fare-basis-group-mappings : Updates an existing dFFareBasisGroupMapping.
     *
     * @param dFFareBasisGroupMapping the dFFareBasisGroupMapping to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dFFareBasisGroupMapping,
     * or with status 400 (Bad Request) if the dFFareBasisGroupMapping is not valid,
     * or with status 500 (Internal Server Error) if the dFFareBasisGroupMapping couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/df-fare-basis-group-mappings")
    @Timed
    public ResponseEntity<DFFareBasisGroupMapping> updateDFFareBasisGroupMapping(@RequestBody DFFareBasisGroupMapping dFFareBasisGroupMapping) throws URISyntaxException {
        log.debug("REST request to update DFFareBasisGroupMapping : {}", dFFareBasisGroupMapping);
        if (dFFareBasisGroupMapping.getId() == null) {
            return createDFFareBasisGroupMapping(dFFareBasisGroupMapping);
        }
        DFFareBasisGroupMapping result = dFFareBasisGroupMappingRepository.save(dFFareBasisGroupMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dFFareBasisGroupMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /df-fare-basis-group-mappings : get all the dFFareBasisGroupMappings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dFFareBasisGroupMappings in body
     */
    @GetMapping("/df-fare-basis-group-mappings")
    @Timed
    public ResponseEntity<List<DFFareBasisGroupMapping>> getAllDFFareBasisGroupMappings(Pageable pageable) {
        log.debug("REST request to get a page of DFFareBasisGroupMappings");
        Page<DFFareBasisGroupMapping> page = dFFareBasisGroupMappingRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/df-fare-basis-group-mappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /df-fare-basis-group-mappings/:id : get the "id" dFFareBasisGroupMapping.
     *
     * @param id the id of the dFFareBasisGroupMapping to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dFFareBasisGroupMapping, or with status 404 (Not Found)
     */
    @GetMapping("/df-fare-basis-group-mappings/{id}")
    @Timed
    public ResponseEntity<DFFareBasisGroupMapping> getDFFareBasisGroupMapping(@PathVariable String id) {
        log.debug("REST request to get DFFareBasisGroupMapping : {}", id);
        DFFareBasisGroupMapping dFFareBasisGroupMapping = dFFareBasisGroupMappingRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dFFareBasisGroupMapping));
    }

    /**
     * DELETE  /df-fare-basis-group-mappings/:id : delete the "id" dFFareBasisGroupMapping.
     *
     * @param id the id of the dFFareBasisGroupMapping to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/df-fare-basis-group-mappings/{id}")
    @Timed
    public ResponseEntity<Void> deleteDFFareBasisGroupMapping(@PathVariable String id) {
        log.debug("REST request to delete DFFareBasisGroupMapping : {}", id);
        dFFareBasisGroupMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
