package com.hjr.exception;

public class MsgException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public MsgException(String msg) {
		super(msg);
	}

	public MsgException() {
		super();
	}

}
