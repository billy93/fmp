package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.RbdQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Rbdquery.
 */
public interface RbdqueryService {

    /**
     * Save a rbdquery.
     *
     * @param rbdquery the entity to save
     * @return the persisted entity
     */
    RbdQuery save(RbdQuery rbdquery);

    /**
     * Get all the rbdqueries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RbdQuery> findAll(Pageable pageable);

    /**
     * Get the "id" rbdquery.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RbdQuery findOne(String id);

    /**
     * Delete the "id" rbdquery.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
