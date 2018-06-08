package com.atibusinessgroup.fmp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterFareMatrix;
import com.atibusinessgroup.fmp.repository.AtpcoMasterFareMatrixRepository;


/**
 * Service Implementation for managing AtpcoMasterFareMatrix.
 */
@Service
public class AtpcoMasterFareMatrixService {

    private final Logger log = LoggerFactory.getLogger(AtpcoMasterFareMatrixService.class);

    private final MongoTemplate mongoTemplate;
    
    private final AtpcoMasterFareMatrixRepository atpcoMasterFareMatrixRepository;

    public AtpcoMasterFareMatrixService(AtpcoMasterFareMatrixRepository atpcoMasterFareMatrixRepository, MongoTemplate mongoTemplate) {
        this.atpcoMasterFareMatrixRepository = atpcoMasterFareMatrixRepository;
        this.mongoTemplate = mongoTemplate;
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
     * Get one atpcoMasterFareMatrix by fare type code.
     *
     * @param fare type code
     * @return the entity
     */
    public AtpcoMasterFareMatrix findOneByFareTypeCode(String code) {
        log.debug("Request to get AtpcoMasterFareMatrix by Fare Type Code: {}", code);
        
        Criteria criteria = Criteria.where("fare_type_code.type_code").is(code);
        Query query = new Query(criteria);
        AtpcoMasterFareMatrix result = mongoTemplate.findOne(query, AtpcoMasterFareMatrix.class);
        
        return result;
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
