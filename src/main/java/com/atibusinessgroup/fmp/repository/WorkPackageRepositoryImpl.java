package com.atibusinessgroup.fmp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.atibusinessgroup.fmp.domain.WorkPackage;
import com.atibusinessgroup.fmp.web.rest.WorkPackageResource.WorkPackageFilter;

public class WorkPackageRepositoryImpl implements WorkPackageRepositoryCustomAnyName{

	@Autowired
    MongoTemplate mongoTemplate;
	
	@Override
	public Page<WorkPackage> findCustom(WorkPackageFilter wpFilter, Pageable pageable) {
		// TODO Auto-generated method stub
		
		///// REVIEW LEVEL
		List<String> reviewLevels = new ArrayList<>();
		if(wpFilter.getReviewLevel().ho) {
			reviewLevels.add("HO");
		}
		if(wpFilter.getReviewLevel().lso) {
			reviewLevels.add("LSO");
		}
		if(wpFilter.getReviewLevel().distribution) {
			reviewLevels.add("DISTRIBUTION");
		}
		if(wpFilter.getReviewLevel().routeManagement) {
			reviewLevels.add("ROUTE_MANAGEMENT");
		}
		Criteria reviewLevelCriteria = Criteria.where("review_level").in(reviewLevels);
		///// END REVIEW LEVEL
		
		//STATUS
		List<String> status = new ArrayList<>();
		if(wpFilter.status.newStatus) {
			status.add("NEW");
		}
		if(wpFilter.status.completed) {
			status.add("COMPLETED");
		}
		if(wpFilter.status.distributed) {
			status.add("DISTRIBUTED");
		}
		if(wpFilter.status.pending) {
			status.add("PENDING");
		}
		if(wpFilter.status.readyToRelease) {
			status.add("READY_TO_RELEASE");
		}
		if(wpFilter.status.reviewing) {
			status.add("REVIEWING");
		}
		if(wpFilter.status.withdraw) {
			status.add("WITHDRAW");
		}
		Criteria statusCriteria = Criteria.where("status").in(status);
		//END STATUS
		
		//DISTRIBUTION TYPE
		List<String> distributionTypes = new ArrayList<>();
		if(wpFilter.distributionType.atpco) {
			distributionTypes.add("ATPCO");
		}
		if(wpFilter.distributionType.market) {
			distributionTypes.add("MARKET");
		}
		Criteria distributionTypesCriteria = Criteria.where("target_distribution").in(distributionTypes);
		//END DISTRIBUTION TYPE
		
		//TYPE
		List<String> types = new ArrayList<>();
		if(wpFilter.type.regular) {
			types.add("REGULAR");
		}
		if(wpFilter.type.discount) {
			types.add("DISCOUNT");
		}
		if(wpFilter.type.waiver) {
			types.add("WAIVER");
		}
		Criteria typesCriteria = Criteria.where("type").in(types);
		//END TYPES
		
		Criteria approvalReference = new Criteria();
		if(wpFilter.getApprovalReference() != null && !wpFilter.getApprovalReference().contentEquals("")) {
			approvalReference = Criteria.where("fare_sheet.approval_reference").regex(wpFilter.getApprovalReference(), "i");
		}
		
		Query query = new Query(new Criteria().andOperator(
			reviewLevels.size() > 0 ? reviewLevelCriteria : new Criteria(),
			status.size() > 0 ? statusCriteria : new Criteria(),
			distributionTypes.size() > 0 ?	distributionTypesCriteria : new Criteria(),
			types.size() > 0 ? typesCriteria : new Criteria(),
			approvalReference					
		)).with(pageable);
		List<WorkPackage> workPackages = mongoTemplate.find(query, WorkPackage.class);
		
		Page<WorkPackage> page = PageableExecutionUtils.getPage(
				workPackages, 
				pageable, 
                () -> mongoTemplate.count(query,  WorkPackage.class));
		return page;
	}

}
