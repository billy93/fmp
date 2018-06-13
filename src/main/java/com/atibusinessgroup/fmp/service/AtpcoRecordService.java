package com.atibusinessgroup.fmp.service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CategoryType;
import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.SurchargeCode;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat01;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat02;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat03;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat04;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat05;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat06;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat07;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat08;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat09;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat11;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat12;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord3CategoryWithDataTable;
import com.atibusinessgroup.fmp.domain.dto.CategoryAttribute;
import com.atibusinessgroup.fmp.domain.dto.CategoryAttributeObject;
import com.atibusinessgroup.fmp.domain.dto.CategoryObject;
import com.atibusinessgroup.fmp.domain.dto.CategoryTextFormatAndAttribute;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.domain.dto.DateTable;
import com.atibusinessgroup.fmp.domain.dto.FlightTable;
import com.atibusinessgroup.fmp.domain.dto.Record3ReflectionObject;
import com.atibusinessgroup.fmp.domain.dto.TextTable;
import com.atibusinessgroup.fmp.repository.SurchargeCodeRepository;
import com.atibusinessgroup.fmp.repository.custom.AtpcoRecord3CategoryCustomRepository;
import com.atibusinessgroup.fmp.service.util.AtpcoDataConverterUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;

@Service
public class AtpcoRecordService {
	
	private final AtpcoRecord3CategoryCustomRepository atpcoRecord3CategoryCustomRepository;
	private final SurchargeCodeRepository surchargeCodeRepository;
	private final MongoTemplate mongoTemplate;

	public AtpcoRecordService(AtpcoRecord3CategoryCustomRepository atpcoRecord3CategoryCustomRepository, SurchargeCodeRepository surchargeCodeRepository,
			MongoTemplate mongoTemplate) {
		this.atpcoRecord3CategoryCustomRepository = atpcoRecord3CategoryCustomRepository;
		this.surchargeCodeRepository = surchargeCodeRepository;
		this.mongoTemplate = mongoTemplate;
	}

	public boolean compareMatchingFareAndRecord(String fGeoType1, String fGeoLoc1, String fGeoType2, String fGeoLoc2,
			String fOwrt, String fRoutingNo, String fFootnote, Object fEffectiveDate, Object fDiscontinueDate,
			String rGeoType1, String rGeoLoc1, String rGeoType2, String rGeoLoc2, String rOwrt, String rRoutingNo,
			String rFootnote, Object rEffectiveDate, Object rDiscontinueDate) {
		boolean match = false;

		if (compareRoutingNo(fRoutingNo, rRoutingNo) && compareOwrt(fOwrt, rOwrt)
				&& compareFootnote(fFootnote, rFootnote)) {
			match = true;
			// System.out.println("matched");
		}

		return match;
	}

