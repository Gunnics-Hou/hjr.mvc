package com.hjr.dao.xmlImpl;

import java.util.HashMap;
import java.util.Map;

import com.hjr.dao.UserDAO;
import com.hjr.domain.User;
import com.hjr.exception.MsgException;

public class UserDAOXmlImpl extends BaseDAO implements UserDAO {

	public static final String[] ATTRIBUTE_NAMES = { "id", "name", "password",
			"birthday", "address", "email" };

	private static final String DOMAIN_NAME = User.class.getName();

	@Override
	public void add(User user) throws MsgException {
		user.validate();
		add(DOMAIN_NAME, user.parse2AttrMap());
	}

	@Override
	public User queryById(int id) {
		return null;
	}

	@Override
	public void remove(int id) throws MsgException {
	}

	@Override
	public void update(User user) throws MsgException {
		int id = user.getId();
		User target = this.queryById(id);
		if (null == target) {
			throw new MsgException("用户id不存在");
		}
		Map<String, String> map = new HashMap<String, String>();

		if (!user.getPassword().equals(target.getPassword())) {
			map.put("password", user.getPassword());
		}

		if (!user.getBirthday().equals(target.getBirthday())) {
			map.put("birthday", user.getBirthday().toString());
		}

		if (!user.getEmail().equals(target.getEmail())) {
			map.put("email", user.getEmail());
		}

		if (!user.getName().equals(target.getName())) {
			throw new MsgException("用户名无法更改");
		}

		if (map.size() == 0) {
			throw new MsgException("您未修改任何信息");
		}
	}
}
