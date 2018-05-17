package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.BusinessArea;
import com.atibusinessgroup.fmp.domain.ReviewLevel;
import com.atibusinessgroup.fmp.service.BusinessAreaService;
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
 * REST controller for managing BusinessArea.
 */
@RestController
@RequestMapping("/api")
public class BusinessAreaResource {

    private final Logger log = LoggerFactory.getLogger(BusinessAreaResource.class);

    private static final String ENTITY_NAME = "businessArea";

    private final BusinessAreaService businessAreaService;

    public BusinessAreaResource(BusinessAreaService businessAreaService) {
        this.businessAreaService = businessAreaService;
    }

    /**
     * POST  /business-areas : Create a new businessArea.
     *
     * @param businessArea the businessArea to create
     * @return the ResponseEntity with status 201 (Created) and with body the new businessArea, or with status 400 (Bad Request) if the businessArea has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/business-areas")
    @Timed
    public ResponseEntity<BusinessArea> createBusinessArea(@RequestBody BusinessArea businessArea) throws URISyntaxException {
        log.debug("REST request to save BusinessArea : {}", businessArea);
        if (businessArea.getId() != null) {
            throw new BadRequestAlertException("A new businessArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessArea result = businessAreaService.save(businessArea);
        return ResponseEntity.created(new URI("/api/business-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /business-areas : Updates an existing businessArea.
     *
     * @param businessArea the businessArea to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated businessArea,
     * or with status 400 (Bad Request) if the businessArea is not valid,
     * or with status 500 (Internal Server Error) if the businessArea couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/business-areas")
    @Timed
    public ResponseEntity<BusinessArea> updateBusinessArea(@RequestBody BusinessArea businessArea) throws URISyntaxException {
        log.debug("REST request to update BusinessArea : {}", businessArea);
        if (businessArea.getId() == null) {
            return createBusinessArea(businessArea);
        }
        BusinessArea result = businessAreaService.save(businessArea);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, businessArea.getId().toString()))
            .body(result);
    }

    /**
     * GET  /business-areas/all : get all the businessAreas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of businessAreas in body
     */
    @GetMapping("/business-areas/all")
    @Timed
    public ResponseEntity<List<BusinessArea>> getAllBusinessAreas() {
        log.debug("REST request to get all of BusinessAreas");
        List<BusinessArea> result = businessAreaService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    /**
     * GET  /business-areas : get all the businessAreas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of businessAreas in body
     */
    @GetMapping("/business-areas")
    @Timed
    public ResponseEntity<List<BusinessArea>> getAllBusinessAreas(Pageable pageable) {
        log.debug("REST request to get a page of BusinessAreas");
        Page<BusinessArea> page = businessAreaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/business-areas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /business-areas/:id : get the "id" businessArea.
     *
     * @param id the id of the businessArea to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the businessArea, or with status 404 (Not Found)
     */
    @GetMapping("/business-areas/{id}")
    @Timed
    public ResponseEntity<BusinessArea> getBusinessArea(@PathVariable String id) {
        log.debug("REST request to get BusinessArea : {}", id);
        BusinessArea businessArea = businessAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(businessArea));
    }

    /**
     * DELETE  /business-areas/:id : delete the "id" businessArea.
     *
     * @param id the id of the businessArea to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/business-areas/{id}")
    @Timed
    public ResponseEntity<Void> deleteBusinessArea(@PathVariable String id) {
        log.debug("REST request to delete BusinessArea : {}", id);
        businessAreaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
