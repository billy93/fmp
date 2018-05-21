package com.atibusinessgroup.fmp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.SystemParameter;
import com.atibusinessgroup.fmp.service.SystemParameterService;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing SystemParameter.
 */
@RestController
@RequestMapping("/api")
public class SystemParameterResource {

    private final Logger log = LoggerFactory.getLogger(SystemParameterResource.class);

    private static final String ENTITY_NAME = "systemParameter";

    private final SystemParameterService systemParameterService;

    public SystemParameterResource(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }

    /**
     * POST  /system-parameters : Create a new systemParameter.
     *
     * @param systemParameter the systemParameter to create
     * @return the ResponseEntity with status 201 (Created) and with body the new systemParameter, or with status 400 (Bad Request) if the systemParameter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/system-parameters")
    @Timed
    public ResponseEntity<SystemParameter> createSystemParameter(@RequestBody SystemParameter systemParameter) throws URISyntaxException {
        log.debug("REST request to save SystemParameter : {}", systemParameter);
        if (systemParameter.getId() != null) {
            throw new BadRequestAlertException("A new systemParameter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SystemParameter result = systemParameterService.save(systemParameter);
        return ResponseEntity.created(new URI("/api/system-parameters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /system-parameters : Updates an existing systemParameter.
     *
     * @param systemParameter the systemParameter to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated systemParameter,
     * or with status 400 (Bad Request) if the systemParameter is not valid,
     * or with status 500 (Internal Server Error) if the systemParameter couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/system-parameters")
    @Timed
    public ResponseEntity<SystemParameter> updateSystemParameter(@RequestBody SystemParameter systemParameter) throws URISyntaxException {
        log.debug("REST request to update SystemParameter : {}", systemParameter);
        if (systemParameter.getId() == null) {
            return createSystemParameter(systemParameter);
        }
        SystemParameter result = systemParameterService.save(systemParameter);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, systemParameter.getId().toString()))
            .body(result);
    }

    /**
     * GET  /system-parameters : get all the systemParameters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of systemParameters in body
     */
    @GetMapping("/system-parameters")
    @Timed
    public ResponseEntity<List<SystemParameter>> getAllSystemParameters(Pageable pageable) {
        log.debug("REST request to get a page of SystemParameters");
        Page<SystemParameter> page = systemParameterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/system-parameters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /system-parameters/:id : get the "id" systemParameter.
     *
     * @param id the id of the systemParameter to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the systemParameter, or with status 404 (Not Found)
     */
    @GetMapping("/system-parameters/{id}")
    @Timed
    public ResponseEntity<SystemParameter> getSystemParameter(@PathVariable String id) {
        log.debug("REST request to get SystemParameter : {}", id);
        SystemParameter systemParameter = systemParameterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(systemParameter));
    }
    
    /**
     * GET  /system-parameters/:id : get the "id" systemParameter.
     *
     * @param id the id of the systemParameter to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the systemParameter, or with status 404 (Not Found)
     */
    @GetMapping("/system-parameters/findByName/{name}")
    @Timed
    public ResponseEntity<SystemParameter> getSystemParameterByName(@PathVariable String name) {
        log.debug("REST request to get SystemParameter : {}", name);
        SystemParameter systemParameter = systemParameterService.findOneByName(name);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(systemParameter));
    }

    /**
     * DELETE  /system-parameters/:id : delete the "id" systemParameter.
     *
     * @param id the id of the systemParameter to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/system-parameters/{id}")
    @Timed
    public ResponseEntity<Void> deleteSystemParameter(@PathVariable String id) {
        log.debug("REST request to delete SystemParameter : {}", id);
        systemParameterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
    
    /**
     * GET  /system-parameters/passwords : get passwords related parameter value.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of systemParameters in body
     */
    @GetMapping("/system-parameters/passwords")
    @Timed
    public ResponseEntity<List<SystemParameter>> getAllPasswordSystemParameters() {
        log.debug("REST request to get all password SystemParameters");
        List<SystemParameter> result = new ArrayList<>();
        
        result.add(systemParameterService.findOneByName(SystemParameter.PASSWORD_MIN_LENGTH));
        result.add(systemParameterService.findOneByName(SystemParameter.PASSWORD_MAX_LENGTH));
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
