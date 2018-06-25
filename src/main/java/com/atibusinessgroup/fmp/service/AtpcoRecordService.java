package com.atibusinessgroup.fmp.service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.CharUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CategoryType;
import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoCcfParcity;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterPassengerTypeCode;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterSurchargeCode;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterTourTypeCode;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat01;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat02;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat03;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat04;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat05;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat06;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat07;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat08;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat09;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat101;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat102;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat103;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat104;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat106;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat106Carriers;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat107;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat107Tarrif;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat108;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat108FareClassType;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat109;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat109OpenJaw;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat11;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat12;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat13;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat14;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat15;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat16;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat17;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat18;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat19;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat23;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat25;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat26;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat27;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat50;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord3CategoryWithDataTable;
import com.atibusinessgroup.fmp.domain.dto.BaseFareTable;
import com.atibusinessgroup.fmp.domain.dto.CategoryAttribute;
import com.atibusinessgroup.fmp.domain.dto.CategoryAttributeObject;
import com.atibusinessgroup.fmp.domain.dto.CategoryObject;
import com.atibusinessgroup.fmp.domain.dto.CategoryTextFormatAndAttribute;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.domain.dto.DateTable;
import com.atibusinessgroup.fmp.domain.dto.FlightTable;
import com.atibusinessgroup.fmp.domain.dto.Record3ReflectionObject;
import com.atibusinessgroup.fmp.domain.dto.TextTable;
import com.atibusinessgroup.fmp.repository.PassengerRepository;
import com.atibusinessgroup.fmp.repository.SurchargeCodeRepository;
import com.atibusinessgroup.fmp.repository.TourTypeCodeRepository;
import com.atibusinessgroup.fmp.repository.custom.AtpcoCcfParcityCustomRepository;
import com.atibusinessgroup.fmp.repository.custom.AtpcoRecord3CategoryCustomRepository;
import com.atibusinessgroup.fmp.service.util.AtpcoDataConverterUtil;
import com.atibusinessgroup.fmp.service.util.DateUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;

@Service
public class AtpcoRecordService {
	
	private final AtpcoCcfParcityCustomRepository atpcoCcfParcityCustomRepository;
	private final AtpcoRecord3CategoryCustomRepository atpcoRecord3CategoryCustomRepository;
	private final SurchargeCodeRepository surchargeCodeRepository;
	private final TourTypeCodeRepository tourTypeCodeRepository;
	private final PassengerRepository passengerRepository;
	private final MongoTemplate mongoTemplate;

	public AtpcoRecordService(AtpcoCcfParcityCustomRepository atpcoCcfParcityCustomRepository, AtpcoRecord3CategoryCustomRepository atpcoRecord3CategoryCustomRepository, SurchargeCodeRepository surchargeCodeRepository,
			TourTypeCodeRepository tourTypeCodeRepository, PassengerRepository passengerRepository, MongoTemplate mongoTemplate) {
		this.atpcoCcfParcityCustomRepository = atpcoCcfParcityCustomRepository;
		this.atpcoRecord3CategoryCustomRepository = atpcoRecord3CategoryCustomRepository;
		this.surchargeCodeRepository = surchargeCodeRepository;
		this.tourTypeCodeRepository = tourTypeCodeRepository;
		this.passengerRepository = passengerRepository;
		this.mongoTemplate = mongoTemplate;
	}

