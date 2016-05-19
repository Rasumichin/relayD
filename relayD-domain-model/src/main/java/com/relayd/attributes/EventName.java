package com.relayd.attributes;

public class EventName {

	private String value;

	public EventName(String anEventName) {
		if (anEventName == null) {
			throw new IllegalArgumentException("[anEventName] must not be [null].");
		}
		value = anEventName;
	}

	private String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getValue();
	}
}