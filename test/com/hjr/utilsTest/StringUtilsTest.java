package com.hjr.utilsTest;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import com.hjr.utils.StringUtils;

public class StringUtilsTest {
	
	@Test
	public void testParseStr2Date() {
		try {
			java.util.Date date = StringUtils.parseStr2Date("2016-02-01",null);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParseDate2Str() {
		String s1 = StringUtils.parseDate2Str(new Date(), null);
		System.out.append(s1);
		String s2 = StringUtils.parseDate2Str(new Date(), "yyyy:dd:MM");
		System.out.println(s2);
	}
	
}
