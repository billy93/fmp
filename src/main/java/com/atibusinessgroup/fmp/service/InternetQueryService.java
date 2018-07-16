package com.atibusinessgroup.fmp.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.dto.InternetQuery;
import com.atibusinessgroup.fmp.domain.dto.InternetQueryParam;
import com.atibusinessgroup.fmp.domain.dto.MasterWebsite;
import com.atibusinessgroup.fmp.repository.VoltrasFareRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class InternetQueryService {

	private final Logger log = LoggerFactory.getLogger(InternetQueryService.class);
	private final VoltrasFareRepository voltrasFareRepository;
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	public InternetQueryService(VoltrasFareRepository voltrasFareRepository) {
		this.voltrasFareRepository = voltrasFareRepository;
	}
	
	public Page<InternetQuery> getSummarizeCaptDateQueries(InternetQueryParam param, Pageable pageable) {
		List<AggregationOperation> aggregationOperations = getAggregationOperationGroup(param, 0);
		Aggregation aggregation = newAggregation(aggregationOperations);
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);
		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);
		Aggregation aggregationPagination = newAggregation(aggregationOperations);
		
		List<InternetQuery> result = mongoTemplate.aggregate(aggregationPagination, InternetQuery.class, InternetQuery.class).getMappedResults();
		long allResultCount = mongoTemplate.aggregate(aggregation, InternetQuery.class, InternetQuery.class).getMappedResults().size();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		List<String> dateList = new ArrayList<>();
		for (InternetQuery internetQuery : result) {
			InternetQueryParam detailParam = new InternetQueryParam();
			detailParam.setCxr(internetQuery.getCxr());
			detailParam.setWebsite(internetQuery.getWebsite());
			detailParam.setOrigin(internetQuery.getOrigin());
			detailParam.setDestination(internetQuery.getDestination());
			detailParam.setApDays(internetQuery.getApDays());
			aggregationOperations = getAggregationOperationGroupDetail(detailParam, 0);
			aggregation = newAggregation(aggregationOperations);
			List<InternetQuery> detailResults = mongoTemplate.aggregate(aggregation, InternetQuery.class, InternetQuery.class).getMappedResults();
			for (InternetQuery internetQueryDetail : detailResults) {
				String captureSdf = sdf.format(internetQueryDetail.getCaptureDateTime());
				if(!dateList.contains(captureSdf)) {
					dateList.add(captureSdf);
				}
			}
		}
		
		for (InternetQuery internetQuery : result) {
			for (String captureSdf : dateList) {
				internetQuery.getDatePrice().put(captureSdf, null);
			}
			
			InternetQueryParam detailParam = new InternetQueryParam();
			detailParam.setCxr(internetQuery.getCxr());
			detailParam.setWebsite(internetQuery.getWebsite());
			detailParam.setOrigin(internetQuery.getOrigin());
			detailParam.setDestination(internetQuery.getDestination());
			detailParam.setApDays(internetQuery.getApDays());
			aggregationOperations = getAggregationOperationGroupDetail(detailParam, 0);
			aggregation = newAggregation(aggregationOperations);
			List<InternetQuery> detailResults = mongoTemplate.aggregate(aggregation, InternetQuery.class, InternetQuery.class).getMappedResults();
			for (InternetQuery internetQueryDetail : detailResults) {
				String captureSdf = sdf.format(internetQueryDetail.getCaptureDateTime());
				internetQuery.getDatePrice().put(captureSdf, internetQueryDetail.getAif().bigDecimalValue());
			}
		}
		
		for (InternetQuery internetQuery : result) {
			Map<String, BigDecimal> oldDataPrice = internetQuery.getDatePrice();
			Map<String, BigDecimal> newDataPrice = new TreeMap<String, BigDecimal>(oldDataPrice);
			internetQuery.setDatePrice(newDataPrice);
		}
		
		return new PageImpl<>(result, pageable, allResultCount);
	}
	
	public Page<InternetQuery> getSummarizeDeptDateQueries(InternetQueryParam param, Pageable pageable) {
		List<AggregationOperation> aggregationOperations = getAggregationOperationGroup(param, 1);
		Aggregation aggregation = newAggregation(aggregationOperations);
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);
		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);
		Aggregation aggregationPagination = newAggregation(aggregationOperations);
		
		List<InternetQuery> result = mongoTemplate.aggregate(aggregationPagination, InternetQuery.class, InternetQuery.class).getMappedResults();
		long allResultCount = mongoTemplate.aggregate(aggregation, InternetQuery.class, InternetQuery.class).getMappedResults().size();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		List<String> dateList = new ArrayList<>();
		for (InternetQuery internetQuery : result) {
			InternetQueryParam detailParam = new InternetQueryParam();
			detailParam.setCxr(internetQuery.getCxr());
			detailParam.setWebsite(internetQuery.getWebsite());
			detailParam.setOrigin(internetQuery.getOrigin());
			detailParam.setDestination(internetQuery.getDestination());
			detailParam.setApDays(internetQuery.getApDays());
			aggregationOperations = getAggregationOperationGroupDetail(detailParam, 1);
			aggregation = newAggregation(aggregationOperations);
			List<InternetQuery> detailResults = mongoTemplate.aggregate(aggregation, InternetQuery.class, InternetQuery.class).getMappedResults();
			for (InternetQuery internetQueryDetail : detailResults) {
				String departSdf = sdf.format(internetQueryDetail.getDepartDateTime());
				if(!dateList.contains(departSdf)) {
					dateList.add(departSdf);
				}
			}
		}
		
		for (InternetQuery internetQuery : result) {
			for (String departSdf : dateList) {
				internetQuery.getDatePrice().put(departSdf, null);
			}
			
			InternetQueryParam detailParam = new InternetQueryParam();
			detailParam.setCxr(internetQuery.getCxr());
			detailParam.setWebsite(internetQuery.getWebsite());
			detailParam.setOrigin(internetQuery.getOrigin());
			detailParam.setDestination(internetQuery.getDestination());
			detailParam.setApDays(internetQuery.getApDays());
			aggregationOperations = getAggregationOperationGroupDetail(detailParam, 1);
			aggregation = newAggregation(aggregationOperations);
			List<InternetQuery> detailResults = mongoTemplate.aggregate(aggregation, InternetQuery.class, InternetQuery.class).getMappedResults();
			for (InternetQuery internetQueryDetail : detailResults) {
				String departSdf = sdf.format(internetQueryDetail.getDepartDateTime());
				internetQuery.getDatePrice().put(departSdf, internetQueryDetail.getAif().bigDecimalValue());
			}
		}
		
		for (InternetQuery internetQuery : result) {
			Map<String, BigDecimal> oldDataPrice = internetQuery.getDatePrice();
			Map<String, BigDecimal> newDataPrice = new TreeMap<String, BigDecimal>(oldDataPrice);
			internetQuery.setDatePrice(newDataPrice);
		}
		
		return new PageImpl<>(result, pageable, allResultCount);
	}
	
	public Page<InternetQuery> getDontSummarizeQueries(InternetQueryParam param, Pageable pageable) {
		List<AggregationOperation> aggregationOperations = getAggregationOperation(param);
		Aggregation aggregation = newAggregation(aggregationOperations);
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);
		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);
		Aggregation aggregationPagination = newAggregation(aggregationOperations);
		
		List<InternetQuery> result = mongoTemplate.aggregate(aggregationPagination, InternetQuery.class, InternetQuery.class).getMappedResults();
		long allResultCount = mongoTemplate.aggregate(aggregation, InternetQuery.class, InternetQuery.class).getMappedResults().size();
		
		for (InternetQuery internetQuery : result) {
			internetQuery.setBaseAmtBD(internetQuery.getBaseAmt().bigDecimalValue());
			internetQuery.setTaxesBD(internetQuery.getTaxes().bigDecimalValue());
			internetQuery.setAifBD(internetQuery.getAif().bigDecimalValue());
		}
		
		return new PageImpl<>(result, pageable, allResultCount);
	}
	
	@SuppressWarnings("unchecked")
	public List<MasterWebsite> findAllMasterWebsite() {
		List<MasterWebsite> result = mongoTemplate.getCollection("voltras_fare").distinct("site_name");
		
		return result;
	}
	
	private List<AggregationOperation> getAggregationOperation(InternetQueryParam param) {
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					match.append("carrier_code", param.getCxr());
				}
				if (param.getWebsite() != null && !param.getWebsite().isEmpty()) {
					match.append("site_name", param.getWebsite());
				}
				if (param.getOrigin() != null && !param.getOrigin().isEmpty()) {
					match.append("origin", param.getOrigin());
				}
				if (param.getDestination() != null && !param.getDestination().isEmpty()) {
					match.append("destination", param.getDestination());
				}
				
				if (param.getDepartDateFrom() != null && param.getDepartDateTo() != null) {
					Date zdtDepartFrom = editTimeOfDate(param.getDepartDateFrom(), 0, 0, 0);
					Date zdtDepartTo = editTimeOfDate(param.getDepartDateTo(), 23, 59, 59);
					match.append("depart_date_time", new BasicDBObject("$gte", zdtDepartFrom).append("$lte", zdtDepartTo));
				} else if(param.getDepartDateFrom() != null) {
					Date zdtDepartFrom = editTimeOfDate(param.getDepartDateFrom(), 0, 0, 0);
					match.append("depart_date_time", new BasicDBObject("$gte", zdtDepartFrom));
				} else if(param.getDepartDateTo() != null) {
					Date zdtDepartTo = editTimeOfDate(param.getDepartDateTo(), 23, 59, 59);
					match.append("depart_date_time", new BasicDBObject("$lte", zdtDepartTo));
				}
				
				if (param.getCaptureDateFrom() != null && param.getCaptureDateTo() != null) {
					Date zdtCaptureFrom = editTimeOfDate(param.getCaptureDateFrom(), 0, 0, 0);
					Date zdtCaptureTo = editTimeOfDate(param.getCaptureDateTo(), 23, 59, 59);
					match.append("capture_date_time", new BasicDBObject("$gte", zdtCaptureFrom).append("$lte", zdtCaptureTo));
				} else if(param.getCaptureDateFrom() != null) {
					Date zdtCaptureFrom = editTimeOfDate(param.getCaptureDateFrom(), 0, 0, 0);
					match.append("capture_date_time", new BasicDBObject("$gte", zdtCaptureFrom));
				} else if(param.getCaptureDateTo() != null) {
					Date zdtCaptureTo = editTimeOfDate(param.getCaptureDateTo(), 23, 59, 59);
					match.append("capture_date_time", new BasicDBObject("$lte", zdtCaptureTo));
				}
				
				return new BasicDBObject("$match", match);
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject();
				
				project.append("website", "$site_name");
				project.append("capture_date_time", "$capture_date_time");
				project.append("ap_days", 
						new BasicDBObject("$ceil", 
								new BasicDBObject("$divide", 
										Arrays.asList(
												new BasicDBObject("$subtract", Arrays.asList("$depart_date_time", "$capture_date_time")), 
												86400000))));
				project.append("cxr", "$carrier_code");
				project.append("airline", "$carrier_code");
				project.append("trip_type", "$round_trip");
				project.append("origin", "$origin");
				project.append("origin_name", "");
				project.append("destination", "$destination");
				project.append("destination_name", "");
				project.append("fare_basis", "");
				project.append("depart_date_time", "$depart_date_time");
				project.append("return_date_time", "$return_date_time");
				project.append("flight_number", "$flight_number");
				project.append("base_amt", "$price");
				project.append("taxes", "$tax");
				project.append("aif", "$total_price");
				project.append("currency", "$currency");
				project.append("ref_amt", "");
				project.append("depart_dow", new BasicDBObject("$dayOfWeek", "$depart_date_time"));
				
				return new BasicDBObject("$project", project);
			}
		});
		
		if (param.getDepartDOW() > 0) {
			aggregationOperations.add(new AggregationOperation() {
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject match = new BasicDBObject();
					match.append("depart_dow", param.getDepartDOW());
					
					return new BasicDBObject("$match", match);
				}
			});
		}
		
		return aggregationOperations;
	}
	
	private List<AggregationOperation> getAggregationOperationGroup(InternetQueryParam param, int viewDataType) {
		List<AggregationOperation> aggregationOperations = getAggregationOperation(param);
		
		if(viewDataType == 0 || viewDataType == 1) {
			if(viewDataType == 0) {
				aggregationOperations.add(new AggregationOperation() {
					@Override
					public DBObject toDBObject(AggregationOperationContext context) {
						BasicDBObject query = new BasicDBObject();
						query.append("_id", 
								new BasicDBObject("cxr", "$cxr_code")
								.append("origin", "$origin")
								.append("destination", "$destination")
								.append("ap_days", "$ap_days")
								.append("website", "$website")
								.append("date", "$capture_date_time"));
						
						return new BasicDBObject("$group", query);
					}
				});
			} else if(viewDataType == 1) {
				aggregationOperations.add(new AggregationOperation() {
					@Override
					public DBObject toDBObject(AggregationOperationContext context) {
						BasicDBObject query = new BasicDBObject();
						query.append("_id", 
								new BasicDBObject("cxr", "$cxr_code")
								.append("origin", "$origin")
								.append("destination", "$destination")
								.append("ap_days", "$ap_days")
								.append("website", "$website")
								.append("date", "$depart_date_time"));
						
						return new BasicDBObject("$group", query);
					}
				});
			}
			
			aggregationOperations.add(new AggregationOperation() {
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject project = new BasicDBObject();
					project.append("_id", 0);
					project.append("cxr", "$_id.cxr");
					project.append("origin", "$_id.origin");
					project.append("destination", "$_id.destination");
					project.append("ap_days", "$_id.ap_days");
					project.append("website", "$_id.website");
					project.append("date", "$_id.date");
					
					return new BasicDBObject("$project", project);
				}
			});
			
			aggregationOperations.add(new AggregationOperation() {
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject sort = new BasicDBObject();
					sort.append("date", 1);
					
					return new BasicDBObject("$sort", sort);
				}
			});
		}
		
		return aggregationOperations;
	}
	
	private List<AggregationOperation> getAggregationOperationGroupDetail(InternetQueryParam param, int groupingType) {
		List<AggregationOperation> aggregationOperations = getAggregationOperation(param);
		
		if(groupingType == 0 || groupingType == 1) {
			aggregationOperations.add(new AggregationOperation() {
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject match = new BasicDBObject();
					match.append("ap_days", param.getApDays());
					
					return new BasicDBObject("$match", match);
				}
			});
			
			if(groupingType == 0) {
				aggregationOperations.add(new AggregationOperation() {
					@Override
					public DBObject toDBObject(AggregationOperationContext context) {
						BasicDBObject sort = new BasicDBObject();
						sort.append("capture_date_time", 1);
						
						return new BasicDBObject("$sort", sort);
					}
				});
			} else if(groupingType == 1) {
				aggregationOperations.add(new AggregationOperation() {
					@Override
					public DBObject toDBObject(AggregationOperationContext context) {
						BasicDBObject sort = new BasicDBObject();
						sort.append("depart_date_time", 1);
						
						return new BasicDBObject("$sort", sort);
					}
				});
			}
		}
		
		return aggregationOperations;
	}
	
	private Date editTimeOfDate(ZonedDateTime zdt, int hours, int minutes, int seconds) {
		ZonedDateTime zdtEdit = ZonedDateTime.ofInstant(zdt.toInstant(), ZoneId.systemDefault());
		Calendar cal = GregorianCalendar.from(zdtEdit);
		cal.set(Calendar.HOUR, hours);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		zdtEdit = ZonedDateTime.ofInstant(cal.getTime().toInstant(), ZoneId.systemDefault());
		
		return new Date(cal.getTimeInMillis());
	}
}
