package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat04;

/**
 * Spring Data MongoDB repository for the AtpcoRecord3Cat04 entity.
 */
@Repository
public interface AtpcoRecord3Cat04Repository extends MongoRepository<AtpcoRecord3Cat04, String> {

	@Query("{\"$and\" : [" +
				"{ \"cat_no\" : ?0 }, " +
				"{ \"tbl_no\" : { \"$in\" : ?1 }}" +
		   "]}")
	List<AtpcoRecord3Cat04> findAllByCatNoAndTableNo(String catNo, List<String> tableNos);
	
}
