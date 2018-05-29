package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.AgentHistory;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Agent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgentHistoryRepository extends MongoRepository<AgentHistory, String> {
	
	Optional<AgentHistory> findOneById(String id);
}
