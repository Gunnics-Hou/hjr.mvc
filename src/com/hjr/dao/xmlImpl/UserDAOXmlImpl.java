package com.hjr.dao.xmlImpl;

import com.hjr.dao.UserDAO;
import com.hjr.domain.User;
import com.hjr.exception.MsgException;
import com.hjr.utils.ReflectionUtils;
import com.hjr.utils.XmlUtils;

public class UserDAOXmlImpl extends BaseDAO implements UserDAO {

	@Override
	public void add(User user) throws MsgException {
		if(null == user) {
			throw new MsgException("参数为空");
		}
		try {
			XmlUtils.addElement(getRoot(), "User",
					ReflectionUtils.getBeanAttrPairs(user));
		} catch (ReflectiveOperationException e) {
			throw new MsgException(e.getMessage());
		}
	}

	@Override
	public User query(int id) {
		User user = null;
		Object resultObj = super.query("User[@id='"+id+"']");
		if (resultObj instanceof User) {
			user = (User)resultObj;
		}
		return user;
	}

	@Override
	public void remove(int id) throws MsgException {
	}

	@Override
	public void update(User user) throws MsgException {

	}

}
