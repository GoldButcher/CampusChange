package com.campus.util;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

public class FormUtil {

	public static final String STRING_DEFAULT = "";
	public static final String NUMBER_DEFAULT = "0.0";

	public static String getField(HttpServletRequest request, String paraName,
			String defaultValue) {
		String data = request.getParameter(paraName);
		if (data == null || data.trim().equals("")) {
			data = defaultValue;
		}
		return data;
	}

	public static BigDecimal getNumberValue(HttpServletRequest request,
			String paraName) throws Exception{
		String data = request.getParameter(paraName);
		if (data == null || data.trim().equals("")) {
			data = NUMBER_DEFAULT;
		} 
		return new BigDecimal(data);
	}

	public static String getStringValue(HttpServletRequest request,
			String paraName) {
		String data = request.getParameter(paraName);
		if (data == null || data.trim().equals("")) {
			data = STRING_DEFAULT;
		}
		return data;
	}

}
