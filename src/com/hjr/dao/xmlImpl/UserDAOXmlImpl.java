package com.hjr.dao.xmlImpl;

import com.hjr.dao.UserDAO;
import com.hjr.domain.User;
import com.hjr.exception.MsgException;

public class UserDAOXmlImpl extends BaseDAO implements UserDAO {

	private static final String DOMAIN_NAME = User.class.getName();

	@Override
	public void add(User user) throws MsgException {
		user.validate();
		add(DOMAIN_NAME, user.parse2AttrMap());
	}

	@Override
	public User queryById(int id) {
		Object usr = null;
		if ((usr = super.queryById(DOMAIN_NAME, id)) instanceof User) {
			return (User) usr;
		}
		return null;
	}

	@Override
	public void remove(int id) throws MsgException {
		super.removeById(DOMAIN_NAME, id);
	}

	@Override
	public void update(User user) throws MsgException {
		int id = user.getId();
		User target = this.queryById(id);
		if (null == target) {
			throw new MsgException("用户id不存在");
		}
		if (!user.getName().equals(target.getName())) {
			throw new MsgException("用户名无法更改");
		}
		super.update(DOMAIN_NAME, id, user.parse2AttrMap());
		
	}
}
