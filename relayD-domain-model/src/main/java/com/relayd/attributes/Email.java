package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   29.03.2016
 * status   initial
 */
public class Email implements Serializable {
	private static final long serialVersionUID = 1L;

	String value = "";

	private Email(String email) {
		super();
		value = email;
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static Email newInstance(String anEmail) {
		validate(anEmail);
		return new Email(anEmail);
	}

	private static void validate(String email) {
		if (email == null) {
			throw new IllegalArgumentException("[email] must not be 'null'.");
		}
	}

	@Override
	public String toString() {
		return value;
	}

	/**
	 *
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 *
	 */
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
		Email other = (Email) obj;
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