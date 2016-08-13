package com.hjr.service;

import java.util.Properties;

import com.hjr.dao.UserDAO;
import com.hjr.domain.User;
import com.hjr.exception.MsgException;
import com.hjr.utils.PropertiesUtils;

public class UserService {
	private static Class<?> daoLoader;
	private UserDAO dao;
	static {
		Properties proper;
		try {
			proper = PropertiesUtils.load(UserService.class
					.getResourceAsStream("/config.properties"));
			String className = PropertiesUtils.getValue(proper, "impl");
			daoLoader = Class.forName(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public UserService() {
		try {
			Object obj = daoLoader.newInstance();
			if (obj instanceof UserDAO) {
				dao = (UserDAO) obj;
			}
		} catch (ReflectiveOperationException e) {
			dao = null;
			e.printStackTrace();
		}
	}

	public User findById(int id) {
		return dao.queryById(id);
	}

	public void add(User user) throws MsgException {
		dao.add(user);
	}

	public void update(User user) throws MsgException {
		dao.update(user);
	}

	public void remove(int id) throws MsgException {
		dao.remove(id);
	}
}
