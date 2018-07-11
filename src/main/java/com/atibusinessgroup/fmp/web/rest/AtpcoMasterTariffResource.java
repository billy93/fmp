package com.atibusinessgroup.fmp.web.rest;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
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

import com.atibusinessgroup.fmp.domain.AtpcoMasterTariff;
import com.atibusinessgroup.fmp.domain.dto.TariffNumberGlobal;
import com.atibusinessgroup.fmp.repository.AtpcoMasterTariffRepository;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing TariffNumber.
 */
@RestController
@RequestMapping("/api")
public class AtpcoMasterTariffResource {

    private final Logger log = LoggerFactory.getLogger(AtpcoMasterTariffResource.class);

    private static final String ENTITY_NAME = "atpcoMasterTariff";

    private final AtpcoMasterTariffRepository tariffNumberRepository;

    private final MongoTemplate mongoTemplate;
    
    public AtpcoMasterTariffResource(AtpcoMasterTariffRepository tariffNumberRepository, MongoTemplate mongoTemplate) {
        this.tariffNumberRepository = tariffNumberRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * POST  /atpcoMasterTariff : Create a new tariffNumber.
     *
     * @param tariffNumber the tariffNumber to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tariffNumber, or with status 400 (Bad Request) if the tariffNumber has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/atpcoMasterTariff")
    @Timed
    public ResponseEntity<AtpcoMasterTariff> createTariffNumber(@RequestBody AtpcoMasterTariff tariffNumber) throws URISyntaxException {
        log.debug("REST request to save TariffNumber : {}", tariffNumber);
        if (tariffNumber.getId() != null) {
            throw new BadRequestAlertException("A new tariffNumber cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AtpcoMasterTariff result = tariffNumberRepository.save(tariffNumber);
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
    @PutMapping("/atpcoMasterTariff")
    @Timed
    public ResponseEntity<AtpcoMasterTariff> updateTariffNumber(@RequestBody AtpcoMasterTariff tariffNumber) throws URISyntaxException {
        log.debug("REST request to update TariffNumber : {}", tariffNumber);
        if (tariffNumber.getId() == null) {
            return createTariffNumber(tariffNumber);
        }
        AtpcoMasterTariff result = tariffNumberRepository.save(tariffNumber);
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
    @GetMapping("/atpcoMasterTariff")
    @Timed
    public ResponseEntity<List<AtpcoMasterTariff>> getAllTariffNumbers(Pageable pageable) {
        log.debug("REST request to get a page of TariffNumbers");
        Page<AtpcoMasterTariff> page = tariffNumberRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tariff-numbers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /tariff-numbers/getAll : get all the tariffNumbers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tariffNumbers in body
     */
    @GetMapping("/atpcoMasterTariff/getAll")
    @Timed
    public ResponseEntity<List<AtpcoMasterTariff>> getAllTariffNumbers() {
        log.debug("REST request to get a page of TariffNumbers");
        List<AtpcoMasterTariff> page = tariffNumberRepository.findAll();
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tariff-numbers");
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }
    
    @GetMapping("/atpcoMasterTariff/getAllGlobal")
    @Timed
    public ResponseEntity<List<TariffNumberGlobal>> getAllTariffNumbersGlobal() {
        log.debug("REST request to get a page of TariffNumbers Global");
        
        List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("_id", "$global");
				group.append("$group", query);
				return group;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject();
				project.append("$project", new BasicDBObject("_id", 0).append("global", "$_id"));
				return project;
			}
		});
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<TariffNumberGlobal> result = mongoTemplate.aggregate(aggregation, "tariff_number", TariffNumberGlobal.class).getMappedResults();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET  /tariff-numbers/:id : get the "id" tariffNumber.
     *
     * @param id the id of the tariffNumber to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tariffNumber, or with status 404 (Not Found)
     */
    @GetMapping("/atpcoMasterTariff/{id}")
    @Timed
    public ResponseEntity<AtpcoMasterTariff> getTariffNumber(@PathVariable String id) {
        log.debug("REST request to get TariffNumber : {}", id);
        AtpcoMasterTariff tariffNumber = tariffNumberRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tariffNumber));
    }

    /**
     * DELETE  /tariff-numbers/:id : delete the "id" tariffNumber.
     *
     * @param id the id of the tariffNumber to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/atpcoMasterTariff/{id}")
    @Timed
    public ResponseEntity<Void> deleteTariffNumber(@PathVariable String id) {
        log.debug("REST request to delete TariffNumber : {}", id);
        tariffNumberRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
