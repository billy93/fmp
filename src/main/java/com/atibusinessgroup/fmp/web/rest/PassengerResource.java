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

import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterPassengerTypeCode;
import com.atibusinessgroup.fmp.repository.PassengerRepository;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Passenger.
 */
@RestController
@RequestMapping("/api")
public class PassengerResource {

    private final Logger log = LoggerFactory.getLogger(PassengerResource.class);

    private static final String ENTITY_NAME = "passenger";

    private final PassengerRepository passengerRepository;

    public PassengerResource(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    /**
     * POST  /passengers : Create a new passenger.
     *
     * @param passenger the passenger to create
     * @return the ResponseEntity with status 201 (Created) and with body the new passenger, or with status 400 (Bad Request) if the passenger has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/passengers")
    @Timed
    public ResponseEntity<AtpcoMasterPassengerTypeCode> createPassenger(@RequestBody AtpcoMasterPassengerTypeCode passenger) throws URISyntaxException {
        log.debug("REST request to save Passenger : {}", passenger);
        if (passenger.getId() != null) {
            throw new BadRequestAlertException("A new passenger cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AtpcoMasterPassengerTypeCode result = passengerRepository.save(passenger);
        return ResponseEntity.created(new URI("/api/passengers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /passengers : Updates an existing passenger.
     *
     * @param passenger the passenger to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated passenger,
     * or with status 400 (Bad Request) if the passenger is not valid,
     * or with status 500 (Internal Server Error) if the passenger couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/passengers")
    @Timed
    public ResponseEntity<AtpcoMasterPassengerTypeCode> updatePassenger(@RequestBody AtpcoMasterPassengerTypeCode passenger) throws URISyntaxException {
        log.debug("REST request to update Passenger : {}", passenger);
        if (passenger.getId() == null) {
            return createPassenger(passenger);
        }
        AtpcoMasterPassengerTypeCode result = passengerRepository.save(passenger);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, passenger.getId().toString()))
            .body(result);
    }

    /**
     * GET  /passengers : get all the passengers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of passengers in body
     */
    @GetMapping("/passengers")
    @Timed
    public ResponseEntity<List<AtpcoMasterPassengerTypeCode>> getAllPassengers(Pageable pageable) {
        log.debug("REST request to get a page of Passengers");
        Page<AtpcoMasterPassengerTypeCode> page = passengerRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/passengers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /passengers/:id : get the "id" passenger.
     *
     * @param id the id of the passenger to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the passenger, or with status 404 (Not Found)
     */
    @GetMapping("/passengers/{id}")
    @Timed
    public ResponseEntity<AtpcoMasterPassengerTypeCode> getPassenger(@PathVariable String id) {
        log.debug("REST request to get Passenger : {}", id);
        AtpcoMasterPassengerTypeCode passenger = passengerRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(passenger));
    }
    
    /**
     * GET  /priorities/getAll : get all the priorities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of priorities in body
     */
    @GetMapping("/passengers/getAll")
    @Timed
    public ResponseEntity<List<AtpcoMasterPassengerTypeCode>> getAllPassengers() {
    	log.debug("REST request to get a page of Priorities");
        List<AtpcoMasterPassengerTypeCode> page = passengerRepository.findAll();
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fare-types");
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    /**
     * DELETE  /passengers/:id : delete the "id" passenger.
     *
     * @param id the id of the passenger to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/passengers/{id}")
    @Timed
    public ResponseEntity<Void> deletePassenger(@PathVariable String id) {
        log.debug("REST request to delete Passenger : {}", id);
        passengerRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
