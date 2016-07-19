package com.hjr.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

	public static String getGetterName(String fieldName) {
		if (null != fieldName && "" != fieldName.trim()) {
			StringBuilder getterName = new StringBuilder("get");
			getterName.append(Character.toUpperCase(fieldName.charAt(0))).append(fieldName.substring(1));
			return getterName.toString();
		}
		return null;
	}

	public static String getSetterName(String fieldName) {
		if (null != fieldName && "" != fieldName.trim()) {
			StringBuilder setterName = new StringBuilder("set");
			setterName.append(Character.toUpperCase(fieldName.charAt(0))).append(fieldName.substring(1));
			return setterName.toString();
		}
		return null;
	}

	public static Date parseStr2Date(String dateStr, String flag) throws ParseException {
		if (null == dateStr) {
			return null;
		}
		if (null == flag || "" == flag.trim()) {
			flag = "-";
		}
		String format = new StringBuilder("yyyy").append(flag).append("MM").append(flag).append("dd").toString();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(dateStr);
		return date;

	}

	public static Object parseStr2Obj(String content, Class<?> cls) throws ParseException {
		if (null == content || null == cls) {
			return null;
		}
		if (cls == String.class) {
			return content;
		}
		if (cls == Integer.class || cls == int.class) {
			return Integer.parseInt(content);
		}
		if (cls == Date.class) {
			return parseStr2Date(content,null);
		}
		if (cls == Boolean.class || cls == boolean.class) {
			return Boolean.parseBoolean(content);
		}
		return null;
	}

}
