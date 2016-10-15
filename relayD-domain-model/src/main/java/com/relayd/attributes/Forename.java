package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   22.03.2016
 *
 */
public class Forename implements Serializable {
	private static final long serialVersionUID = 1L;

	String value = "";
	
	private Forename() {
	}

	private Forename(String forename) {
		super();
		value = forename;
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	static public Forename newInstance(String forename) {
		validate(forename);
		return new Forename(forename);
	}

	private static void validate(String forename) {
		if (forename == null) {
			throw new IllegalArgumentException("[forename] must not be 'null'.");
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
		Forename other = (Forename) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
	
	private static final class NullObjectForename extends Forename {
		private static final long serialVersionUID = 6577776791000840413L;
		
		private static final NullObjectForename SINGLETON = new NullObjectForename();
		
		private static NullObjectForename instance() {
			return SINGLETON;
		}
		
		@Override
		public String toString() {
			return "";
		}
	}
}