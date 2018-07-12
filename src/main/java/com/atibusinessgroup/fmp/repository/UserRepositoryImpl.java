package com.atibusinessgroup.fmp.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.atibusinessgroup.fmp.domain.User;
import com.atibusinessgroup.fmp.service.dto.UserDTO;
import com.atibusinessgroup.fmp.web.rest.UserResource.UserFilter;

public class UserRepositoryImpl implements UserRepositoryCustomAnyName{
	 
	@Autowired
    MongoTemplate mongoTemplate;

	@Override
	public Page<UserDTO> findCustom(UserFilter filter, Pageable pageable) {
		Criteria loginCriteria = new Criteria();
		if(filter.getUsername() != null && !filter.getUsername().contentEquals("")) {
			loginCriteria  = Criteria.where("login").regex(filter.getUsername(),"i");
		}
		Criteria emailCriteria = new Criteria();
		if(filter.getEmail() !=null && !filter.getEmail().contentEquals("")) {
			emailCriteria = Criteria.where("email").regex(filter.getEmail(),"i");
		}
		Criteria firstNameCriteria = new Criteria();
		if(filter.getFirstName()!=null && !filter.getFirstName().contentEquals("")) {
			firstNameCriteria = Criteria.where("first_name").regex(filter.getFirstName(),"i");
		}
		Criteria lastNameCriteria = new Criteria();
		if(filter.getLastName() !=null && !filter.getLastName().contentEquals("")) {
			lastNameCriteria = Criteria.where("last_name").regex(filter.getLastName(),"i");
		}
		
		Query query = new Query(new Criteria().andOperator(
				loginCriteria, emailCriteria, firstNameCriteria,lastNameCriteria
		)).with(pageable);
		List<User> userDTOs = mongoTemplate.find(query, User.class);
		Page<User> page = PageableExecutionUtils.getPage(
				userDTOs, 
				pageable,
				() -> mongoTemplate.count(query, User.class));
		return page.map(UserDTO::new);
	}

	@Override
	public List<UserDTO> findCustom(UserFilter filter) {
		Criteria loginCriteria = new Criteria();
		if(filter.getUsername() !=null) {
			loginCriteria  = Criteria.where("login").regex(filter.getUsername(),"i");
		}
		Criteria emailCriteria = new Criteria();
		if(filter.getEmail() !=null) {
			emailCriteria = Criteria.where("email").regex(filter.getEmail(),"i");
		}
		Criteria firstNameCriteria = new Criteria();
		if(filter.getFirstName()!=null) {
			firstNameCriteria = Criteria.where("first_name").regex(filter.getFirstName(),"i");
		}
		Criteria lastNameCriteria = new Criteria();
		if(filter.getLastName() !=null) {
			lastNameCriteria = Criteria.where("last_name").regex(filter.getLastName(),"i");
		}
		
		Query query = new Query(new Criteria().andOperator(
				loginCriteria, emailCriteria, firstNameCriteria,lastNameCriteria
			));
		List<UserDTO> userDTOs = mongoTemplate.find(query, UserDTO.class);
		return userDTOs;
	}


}
