package com.atibusinessgroup.fmp.service.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
}
