package com.hjr.domain;

import java.util.Map;

import com.hjr.exception.DomainValidationException;

public abstract class BaseDomain {

	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public abstract void validate() throws DomainValidationException;

	public abstract Map<String, Object> parse2AttrMap();

}
