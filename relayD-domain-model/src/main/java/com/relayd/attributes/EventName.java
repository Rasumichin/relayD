package com.relayd.attributes;

public class EventName {

	private String value;

	public EventName(String anEventName) {
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