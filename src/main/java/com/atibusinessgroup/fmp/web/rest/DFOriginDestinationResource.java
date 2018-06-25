package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.DFOriginDestination;

import com.atibusinessgroup.fmp.repository.DFOriginDestinationRepository;
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
 * REST controller for managing DFOriginDestination.
 */
@RestController
@RequestMapping("/api")
public class DFOriginDestinationResource {

    private final Logger log = LoggerFactory.getLogger(DFOriginDestinationResource.class);

    private static final String ENTITY_NAME = "dFOriginDestination";

    private final DFOriginDestinationRepository dFOriginDestinationRepository;

    public DFOriginDestinationResource(DFOriginDestinationRepository dFOriginDestinationRepository) {
        this.dFOriginDestinationRepository = dFOriginDestinationRepository;
    }

    /**
     * POST  /df-origin-destinations : Create a new dFOriginDestination.
     *
     * @param dFOriginDestination the dFOriginDestination to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dFOriginDestination, or with status 400 (Bad Request) if the dFOriginDestination has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/df-origin-destinations")
    @Timed
    public ResponseEntity<DFOriginDestination> createDFOriginDestination(@RequestBody DFOriginDestination dFOriginDestination) throws URISyntaxException {
        log.debug("REST request to save DFOriginDestination : {}", dFOriginDestination);
        if (dFOriginDestination.getId() != null) {
            throw new BadRequestAlertException("A new dFOriginDestination cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DFOriginDestination result = dFOriginDestinationRepository.save(dFOriginDestination);
        return ResponseEntity.created(new URI("/api/df-origin-destinations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /df-origin-destinations : Updates an existing dFOriginDestination.
     *
     * @param dFOriginDestination the dFOriginDestination to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dFOriginDestination,
     * or with status 400 (Bad Request) if the dFOriginDestination is not valid,
     * or with status 500 (Internal Server Error) if the dFOriginDestination couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/df-origin-destinations")
    @Timed
    public ResponseEntity<DFOriginDestination> updateDFOriginDestination(@RequestBody DFOriginDestination dFOriginDestination) throws URISyntaxException {
        log.debug("REST request to update DFOriginDestination : {}", dFOriginDestination);
        if (dFOriginDestination.getId() == null) {
            return createDFOriginDestination(dFOriginDestination);
        }
        DFOriginDestination result = dFOriginDestinationRepository.save(dFOriginDestination);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dFOriginDestination.getId().toString()))
            .body(result);
    }

    /**
     * GET  /df-origin-destinations : get all the dFOriginDestinations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dFOriginDestinations in body
     */
    @GetMapping("/df-origin-destinations")
    @Timed
    public ResponseEntity<List<DFOriginDestination>> getAllDFOriginDestinations(Pageable pageable) {
        log.debug("REST request to get a page of DFOriginDestinations");
        Page<DFOriginDestination> page = dFOriginDestinationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/df-origin-destinations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /df-origin-destinations/:id : get the "id" dFOriginDestination.
     *
     * @param id the id of the dFOriginDestination to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dFOriginDestination, or with status 404 (Not Found)
     */
    @GetMapping("/df-origin-destinations/{id}")
    @Timed
    public ResponseEntity<DFOriginDestination> getDFOriginDestination(@PathVariable String id) {
        log.debug("REST request to get DFOriginDestination : {}", id);
        DFOriginDestination dFOriginDestination = dFOriginDestinationRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dFOriginDestination));
    }

    /**
     * DELETE  /df-origin-destinations/:id : delete the "id" dFOriginDestination.
     *
     * @param id the id of the dFOriginDestination to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/df-origin-destinations/{id}")
    @Timed
    public ResponseEntity<Void> deleteDFOriginDestination(@PathVariable String id) {
        log.debug("REST request to delete DFOriginDestination : {}", id);
        dFOriginDestinationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
