package com.atibusinessgroup.fmp.repository;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.atibusinessgroup.fmp.domain.CityGroup;
import com.atibusinessgroup.fmp.web.rest.CityGroupResource.CityGroupFilter;

public class CityGroupRepositoryImpl implements CityGroupRepositoryCustomAnyName{

	 private final Logger log = LoggerFactory.getLogger(CityGroupRepositoryImpl.class);
	 
	@Autowired
    MongoTemplate mongoTemplate;

	
	@Override
	public Page<CityGroup> findCustom(CityGroupFilter filter, Pageable pageable) {
		Page<CityGroup> page = null ;
		if(filter.getOperator() == null) {
			filter.setOperator("or");
		}
		Criteria codeCriteria = new Criteria();
		if(filter.getCode() != null) {
			codeCriteria = Criteria.where("code").regex(filter.getCode(),"i");
		}
		Criteria descriptionCriteria = new Criteria();
		if(filter.getDescription() != null) {
			descriptionCriteria = Criteria.where("description").regex(filter.getDescription(),"i");
		}
		
		Criteria cityCriteria = new Criteria();
		if(filter.getCities().getCityCode() != null) {
			cityCriteria = Criteria.where("cities.city_code").regex(filter.getCities().getCityCode(),"i");
		}
		Criteria countryCriteria = new Criteria();
		if(filter.getCities().getCountryCode() != null) {
			countryCriteria = Criteria.where("cities.country_code").regex(filter.getCities().getCountryCode(),"i");
		}
			
		if(filter.getOperator().equals("or")) {			
			Criteria c = new Criteria().andOperator(
					new Criteria().andOperator(codeCriteria, descriptionCriteria),
					new Criteria().orOperator(cityCriteria,countryCriteria));
			
			Query query = new Query(c)
					.with(pageable);
			List<CityGroup> cityGroups = mongoTemplate.find(query, CityGroup.class);
			
			page = PageableExecutionUtils.getPage(
					cityGroups, 
					pageable, 
					() -> mongoTemplate.count(query, CityGroup.class));
		}else if(filter.getOperator().equals("and")){
			Criteria c = new Criteria().andOperator(
					new Criteria().andOperator(codeCriteria, descriptionCriteria),
					new Criteria().andOperator(cityCriteria,countryCriteria));
			
			Query query = new Query(c)
					.with(pageable);
			List<CityGroup> cityGroups = mongoTemplate.find(query, CityGroup.class);
			
			page = PageableExecutionUtils.getPage(
					cityGroups, 
					pageable, 
					() -> mongoTemplate.count(query, CityGroup.class));
		}
				
		return page;
	}

	@Override
	public List<CityGroup> findCustom(CityGroupFilter filter) {
		Criteria codeCriteria = new Criteria();
		if(filter.getCode() != null) {
			codeCriteria = Criteria.where("code").regex(filter.getCode(),"i");
		}
		Criteria descriptionCriteria = new Criteria();
		if(filter.getDescription() != null) {
			descriptionCriteria = Criteria.where("description").regex(filter.getDescription(),"i");
		}
		Query query = new Query(new Criteria().andOperator(codeCriteria, descriptionCriteria));
		
		List<CityGroup> cityGroups = mongoTemplate.find(query, CityGroup.class);
		
		return cityGroups;
	}
	
	

}
