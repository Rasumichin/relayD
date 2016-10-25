package com.relayd.attributes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Fowler, Martin, Analysis Pattern, 3.1 Quantity Pattern
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   05.10.2016
 *
 */
public class Distance implements Serializable {
	private static final long serialVersionUID = 1L;

	BigDecimal value;

	private Unity unity = Unity.KM;

	private Distance(BigDecimal distance) {
		super();
		value = distance.setScale(2, RoundingMode.HALF_UP);
	}

	public static Distance newInstance(BigDecimal aDistance) {
		validate(aDistance);
		return new Distance(aDistance);
	}

	public static Distance newInstance(BigDecimal aDistance, Unity anUnity) {
		validate(aDistance);
		Distance distance = new Distance(aDistance);
		distance.unity = anUnity;
		return distance;
	}

	public static Distance kilometers(BigDecimal distance) {
		validate(distance);
		return new Distance(distance);
	}

	public static Distance meters(BigDecimal aDistance) {
		validate(aDistance);
		return Distance.newInstance(aDistance, Unity.METER);
	}

	private static void validate(BigDecimal distance) {
		if (distance == null) {
			throw new IllegalArgumentException("[distance] must not be 'null'.");
		}
	}

	private Unity getUnity() {
		return unity;
	}

	public Distance add(Distance aDistance) {
		if (areSameUnity(aDistance)) {
			return Distance.newInstance(value.add(aDistance.value), getUnity());
		} else {
			// Klappt aber aktuell nur von KM->METER. Nicht von wenn METER->KM!
			return Distance.newInstance(value.multiply(new BigDecimal(1000)).add(aDistance.value), getUnity());
		}
	}

	private boolean areSameUnity(Distance aDistance) {
		return getUnity().equals(aDistance.getUnity());
	}

	public String toStringWithUnity() {
		return value + " " + getUnity();
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