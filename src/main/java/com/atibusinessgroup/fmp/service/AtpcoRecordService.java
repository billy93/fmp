package com.atibusinessgroup.fmp.service;

import org.springframework.stereotype.Service;

@Service
public class AtpcoRecordService {

	public boolean compareMatchingFareAndRecord(String fGeoType1, String fGeoLoc1, String fGeoType2, String fGeoLoc2, String fOwrt, String fRoutingNo, String fFootnote, Object fEffectiveDate, Object fDiscontinueDate, 
			String rGeoType1, String rGeoLoc1, String rGeoType2, String rGeoLoc2, String rOwrt, String rRoutingNo, String rFootnote, Object rEffectiveDate, Object rDiscontinueDate) {
		boolean match = true;
		
		System.out.println(fGeoType1 + " " + fGeoLoc1 + " " + fGeoType2 + " " + fGeoLoc2 + " " + fOwrt + " " + fRoutingNo + " " + fFootnote + " " + fEffectiveDate + " " + fDiscontinueDate);
		System.out.println(rGeoType1 + " " + rGeoLoc1 + " " + rGeoType2 + " " + rGeoLoc2 + " " + rOwrt + " " + rRoutingNo + " " + rFootnote + " " + rEffectiveDate + " " + rDiscontinueDate);
		
		
		
		return match;
	}

}
