package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.ReviewLevel;
import com.atibusinessgroup.fmp.repository.ReviewLevelRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing ReviewLevel.
 */
@Service
public class ReviewLevelService {

    private final Logger log = LoggerFactory.getLogger(ReviewLevelService.class);

    private final ReviewLevelRepository reviewLevelRepository;

    public ReviewLevelService(ReviewLevelRepository reviewLevelRepository) {
        this.reviewLevelRepository = reviewLevelRepository;
    }

    /**
     * Save a reviewLevel.
     *
     * @param reviewLevel the entity to save
     * @return the persisted entity
     */
    public ReviewLevel save(ReviewLevel reviewLevel) {
        log.debug("Request to save ReviewLevel : {}", reviewLevel);
        return reviewLevelRepository.save(reviewLevel);
    }

    /**
     * Get all the reviewLevels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<ReviewLevel> findAll(Pageable pageable) {
        log.debug("Request to get all ReviewLevels");
        return reviewLevelRepository.findAll(pageable);
    }

    /**
     * Get one reviewLevel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public ReviewLevel findOne(String id) {
        log.debug("Request to get ReviewLevel : {}", id);
        return reviewLevelRepository.findOne(id);
    }

    /**
     * Delete the reviewLevel by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete ReviewLevel : {}", id);
        reviewLevelRepository.delete(id);
    }

	public List<ReviewLevel> findAll() {
		log.debug("Request to get all ReviewLevels");
        return reviewLevelRepository.findAll();
	}
}
