package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.TargetDistribution;
import com.atibusinessgroup.fmp.repository.TargetDistributionRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing TargetDistribution.
 */
@Service
public class TargetDistributionService {

    private final Logger log = LoggerFactory.getLogger(TargetDistributionService.class);

    private final TargetDistributionRepository targetDistributionRepository;

    public TargetDistributionService(TargetDistributionRepository targetDistributionRepository) {
        this.targetDistributionRepository = targetDistributionRepository;
    }

    /**
     * Save a targetDistribution.
     *
     * @param targetDistribution the entity to save
     * @return the persisted entity
     */
    public TargetDistribution save(TargetDistribution targetDistribution) {
        log.debug("Request to save TargetDistribution : {}", targetDistribution);
        return targetDistributionRepository.save(targetDistribution);
    }

    /**
     * Get all the targetDistributions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<TargetDistribution> findAll(Pageable pageable) {
        log.debug("Request to get all TargetDistributions");
        return targetDistributionRepository.findAll(pageable);
    }

    /**
     * Get one targetDistribution by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public TargetDistribution findOne(String id) {
        log.debug("Request to get TargetDistribution : {}", id);
        return targetDistributionRepository.findOne(id);
    }

    /**
     * Delete the targetDistribution by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete TargetDistribution : {}", id);
        targetDistributionRepository.delete(id);
    }

	public List<TargetDistribution> findAll() {
		log.debug("Request to get all TargetDistributions");
        return targetDistributionRepository.findAll();
	}

	public TargetDistribution findOneByName(String string) {
		// TODO Auto-generated method stub
		return targetDistributionRepository.findOneByName(string);
	}
}
