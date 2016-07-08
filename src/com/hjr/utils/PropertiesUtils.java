package com.hjr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 
 * @author houjingrui
 *
 */

public class PropertiesUtils {

	public static Properties load(InputStream s) throws IOException {
		Properties proper = new Properties();
		if (null != s) {
			proper.load(s);
		}
		return proper;
	}

	public static String getValue(Properties proper, String key) {
		String value = null;
		if (null != proper && null != key) {
			value = proper.getProperty(key);
		}
		return value;
	}

	public static Enumeration<Object> getKeys(Properties proper) {
		Enumeration<Object> keys = null;
		if (null != proper) {
			keys = proper.keys();
		}
		return keys;
	}

}
