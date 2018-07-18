package com.atibusinessgroup.fmp.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.atibusinessgroup.fmp.domain.User;
import com.atibusinessgroup.fmp.domain.WorkPackage;
import com.atibusinessgroup.fmp.domain.WorkPackageFilter;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.WorkPackageMarketFare;
import com.atibusinessgroup.fmp.security.SecurityUtils;
import com.atibusinessgroup.fmp.service.util.DateUtil;
import com.atibusinessgroup.fmp.web.rest.WorkPackageResource.WorkPackageQuery;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


public class WorkPackageRepositoryImpl implements WorkPackageRepositoryCustomAnyName {

	@Autowired
    MongoTemplate mongoTemplate;
	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public Page<WorkPackage> findCustom(WorkPackageFilter wpFilter, Pageable pageable) {
		Query query = new Query(findByQuery(wpFilter)).with(pageable);	
		List<WorkPackage> workPackages = mongoTemplate.find(query, WorkPackage.class);
		
		Page<WorkPackage> page = PageableExecutionUtils.getPage(
				workPackages, 
				pageable, 
                () -> mongoTemplate.count(query,  WorkPackage.class));
		return page;
	}

	@Override
	public Page<WorkPackage> findCustomQuery(WorkPackageQuery filter, Pageable pageable) {
		Criteria wpIDCriteria = new Criteria();
		if(filter.getWpID() != null) {
			wpIDCriteria = Criteria.where("wpid").is(filter.getWpID());
		}		
		Criteria nameCriteria = new Criteria();
		if(filter.getName() != null) {
			nameCriteria = Criteria.where("name").regex(filter.getName(),"i");
		}		
		Criteria statusCriteria = new Criteria();
		if(filter.getStatus() != null) {
			statusCriteria = Criteria.where("status").regex(filter.getStatus(),"i");
		}
		Criteria targetCriteria = new Criteria();
		if(filter.getDistribution() != null) {
			targetCriteria = Criteria.where("target_distribution").is(filter.getDistribution());
		}
		Criteria typeCriteria = new Criteria();
		if(filter.getWpType() != null) {
			typeCriteria = Criteria.where("type").regex(filter.getWpType(),"i");
		}
		Criteria businessCriteria = new Criteria();
		if(filter.getBusinessArea() != null) {
			businessCriteria = Criteria.where("business_area").is(filter.getBusinessArea().getName());
		}
		Criteria createdCriteria = new Criteria();
		if(filter.getCreator() != null) {
			createdCriteria = Criteria.where("created_by").is(filter.getCreator().getLogin());
		}
		Criteria approvalCriteria = new Criteria();
		if(filter.getApproval() != null) {
			approvalCriteria = Criteria.where("fare_sheet.approval_reference").is(filter.getApproval());
		}
				
		Criteria fareTypeCriteria = new Criteria();
		if(filter.getFareClass() != null) {
			fareTypeCriteria = Criteria.where("fare_sheet.fare_type").is(filter.getFareClass().getName());
		}
		
		Criteria createdDateCriteria = new Criteria();
		if(filter.getCreatedDateFrom() !=null && filter.getCreatedDateTo() != null) {
			Date from = DateUtil.convertObjectToDate(filter.getCreatedDateFrom());
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(filter.getCreatedDateTo());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 0);
			Date to = calendar.getTime();
			
			createdDateCriteria = Criteria.where("created_date").gte(from).lte(to);
		}else if(filter.getCreatedDateFrom() !=null) {
			Date from = DateUtil.convertObjectToDate(filter.getCreatedDateFrom());
			createdDateCriteria = Criteria.where("created_date").gte(from);
		}else if(filter.getCreatedDateTo() !=null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(filter.getCreatedDateTo());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 0);
			Date to = calendar.getTime();
			createdDateCriteria = Criteria.where("created_date").lte(to);
		}
		
		
		Criteria filingDateCriteria = new Criteria();
		if(filter.getFilingDateFrom() !=null && filter.getFilingDateTo() != null) {
			Date from = DateUtil.convertObjectToDate(filter.getFilingDateFrom());
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(filter.getFilingDateTo());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 0);
			Date to = calendar.getTime();
			
			filingDateCriteria = Criteria.where("filing_date").gte(from).lte(to);
		}else if(filter.getFilingDateFrom() !=null) {
			Date from = DateUtil.convertObjectToDate(filter.getFilingDateFrom());
			filingDateCriteria = Criteria.where("filing_date").gte(from);
		}else if(filter.getFilingDateTo() !=null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(filter.getFilingDateTo());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 0);
			Date to = calendar.getTime();
			filingDateCriteria = Criteria.where("filing_date").lte(to);
		}
				
		Criteria distribDateCriteria = new Criteria();
		if(filter.getDistribDateFrom() !=null && filter.getDistribDateTo() != null) {
			Date from = DateUtil.convertObjectToDate(filter.getDistribDateFrom());
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(filter.getDistribDateTo());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 0);
			Date to = calendar.getTime();
			
			distribDateCriteria = Criteria.where("distribution_date").gte(from).lte(to);
		}else if(filter.getDistribDateFrom() !=null) {
			Date from = DateUtil.convertObjectToDate(filter.getDistribDateFrom());
			distribDateCriteria = Criteria.where("distribution_date").gte(from);
		}else if(filter.getDistribDateTo() !=null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(filter.getDistribDateTo());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 0);
			Date to = calendar.getTime();
			distribDateCriteria = Criteria.where("distribution_date").lte(to);
		}
		
		Criteria discDateCriteria = new Criteria();
		if(filter.getDiscDateFrom() !=null && filter.getDiscDateTo() != null) {
			Date from = DateUtil.convertObjectToDate(filter.getDiscDateFrom());
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(filter.getDiscDateTo());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 0);
			Date to = calendar.getTime();
			
			discDateCriteria = Criteria.where("discontinue_date").gte(from).lte(to);
		}else if(filter.getDiscDateFrom() !=null) {
			Date from = DateUtil.convertObjectToDate(filter.getDiscDateFrom());
			discDateCriteria = Criteria.where("discontinue_date").gte(from);
		}else if(filter.getDiscDateTo() !=null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(filter.getDiscDateTo());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 0);
			Date to = calendar.getTime();
			discDateCriteria = Criteria.where("discontinue_date").lte(to);
		}
		
		Query query = new Query(new Criteria().andOperator(wpIDCriteria,nameCriteria,statusCriteria,
				targetCriteria,typeCriteria, businessCriteria, createdCriteria, createdDateCriteria, 
				filingDateCriteria, distribDateCriteria, discDateCriteria, approvalCriteria, fareTypeCriteria))
				.with(pageable);
		
		List<WorkPackage> workPackages = mongoTemplate.find(query, WorkPackage.class);

		Page<WorkPackage> page = PageableExecutionUtils.getPage(
				workPackages, 
				pageable, 
				() -> mongoTemplate.count(query, WorkPackage.class));

		return page;
	}
	
	public Criteria findByQuery(WorkPackageFilter wpFilter){
		// TODO Auto-generated method stub
		
		String createdTime = wpFilter.getCreatedTime();
		Criteria createdTimeCriteria = null;
		if(createdTime.contentEquals("1")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(1, ChronoUnit.DAYS);		
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("2")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(2, ChronoUnit.DAYS);		
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("3")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(3, ChronoUnit.DAYS);		
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("4")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(4, ChronoUnit.DAYS);		
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("5")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(5, ChronoUnit.DAYS);		
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("6")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(6, ChronoUnit.DAYS);		
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("7")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(1, ChronoUnit.WEEKS);		
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("8")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(2, ChronoUnit.WEEKS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("9")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(3, ChronoUnit.WEEKS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("10")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(1, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("11")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(2, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("12")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(3, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("13")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(4, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("14")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(5, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("15")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(6, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("16")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(7, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("17")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(8, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("18")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(9, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("19")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(10, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("20")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(11, ChronoUnit.MONTHS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("21")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(1, ChronoUnit.YEARS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("22")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(2, ChronoUnit.YEARS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("23")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
			ZonedDateTime searchTime = today.minus(3, ChronoUnit.YEARS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").gte(searchTime).lte(today);
		}
		else if(createdTime.contentEquals("24")) {
			ZonedDateTime today = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
//			ZonedDateTime searchTime = today.minus(3, ChronoUnit.YEARS);	
			today = today.plus(1, ChronoUnit.DAYS);
			createdTimeCriteria = Criteria.where("created_date").lte(today);
		}
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
		
		if(reviewLevels.isEmpty()) {
			reviewLevels.add(null);
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
		if(wpFilter.status.withdrawn) {
			status.add("WITHDRAWN");
		}
		if(wpFilter.status.referred) {
			status.add("REFERRED");
		}
		if(wpFilter.status.discontinued) {
			status.add("DISCONTINUED");
		}
		if(status.isEmpty()) {
			status.add(null);
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
		
		//BUSINESS AREA 
		List<String> businessArea = new ArrayList<>();
		User u = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
		businessArea.addAll(u.getBusinessAreas());
		if(businessArea.isEmpty()) {
			businessArea.add(null);
		}
		Criteria businessAreaCriteria = Criteria.where("business_area").in(businessArea);
		//END BUSINESS AREA
		
		Criteria approvalReference = new Criteria();
		if(wpFilter.getApprovalReference() != null && !wpFilter.getApprovalReference().contentEquals("")) {
			approvalReference = Criteria.where("fare_sheet.approval_reference").regex(wpFilter.getApprovalReference(), "i");
		}
		
		Criteria replaceCriteria = new Criteria();
		if(wpFilter.status.replace) {
			replaceCriteria = Criteria.where("replace_from").ne(null);
		}
		
		Criteria reuseCriteria = new Criteria();
		if(wpFilter.status.reuse) {
			reuseCriteria = Criteria.where("reuse_from").ne(null);
		}	
		
		Criteria wpidCriteria = Criteria.where("wpid").ne(null);
		Criteria criteriaAnd1Query = new Criteria().andOperator(
				createdTimeCriteria,
				wpidCriteria,
				reviewLevels.size() > 0 ? reviewLevelCriteria : new Criteria(),
				status.size() > 0 ? statusCriteria : new Criteria(),
				distributionTypes.size() > 0 ?	distributionTypesCriteria : new Criteria(),
				types.size() > 0 ? typesCriteria : new Criteria(),
				businessArea.size() > 0 ? businessAreaCriteria : new Criteria(),
				approvalReference,
				wpFilter.status.replace && wpFilter.status.reuse ? 
						new Criteria().orOperator(replaceCriteria, reuseCriteria) : 
							wpFilter.status.replace ? replaceCriteria :
								wpFilter.status.reuse ? reuseCriteria : new Criteria()
		);		
		
		return criteriaAnd1Query;
	}
	
	@Override
	public List<WorkPackage> findCustom(WorkPackageFilter workPackageFilter) {
		// TODO Auto-generated method stub
		Query query = new Query(findByQuery(workPackageFilter));	
		List<WorkPackage> workPackages = mongoTemplate.find(query, WorkPackage.class);

		return workPackages;
	}

	@Override
	public Page<WorkPackageMarketFare> findAllMarketFare(AfdQueryParam param, Pageable pageable) {
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				match.append("$match", new BasicDBObject("type", "REGULAR").append("target_distribution", "MARKET"));
				return match;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject unwind = new BasicDBObject();
				unwind.append("$unwind", new BasicDBObject("path", "$market_fare_sheet"));
				return unwind;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject();
				project.append("$project", new BasicDBObject("fares", "$market_fare_sheet.fares").append("wpid", "$wpid").append("wpname", "$name"));
				return project;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject unwind = new BasicDBObject();
				unwind.append("$unwind", new BasicDBObject("path", "$fares"));
				return unwind;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject and = new BasicDBObject();
				List<BasicDBObject> queries = new ArrayList<>();
				
				if (param.getCarrier() != null && !param.getCarrier().isEmpty()) {
					BasicDBObject carrier = new BasicDBObject();
					carrier.append("fares.carrier", new BasicDBObject("$in",  Arrays.stream(param.getCarrier().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(carrier);
				}
				
				if (param.getOrigin() != null && !param.getOrigin().isEmpty()) {
					BasicDBObject origin = new BasicDBObject();
					origin.append("fares.origin", new BasicDBObject("$in",  Arrays.stream(param.getOrigin().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(origin);
				}
				
				if (param.getDestination() != null && !param.getDestination().isEmpty()) {
					BasicDBObject destination = new BasicDBObject();
					destination.append("fares.destination", new BasicDBObject("$in",  Arrays.stream(param.getDestination().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(destination);
				}
				
				if (queries.size() > 0) {
					and.append("$and", queries);
				}
				
				match.append("$match", and);
				
				return match;
			}
		});
		
//		{
//	        $match: {
//	            $and: [
//	                {
//	                    'fares.carrier': 'GA'
//	                },
//	                {
//	                    'fares.origin': 'JKT'
//	                },
//	                {
//	                    'fares.destination': 'SIN'
//	                }  
//	            ]
//	        }
//	    }
		
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);

		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);

		Aggregation aggregationPagination = newAggregation(aggregationOperations);
		
		List<WorkPackageMarketFare> result = mongoTemplate.aggregate(aggregationPagination, "work_package", WorkPackageMarketFare.class).getMappedResults();
		
		return new PageImpl<>(result, pageable, mongoTemplate.aggregate(aggregation, "work_package", WorkPackageMarketFare.class).getMappedResults().size());
	}
}
