package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.AtpcoMasterFareType;
import com.atibusinessgroup.fmp.repository.AtpcoMasterFareTypeRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing AtpcoMasterFareType.
 */
@Service
public class AtpcoMasterFareTypeService {

    private final Logger log = LoggerFactory.getLogger(AtpcoMasterFareTypeService.class);

    private final AtpcoMasterFareTypeRepository atpcoMasterFareTypeRepository;

    public AtpcoMasterFareTypeService(AtpcoMasterFareTypeRepository atpcoMasterFareTypeRepository) {
        this.atpcoMasterFareTypeRepository = atpcoMasterFareTypeRepository;
    }

    /**
     * Save a atpcoMasterFareType.
     *
     * @param atpcoMasterFareType the entity to save
     * @return the persisted entity
     */
    public AtpcoMasterFareType save(AtpcoMasterFareType atpcoMasterFareType) {
        log.debug("Request to save AtpcoMasterFareType : {}", atpcoMasterFareType);
        return atpcoMasterFareTypeRepository.save(atpcoMasterFareType);
    }

    /**
     * Get all the atpcoMasterFareTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<AtpcoMasterFareType> findAll(Pageable pageable) {
        log.debug("Request to get all AtpcoMasterFareTypes");
        return atpcoMasterFareTypeRepository.findAll(pageable);
    }

    /**
     * Get one atpcoMasterFareType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public AtpcoMasterFareType findOne(String id) {
        log.debug("Request to get AtpcoMasterFareType : {}", id);
        return atpcoMasterFareTypeRepository.findOne(id);
    }

    /**
     * Delete the atpcoMasterFareType by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete AtpcoMasterFareType : {}", id);
        atpcoMasterFareTypeRepository.delete(id);
    }

	public List<AtpcoMasterFareType> findAll() {
		log.debug("Request to get all AtpcoMasterFareTypes");
        return atpcoMasterFareTypeRepository.findAll();
	}
}
