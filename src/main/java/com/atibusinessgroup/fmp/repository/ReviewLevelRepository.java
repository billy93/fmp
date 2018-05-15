package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.ReviewLevel;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the ReviewLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReviewLevelRepository extends MongoRepository<ReviewLevel, String> {

}
