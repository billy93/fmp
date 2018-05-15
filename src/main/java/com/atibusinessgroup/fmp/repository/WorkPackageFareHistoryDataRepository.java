package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.WorkPackageFare;
import com.atibusinessgroup.fmp.domain.WorkPackageFareHistoryData;

/**
 * Spring Data MongoDB repository for the Fare entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkPackageFareHistoryDataRepository extends MongoRepository<WorkPackageFareHistoryData, String> {

	List<WorkPackageFare> findAllByWorkPackage(ObjectId objectId);

	List<WorkPackageFare> findAllByWorkPackageAndFareType(ObjectId objectId, String string);

	List<WorkPackageFare> findAllByOriginAndDestinationAndFareBasis(String origin, String destination,
			String fareBasis);

}
