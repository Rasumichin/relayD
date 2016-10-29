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

	private Distance(BigDecimal aValue, Unity anUnity) {
		value = aValue;
		unity = anUnity;
	}

	// TODO -Team relayd- Braucht man diesen Konstruktor eigentlich?
	// Eine Distance sollte doch eigentlich immer genau eine Ausprägung an Unity besitzen
	public static Distance newInstance(BigDecimal aDistance) {
		validate(aDistance);
		return new Distance(aDistance);
	}

	public static Distance kilometers(BigDecimal distance) {
		validate(distance);
		return new Distance(distance);
	}

	public static Distance meters(BigDecimal aDistance) {
		validate(aDistance);
		return new Distance(aDistance, Unity.METER);
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
			return new Distance(value.add(aDistance.value), getUnity());
		} else {
			throw new IllegalArgumentException("Only Distances with same unity are supported!");
		}
	}

	private boolean areSameUnity(Distance aDistance) {
		return getUnity().equals(aDistance.getUnity());
	}

	public String toStringWithUnity() {
		return toString() + " " + getUnity();
	}

	@Override
	public String toString() {
		return value.stripTrailingZeros().toString();
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