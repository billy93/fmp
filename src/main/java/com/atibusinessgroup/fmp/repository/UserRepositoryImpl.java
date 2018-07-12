package com.atibusinessgroup.fmp.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.atibusinessgroup.fmp.service.dto.UserDTO;

public class UserRepositoryImpl implements UserRepositoryCustomAnyName{
	 
	@Autowired
    MongoTemplate mongoTemplate;

	@Override
	public Page<UserDTO> findCustom(UserDTO filter, Pageable pageable) {
		System.out.println("filter : "+filter);
		Criteria loginCriteria = new Criteria();
		if(filter.getLogin() !=null) {
			loginCriteria  = Criteria.where("agent_name").regex(filter.getLogin(),"i");
		}
		Criteria emailCriteria = new Criteria();
		if(filter.getEmail() !=null) {
			emailCriteria = Criteria.where("agent_type").regex(filter.getEmail(),"i");
		}
		Criteria firstNameCriteria = new Criteria();
		if(filter.getFirstName()!=null) {
			firstNameCriteria = Criteria.where("agent_category").regex(filter.getFirstName(),"i");
		}
		Criteria lastNameCriteria = new Criteria();
		if(filter.getLastName() !=null) {
			lastNameCriteria = Criteria.where("pos_country").regex(filter.getLastName(),"i");
		}
		
		Query query = new Query(new Criteria().andOperator(
				loginCriteria, emailCriteria, firstNameCriteria,lastNameCriteria
			)).with(pageable);
		List<UserDTO> userDTOs = mongoTemplate.find(query, UserDTO.class);
		Page<UserDTO> page = PageableExecutionUtils.getPage(
				userDTOs, 
				pageable,
				() -> mongoTemplate.count(query, UserDTO.class));
		return page;
	}

	@Override
	public List<UserDTO> findCustom(UserDTO filter) {
		Criteria loginCriteria = new Criteria();
		if(filter.getLogin() !=null) {
			loginCriteria  = Criteria.where("agent_name").regex(filter.getLogin(),"i");
		}
		Criteria emailCriteria = new Criteria();
		if(filter.getEmail() !=null) {
			emailCriteria = Criteria.where("agent_type").regex(filter.getEmail(),"i");
		}
		Criteria firstNameCriteria = new Criteria();
		if(filter.getFirstName()!=null) {
			firstNameCriteria = Criteria.where("agent_category").regex(filter.getFirstName(),"i");
		}
		Criteria lastNameCriteria = new Criteria();
		if(filter.getLastName() !=null) {
			lastNameCriteria = Criteria.where("pos_country").regex(filter.getLastName(),"i");
		}
		
		Query query = new Query(new Criteria().andOperator(
				loginCriteria, emailCriteria, firstNameCriteria,lastNameCriteria
			));
		List<UserDTO> userDTOs = mongoTemplate.find(query, UserDTO.class);
		return userDTOs;
	}


}
