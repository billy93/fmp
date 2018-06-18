package com.atibusinessgroup.fmp.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.atibusinessgroup.fmp.constant.CategoryType;
import com.atibusinessgroup.fmp.domain.dto.CarrierField;
import com.atibusinessgroup.fmp.domain.dto.DateTable;
import com.atibusinessgroup.fmp.domain.dto.FlightTable;
import com.atibusinessgroup.fmp.domain.dto.TextTable;

public class AtpcoDataConverterUtil {
	
	public static String convertCategoryTypeToName(String type) {
		String result = "";

		if (type != null) {
			if (type.contentEquals(CategoryType.RULE)) {
				result = "FARE";
			} else if (type.contentEquals(CategoryType.FOOTNOTE)) {
				result = "FOOTNOTE";
			} else if (type.contentEquals(CategoryType.GENERAL_RULE)) {
				result = "GENERAL";
			} else if (type.contentEquals(CategoryType.ALTERNATE_GENERAL_RULE)) {
				result = "ALTERNATE GENERAL";
			}
		}
		
		return result;
	}
	
	public static String convertRelationshipToName(String relationship) {
		String result = "";

		if (relationship != null) {
			if (relationship.contentEquals("=")) {
				result = "THEN";
			} else if (relationship.contentEquals(":")) {
				result = "IF";
			} else if (relationship.contentEquals("&")) {
				result = "AND";
			} else if (relationship.contentEquals("/")) {
				result = "OR";
			} else if (relationship.contentEquals("*")) {
				result = "ELSE";
			}
		}
		
		return result;
	}
	
	public static String convertDayInNumberToName(String number) {
		String result = "";
		
		for (int i = 0; i < number.length(); i++) {
			if (number.charAt(i) == '1') {
				result += "MON";
			} else if (number.charAt(i) == '2') {
				result += "TUE";
			} else if (number.charAt(i) == '3') {
				result += "WED";
			} else if (number.charAt(i) == '4') {
				result += "THU";
			} else if (number.charAt(i) == '5') {
				result += "FRI";
			} else if (number.charAt(i) == '6') {
				result += "SAT";
			} else if (number.charAt(i) == '7') {
				result += "SUN";
			}
			
			if (i < number.length() - 1) {
				result += ", ";
			}
		}
		
		return result;
	}
	
	public static String convertUnitToName(String unit) {
		String result = "";
		
		if (unit != null) {
			switch(unit.trim()) {
			case "N": result = "MINUTES";
				break;
			case "H": result = "HOURS";
				break;
			case "D": result = "DAYS";
				break;
			case "M": result = "MONTHS";
				break;
			}
		}
		
		return result;
	}
	
	public static String convertSurchargeApplicationToName(String appl) {
		String result = "";
		
		if (appl != null) {
			switch(appl.trim()) {
			case "A": 
				result = "ANY PASSENGER";
				break;
			case "1": 
			case "B": 
				result = "CHILD/INFANT";
				break;
			case "2": 
			case "C":
				result = "ADULT/CHILD/INFANT";
				break;
			case "3": 
			case "D":
				result = "ADULT/CHILD/INFANT, BUT CHILD/INFANT ARE DISCOUNTED";
				break;
			case "4": 
			case "E":
				result = "ADULT";
				break;
			}
		}
		
		return result;
	}
	
	public static boolean checkSurchargeApplicationIsNegativeAmount(String appl) {
		boolean result = false;
		
		if (appl != null) {
			if (appl.trim().contentEquals("A") || appl.trim().contentEquals("B") || 
					appl.trim().contentEquals("C") || appl.trim().contentEquals("D") ||
					appl.trim().contentEquals("E")) {
				result = true;
			}
		}
		
		return result;
	}
	
	public static String convertFlightApplicationRelationshipIndicatorToName(String indicator) {
		String result = "";
		
		switch (indicator) {
		case "0":
			result = "TRAVEL MUST NOT BE VIA SPECIFIED FLIGHT NUMBER";
			break;
		case "1":
			result = "TRAVEL MUST BE VIA SPECIFIED FLIGHT NUMBER";
			break;
		case "2":
			result = "TRAVEL MUST NOT BE VIA A FLIGHT NUMBER WITHIN THE RANGE SPECIFIED IN FLIGHT 1 AND FLIGHT 2 FIELDS";
			break;
		case "3":
			result = "TRAVEL MUST BE VIA A FLIGHT NUMBER WITHIN THE RANGE SPECIFIED IN FLIGHT 1 AND FLIGHT 2 FIELDS";
			break;
		}
				
		return result;
	}
	
	public static String convertFlightApplicationBetweenCarrierIndicatorToName(String indicator) {
		String result = "";
		
		switch (indicator) {
		case "0":
			result = "AND/OR";
			break;
		case "1":
			result = "CONNECTING TO";
			break;
		case "2":
			result = "AND";
			break;
		}
				
		return result;
	}
	
