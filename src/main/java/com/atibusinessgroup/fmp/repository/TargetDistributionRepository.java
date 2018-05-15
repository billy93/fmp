package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.TargetDistribution;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TargetDistribution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TargetDistributionRepository extends MongoRepository<TargetDistribution, String> {

	TargetDistribution findOneByName(String string);

}
