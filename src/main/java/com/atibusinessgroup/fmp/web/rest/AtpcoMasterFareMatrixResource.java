package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.AtpcoMasterFareMatrix;
import com.atibusinessgroup.fmp.service.AtpcoMasterFareMatrixService;
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
 * REST controller for managing AtpcoMasterFareMatrix.
 */
@RestController
@RequestMapping("/api")
public class AtpcoMasterFareMatrixResource {

    private final Logger log = LoggerFactory.getLogger(AtpcoMasterFareMatrixResource.class);

    private static final String ENTITY_NAME = "atpcoMasterFareMatrix";

    private final AtpcoMasterFareMatrixService atpcoMasterFareMatrixService;

    public AtpcoMasterFareMatrixResource(AtpcoMasterFareMatrixService atpcoMasterFareMatrixService) {
        this.atpcoMasterFareMatrixService = atpcoMasterFareMatrixService;
    }

    /**
     * POST  /atpco-master-fare-matrices : Create a new atpcoMasterFareMatrix.
     *
     * @param atpcoMasterFareMatrix the atpcoMasterFareMatrix to create
     * @return the ResponseEntity with status 201 (Created) and with body the new atpcoMasterFareMatrix, or with status 400 (Bad Request) if the atpcoMasterFareMatrix has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/atpco-master-fare-matrices")
    @Timed
    public ResponseEntity<AtpcoMasterFareMatrix> createAtpcoMasterFareMatrix(@RequestBody AtpcoMasterFareMatrix atpcoMasterFareMatrix) throws URISyntaxException {
        log.debug("REST request to save AtpcoMasterFareMatrix : {}", atpcoMasterFareMatrix);
        if (atpcoMasterFareMatrix.getId() != null) {
            throw new BadRequestAlertException("A new atpcoMasterFareMatrix cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AtpcoMasterFareMatrix result = atpcoMasterFareMatrixService.save(atpcoMasterFareMatrix);
        return ResponseEntity.created(new URI("/api/atpco-master-fare-matrices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /atpco-master-fare-matrices : Updates an existing atpcoMasterFareMatrix.
     *
     * @param atpcoMasterFareMatrix the atpcoMasterFareMatrix to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated atpcoMasterFareMatrix,
     * or with status 400 (Bad Request) if the atpcoMasterFareMatrix is not valid,
     * or with status 500 (Internal Server Error) if the atpcoMasterFareMatrix couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/atpco-master-fare-matrices")
    @Timed
    public ResponseEntity<AtpcoMasterFareMatrix> updateAtpcoMasterFareMatrix(@RequestBody AtpcoMasterFareMatrix atpcoMasterFareMatrix) throws URISyntaxException {
        log.debug("REST request to update AtpcoMasterFareMatrix : {}", atpcoMasterFareMatrix);
        if (atpcoMasterFareMatrix.getId() == null) {
            return createAtpcoMasterFareMatrix(atpcoMasterFareMatrix);
        }
        AtpcoMasterFareMatrix result = atpcoMasterFareMatrixService.save(atpcoMasterFareMatrix);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, atpcoMasterFareMatrix.getId().toString()))
            .body(result);
    }

    /**
     * GET  /atpco-master-fare-matrices : get all the atpcoMasterFareMatrices.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of atpcoMasterFareMatrices in body
     */
    @GetMapping("/atpco-master-fare-matrices")
    @Timed
    public ResponseEntity<List<AtpcoMasterFareMatrix>> getAllAtpcoMasterFareMatrices(Pageable pageable) {
        log.debug("REST request to get a page of AtpcoMasterFareMatrices");
        Page<AtpcoMasterFareMatrix> page = atpcoMasterFareMatrixService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/atpco-master-fare-matrices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /atpco-master-fare-matrices/:id : get the "id" atpcoMasterFareMatrix.
     *
     * @param id the id of the atpcoMasterFareMatrix to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the atpcoMasterFareMatrix, or with status 404 (Not Found)
     */
    @GetMapping("/atpco-master-fare-matrices/{id}")
    @Timed
    public ResponseEntity<AtpcoMasterFareMatrix> getAtpcoMasterFareMatrix(@PathVariable String id) {
        log.debug("REST request to get AtpcoMasterFareMatrix : {}", id);
        AtpcoMasterFareMatrix atpcoMasterFareMatrix = atpcoMasterFareMatrixService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(atpcoMasterFareMatrix));
    }

    /**
     * DELETE  /atpco-master-fare-matrices/:id : delete the "id" atpcoMasterFareMatrix.
     *
     * @param id the id of the atpcoMasterFareMatrix to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/atpco-master-fare-matrices/{id}")
    @Timed
    public ResponseEntity<Void> deleteAtpcoMasterFareMatrix(@PathVariable String id) {
        log.debug("REST request to delete AtpcoMasterFareMatrix : {}", id);
        atpcoMasterFareMatrixService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
