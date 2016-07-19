package com.hjr.daoTest;

import org.dom4j.Element;
import org.junit.Test;

import com.hjr.dao.xmlImpl.BaseDAO;

public class BaseDAOTest {
	
	private BaseDAO dao = new BaseDAO();
	
	@Test
	public void testParse2Bean() {
		Element ele = dao.query("User[3]");
		Object obj;
		try {
			obj = dao.parse2Bean(ele);
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
