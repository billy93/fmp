package com.atibusinessgroup.fmp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.WorkPackageFilter;

/**
 * Spring Data MongoDB repository for the WorkPackage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkPackageFilterRepository extends MongoRepository<WorkPackageFilter, String>{
	
	Optional<WorkPackageFilter> findOneByLoginName(String loginName);
	
	List<WorkPackageFilter> findByLoginName(String loginName);
}
