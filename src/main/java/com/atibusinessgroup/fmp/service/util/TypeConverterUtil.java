package com.atibusinessgroup.fmp.service.util;

public class TypeConverterUtil {

	public static Integer convertStringToInt(String value) {
		Integer result = null;
		
		try {
			result = Integer.parseInt(value);
		} catch (Exception e) {
		}
		
		return result;
	}
}
