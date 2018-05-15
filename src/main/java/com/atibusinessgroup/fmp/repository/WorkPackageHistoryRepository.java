package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.WorkPackageHistory;

/**
 * Spring Data MongoDB repository for the WorkPackage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkPackageHistoryRepository extends MongoRepository<WorkPackageHistory, String> {

	List<WorkPackageHistory> findAllByWorkPackage(ObjectId objectId);
	
}
