package com.hjr.daoTest;

import org.junit.Test;

import com.hjr.dao.xmlImpl.BaseDAO;

public class BaseDAOTest {

	@Test
	public void init() {
		BaseDAO dao = new BaseDAO();
		dao.hashCode();
	}
}

