package com.atibusinessgroup.fmp.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.dto.ProsDataFeedsHeader;
import com.atibusinessgroup.fmp.domain.dto.ProsDataFeedsRecord;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service
public class ProsDatafeedService {

	@Autowired
	MongoTemplate mongoTemplate;

	public ProsDatafeedService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public String getFaresProsDatafeed() {
		String prosFeed = "";
		String file = "ProsFeed.txt";
		Date today = new Date();
		String target = "2018-03-13";
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Date result = null;
		try {
			result = df.parse(target);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("DATEEEEE =>>> "+result);
		DBCollection collection = mongoTemplate.getCollection(CollectionName.ATPCO_FARE);
		BasicDBObject query = setQuery(today);
//		BasicDBObject query = new BasicDBObject("cxr_cd", "GA");

		DBCursor cursor = collection.find(query);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			ProsDataFeedsHeader prosHeader = setProsDataFeedsHeader(today);
			bw.write(prosHeader.build());
			bw.newLine();
			for (DBObject data : cursor) {
				AtpcoFare fares = (AtpcoFare) convertDBObjectToPOJO(AtpcoFare.class, data);
				prosFeed = setProsDataRecord(fares);
				bw.write(prosFeed);
				bw.newLine();
				System.out.println(prosFeed);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prosFeed;
	}

	private BasicDBObject setQuery(Date date) {
		BasicDBObject finalQuery = new BasicDBObject();
		BasicDBList andClause = new BasicDBList();
		
		Date startDate = getDate(date, 1) ;
		Date endDate = getDate(date, 365);
		
		DBObject discDate = new BasicDBObject("dates_discontinue",new BasicDBObject("$not", new BasicDBObject("$lte", startDate)));
		DBObject effDate = new BasicDBObject("tar_eff_date",new BasicDBObject("$not", new BasicDBObject("$gte", endDate)));
		
		andClause.add(new BasicDBObject("cxr_cd","GA"));		
		andClause.add(discDate);
		andClause.add(effDate);
		
		finalQuery.append("$and", andClause);
		System.out.println("queries =>> "+ finalQuery);
		
		return finalQuery;
	}

	private Date getDate(Date currentDate, int next) {
    	Calendar date = Calendar.getInstance();
    	date.setTime(currentDate);
        date.add(Calendar.DATE, next);
        
		return date.getTime();
	}
	
	private ProsDataFeedsHeader setProsDataFeedsHeader(Date currentDate) {
		//Header FPG
		ProsDataFeedsHeader prosHeader = new ProsDataFeedsHeader(); 
    	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        String startDate = dateFormat.format(getDate(currentDate, 1));
        String endDate = dateFormat.format(getDate(currentDate, 365));
        prosHeader.setRecordId("FPG");
		prosHeader.setCreationDate(dateFormat.format(currentDate));
		prosHeader.setStartDate(startDate);
		prosHeader.setEndDate(endDate);
    	return prosHeader;
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

	@SuppressWarnings("deprecation")
	private String setProsDataRecord(AtpcoFare data) {
		ProsDataFeedsRecord prosDatafeed = new ProsDataFeedsRecord();
    	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    	Date endOfTime = new Date();
    	endOfTime.setYear(9999);
    	
    	Date effDate = data.getTariffEffectiveDateObject()=="indef" ? endOfTime : (Date) data.getTariffEffectiveDateObject();
    	Date discDate = data.getDiscontinueDateObject().toString().equalsIgnoreCase("indef") ? endOfTime : (Date) data.getDiscontinueDateObject();
    	Date saleStartDate = data.getFirstSaleDateObject().toString().equalsIgnoreCase("indef") ? endOfTime : (Date) data.getFirstSaleDateObject();
    	Date saleEndDate = data.getLastSaleDateObject().toString().equalsIgnoreCase("indef") ? endOfTime : (Date) data.getLastSaleDateObject();
    	
		prosDatafeed.setRecordId("FPO");
		prosDatafeed.setOriginAirportCode(data.getOriginCity());
		prosDatafeed.setDestAirportCode(data.getDestinationCity());
		prosDatafeed.setEffectiveDate(dateFormat.format(effDate));
		prosDatafeed.setDiscontinueDate(dateFormat.format(discDate));
		prosDatafeed.setSaleStartDate(dateFormat.format(saleStartDate));
		prosDatafeed.setSaleEndDate(dateFormat.format(saleEndDate));
		prosDatafeed.setBookingClass("");
		prosDatafeed.setOriginCountry(data.getOriginCountry());
		prosDatafeed.setFareBasisGroup("");
		prosDatafeed.setCarrier(data.getCarrierCode());
		prosDatafeed.setFareClassCode(data.getFareClassCode());
		prosDatafeed.setOwrt(data.getOwrt());
		prosDatafeed.setFareSourceCode("A");
		prosDatafeed.setBaseFareAmount(data.getFareOriginAmountDecimal());
		prosDatafeed.setCurrencyCode(data.getFareOriginCurrencyCode());
		prosDatafeed.setIataFareFlag("");
		prosDatafeed.setAdvancePurchase("");
		prosDatafeed.setMinStay("3");
		prosDatafeed.setPointOfSales("ID");
		prosDatafeed.setFareTypeCode(data.getFareType());
		prosDatafeed.setUseInCalculation("Y");
		prosDatafeed.setTimeOfDays("0800-1500");
		prosDatafeed.setFlightFrequency("CO123");
		prosDatafeed.setFlightFrequency("1101101");
		prosDatafeed.setSeasonStartDate("20090712");
		prosDatafeed.setSeasonEndDate("20090712");
		prosDatafeed.setEndFareAmount("");
		prosDatafeed.setFpsRecordLocator("");
		prosDatafeed.setItineraryType("A");
		prosDatafeed.setAirportConnection("A");

		return prosDatafeed.build();
	}

}
