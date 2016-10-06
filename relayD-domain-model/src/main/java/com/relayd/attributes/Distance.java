package com.relayd.attributes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   05.10.2016
 *
 */
public class Distance implements Serializable {
	private static final long serialVersionUID = 1L;

	BigDecimal value;

	private Distance(BigDecimal aDistance) {
		super();
		value = aDistance.setScale(2, RoundingMode.HALF_UP);
	}

	public static Distance newInstance(BigDecimal distance) {
		validate(distance);
		return new Distance(distance);
	}

	private static void validate(BigDecimal distance) {
		if (distance == null) {
			throw new IllegalArgumentException("[distance] must not be 'null'.");
		}
	}

	@Override
	public String toString() {
		return value.toString();
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
		Distance other = (Distance) obj;
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