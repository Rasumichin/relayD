package com.relayd.web.bridge;

public class ValidationResultImpl implements ValidationResult {
	private String message = null;

	public ValidationResultImpl(String aMessage) {
		message = aMessage;
	}

	@Override
	public String getMessage() {
		return message;
	}
}