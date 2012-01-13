package org.mkcl.els.common.exception;

public class SessionExpiredException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public SessionExpiredException () {
		message = "Unknown";
	}

	public SessionExpiredException (String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
