package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   22.03.2016
 *
 */
public class Eventname implements Serializable {
	private static final long serialVersionUID = -239584936730007592L;

	String value;

	private Eventname() {
		super();
	}

	private Eventname(String eventname) {
		super();
		value = eventname;
	}

	static public Eventname newInstance() {
		return EventnameNullObject.instance();
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static Eventname newInstance(String eventname) {
		if (eventname == null || eventname.trim().isEmpty()) {
			return EventnameNullObject.instance();
		}

		return new Eventname(eventname);
	}

	private String getValue() {
		return value;
	}

	public boolean isEmpty() {
		return false;
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

	static final class EventnameNullObject extends Eventname {
		private static final long serialVersionUID = 5459181639652243416L;

		private static final EventnameNullObject SINGLETON = new EventnameNullObject();

		private static EventnameNullObject instance() {
			return SINGLETON;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public String toString() {
			return "";
		}
	}
}