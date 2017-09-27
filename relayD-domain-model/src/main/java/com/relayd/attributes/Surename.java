package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author schmollc (Christian@relayD.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 22.03.2016
 *
 */
public class Surename implements Serializable {
	private static final long serialVersionUID = 1L;

	String value;

	private Surename() {
	}

	private Surename(String surename) {
		super();
		value = surename;
	}

	public static Surename newInstance() {
		return SurenameNullObject.instance();
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static Surename newInstance(String surename) {
		if (surename == null || surename.trim().isEmpty()) {
			return SurenameNullObject.instance();
		}

		return new Surename(surename);
	}

	public boolean isEmpty() {
		return false;
	}

	public static int sortBySurename(Surename name1, Surename name2) {
		return name1.toString().toUpperCase().compareTo(name2.toString().toUpperCase());
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

	static final class SurenameNullObject extends Surename {
		private static final long serialVersionUID = -1400372422930380396L;

		private static final SurenameNullObject SINGLETON = new SurenameNullObject();

		private static SurenameNullObject instance() {
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