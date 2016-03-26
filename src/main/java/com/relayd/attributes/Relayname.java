package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   22.03.2016
 * status   initial
 */
public class Relayname implements Serializable {
	private static final long serialVersionUID = 1L;

	private String value = "";

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	static public Relayname newInstance(String aRelayname) {
		validate(aRelayname);
		return new Relayname(aRelayname);

	}

	private Relayname(String aRelayname) {
		super();
		value = aRelayname;
	}

	private static void validate(String aRelayname) {
		if (aRelayname == null) {
			throw new IllegalArgumentException("Darf nicht null sein");
		}
	}

	@Override
	public String toString() {
		return value;
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