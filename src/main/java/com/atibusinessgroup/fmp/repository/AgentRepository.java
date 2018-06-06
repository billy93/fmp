package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.Agent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Agent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgentRepository extends MongoRepository<Agent, String>, AgentRepositoryCustomAnyName {
	
	Optional<Agent> findOneByIataCode(String iata_code);

	Optional<Agent> findOneByEmail(String email);
	
	Optional<Agent> findOneById(String id);

	List<Agent> findCustom(Agent filter);
}
