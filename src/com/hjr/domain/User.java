package com.hjr.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	/**
	 * 默认serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
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
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", birthday=" + birthday + ", address=" + address
				+ ", email=" + email + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
