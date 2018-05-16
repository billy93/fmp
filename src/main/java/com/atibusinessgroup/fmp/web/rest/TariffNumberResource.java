package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.TariffNumber;

import com.atibusinessgroup.fmp.repository.TariffNumberRepository;
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
 * REST controller for managing TariffNumber.
 */
@RestController
@RequestMapping("/api")
public class TariffNumberResource {

    private final Logger log = LoggerFactory.getLogger(TariffNumberResource.class);

    private static final String ENTITY_NAME = "tariffNumber";

    private final TariffNumberRepository tariffNumberRepository;

    public TariffNumberResource(TariffNumberRepository tariffNumberRepository) {
        this.tariffNumberRepository = tariffNumberRepository;
    }

    /**
     * POST  /tariff-numbers : Create a new tariffNumber.
     *
     * @param tariffNumber the tariffNumber to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tariffNumber, or with status 400 (Bad Request) if the tariffNumber has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tariff-numbers")
    @Timed
    public ResponseEntity<TariffNumber> createTariffNumber(@RequestBody TariffNumber tariffNumber) throws URISyntaxException {
        log.debug("REST request to save TariffNumber : {}", tariffNumber);
        if (tariffNumber.getId() != null) {
            throw new BadRequestAlertException("A new tariffNumber cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TariffNumber result = tariffNumberRepository.save(tariffNumber);
        return ResponseEntity.created(new URI("/api/tariff-numbers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tariff-numbers : Updates an existing tariffNumber.
     *
     * @param tariffNumber the tariffNumber to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tariffNumber,
     * or with status 400 (Bad Request) if the tariffNumber is not valid,
     * or with status 500 (Internal Server Error) if the tariffNumber couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tariff-numbers")
    @Timed
    public ResponseEntity<TariffNumber> updateTariffNumber(@RequestBody TariffNumber tariffNumber) throws URISyntaxException {
        log.debug("REST request to update TariffNumber : {}", tariffNumber);
        if (tariffNumber.getId() == null) {
            return createTariffNumber(tariffNumber);
        }
        TariffNumber result = tariffNumberRepository.save(tariffNumber);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tariffNumber.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tariff-numbers : get all the tariffNumbers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tariffNumbers in body
     */
    @GetMapping("/tariff-numbers")
    @Timed
    public ResponseEntity<List<TariffNumber>> getAllTariffNumbers(Pageable pageable) {
        log.debug("REST request to get a page of TariffNumbers");
        Page<TariffNumber> page = tariffNumberRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tariff-numbers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tariff-numbers/:id : get the "id" tariffNumber.
     *
     * @param id the id of the tariffNumber to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tariffNumber, or with status 404 (Not Found)
     */
    @GetMapping("/tariff-numbers/{id}")
    @Timed
    public ResponseEntity<TariffNumber> getTariffNumber(@PathVariable String id) {
        log.debug("REST request to get TariffNumber : {}", id);
        TariffNumber tariffNumber = tariffNumberRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tariffNumber));
    }

    /**
     * DELETE  /tariff-numbers/:id : delete the "id" tariffNumber.
     *
     * @param id the id of the tariffNumber to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tariff-numbers/{id}")
    @Timed
    public ResponseEntity<Void> deleteTariffNumber(@PathVariable String id) {
        log.debug("REST request to delete TariffNumber : {}", id);
        tariffNumberRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
