package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.AgentCategory;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the AgentCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgentCategoryRepository extends MongoRepository<AgentCategory, String> {

}
