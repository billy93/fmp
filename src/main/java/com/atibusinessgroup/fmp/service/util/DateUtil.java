package com.atibusinessgroup.fmp.service.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static String convertSeasonDayMonthYearFormat(String day, String month, String year) {
		String result = "";
		
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		
		try {
			if (day != null && !day.isEmpty()) {
				int dayInt = Integer.parseInt(day);
				calendar.set(Calendar.DAY_OF_MONTH, dayInt);
				result += new SimpleDateFormat("dd").format(calendar.getTime());
			}
		} catch (Exception e) {
		}
		
		try {
			if (month != null && !month.isEmpty()) {
				int monthInt = Integer.parseInt(month);
				calendar.set(Calendar.MONTH, monthInt - 1);
				result += new SimpleDateFormat("MMM").format(calendar.getTime());
			}
		} catch (Exception e) {
		}

		try {
			if (year != null && !year.isEmpty()) {
				int yearInt = Integer.parseInt(year);
				calendar.set(Calendar.YEAR, yearInt);
				result += new SimpleDateFormat("yyyy").format(calendar.getTime());
			} else {
				result += "XXXX";
			}
		} catch (Exception e) {
		}
		
		return result;
	}
	
	public static boolean firstDateIsLessThanEqualSecondDate(Date firstDate, Date secondDate) {
		System.out.println(firstDate + " " + secondDate);
		
		if (firstDate == null || secondDate == null) {
			return true;
		}
		
		if (firstDate.equals(secondDate)) {
			return true;
		} else if (firstDate.before(secondDate)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Date convertObjectToDate(Object date) {
		Date result = null;
		
		try {
			Date paramDate = (Date) date;
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.setTime(paramDate);
			
			result = calendar.getTime();
		} catch (Exception e) {
		}
		
		if (result == null) {
			try {
				String dateString = date.toString();
				if (dateString.contains("T") && dateString.contains("+")) {
					String tempDate = dateString.substring(0, 10);
					result = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);
				}
			} catch (Exception e) {
			}
		}
		
		return result;
	}
}
