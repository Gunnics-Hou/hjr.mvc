package com.hjr.utilsTest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Test;

import com.hjr.dao.xmlImpl.BaseDAO;

public class XmlImplTest {

	private BaseDAO baseDAO = new BaseDAO();

	@Test
	public void testLoad() {
		Document doc = baseDAO.getDocument();
		Element root = baseDAO.getRoot();
		Assert.assertNotNull(doc);
		Assert.assertNotNull(root);
		Assert.assertEquals("data", root.getName());
	}

	@Test
	public void testQuery() {
	}

	@Test
	public void testQueryList() {
	}
}
