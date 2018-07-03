package com.atibusinessgroup.fmp.service.util;

public class TypeConverterUtil {

	public static Integer convertStringToInt(String value) {
		Integer result = 0;
		
		try {
			result = Integer.parseInt(value);
		} catch (Exception e) {
		}
		
		return result;
	}

	public static double convertStringToDouble(String value) {
		Double result = 0.0;
		
		try {
			result = Double.parseDouble(value);
		} catch (Exception e) {
		}
		
		return result;
	}
}
