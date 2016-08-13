package com.hjr.exception;

public class DomainValidationException extends MsgException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2L;

	public DomainValidationException(String msg) {
		super(msg);
	}

	public DomainValidationException() {
		super();
	}
}
