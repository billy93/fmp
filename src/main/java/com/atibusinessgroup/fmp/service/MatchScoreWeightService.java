package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.MatchScoreWeight;
import com.atibusinessgroup.fmp.repository.MatchScoreWeightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing MatchScoreWeight.
 */
@Service
public class MatchScoreWeightService {

    private final Logger log = LoggerFactory.getLogger(MatchScoreWeightService.class);

    private final MatchScoreWeightRepository matchScoreWeightRepository;

    public MatchScoreWeightService(MatchScoreWeightRepository matchScoreWeightRepository) {
        this.matchScoreWeightRepository = matchScoreWeightRepository;
    }

    /**
     * Save a matchScoreWeight.
     *
     * @param matchScoreWeight the entity to save
     * @return the persisted entity
     */
    public MatchScoreWeight save(MatchScoreWeight matchScoreWeight) {
        log.debug("Request to save MatchScoreWeight : {}", matchScoreWeight);
        return matchScoreWeightRepository.save(matchScoreWeight);
    }

    /**
     * Get all the matchScoreWeights.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<MatchScoreWeight> findAll(Pageable pageable) {
        log.debug("Request to get all MatchScoreWeights");
        return matchScoreWeightRepository.findAll(pageable);
    }

    /**
     * Get one matchScoreWeight by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public MatchScoreWeight findOne(String id) {
        log.debug("Request to get MatchScoreWeight : {}", id);
        return matchScoreWeightRepository.findOne(id);
    }

    /**
     * Delete the matchScoreWeight by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete MatchScoreWeight : {}", id);
        matchScoreWeightRepository.delete(id);
    }
}
