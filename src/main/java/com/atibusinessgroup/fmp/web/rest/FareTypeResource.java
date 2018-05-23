package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.FareType;

import com.atibusinessgroup.fmp.repository.FareTypeRepository;
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
 * REST controller for managing FareType.
 */
@RestController
@RequestMapping("/api")
public class FareTypeResource {

    private final Logger log = LoggerFactory.getLogger(FareTypeResource.class);

    private static final String ENTITY_NAME = "fareType";

    private final FareTypeRepository fareTypeRepository;

    public FareTypeResource(FareTypeRepository fareTypeRepository) {
        this.fareTypeRepository = fareTypeRepository;
    }

    /**
     * POST  /fare-types : Create a new fareType.
     *
     * @param fareType the fareType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fareType, or with status 400 (Bad Request) if the fareType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fare-types")
    @Timed
    public ResponseEntity<FareType> createFareType(@RequestBody FareType fareType) throws URISyntaxException {
        log.debug("REST request to save FareType : {}", fareType);
        if (fareType.getId() != null) {
            throw new BadRequestAlertException("A new fareType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FareType result = fareTypeRepository.save(fareType);
        return ResponseEntity.created(new URI("/api/fare-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fare-types : Updates an existing fareType.
     *
     * @param fareType the fareType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fareType,
     * or with status 400 (Bad Request) if the fareType is not valid,
     * or with status 500 (Internal Server Error) if the fareType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fare-types")
    @Timed
    public ResponseEntity<FareType> updateFareType(@RequestBody FareType fareType) throws URISyntaxException {
        log.debug("REST request to update FareType : {}", fareType);
        if (fareType.getId() == null) {
            return createFareType(fareType);
        }
        FareType result = fareTypeRepository.save(fareType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fareType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fare-types : get all the fareTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fareTypes in body
     */
    @GetMapping("/fare-types")
    @Timed
    public ResponseEntity<List<FareType>> getAllFareTypes(Pageable pageable) {
        log.debug("REST request to get a page of FareTypes");
        Page<FareType> page = fareTypeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fare-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fare-types : get all the fareTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fareTypes in body
     */
    @GetMapping("/fare-types/getAll")
    @Timed
    public ResponseEntity<List<FareType>> getAllFareTypes() {
        log.debug("REST request to get a page of FareTypes");
        List<FareType> page = fareTypeRepository.findAll();
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fare-types");
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }
    
    /**
     * GET  /fare-types/:id : get the "id" fareType.
     *
     * @param id the id of the fareType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fareType, or with status 404 (Not Found)
     */
    @GetMapping("/fare-types/{id}")
    @Timed
    public ResponseEntity<FareType> getFareType(@PathVariable String id) {
        log.debug("REST request to get FareType : {}", id);
        FareType fareType = fareTypeRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fareType));
    }

    /**
     * DELETE  /fare-types/:id : delete the "id" fareType.
     *
     * @param id the id of the fareType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fare-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteFareType(@PathVariable String id) {
        log.debug("REST request to delete FareType : {}", id);
        fareTypeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