	public boolean compareMatchingFareAndRecord(String fGeoType1, String fGeoLoc1, String fGeoType2, String fGeoLoc2,
			String fFareClass, String fFareType, String fSeasonType, String fDayOfWeekType, String fOwrt,
			String fRoutingNo, String fFootnote, Object fEffectiveDate, Object fDiscontinueDate, String rGeoType1,
			String rGeoLoc1, String rGeoType2, String rGeoLoc2, String rFareClass, String rFareType, String rSeasonType,
			String rDayOfWeekType, String rOwrt, String rRoutingNo, String rFootnote, Object rEffectiveDate,
			Object rDiscontinueDate) {
		boolean match = false;

		if (compareMatchingFareAndRecord(fGeoType1, fGeoLoc1, fGeoType2, fGeoLoc2, fOwrt, fRoutingNo, fFootnote,
				fEffectiveDate, fDiscontinueDate, rGeoType1, rGeoLoc1, rGeoType2, rGeoLoc2, rOwrt, rRoutingNo,
				rFootnote, rEffectiveDate, rDiscontinueDate) && compareFareClass(fFareClass, rFareClass)
				&& compareFareType(fFareType, rFareType) && compareSeasonType(fSeasonType, rSeasonType)
				&& compareDayOfWeekType(fDayOfWeekType, rDayOfWeekType)) {
			match = true;
			// System.out.println("matched");
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
		
//		System.out.println("rtg no: " + frn + ", " + rrn + ", " + match);
		
		return match;
	}

	public boolean compareFareClass(String fFareClass, String rFareClass) {
		boolean match = true;
		
//		if (fFareClass != null && rFareClass != null) {
//			if (rFareClass.contentEquals("") || fFareClass.contentEquals(rFareClass)) {
//				match = true;
//			}
//		} else {
//			match = true;
//		}
//
//		System.out.println("fare class: " + fFareClass + ", " + rFareClass + ", " + match);
		
		return match;
	}

	public boolean compareFareType(String fFareType, String rFareType) {
		boolean match = true;

		if (fFareType != null && rFareType != null) {
			if (rFareType.contentEquals("") || fFareType.contentEquals(rFareType)) {
				match = true;
			}
		} else {
			match = true;
		}

//		System.out.println("fare type: " + fFareType + ", " + rFareType + ", " + match);
		
		return match;
	}

	public boolean compareSeasonType(String fSeasonType, String rSeasonType) {
		boolean match = false;
		
		if (fSeasonType != null && rSeasonType != null) {
			if (rSeasonType.contentEquals("") || fSeasonType.contentEquals(rSeasonType)) {
				match = true;
			}
		} 
		
//		System.out.println("season type: " + fSeasonType + ", " + rSeasonType + ", " + match);
		
		return match;
	}

	public boolean compareDayOfWeekType(String fDayOfWeekType, String rDayOfWeekType) {
		boolean match = true;

		if (fDayOfWeekType != null && rDayOfWeekType != null) {
			if (rDayOfWeekType.contentEquals("") || fDayOfWeekType.contentEquals(rDayOfWeekType)) {
				match = true;
			}
		} else {
			match = true;
		}
		
//		System.out.println("day of week type: " + fDayOfWeekType + ", " + rDayOfWeekType + ", " + match);
		
		return match;
	}

	public boolean compareOwrt(String fowrt, String rowrt) {
		boolean match = false;

		if (fowrt != null && rowrt != null) {
			if (rowrt.contentEquals("") || fowrt.contentEquals(rowrt)) {
				match = true;
			}
		} else {
			match = true;
		}
		
//		System.out.println("owrt: " + fowrt + ", " + rowrt + ", " + match);
		
		return match;
	}

	public boolean compareFootnote(String ffnt, String rfnt) {
		boolean match = true;

		if (ffnt != null && rfnt != null) {
			if (rfnt.contentEquals("") || ffnt.contentEquals(rfnt)) {
				match = true;
			}
		} else {
			match = true;
		}
		
//		System.out.println("footnote: " + ffnt + ", " + rfnt + ", " + match);
		
		return match;
	}

	public boolean compareGeoSpec(String ft1, String fl1, String ft2, String fl2, String rt1, String rl1, String rt2,
			String rl2) {
		boolean match = true;
		
//		System.out.println("geospec: (" + ft1 + ", " + fl1 + ") (" + ft2 + ", " + fl2 + ") (" + rt1 + ", " + rl1 + ") (" + rt2 + ", " + rl2 + ")" + match);
		
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
					
					if (!textFormat.isEmpty()) {
						textFormat += AtpcoDataConverterUtil.convertRelationshipToName(relationship) + "\n";
					}
					
					String dateTable = AtpcoDataConverterUtil.convertDateTableToText(dateTable994);
					if (dateTable != null) {
						textFormat += dateTable + "\n";
					}
					
					textFormat += convertCodedCategoryValueToText(category, cat);
					
					String textTable = AtpcoDataConverterUtil.convertTextTableToText(textTable996);
					if (!textTable.isEmpty()) {
						textFormat += textTable + "\n";
					}
					
					attributes.add(convertObjectToCategoryAttribute(AtpcoDataConverterUtil.convertCategoryTypeToName(type), AtpcoDataConverterUtil.convertRelationshipToName(relationship), cat));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		result.setTextFormat(textFormat);
		result.setAttributes(attributes);

		return result;
	}

	public CategoryAttribute convertObjectToCategoryAttribute(String type, String relationship, Object obj) {
		CategoryAttribute result = new CategoryAttribute();
		result.setType(type);
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
		
		result += "EFFECTIVE: " + (effStr != null ? effStr.toUpperCase() : "") + "\n";
		
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
		case "010":
			result.setClassName(classBasePackage.concat("AtpcoRecord3Cat101"));
			result.setGetTableNoMethodName("getTbl_no");
			result.setGetTextTable996NoMethodName("getText_tbl_no_996");
			result.setCollectionName(CollectionName.ATPCO_RECORD_3_CAT_010);
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
					SurchargeCode sc = surchargeCodeRepository.findOneByCode(cat12.getSur_type().trim());
					if (sc != null) {
						result += "\tSURCHARGE TYPE: " + sc.getType() + "\n";
					}
				}
				
				break;
			}
			
		} catch (Exception e) {
			result = e.getMessage().toUpperCase();
		}
		
		return result;
	}
}
