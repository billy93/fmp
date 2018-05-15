package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.BusinessArea;
import com.atibusinessgroup.fmp.repository.BusinessAreaRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing BusinessArea.
 */
@Service
public class BusinessAreaService {

    private final Logger log = LoggerFactory.getLogger(BusinessAreaService.class);

    private final BusinessAreaRepository businessAreaRepository;

    public BusinessAreaService(BusinessAreaRepository businessAreaRepository) {
        this.businessAreaRepository = businessAreaRepository;
    }

    /**
     * Save a businessArea.
     *
     * @param businessArea the entity to save
     * @return the persisted entity
     */
    public BusinessArea save(BusinessArea businessArea) {
        log.debug("Request to save BusinessArea : {}", businessArea);
        return businessAreaRepository.save(businessArea);
    }

    /**
     * Get all the businessAreas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<BusinessArea> findAll(Pageable pageable) {
        log.debug("Request to get all BusinessAreas");
        return businessAreaRepository.findAll(pageable);
    }

    /**
     * Get one businessArea by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public BusinessArea findOne(String id) {
        log.debug("Request to get BusinessArea : {}", id);
        return businessAreaRepository.findOne(id);
    }

    /**
     * Delete the businessArea by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete BusinessArea : {}", id);
        businessAreaRepository.delete(id);
    }

	public List<BusinessArea> findAll() {
		log.debug("Request to get all BusinessAreas");
        return businessAreaRepository.findAll();
	}

	public BusinessArea findOneByName(String string) {
		// TODO Auto-generated method stub
		return businessAreaRepository.findOneByName(string);
	}
}
