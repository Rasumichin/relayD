package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 18.09.2016
 */
public class YearOfBirth implements Serializable {
	private static final long serialVersionUID = -6062906068983082920L;

	Integer value;

	private YearOfBirth(Integer aYear) {
		value = aYear;
	}

	public static YearOfBirth newInstance(Integer year) {
		validate(year);
		return new YearOfBirth(year);
	}

	private static void validate(Integer year) {
		if (year == null) {
			throw new IllegalArgumentException("[year] must not be 'null'!");
		}
	}

	@Override
	public String toString() {
		return value.toString();
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
		YearOfBirth other = (YearOfBirth) obj;
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