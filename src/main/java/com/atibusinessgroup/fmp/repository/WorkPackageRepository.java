package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.WorkPackage;
import com.atibusinessgroup.fmp.domain.enumeration.Status;
import com.atibusinessgroup.fmp.web.rest.WorkPackageResource.WorkPackageFilter;

/**
 * Spring Data MongoDB repository for the WorkPackage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkPackageRepository extends MongoRepository<WorkPackage, String>, WorkPackageRepositoryCustomAnyName{

	@Query("{'status' : ?0}")
	List<WorkPackage> findAllByStatus(String status);

	Page<WorkPackage> findAllByOrderByLastModifiedDateDesc(Pageable pageable);

}
