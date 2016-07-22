package com.hjr.dao;

import com.hjr.domain.User;
import com.hjr.exception.MsgException;

public interface UserDAO {

	/**
	 * 添加一个用户
	 * @param user
	 * @throws MsgException
	 */
	public void add(User user) throws MsgException;

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public User query(int id);

	/**
	 *
	 * @param id
	 * @throws MsgException
	 */
	public void remove(int id) throws MsgException;

	/**
	 * 更新用户信息
	 * @param user
	 * @throws MsgException
	 */
	public void update(User user) throws MsgException;

}
