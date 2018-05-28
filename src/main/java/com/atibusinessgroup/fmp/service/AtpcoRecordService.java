package com.atibusinessgroup.fmp.service;

import org.springframework.stereotype.Service;

@Service
public class AtpcoRecordService {

	public boolean compareMatchingFareAndRecord(String fGeoType1, String fGeoLoc1, String fGeoType2, String fGeoLoc2, String fOwrt, String fRoutingNo, String fFootnote, Object fEffectiveDate, Object fDiscontinueDate, 
			String rGeoType1, String rGeoLoc1, String rGeoType2, String rGeoLoc2, String rOwrt, String rRoutingNo, String rFootnote, Object rEffectiveDate, Object rDiscontinueDate) {
		boolean match = false;
		
		
		System.out.println(fGeoType1 + " " + fGeoLoc1 + " " + fGeoType2 + " " + fGeoLoc2 + " " + fOwrt + " " + fRoutingNo + " " + fFootnote + " " + fEffectiveDate + " " + fDiscontinueDate);
		System.out.println(rGeoType1 + " " + rGeoLoc1 + " " + rGeoType2 + " " + rGeoLoc2 + " " + rOwrt + " " + rRoutingNo + " " + rFootnote + " " + rEffectiveDate + " " + rDiscontinueDate);
		
		if (compareRoutingNo(fRoutingNo, rRoutingNo) && compareOwrt(fOwrt, rOwrt) && compareFootnote(fFootnote, rFootnote)) {
			match = true;
			System.out.println("MATCHED");
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
}
