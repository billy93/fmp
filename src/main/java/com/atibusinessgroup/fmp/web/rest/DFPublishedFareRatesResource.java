package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.DFPublishedFareRates;

import com.atibusinessgroup.fmp.repository.DFPublishedFareRatesRepository;
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
 * REST controller for managing DFPublishedFareRates.
 */
@RestController
@RequestMapping("/api")
public class DFPublishedFareRatesResource {

    private final Logger log = LoggerFactory.getLogger(DFPublishedFareRatesResource.class);

    private static final String ENTITY_NAME = "dFPublishedFareRates";

    private final DFPublishedFareRatesRepository dFPublishedFareRatesRepository;

    public DFPublishedFareRatesResource(DFPublishedFareRatesRepository dFPublishedFareRatesRepository) {
        this.dFPublishedFareRatesRepository = dFPublishedFareRatesRepository;
    }

    /**
     * POST  /df-published-fare-rates : Create a new dFPublishedFareRates.
     *
     * @param dFPublishedFareRates the dFPublishedFareRates to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dFPublishedFareRates, or with status 400 (Bad Request) if the dFPublishedFareRates has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/df-published-fare-rates")
    @Timed
    public ResponseEntity<DFPublishedFareRates> createDFPublishedFareRates(@RequestBody DFPublishedFareRates dFPublishedFareRates) throws URISyntaxException {
        log.debug("REST request to save DFPublishedFareRates : {}", dFPublishedFareRates);
        if (dFPublishedFareRates.getId() != null) {
            throw new BadRequestAlertException("A new dFPublishedFareRates cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DFPublishedFareRates result = dFPublishedFareRatesRepository.save(dFPublishedFareRates);
        return ResponseEntity.created(new URI("/api/df-published-fare-rates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /df-published-fare-rates : Updates an existing dFPublishedFareRates.
     *
     * @param dFPublishedFareRates the dFPublishedFareRates to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dFPublishedFareRates,
     * or with status 400 (Bad Request) if the dFPublishedFareRates is not valid,
     * or with status 500 (Internal Server Error) if the dFPublishedFareRates couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/df-published-fare-rates")
    @Timed
    public ResponseEntity<DFPublishedFareRates> updateDFPublishedFareRates(@RequestBody DFPublishedFareRates dFPublishedFareRates) throws URISyntaxException {
        log.debug("REST request to update DFPublishedFareRates : {}", dFPublishedFareRates);
        if (dFPublishedFareRates.getId() == null) {
            return createDFPublishedFareRates(dFPublishedFareRates);
        }
        DFPublishedFareRates result = dFPublishedFareRatesRepository.save(dFPublishedFareRates);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dFPublishedFareRates.getId().toString()))
            .body(result);
    }

    /**
     * GET  /df-published-fare-rates : get all the dFPublishedFareRates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dFPublishedFareRates in body
     */
    @GetMapping("/df-published-fare-rates")
    @Timed
    public ResponseEntity<List<DFPublishedFareRates>> getAllDFPublishedFareRates(Pageable pageable) {
        log.debug("REST request to get a page of DFPublishedFareRates");
        Page<DFPublishedFareRates> page = dFPublishedFareRatesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/df-published-fare-rates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /df-published-fare-rates/:id : get the "id" dFPublishedFareRates.
     *
     * @param id the id of the dFPublishedFareRates to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dFPublishedFareRates, or with status 404 (Not Found)
     */
    @GetMapping("/df-published-fare-rates/{id}")
    @Timed
    public ResponseEntity<DFPublishedFareRates> getDFPublishedFareRates(@PathVariable String id) {
        log.debug("REST request to get DFPublishedFareRates : {}", id);
        DFPublishedFareRates dFPublishedFareRates = dFPublishedFareRatesRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dFPublishedFareRates));
    }

    /**
     * DELETE  /df-published-fare-rates/:id : delete the "id" dFPublishedFareRates.
     *
     * @param id the id of the dFPublishedFareRates to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/df-published-fare-rates/{id}")
    @Timed
    public ResponseEntity<Void> deleteDFPublishedFareRates(@PathVariable String id) {
        log.debug("REST request to delete DFPublishedFareRates : {}", id);
        dFPublishedFareRatesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
