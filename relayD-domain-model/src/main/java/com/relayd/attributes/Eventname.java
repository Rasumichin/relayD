package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   22.03.2016
 *
 */
public class Eventname implements Serializable {
	private static final long serialVersionUID = 1L;

	String value;

	private Eventname(String eventname) {
		super();
		value = eventname;
	}

	public static Eventname newInstance(String eventname) {
		if (eventname == null) {
			throw new IllegalArgumentException("[eventname] must not be 'null'.");
		}
		return new Eventname(eventname);
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
		Eventname other = (Eventname) obj;
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