	public static String convertTicketAndReservationRestrictionToName(String restriction) {
		String result = "";
		
		switch (restriction) {
		case "X":
			result = "TICKET IS NONREFUNDABLE";
			break;
		case "N":
			result = "RESERVATIONS CANNOT BE CHANGED";
			break;
		case "B":
			result = "TICKET IS NONREFUNDABLE AND RESERVATIONS CANNOT BE CHANGED";
			break;
		}
				
		return result;
	}

	public static String convertChargesApplicationToName(String appl) {
		String result = "";
		
		switch (appl) {
		case "1":
			result = "PENALTY ANYTIME";
			break;
		case "2":
			result = "PENALTY BEFORE DEPARTURE";
			break;
		case "3":
			result = "PENALTY AFTER DEPARTURE";
			break;
		case "4":
			result = "PENALTY ANYTIME. CHILD'S CHARGE CAN BE DISCOUNTED";
			break;
		case "5":
			result = "PENALTY BEFORE DEPARTURE. CHILD'S CHARGE CAN BE DISCOUNTED";
			break;
		case "6":
			result = "PENALTY AFTER DEPARTURE. CHILD'S CHARGE CAN BE DISCOUNTED";
			break;
		}
				
		return result;
	}

	public static String convertEndorsementTicketLocationToName(String location) {
		String result = "";
		
		switch (location) {
		case "1":
			result = "FORM OF PAYMENT BOX FOR ORIGINAL TICKET";
			break;
		case "2":
			result = "ENDORSEMENT BOX FOR ORIGINAL TICKET";
			break;
		case "3":
			result = "FORM OF PAYMENT BOX FOR REISSUED TICKET";
			break;
		case "4":
			result = "ENDORSEMENT BOX FOR REISSUED TICKET";
			break;
		case "5":
			result = "FORM OF PAYMENT BOX FOR BOTH ORIGINAL AND REISSUED TICKET";
			break;
		case "6":
			result = "ENDORSEMENT BOX FOR BOTH ORIGINAL AND REISSUED TICKET";
			break;
		}
				
		return result;
	}

	public static String convertAddonConstructionToName(String construction) {
		String result = "";
		
		switch (construction) {
		case "Y":
			result = "THE FARE MAY BE USED FOR ADD-ON CONSTRUCTION";
			break;
		case "R":
			result = "THE FARE MUST ONLY BE USED FOR ADD-ON CONSTRUCTION AND SHOULD NOT BE DISPLAYED OR SOLD IN ITS SPECIFIED FARE MARKET";
			break;
		case "N":
			result = "THE FARE MAY NOT BE USED FOR ADD-ON CONSTRUCTION";
			break;
		case "":
			result = "THE FARE MAY BE USED FOR ANY PURPOSE (WITHIN THE CONFINES OF THE REMAINDER OF THE FIELDS IN THE RECORD)";
			break;
		}
		
		return result;
	}

	public static String convertFareCalculationCodeIndicatorToName(String indicator) {
		String result = "";
		
		switch (indicator) {
		case "C":
			result = "CALCULATED";
			break;
		case "S":
			result = "SPECIFIED";
			break;
		case "A":
			result = "ADDING CALCULATED TO SPECIFIED";
			break;
		case "M":
			result = "SUBTRACTING SPECIFIED FROM CALCULATED";
			break;
		case "B":
			result = "ADDING SPECIFIED TO BASE AMOUNT THEN CALCULATE ON THAT RESULT";
			break;
		case "G":
			result = "SUBTRACTING SPECIFIED FROM BASE AMOUNT THEN CALCULATE ON THAT RESULT";
			break;
		case "H":
			result = "USING THE HIGHER OF THE FOLLOWING EQUATION: CALCULATED COMPARED TO SPECIFIED, CALCULATED COMPARED TO FARE COMPARISON, CALCULATED COMPARED TO BOTH, SPECIFIED AND FARE COMPARISON";
			break;
		case "L":
			result = "USING THE LOWER OF THE FOLLOWING EQUATION: CALCULATED COMPARED TO SPECIFIED, CALCULATED COMPARED TO FARE COMPARISON, CALCULATED COMPARED TO BOTH, SPECIFIED AND FARE COMPARISON";
			break;
		case "D":
			result = "DOUBLING THE CALCULATE TO CREATE A TAG 2 FARE";
			break;
		case "E":
			result = "SPECIFIED RATE PER 100 MILES (TICKETED POINT MILEAGE)";
			break;
		case "F":
			result = "SPECIFIED RATE PER 100 MILES (NON-STOP SECTOR MILES)";
			break;
		case "K":
			result = "SPECIFIED AMOUNT IN A CURRENCY OTHER THAN THE CURRENCY OF COUNTRY OF ORIGIN";
			break;
		}
		
		return result;
	}

	public static String convertOwrtToName(String owrt) {
		String result = "";
		
		switch (owrt) {
		case "1":
			result = "One Way";
			break;
		case "2":
			result = "Round Trip";
			break;
		case "3":
			result = "One Way Fare That May Not Be Doubled";
			break;
		}
		
		return result;
	}
	
