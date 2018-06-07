package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterFareMatrix;
import com.atibusinessgroup.fmp.repository.AtpcoMasterFareMatrixRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing AtpcoMasterFareMatrix.
 */
@Service
public class AtpcoMasterFareMatrixService {

    private final Logger log = LoggerFactory.getLogger(AtpcoMasterFareMatrixService.class);

    private final AtpcoMasterFareMatrixRepository atpcoMasterFareMatrixRepository;

    public AtpcoMasterFareMatrixService(AtpcoMasterFareMatrixRepository atpcoMasterFareMatrixRepository) {
        this.atpcoMasterFareMatrixRepository = atpcoMasterFareMatrixRepository;
    }

    /**
     * Save a atpcoMasterFareMatrix.
     *
     * @param atpcoMasterFareMatrix the entity to save
     * @return the persisted entity
     */
    public AtpcoMasterFareMatrix save(AtpcoMasterFareMatrix atpcoMasterFareMatrix) {
        log.debug("Request to save AtpcoMasterFareMatrix : {}", atpcoMasterFareMatrix);
        return atpcoMasterFareMatrixRepository.save(atpcoMasterFareMatrix);
    }

    /**
     * Get all the atpcoMasterFareMatrices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<AtpcoMasterFareMatrix> findAll(Pageable pageable) {
        log.debug("Request to get all AtpcoMasterFareMatrices");
        return atpcoMasterFareMatrixRepository.findAll(pageable);
    }

    /**
     * Get one atpcoMasterFareMatrix by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public AtpcoMasterFareMatrix findOne(String id) {
        log.debug("Request to get AtpcoMasterFareMatrix : {}", id);
        return atpcoMasterFareMatrixRepository.findOne(id);
    }

    /**
     * Delete the atpcoMasterFareMatrix by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete AtpcoMasterFareMatrix : {}", id);
        atpcoMasterFareMatrixRepository.delete(id);
    }
}
