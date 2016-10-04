package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   22.03.2016
 * status   initial
 */
public class Relayname implements Serializable, Comparable<Relayname> {
	private static final long serialVersionUID = 1L;

	String value;

	private Relayname(String relayname) {
		super();
		value = relayname;
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	static public Relayname newInstance(String relayname) {
		validate(relayname);
		return new Relayname(relayname);

	}

	private static void validate(String relayname) {
		if (relayname == null) {
			throw new IllegalArgumentException("[relayname] must not be 'null'.");
		}
	}

	public boolean isEmpty() {
		return value.trim().isEmpty();
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int compareTo(Relayname aRelayname) {
		return value.toLowerCase().compareTo(aRelayname.value.toLowerCase());
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
		Relayname other = (Relayname) obj;
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