	public static String convertSeparatedDateIntoTextDate(String day, String month, String year) {
		String result = "";
		
		if (day != null && !day.isEmpty()) {
			result += day;
		} else {
			result += "XX";
		}
		
		if (month != null && !month.isEmpty()) {
			switch (month) {
			case "01":
				result += "JAN";
				break;
			case "02":
				result += "FEB";
				break;
			case "03":
				result += "MAR";
				break;
			case "04":
				result += "APR";
				break;
			case "05":
				result += "MAY";
				break;
			case "06":
				result += "JUN";
				break;
			case "07":
				result += "JUL";
				break;
			case "08":
				result += "AUG";
				break;
			case "09":
				result += "SEP";
				break;
			case "10":
				result += "OCT";
				break;
			case "11":
				result += "NOV";
				break;
			case "12":
				result += "DEC";
				break;
			default:
				result += "XXX";
				break;
			}
		} else {
			result += "XXX";
		}
		
		if (year != null && !year.isEmpty()) {
			result += year;
		} else {
			result += "XXXX";
		}
		
		if (result.contentEquals("XXXXXXXXX")) {
			result = "";
		}
		
		return result.toUpperCase();
	}
	
	public static String convertDateObjectToText(Object dateObj) {
		String result = "";
		
		if (dateObj != null) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy");
				Date date = (Date) dateObj;
				result = formatter.format(date);
			} catch (Exception e) {
				if (dateObj.toString().contentEquals("indef")) {
					result = "XXXXXXXXX";
				}
			}
		}
		
		return result.toUpperCase();
	}
	
	public static String convertFlightTableToText(FlightTable flightTable986l) {
		String result = "";

		if (flightTable986l != null) {
			for (int i = 0; i < flightTable986l.getCarriers().size(); i++) {
				CarrierField cf = flightTable986l.getCarriers().get(i);
				result = "\t\t";
				if (cf.getMarketingCarrier() != null && !cf.getMarketingCarrier().isEmpty()) {
					result += cf.getMarketingCarrier() + " ";
				}
				if (cf.getFlightNo1() != null && !cf.getFlightNo1().isEmpty()) {
					result += cf.getFlightNo1();
				}
				if (cf.getFlightNo2() != null && !cf.getFlightNo2().isEmpty()) {
					result += " THROUGH " + cf.getFlightNo2();
				}
				if (cf.getOperatingCarrier() != null && !cf.getOperatingCarrier().isEmpty()) {
					result += " OPERATED BY " + cf.getOperatingCarrier();
				}
				if (i < flightTable986l.getCarriers().size() - 1) {
					result += "\n";
				}
			}
		}
		
		return result;
	}
	
	public static String convertTextTableToText(TextTable textTable996) {
		String result = "";
		
		if (textTable996 != null && textTable996.getText().size() > 0) {
			result = "\tNOTE:\n";
			
			for (String line:textTable996.getText()) {
				result += "\t\t" + line + "\n";
			}
		}
		
		return result;
	}
	
	public static String convertDateTableToText(DateTable dateTable994) {
		String result = "";
		
		if (dateTable994 != null) {
			if (dateTable994.getReservationEffectiveDate() != null || dateTable994.getReservationDiscontinueDate() != null) {
				String reservation = "FOR RESERVATION ";
				String start = convertDateObjectToText(dateTable994.getReservationEffectiveDate());
				String end = convertDateObjectToText(dateTable994.getReservationDiscontinueDate());
				if (!start.isEmpty()) {
					reservation += "ON AFTER " + start + " ";
				}
				if (!end.isEmpty()) {
					reservation += "ON BEFORE " + end;
				}
				result += reservation + "\n";
			}
			if (dateTable994.getTicketingEffectiveDate() != null || dateTable994.getTicketingDiscontinueDate() != null) {
				String ticketing = "FOR TICKETING ";
				String start = convertDateObjectToText(dateTable994.getTicketingEffectiveDate());
				String end = convertDateObjectToText(dateTable994.getTicketingDiscontinueDate());
				if (!start.isEmpty()) {
					ticketing += "ON AFTER " + start + " ";
				}
				if (!end.isEmpty()) {
					ticketing += "ON BEFORE " + end;
				}
				result += ticketing + "\n";
			}
			if (dateTable994.getTravelEffectiveDate() != null || dateTable994.getTravelDiscontinueDate() != null) {
				String travel = "FOR TRAVEL ";
				String start = convertDateObjectToText(dateTable994.getTravelEffectiveDate());
				String end = convertDateObjectToText(dateTable994.getTravelDiscontinueDate());
				if (!start.isEmpty()) {
					travel += "ON AFTER " + start + " ";
				}
				if (!end.isEmpty()) {
					travel += "ON BEFORE " + end;
				}
				result += travel + "\n";
			}
		}
		
		return result;
	}
}
