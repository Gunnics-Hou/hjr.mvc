package com.hjr.daoTest;

import java.text.ParseException;

import org.junit.Test;

import com.hjr.dao.UserDAO;
import com.hjr.dao.xmlImpl.UserDAOXmlImpl;
import com.hjr.domain.User;
import com.hjr.exception.MsgException;
import com.hjr.utils.StringUtils;

public class UserDAOXmlImplTest {

	private UserDAO dao = new UserDAOXmlImpl();

	@Test
	public void testAdd() {
		try {
			User user = new User(6, "Jackson", "123456",
					StringUtils.parseStr2Date("2010-01-19", null), "旧金山",
					"12@sz.com");
			dao.add(user);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (MsgException e) {
			e.printStackTrace();
		}
	}

}
