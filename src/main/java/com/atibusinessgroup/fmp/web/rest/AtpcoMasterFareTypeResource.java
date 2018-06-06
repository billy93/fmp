package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.AtpcoMasterFareType;
import com.atibusinessgroup.fmp.service.AtpcoMasterFareTypeService;
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
 * REST controller for managing AtpcoMasterFareType.
 */
@RestController
@RequestMapping("/api")
public class AtpcoMasterFareTypeResource {

    private final Logger log = LoggerFactory.getLogger(AtpcoMasterFareTypeResource.class);

    private static final String ENTITY_NAME = "atpcoMasterFareType";

    private final AtpcoMasterFareTypeService atpcoMasterFareTypeService;

    public AtpcoMasterFareTypeResource(AtpcoMasterFareTypeService atpcoMasterFareTypeService) {
        this.atpcoMasterFareTypeService = atpcoMasterFareTypeService;
    }

    /**
     * POST  /atpco-master-fare-types : Create a new atpcoMasterFareType.
     *
     * @param atpcoMasterFareType the atpcoMasterFareType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new atpcoMasterFareType, or with status 400 (Bad Request) if the atpcoMasterFareType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/atpco-master-fare-types")
    @Timed
    public ResponseEntity<AtpcoMasterFareType> createAtpcoMasterFareType(@RequestBody AtpcoMasterFareType atpcoMasterFareType) throws URISyntaxException {
        log.debug("REST request to save AtpcoMasterFareType : {}", atpcoMasterFareType);
        if (atpcoMasterFareType.getId() != null) {
            throw new BadRequestAlertException("A new atpcoMasterFareType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AtpcoMasterFareType result = atpcoMasterFareTypeService.save(atpcoMasterFareType);
        return ResponseEntity.created(new URI("/api/atpco-master-fare-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /atpco-master-fare-types : Updates an existing atpcoMasterFareType.
     *
     * @param atpcoMasterFareType the atpcoMasterFareType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated atpcoMasterFareType,
     * or with status 400 (Bad Request) if the atpcoMasterFareType is not valid,
     * or with status 500 (Internal Server Error) if the atpcoMasterFareType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/atpco-master-fare-types")
    @Timed
    public ResponseEntity<AtpcoMasterFareType> updateAtpcoMasterFareType(@RequestBody AtpcoMasterFareType atpcoMasterFareType) throws URISyntaxException {
        log.debug("REST request to update AtpcoMasterFareType : {}", atpcoMasterFareType);
        if (atpcoMasterFareType.getId() == null) {
            return createAtpcoMasterFareType(atpcoMasterFareType);
        }
        AtpcoMasterFareType result = atpcoMasterFareTypeService.save(atpcoMasterFareType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, atpcoMasterFareType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /atpco-master-fare-types : get all the atpcoMasterFareTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of atpcoMasterFareTypes in body
     */
    @GetMapping("/atpco-master-fare-types")
    @Timed
    public ResponseEntity<List<AtpcoMasterFareType>> getAllAtpcoMasterFareTypes(Pageable pageable) {
        log.debug("REST request to get a page of AtpcoMasterFareTypes");
        Page<AtpcoMasterFareType> page = atpcoMasterFareTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/atpco-master-fare-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /atpco-master-fare-types/all : get all the atpcoMasterFareTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of atpcoMasterFareTypes in body
     */
    @GetMapping("/atpco-master-fare-types/all")
    @Timed
    public ResponseEntity<List<AtpcoMasterFareType>> getAllAtpcoMasterFareTypes() {
        log.debug("REST request to get all of AtpcoMasterFareTypes");
        List<AtpcoMasterFareType> result = atpcoMasterFareTypeService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET  /atpco-master-fare-types/:id : get the "id" atpcoMasterFareType.
     *
     * @param id the id of the atpcoMasterFareType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the atpcoMasterFareType, or with status 404 (Not Found)
     */
    @GetMapping("/atpco-master-fare-types/{id}")
    @Timed
    public ResponseEntity<AtpcoMasterFareType> getAtpcoMasterFareType(@PathVariable String id) {
        log.debug("REST request to get AtpcoMasterFareType : {}", id);
        AtpcoMasterFareType atpcoMasterFareType = atpcoMasterFareTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(atpcoMasterFareType));
    }

    /**
     * DELETE  /atpco-master-fare-types/:id : delete the "id" atpcoMasterFareType.
     *
     * @param id the id of the atpcoMasterFareType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/atpco-master-fare-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteAtpcoMasterFareType(@PathVariable String id) {
        log.debug("REST request to delete AtpcoMasterFareType : {}", id);
        atpcoMasterFareTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
