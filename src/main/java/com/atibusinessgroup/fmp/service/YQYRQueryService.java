package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.YQYRQuery;
import com.atibusinessgroup.fmp.repository.YQYRQueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing YQYRQuery.
 */
@Service
public class YQYRQueryService {

    private final Logger log = LoggerFactory.getLogger(YQYRQueryService.class);

    private final YQYRQueryRepository yQYRQueryRepository;

    public YQYRQueryService(YQYRQueryRepository yQYRQueryRepository) {
        this.yQYRQueryRepository = yQYRQueryRepository;
    }

    /**
     * Save a yQYRQuery.
     *
     * @param yQYRQuery the entity to save
     * @return the persisted entity
     */
    public YQYRQuery save(YQYRQuery yQYRQuery) {
        log.debug("Request to save YQYRQuery : {}", yQYRQuery);
        return yQYRQueryRepository.save(yQYRQuery);
    }

    /**
     * Get all the yQYRQueries.
     *
     * @return the list of entities
     */
    public List<YQYRQuery> findAll() {
        log.debug("Request to get all YQYRQueries");
        return yQYRQueryRepository.findAll();
    }

    /**
     * Get one yQYRQuery by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public YQYRQuery findOne(String id) {
        log.debug("Request to get YQYRQuery : {}", id);
        return yQYRQueryRepository.findOne(id);
    }

    /**
     * Delete the yQYRQuery by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete YQYRQuery : {}", id);
        yQYRQueryRepository.delete(id);
    }
}
