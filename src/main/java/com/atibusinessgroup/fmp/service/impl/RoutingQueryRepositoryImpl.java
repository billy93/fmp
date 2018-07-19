package com.atibusinessgroup.fmp.service.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import com.atibusinessgroup.fmp.service.RoutingQueryService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.atibusinessgroup.fmp.domain.RoutingQueryDetails;
import com.atibusinessgroup.fmp.domain.RoutingQueryRestriction;
import com.atibusinessgroup.fmp.domain.RoutingQueryTextRestriction;
import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.RoutingQuery;
import com.atibusinessgroup.fmp.domain.dto.RouteMapView;
import com.atibusinessgroup.fmp.domain.dto.RoutingQueryParam;
import com.atibusinessgroup.fmp.repository.RoutingqueryRepository;

import java.io.IOException;
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
    
    public class RouteMap {
    	private String routeName;
    	private String routeCode;
    	private String routeTag;
    	private String nextRouteCode;
    	private String altRouteCode;
    	private Map<String, RouteMap> nextRouteCodeData = new HashMap<>();
    	
    	private String exitRoute;
    	
		public String getRouteName() {
			return routeName;
		}
		public void setRouteName(String routeName) {
			this.routeName = routeName;
		}
		public String getRouteCode() {
			return routeCode;
		}
		public void setRouteCode(String routeCode) {
			this.routeCode = routeCode;
		}
		public String getRouteTag() {
			return routeTag;
		}
		public void setRouteTag(String routeTag) {
			this.routeTag = routeTag;
		}
		public String getNextRouteCode() {
			return nextRouteCode;
		}
		public void setNextRouteCode(String nextRouteCode) {
			this.nextRouteCode = nextRouteCode;
		}
		public String getAltRouteCode() {
			return altRouteCode;
		}
		public void setAltRouteCode(String altRouteCode) {
			this.altRouteCode = altRouteCode;
		}
		public List<RouteMap> getNextRouteCodeData() {
//			return nextRouteCodeData; 
			return new ArrayList<RouteMap>(nextRouteCodeData.values());
		}
		public void setNextRouteCodeData(Map<String, RouteMap> nextRouteCodeData) {
			this.nextRouteCodeData = nextRouteCodeData;
		}
		public String getExitRoute() {
			return exitRoute;
		}
		public void setExitRoute(String exitRoute) {
			this.exitRoute = exitRoute;
		}
		
		@Override
		public String toString() {
			String routeNameJson = "";
			String nextRouteDataJson = "\"child\":"+getNextRouteCodeData().toString();
			
			if(getRouteTag().equals("X")) {
				routeNameJson = "\"name\":\""+exitRoute+"\"";
			} else {
				routeNameJson = "\"name\":\""+routeName+"\"";
			}
			return "{"+routeNameJson+","+nextRouteDataJson+"}";
		} 
    }

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
	public String[][] getRouteDetails(RoutingQuery routingquery) {
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
	
	private String[][] getDetails(int batchNo, String linkNo) {
		Query query = new Query(Criteria.where("batch_number").is(batchNo));
		query.addCriteria(Criteria.where("link_no").is(linkNo));
		List<RoutingQueryDetails> routingqueries = mongoTemplate.find(query, RoutingQueryDetails.class);
		
		Map<String, RouteMap> cityList = new HashMap<String, RouteMap>();
		Map<String, String> routeList = new HashMap<String, String>();
		for (RoutingQueryDetails routingQueryDetails : routingqueries) {	
			cityList.put(routingQueryDetails.getCityNo(), new RouteMap());
			cityList.get(routingQueryDetails.getCityNo()).setRouteName(routingQueryDetails.getCityName());
			cityList.get(routingQueryDetails.getCityNo()).setRouteCode(routingQueryDetails.getCityNo());
			cityList.get(routingQueryDetails.getCityNo()).setRouteTag(routingQueryDetails.getCityTag());
			cityList.get(routingQueryDetails.getCityNo()).setNextRouteCode(routingQueryDetails.getNextCity());
			cityList.get(routingQueryDetails.getCityNo()).setAltRouteCode(routingQueryDetails.getAlternateCity());
		}
		Map<String, RouteMap> sortCityList = cityList.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		for (Map.Entry<String, RouteMap> route : sortCityList.entrySet()) {
			if(route.getValue().getRouteTag().equals("1")) {
				String nextCityNo = route.getValue().getNextRouteCode();
				String currentCityName = route.getValue().getRouteName();
				if (routeList.containsKey(nextCityNo)) {
					String lastCityData = routeList.get(nextCityNo);
					routeList.put(nextCityNo, lastCityData+"/"+currentCityName);
			    } else {
			    	routeList.put(nextCityNo, currentCityName);
			    } 
			}
		}
		
		List<RouteMap> routeMapList = new ArrayList<>();
		for (Map.Entry<String, String> route : routeList.entrySet()) {
			RouteMap routeMap = new RouteMap();
			routeMap.setRouteName(route.getValue());
			routeMap.setNextRouteCode(route.getKey());
			routeMap.setRouteTag("1");
			routeMap.setNextRouteCodeData(getViaRoutes(routeMap, cityList));
			routeMapList.add(routeMap);
		}
//		log.debug("routeMapList "+routeMapList);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, RouteMapView> routeViewList = new HashMap<>();
		int index = 0;
		for (RouteMap routeMap : routeMapList) {
			try {
				routeViewList.put("parent-"+index, mapper.readValue(routeMap.toString(), RouteMapView.class));
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			index++;
		}
//		log.debug("routeViewList 1 "+routeViewList);
		
		Map<String, RouteMapView> routeViewListNew = routeViewList.entrySet().stream().sorted(Map.Entry.comparingByKey()).
						collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		routeViewList = routeViewListNew;
//		routeViewList = calculateRowSpan(routeViewList); 
		routeViewList = getRouteMapView(routeViewList); 
//		log.debug("routeViewList 2 "+routeViewList);
		
		List<String> routeViewString = new ArrayList<>();
		for (Map.Entry<String, RouteMapView> routeMapViewData : routeViewList.entrySet()) {
			routeViewString.add(routeMapViewData.getValue().getName());
		}
//		log.debug("routeViewString "+routeViewString);
		
		int rowCount = routeViewList.size();
		int columnCount = 0;
		for (Map.Entry<String, RouteMapView> route : routeViewList.entrySet()) {
			String[] routeParts = route.getValue().getName().split("-");
			if(columnCount < routeParts.length)
				columnCount = routeParts.length;
		}
//		log.debug("rowCount "+rowCount);
//		log.debug("columnCount "+columnCount);
		
		index = 0;
		String routeMapTable[][] = new String[rowCount][columnCount];
		for (Map.Entry<String, RouteMapView> route : routeViewList.entrySet()) {
			String[] routeParts = route.getValue().getName().split("-");
			int routePartIndex = 0;
			for (String routePart : routeParts) {
				routeMapTable[index][routePartIndex] = routePart;
				routePartIndex++;
			}
			index++;
		}
//		log.debug("routeMapTable "+routeMapTable);
		
//		List<String> routingMapsTable = new ArrayList<>(); 
//		for (int i = 0; i < routeMapTable.length; i++) {
//			if(i == 0) {
//				for (String str : routeMapTable[i]) {
//					if(str != null) {
//						routingMapsTable.add(str);
//					}
//				}
//				log.debug("Mulai "+routingMapsTable);
//			} else {
//				log.debug("=========================================================================================================");
//				log.debug("awal data");
//				int columnIndex = 0;
//				String routeStr = routeMapTable[i][0];
//				for (int j = 0; j < routeMapTable[i].length; j++) {
//					String route = routeMapTable[i][j];
//					if(route != null) {
//						for (int k = 0; k < routingMapsTable.size(); k++) {
//							log.debug("routeStr "+routeStr);
//							if(isContentRoute(routingMapsTable.get(k), routeMapTable[i][j])) {
//								log.debug("if "+routeMapTable[i][j]+" udah ada dikolom "+k+" baris "+routingMapsTable.get(k).indexOf(routeMapTable[i][j]));
//								columnIndex++;
//								String addedRoute = routeStr.replaceAll("-"+routeMapTable[i][j], "");
//								if(j > 0 && !isContentRoute(routingMapsTable.get(columnIndex-1), addedRoute)) {
//									log.debug(addedRoute+" akan di-add dikolom "+(columnIndex-1));
//									routingMapsTable.set(columnIndex-1, routingMapsTable.get(columnIndex-1)+"="+addedRoute);
//								}
//								j++;
//								routeStr = routeMapTable[i][j];
//								break;
//							} else if(isContentRoute(routingMapsTable.get(k), routeStr)) {
//								log.debug("else if "+routeStr+" udah ada dikolom "+k+" baris "+routingMapsTable.get(k).indexOf(routeStr));
//								columnIndex++;
//								routeStr = routeMapTable[i][j];
//								break;
//							} else {
//								log.debug(routeMapTable[i][j]+" gak ada dikolom "+k);
////								if(!isContentRoute(routeStr, routeMapTable[i][j])) {
//								if(routeStr.indexOf(routeMapTable[i][j]) < 0) {	
//									log.debug(routeMapTable[i][j]+" belum ada di "+routeStr);
//									routeStr = routeStr+"-"+routeMapTable[i][j];
//								}
//							}
//						}
//					}
//				}
//				log.debug("akhir data");
//				if(routeStr != null) {
//					boolean flag = false;
//					for (int k = 0; k < routingMapsTable.size(); k++) {
//						if(routingMapsTable.get(k).indexOf(routeStr) >= 0) {
//							flag = true;
//							break;
//						}
//					}
//					
//					if(!flag) {
//						log.debug(routeStr+" akan di-add dikolom "+columnIndex);
//						routingMapsTable.set(columnIndex, routingMapsTable.get(columnIndex)+"="+routeStr);
//					}
//				}
//				log.debug("********************************************************************************************************");
//			}
//			log.debug("Update tesMaps "+routingMapsTable);
//		}
//		log.debug("tesMaps "+routingMapsTable);
		
//		return new ArrayList<RouteMapView>(routeViewList.values());
		return routeMapTable;
	}
	
	private boolean isContentRoute(String masterRoute, String contentRoute) {
		boolean isContentRoute = false;
		String[] masterRouteArr = masterRoute.split("=");
		for (String route : masterRouteArr) {
			if(route.indexOf(contentRoute) >= 0) {
				if(route.length() == contentRoute.length()) {
					isContentRoute = true; 
					break;
				}
			}
		}
		
		return isContentRoute;
	}
	
	private Map<String, RouteMapView> calculateRowSpan(Map<String, RouteMapView> routeViewList) {
		for (Map.Entry<String, RouteMapView> routeMapViewData : routeViewList.entrySet()) {
			String currentKey = routeMapViewData.getKey();
			RouteMapView currentRouteMap = routeMapViewData.getValue();
			int rowSpan = 1;
			int index = 0;
			for (RouteMapView child : currentRouteMap.getChild()) {
				child = getChildRowSpan(child);
				if(child.getRowSpan() > 1 || child.getChild().size() > 1) {
					currentRouteMap.getChild().set(index, child);
					rowSpan = rowSpan + child.getRowSpan();
				}
				index++;
			}
			if(rowSpan < currentRouteMap.getChild().size()) {
				currentRouteMap.setRowSpan(currentRouteMap.getChild().size());
			} else {
				currentRouteMap.setRowSpan(rowSpan);
			}
			
			routeViewList.put(currentKey, currentRouteMap);
		}
		
		return routeViewList;
	}
	
	private RouteMapView getChildRowSpan(RouteMapView routeMapView) {
		int rowSpan = 1;
		int index = 0;
		for (RouteMapView child : routeMapView.getChild()) {
			child = getChildRowSpan(child);
			if(child.getRowSpan() > 1 || child.getChild().size() > 1) { 
				routeMapView.getChild().set(index, child);
				rowSpan = rowSpan + child.getRowSpan();
			}
			index++;
		}
		routeMapView.setRowSpan(rowSpan);
		return routeMapView;
	}
	
	private Map<String, RouteMapView> getRouteMapView(Map<String, RouteMapView> routeViewList) {
		String currentKey = "";
		boolean flag = false;
		boolean isDeleted = false;
		for (Map.Entry<String, RouteMapView> routeMapViewData : routeViewList.entrySet()) {
			currentKey = routeMapViewData.getKey();
			RouteMapView routeMapView = routeMapViewData.getValue();
			String oldName = routeMapView.getName();
			if(routeMapView.getChild().size() > 1) {
				int childIndex = 0;
				for (RouteMapView childRouteMapView : routeMapView.getChild()) {
					childRouteMapView.setName(oldName+"-"+childRouteMapView.getName());
					routeViewList.put(currentKey+"-"+childIndex, childRouteMapView);
					childIndex++;
				}
				flag = true;
				isDeleted = true;
				break;
			} else if(routeMapView.getChild().size() == 1){
				RouteMapView child = routeMapView.getChild().get(0);
				child.setName(oldName+"-"+child.getName());
				routeViewList.put(currentKey, child);
				if(child.getChild().size() > 0) {
					flag = true;
					break;
				}
			}
		}
		
		if(flag) {
			if(isDeleted) routeViewList.remove(currentKey);
			Map<String, RouteMapView> routeViewListNew = routeViewList.entrySet().stream().sorted(Map.Entry.comparingByKey()).
					collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			routeViewList = routeViewListNew;
			routeViewList = getRouteMapView(routeViewList);
		} 
		
		return routeViewList;
	}
	
	private Map<String, RouteMap> getViaRoutes(RouteMap parent, Map<String, RouteMap> cityList) {
		Map<String, RouteMap> route = new HashMap<>();
		
		String nextCityCode = parent.getNextRouteCode();
		RouteMap nextRouteMap = cityList.get(nextCityCode);
		if(nextRouteMap.getRouteTag().equals("")) {
			String innerNextCityCode = nextRouteMap.getNextRouteCode();
			if(!innerNextCityCode.equals("")) {
				nextRouteMap.setNextRouteCodeData(getViaRoutes(nextRouteMap, cityList));
			}
			route.put(nextCityCode, nextRouteMap);
			
			String altCityCode = nextRouteMap.getAltRouteCode();
			if(!altCityCode.equals("")) {
				boolean flag = true;
				RouteMap altRouteMap = new RouteMap();
				while (flag) {
					altRouteMap = cityList.get(altCityCode);
					if(nextRouteMap.getNextRouteCode().equals(altRouteMap.getNextRouteCode())) {
						String oldRouteMapName = route.get(nextRouteMap.getRouteCode()).getRouteName();
						route.get(nextRouteMap.getRouteCode()).setRouteName(oldRouteMapName+"/"+altRouteMap.getRouteName());
						if(!altRouteMap.getAltRouteCode().equals("")) {
							altCityCode = altRouteMap.getAltRouteCode();
						} else {
							flag = false;
						}
					} else {
						altRouteMap.setNextRouteCodeData(getViaRoutes(altRouteMap, cityList));
						route.put(altCityCode, altRouteMap);
						flag = false;
					}
				}
			}
		} else {
			nextRouteMap.setExitRoute(getDestinyRoutes(nextRouteMap, cityList));
			route.put(nextCityCode, nextRouteMap);
		}
		
		return route;
	}
	
	private String getDestinyRoutes(RouteMap parent, Map<String, RouteMap> cityList) {
		String cityNo = parent.getRouteCode();
		String destinyRoutes = parent.getRouteName();
		if(!cityList.get(cityNo).getAltRouteCode().equals("")) {
			destinyRoutes = destinyRoutes+"/"+getAlternateRoutes(cityList.get(cityNo).getAltRouteCode(), cityList);
		}
		
		return destinyRoutes;
	}
	
	private String getAlternateRoutes(String cityNo, Map<String, RouteMap> cityList) {
		String route = "";
		route = cityList.get(cityNo).getRouteName();
		if(!cityList.get(cityNo).getAltRouteCode().equals("")) {
			route = route+"/"+getAlternateRoutes(cityList.get(cityNo).getAltRouteCode(), cityList);
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
		List<AggregationOperation> aggregationOperations = getAggregationOperation(param);
		Aggregation aggregation = newAggregation(aggregationOperations);
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);
		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);
		Aggregation aggregationPagination = newAggregation(aggregationOperations);
		
//		log.debug("ambil data mulai");
//		log.debug(pageable.toString());
//		log.debug("aggregationPagination "+aggregationPagination);
		log.debug("aggregation "+aggregation);
		List<RoutingQuery> result = mongoTemplate.aggregate(aggregationPagination, CollectionName.ATPCO_ROUTING_HEADER, RoutingQuery.class).getMappedResults();
		long allResultCount = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_ROUTING_HEADER, RoutingQuery.class).getMappedResults().size();
		
//		log.debug("routingqueries "+result);
//		log.debug("allResultCount "+allResultCount);
//		log.debug("ambil data selesai");
		
		return new PageImpl<>(result, pageable, allResultCount);
	}

	@Override
	public RoutingQuery findOneCustom(RoutingQueryParam param) {
		List<AggregationOperation> aggregationOperations = getAggregationOperation(param);
		Aggregation aggregations = newAggregation(aggregationOperations);
		List<RoutingQuery> result = mongoTemplate.aggregate(aggregations, CollectionName.ATPCO_ROUTING_HEADER, RoutingQuery.class).getMappedResults();
		
		if(result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	private List<AggregationOperation> getAggregationOperation(RoutingQueryParam param) {
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				
				if (param.getTarNo() != null && !param.getTarNo().isEmpty()) {
					match.append("tar_no", param.getTarNo());
				}
				if (param.getCarrier() != null && !param.getCarrier().isEmpty()) {
					match.append("cxr_cd", new BasicDBObject("$in", Arrays.asList(param.getCarrier().split(","))));
				}
				if (param.getRoutingNo() != null && !param.getRoutingNo().isEmpty()) {
					match.append("rtg_no", param.getRoutingNo());
				}
				if (param.getEffectiveDateFrom() != null && param.getEffectiveDateTo() != null) {
					match.append("dates_effective", new BasicDBObject("$lte", param.getEffectiveDateFrom()));
					match.append("$or", Arrays.asList(new BasicDBObject("dates_discontinue", new BasicDBObject("$gte", param.getEffectiveDateTo())), new BasicDBObject("dates_discontinue", "indef")));
				} else if(param.getEffectiveDateFrom() != null) {
					match.append("dates_effective", new BasicDBObject("$lte", param.getEffectiveDateFrom()));
					match.append("dates_discontinue", "indef");
				} else if(param.getEffectiveDateTo() != null) {
					match.append("$or", Arrays.asList(new BasicDBObject("dates_discontinue", new BasicDBObject("$gte", param.getEffectiveDateTo())), new BasicDBObject("dates_discontinue", "indef")));
				}
				
				return new BasicDBObject("$match", match);
			}
		});
		
		if((param.getEntryPoint() != null && !param.getEntryPoint().isEmpty()) || (param.getExitPoint() != null && !param.getExitPoint().isEmpty())) {
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
					
					List<BasicDBObject> orPointList = new ArrayList<>();
					if((param.getEntryPoint() != null && !param.getEntryPoint().isEmpty()) && (param.getExitPoint() != null && !param.getExitPoint().isEmpty())) {
						orPointList.add(new BasicDBObject("$and", Arrays.asList(new BasicDBObject("city_1_tag", "1"), new BasicDBObject("city_1_name", param.getEntryPoint()))));
						orPointList.add(new BasicDBObject("$and", Arrays.asList(new BasicDBObject("city_1_tag", "X"), new BasicDBObject("city_1_name", param.getExitPoint()))));
					} else if(param.getEntryPoint() != null && !param.getEntryPoint().isEmpty()) {
						orPointList.add(new BasicDBObject("$and", Arrays.asList(new BasicDBObject("city_1_tag", "1"), new BasicDBObject("city_1_name", param.getEntryPoint()))));
					} else if(param.getExitPoint() != null && !param.getExitPoint().isEmpty()) {
						orPointList.add(new BasicDBObject("$and", Arrays.asList(new BasicDBObject("city_1_tag", "X"), new BasicDBObject("city_1_name", param.getExitPoint()))));
					}
					
					BasicDBObject match = new BasicDBObject();
					BasicDBObject and = new BasicDBObject();
					and.append("$and", Arrays.asList(
							new BasicDBObject("$expr", new BasicDBObject("$eq", Arrays.asList("$batch_number", "$$batchNumber"))),
							new BasicDBObject("$expr", new BasicDBObject("$eq", Arrays.asList("$link_no", "$$linkNo"))),
							new BasicDBObject("$or", orPointList)
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
					if((param.getEntryPoint() != null && !param.getEntryPoint().isEmpty()) && (param.getExitPoint() != null && !param.getExitPoint().isEmpty())) {
						detailSize.append("$gt", 1);
					} else {
						detailSize.append("$gte", 1);
					}
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
		}
		
		return aggregationOperations;
	}
}
