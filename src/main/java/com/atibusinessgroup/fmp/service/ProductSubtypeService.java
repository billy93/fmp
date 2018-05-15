package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.ProductSubtype;
import com.atibusinessgroup.fmp.repository.ProductSubtypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing ProductSubtype.
 */
@Service
public class ProductSubtypeService {

    private final Logger log = LoggerFactory.getLogger(ProductSubtypeService.class);

    private final ProductSubtypeRepository productSubtypeRepository;

    public ProductSubtypeService(ProductSubtypeRepository productSubtypeRepository) {
        this.productSubtypeRepository = productSubtypeRepository;
    }

    /**
     * Save a productSubtype.
     *
     * @param productSubtype the entity to save
     * @return the persisted entity
     */
    public ProductSubtype save(ProductSubtype productSubtype) {
        log.debug("Request to save ProductSubtype : {}", productSubtype);
        return productSubtypeRepository.save(productSubtype);
    }

    /**
     * Get all the productSubtypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<ProductSubtype> findAll(Pageable pageable) {
        log.debug("Request to get all ProductSubtypes");
        return productSubtypeRepository.findAll(pageable);
    }

    /**
     * Get one productSubtype by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public ProductSubtype findOne(String id) {
        log.debug("Request to get ProductSubtype : {}", id);
        return productSubtypeRepository.findOne(id);
    }

    /**
     * Delete the productSubtype by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete ProductSubtype : {}", id);
        productSubtypeRepository.delete(id);
    }
}