	public Date resolveFocusDate(Date paramFrom, Object effective, Object discontinue) {
		Date date = null;
		
		if (paramFrom == null) {
			paramFrom = new Date();
		}
		
		if (effective != null && discontinue != null) {
			Date effDate = null;
			
			if (!effective.toString().contentEquals("indef")) {
				try {
					effDate = (Date) effective;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (effDate != null) {
				if (discontinue.toString().contentEquals("indef")) {
					if (paramFrom.after(effDate) || paramFrom.equals(effDate)) {
						date = paramFrom;
					} else {
						date = effDate;
					}
				} else {
					Date discDate = null;
					
					try {
						discDate = (Date) discontinue;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if (discDate != null) {
						if ((paramFrom.after(effDate) || paramFrom.equals(effDate)) && (paramFrom.before(discDate) || paramFrom.equals(discDate))) {
							date = paramFrom;
						} else if (paramFrom.after(discDate) || paramFrom.equals(discDate)) {
							date = discDate;
						}
					}
				}
			}
		}
		
		return date;
	}
	
	public boolean compareMatchingFareAndRecord(String fOwrt, String fRoutingNo, String fFootnote, Object focusDate,
			String rOwrt, String rRoutingNo, String rFootnote, Object rEffectiveDate, String rDiscontinueDate) {
		boolean match = false;
		
		if (compareEffectiveDiscontinueDates(focusDate, rEffectiveDate, rDiscontinueDate) && compareRoutingNo(fRoutingNo, rRoutingNo) && compareOwrt(fOwrt, rOwrt)
				&& compareFootnote(fFootnote, rFootnote)) {
			match = true;
//			 System.out.println("Matched");
		}
		
		return match;
	}
	
	public boolean compareMatchingFareAndRecord(String fGeoType1, String fGeoLoc1, String fGeoType2, String fGeoLoc2,
			String fOwrt, String fRoutingNo, String fFootnote, Object focusDate,
			String rGeoType1, String rGeoLoc1, String rGeoType2, String rGeoLoc2, String rOwrt, String rRoutingNo,
			String rFootnote, Object rEffectiveDate, Object rDiscontinueDate) {
		boolean match = false;

		if (compareEffectiveDiscontinueDates(focusDate, rEffectiveDate, rDiscontinueDate) && compareRoutingNo(fRoutingNo, rRoutingNo) && compareOwrt(fOwrt, rOwrt)
				&& compareFootnote(fFootnote, rFootnote) && compareGeoSpec(fGeoType1, fGeoLoc1, fGeoType2, fGeoLoc2, rGeoType1, rGeoLoc1, rGeoType2, rGeoLoc2)) {
			match = true;
//			 System.out.println("Matched");
		}

		return match;
	}

	public boolean compareMatchingFareAndRecord(String fGeoType1, String fGeoLoc1, String fGeoType2, String fGeoLoc2,
			String fFareClass, String fFareType, String fSeasonType, String fDayOfWeekType, String fOwrt,
			String fRoutingNo, String fFootnote, Object focusDate, String rGeoType1,
			String rGeoLoc1, String rGeoType2, String rGeoLoc2, String rFareClass, String rFareType, String rSeasonType,
			String rDayOfWeekType, String rOwrt, String rRoutingNo, String rFootnote, Object rEffectiveDate,
			Object rDiscontinueDate) {
		boolean match = false;

		if (compareMatchingFareAndRecord(fGeoType1, fGeoLoc1, fGeoType2, fGeoLoc2, fOwrt, fRoutingNo, fFootnote,
				focusDate, rGeoType1, rGeoLoc1, rGeoType2, rGeoLoc2, rOwrt, rRoutingNo,
				rFootnote, rEffectiveDate, rDiscontinueDate) && compareFareClass(fFareClass, rFareClass)
				&& compareFareType(fFareType, rFareType) && compareSeasonType(fSeasonType, rSeasonType)
				&& compareDayOfWeekType(fDayOfWeekType, rDayOfWeekType)) {
			match = true;
//			 System.out.println("Matched");
		}

		return match;
	}

	public boolean compareRoutingNo(String frn, String rrn) {
		boolean match = false;

		if (frn != null && rrn != null) {
			if (rrn.contentEquals("99999") || frn.contentEquals(rrn)) {
				match = true;
			}
		} else {
			match = true;
		}
		
//		System.out.println("Routing No: " + frn + ", " + rrn + ", " + match);
		
		return match;
	}

	public boolean compareFareClass(String fFareClass, String rFareClass) {
		boolean match = true;
		
		if (fFareClass != null && !fFareClass.trim().isEmpty() && rFareClass != null && !rFareClass.trim().isEmpty()) {
			rFareClass = rFareClass.trim();
			
			if (rFareClass.contains("-")) {
				rFareClass += "-";
				String[] parts = rFareClass.split("-");
				for (String part: parts) {
					if (fFareClass.indexOf(part) != -1) {
						int from = fFareClass.indexOf(part) - 1;
						int to = from + part.length() + 1;
						
						try {
							if (CharUtils.isAsciiNumeric(fFareClass.charAt(from + 1)) && CharUtils.isAsciiNumeric(fFareClass.charAt(from))) {
								match = false;
							}
						} catch (Exception e) {
						}
						
						try {
							if (CharUtils.isAsciiNumeric(fFareClass.charAt(to - 1)) && CharUtils.isAsciiNumeric(fFareClass.charAt(to))) {
								match = false;
							}
						} catch (Exception e) {
						}
					} else {
						match = false;
					}
					
					if (!match) {
						break;
					}
				}
			} else {
				if (!fFareClass.trim().contentEquals(rFareClass.trim())) {
					match = false;
				}
			}
		} else {
			match = true;
		}
		
//		System.out.println("Fare Class: " + fFareClass + ", " + rFareClass + ", " + match);
		
		return match;
	}

	public boolean compareFareType(String fFareType, String rFareType) {
		boolean match = false;

		if (fFareType != null && rFareType != null) {
			if (rFareType.trim().isEmpty() || fFareType.contentEquals(rFareType)) {
				match = true;
			}
		} else {
			match = true;
		}

//		System.out.println("Fare Type: " + fFareType + ", " + rFareType + ", " + match);
		
		return match;
	}

	public boolean compareSeasonType(String fSeasonType, String rSeasonType) {
		boolean match = false;
		
		if (fSeasonType != null && rSeasonType != null) {
			if (rSeasonType.trim().isEmpty() || fSeasonType.contentEquals(rSeasonType)) {
				match = true;
			}
		} 
		
//		System.out.println("Season Type: " + fSeasonType + ", " + rSeasonType + ", " + match);
		
		return match;
	}

	public boolean compareDayOfWeekType(String fDayOfWeekType, String rDayOfWeekType) {
		boolean match = false;

		if (fDayOfWeekType != null && rDayOfWeekType != null) {
			if (rDayOfWeekType.trim().isEmpty() || fDayOfWeekType.contentEquals(rDayOfWeekType)) {
				match = true;
			}
		} else {
			match = true;
		}
		
//		System.out.println("DOW Type: " + fDayOfWeekType + ", " + rDayOfWeekType + ", " + match);
		
		return match;
	}

	public boolean compareOwrt(String fowrt, String rowrt) {
		boolean match = false;

		if (fowrt != null && rowrt != null) {
			if (rowrt.trim().isEmpty() || fowrt.contentEquals(rowrt)) {
				match = true;
			}
		} else {
			match = true;
		}
		
//		System.out.println("OW/RT: " + fowrt + ", " + rowrt + ", " + match);
		
		return match;
	}

	public boolean compareFootnote(String ffnt, String rfnt) {
		boolean match = false;

		if (ffnt != null && rfnt != null) {
			if (rfnt.trim().isEmpty() || ffnt.contentEquals(rfnt)) {
				match = true;
			}
		} else {
			match = true;
		}
		
//		System.out.println("Ftnt: " + ffnt + ", " + rfnt + ", " + match);
		
		return match;
	}

	public boolean compareEffectiveDiscontinueDates(Object focusDate, Object effective, Object discontinue) {
		boolean match = false;
		
		if (effective != null && discontinue != null) {
			Date focus = null, eff = null, disc = null;
			
			try {
				focus = DateUtil.convertObjectToDate(focusDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (focus != null) {
				if (effective.toString().contentEquals("indef") && discontinue.toString().contentEquals("indef")) {
					match = true;
				} else {
					if (!effective.toString().contentEquals("indef")) {
						try {
							eff = (Date) effective;
						} catch (Exception e) {
						}
					}
					
					if (!discontinue.toString().contentEquals("indef")) {
						try {
							disc = (Date) discontinue;
						} catch (Exception e) {
						}
					}
					
					if (eff != null && disc != null) {
						if ((focus.after(eff) || focus.equals(eff)) && (focus.before(disc) || focus.equals(disc))) {
							match = true;
						}
					} else if (eff != null && disc == null) {
						if (focus.after(eff) || focus.equals(eff)) {
							match = true;
						}
					} else if (eff == null && disc != null) {
						if (focus.before(disc) || focus.equals(disc)) {
							match = true;
						}
					}
				}
			}
		}
		
//		System.out.println("Effective discontinue date : " + focusDate + ", " + effective + ", " + discontinue + ", " + match);
		
		return match;
	}
	
	public boolean compareGeoSpec(String ft1, String fl1, String ft2, String fl2, String rt1, String rl1, String rt2,
			String rl2) {
		boolean match = false;
		
		if (compareLocations(ft1, fl1, rt1, rl1) || compareLocations(ft1, fl1, rt2, rl2)) {
			match = true;
		} else if (compareLocations(ft2, fl2, rt1, rl1) || compareLocations(ft2, fl2, rt2, rl2)) {
			match = true;
		}
		
//		System.out.println("GeoSpec: (" + ft1 + ", " + fl1 + ") (" + ft2 + ", " + fl2 + ") | (" + rt1 + ", " + rl1 + ") (" + rt2 + ", " + rl2 + ") " + match);
		
		return match;
	}
	
	public boolean compareLocations(String t1, String l1, String t2, String l2) {
		boolean match = false;
		
		if (t1 != null && !t1.trim().isEmpty() && t2 != null && !t2.trim().isEmpty() &&
				l1 != null && !l1.trim().isEmpty() && l2 != null && !l2.trim().isEmpty()) {
			t1 = t1.trim();
			t2 = t2.trim();
			l1 = l1.trim();
			l2 = l2.trim();
			
			if (t1.contentEquals(t2) && l1.contentEquals(l2)) {
				match = true;
			} else {
				AtpcoCcfParcity par = atpcoCcfParcityCustomRepository.findOneByTypesAndLocations(t1, l1, t2, l2);
				if (par != null) {
					match = true;
				}
			}
		} else {
			match = true;
		}
		
		return match;
	}

	public List<CategoryObject> getAndConvertCategoryObjectDataTable(String category, List<DataTable> dataTables, String type) {
		List<CategoryObject> result = new ArrayList<>();

		String classBasePackage = "com.atibusinessgroup.fmp.domain.atpco.";

		Record3ReflectionObject ro = null;

		if (type != null && type.contentEquals("Rule")) {
			ro = getCategorySettingInfo(classBasePackage, category);
		} else if (type != null && type.contentEquals("Footnote")) {
			ro = getFootnoteCategorySettingInfo(classBasePackage, category);
		}

		String relationship = null;

		if (ro != null && ro.getCollectionName() != null) {
			List<AtpcoRecord3CategoryWithDataTable> catdts = atpcoRecord3CategoryCustomRepository
					.findAllRecord3ByDataTable(ro.getCollectionName(),
							dataTables.stream().map(DataTable::getTableNo).collect(Collectors.toList()));

			for (AtpcoRecord3CategoryWithDataTable catdt : catdts) {
				try {
					Class<?> c = Class.forName(ro.getClassName());
					Object cat = convertDBObjectToPOJO(c, catdt.getCategory());
					
					if (ro.getGetTableNoMethodName() != null) {
						Method tableNoMethod = c.getMethod(ro.getGetTableNoMethodName(), null);
						String tableNo = (String) tableNoMethod.invoke(cat);

						for (DataTable dt : dataTables) {
							if (dt.getTableNo().contentEquals(tableNo)) {
								relationship = dt.getRelationalIndicator();
								break;
							}
						}
					}
					
					CategoryObject cf = new CategoryObject();
					cf.setRelationship(relationship);
					cf.setCategory(cat);
					result.add(cf);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	
	public CategoryTextFormatAndAttribute getAndConvertCategoryDataTable(String category, List<DataTable> dataTables,
			String type) {
		CategoryTextFormatAndAttribute result = new CategoryTextFormatAndAttribute();
		List<CategoryAttribute> attributes = new ArrayList<>();
		
		String classBasePackage = "com.atibusinessgroup.fmp.domain.atpco.";

		Record3ReflectionObject ro = null;

		if (type.contentEquals(CategoryType.FOOTNOTE)) {
			ro = getFootnoteCategorySettingInfo(classBasePackage, category);
		} else {
			ro = getCategorySettingInfo(classBasePackage, category);
		}
		
		String textFormat = "";
		String relationship = null;
		TextTable textTable996 = null;
		DateTable dateTable994 = null;
		
		if (ro.getCollectionName() != null) {
			List<AtpcoRecord3CategoryWithDataTable> catdts = atpcoRecord3CategoryCustomRepository
					.findAllRecord3ByDataTable(ro.getCollectionName(),
							dataTables.stream().map(DataTable::getTableNo).collect(Collectors.toList()));
			
			for (AtpcoRecord3CategoryWithDataTable catdt : catdts) {
				try {
					textTable996 = null;
					
					Class<?> c = Class.forName(ro.getClassName());
					Object cat = convertDBObjectToPOJO(c, catdt.getCategory());
					
					if (ro.getGetTableNoMethodName() != null) {
						Method tableNoMethod = c.getMethod(ro.getGetTableNoMethodName(), null);
						String tableNo = (String) tableNoMethod.invoke(cat);
						
						for (DataTable dt : dataTables) {
							if (dt.getTableNo().contentEquals(tableNo)) {
								relationship = dt.getRelationalIndicator();
								break;
							}
						}
					}
					
					if (ro.getGetTextTable996NoMethodName() != null) {
						Method textTable996NoMethod = c.getMethod(ro.getGetTextTable996NoMethodName(), null);
						String textTable996No = (String) textTable996NoMethod.invoke(cat);
						
						if (textTable996No != null && !textTable996No.isEmpty() && !textTable996No.contentEquals("00000000")) {
							textTable996 = atpcoRecord3CategoryCustomRepository.findRecord3TextTable(textTable996No);
						}
					}
					
					if (ro.getGetDateTable994NoMethodName() != null) {
						Method dateTable994NoMethod = c.getMethod(ro.getGetDateTable994NoMethodName(), null);
						String dateTable994No = (String) dateTable994NoMethod.invoke(cat);
						
						if (dateTable994No != null && !dateTable994No.isEmpty() && !dateTable994No.contentEquals("00000000")) {
							dateTable994 = atpcoRecord3CategoryCustomRepository.findRecord3DateTable(dateTable994No);
						}
					}
					
					if (!textFormat.trim().isEmpty()) {
						textFormat += AtpcoDataConverterUtil.convertRelationshipToName(relationship) + "\n\n";
					}
					
					String dateTable = AtpcoDataConverterUtil.convertDateTableToText(dateTable994);
					if (!dateTable.isEmpty()) {
						textFormat += dateTable + "\n";
					}
					
					String text = convertCodedCategoryValueToText(category, cat);
					
					if (!text.trim().isEmpty()) {
						textFormat += text;
					}
					
					String textTable = AtpcoDataConverterUtil.convertTextTableToText(textTable996);
					if (!textTable.isEmpty()) {
						textFormat += textTable + "\n";
					}
					
					attributes.add(convertObjectToCategoryAttribute(AtpcoDataConverterUtil.convertCategoryTypeToName(type), AtpcoDataConverterUtil.convertRelationshipToName(relationship), category, cat));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		result.setTextFormat(textFormat);
		result.setAttributes(attributes);

		return result;
	}

	public CategoryAttribute convertObjectToCategoryAttribute(String type, String relationship, String category, Object obj) {
		CategoryAttribute result = new CategoryAttribute();
		result.setType(type);
		result.setSubcat(category);
		result.setRelationship(relationship);

		List<CategoryAttributeObject> catAttrObjs = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();

		try {
			String objJson = mapper.writeValueAsString(obj);

			Map<String, Object> map = new HashMap<String, Object>();
			map = mapper.readValue(objJson, new TypeReference<Map<String, Object>>() {});
			
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				
				CategoryAttributeObject attObj = new CategoryAttributeObject();
				attObj.setKey(key);
				attObj.setValue(value);
				catAttrObjs.add(attObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.setAttributes(catAttrObjs);

		return result;
	}

	public Object convertDBObjectToPOJO(Class<?> c, DBObject category) {
		Object result = null;

		try {
			result = mongoTemplate.getConverter().read(c, category);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public String generateCategoryTextHeader(String type, String tariffNo, String tariffCode, String ruleNo, String sequence, Object date) {
		String result = "";
		
		String catType = AtpcoDataConverterUtil.convertCategoryTypeToName(type);
		
		result += (catType != null ? catType : "") + " RULE " + (ruleNo != null ? ruleNo : "") + " IN TARIFF " + (tariffCode != null ? tariffCode : "") + " (" + (tariffNo != null ? tariffNo : "") + ")\n";
		result += "SEQUENCE: " + (sequence != null ? sequence : "") + "\n";
		
		String effStr = null;
		try {
			Date eff = (Date) date;
			effStr = new SimpleDateFormat("ddMMMyyyy").format(eff);
		} catch (Exception e) {
		}
		
		result += "EFFECTIVE: " + (effStr != null ? effStr.toUpperCase() : "") + "\n\n";
		
		return result;
	}
	
	public Record3ReflectionObject getCategorySettingInfo(String classBasePackage, String category) {
		Record3ReflectionObject result = new Record3ReflectionObject();

		switch (category) {
		case "001":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat01"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_001);
			break;
		case "002":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat02"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_002);
			break;
		case "003":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat03"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_003);
			break;
		case "004":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat04"));
			result.setGetTableNoMethodName("getTableNo");
			result.setGetTextTable996NoMethodName("getTextTable996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_004);
			break;
		case "005":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat05"));
			result.setGetTableNoMethodName("getTableNumber");
			result.setGetTextTable996NoMethodName("getTextTableNumber996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_005);
			break;
		case "006":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat06"));
			result.setGetTableNoMethodName("getTblNo");
			result.setGetTextTable996NoMethodName("getTextTblNo996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_006);
			break;
		case "007":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat07"));
			result.setGetTableNoMethodName("getTblNo");
			result.setGetTextTable996NoMethodName("getTextTblNo996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_007);
			break;
		case "008":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat08"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_008);
			break;
		case "009":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat09"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_009);
			break;
		case "101":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat101"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_101);
			break;
		case "102":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat102"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_102);
			break;
		case "103":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat103"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_103);
			break;
		case "104":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat104"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_104);
			break;
		case "106":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat106"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_106);
			break;
		case "107":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat107"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_107);
			break;
		case "108":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat108"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_108);
			break;
		case "109":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat109"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_109);
			break;
		case "011":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat11"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_011);
			break;
		case "012":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat12"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_table_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_012);
			break;
		case "013":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat13"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_013);
			break;
		case "014":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat14"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_table_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_014);
			break;
		case "015":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat15"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_table_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_015);
			break;
		case "016":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat16"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_016);
			break;
		case "017":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat17"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_017);
			break;
		case "018":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat18"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_018);
			break;
		case "019":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat19"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_019);
			break;
		case "020":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat20"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_020);
			break;
		case "021":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat21"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_021);
			break;
		case "022":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat22"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_022);
			break;
		case "023":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat23"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_023);
			break;
		case "025":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat25"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_025);
			break;
		case "026":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat26"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_026);
			break;
		case "027":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat27"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_table_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_027);
			break;
		case "028":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat28"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_028);
			break;
		case "029":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat29"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_029);
			break;
		case "031":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat31"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_031);
			break;
		case "033":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat33"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_033);
			break;
		case "035":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat35"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_035);
			break;
		case "050":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat50"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_050);
			break;
		}

		return result;
	}

	public Record3ReflectionObject getFootnoteCategorySettingInfo(String classBasePackage, String category) {
		Record3ReflectionObject result = new Record3ReflectionObject();

		switch (category) {
		case "003":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat03"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_003);
			break;
		case "011":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat11"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_011);
			break;
		case "014":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat14"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_table_no_996");
			result.setCollectionName(CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_014);
			break;
		case "015":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat15"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_table_no_996");
			result.setCollectionName(CollectionName.ATPCO_FOOTNOTE_RECORD_3_CAT_015);
			break;
		}

		return result;
	}

	private String convertCodedCategoryValueToText(String category, Object catObj) {
		String result = "";
		
		try {
			switch (category) {
			case "001":
				AtpcoRecord3Cat01 cat01 = (AtpcoRecord3Cat01) catObj;
				if (cat01.getPsgr_type() != null && !cat01.getPsgr_type().isEmpty()) {
					result += "\tVALID FOR: " + cat01.getPsgr_type() + "\n";
				}
				if (cat01.getAge_min() != null && !cat01.getAge_min().isEmpty()) {
					result += "\tMINIMUM AGE: " + cat01.getAge_min() + "\n";
				}
				if (cat01.getAge_max() != null && !cat01.getAge_max().isEmpty()) {
					result += "\tMAXIMUM AGE: " + cat01.getAge_max() + "\n";
				}
				if (cat01.getAccount_code() != null && !cat01.getAccount_code().isEmpty()) {
					result += "\tACCOUNT CODE: " + cat01.getAccount_code() + "\n\n";
				}
				break;
			case "002":
				AtpcoRecord3Cat02 cat02 = (AtpcoRecord3Cat02) catObj;
				if (cat02.getDay_of_week() != null && !cat02.getDay_of_week().isEmpty()) {
					String days = AtpcoDataConverterUtil.convertDayInNumberToName(cat02.getDay_of_week());
					result += "\tAPPLICABLE DAYS: " + days + "\n";
				}
				String time = "";
				if (cat02.getTime_of_day_start() != null && !cat02.getTime_of_day_start().isEmpty()) {
					time += cat02.getTime_of_day_start();
				}
				if (cat02.getTime_of_day_stop() != null && !cat02.getTime_of_day_stop().isEmpty()) {
					time += " - " + cat02.getTime_of_day_stop();
				}
				if (!time.isEmpty()) {
					result += "\tTIME: " + time + " HOURS ON APPLICABLE DAYS OF WEEK\n\n";
				}
				break;
			case "003":
				AtpcoRecord3Cat03 cat03 = (AtpcoRecord3Cat03) catObj;
				String season = "";
				String seastart = AtpcoDataConverterUtil.convertSeparatedDateIntoTextDate(cat03.getDate_start_dd(), cat03.getDate_start_mm(), cat03.getDate_start_yy());
				String seaend = AtpcoDataConverterUtil.convertSeparatedDateIntoTextDate(cat03.getDate_stop_dd(), cat03.getDate_stop_mm(), cat03.getDate_stop_yy());
				if (!seastart.isEmpty()) {
					season += seastart;
				}
				if (!seaend.isEmpty()) {
					season += " - " + seaend;
				}
				if (!season.isEmpty()) {
					result += "\tSEASON: " + season + "\n\n";
				}
				break;
			case "004":
				AtpcoRecord3Cat04 cat04 = (AtpcoRecord3Cat04) catObj;
				if (cat04.getFlightNoApplicationIndicator() != null && !cat04.getFlightNoApplicationIndicator().isEmpty()) {
					result += "\tAPPLICATION: " + AtpcoDataConverterUtil.convertFlightApplicationRelationshipIndicatorToName(cat04.getFlightNoApplicationIndicator()) + "\n";
				}
				if (cat04.getCarrierCode1() != null && !cat04.getCarrierCode1().isEmpty()) {
					result += "\tCARRIER 1: " + cat04.getCarrierCode1() + "\n";
				}
				if (cat04.getFlightNo1() != null && !cat04.getFlightNo1().isEmpty()) {
					result += "\tFLIGHT NO 1: " + cat04.getFlightNo1() + "\n";
				}
				if (cat04.getCarrierFlight1TableNo986() != null && !cat04.getCarrierFlight1TableNo986().isEmpty()) {
					FlightTable ft = atpcoRecord3CategoryCustomRepository.findRecord3FlightTable(cat04.getCarrierFlight1TableNo986());
					String ft986 = AtpcoDataConverterUtil.convertFlightTableToText(ft);
					if (!ft986.isEmpty()) {
						result += "\tCARRIER/FLIGHT TABLE 1: \n";
						result += ft986 + "\n";
					}
				}
				if (cat04.getRelationshipIndicator1And2() != null && !cat04.getRelationshipIndicator1And2().isEmpty()) {
					String ri = AtpcoDataConverterUtil.convertFlightApplicationBetweenCarrierIndicatorToName(cat04.getRelationshipIndicator1And2());
					if (!ri.isEmpty()) {
						result += ri + "\n";
					} else {
						result += "INVALID result APPLICATION RELATIONSHIP INDICATOR\n";
					}
				}
				if (cat04.getCarrierCode2() != null && !cat04.getCarrierCode2().isEmpty()) {
					result += "\tCARRIER 2: " + cat04.getCarrierCode2() + "\n";
				}
				if (cat04.getFlightNo2() != null && !cat04.getFlightNo2().isEmpty()) {
					result += "\tFLIGHT NO 2: " + cat04.getFlightNo2() + "\n";
				}
				if (cat04.getCarrierFlight2TableNo986() != null && !cat04.getCarrierFlight2TableNo986().isEmpty()) {
					FlightTable ft = atpcoRecord3CategoryCustomRepository.findRecord3FlightTable(cat04.getCarrierFlight2TableNo986());
					String ft986 = AtpcoDataConverterUtil.convertFlightTableToText(ft);
					if (!ft986.isEmpty()) {
						result += "\tCARRIER/FLIGHT TABLE 2: \n";
						result += ft986 + "\n";
					}
				}
				if (cat04.getRelationshipIndicator2And3() != null && !cat04.getRelationshipIndicator2And3().isEmpty()) {
					String ri = AtpcoDataConverterUtil.convertFlightApplicationBetweenCarrierIndicatorToName(cat04.getRelationshipIndicator2And3());
					if (!ri.isEmpty()) {
						result += ri + "\n";
					} else {
						result += "INVALID result APPLICATION RELATIONSHIP INDICATOR\n";
					}
				}
				if (cat04.getCarrierCode3() != null && !cat04.getCarrierCode3().isEmpty()) {
					result += "\tCARRIER 3: " + cat04.getCarrierCode3() + "\n";
				}
				if (cat04.getFlightNo3() != null && !cat04.getFlightNo3().isEmpty()) {
					result += "\tFLIGHT NO 3: " + cat04.getFlightNo3() + "\n";
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "005":
				AtpcoRecord3Cat05 cat05 = (AtpcoRecord3Cat05) catObj;
				String firstAdv = "";
				if (cat05.getAdvancedReservationFirstTimeOfDay() != null && !cat05.getAdvancedReservationFirstTimeOfDay().isEmpty()) {
					firstAdv += cat05.getAdvancedReservationFirstTimeOfDay() + " ";
				}
				if (cat05.getAdvancedReservationFirstPeriod() != null && !cat05.getAdvancedReservationFirstPeriod().isEmpty()) {
					firstAdv += cat05.getAdvancedReservationFirstPeriod() + " ";			
				}
				if (cat05.getAdvancedReservationFirstUnit() != null && !cat05.getAdvancedReservationFirstUnit().isEmpty()) {
					firstAdv += AtpcoDataConverterUtil.convertUnitToName(cat05.getAdvancedReservationFirstUnit()) + " ";	
				}
				if (!firstAdv.isEmpty()) {
					result += "\tRESERVATION NO EARLIER THAN " + firstAdv + " BEFORE DEPARTURE\n";
				}
				String lastAdv = "";
				if (cat05.getAdvancedReservationLastTimeOfDay() != null && !cat05.getAdvancedReservationLastTimeOfDay().isEmpty()) {
					lastAdv += cat05.getAdvancedReservationLastTimeOfDay() + " ";
				}
				if (cat05.getAdvancedReservationLastPeriod() != null && !cat05.getAdvancedReservationLastPeriod().isEmpty()) {
					lastAdv += cat05.getAdvancedReservationLastPeriod() + " ";			
				}
				if (cat05.getAdvancedReservationLastUnit() != null && !cat05.getAdvancedReservationLastUnit().isEmpty()) {
					lastAdv += AtpcoDataConverterUtil.convertUnitToName(cat05.getAdvancedReservationLastUnit()) + " ";	
				}
				if (!lastAdv.isEmpty()) {
					result += "\tRESERVATION AT LEAST " + lastAdv + " BEFORE DEPARTURE\n";
				}
				String tkt = "";
				if (cat05.getAdvancedTicketingTimeOfDay() != null && !cat05.getAdvancedTicketingTimeOfDay().isEmpty()) {
					tkt += cat05.getAdvancedTicketingTimeOfDay() + " ";
				}
				if (cat05.getAdvancedTicketingPeriod() != null && !cat05.getAdvancedTicketingPeriod().isEmpty()) {
					tkt += cat05.getAdvancedTicketingPeriod() + " ";			
				}
				if (cat05.getAdvancedTicketingUnit1() != null && !cat05.getAdvancedTicketingUnit1().isEmpty()) {
					tkt += AtpcoDataConverterUtil.convertUnitToName(cat05.getAdvancedTicketingUnit1()) + " ";	
				}
				if (!tkt.isEmpty()) {
					result += "\tTICKETING MUST BE WITHIN " + tkt + " OF MAKING RESERVATION\n";
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "006":
				AtpcoRecord3Cat06 cat06 = (AtpcoRecord3Cat06) catObj;
				String min = "";
				if (cat06.getOriginDayOfWeek() != null && !cat06.getOriginDayOfWeek().isEmpty()) {
					min += cat06.getOriginDayOfWeek() + " ";
				}
				if (cat06.getTimeOfDay() != null && !cat06.getTimeOfDay().isEmpty()) {
					min += cat06.getTimeOfDay() + " ";			
				}
				if (cat06.getMinStay() != null && !cat06.getMinStay().isEmpty()) {
					min += cat06.getMinStay() + " ";	
				}
				if (cat06.getUnit() != null && !cat06.getUnit().isEmpty()) {
					min += AtpcoDataConverterUtil.convertUnitToName(cat06.getUnit()) + " ";	
				}
				if (!min.isEmpty()) {
					result += "\tMINIMUM STAY: " + min + "\n\n";
				}
				break;
			case "007":
				AtpcoRecord3Cat07 cat07 = (AtpcoRecord3Cat07) catObj;
				String max = "";
				if (cat07.getTimeOfDay() != null && !cat07.getTimeOfDay().isEmpty()) {
					max += cat07.getTimeOfDay() + " ";			
				}
				if (cat07.getMaxStay() != null && !cat07.getMaxStay().isEmpty()) {
					max += cat07.getMaxStay() + " ";	
				}
				if (cat07.getUnit() != null && !cat07.getUnit().isEmpty()) {
					max += AtpcoDataConverterUtil.convertUnitToName(cat07.getUnit()) + " ";	
				}
				if (!max.isEmpty()) {
					result += "\tMAXIMUM STAY: " + max + "\n\n";
				}
				break;
			case "008":
				AtpcoRecord3Cat08 cat08 = (AtpcoRecord3Cat08) catObj;
				String stopover = "";
				if (cat08.getNo_of_stops_min() != null && !cat08.getNo_of_stops_min().isEmpty()) {
					stopover += "\tMINIMUM " + cat08.getNo_of_stops_min() + " STOPOVERS\n";
				}
				if (cat08.getNo_of_stops_max() != null && !cat08.getNo_of_stops_max().isEmpty()) {
					if (cat08.getNo_of_stops_max().contentEquals("XX")) {
						stopover += "\tUNLIMITED STOPOVERS PERMITTED\n";
					} else {
						stopover += "\tMAXIMUM " + cat08.getNo_of_stops_max() + " STOPOVERS\n";
					}
				}
				String first = "";
				if (cat08.getCharges_1_first_no() != null && !cat08.getCharges_1_first_no().isEmpty()) {
					first += "\tFIRST " + cat08.getCharges_1_first_no() + " STOPOVERS ";
				}
				if (cat08.getCharges_1_first_amt() != null && cat08.getCharges_1_first_amt().bigDecimalValue().doubleValue() != 0.0) {
					if (cat08.getCharges_1_cur() != null && !cat08.getCharges_1_cur().isEmpty()) {
						first += "\tAT " + cat08.getCharges_1_cur() + " " + cat08.getCharges_1_first_amt().bigDecimalValue().doubleValue() + " EACH";
					}
				}
				if (!first.isEmpty()) {
					stopover += first + "\n";
				}
				String addl = "";
				if (cat08.getCharges_1_addl_no() != null && !cat08.getCharges_1_addl_no().isEmpty()) {
					addl += "\tADDITIONAL " + cat08.getCharges_1_addl_no() + " STOPOVERS ";
				}
				if (cat08.getCharges_1_addl_amt() != null && cat08.getCharges_1_addl_amt().bigDecimalValue().doubleValue() != 0.0) {
					if (cat08.getCharges_1_cur() != null && !cat08.getCharges_1_cur().isEmpty()) {
						addl += "\tAT " + cat08.getCharges_1_cur() + " " + cat08.getCharges_1_addl_amt().bigDecimalValue().doubleValue() + " EACH";
					}
				}
				if (!addl.isEmpty()) {
					stopover += addl + "\n";
				}
				if (!stopover.isEmpty()) {
					result += stopover + "\n";
				}
				break;
			case "009":
				AtpcoRecord3Cat09 cat09 = (AtpcoRecord3Cat09) catObj;
				String transfer = "";
				if (cat09.getNo_of_transfers_min() != null && !cat09.getNo_of_transfers_min().isEmpty()) {
					transfer += "\tMINIMUM " + cat09.getNo_of_transfers_min() + " TRANSFERS\n";
				}
				if (cat09.getNo_of_transfers_max() != null && !cat09.getNo_of_transfers_max().isEmpty()) {
					if (cat09.getNo_of_transfers_max().contentEquals("XX")) {
						transfer += "\tUNLIMITED TRANSFERS PERMITTED\n";
					} else {
						transfer += "\tMAXIMUM " + cat09.getNo_of_transfers_max() + " TRANSFERS\n";
					}
				}
				if (!transfer.isEmpty()) {
					result += transfer + "\n";
				}
				break;
			case "101":
				AtpcoRecord3Cat101 cat101 = (AtpcoRecord3Cat101) catObj;
				if (cat101.getAppl() != null) {
					if (cat101.getAppl().trim().isEmpty()) {
						result += "\tSINGLE AND DOUBLE OPEN JAWS PERMITTED\n";
					} else if (cat101.getAppl().contentEquals("S")) {
						result += "\tSINGLE OPEN JAW PERMITTED";
					} else if (cat101.getAppl().contentEquals("D")) {
						result += "\tDOUBLE OPEN JAW PERMITTED";
					}
				}
				if (cat101.getSame() != null && cat101.getSame().contentEquals("X")) {
					result += "\tOPEN JAW COMBINATIONS PERMITTED WITH FARE OWNING CARRIER\n";
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "102":
				AtpcoRecord3Cat102 cat102 = (AtpcoRecord3Cat102) catObj;
				if (cat102.getSame() != null && cat102.getSame().contentEquals("X")) {
					result += "\tROUND TRIP COMBINATIONS PERMITTED WITH ANY CARRIER\n";
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "103":
				AtpcoRecord3Cat103 cat103 = (AtpcoRecord3Cat103) catObj;
				if (cat103.getSame() != null && cat103.getSame().contentEquals("X")) {
					result += "\tCIRCLE TRIP COMBINATIONS PERMITTED WITH ANY CARRIER\n";
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "104":
				AtpcoRecord3Cat104 cat104 = (AtpcoRecord3Cat104) catObj;
				if (cat104.getDom() != null && !cat104.getDom().isEmpty()) {
					String dom = AtpcoDataConverterUtil.convertCombination104OptionToName(cat104.getDom());
					if (!dom.isEmpty()) {
						result += "\tEND TO END COMBINATIONS " + dom + " WITH DOMESTIC FARES\n";
					}
				}
				if (cat104.getIntl() != null && !cat104.getIntl().isEmpty()) {
					String intl = AtpcoDataConverterUtil.convertCombination104OptionToName(cat104.getIntl());
					if (!intl.isEmpty()) {
						result += "\tEND TO END COMBINATIONS " + intl + " WITH INTERNATIONAL FARES\n";
					}
				}
				if (cat104.getSame() != null && cat104.getSame().contentEquals("X")) {
					result += "\tEND TO END COMBINATIONS PERMITTED WITH FARE OWNING CARRIER\n";
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "106":
				AtpcoRecord3Cat106 cat106 = (AtpcoRecord3Cat106) catObj;
				if (cat106.getCarriers() != null) {
					for (AtpcoRecord3Cat106Carriers car:cat106.getCarriers()) {
						String appl = "";
						if (car.getAppl() != null && !car.getAppl().isEmpty()) {
							appl = AtpcoDataConverterUtil.convertCombination106Application(car.getAppl());
						}
						String carrier = "";
						for (String carr:car.getCarriers()) {
							carrier += carr + ", ";
						}
						if (!appl.isEmpty()) {
							result += "\tCOMBINATIONS " + appl + (!carrier.isEmpty() ? " WITH CARRIERS " + carrier.substring(0, carrier.length() - 2) + "\n" : "\n");
						}
					}
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "107":
				AtpcoRecord3Cat107 cat107 = (AtpcoRecord3Cat107) catObj;
				String ruletar = "";
				for (AtpcoRecord3Cat107Tarrif tar:cat107.getTarrif()) {
					if (tar.getRule_no() != null && !tar.getRule_no().isEmpty()) {
						ruletar += "\t\tRULE " + tar.getRule_no();
					}
					if (tar.getTar_no() != null && !tar.getTar_no().isEmpty()) {
						ruletar += " IN TARIFF " + tar.getTar_no() + "\n";
					}
				}
				if (!ruletar.isEmpty()) {
					result += "\tCOMBINATIONS PERMITTED WITH THE FOLLOWING: \n" + ruletar;
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "108":
				AtpcoRecord3Cat108 cat108 = (AtpcoRecord3Cat108) catObj;
				String fareclass = "";
				for (AtpcoRecord3Cat108FareClassType fc:cat108.getFare_class_type()) {
					String type = "";
					if (fc.getType() != null && !fc.getType().isEmpty()) {
						type = AtpcoDataConverterUtil.convertCombination108TypeToName(fc.getType());
					}
					if (fc.getFare_class_type() != null && !fc.getFare_class_type().isEmpty()) {
						fareclass += "\t\t" + (!type.isEmpty() ? type + " " : "") + fc.getFare_class_type() + "\n";
					}
				}
				if (!fareclass.isEmpty()) {
					result += "\tCOMBINATIONS PERMITTED WITH THE FOLLOWING: \n" + fareclass;
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "109":
				AtpcoRecord3Cat109 cat109 = (AtpcoRecord3Cat109) catObj;
				String geospec = "";
				for (AtpcoRecord3Cat109OpenJaw oj:cat109.getOpen_jaw_sets()) {
					String set = "";
					if (oj.getSet_1_geo_type_1() != null && !oj.getSet_1_geo_type_1().isEmpty()) {
						String type = AtpcoDataConverterUtil.convertCombination109TypeToName(oj.getSet_1_geo_type_1());
						if (!type.isEmpty()) {
							set += type;
						}
					}
					if (oj.getSet_1_geo_loc_1() != null && !oj.getSet_1_geo_loc_1().isEmpty()) {
						set += (!set.isEmpty() ? " " : "") + oj.getSet_1_geo_loc_1();
					}
					if (oj.getSet_1_geo_type_2() != null && !oj.getSet_1_geo_type_2().isEmpty()) {
						String type = AtpcoDataConverterUtil.convertCombination109TypeToName(oj.getSet_1_geo_type_2());
						if (!type.isEmpty()) {
							set += (!set.isEmpty() ? " AND " : "") + type;
						}
					}
					if (oj.getSet_1_geo_loc_2() != null && !oj.getSet_1_geo_loc_2().isEmpty()) {
						set += (!set.isEmpty() ? " " : "") + oj.getSet_1_geo_loc_2();
					}
					if (!set.isEmpty()) {
						geospec += "\tOPEN JAWS PERMITTED IN " + set + "\n";
					}
					if (oj.getSet_2_geo_type_1() != null && !oj.getSet_2_geo_type_1().isEmpty()) {
						String type = AtpcoDataConverterUtil.convertCombination109TypeToName(oj.getSet_2_geo_type_1());
						if (!type.isEmpty()) {
							set += type;
						}
					}
					if (oj.getSet_2_geo_loc_1() != null && !oj.getSet_2_geo_loc_1().isEmpty()) {
						set += (!set.isEmpty() ? " " : "") + oj.getSet_2_geo_loc_1();
					}
					if (oj.getSet_2_geo_type_2() != null && !oj.getSet_2_geo_type_2().isEmpty()) {
						String type = AtpcoDataConverterUtil.convertCombination109TypeToName(oj.getSet_2_geo_type_2());
						if (!type.isEmpty()) {
							set += (!set.isEmpty() ? " AND " : "") + type;
						}
					}
					if (oj.getSet_2_geo_loc_2() != null && !oj.getSet_2_geo_loc_2().isEmpty()) {
						set += (!set.isEmpty() ? " " : "") + oj.getSet_2_geo_loc_2();
					}
					if (!set.isEmpty()) {
						geospec += "\tOPEN JAWS PERMITTED IN " + set + "\n";
					}
				}
				if (!geospec.isEmpty()) {
					result += geospec + "\n\n";
				}
				break;
			case "011":
				AtpcoRecord3Cat11 cat11 = (AtpcoRecord3Cat11) catObj;
				String blackout = "";
				String bstart = AtpcoDataConverterUtil.convertSeparatedDateIntoTextDate(cat11.getDate_start_dd(), cat11.getDate_start_mm(), cat11.getDate_start_yy());
				String bend = AtpcoDataConverterUtil.convertSeparatedDateIntoTextDate(cat11.getDate_stop_dd(), cat11.getDate_stop_mm(), cat11.getDate_stop_yy());
				if (!bstart.isEmpty()) {
					blackout += bstart;
				}
				if (!bend.isEmpty()) {
					result += "\tTRAVEL IS NOT PERMITTED ";
					if (cat11.getAppl() != null) {
						if (cat11.getAppl().contentEquals("D")) {
							result += "ON " + blackout + " AND " + bend + "\n\n";
						} else if (cat11.getAppl().contentEquals("R")) {
							result += "FROM " + blackout + " THROUGH " + bend + "\n\n";
						}
					}
				}
				break;
			case "012":
				AtpcoRecord3Cat12 cat12 = (AtpcoRecord3Cat12) catObj;
				String sudate = "";
				String sustart = AtpcoDataConverterUtil.convertSeparatedDateIntoTextDate(cat12.getDate_start_dd(), cat12.getDate_start_mm(), cat12.getDate_start_yy());
				String suend = AtpcoDataConverterUtil.convertSeparatedDateIntoTextDate(cat12.getDate_stop_dd(), cat12.getDate_stop_mm(), cat12.getDate_stop_yy());
				if (!sustart.isEmpty()) {
					sudate += "\tSURCHARGE START/END DATE: " + sustart;
				}
				if (!suend.isEmpty()) {
					sudate += " / " + suend;
				}
				if (!sudate.isEmpty()) {
					result += sudate + "\n";
				}
				String sutime = "";
				if (cat12.getTime_of_day_start() != null && !cat12.getTime_of_day_start().isEmpty()) {
					sutime += "\tSURCHARGE START/END TIME: " + cat12.getTime_of_day_start();
				}
				if (cat12.getTime_of_day_stop() != null && !cat12.getTime_of_day_stop().isEmpty()) {
					sutime += " / " + cat12.getTime_of_day_stop();
				}
				if (!sutime.isEmpty()) {
					result += sutime + "\n";
				}
				if (cat12.getDay_of_week() != null && !cat12.getDay_of_week().isEmpty()) {
					String days = AtpcoDataConverterUtil.convertDayInNumberToName(cat12.getDay_of_week());
					result += "\tAPPLICABLE DAYS: " + days + "\n";
				}
				if (cat12.getSur_type() != null && !cat12.getSur_type().isEmpty()) {
					AtpcoMasterSurchargeCode sc = surchargeCodeRepository.findOneByCode(cat12.getSur_type().trim());
					if (sc != null && sc.getType() != null) {
						result += "\tSURCHARGE TYPE: " + sc.getType().toUpperCase() + "\n";
					}
				}
				boolean isNegativeAmount = false;
				String charge = "";
				if (cat12.getCharges_first_cur() != null && !cat12.getCharges_first_cur().isEmpty()) {
					charge += cat12.getCharges_first_cur() + " ";
				}
				if (cat12.getCharges_first_amt() != null) {
					charge += cat12.getCharges_first_amt().bigDecimalValue().doubleValue();
				}
				if (cat12.getSurcharge_appl() != null && !cat12.getSurcharge_appl().isEmpty()) {
					String appl = AtpcoDataConverterUtil.convertSurchargeApplicationToName(cat12.getSurcharge_appl());
					isNegativeAmount = AtpcoDataConverterUtil.checkSurchargeApplicationIsNegativeAmount(cat12.getSurcharge_appl());
					
					result += "\tSURCHARGE OF " + (isNegativeAmount ? "" : "- ") + charge + " APPLIES TO " + appl + "\n";
				}
				if (cat12.getRbd() != null && !cat12.getRbd().isEmpty()) {
					result += "\tSURCHARGE APPLIES FOR TRAVEL IN RBD '" + cat12.getRbd() + "'\n";
				}
				if (!result.isEmpty()) {
					result +='\n';
				}
				break;
			case "013":
				AtpcoRecord3Cat13 cat13 = (AtpcoRecord3Cat13) catObj;
				String acc = "";
				if (cat13.getAcc_trvl_sect_all() != null && cat13.getAcc_trvl_sect_all().contentEquals("X")) {
					acc += "\tON ALL SECTORS\n";
				}
				if (cat13.getAcc_trvl_sect_out() != null && cat13.getAcc_trvl_sect_out().contentEquals("X")) {
					acc += "\tON OUTBOUND JOURNEY\n";
				}
				if (cat13.getAcc_trvl_sect_one() != null && cat13.getAcc_trvl_sect_one().contentEquals("X")) {
					acc += "\tON AT LEAST ONE SECTOR\n";
				}
				if (cat13.getAcc_trvl_cmpt() != null && cat13.getAcc_trvl_cmpt().contentEquals("X")) {
					acc += "\tIN THE SAME COMPARTMENT\n";
				}
				if (cat13.getAcc_trvl_rule() != null && cat13.getAcc_trvl_rule().contentEquals("X")) {
					acc += "\tFARE GOVERNED USING THE SAME RULE\n";
				}
				if (!acc.isEmpty()) {
					result += "\tPASSENGER MUST BE ACCOMPANIED: \n";
				}
				String psg = "";
				if (cat13.getAppl() != null) {
					if (cat13.getAppl().contentEquals("X")) {
						psg += "\tACCOMPANYING PASSENGER MUST NOT BE ";
					} else {
						psg += "\tACCOMPANYING PASSENGER MUST BE ";
					}
				}
				if (cat13.getPsgr_type() != null && !cat13.getPsgr_type().isEmpty()) {
					AtpcoMasterPassengerTypeCode ptc = passengerRepository.findOneByCode(cat13.getPsgr_type().trim());
					if (ptc != null && ptc.getName() != null) {
						psg += ptc.getName().toUpperCase() + " ";
					}
				}
				String age = "";
				if (cat13.getPsgr_age_min() != null && !cat13.getPsgr_age_min().isEmpty()) {
					age += "MINIMUM OF " + cat13.getPsgr_age_min() + " YEARS";
				}
				if (cat13.getPsgr_age_max() != null && !cat13.getPsgr_age_max().isEmpty()) {
					if (!age.isEmpty()) {
						age += " AND ";
					}
					age += "MAXIMUM OF " + cat13.getPsgr_age_max() + " YEARS";
				}
				if (!age.isEmpty()) {
					result += "\t" + psg + age + "\n";
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "014":
				AtpcoRecord3Cat14 cat14 = (AtpcoRecord3Cat14) catObj;
				String comm = AtpcoDataConverterUtil.convertDateObjectToText(cat14.getTravel_dates_comm());
				String exp = AtpcoDataConverterUtil.convertDateObjectToText(cat14.getTravel_dates_exp());
				String date = "";
				if (!comm.isEmpty() && !comm.contentEquals("XXXXXXXXX")) {
					date += " ON/AFTER " + comm;
				}
				if (!exp.isEmpty() && !exp.contentEquals("XXXXXXXXX")) {
					if (!date.isEmpty()) {
						date += " AND";
					}
					date += " ON/BEFORE " + exp;
				}
				if (!date.isEmpty()) {
					result += "\tVALID FOR TRAVEL COMMENCING" + date + "\n\n";
				}
				break;
			case "015":
				AtpcoRecord3Cat15 cat15 = (AtpcoRecord3Cat15) catObj;
				String resstart = AtpcoDataConverterUtil.convertDateObjectToText(cat15.getSales_dates_earliest_res());
				String resend = AtpcoDataConverterUtil.convertDateObjectToText(cat15.getSales_dates_latest_res());
				String resdate = "";
				if (!resstart.isEmpty() && !resstart.contentEquals("XXXXXXXXX")) {
					resdate += " ON/AFTER " + resstart;
				}
				if (!resend.isEmpty() && !resend.contentEquals("XXXXXXXXX")) {
					if (!resdate.isEmpty()) {
						resdate += " AND";
					}
					resdate += " ON/BEFORE " + resend;
				}
				if (!resdate.isEmpty()) {
					result += "\tRESERVATION MUST BE" + resdate + "\n";
				}
				String tktstart = AtpcoDataConverterUtil.convertDateObjectToText(cat15.getSales_dates_earliest_tktg());
				String tktend = AtpcoDataConverterUtil.convertDateObjectToText(cat15.getSales_dates_latest_tktg());
				String tktdate = "";
				if (!tktstart.isEmpty() && !tktstart.contentEquals("XXXXXXXXX")) {
					tktdate += " ON/AFTER " + tktstart;
				}
				if (!tktend.isEmpty() && !tktend.contentEquals("XXXXXXXXX")) {
					if (!tktdate.isEmpty()) {
						tktdate += " AND";
					}
					tktdate += " ON/BEFORE " + tktend;
				}
				if (!tktdate.isEmpty()) {
					result += "\tTICKETING MUST BE" + tktdate + "\n";
				}
				if (cat15.getCxr_gds_sale() != null) {
					if (cat15.getCxr_gds_sale().contentEquals("C")) {
						if (cat15.getCxr_gds_oth() != null && !cat15.getCxr_gds_oth().isEmpty()) {
							result += "\tTICKETS MUST BE SOLD BY GDS " + cat15.getCxr_gds_oth() + "\n"; 
						}
					} else if (cat15.getCxr_gds_sale().contentEquals("X")) {
						if (cat15.getCxr_gds_oth() != null && !cat15.getCxr_gds_oth().isEmpty()) {
							result += "\tTICKETS MUST BE SOLD BY AIRLINE " + cat15.getCxr_gds_oth() + "\n"; 
						}
					}
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "016":
				AtpcoRecord3Cat16 cat16 = (AtpcoRecord3Cat16) catObj;
				if (cat16.getAppl_vol() != null && !cat16.getAppl_vol().isEmpty()) {
					if (cat16.getAppl_vol().contentEquals("X")) {
						result += "\tTHE FOLLOWING RESULTS APPLIES FOR VOLUNTARY CHANGES\n";
					}
				}
				if (cat16.getAppl_inv() != null && !cat16.getAppl_inv().isEmpty()) {
					if (cat16.getAppl_inv().contentEquals("X")) {
						result += "\tTHE FOLLOWING RESULTS APPLIES FOR INVOLUNTARY CHANGES\n";
					}
				}
				if (cat16.getAppl_canx() != null && !cat16.getAppl_canx().isEmpty()) {
					if (cat16.getAppl_canx().contentEquals("X")) {
						result += "\tTHE FOLLOWING RESULTS APPLIES FOR CANCELLATIONS AND REFUNDS\n";
					}
				}
				String tkrnrf = AtpcoDataConverterUtil.convertTicketAndReservationRestrictionToName(cat16.getTkt_nrf());
				if (!tkrnrf.isEmpty()) {
					result += "\t" + tkrnrf + "\n";
				}
				String penalties = "";
				if (cat16.getAppl_canx() != null && !cat16.getAppl_canx().isEmpty()) {
					if (cat16.getAppl_canx().contentEquals("X")) {
						penalties += "\t\tCANCELLATION\n";
					}
				}
				if (cat16.getPenalties_fail() != null && !cat16.getPenalties_fail().isEmpty()) {
					if (cat16.getPenalties_fail().contentEquals("X")) {
						penalties += "\t\tFAILURE TO USE CONFIRMED SPACES\n";
					}
				}
				if (cat16.getPenalties_chrg() != null && !cat16.getPenalties_chrg().isEmpty()) {
					if (cat16.getPenalties_chrg().contentEquals("X")) {
						penalties += "\t\tCHANGE OF ITINERARY REQUIRING REISSUE OF TICKET\n";
					}
				}
				if (cat16.getPenalties_rep() != null && !cat16.getPenalties_rep().isEmpty()) {
					if (cat16.getPenalties_rep().contentEquals("X")) {
						penalties += "\t\tREPLACEMENT OF LAST TICKET/EXCHANGE ORDER\n";
					}
				}
				if (cat16.getPenalties_chgn() != null && !cat16.getPenalties_chgn().isEmpty()) {
					if (cat16.getPenalties_chgn().contentEquals("X")) {
						penalties += "\t\tCHANGE NOT REQUIRING REISSUE\n";
					}
				}
				if (cat16.getPenalties_tkt() != null && !cat16.getPenalties_tkt().isEmpty()) {
					if (cat16.getPenalties_tkt().contentEquals("X")) {
						penalties += "\t\tTICKET REFUND\n";
					}
				}
				if (cat16.getPenalties_pta() != null && !cat16.getPenalties_pta().isEmpty()) {
					if (cat16.getPenalties_pta().contentEquals("X")) {
						penalties += "\t\tUNTICKETD PTA'S\n";
					}
				}
				if (!penalties.isEmpty()) {
					result += "\tPENALTY APPLIES FOR THE FOLLOWING: \n" + penalties;
				}
				String chargesText = "";
				String chargesappl = AtpcoDataConverterUtil.convertChargesApplicationToName(cat16.getCharges_appl());
				if (!chargesappl.isEmpty()) {
					chargesText += chargesappl + " ";
				}
				String charges = "";
				if (cat16.getCharges_cur_1() != null && !cat16.getCharges_cur_1().isEmpty()) {
					charges += cat16.getCharges_cur_1() + " ";
				}
				if (cat16.getCharges_amt_1() != null && cat16.getCharges_amt_1().bigDecimalValue().doubleValue() != 0.0) {
					charges += cat16.getCharges_amt_1().bigDecimalValue().doubleValue();
				}
				if (cat16.getCharges_percent() != null && cat16.getCharges_percent().bigDecimalValue().doubleValue() != 0.0) {
					if (!charges.isEmpty()) {
						charges += " OR " + cat16.getCharges_percent() + "%";
					} else {
						charges += cat16.getCharges_percent() + "%";
					}
				}
				if (cat16.getCharges_h_l() != null && !cat16.getCharges_h_l().isEmpty()) {
					if (cat16.getCharges_h_l().contentEquals("H")) {
						charges += ", WHICHEVER IS HIGHER";
					} else if (cat16.getCharges_h_l().contentEquals("L")) {
						charges += ", WHICHEVER IS LOWER";
					}
				}
				if (!charges.isEmpty()) {
					chargesText += "IS " + charges;
				}
				if (!chargesText.isEmpty()) {
					result += "\t" + chargesText + "\n";
				}
				String waiver = "";
				if (cat16.getWaiver_death_of_passenger() != null && !cat16.getWaiver_death_of_passenger().isEmpty()) {
					if (cat16.getWaiver_death_of_passenger().contentEquals("X")) {
						waiver += "\t\tDEATH OF PASSENGER\n";
					}
				}
				if (cat16.getWaiver_illness_of_passenger() != null && !cat16.getWaiver_illness_of_passenger().isEmpty()) {
					if (cat16.getWaiver_illness_of_passenger().contentEquals("X")) {
						waiver += "\t\tILLNESS OF PASSENGER\n";
					}
				}
				if (cat16.getWaiver_death_of_immediate_family_member() != null && !cat16.getWaiver_death_of_immediate_family_member().isEmpty()) {
					if (cat16.getWaiver_death_of_immediate_family_member().contentEquals("X")) {
						waiver += "\t\tDEATH OF IMMEDIATE FAMILY MEMBER\n";
					}
				}
				if (cat16.getWaiver_illness_of_immediate_family_member() != null && !cat16.getWaiver_illness_of_immediate_family_member().isEmpty()) {
					if (cat16.getWaiver_illness_of_immediate_family_member().contentEquals("X")) {
						waiver += "\t\tILLNESS OF IMMEDIATE FAMILY MEMBER\n";
					}
				}
				if (cat16.getWaiver_schedule_change() != null && !cat16.getWaiver_schedule_change().isEmpty()) {
					if (cat16.getWaiver_schedule_change().contentEquals("X")) {
						waiver += "\t\tSCHEDULE CHANGE\n";
					}
				}
				if (cat16.getWaiver_ticket_upgrade() != null && !cat16.getWaiver_ticket_upgrade().isEmpty()) {
					if (cat16.getWaiver_ticket_upgrade().contentEquals("X")) {
						waiver += "\t\tTICKET UPGRADE\n";
					}
				}
				if (!waiver.isEmpty()) {
					result += "\tPENALTY WAIVED IN THE FOLLOWING CASES: \n" + waiver;
				}
				if(!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "017":
				AtpcoRecord3Cat17 cat17 = (AtpcoRecord3Cat17) catObj;
				if (cat17.getNo_hip() != null && !cat17.getNo_hip().isEmpty()) {
					if (cat17.getNo_hip().contentEquals("X")) {
						result += "\tHIGHER INTERMEDIATE POINT IS NOT PERMITTED\n";
					}
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "018": 
				AtpcoRecord3Cat18 cat18 = (AtpcoRecord3Cat18) catObj;
				if (cat18.getTicket_endorsement_text() != null && !cat18.getTicket_endorsement_text().isEmpty()) {
					result += "\t" + cat18.getTicket_endorsement_text() + "\n";
				}
				String ticketloc = AtpcoDataConverterUtil.convertEndorsementTicketLocationToName(cat18.getTkt_loc());
				if (!ticketloc.isEmpty()) {
					result += "\tENDORSEMENT TEXT MUST APPEAR ON THE " + ticketloc + "\n";
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "019":
				AtpcoRecord3Cat19 cat19 = (AtpcoRecord3Cat19) catObj;
				String fbrpsg19 = "";
				if (cat19.getFbr_passenger_type() != null && !cat19.getFbr_passenger_type().isEmpty()) {
					AtpcoMasterPassengerTypeCode ptc = passengerRepository.findOneByCode(cat19.getFbr_passenger_type().trim());
					if (ptc != null && ptc.getName() != null) {
						fbrpsg19 += ptc.getName().toUpperCase();
					}
				}
				if (!fbrpsg19.isEmpty()) {
					result += "\tTHE FOLLOWING APPLIES FOR PASSENGER TYPE " + fbrpsg19;
				}
				String initialfbrage19 = "";
				String fbrage19 = "";
				if (cat19.getFbr_age_min() != null && !cat19.getFbr_age_min().isEmpty()) {
					fbrage19 += cat19.getFbr_age_min();
					initialfbrage19 = "ABOVE ";
				}
				if (cat19.getFbr_age_max() != null && !cat19.getFbr_age_max().isEmpty()) {
					if (!fbrage19.isEmpty()) {
						fbrage19 += " - ";
						initialfbrage19 = "FROM ";
					}
					fbrage19 += cat19.getFbr_age_max();
					if (initialfbrage19.isEmpty()) {
						initialfbrage19 = "UNDER ";
					}
				}
				
				if (!fbrage19.isEmpty()) {
					result += " AGE " + initialfbrage19 + fbrage19 + "\n";
				}
				if (cat19.getFbr_percent() != null && cat19.getFbr_percent().bigDecimalValue().doubleValue() != 0.0) {
					result += "\t" + cat19.getFbr_percent() + "% OF THE ADULT FARE\n";
				}
				if (cat19.getFbr_ticketing_code() != null && !cat19.getFbr_ticketing_code().isEmpty()) {
					result += "\tBASE FARE FARE CODE PLUS " + cat19.getFbr_ticketing_code() + "\n";
				}
				if (cat19.getFbr_ticket_designator() != null && !cat19.getFbr_ticket_designator().isEmpty()) {
					result += "\tBASE FARE FARE CODE PLUS " + cat19.getFbr_ticket_designator() + "\n";
				}
				if (!result.isEmpty()) {
					result += "\n";
				}
				break;
			case "020":
				break;
			case "021":
				break;
			case "022":
				break;
			case "023":
				AtpcoRecord3Cat23 cat23 = (AtpcoRecord3Cat23) catObj;
				String addonconst = AtpcoDataConverterUtil.convertAddonConstructionToName(cat23.getAdd_on_constr());
				if (!addonconst.isEmpty()) {
					result += "\t" + addonconst + "\n\n";
				}
				break;
			case "025":
				AtpcoRecord3Cat25 cat25 = (AtpcoRecord3Cat25) catObj;
				if (cat25.getPsgr_type() != null && !cat25.getPsgr_type().isEmpty()) {
					AtpcoMasterPassengerTypeCode ptc = passengerRepository.findOneByCode(cat25.getPsgr_type().trim());
					if (ptc != null && ptc.getName() != null) {
						result += "\tTHE FOLLOWING CONDITIONS APPLY FOR PASSENGER TYPE " + ptc.getName().toUpperCase() + "\n";
					}
				}
				if (cat25.getFare_calc_mileage_min() != null && !cat25.getFare_calc_mileage_min().isEmpty()) {
					result += "\tMINIMUM MILEAGE: " + cat25.getFare_calc_mileage_min() + "\n";
				}
				if (cat25.getFare_calc_mileage_max() != null && !cat25.getFare_calc_mileage_max().isEmpty()) {
					result += "\tMAXIMUM MILEAGE: " + cat25.getFare_calc_mileage_max() + "\n";
				}
				String codeind = AtpcoDataConverterUtil.convertFareCalculationCodeIndicatorToName(cat25.getFare_calc_mileage_find());
				if (!codeind.isEmpty()) {
					result += "\tTHE RESULTING FARE IS CREATED BY " + codeind;
				}
				if (cat25.getFare_calc_mileage_percent() != null && !cat25.getFare_calc_mileage_percent().isEmpty() && !cat25.getFare_calc_mileage_percent().contentEquals("0000000")) {
					result += cat25.getFare_calc_mileage_percent() + "% OF THE BASE FARE MENTIONED BELOW";
				}
				String fcamount = "";
				if (cat25.getFare_calc_fare_amt_1() != null && cat25.getFare_calc_fare_amt_1().doubleValue() != 0.0) {
					if (cat25.getFare_calc_fare_cur_1() != null && !cat25.getFare_calc_fare_cur_1().isEmpty()) {
						fcamount += cat25.getFare_calc_fare_cur_1() + " ";
					}
					
					fcamount += cat25.getFare_calc_fare_amt_1().doubleValue();
				}
				if (!fcamount.isEmpty()) {
					result += "\t" + fcamount + "\n";
				}
				if (cat25.getResulting_fare_owrt() != null && !cat25.getResulting_fare_owrt().isEmpty()) {
					String owrt = AtpcoDataConverterUtil.convertOwrtToName(cat25.getResulting_fare_owrt());
					if (!owrt.isEmpty()) {
						result += "\tOW/RT: " + owrt + "\n"; 
					}
				}
				if (cat25.getResulting_fare_global() != null && !cat25.getResulting_fare_global().isEmpty()) {
					result += "\tGLOBAL: " + cat25.getResulting_fare_global() + "\n";
				}
				if (cat25.getResulting_fare_routing_tarrif() != null && !cat25.getResulting_fare_routing_tarrif().isEmpty()) {
					result += "\tROUTING TARIFF: " + cat25.getResulting_fare_routing_tarrif() + "\n";
				}
				if (cat25.getResulting_fare_routing_number() != null && !cat25.getResulting_fare_routing_number().isEmpty()) {
					result += "\tROUTING NUMBER: " + cat25.getResulting_fare_routing_number() + "\n";
				}
				if (cat25.getResulting_fare_fare_class() != null && !cat25.getResulting_fare_fare_class().isEmpty()) {
					result += "\tFARE CLASS: " + cat25.getResulting_fare_fare_class() + "\n";
				}
				if (cat25.getResulting_fare_type_fare() != null && !cat25.getResulting_fare_type_fare().isEmpty()) {
					result += "\tFARE TYPE: " + cat25.getResulting_fare_type_fare() + "\n";
				}
				if (cat25.getResulting_fare_type_season() != null && !cat25.getResulting_fare_type_season().isEmpty()) {
					result += "\tSEASON: " + cat25.getResulting_fare_type_season() + "\n";
				}
				if (cat25.getResulting_fare_type_day_of_week() != null && !cat25.getResulting_fare_type_day_of_week().isEmpty()) {
					result += "\tDAY OF WEEK: " + cat25.getResulting_fare_type_day_of_week() + "\n";
				}
				if (cat25.getResulting_fare_disc_cat() != null && !cat25.getResulting_fare_disc_cat().isEmpty()) {
					result += "\tDIS CAT: " + cat25.getResulting_fare_disc_cat() + "\n";
				}
				if (cat25.getResulting_fare_prime_rbd_1() != null && !cat25.getResulting_fare_prime_rbd_1().isEmpty()) {
					result += "\tPRIME RBD: " + cat25.getResulting_fare_prime_rbd_1() + "\n";
				}
				if (cat25.getResulting_fare_ticketing_code() != null && !cat25.getResulting_fare_ticketing_code().isEmpty()) {
					result += "\tTICKETING CODE: " + cat25.getResulting_fare_ticketing_code() + "\n";
				}
				if (cat25.getResulting_fare_ticket_designator() != null && !cat25.getResulting_fare_ticket_designator().isEmpty()) {
					result += "\tTICKET DESIGNATOR: " + cat25.getResulting_fare_ticket_designator() + "\n";
				}
				if (cat25.getResulting_fare_ticket_designator() != null && !cat25.getResulting_fare_ticket_designator().isEmpty()) {
					result += "\tTICKET DESIGNATOR: " + cat25.getResulting_fare_ticket_designator() + "\n";
				}
				if (cat25.getBase_tbl_no_989() != null && !cat25.getBase_tbl_no_989().isEmpty() && !cat25.getBase_tbl_no_989().contentEquals("00000000")) {
					BaseFareTable bft = atpcoRecord3CategoryCustomRepository.findRecord3BaseFareTable(cat25.getBase_tbl_no_989());
					if (bft != null) {
						String bf = "";
						if (bft.getOwrt() != null && !bft.getOwrt().isEmpty()) {
							String owrt = AtpcoDataConverterUtil.convertOwrtToName(bft.getOwrt());
							if (!owrt.isEmpty()) {
								bf += "\t\tOW/RT: " + owrt + "\n";
							}
						}
						if (bft.getGlobal() != null && !bft.getGlobal().isEmpty()) {
							bf += "\t\tGLOBAL: " + bft.getGlobal() + "\n"; 
						}
						if (bft.getCarrierCode() != null && !bft.getCarrierCode().isEmpty()) {
							bf += "\t\tCARRIER CODE: " + bft.getCarrierCode() + "\n"; 
						}
						if (bft.getPubCalc() != null && !bft.getPubCalc().isEmpty()) {
							bf += "\t\tPUB/CALC: " + bft.getPubCalc() + "\n"; 
						}
						if (bft.getRuleTariff() != null && !bft.getRuleTariff().isEmpty()) {
							bf += "\t\tRULE TARIFF: " + bft.getRuleTariff() + "\n"; 
						}
						if (bft.getRuleNo() != null && !bft.getRuleNo().isEmpty()) {
							bf += "\t\tRULE NO: " + bft.getRuleNo() + "\n"; 
						}
						if (bft.getFareClass() != null && !bft.getFareClass().isEmpty()) {
							bf += "\t\tFARE CLASS: " + bft.getFareClass() + "\n"; 
						}
						if (bft.getFareType() != null && !bft.getFareType().isEmpty()) {
							bf += "\t\tFARE TYPE: " + bft.getFareType() + "\n"; 
						}
						if (bft.getPassengerType() != null && !bft.getPassengerType().isEmpty()) {
							bf += "\t\tPASSENGER TYPE: " + bft.getPassengerType() + "\n"; 
						}
						if (bft.getSeasonType() != null && !bft.getSeasonType().isEmpty()) {
							bf += "\t\tSEASON TYPE: " + bft.getSeasonType() + "\n"; 
						}
						if (bft.getDayType() != null && !bft.getDayType().isEmpty()) {
							bf += "\t\tDAY TYPE: " + bft.getDayType() + "\n"; 
						}
						if (bft.getPricingCategoryType() != null && !bft.getPricingCategoryType().isEmpty()) {
							bf += "\t\tPRICING CATEGORY TYPE: " + bft.getPricingCategoryType() + "\n"; 
						}
						if (bft.getRoutingNo() != null && !bft.getRoutingNo().isEmpty()) {
							bf += "\t\tROUTING NO: " + bft.getRoutingNo() + "\n"; 
						}
						if (bft.getFootnote() != null && !bft.getFootnote().isEmpty()) {
							bf += "\t\tFOOTNOTE: " + bft.getFootnote() + "\n"; 
						}
						if (bft.getRbd() != null && !bft.getRbd().isEmpty()) {
							bf += "\t\tRBD: " + bft.getRbd() + "\n"; 
						}
						if (bft.getCurrency() != null && !bft.getCurrency().isEmpty()) {
							bf += "\t\tCURRENCY: " + bft.getCurrency() + "\n"; 
						}
						if (bft.getMinFare() != null && bft.getMinFare().getLow() != 0) {
							bf += "\t\tMIN FARE: " + bft.getMinFare().getLow() + "\n"; 
						}
						if (bft.getMaxFare() != null && bft.getMaxFare().getLow() != 0) {
							bf += "\t\tMAX FARE: " + bft.getMaxFare().getLow() + "\n"; 
						}
						if (!bf.isEmpty()) {
							result += "\n\tBASE FARE TABLE\n" + bf + "\n";
						}
					}
				}
				break;
			case "026":
				AtpcoRecord3Cat26 cat26 = (AtpcoRecord3Cat26) catObj;
				String group = "";
				if (cat26.getSize_min() != null && !cat26.getSize_min().isEmpty()) {
					group += "\tMINIMUM GROUP SIZE: " + cat26.getSize_min() + "\n";
				}
				if (cat26.getSize_max() != null && !cat26.getSize_max().isEmpty()) {
					group += "\tMAXIMUM GROUP SIZE: " + cat26.getSize_max() + "\n";
				}
				if (group.isEmpty()) {
					result += group + "\n";
				}
				break;
			case "027":
				AtpcoRecord3Cat27 cat27 = (AtpcoRecord3Cat27) catObj;
				String tour = "";
				if (cat27.getType() != null && !cat27.getType().isEmpty()) {
					AtpcoMasterTourTypeCode tourType = tourTypeCodeRepository.findOneByCode(cat27.getType().trim());
					tour += "\tTOUR TYPE: " + cat27.getType() + " - " + tourType.getTourType() + "\n";
				}
				if (cat27.getTour_no() != null && !cat27.getTour_no().isEmpty()) {
					tour += "\tTOUR NO: " + cat27.getTour_no() + "\n";
				}
				if (!tour.isEmpty()) {
					result += tour + "\n";
				}
				break;
			case "028":
				break;
			case "029":
				break;
			case "031":
				break;
			case "033":
				break;
			case "035":
				break;
			case "050":
				AtpcoRecord3Cat50 cat50 = (AtpcoRecord3Cat50) catObj;
				if (cat50.getApplication_title() != null && !cat50.getApplication_title().isEmpty()) {
					result += "\tAPPLICATION TITLE: " + cat50.getApplication_title() + "\n\n";
				}
				break;
			}
		} catch (Exception e) {
			result = e.getMessage().toUpperCase();
		}
		
		return result;
	}
}
