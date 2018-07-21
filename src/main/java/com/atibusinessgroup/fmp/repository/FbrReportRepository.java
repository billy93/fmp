package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atibusinessgroup.fmp.domain.FbrReport;


/**
 * Spring Data MongoDB repository for the Fbr Report entity.
 */
public interface FbrReportRepository extends MongoRepository<FbrReport,String> {
	
}
