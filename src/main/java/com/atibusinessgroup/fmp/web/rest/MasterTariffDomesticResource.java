package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.MasterTariffDomestic;

import com.atibusinessgroup.fmp.repository.MasterTariffDomesticRepository;
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
 * REST controller for managing MasterTariffDomestic.
 */
@RestController
@RequestMapping("/api")
public class MasterTariffDomesticResource {

    private final Logger log = LoggerFactory.getLogger(MasterTariffDomesticResource.class);

    private static final String ENTITY_NAME = "masterTariff";

    private final MasterTariffDomesticRepository masterTariffDomesticRepository;

    public MasterTariffDomesticResource(MasterTariffDomesticRepository masterTariffRepository) {
        this.masterTariffDomesticRepository = masterTariffRepository;
    }

    /**
     * POST  /master-tariffs : Create a new masterTariff.
     *
     * @param masterTariff the masterTariff to create
     * @return the ResponseEntity with status 201 (Created) and with body the new masterTariff, or with status 400 (Bad Request) if the masterTariff has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/master-tariffs-domestic")
    @Timed
    public ResponseEntity<MasterTariffDomestic> createMasterTariff(@RequestBody MasterTariffDomestic masterTariff) throws URISyntaxException {
        log.debug("REST request to save MasterTariff : {}", masterTariff);
        if (masterTariff.getId() != null) {
            throw new BadRequestAlertException("A new masterTariff cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MasterTariffDomestic result = masterTariffDomesticRepository.save(masterTariff);
        return ResponseEntity.created(new URI("/api/master-tariffs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /master-tariffs : Updates an existing masterTariff.
     *
     * @param masterTariff the masterTariff to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated masterTariff,
     * or with status 400 (Bad Request) if the masterTariff is not valid,
     * or with status 500 (Internal Server Error) if the masterTariff couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/master-tariffs-domestic")
    @Timed
    public ResponseEntity<MasterTariffDomestic> updateMasterTariff(@RequestBody MasterTariffDomestic masterTariff) throws URISyntaxException {
        log.debug("REST request to update MasterTariffDomestic : {}", masterTariff);
        if (masterTariff.getId() == null) {
            return createMasterTariff(masterTariff);
        }
        MasterTariffDomestic result = masterTariffDomesticRepository.save(masterTariff);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, masterTariff.getId().toString()))
            .body(result);
    }

    /**
     * GET  /master-tariffs : get all the masterTariffs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of masterTariffs in body
     */
    @GetMapping("/master-tariffs-domestic")
    @Timed
    public ResponseEntity<List<MasterTariffDomestic>> getAllMasterTariffs(Pageable pageable) {
        log.debug("REST request to get a page of MasterTariffs");
        Page<MasterTariffDomestic> page = masterTariffDomesticRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/master-tariffs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /master-tariffs/:id : get the "id" masterTariff.
     *
     * @param id the id of the masterTariff to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the masterTariff, or with status 404 (Not Found)
     */
    @GetMapping("/master-tariffs-domestic/{id}")
    @Timed
    public ResponseEntity<MasterTariffDomestic> getMasterTariff(@PathVariable String id) {
        log.debug("REST request to get MasterTariff : {}", id);
        MasterTariffDomestic masterTariff = masterTariffDomesticRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(masterTariff));
    }

    /**
     * DELETE  /master-tariffs/:id : delete the "id" masterTariff.
     *
     * @param id the id of the masterTariff to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/master-tariffs-domestic/{id}")
    @Timed
    public ResponseEntity<Void> deleteMasterTariff(@PathVariable String id) {
        log.debug("REST request to delete MasterTariff : {}", id);
        masterTariffDomesticRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
