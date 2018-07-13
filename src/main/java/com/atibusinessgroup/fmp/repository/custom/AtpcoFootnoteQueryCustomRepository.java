package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFootnoteRecord2;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteQueryDetails;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteQueryGroup;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.domain.dto.FootnoteQueryParam;
import com.atibusinessgroup.fmp.service.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoFootnoteQueryCustomRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	private FootnoteQueryParam ftntParam = null;

	public Page<AtpcoFootnoteQueryGroup> groupingFootnoteQueryAll(FootnoteQueryParam param, Pageable pageable) {
		
		Date today = getCalendarDate(0);
		Date twoYearsBefore = getCalendarDate(1);
		
		ftntParam = param;
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();

				if (param.isIncludeDiscDate()) {
					query.append("$or",
							Arrays.asList(
									new BasicDBObject("dates_disc", new BasicDBObject("$gte", twoYearsBefore)),
									new BasicDBObject("dates_disc", "indef")));

				} else {
					query.append("$or", Arrays.asList(
							new BasicDBObject("dates_disc", new BasicDBObject("$gte", today)),
							new BasicDBObject("dates_disc", "indef")
					));
				}

				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					query.append("cxr_code", param.getCxr());
				} else {
					query.append("cxr_code", new BasicDBObject("$exists", "true"));
				}

				if (param.getTarNo() != null && !param.getTarNo().isEmpty()) {
					query.append("fare_tar_no", param.getTarNo());
				} else {
					query.append("fare_tar_no", new BasicDBObject("$exists", "true"));
				}

				if (param.getFtnt() != null && !param.getFtnt().isEmpty()) {
					query.append("ftnt", param.getFtnt());
				} else {
					query.append("ftnt", new BasicDBObject("$exists", "true"));
				}
				

				if (param.getCatNo() != null && !param.getCatNo().isEmpty()) {
					if (param.getCatNo().equalsIgnoreCase("comb")) {

						query.append("cat_no", new BasicDBObject("$in", Arrays.asList("014", "015")));

					} else {
						query.append("cat_no", param.getCatNo());
					}

				} else {
					if ((param.getSaleDateFrom() != null || param.getSaleDateTo() != null)
						&& (param.getTravelDateFrom() == null || param.getTravelDateTo() == null)
						&& (param.getCompletedDateFrom() == null || param.getTravelOpt() == null)) {

						query.append("cat_no", "015");

					} else if ((param.getSaleDateFrom() == null || param.getSaleDateTo() == null)
							&& (param.getTravelDateFrom() == null || param.getTravelDateTo() == null)
							&& (param.getCompletedDateFrom() != null || param.getTravelOpt() != null)) {

						query.append("cat_no", "014");

					} else if (
						((param.getSaleDateFrom() != null || param.getSaleDateTo() != null) && (param.getTravelDateFrom() != null || param.getTravelDateTo() != null) && (param.getCompletedDateFrom() != null || param.getTravelOpt() != null)) 
						|| ((param.getSaleDateFrom() != null || param.getSaleDateTo() != null) && (param.getTravelDateFrom() != null || param.getTravelDateTo() != null))
						|| ((param.getTravelDateFrom() != null || param.getTravelDateTo() != null) && (param.getCompletedDateFrom() != null || param.getTravelOpt() != null))
						|| ((param.getSaleDateFrom() != null || param.getSaleDateTo() != null) && (param.getCompletedDateFrom() != null || param.getTravelOpt() != null)))
					{

						query.append("cat_no", new BasicDBObject("$in", Arrays.asList("014", "015")));

					} else {

						query.append("cat_no", new BasicDBObject("$exists", "true"));

					}

				}

				match.append("$match", query);

				return match;
			}
		});
		
		if ((param.getSaleDateFrom() != null || param.getSaleDateTo() != null)
				&& param.getTravelDateFrom() == null && param.getTravelDateTo() == null
				&& param.getCompletedDateFrom() == null && param.getTravelOpt() == null) {
			
			//sales dates cat 015
			
			aggregationOperations.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject lookup = new BasicDBObject();
					BasicDBObject query = new BasicDBObject();
					
					query.append("from", CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_015);
					query.append("localField", "data_table.tbl_no");
					query.append("foreignField", "tbl_no");
					query.append("as", "cat015");
					lookup.append("$lookup", query);
					
					return lookup;
				}
			
			});
			
			aggregationOperations.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject query = new BasicDBObject();

					query.append("$unwind", "$cat015");
					
					return query;
				}
			}); 
			
			aggregationOperations.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					

					List<BasicDBObject> matchFilter = new ArrayList<>();
					BasicDBObject and = new BasicDBObject();
					BasicDBObject match = new BasicDBObject();
					
					
					matchFilter.add(new BasicDBObject("cat015", new BasicDBObject("$ne", Arrays.asList())));
					
						if(param.getSaleDateFrom() != null) {
							
							if(param.getSaleDateType() != null) {
								
								if(param.getSaleDateType().equalsIgnoreCase("1")) {
									
									matchFilter.add(new BasicDBObject("$or", Arrays.asList(
											new BasicDBObject("cat015.sales_dates_latest_tktg", "indef"),
											new BasicDBObject("cat015.sales_dates_latest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getSaleDateFrom())))
									)));
									
								} else {
									
									
									matchFilter.add(new BasicDBObject("cat015.sales_dates_earliest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getSaleDateFrom()))));
									matchFilter.add(new BasicDBObject("cat015.sales_dates_earliest_tktg", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(param.getSaleDateFrom(), 1, 0, 0))));
									
								}
							} else {
								matchFilter.add(new BasicDBObject("$or", Arrays.asList(
										new BasicDBObject("cat015.sales_dates_latest_tktg", "indef"),
										new BasicDBObject("cat015.sales_dates_latest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getSaleDateFrom())))
								)));
							}
							
							
						} 
						
						if(param.getSaleDateTo() != null) {
							
							if(param.getSaleDateType() != null) {
								
								if(param.getSaleDateType().equalsIgnoreCase("1")) {
									
									matchFilter.add(new BasicDBObject("$or", Arrays.asList(
											new BasicDBObject("cat015.sales_dates_earliest_tktg", "indef"),
											new BasicDBObject("cat015.sales_dates_earliest_tktg", new BasicDBObject("$lte", DateUtil.convertObjectToDate(param.getSaleDateTo())))
											)));
											
									
								} else {
									
									matchFilter.add(new BasicDBObject("cat015.sales_dates_latest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getSaleDateTo()))));
									matchFilter.add(new BasicDBObject("cat015.sales_dates_latest_tktg", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(param.getSaleDateTo(), 1, 0, 0))));
										
									
								}
								
							} else {
								matchFilter.add(new BasicDBObject("$or", Arrays.asList(
										new BasicDBObject("cat015.sales_dates_earliest_tktg", "indef"),
										new BasicDBObject("cat015.sales_dates_earliest_tktg", new BasicDBObject("$lte", DateUtil.convertObjectToDate(param.getSaleDateTo())))
										)));
							}
							
						} 
					
					and.append("$and", matchFilter);
					match.append("$match", and);

					return match;
				}
			});
			

			aggregationOperations.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject group = new BasicDBObject("$group",new BasicDBObject("_id", 
							new BasicDBObject("cxr", "$cxr_code")
							.append("tarNo", "$tar_no")
							.append("ftnt","$ftnt")
							));
					
					return group;
				}
			});
			
		} else if (param.getSaleDateFrom() == null && param.getSaleDateTo() == null
				&& (param.getTravelDateFrom() != null || param.getTravelDateTo() != null || param.getCompletedDateFrom() != null || param.getTravelOpt() != null)) {
			//travel cat 014
			
			
			
			aggregationOperations.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject lookup = new BasicDBObject();
					BasicDBObject query = new BasicDBObject();

					query.append("from", CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_014);
					query.append("localField", "data_table.tbl_no");
					query.append("foreignField", "tbl_no");
					query.append("as", "cat014");
					lookup.append("$lookup", query);
					
					return lookup;
				}
			}); 
			
			aggregationOperations.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject query = new BasicDBObject();

					query.append("$unwind", "$cat014");
					
					return query;
				}
			}); 
			
			aggregationOperations.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {

					List<BasicDBObject> matchFilter = new ArrayList<>();
					BasicDBObject and = new BasicDBObject();
					BasicDBObject match = new BasicDBObject();
					
					matchFilter.add(new BasicDBObject("cat014", new BasicDBObject("$ne", Arrays.asList())));
					
						if(param.getTravelDateFrom() != null) {
							
							if(param.getTravelDateType() != null) {
								
								if(param.getTravelDateType().equalsIgnoreCase("1")) {

									matchFilter.add(new BasicDBObject("$or", Arrays.asList(
											new BasicDBObject("cat014.travel_dates_exp", "indef"),
											new BasicDBObject("cat014.travel_dates_exp", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getTravelDateFrom()))))
									));
									
								} else {
								
									matchFilter.add(new BasicDBObject("cat014.travel_dates_comm", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getTravelDateFrom()))));
									matchFilter.add(new BasicDBObject("cat014.travel_dates_comm", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(param.getTravelDateFrom(), 1, 0, 0))));
								}
								
							} else {
								matchFilter.add(new BasicDBObject("$or", Arrays.asList(
										new BasicDBObject("cat014.travel_dates_exp", "indef"),
										new BasicDBObject("cat014.travel_dates_exp", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getTravelDateFrom()))))
								));
							}
							
							
						
						}
						
						if(param.getTravelDateTo() != null) {
							
							if(param.getTravelDateType() != null) {
								
								if(param.getTravelDateType().equalsIgnoreCase("1")) {

									matchFilter.add(new BasicDBObject("$or", Arrays.asList(
											new BasicDBObject("cat014.travel_dates_comm", "indef"),
											new BasicDBObject("cat014.travel_dates_comm", new BasicDBObject("$lte", DateUtil.convertObjectToDate(param.getTravelDateTo()))))
									));
									
								} else {
								
									matchFilter.add(new BasicDBObject("cat014.travel_dates_exp", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getTravelDateTo()))));
									matchFilter.add(new BasicDBObject("cat014.travel_dates_exp", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(param.getTravelDateTo(), 1, 0, 0))));
								}
								
							} else {
								matchFilter.add(new BasicDBObject("$or", Arrays.asList(
										new BasicDBObject("cat014.travel_dates_comm", "indef"),
										new BasicDBObject("cat014.travel_dates_comm", new BasicDBObject("$lte", DateUtil.convertObjectToDate(param.getTravelDateTo()))))
								));
							}
							
							
						}
						
						if(param.getCompletedDateFrom() != null) {
							
							matchFilter.add(new BasicDBObject("cat014.travel_dates_commence_complete", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getCompletedDateFrom()))));
							matchFilter.add(new BasicDBObject("cat014.travel_dates_commence_complete", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(param.getCompletedDateFrom(), 1, 0, 0))));
							
							
						} 
						
						if( param.getTravelOpt() != null) {
							
							matchFilter.add(new BasicDBObject("cat014.travel_dates_appl", param.getTravelOpt()));
								
						} 
						and.append("$and", matchFilter);
						match.append("$match", and);
					return match;
				}
			});
			

			aggregationOperations.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject group = new BasicDBObject("$group",new BasicDBObject("_id", 
							new BasicDBObject("cxr", "$cxr_code")
							.append("tarNo", "$fare_tar_no")
							.append("ftnt","$ftnt")
							));
					
					return group;
				}
			});
			
		} else if ((param.getSaleDateFrom() != null || param.getSaleDateTo() != null)
				&& (param.getTravelDateFrom() != null || param.getTravelDateTo() != null)
				&& (param.getCompletedDateFrom() != null || param.getTravelOpt() != null)) {
			
				//sales cat 015  & travel dates cat 014
			
				
				aggregationOperations.add(new AggregationOperation() {
					
					@Override
					public DBObject toDBObject(AggregationOperationContext context) {
						BasicDBObject lookup = new BasicDBObject();
						BasicDBObject query = new BasicDBObject();
						
						query.append("from", CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_014);
						query.append("localField", "data_table.tbl_no");
						query.append("foreignField", "tbl_no");
						query.append("as", "cat14");
						lookup.append("$lookup", query);
						
						return lookup;
					}
				});
				
				aggregationOperations.add(new AggregationOperation() {
					
					@Override
					public DBObject toDBObject(AggregationOperationContext context) {
						BasicDBObject lookup = new BasicDBObject();
						BasicDBObject query = new BasicDBObject();
						
						query.append("from", CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_015);
						query.append("localField", "data_table.tbl_no");
						query.append("foreignField", "tbl_no");
						query.append("as", "cat15");
						lookup.append("$lookup", query);
						
						return lookup;
					}
				});
				
				BasicDBObject matchQuery = new BasicDBObject();
				
				matchQuery.append("$or", Arrays.asList(
						new BasicDBObject("cat14", new BasicDBObject("$ne", Arrays.asList())), 
						new BasicDBObject("cat15", new BasicDBObject("$ne", Arrays.asList()))
						));
			
				if(param.getSaleDateFrom() != null) {
					
					if(param.getSaleDateType() != null) {
						
						if(param.getSaleDateType().equalsIgnoreCase("1")) {
							
							matchQuery.append("$or", Arrays.asList(
									new BasicDBObject("cat15.sales_dates_latest_tktg", "indef"),
									new BasicDBObject("cat15.sales_dates_latest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getSaleDateFrom())))
									));
							
						} else {
							matchQuery.append("$or", Arrays.asList(
									new BasicDBObject("cat15.sales_dates_earliest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getSaleDateFrom()))),
									new BasicDBObject("cat15.sales_dates_earliest_tktg", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(param.getSaleDateFrom(), 1, 0, 0)))
									));
						}
						
					} else {
						matchQuery.append("$or", Arrays.asList(
								new BasicDBObject("cat15.sales_dates_latest_tktg", "indef"),
								new BasicDBObject("cat15.sales_dates_latest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getSaleDateFrom())))
								));
					}
				} 
				
				if(param.getSaleDateTo() != null) {
					
					if(param.getSaleDateType() != null) {
						
						if(param.getSaleDateType().equalsIgnoreCase("1")) {
							
							matchQuery.append("$or", Arrays.asList(
									new BasicDBObject("cat15.sales_dates_earliest_tktg", "indef"),
									new BasicDBObject("cat15.sales_dates_earliest_tktg", new BasicDBObject("$lte", DateUtil.convertObjectToDate(param.getSaleDateTo())))
									));
							
						} else {
							matchQuery.append("$or", Arrays.asList(
									new BasicDBObject("cat15.sales_dates_latest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getSaleDateTo()))),
									new BasicDBObject("cat15.sales_dates_latest_tktg", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(param.getSaleDateTo(), 1, 0, 0)))
									));
						}
						
					} else {
						matchQuery.append("$or", Arrays.asList(
								new BasicDBObject("cat15.sales_dates_earliest_tktg", "indef"),
								new BasicDBObject("cat15.sales_dates_earliest_tktg", new BasicDBObject("$lte", DateUtil.convertObjectToDate(param.getSaleDateTo())))
								));
					}
				} 
				
				if(param.getTravelDateFrom() != null) {
					
					if(param.getTravelDateType() != null) {
						
						if(param.getTravelDateType().equalsIgnoreCase("1")) {
							
							matchQuery.append("$or", Arrays.asList(
									new BasicDBObject("cat14.travel_dates_exp", "indef"),
									new BasicDBObject("cat14.travel_dates_exp", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getTravelDateFrom())))
									));
							
						} else {
							matchQuery.append("$or", Arrays.asList(
									new BasicDBObject("cat14.travel_dates_comm", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getTravelDateFrom()))),
									new BasicDBObject("cat14.travel_dates_comm", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(param.getTravelDateFrom(), 1, 0, 0)))
									));
						}
						
					} else {
						matchQuery.append("$or", Arrays.asList(
								new BasicDBObject("cat14.travel_dates_exp", "indef"),
								new BasicDBObject("cat14.travel_dates_exp", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getTravelDateFrom())))
								));
					}
				} 
				
				if(param.getTravelDateTo() != null) {
					
					if(param.getTravelDateType() != null) {
						
						if(param.getTravelDateType().equalsIgnoreCase("1")) {
							
							matchQuery.append("$or", Arrays.asList(
									new BasicDBObject("cat14.travel_dates_comm", "indef"),
									new BasicDBObject("cat14.travel_dates_comm", new BasicDBObject("$lte", DateUtil.convertObjectToDate(param.getTravelDateTo())))
									));
							
						} else {
							matchQuery.append("$or", Arrays.asList(
									new BasicDBObject("cat14.travel_dates_exp", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getTravelDateTo()))),
									new BasicDBObject("cat14.travel_dates_exp", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(param.getTravelDateTo(), 1, 0, 0)))
									));
						}
						
					} else {
						
						matchQuery.append("$or", Arrays.asList(
								new BasicDBObject("cat14.travel_dates_comm", "indef"),
								new BasicDBObject("cat14.travel_dates_comm", new BasicDBObject("$lte", DateUtil.convertObjectToDate(param.getTravelDateTo())))
								));
						
					}
					
				}
				
				if(param.getCompletedDateFrom() != null) {
					
					matchQuery.append("$or", Arrays.asList(
						new BasicDBObject("cat14.travel_dates_commence_complete", new BasicDBObject("$gte", DateUtil.convertObjectToDate(param.getCompletedDateFrom()))),
						new BasicDBObject("cat14.travel_dates_commence_complete", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(param.getCompletedDateFrom(), 1, 0, 0)))
					));
					
				} 
				
				if(param.getTravelOpt() != null) {
					
					matchQuery.append("cat14.travel_dates_appl", param.getTravelOpt());
					
				}
				
				aggregationOperations.add(new AggregationOperation() {
					
					@Override
					public DBObject toDBObject(AggregationOperationContext context) {
						BasicDBObject match = new BasicDBObject();
						
						match.append("$match", matchQuery);
						return match;
					}
				});
				
				aggregationOperations.add(new AggregationOperation() {
					
					@Override
					public DBObject toDBObject(AggregationOperationContext context) {
						BasicDBObject group = new BasicDBObject("$group",new BasicDBObject("_id", 
								new BasicDBObject("cxr", "$cxr_code")
								.append("tarNo", "$fare_tar_no")
								.append("ftnt","$ftnt")
								));
						
						return group;
					}
				});

		} 
		else  {
			//all 
			
			aggregationOperations.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject group = new BasicDBObject("$group",new BasicDBObject("_id", 
							new BasicDBObject("cxr", "$cxr_code")
							.append("tarNo", "$fare_tar_no")
							.append("ftnt","$ftnt")
							));
					
					return group;
				}
			});
			
		}
		
		
		aggregationOperations.add(new AggregationOperation() {
		
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject("$project", new BasicDBObject("tmp", new BasicDBObject("result", "$_id")));
				return project;
			}
		});

		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject("$group",
						new BasicDBObject("_id", null)  
						.append("total", new BasicDBObject("$sum", 1))
						.append("data", new BasicDBObject("$addToSet", "$tmp.result")));
				return group;
			}
		});

		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject("$project", 
						new BasicDBObject("_id", null)
						.append("total", "$total")
						.append("data", new BasicDBObject("$slice", Arrays.asList("$data", pageable.getPageNumber() * pageable.getPageSize(), pageable.getPageSize())))
						);
				return project;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				return new BasicDBObject("$unwind", "$data");
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				
				query.append("from", CollectionName.ATPCO_MASTER_TARIFF);
				query.append("let", new BasicDBObject("tar_no", "$data.tarNo"));
				query.append("pipeline", Arrays.asList(
						new BasicDBObject("$match", 
								new BasicDBObject("$expr", 
										new BasicDBObject("$and", Arrays.asList(
												new BasicDBObject("$eq", Arrays.asList("$tar_no","$$tar_no")),
												new BasicDBObject("$eq", Arrays.asList("$type","FARE RULE")),
												new BasicDBObject("$eq", Arrays.asList("$pp","public"))
												))
										)
								)
						));
				query.append("as", "m_tariff");
				lookup.append("$lookup", query);
				return lookup;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				return new BasicDBObject("$unwind", new BasicDBObject("path",  "$m_tariff").append("preserveNullAndEmptyArrays", true));
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject("$project", new BasicDBObject("_id", 0)
						.append("total", "$total")
						.append("cxr", "$data.cxr")
						.append("tarNo", "$data.tarNo")
						.append("ftnt", "$data.ftnt")
						.append("tarCd", "$m_tariff.tar_cd"));
				return project;
			}
		});
		
		Aggregation aggregation = newAggregation(aggregationOperations);

		List<AtpcoFootnoteQueryGroup> data = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FOOTNOTE_RECORD_2, AtpcoFootnoteQueryGroup.class).getMappedResults();
		
		int total = 0;
		
		if(data.size() > 0) {
			
			total = data.get(0).getTotal();
		}

		return new PageImpl<>(data, pageable, total);
	}
	
	
	public Page<AtpcoFootnoteQueryGroup> groupingFootnoteQueryAvailable(FootnoteQueryParam param, Pageable pageable) {
		
		Date today = getCalendarDate(0);

		List<AggregationOperation> aggregationOperations = new ArrayList<>();

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();

				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					query.append("cxr_code", param.getCxr());
				} else {
					query.append("cxr_code", new BasicDBObject("$exists", "true"));
				}

				if (param.getTarNo() != null && !param.getTarNo().isEmpty()) {
					query.append("fare_tar_no", param.getTarNo());
				} else {
					query.append("fare_tar_no", new BasicDBObject("$exists", "true"));
				}

				if (param.getFtnt() != null && !param.getFtnt().isEmpty()) {
					query.append("ftnt", param.getFtnt());
				} else {
					query.append("ftnt", new BasicDBObject("$exists", "true"));
				}
				
				query.append("cat_no", new BasicDBObject("$in", Arrays.asList("014", "015")));
				
				match.append("$match", query);

				return match;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				
				query.append("from", CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_014);
				query.append("localField", "data_table.tbl_no");
				query.append("foreignField", "tbl_no");
				query.append("as", "cat014");
				lookup.append("$lookup", query);
				
				return lookup;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				
				query.append("from", CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_015);
				query.append("localField", "data_table.tbl_no");
				query.append("foreignField", "tbl_no");
				query.append("as", "cat015");
				lookup.append("$lookup", query);
				
				return lookup;
			}
		});
		
		
		aggregationOperations.add(new AggregationOperation() {

			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject unwind = new BasicDBObject("$unwind", 
						new BasicDBObject("path", "$cat014")
						.append("preserveNullAndEmptyArrays", true)
					);
				return unwind;
			}
			
		});
		
		aggregationOperations.add(new AggregationOperation() {

			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject unwind = new BasicDBObject("$unwind", 
						new BasicDBObject("path", "$cat015")
						.append("preserveNullAndEmptyArrays", true)
					);
				return unwind;
			}
			
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				List<BasicDBObject> or = new ArrayList<>();
				or.add(new BasicDBObject("$or", Arrays.asList( 
						new BasicDBObject("cat014", new BasicDBObject("$ne", Arrays.asList())),
						new BasicDBObject("cat015", new BasicDBObject("$ne", Arrays.asList()))
						)));
		
				or.add(new BasicDBObject("$or", Arrays.asList( 
						new BasicDBObject("cat014.travel_dates_exp", "indef"),
						new BasicDBObject("cat014.travel_dates_exp", new BasicDBObject("$gte", today)),
						new BasicDBObject("cat015.sales_dates_latest_tktg", "indef"),
						new BasicDBObject("cat015.sales_dates_latest_tktg", new BasicDBObject("$gte", today))
								)));
				
				match.append("$match", new BasicDBObject("$and", or));
				return match;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject("$group",new BasicDBObject("_id", 
						new BasicDBObject("cxr", "$cxr_code")
						.append("tarNo", "$fare_tar_no")
						.append("ftnt","$ftnt")
						));
				
				return group;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject("$project", new BasicDBObject("tmp", new BasicDBObject("result", "$_id")));
				return project;
			}
		});

		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject("$group",
						new BasicDBObject("_id", null)  
						.append("total", new BasicDBObject("$sum", 1))
						.append("data", new BasicDBObject("$addToSet", "$tmp.result")));
				return group;
			}
		});

		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject("$project", 
						new BasicDBObject("_id", null)
						.append("total", "$total")
						.append("data", new BasicDBObject("$slice", Arrays.asList("$data", pageable.getPageNumber() * pageable.getPageSize(), pageable.getPageSize())))
						);
				return project;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				return new BasicDBObject("$unwind", "$data");
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				
				query.append("from", CollectionName.ATPCO_MASTER_TARIFF);
				query.append("let", new BasicDBObject("tar_no", "$data.tarNo"));
				query.append("pipeline", Arrays.asList(
						new BasicDBObject("$match", 
								new BasicDBObject("$expr", 
										new BasicDBObject("$and", Arrays.asList(
												new BasicDBObject("$eq", Arrays.asList("$tar_no","$$tar_no")),
												new BasicDBObject("$eq", Arrays.asList("$type","FARE"))
												))
										)
								)
						));
				query.append("as", "m_tariff");
				lookup.append("$lookup", query);
				return lookup;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				return new BasicDBObject("$unwind", new BasicDBObject("path",  "$m_tariff").append("preserveNullAndEmptyArrays", true));
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject("$project", new BasicDBObject("_id", 0)
						.append("total", "$total")
						.append("cxr", "$data.cxr")
						.append("tarNo", "$data.tarNo")
						.append("ftnt", "$data.ftnt")
						.append("tarCd", "$m_tariff.tar_cd"));
				return project;
			}
		});
	

		Aggregation aggregation = newAggregation(aggregationOperations);

		List<AtpcoFootnoteQueryGroup> data = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FOOTNOTE_RECORD_2, AtpcoFootnoteQueryGroup.class).getMappedResults();
		
		int total = 0;
		if(data.size() > 0) {
			
			total = data.get(0).getTotal();
		}

		return new PageImpl<>(data, pageable, total);
	}
	

	public Page<AtpcoFootnoteQueryGroup> groupingFootnoteQueryExpired(FootnoteQueryParam param, Pageable pageable) {
		
		Date today = getCalendarDate(0);

		List<AggregationOperation> aggregationOperations = new ArrayList<>();

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();

				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					query.append("cxr_code", param.getCxr());
				} else {
					query.append("cxr_code", new BasicDBObject("$exists", "true"));
				}

				if (param.getTarNo() != null && !param.getTarNo().isEmpty()) {
					query.append("fare_tar_no", param.getTarNo());
				} else {
					query.append("fare_tar_no", new BasicDBObject("$exists", "true"));
				}

				if (param.getFtnt() != null && !param.getFtnt().isEmpty()) {
					query.append("ftnt", param.getFtnt());
				} else {
					query.append("ftnt", new BasicDBObject("$exists", "true"));
				}
				
				query.append("cat_no", new BasicDBObject("$in", Arrays.asList("014", "015")));
				
				match.append("$match", query);

				return match;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				
				query.append("from", CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_014);
				query.append("localField", "data_table.tbl_no");
				query.append("foreignField", "tbl_no");
				query.append("as", "cat014");
				lookup.append("$lookup", query);
				
				return lookup;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				
				query.append("from", CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_015);
				query.append("localField", "data_table.tbl_no");
				query.append("foreignField", "tbl_no");
				query.append("as", "cat015");
				lookup.append("$lookup", query);
				
				return lookup;
			}
		});
		
		
		aggregationOperations.add(new AggregationOperation() {

			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject unwind = new BasicDBObject("$unwind", 
						new BasicDBObject("path", "$cat014")
						.append("preserveNullAndEmptyArrays", true)
					);
				return unwind;
			}
			
		});
		
		aggregationOperations.add(new AggregationOperation() {

			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject unwind = new BasicDBObject("$unwind", 
						new BasicDBObject("path", "$cat015")
						.append("preserveNullAndEmptyArrays", true)
					);
				return unwind;
			}
			
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				List<BasicDBObject> or = new ArrayList<>();
				or.add(new BasicDBObject("$or", Arrays.asList( 
						new BasicDBObject("cat014", new BasicDBObject("$ne", Arrays.asList())),
						new BasicDBObject("cat015", new BasicDBObject("$ne", Arrays.asList()))
						)));
				or.add(new BasicDBObject("$or", Arrays.asList( 
				new BasicDBObject("cat014.travel_dates_exp", new BasicDBObject("$ne", "indef")),
				new BasicDBObject("cat014.travel_dates_exp", new BasicDBObject("$lt", today)),
				new BasicDBObject("cat015.sales_dates_latest_tktg", new BasicDBObject("$ne", "indef")),
				new BasicDBObject("cat015.sales_dates_latest_tktg", new BasicDBObject("$lt", today))
						)));
				
				match.append("$match", new BasicDBObject("$and", or));
				return match;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject("$group",new BasicDBObject("_id", 
						new BasicDBObject("cxr", "$cxr_code")
						.append("tarNo", "$fare_tar_no")
						.append("ftnt","$ftnt")
						));
				
				return group;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject("$project", new BasicDBObject("tmp", new BasicDBObject("result", "$_id")));
				return project;
			}
		});

		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject("$group",
						new BasicDBObject("_id", null)  
						.append("total", new BasicDBObject("$sum", 1))
						.append("data", new BasicDBObject("$addToSet", "$tmp.result")));
				return group;
			}
		});

		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject("$project", 
						new BasicDBObject("_id", null)
						.append("total", "$total")
						.append("data", new BasicDBObject("$slice", Arrays.asList("$data", pageable.getPageNumber() * pageable.getPageSize(), pageable.getPageSize())))
						);
				return project;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				return new BasicDBObject("$unwind", "$data");
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				
				query.append("from", CollectionName.ATPCO_MASTER_TARIFF);
				query.append("let", new BasicDBObject("tar_no", "$data.tarNo"));
				query.append("pipeline", Arrays.asList(
						new BasicDBObject("$match", 
								new BasicDBObject("$expr", 
										new BasicDBObject("$and", Arrays.asList(
												new BasicDBObject("$eq", Arrays.asList("$tar_no","$$tar_no")),
												new BasicDBObject("$eq", Arrays.asList("$type","FARE RULE")),
												new BasicDBObject("$eq", Arrays.asList("$pp","public"))
												))
										)
								)
						));
				query.append("as", "m_tariff");
				lookup.append("$lookup", query);
				return lookup;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				return new BasicDBObject("$unwind", new BasicDBObject("path",  "$m_tariff").append("preserveNullAndEmptyArrays", true));
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject("$project", new BasicDBObject("_id", 0)
						.append("total", "$total")
						.append("cxr", "$data.cxr")
						.append("tarNo", "$data.tarNo")
						.append("ftnt", "$data.ftnt")
						.append("tarCd", "$m_tariff.tar_cd"));
				return project;
			}
		});
	

		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<AtpcoFootnoteQueryGroup> data = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FOOTNOTE_RECORD_2, AtpcoFootnoteQueryGroup.class).getMappedResults();
		
		int total = 0;
		if(data.size() > 0) {
			
			total = data.get(0).getTotal();
		}

		return new PageImpl<>(data, pageable, total);
	}
	
	
	public List<AtpcoFootnoteQueryDetails> getListFtntRecord2(String recordId) {
	
		List<AggregationOperation> aggregationMatch = new ArrayList<>();
		
		aggregationMatch.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject query = new BasicDBObject();
				query.append("record_id", recordId);
				BasicDBObject match = new BasicDBObject("$match", query);
				return match;
			}
		});
		
		Aggregation aggregationFtntRec2 = newAggregation(aggregationMatch);
		
		List<AtpcoFootnoteRecord2> ftntRec2 = mongoTemplate.aggregate(aggregationFtntRec2, CollectionName.ATPCO_FOOTNOTE_RECORD_2, AtpcoFootnoteRecord2.class).getMappedResults();
		
		List<AtpcoFootnoteQueryDetails> result = new ArrayList<>();
		
		if(ftntRec2.size() > 0) {
			for (AtpcoFootnoteRecord2 atpcoFootnoteRecord2 : ftntRec2) {
				
				if(atpcoFootnoteRecord2.getDataTables().size() > 0) {
					
					if(atpcoFootnoteRecord2.getCatNo().equalsIgnoreCase("014") || atpcoFootnoteRecord2.getCatNo().equalsIgnoreCase("015")) {
						
					for (DataTable dt : atpcoFootnoteRecord2.getDataTables()) {
						

							List<AggregationOperation> aggregationOperations = new ArrayList<>();
							
							aggregationOperations.add(new AggregationOperation() {
								
								@Override
								public DBObject toDBObject(AggregationOperationContext context) {
									BasicDBObject match = new BasicDBObject();
									BasicDBObject query = new BasicDBObject();
									
									query.append("record_id", recordId);
									query.append("cat_no", dt.getCatNo());
									
									match.append("$match", query);
									return match;
								}
							});
							


							aggregationOperations.add(new AggregationOperation() {
								
								@Override
								public DBObject toDBObject(AggregationOperationContext context) {
									return new BasicDBObject("$unwind", "$data_table");
								}
							});
							
							
							
							
							aggregationOperations.add(new AggregationOperation() {
								
								@Override
								public DBObject toDBObject(AggregationOperationContext context) {
									return new BasicDBObject("$match", new BasicDBObject("data_table.tbl_no", dt.getTableNo()));
								}
							});
							
							aggregationOperations.add(new AggregationOperation() {
								
								@Override
								public DBObject toDBObject(AggregationOperationContext context) {
									BasicDBObject lookup = new BasicDBObject();
									BasicDBObject query = new BasicDBObject();
									
									query.append("from", "atpco_footnote_record_3_cat_"+dt.getCatNo());
									query.append("localField", "data_table.tbl_no");
									query.append("foreignField", "tbl_no");	
									query.append("as", "cat"+dt.getCatNo());
									lookup.append("$lookup", query);
									return lookup;
								}
							});
							
						if(ftntParam != null) {
							
							aggregationOperations.add(new AggregationOperation() {
								
								@Override
								public DBObject toDBObject(AggregationOperationContext context) {
									
									BasicDBObject match = new BasicDBObject();
									BasicDBObject matchQuery = new BasicDBObject();
									
									String catNo = "cat"+dt.getCatNo();
									

									

									if(dt.getCatNo().equalsIgnoreCase("015")) {
										if(ftntParam.getSaleDateFrom() != null) {
											
											if(ftntParam.getSaleDateType() != null) {
												
												if(ftntParam.getSaleDateType().equalsIgnoreCase("1")) {
													
													matchQuery.append("$or", Arrays.asList(
															new BasicDBObject(catNo+".sales_dates_latest_tktg", "indef"),
															new BasicDBObject(catNo+".sales_dates_latest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(ftntParam.getSaleDateFrom())))
															));
													
												} else {
													matchQuery.append("$or", Arrays.asList(
															new BasicDBObject(catNo+".sales_dates_earliest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(ftntParam.getSaleDateFrom()))),
															new BasicDBObject(catNo+".sales_dates_earliest_tktg", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(ftntParam.getSaleDateFrom(), 1, 0, 0)))
															));
												}
												
											} else {
												matchQuery.append("$or", Arrays.asList(
														new BasicDBObject(catNo+".sales_dates_latest_tktg", "indef"),
														new BasicDBObject(catNo+".sales_dates_latest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(ftntParam.getSaleDateFrom())))
														));
											}
										} 
										
										if(ftntParam.getSaleDateTo() != null) {
											
											if(ftntParam.getSaleDateType() != null) {
												
												if(ftntParam.getSaleDateType().equalsIgnoreCase("1")) {
													
													matchQuery.append("$or", Arrays.asList(
															new BasicDBObject(catNo+".sales_dates_earliest_tktg", "indef"),
															new BasicDBObject(catNo+".sales_dates_earliest_tktg", new BasicDBObject("$lte", DateUtil.convertObjectToDate(ftntParam.getSaleDateTo())))
															));
													
												} else {
													matchQuery.append("$or", Arrays.asList(
															new BasicDBObject(catNo+".sales_dates_latest_tktg", new BasicDBObject("$gte", DateUtil.convertObjectToDate(ftntParam.getSaleDateTo()))),
															new BasicDBObject(catNo+".sales_dates_latest_tktg", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(ftntParam.getSaleDateTo(), 1, 0, 0)))
															));
												}
												
											} else {
												matchQuery.append("$or", Arrays.asList(
														new BasicDBObject(catNo+".sales_dates_earliest_tktg", "indef"),
														new BasicDBObject(catNo+".sales_dates_earliest_tktg", new BasicDBObject("$lte", DateUtil.convertObjectToDate(ftntParam.getSaleDateTo())))
														));
											}
										} 
										
									} else if(dt.getCatNo().equalsIgnoreCase("014")) {
										
										if(ftntParam.getTravelDateFrom() != null) {
											
											if(ftntParam.getTravelDateType() != null) {
												
												if(ftntParam.getTravelDateType().equalsIgnoreCase("1")) {
													
													matchQuery.append("$or", Arrays.asList(
															new BasicDBObject(catNo+".travel_dates_exp", "indef"),
															new BasicDBObject(catNo+".travel_dates_exp", new BasicDBObject("$gte", DateUtil.convertObjectToDate(ftntParam.getTravelDateFrom())))
															));
													
												} else {
													matchQuery.append("$or", Arrays.asList(
															new BasicDBObject(catNo+".travel_dates_comm", new BasicDBObject("$gte", DateUtil.convertObjectToDate(ftntParam.getTravelDateFrom()))),
															new BasicDBObject(catNo+".travel_dates_comm", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(ftntParam.getTravelDateFrom(), 1, 0, 0)))
															));
												}
												
											} else {
												matchQuery.append("$or", Arrays.asList(
														new BasicDBObject(catNo+".travel_dates_exp", "indef"),
														new BasicDBObject(catNo+".travel_dates_exp", new BasicDBObject("$gte", DateUtil.convertObjectToDate(ftntParam.getTravelDateFrom())))
														));
											}
										} 
										
										if(ftntParam.getTravelDateTo() != null) {
											
											if(ftntParam.getTravelDateType() != null) {
												
												if(ftntParam.getTravelDateType().equalsIgnoreCase("1")) {
													
													matchQuery.append("$or", Arrays.asList(
															new BasicDBObject(catNo+".travel_dates_comm", "indef"),
															new BasicDBObject(catNo+".travel_dates_comm", new BasicDBObject("$lte", DateUtil.convertObjectToDate(ftntParam.getTravelDateTo())))
															));
													
												} else {
													matchQuery.append("$or", Arrays.asList(
															new BasicDBObject(catNo+".travel_dates_exp", new BasicDBObject("$gte", DateUtil.convertObjectToDate(ftntParam.getTravelDateTo()))),
															new BasicDBObject(catNo+".travel_dates_exp", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(ftntParam.getTravelDateTo(), 1, 0, 0)))
															));
												}
												
											} else {
												
												matchQuery.append("$or", Arrays.asList(
														new BasicDBObject(catNo+".travel_dates_comm", "indef"),
														new BasicDBObject(catNo+".travel_dates_comm", new BasicDBObject("$lte", DateUtil.convertObjectToDate(ftntParam.getTravelDateTo())))
														));
												
											}
											
										}
										
										if(ftntParam.getCompletedDateFrom() != null) {
											
											matchQuery.append("$or", Arrays.asList(
												new BasicDBObject(catNo+".travel_dates_commence_complete", new BasicDBObject("$gte", DateUtil.convertObjectToDate(ftntParam.getCompletedDateFrom()))),
												new BasicDBObject(catNo+".travel_dates_commence_complete", new BasicDBObject("$lt", DateUtil.convertObjectToDateWithParam(ftntParam.getCompletedDateFrom(), 1, 0, 0)))
											));
											
										} 
										
										if(ftntParam.getTravelOpt() != null) {
											
											matchQuery.append(catNo+".travel_dates_appl", ftntParam.getTravelOpt());
											
										}
										
									}
									
									match.append("$match", matchQuery);
									
									return match;
									
								}
							});
						}
							
							aggregationOperations.add(new AggregationOperation() {
								
								@Override
								public DBObject toDBObject(AggregationOperationContext context) {
									return new BasicDBObject("$unwind", "$cat"+dt.getCatNo());
								}
							});
							
							aggregationOperations.add(new AggregationOperation() {

								@Override
								public DBObject toDBObject(AggregationOperationContext context) {
									BasicDBObject project = new BasicDBObject();
									BasicDBObject query = new BasicDBObject();
									
									query.append("catNo", "$cat_no" );
									query.append("seqNo", "$seq_no");
									query.append("locType1", "$geo_spec_1_type");
									query.append("loc1", "$geo_spec_1_value");
									query.append("locType2", "$geo_spec_2_type");
									query.append("loc2", "$geo_spec_2_value");
									query.append("fareClass", "$fare_class");
									query.append("owrt", "$ow_rt");
									query.append("routingNo", "$rtg_no");
									query.append("ftnt","$ftnt");
									query.append("effDate", "$dates_eff");
									query.append("discDate", "$dates_disc");
//									if(dt.getCatNo().equalsIgnoreCase("014") || dt.getCatNo().equalsIgnoreCase("015")) {
										query.append("travelStart","$cat014.travel_dates_comm");
										query.append("travelEnd", "$cat014.travel_dates_exp");
										query.append("saleStart", "$cat015.sales_dates_earliest_tktg");
										query.append("saleEnd","$cat015.sales_dates_latest_tktg");
										query.append("travelComplete","$cat014.travel_dates_commence_complete");
										query.append("travelCompInd", "$cat014.travel_appl");
//									}
									
									
							        
									project.append("$project", query);
									return project;
								}
								
							});
							
							
							
							Aggregation aggregation = newAggregation(aggregationOperations);
							
							AtpcoFootnoteQueryDetails af = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FOOTNOTE_RECORD_2, AtpcoFootnoteQueryDetails.class).getUniqueMappedResult();
							
							if(af != null) {
								result.add(af);
							}
							
							
						
						
						}
					
					} else {
						
						List<AggregationOperation> aggregationOperations = new ArrayList<>();
						
						aggregationOperations.add(new AggregationOperation() {
							
							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								BasicDBObject match = new BasicDBObject();
								BasicDBObject query = new BasicDBObject();
								
								query.append("record_id", recordId);
								query.append("cat_no", atpcoFootnoteRecord2.getCatNo());
								
								match.append("$match", query);
								return match;
							}
						});
						


						aggregationOperations.add(new AggregationOperation() {
							
							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								return new BasicDBObject("$unwind", "$data_table");
							}
						});
						
						
						
						
						aggregationOperations.add(new AggregationOperation() {
							
							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								return new BasicDBObject("$match", new BasicDBObject("data_table.tbl_no", atpcoFootnoteRecord2.getDataTables().get(0).getTableNo()));
							}
						});
						
						aggregationOperations.add(new AggregationOperation() {
							
							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								BasicDBObject lookup = new BasicDBObject();
								BasicDBObject query = new BasicDBObject();
								
								query.append("from", "atpco_footnote_record_3_cat_"+atpcoFootnoteRecord2.getCatNo());
								query.append("localField", "data_table.tbl_no");
								query.append("foreignField", "tbl_no");	
								query.append("as", "cat"+atpcoFootnoteRecord2.getCatNo());
								lookup.append("$lookup", query);
								return lookup;
							}
						});
						
						aggregationOperations.add(new AggregationOperation() {
							
							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								return new BasicDBObject("$match", new BasicDBObject("cat"+atpcoFootnoteRecord2.getCatNo(), new BasicDBObject("$ne", Arrays.asList())));
							}
						});
						
						aggregationOperations.add(new AggregationOperation() {
							
							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								return new BasicDBObject("$unwind", "$cat"+atpcoFootnoteRecord2.getCatNo());
							}
						});
						
						aggregationOperations.add(new AggregationOperation() {

							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								BasicDBObject project = new BasicDBObject();
								BasicDBObject query = new BasicDBObject();
								
								query.append("catNo", "$cat_no" );
								query.append("seqNo", "$seq_no");
								query.append("locType1", "$geo_spec_1_type");
								query.append("loc1", "$geo_spec_1_value");
								query.append("locType2", "$geo_spec_2_type");
								query.append("loc2", "$geo_spec_2_value");
								query.append("fareClass", "$fare_class");
								query.append("owrt", "$ow_rt");
								query.append("routingNo", "$rtg_no");
								query.append("ftnt","$ftnt");
								query.append("effDate", "$dates_eff");
								query.append("discDate", "$dates_disc");
								
						        
								project.append("$project", query);
								return project;
							}
							
						});
						
						
						
						Aggregation aggregation = newAggregation(aggregationOperations);
						
						AtpcoFootnoteQueryDetails af = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FOOTNOTE_RECORD_2, AtpcoFootnoteQueryDetails.class).getUniqueMappedResult();
						
						if(af != null) {
							result.add(af);
						}
						
						
					}
				}
			}
		}
		
		
		return result;
	}

	
	public Page<AtpcoFootnoteRecord2> findByFootnoteQueryParam(FootnoteQueryParam param, Pageable pageable) {

		try {

			List<AggregationOperation> aggregationOperations = getAggregationMatchFootnoteQuery(param);

			Aggregation aggregation = newAggregation(aggregationOperations);

			SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
			aggregationOperations.add(skip);

			LimitOperation limit = new LimitOperation(pageable.getPageSize());
			aggregationOperations.add(limit);

			Aggregation aggregationPagination = newAggregation(aggregationOperations);

			List<AtpcoFootnoteRecord2> result = mongoTemplate
					.aggregate(aggregationPagination, "atpco_footnote_record_2", AtpcoFootnoteRecord2.class)
					.getMappedResults();

			return new PageImpl<>(result, pageable,
					mongoTemplate.aggregate(aggregation, "atpco_footnote_record_2", AtpcoFootnoteRecord2.class)
							.getMappedResults().size());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<AtpcoFootnoteRecord2GroupByCatNo> groupFootnoteByRecordId(String recordId) {

		List<AggregationOperation> aggregationOperations = new ArrayList<>();

		MatchOperation match = new MatchOperation(new Criteria("record_id").is(recordId));
		aggregationOperations.add(match);

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("_id", "$cat_no");
				query.append("record_2", new BasicDBObject("$push", "$$ROOT"));
				group.append("$group", query);
				return group;
			}
		});

		SortOperation sort = new SortOperation(new Sort(Direction.ASC, "_id"));
		aggregationOperations.add(sort);

		Aggregation aggregation = newAggregation(aggregationOperations);

		List<AtpcoFootnoteRecord2GroupByCatNo> result = mongoTemplate
				.aggregate(aggregation, CollectionName.ATPCO_FOOTNOTE_RECORD_2, AtpcoFootnoteRecord2GroupByCatNo.class)
				.getMappedResults();

		return result;
	}

	public List<AggregationOperation> getAggregationMatchFootnoteQuery(FootnoteQueryParam param) {
		List<AggregationOperation> aggregationOperations = new ArrayList<>();

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();

				Date today = getCalendarDate(0);
				Date twoYearsBefore = getCalendarDate(1);

				if (param.isIncludeDiscDate()) {
					query.append("$or",
							Arrays.asList(
									new BasicDBObject("dates_disc",
											new BasicDBObject("$lte", today).append("$gte", twoYearsBefore)),
									new BasicDBObject("dates_disc", "indef")));

				} else {
					query.append("$or", Arrays.asList(new BasicDBObject("dates_disc", new BasicDBObject("$gte", today)),
							new BasicDBObject("dates_disc", "indef")));
				}

				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					query.append("cxr_code", param.getCxr());
				} else {
					query.append("cxr_code", new BasicDBObject("$exists", "true"));
				}

				if (param.getTarNo() != null && !param.getTarNo().isEmpty()) {
					query.append("fare_tar_no", param.getTarNo());
				} else {
					query.append("fare_tar_no", new BasicDBObject("$exists", "true"));
				}

				if (param.getFtnt() != null && !param.getFtnt().isEmpty()) {
					query.append("ftnt", param.getFtnt());
				} else {
					query.append("ftnt", new BasicDBObject("$exists", "true"));
				}

				if (param.getCatNo() != null && !param.getCatNo().isEmpty()) {
					if (param.getCatNo().equalsIgnoreCase("comb")) {

						query.append("cat_no", new BasicDBObject("$in", Arrays.asList("014", "015")));

					} else {
						query.append("cat_no", param.getCatNo());
					}

				} else {
					query.append("cat_no", new BasicDBObject("$exists", "true"));
				}

				match.append("$match", query);

				return match;
			}
		});

		return aggregationOperations;
	}

	public Date getCalendarDate(int beforeAfter) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		if (beforeAfter == 1) {
			cal.add(Calendar.YEAR, -2);
		}

		return cal.getTime();
	}
}
