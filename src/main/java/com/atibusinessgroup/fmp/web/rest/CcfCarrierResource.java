package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.CcfCarrier;

import com.atibusinessgroup.fmp.repository.CcfCarrierRepository;
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
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CcfCarrier.
 */
@RestController
@RequestMapping("/api")
public class CcfCarrierResource {

    private final Logger log = LoggerFactory.getLogger(CcfCarrierResource.class);

    private static final String ENTITY_NAME = "ccfCarrier";

    private final CcfCarrierRepository ccfCarrierRepository;

    public CcfCarrierResource(CcfCarrierRepository ccfCarrierRepository) {
        this.ccfCarrierRepository = ccfCarrierRepository;
    }

    /**
     * POST  /ccf-carriers : Create a new ccfCarrier.
     *
     * @param ccfCarrier the ccfCarrier to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ccfCarrier, or with status 400 (Bad Request) if the ccfCarrier has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ccf-carriers")
    @Timed
    public ResponseEntity<CcfCarrier> createCcfCarrier(@RequestBody CcfCarrier ccfCarrier) throws URISyntaxException {
        log.debug("REST request to save CcfCarrier : {}", ccfCarrier);
        if (ccfCarrier.getId() != null) {
            throw new BadRequestAlertException("A new ccfCarrier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CcfCarrier result = ccfCarrierRepository.save(ccfCarrier);
        return ResponseEntity.created(new URI("/api/ccf-carriers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ccf-carriers : Updates an existing ccfCarrier.
     *
     * @param ccfCarrier the ccfCarrier to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ccfCarrier,
     * or with status 400 (Bad Request) if the ccfCarrier is not valid,
     * or with status 500 (Internal Server Error) if the ccfCarrier couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ccf-carriers")
    @Timed
    public ResponseEntity<CcfCarrier> updateCcfCarrier(@RequestBody CcfCarrier ccfCarrier) throws URISyntaxException {
        log.debug("REST request to update CcfCarrier : {}", ccfCarrier);
        if (ccfCarrier.getId() == null) {
            return createCcfCarrier(ccfCarrier);
        }
        CcfCarrier result = ccfCarrierRepository.save(ccfCarrier);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ccfCarrier.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ccf-carriers : get all the ccfCarriers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ccfCarriers in body
     */
    @GetMapping("/ccf-carriers")
    @Timed
    public ResponseEntity<List<CcfCarrier>> getAllCcfCarriers(Pageable pageable) {
        log.debug("REST request to get a page of CcfCarriers");
        Page<CcfCarrier> page = ccfCarrierRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ccf-carriers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ccf-carriers/:id : get the "id" ccfCarrier.
     *
     * @param id the id of the ccfCarrier to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ccfCarrier, or with status 404 (Not Found)
     */
    @GetMapping("/ccf-carriers/{id}")
    @Timed
    public ResponseEntity<CcfCarrier> getCcfCarrier(@PathVariable String id) {
        log.debug("REST request to get CcfCarrier : {}", id);
        CcfCarrier ccfCarrier = ccfCarrierRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ccfCarrier));
    }

    /**
     * DELETE  /ccf-carriers/:id : delete the "id" ccfCarrier.
     *
     * @param id the id of the ccfCarrier to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ccf-carriers/{id}")
    @Timed
    public ResponseEntity<Void> deleteCcfCarrier(@PathVariable String id) {
        log.debug("REST request to delete CcfCarrier : {}", id);
        ccfCarrierRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
    
    /**
     * GET  /ccf-carriers : get all the ccfCarriers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ccfCarriers in body
     */
    @GetMapping("/ccf-carriers/getAll")
    @Timed
    public ResponseEntity<List<CcfCarrier>> getAllCarriers() {
        List<CcfCarrier> result = ccfCarrierRepository.findAll();
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
