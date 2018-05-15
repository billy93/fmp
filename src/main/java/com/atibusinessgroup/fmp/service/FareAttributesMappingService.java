package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.FareAttributesMapping;
import com.atibusinessgroup.fmp.repository.FareAttributesMappingRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing FareAttributesMapping.
 */
@Service
public class FareAttributesMappingService {

    private final Logger log = LoggerFactory.getLogger(FareAttributesMappingService.class);

    private final FareAttributesMappingRepository fareAttributesMappingRepository;

    public FareAttributesMappingService(FareAttributesMappingRepository fareAttributesMappingRepository) {
        this.fareAttributesMappingRepository = fareAttributesMappingRepository;
    }

    /**
     * Save a fareAttributesMapping.
     *
     * @param fareAttributesMapping the entity to save
     * @return the persisted entity
     */
    public FareAttributesMapping save(FareAttributesMapping fareAttributesMapping) {
        log.debug("Request to save FareAttributesMapping : {}", fareAttributesMapping);
        return fareAttributesMappingRepository.save(fareAttributesMapping);
    }

    /**
     * Get all the fareAttributesMappings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<FareAttributesMapping> findAll(Pageable pageable) {
        log.debug("Request to get all FareAttributesMappings");
        return fareAttributesMappingRepository.findAll(pageable);
    }

    /**
     * Get one fareAttributesMapping by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public FareAttributesMapping findOne(String id) {
        log.debug("Request to get FareAttributesMapping : {}", id);
        return fareAttributesMappingRepository.findOne(id);
    }

    /**
     * Delete the fareAttributesMapping by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete FareAttributesMapping : {}", id);
        fareAttributesMappingRepository.delete(id);
    }

	public List<FareAttributesMapping> findAll() {
		log.debug("Request to get all FareAttributesMappings");
        return fareAttributesMappingRepository.findAll();
	}
}
