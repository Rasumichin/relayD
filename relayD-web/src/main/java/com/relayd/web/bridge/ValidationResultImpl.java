package com.relayd.web.bridge;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 03.07.2016
 *
 */
public class ValidationResultImpl implements ValidationResult {
	private String message = null;

	public ValidationResultImpl(String aMessage) {
		message = aMessage;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public boolean isEmpty() {
		if (message != null && !message.isEmpty()) {
			return false;
		}
		return true;
	}
}