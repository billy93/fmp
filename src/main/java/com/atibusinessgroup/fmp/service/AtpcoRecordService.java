package com.atibusinessgroup.fmp.service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CategoryType;
import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat01;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord3CategoryWithDataTable;
import com.atibusinessgroup.fmp.domain.dto.CategoryAttribute;
import com.atibusinessgroup.fmp.domain.dto.CategoryAttributeObject;
import com.atibusinessgroup.fmp.domain.dto.CategoryObject;
import com.atibusinessgroup.fmp.domain.dto.CategoryTextFormatAndAttribute;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.domain.dto.DateTable;
import com.atibusinessgroup.fmp.domain.dto.Record3ReflectionObject;
import com.atibusinessgroup.fmp.domain.dto.TextTable;
import com.atibusinessgroup.fmp.repository.custom.AtpcoRecord3CategoryCustomRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;

@Service
public class AtpcoRecordService {

	private final String[] textTable996Tags = new String[] {"text_tbl_no_996", "text_table_no_996", "textTableNumber996"};
	private final String[] dateTable994Tags = new String[] {"date_tbl_no_994"};
	
	private final AtpcoRecord3CategoryCustomRepository atpcoRecord3CategoryCustomRepository;
	private final MongoTemplate mongoTemplate;

	public AtpcoRecordService(AtpcoRecord3CategoryCustomRepository atpcoRecord3CategoryCustomRepository,
			MongoTemplate mongoTemplate) {
		this.atpcoRecord3CategoryCustomRepository = atpcoRecord3CategoryCustomRepository;
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
		DateTable dateTable996 = null;
		
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
							dateTable996 = atpcoRecord3CategoryCustomRepository.findRecord3DateTable(dateTable994No);
						}
					}
					
					textFormat = convertCodedCategoryValueToText(category, cat);
					
					String text = convertTextTableToText(textTable996);
					if (text != null) {
						textFormat += text + "\n";
					}
					
					attributes.add(convertObjectToCategoryAttribute(getCategoryTypeName(type), relationship, cat, text, dateTable996));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		result.setTextFormat(textFormat);
		result.setAttributes(attributes);

		return result;
	}

	public CategoryAttribute convertObjectToCategoryAttribute(String type, String relationship, Object obj, String textTable996, DateTable dateTable994) {
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
				
				if (ArrayUtils.contains(textTable996Tags, key) && textTable996 != null) {
					attObj.setValue(textTable996);
				}
				
				if (dateTable994 != null && ArrayUtils.contains(dateTable994Tags, key)) {
					attObj.setValue(dateTable994);
				}
				
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
		
		String catType = getCategoryTypeName(type);
		
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
	
	public String convertTextTableToText(TextTable textTable996) {
		String result = null;
		
		if (textTable996 != null && textTable996.getText().size() > 0) {
			result = "";
			
			for (String line:textTable996.getText()) {
				result += "\t" + line + "\n";
			}
		}
		
		return result;
	}
	
	public String getCategoryTypeName(String type) {
		String result = null;

		if (type.contentEquals(CategoryType.RULE)) {
			result = "RULE";
		} else if (type.contentEquals(CategoryType.FOOTNOTE)) {
			result = "FOOTNOTE";
		} else if (type.contentEquals(CategoryType.GENERAL_RULE)) {
			result = "GENERAL";
		} else if (type.contentEquals(CategoryType.ALTERNATE_GENERAL_RULE)) {
			result = "ALTERNATE GENERAL";
		}

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
		
		switch (category) {
		case "001":
			AtpcoRecord3Cat01 cat = (AtpcoRecord3Cat01) catObj;
			if (cat.getPsgr_type() != null && !cat.getPsgr_type().isEmpty()) {
				result += "VALID FOR: " + cat.getPsgr_type() + "\n";
			}
			if (cat.getAge_min() != null && !cat.getAge_min().isEmpty()) {
				result += "MINIMUM AGE: " + cat.getAge_min() + "\n";
			}
			if (cat.getAge_max() != null && !cat.getAge_max().isEmpty()) {
				result += "MAXIMUM AGE: " + cat.getAge_max() + "\n";
			}
			if (cat.getAccount_code() != null && !cat.getAccount_code().isEmpty()) {
				result += "ACCOUNT CODE: " + cat.getAccount_code() + "\n\n";
			}
			break;
		}
		
		return result;
	}
}
