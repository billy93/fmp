package com.atibusinessgroup.fmp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
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

import com.atibusinessgroup.fmp.domain.WorkPackageFilter;
import com.atibusinessgroup.fmp.repository.WorkPackagefilterRepository;
import com.atibusinessgroup.fmp.security.SecurityUtils;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing workPackagefilter.
 */
@RestController
@RequestMapping("/api")
public class WorkPackageFilterResource {

    private final Logger log = LoggerFactory.getLogger(WorkPackageFilterResource.class);

    private static final String ENTITY_NAME = "work_package_filter";

    private final WorkPackagefilterRepository workPackagefilterRepository;

    public WorkPackageFilterResource(WorkPackagefilterRepository workPackagefilterRepository) {
        this.workPackagefilterRepository = workPackagefilterRepository;
    }

    /**
     * POST  /workPackagefilter : Create a new work_package_filter.
     *
     * @param workPackagefilter the workPackagefilter to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackagefilter, or with status 400 (Bad Request) if the workPackagefilter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/workpackagefilters")
    @Timed
    public ResponseEntity<WorkPackageFilter> createWorkPackageFilter(@RequestBody WorkPackageFilter workPackagefilter) throws URISyntaxException {
        log.debug("REST request to save work_package_filter : {}", workPackagefilter);
        if (workPackagefilter.getId() != null) {
            throw new BadRequestAlertException("A new workPackagefilter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkPackageFilter result = workPackagefilterRepository.save(workPackagefilter);
        return ResponseEntity.created(new URI("/api/workpackagefilters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workPackagefilter : Updates an existing workPackagefilter.
     *i,kjs  
     * @param workPackagefilter the workPackagefilter to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated currency,
     * or with status 400 (Bad Request) if the currency is not valid,
     * or with status 500 (Internal Server Error) if the currency couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/workpackagefilters")
    @Timed
    public ResponseEntity<WorkPackageFilter> updateWorkPackageFilter(@RequestBody WorkPackageFilter workPackagefilter) throws URISyntaxException {
        log.debug("REST request to update work_package_filter : {}", workPackagefilter);
        if (workPackagefilter.getId() == null) {
            return createWorkPackageFilter(workPackagefilter);
        }
        WorkPackageFilter result = workPackagefilterRepository.save(workPackagefilter);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workPackagefilter.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workPackagefilter : get all the workPackagefilter.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of currencies in body
     */
    @GetMapping("/workpackagefilters")
    @Timed
    public ResponseEntity<List<WorkPackageFilter>> getAllWorkPackageFilter(Pageable pageable) {
        log.debug("REST request to get a page of WorkPackageFilter");
        Page<WorkPackageFilter> page = workPackagefilterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/workPackagefilters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /workPackagefilter/getAll: get all the workPackagefilters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of currencies in body
     */
    @GetMapping("/workpackagefilters/getAll")
    @Timed
    public ResponseEntity<List<WorkPackageFilter>> getAllWorkPackageFilter() {
        log.debug("REST request to get a page of workPackagefilter");
        List<WorkPackageFilter> page = workPackagefilterRepository.findAll();
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    /**
     * GET  /workPackagefilter/:id : get the "id" workPackagefilter.
     *
     * @param id the id of the workPackagefilter to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workPackagefilter, or with status 404 (Not Found)
     */
    @GetMapping("/workpackagefilters/{id}")
    @Timed
    public ResponseEntity<WorkPackageFilter> getWorkPackageFilter(@PathVariable String id) {
        log.debug("REST request to get workPackagefilter : {}", id);
        WorkPackageFilter workPackagefilter = workPackagefilterRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(workPackagefilter));
    }

    /**
     * GET  /workPackagefilter/:id : get the "id" workPackagefilter.
     *
     * @param id the id of the workPackagefilter to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workPackagefilter, or with status 404 (Not Found)
     */
    @GetMapping("/workpackagefilters/byname")
    @Timed
    public ResponseEntity<WorkPackageFilter> getWorkPackageFilterbyLoginName() {
    	String loginName = SecurityUtils.getCurrentUserLogin().get();
        log.debug("REST request to get workPackagefilter : {}", loginName);
        WorkPackageFilter result = null;
        Optional<WorkPackageFilter> workPackagefilter = workPackagefilterRepository.findOneByLoginName(loginName);
        if(workPackagefilter.isPresent()) {
        	 result = workPackagefilter.get();
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    
    /**
     * DELETE  /workPackagefilter/:id : delete the "id" workPackagefilter.
     *
     * @param id the id of the workPackagefilter to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/workpackagefilters/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkPackageFilter(@PathVariable String id) {
        log.debug("REST request to delete workPackagefilter : {}", id);
        workPackagefilterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
