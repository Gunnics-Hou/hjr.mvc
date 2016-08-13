package com.hjr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.hjr.exception.DomainValidationException;

public class User extends BaseDomain implements Serializable {

	/**
	 * 默认serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private Date birthday;
	private String address;
	private String email;

	public User() {
		super();
	}

	public User(Integer id, String name, String password, Date birthday,
			String address, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.birthday = birthday;
		this.address = address;
		this.email = email;
	}

	@Override
	public void validate() throws DomainValidationException {
		if (null == this.id) {
			throw new DomainValidationException("用户id不能为空");
		}
		if (null == this.name || "".equals(this.name.trim())) {
			throw new DomainValidationException("用户名不能为空");
		}
		if (null == this.password || "".equals(this.password.trim())) {
			throw new DomainValidationException("用户密码不能为空");
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", birthday=" + birthday + ", address=" + address
				+ ", email=" + email + "]";
	}

	@Override
	public Map<String, Object> parse2AttrMap() {
		Map<String, Object> map = new LinkedHashMap<String, Object>(6);
		map.put("id", this.getId());
		map.put("name", this.getName());
		map.put(email, this.getEmail());
		map.put("passowrd", this.getPassword());
		map.put("birthday", this.getBirthday());
		map.put("address", this.getAddress());
		return map;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
