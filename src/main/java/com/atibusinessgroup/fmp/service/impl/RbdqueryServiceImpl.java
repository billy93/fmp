package com.atibusinessgroup.fmp.service.impl;

import com.atibusinessgroup.fmp.service.RbdqueryService;
import com.atibusinessgroup.fmp.domain.RbdQuery;
import com.atibusinessgroup.fmp.domain.dto.RuleQueryParam;
import com.atibusinessgroup.fmp.repository.RbdqueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing Rbdquery.
 */
@Service
public class RbdqueryServiceImpl implements RbdqueryService {

    private final Logger log = LoggerFactory.getLogger(RbdqueryServiceImpl.class);

    private final RbdqueryRepository rbdqueryRepository;

    public RbdqueryServiceImpl(RbdqueryRepository rbdqueryRepository) {
        this.rbdqueryRepository = rbdqueryRepository;
    }

    /**
     * Save a rbdquery.
     *
     * @param rbdquery the entity to save
     * @return the persisted entity
     */
    @Override
    public RbdQuery save(RbdQuery rbdquery) {
        log.debug("Request to save Rbdquery : {}", rbdquery);
        return rbdqueryRepository.save(rbdquery);
    }

    /**
     * Get all the rbdqueries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<RbdQuery> findAll(Pageable pageable) {
        log.debug("Request to get all Rbdqueries");
        return rbdqueryRepository.findAll(pageable);
    }

    /**
     * Get one rbdquery by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public RbdQuery findOne(String id) {
        log.debug("Request to get Rbdquery : {}", id);
        return rbdqueryRepository.findOne(id);
    }

    /**
     * Delete the rbdquery by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Rbdquery : {}", id);
        rbdqueryRepository.delete(id);
    }
}
