package com.atibusinessgroup.fmp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atibusinessgroup.fmp.domain.Agent;

public interface AgentRepositoryCustomAnyName {
	
	Page<Agent> findCustom(Agent filter, Pageable pageable);
	
}
