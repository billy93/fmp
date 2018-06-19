package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.TariffNumberAddOn;

import com.atibusinessgroup.fmp.repository.TariffNumberAddOnRepository;
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
 * REST controller for managing TariffNumberAddOn.
 */
@RestController
@RequestMapping("/api")
public class TariffNumberAddOnResource {

    private final Logger log = LoggerFactory.getLogger(TariffNumberAddOnResource.class);

    private static final String ENTITY_NAME = "tariffNumberAddOn";

    private final TariffNumberAddOnRepository tariffNumberAddOnRepository;

    public TariffNumberAddOnResource(TariffNumberAddOnRepository tariffNumberAddOnRepository) {
        this.tariffNumberAddOnRepository = tariffNumberAddOnRepository;
    }

    /**
     * POST  /tariff-number-add-ons : Create a new tariffNumberAddOn.
     *
     * @param tariffNumberAddOn the tariffNumberAddOn to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tariffNumberAddOn, or with status 400 (Bad Request) if the tariffNumberAddOn has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tariff-number-add-ons")
    @Timed
    public ResponseEntity<TariffNumberAddOn> createTariffNumberAddOn(@RequestBody TariffNumberAddOn tariffNumberAddOn) throws URISyntaxException {
        log.debug("REST request to save TariffNumberAddOn : {}", tariffNumberAddOn);
        if (tariffNumberAddOn.getId() != null) {
            throw new BadRequestAlertException("A new tariffNumberAddOn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TariffNumberAddOn result = tariffNumberAddOnRepository.save(tariffNumberAddOn);
        return ResponseEntity.created(new URI("/api/tariff-number-add-ons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tariff-number-add-ons : Updates an existing tariffNumberAddOn.
     *
     * @param tariffNumberAddOn the tariffNumberAddOn to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tariffNumberAddOn,
     * or with status 400 (Bad Request) if the tariffNumberAddOn is not valid,
     * or with status 500 (Internal Server Error) if the tariffNumberAddOn couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tariff-number-add-ons")
    @Timed
    public ResponseEntity<TariffNumberAddOn> updateTariffNumberAddOn(@RequestBody TariffNumberAddOn tariffNumberAddOn) throws URISyntaxException {
        log.debug("REST request to update TariffNumberAddOn : {}", tariffNumberAddOn);
        if (tariffNumberAddOn.getId() == null) {
            return createTariffNumberAddOn(tariffNumberAddOn);
        }
        TariffNumberAddOn result = tariffNumberAddOnRepository.save(tariffNumberAddOn);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tariffNumberAddOn.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tariff-number-add-ons : get all the tariffNumberAddOns.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tariffNumberAddOns in body
     */
    @GetMapping("/tariff-number-add-ons")
    @Timed
    public ResponseEntity<List<TariffNumberAddOn>> getAllTariffNumberAddOns(Pageable pageable) {
        log.debug("REST request to get a page of TariffNumberAddOns");
        Page<TariffNumberAddOn> page = tariffNumberAddOnRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tariff-number-add-ons");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tariff-number-add-ons : get all the tariffNumberAddOns.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tariffNumberAddOns in body
     */
    @GetMapping("/tariff-number-add-ons/getAll")
    @Timed
    public ResponseEntity<List<TariffNumberAddOn>> getAllTariffNumberAddOns() {
        log.debug("REST request to get a page of TariffNumberAddOns");
        List<TariffNumberAddOn> page = tariffNumberAddOnRepository.findAll();
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }
    /**
     * GET  /tariff-number-add-ons/:id : get the "id" tariffNumberAddOn.
     *
     * @param id the id of the tariffNumberAddOn to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tariffNumberAddOn, or with status 404 (Not Found)
     */
    @GetMapping("/tariff-number-add-ons/{id}")
    @Timed
    public ResponseEntity<TariffNumberAddOn> getTariffNumberAddOn(@PathVariable String id) {
        log.debug("REST request to get TariffNumberAddOn : {}", id);
        TariffNumberAddOn tariffNumberAddOn = tariffNumberAddOnRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tariffNumberAddOn));
    }

    /**
     * DELETE  /tariff-number-add-ons/:id : delete the "id" tariffNumberAddOn.
     *
     * @param id the id of the tariffNumberAddOn to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tariff-number-add-ons/{id}")
    @Timed
    public ResponseEntity<Void> deleteTariffNumberAddOn(@PathVariable String id) {
        log.debug("REST request to delete TariffNumberAddOn : {}", id);
        tariffNumberAddOnRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
