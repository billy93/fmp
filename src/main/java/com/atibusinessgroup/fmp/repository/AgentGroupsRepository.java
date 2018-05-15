package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.AgentGroups;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the AgentGroups entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgentGroupsRepository extends MongoRepository<AgentGroups, String> {

}
