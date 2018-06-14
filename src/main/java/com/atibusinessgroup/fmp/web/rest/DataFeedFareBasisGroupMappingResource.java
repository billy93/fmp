package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.DataFeedFareBasisGroupMapping;
import com.atibusinessgroup.fmp.service.DataFeedFareBasisGroupMappingService;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DataFeedFareBasisGroupMapping.
 */
@RestController
@RequestMapping("/api")
public class DataFeedFareBasisGroupMappingResource {

    private final Logger log = LoggerFactory.getLogger(DataFeedFareBasisGroupMappingResource.class);

    private static final String ENTITY_NAME = "dataFeedFareBasisGroupMapping";

    private final DataFeedFareBasisGroupMappingService dataFeedFareBasisGroupMappingService;

    public DataFeedFareBasisGroupMappingResource(DataFeedFareBasisGroupMappingService dataFeedFareBasisGroupMappingService) {
        this.dataFeedFareBasisGroupMappingService = dataFeedFareBasisGroupMappingService;
    }

    /**
     * POST  /data-feed-fare-basis-group-mappings : Create a new dataFeedFareBasisGroupMapping.
     *
     * @param dataFeedFareBasisGroupMapping the dataFeedFareBasisGroupMapping to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dataFeedFareBasisGroupMapping, or with status 400 (Bad Request) if the dataFeedFareBasisGroupMapping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/data-feed-fare-basis-group-mappings")
    @Timed
    public ResponseEntity<DataFeedFareBasisGroupMapping> createDataFeedFareBasisGroupMapping(@RequestBody DataFeedFareBasisGroupMapping dataFeedFareBasisGroupMapping) throws URISyntaxException {
        log.debug("REST request to save DataFeedFareBasisGroupMapping : {}", dataFeedFareBasisGroupMapping);
        if (dataFeedFareBasisGroupMapping.getId() != null) {
            throw new BadRequestAlertException("A new dataFeedFareBasisGroupMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataFeedFareBasisGroupMapping result = dataFeedFareBasisGroupMappingService.save(dataFeedFareBasisGroupMapping);
        return ResponseEntity.created(new URI("/api/data-feed-fare-basis-group-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /data-feed-fare-basis-group-mappings : Updates an existing dataFeedFareBasisGroupMapping.
     *
     * @param dataFeedFareBasisGroupMapping the dataFeedFareBasisGroupMapping to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dataFeedFareBasisGroupMapping,
     * or with status 400 (Bad Request) if the dataFeedFareBasisGroupMapping is not valid,
     * or with status 500 (Internal Server Error) if the dataFeedFareBasisGroupMapping couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/data-feed-fare-basis-group-mappings")
    @Timed
    public ResponseEntity<DataFeedFareBasisGroupMapping> updateDataFeedFareBasisGroupMapping(@RequestBody DataFeedFareBasisGroupMapping dataFeedFareBasisGroupMapping) throws URISyntaxException {
        log.debug("REST request to update DataFeedFareBasisGroupMapping : {}", dataFeedFareBasisGroupMapping);
        if (dataFeedFareBasisGroupMapping.getId() == null) {
            return createDataFeedFareBasisGroupMapping(dataFeedFareBasisGroupMapping);
        }
        DataFeedFareBasisGroupMapping result = dataFeedFareBasisGroupMappingService.save(dataFeedFareBasisGroupMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dataFeedFareBasisGroupMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /data-feed-fare-basis-group-mappings : get all the dataFeedFareBasisGroupMappings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dataFeedFareBasisGroupMappings in body
     */
    @GetMapping("/data-feed-fare-basis-group-mappings")
    @Timed
    public List<DataFeedFareBasisGroupMapping> getAllDataFeedFareBasisGroupMappings() {
        log.debug("REST request to get all DataFeedFareBasisGroupMappings");
        return dataFeedFareBasisGroupMappingService.findAll();
        }

    /**
     * GET  /data-feed-fare-basis-group-mappings/:id : get the "id" dataFeedFareBasisGroupMapping.
     *
     * @param id the id of the dataFeedFareBasisGroupMapping to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dataFeedFareBasisGroupMapping, or with status 404 (Not Found)
     */
    @GetMapping("/data-feed-fare-basis-group-mappings/{id}")
    @Timed
    public ResponseEntity<DataFeedFareBasisGroupMapping> getDataFeedFareBasisGroupMapping(@PathVariable String id) {
        log.debug("REST request to get DataFeedFareBasisGroupMapping : {}", id);
        DataFeedFareBasisGroupMapping dataFeedFareBasisGroupMapping = dataFeedFareBasisGroupMappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dataFeedFareBasisGroupMapping));
    }

    /**
     * DELETE  /data-feed-fare-basis-group-mappings/:id : delete the "id" dataFeedFareBasisGroupMapping.
     *
     * @param id the id of the dataFeedFareBasisGroupMapping to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/data-feed-fare-basis-group-mappings/{id}")
    @Timed
    public ResponseEntity<Void> deleteDataFeedFareBasisGroupMapping(@PathVariable String id) {
        log.debug("REST request to delete DataFeedFareBasisGroupMapping : {}", id);
        dataFeedFareBasisGroupMappingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
