package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.Airline;
import com.atibusinessgroup.fmp.repository.AirlineRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing Airline.
 */
@Service
public class AirlineService {

    private final Logger log = LoggerFactory.getLogger(AirlineService.class);

    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    /**
     * Save a airline.
     *
     * @param airline the entity to save
     * @return the persisted entity
     */
    public Airline save(Airline airline) {
        log.debug("Request to save Airline : {}", airline);
        return airlineRepository.save(airline);
    }

    /**
     * Get all the airlines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<Airline> findAll(Pageable pageable) {
        log.debug("Request to get all Airlines");
        return airlineRepository.findAll(pageable);
    }

    /**
     * Get one airline by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Airline findOne(String id) {
        log.debug("Request to get Airline : {}", id);
        return airlineRepository.findOne(id);
    }

    /**
     * Delete the airline by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Airline : {}", id);
        airlineRepository.delete(id);
    }

	public List<Airline> findAll() {
		log.debug("Request to get all Airlines");
        return airlineRepository.findAll();
	}
}
