package com.hjr.domain;

import java.util.Map;

import com.hjr.exception.DomainValidationException;

public abstract class BaseDomain {

	public abstract void validate() throws DomainValidationException;

	public abstract Map<String, Object> parse2AttrMap();

}
