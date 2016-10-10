package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 22.03.2016
 *
 */
public class Surename implements Serializable {
	private static final long serialVersionUID = 1L;

	String value;

	private Surename(String surename) {
		super();
		value = surename;
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	static public Surename newInstance(String surename) {
		validate(surename);
		return new Surename(surename);
	}

	private static void validate(String surename) {
		if (surename == null) {
			throw new IllegalArgumentException("[surename] must not be 'null'.");
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
		Surename other = (Surename) obj;
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