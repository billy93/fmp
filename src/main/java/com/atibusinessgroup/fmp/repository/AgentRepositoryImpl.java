package com.atibusinessgroup.fmp.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.atibusinessgroup.fmp.domain.Agent;

public class AgentRepositoryImpl implements AgentRepositoryCustomAnyName{

	@Autowired
    MongoTemplate mongoTemplate;

	@Override
	public Page<Agent> findCustom(Agent filter, Pageable pageable) {
		Criteria nameCriteria = new Criteria();
		if(filter.getAgentName()!=null) {
			nameCriteria  = Criteria.where("agent_name").regex(filter.getAgentName(),"i");
		}
		Criteria agentTypeCriteria = new Criteria();
		if(filter.getAgentType()!=null) {
			agentTypeCriteria = Criteria.where("agent_type").regex(filter.getAgentType(),"i");
		}
		Criteria agentCategoryCriteria = new Criteria();
		if(filter.getAgentCategory()!=null) {
			agentCategoryCriteria = Criteria.where("agent_category").regex(filter.getAgentCategory(),"i");
		}
		Criteria posCountryCriteria = new Criteria();
		if(filter.getPosCountry()!=null) {
			posCountryCriteria = Criteria.where("pos_country").regex(filter.getPosCountry(),"i");
		}
		Criteria posCityCriteria = new Criteria();
		if(filter.getPosCity()!=null) {
			posCityCriteria = Criteria.where("pos_city").regex(filter.getPosCity(),"i");
		}
		Criteria iataCodeCriteria = new Criteria();
		if(filter.getIataCode()!=null) {
			iataCodeCriteria = Criteria.where("iata_code").regex(filter.getIataCode(),"i");
		}
		Criteria isDeletedCriteria = new Criteria();
		if(filter.getIsDeleted()!=null) {
			isDeletedCriteria = Criteria.where("is_deleted").is(filter.getIsDeleted());
		}
		
		
		Query query = new Query(new Criteria().andOperator(
				nameCriteria, agentTypeCriteria, agentCategoryCriteria, posCountryCriteria, posCityCriteria,
				iataCodeCriteria, isDeletedCriteria
			)).with(pageable);
		List<Agent> agents = mongoTemplate.find(query, Agent.class);
		
		Page<Agent> page = PageableExecutionUtils.getPage(
				agents, 
				pageable, 
				() -> mongoTemplate.count(query, Agent.class));
		return page;
	}
	
	

}
