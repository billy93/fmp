package com.atibusinessgroup.fmp.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord3CategoryWithDataTable;
import com.atibusinessgroup.fmp.domain.dto.CategoryAttribute;
import com.atibusinessgroup.fmp.domain.dto.CategoryAttributeObject;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.resository.custom.AtpcoRecord3CategoryCustomRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;

@Service
public class AtpcoRecordService {

	private final AtpcoRecord3CategoryCustomRepository atpcoRecord3CategoryCustomRepository;
	private final MongoTemplate mongoTemplate;
	
	public AtpcoRecordService(AtpcoRecord3CategoryCustomRepository atpcoRecord3CategoryCustomRepository, MongoTemplate mongoTemplate) {
		this.atpcoRecord3CategoryCustomRepository = atpcoRecord3CategoryCustomRepository;
		this.mongoTemplate = mongoTemplate;
	}
	
	public boolean compareMatchingFareAndRecord(String fGeoType1, String fGeoLoc1, String fGeoType2, String fGeoLoc2, String fOwrt, String fRoutingNo, String fFootnote, Object fEffectiveDate, Object fDiscontinueDate, 
			String rGeoType1, String rGeoLoc1, String rGeoType2, String rGeoLoc2, String rOwrt, String rRoutingNo, String rFootnote, Object rEffectiveDate, Object rDiscontinueDate) {
		boolean match = false;
		
		System.out.println(fGeoType1 + " " + fGeoLoc1 + " " + fGeoType2 + " " + fGeoLoc2 + " " + fOwrt + " " + fRoutingNo + " " + fFootnote + " " + fEffectiveDate + " " + fDiscontinueDate);
		System.out.println(rGeoType1 + " " + rGeoLoc1 + " " + rGeoType2 + " " + rGeoLoc2 + " " + rOwrt + " " + rRoutingNo + " " + rFootnote + " " + rEffectiveDate + " " + rDiscontinueDate);
		
		if (compareRoutingNo(fRoutingNo, rRoutingNo) && compareOwrt(fOwrt, rOwrt) && compareFootnote(fFootnote, rFootnote)) {
			match = true;
			System.out.println("matched");
		}
		
		System.out.println("");
		System.out.println("");
		
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
		
		System.out.println("rtg no: " + frn + ", " + rrn + ", " + match);
		
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
		
		System.out.println("owrt: " + fowrt + ", " + rowrt + ", " + match);
		
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
		
		System.out.println("footnote: " + ffnt + ", " + rfnt + ", " + match);
		
		return match;
	}
	
	public boolean compareGeoSpec(String ft1, String fl1, String ft2, String fl2, String rt1, String rl1, String rt2, String rl2) {
		boolean match = true;
		
		return match;
	}

	public List<CategoryAttribute> getAndConvertCategoryDataTable(String category, List<DataTable> dataTables) {
		List<CategoryAttribute> result = new ArrayList<>();
		
		String baseClassPath = "com.atibusinessgroup.fmp.domain.atpco.";
		String collectionName = null, className = null, methodName = null;
		
		switch (category) {
			case "001": 
						break;
			case "002": 
						break;
			case "003": 
						break;
			case "004": className = baseClassPath.concat("AtpcoRecord3Cat04");
						methodName = "getTableNo";
						collectionName = CollectionName.ATPCO_RECORD_3_CAT_004;
						break;
			case "005": className = baseClassPath.concat("AtpcoRecord3Cat05");
						methodName = "getTableNumber";
						collectionName = CollectionName.ATPCO_RECORD_3_CAT_005;
						break;
			case "006": 
						break;
			case "007": 
						break;
			case "008": 
						break;
			case "009": 
						break;
			case "010": 
						break;
			case "011": 
						break;
			case "012": 
						break;
			case "013": 
						break;
			case "014": 
						break;
			case "015": 
						break;
			case "016": 
						break;
			case "017": 
						break;
			case "018": 
						break;
			case "019": 
						break;
			case "020": 
						break;
			case "021": 
						break;
			case "022": 
						break;
			case "023": 
						break;
			case "026": 
						break;
			case "027": 
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
						break;
		}
		
		String relationship = null;
		
		if (collectionName != null) {
			List<AtpcoRecord3CategoryWithDataTable> catdts = atpcoRecord3CategoryCustomRepository.findAllRecord3ByDataTable(collectionName, dataTables.stream().map(DataTable::getTableNo).collect(Collectors.toList()));
			
			for (AtpcoRecord3CategoryWithDataTable catdt:catdts) {
				try {
					Class<?> c = Class.forName(className);
					Object cat = convertDBObjectToPOJO(c, catdt.getCategory());
					
					Method method = c.getMethod(methodName, null);
					String tableNo = (String) method.invoke(cat);
					
					for (DataTable dt:dataTables) {
						if (dt.getTableNo().contentEquals(tableNo)) {
							relationship = dt.getRelationalIndicator();
							break;
						}
					}
					
					result.add(convertObjectToCategoryAttribute(relationship, cat));
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	private Object convertDBObjectToPOJO(Class<?> c, DBObject category) {
		Object result = null;
		
		try {
			result = mongoTemplate.getConverter().read(c, category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public CategoryAttribute convertObjectToCategoryAttribute(String relationship, Object obj) {
		CategoryAttribute result = new CategoryAttribute();
		result.setRelationship(relationship);
		
		List<CategoryAttributeObject> catAttrObjs = new ArrayList<>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String objJson = mapper.writeValueAsString(obj);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map = mapper.readValue(objJson, new TypeReference<Map<String, Object>>(){});
			
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				CategoryAttributeObject attObj = new CategoryAttributeObject();
				attObj.setKey(entry.getKey());
				attObj.setValue(entry.getValue());
				
				catAttrObjs.add(attObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result.setAttributes(catAttrObjs);
		
		return result;
	}
}
