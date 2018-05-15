package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.WorkPackage;
import com.atibusinessgroup.fmp.domain.WorkPackageHistoryData;
import com.atibusinessgroup.fmp.domain.enumeration.Status;

/**
 * Spring Data MongoDB repository for the WorkPackage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkPackageHistoryDataRepository extends MongoRepository<WorkPackageHistoryData, String> {

	long countByWorkPackage(ObjectId objectId);

	WorkPackage findByWorkPackageAndVersion(ObjectId objectId, String version);

}
