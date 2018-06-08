package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.DataFeedFareBasisGroupMapping;
import com.atibusinessgroup.fmp.repository.DataFeedFareBasisGroupMappingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing DataFeedFareBasisGroupMapping.
 */
@Service
public class DataFeedFareBasisGroupMappingService {

    private final Logger log = LoggerFactory.getLogger(DataFeedFareBasisGroupMappingService.class);

    private final DataFeedFareBasisGroupMappingRepository dataFeedFareBasisGroupMappingRepository;

    public DataFeedFareBasisGroupMappingService(DataFeedFareBasisGroupMappingRepository dataFeedFareBasisGroupMappingRepository) {
        this.dataFeedFareBasisGroupMappingRepository = dataFeedFareBasisGroupMappingRepository;
    }

    /**
     * Save a dataFeedFareBasisGroupMapping.
     *
     * @param dataFeedFareBasisGroupMapping the entity to save
     * @return the persisted entity
     */
    public DataFeedFareBasisGroupMapping save(DataFeedFareBasisGroupMapping dataFeedFareBasisGroupMapping) {
        log.debug("Request to save DataFeedFareBasisGroupMapping : {}", dataFeedFareBasisGroupMapping);
        return dataFeedFareBasisGroupMappingRepository.save(dataFeedFareBasisGroupMapping);
    }

    /**
     * Get all the dataFeedFareBasisGroupMappings.
     *
     * @return the list of entities
     */
    public List<DataFeedFareBasisGroupMapping> findAll() {
        log.debug("Request to get all DataFeedFareBasisGroupMappings");
        return dataFeedFareBasisGroupMappingRepository.findAll();
    }

    /**
     * Get one dataFeedFareBasisGroupMapping by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public DataFeedFareBasisGroupMapping findOne(String id) {
        log.debug("Request to get DataFeedFareBasisGroupMapping : {}", id);
        return dataFeedFareBasisGroupMappingRepository.findOne(id);
    }

    /**
     * Delete the dataFeedFareBasisGroupMapping by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete DataFeedFareBasisGroupMapping : {}", id);
        dataFeedFareBasisGroupMappingRepository.delete(id);
    }
}
