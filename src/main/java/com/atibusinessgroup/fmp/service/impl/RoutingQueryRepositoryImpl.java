package com.atibusinessgroup.fmp.service.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import com.atibusinessgroup.fmp.service.RoutingQueryService;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.atibusinessgroup.fmp.domain.RoutingQueryDetails;
import com.atibusinessgroup.fmp.domain.RoutingQueryRestriction;
import com.atibusinessgroup.fmp.domain.RoutingQueryTextRestriction;
import com.atibusinessgroup.fmp.domain.RoutingQuery;
import com.atibusinessgroup.fmp.domain.dto.RoutingQueryParam;
import com.atibusinessgroup.fmp.repository.RoutingqueryRepository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing RoutingQuery.
 */
@Service
public class RoutingQueryRepositoryImpl implements RoutingQueryService {

    private final Logger log = LoggerFactory.getLogger(RoutingQueryRepositoryImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;
    
    private final RoutingqueryRepository routingQueryRepository;

    public RoutingQueryRepositoryImpl(RoutingqueryRepository routingQueryRepository) {
        this.routingQueryRepository = routingQueryRepository;
    }

    /**
     * Save a routingQuery.
     *
     * @param routingQuery the entity to save
     * @return the persisted entity
     */
    @Override
    public RoutingQuery save(RoutingQuery routingQuery) {
        log.debug("Request to save RoutingQuery : {}", routingQuery);
        return routingQueryRepository.save(routingQuery);
    }

    /**
     * Get all the routingQuerys.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<RoutingQuery> findAll(Pageable pageable) {
        log.debug("Request to get all RoutingQuerys");
        return routingQueryRepository.findAll(pageable);
    }

    /**
     * Get one routingQuery by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public RoutingQuery findOne(String id) {
        log.debug("Request to get RoutingQuery : {}", id);
        return routingQueryRepository.findOne(id);
    }

    /**
     * Delete the routingQuery by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete RoutingQuery : {}", id);
        routingQueryRepository.delete(id);
    }
    
    @Override
	public Page<RoutingQuery> findCustom(RoutingQueryParam param, Pageable pageable) {
    	Query query = new Query().with(pageable);
    	if (param.getTarNo()!= null && !param.getTarNo().isEmpty()) {
    		query.addCriteria(Criteria.where("tar_no").is(param.getTarNo()));
		}

		if (param.getCarrier() != null && !param.getCarrier().isEmpty()) {
			query.addCriteria(Criteria.where("cxr_cd").is(param.getCarrier()));
		}

		if (param.getRoutingNo() != null && !param.getRoutingNo().isEmpty()) {
			query.addCriteria(Criteria.where("rtg_no").is(param.getRoutingNo()));
		}
		
		if (param.getEffectiveDateFrom() != null && param.getEffectiveDateTo() != null) {
			query.addCriteria(Criteria.where("dates_effective").gte(param.getEffectiveDateFrom()).lte(param.getEffectiveDateTo()));
		} else if(param.getEffectiveDateFrom() != null) {
			query.addCriteria(Criteria.where("dates_effective").gte(param.getEffectiveDateFrom()));
		} else if(param.getEffectiveDateTo() != null) {
			query.addCriteria(Criteria.where("dates_effective").lte(param.getEffectiveDateTo()));
		}
		
		List<RoutingQuery> routingqueries = mongoTemplate.find(query, RoutingQuery.class);
		Page<RoutingQuery> page = PageableExecutionUtils.getPage(
				routingqueries, 
				pageable, 
                () -> mongoTemplate.count(query,  RoutingQuery.class));
		
		return page;
	}

	@Override
	public ArrayList<ArrayList<String>> getRouteDetails(RoutingQuery routingquery) {
		return getDetails(routingquery.getBatchNumber(), routingquery.getLinkNo());
	}
	
	@Override
	public RoutingQuery getFullRouteDetails(RoutingQuery routingquery) {
		int batchNo = routingquery.getBatchNumber();
		String linkNo = routingquery.getLinkNo();
		routingquery.setDetails(getDetails(batchNo, linkNo));
		routingquery.setRestrictions(getRestrictions(batchNo, linkNo));
		routingquery.setTexts(getTextRestrictions(batchNo, linkNo));
		
		return routingquery;
	}
	
	private ArrayList<ArrayList<String>> getDetails(int batchNo, String linkNo) {
		Query query = new Query(Criteria.where("batch_number").is(batchNo));
		query.addCriteria(Criteria.where("link_no").is(linkNo));
		List<RoutingQueryDetails> routingqueries = mongoTemplate.find(query, RoutingQueryDetails.class);
		
		Map<String, Map<String, String>> cityList = new HashMap<String, Map<String, String>>();
		Map<String, String> routeList = new HashMap<String, String>();
		for (RoutingQueryDetails routingQueryDetails : routingqueries) {	
			cityList.put(routingQueryDetails.getCityNo(), new HashMap<String, String>());
			cityList.get(routingQueryDetails.getCityNo()).put("cityName", routingQueryDetails.getCityName());
			cityList.get(routingQueryDetails.getCityNo()).put("cityTag", routingQueryDetails.getCityTag());
			cityList.get(routingQueryDetails.getCityNo()).put("nextCity", routingQueryDetails.getNextCity());
			cityList.get(routingQueryDetails.getCityNo()).put("alternateCity", routingQueryDetails.getAlternateCity());
		}
		Map<String, Map<String, String>> sortCityList = cityList.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		for (Map.Entry<String, Map<String, String>> route : sortCityList.entrySet()) {
			if(route.getValue().get("cityTag").equals("1")) {
				String nextCityNo = route.getValue().get("nextCity");
				String currentCityName = route.getValue().get("cityName");
				if (routeList.containsKey(nextCityNo)) {
					String lastCityData = routeList.get(nextCityNo);
					routeList.put(nextCityNo, lastCityData+"/"+currentCityName);
			    } else {
			    	routeList.put(nextCityNo, currentCityName);
			    }
			}
		}
		
		for (Map.Entry<String, String> route : routeList.entrySet()) {
			String key = route.getKey();
			String value = route.getValue();
			value = value+"-"+getNextRoute(key, cityList);
			routeList.put(key, value);
		}
		
		Map<String, Integer> routeNumber = new HashMap<>();
		int routeDistance = 0;
		for (Map.Entry<String, String> route : routeList.entrySet()) {
			String[] routeParts = route.getValue().split("-");
			int index = 0;
			for (String part : routeParts) {
				if ((routeNumber.containsKey(part) && routeNumber.get(part) < index) || !routeNumber.containsKey(part)) {
					routeNumber.put(part, index);
				}
				index++;
			}
			
			if(routeParts.length > routeDistance) {
				routeDistance = routeParts.length;
			}
		}
		
		ArrayList<ArrayList<String>> routeMaps = new ArrayList<ArrayList<String>>();
		int index = 0;
		for (Map.Entry<String, String> route : routeList.entrySet()) {
			routeMaps.add(new ArrayList<>());
			for (int i = 0; i < routeDistance; i++) {
				routeMaps.get(index).add("---");
			}
			
			String[] routeParts = route.getValue().split("-");
			for (String part : routeParts) {
				routeMaps.get(index).set(routeNumber.get(part), part);
			}
			
			index++;
		}
		
		return routeMaps;
	}
	
	private String getNextRoute(String cityNo, Map<String, Map<String, String>> cityList) {
		String route = cityList.get(cityNo).get("cityName");
		if(!cityList.get(cityNo).get("alternateCity").equals("")) {
			route = route+"/"+getAlternateRoutes(cityList.get(cityNo).get("alternateCity"), cityList);
		}
		
		if(cityList.get(cityNo).get("cityTag").equals("")) {
			route = route+"-"+getNextRoute(cityList.get(cityNo).get("nextCity"), cityList);
		}
		
		return route;
	}
	
	private String getAlternateRoutes(String cityNo, Map<String, Map<String, String>> cityList) {
		String route = "";
		route = cityList.get(cityNo).get("cityName");
		if(!cityList.get(cityNo).get("alternateCity").equals("")) {
			route = route+"/"+getAlternateRoutes(cityList.get(cityNo).get("alternateCity"), cityList);
		}
		
		return route;
	}
	
	private List<RoutingQueryRestriction> getRestrictions(int batchNo, String linkNo) {
		Query query = new Query(Criteria.where("batch_number").is(batchNo));
		query.addCriteria(Criteria.where("link_no").is(linkNo));
		List<RoutingQueryRestriction> routingQueryRestrictions = mongoTemplate.find(query, RoutingQueryRestriction.class);
		
		return routingQueryRestrictions;
	}
	
	private List<RoutingQueryTextRestriction> getTextRestrictions(int batchNo, String linkNo) {
		Query query = new Query(Criteria.where("batch_number").is(batchNo));
		query.addCriteria(Criteria.where("link_no").is(linkNo));
		List<RoutingQueryTextRestriction> routingQueryTextRestrictions = mongoTemplate.find(query, RoutingQueryTextRestriction.class);
		
		return routingQueryTextRestrictions;
	}
	
	@Override
	public Page<RoutingQuery> findCustomJoin(RoutingQueryParam param, Pageable pageable) {
		if((param.getEntryPoint() == null || param.getEntryPoint().isEmpty())
				&& (param.getExitPoint() == null || param.getExitPoint().isEmpty())) 
		{
			return findCustom(param, pageable);
		} else {
			List<AggregationOperation> aggregationOperations = new ArrayList<>();
			
			aggregationOperations.add(new AggregationOperation() {
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject match = new BasicDBObject();
					BasicDBObject and = new BasicDBObject();
					List<BasicDBObject> queries = new ArrayList<>();
					
					if (param.getTarNo() != null && !param.getTarNo().isEmpty()) {
						BasicDBObject linkNo = new BasicDBObject();
						linkNo.append("tar_no", param.getTarNo());
						queries.add(linkNo);
					}

					if (param.getCarrier() != null && !param.getCarrier().isEmpty()) {
						BasicDBObject cxr = new BasicDBObject();
						cxr.append("cxr_cd", param.getCarrier());
						queries.add(cxr);
					}

					if (param.getRoutingNo() != null && !param.getRoutingNo().isEmpty()) {
						BasicDBObject rtg = new BasicDBObject();
						rtg.append("rtg_no", param.getRoutingNo());
						queries.add(rtg);
					}
					
//					if (param.getEffectiveDateFrom() != null && param.getEffectiveDateTo() != null) {
//						BasicDBObject effectiveDate = new BasicDBObject();
//						effectiveDate.append("dates_effective", BasicDBObjectBuilder.start("$gte", param.getEffectiveDateFrom()).add("$lte", param.getEffectiveDateTo()).get());
//					} else if(param.getEffectiveDateFrom() != null) {
//						BasicDBObject effectiveDate = new BasicDBObject();
//						effectiveDate.append("dates_effective", new BasicDBObject("$gte", param.getEffectiveDateFrom()));
//					} else if(param.getEffectiveDateTo() != null) {
//						BasicDBObject effectiveDate = new BasicDBObject();
//						effectiveDate.append("dates_effective", new BasicDBObject("$lte", param.getEffectiveDateTo()));
//					}
					
					if (queries.size() > 0) {
						and.append("$and", queries);
					}
					
					match.append("$match", and);
					
					return match;
				}
			});
			
			aggregationOperations.add(new AggregationOperation() {
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject project = new BasicDBObject();
					BasicDBObject header = new BasicDBObject();
					header.append("header", "$$ROOT");
					project.append("$project", header);
					return project;
				}
			});
			
			aggregationOperations.add(new AggregationOperation() {
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject lookup = new BasicDBObject();
					BasicDBObject query = new BasicDBObject();
					query.append("from", "Full_Map_Routing_Details");
					query.append("let", new BasicDBObject("linkNo", "$header.link_no").append("batchNumber", "$header.batch_number"));
					
					BasicDBObject match = new BasicDBObject();
					BasicDBObject and = new BasicDBObject();
					and.append("$and", Arrays.asList(
							new BasicDBObject("$expr", new BasicDBObject("$eq", Arrays.asList("$batch_number", "$$batchNumber"))),
							new BasicDBObject("$expr", new BasicDBObject("$eq", Arrays.asList("$link_no", "$$linkNo")))
//							new BasicDBObject("$or", Arrays.asList(
//									new BasicDBObject("$and", Arrays.asList(new BasicDBObject("city_1_tag", 1), new BasicDBObject("city_1_name", param.getEntryPoint()))),
//									new BasicDBObject("$and", Arrays.asList(new BasicDBObject("city_1_tag", "X"), new BasicDBObject("city_1_name", param.getExitPoint())))
//								)
//							)
						)
					);
					match.append("$match", and);
					
					BasicDBObject project = new BasicDBObject();
					BasicDBObject cityName = new BasicDBObject();
					cityName.append("city_1_name", 1);
					project.append("$project", cityName);
					
					BasicDBObject group = new BasicDBObject();
					BasicDBObject id = new BasicDBObject();
					id.append("_id", "$city_1_name");
					group.append("$group", id);
					
					query.append("pipeline", Arrays.asList(new BasicDBObject(match), new BasicDBObject(project), new BasicDBObject(group)));
					query.append("as", "details");
					lookup.append("$lookup", query);
					return lookup;
				}
			});
			
			aggregationOperations.add(new AggregationOperation() {
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject project = new BasicDBObject();
					BasicDBObject projectInline = new BasicDBObject();
					projectInline.append("header", "$header");
					projectInline.append("details", "$details");
					
					BasicDBObject detailSize = new BasicDBObject();
					detailSize.append("$size", "$details");
					projectInline.append("details_size", detailSize);
					
					project.append("$project", projectInline);
					return project;
				}
			});
			
			aggregationOperations.add(new AggregationOperation() {
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject match = new BasicDBObject();
					BasicDBObject matchInline = new BasicDBObject();
					BasicDBObject detailSize = new BasicDBObject();
					detailSize.append("$gt", 1);
					matchInline.append("details_size", detailSize);
					
					match.append("$match", matchInline);
					return match;
				}
			});
			
			aggregationOperations.add(new AggregationOperation() {
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject project = new BasicDBObject();
					BasicDBObject projectInline = new BasicDBObject();
					projectInline.append("batch_number", "$header.batch_number");
					projectInline.append("link_no", "$header.link_no");
					projectInline.append("cxr_cd", "$header.cxr_cd");
					projectInline.append("tar_no", "$header.tar_no");
					projectInline.append("rtg_no", "$header.rtg_no");
					projectInline.append("dates_effective", "$header.dates_effective");
					projectInline.append("dates_discontinue", "$header.dates_discontinue");
					projectInline.append("drv", "$header.drv");
					projectInline.append("cp", "$header.cp");
					projectInline.append("di", "$header.di");
					projectInline.append("int_pt", "$header.int_pt");
					projectInline.append("unt_pt", "$header.unt_pt");
					
					project.append("$project", projectInline);
					return project;
				}
			});
			
			Aggregation aggregation = newAggregation(aggregationOperations);
			
			SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
			aggregationOperations.add(skip);
			
			LimitOperation limit = new LimitOperation(pageable.getPageSize());
			aggregationOperations.add(limit);
		
			Aggregation aggregationPagination = newAggregation(aggregationOperations);
			
			log.debug("aggregation "+aggregation);
			log.debug("ambil data mulai");
			List<RoutingQuery> result = mongoTemplate.aggregate(aggregationPagination, "Full_Map_Routing_Header", RoutingQuery.class).getMappedResults();
			log.debug("routingqueries "+result);
			log.debug("ambil data selesai");
			
			return new PageImpl<>(result, pageable, mongoTemplate.aggregate(aggregation, "Full_Map_Routing_Header", RoutingQuery.class).getMappedResults().size());
		}
	}
}
