package com.relayd.attributes;

import java.io.Serializable;

public class EventName implements Serializable {
	private static final long serialVersionUID = 1L;

	String value;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EventName other = (EventName) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}