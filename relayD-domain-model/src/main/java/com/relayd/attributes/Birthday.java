package com.relayd.attributes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   22.03.2016
 * status   initial
 */
public class Birthday implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String DATE_PATTERN = "dd-MM-yyyy";

	private LocalDate value;

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	static public Birthday newInstance(LocalDate dateOfBirth) {
		validate(dateOfBirth);
		return new Birthday(dateOfBirth);
	}

	private Birthday(LocalDate aDate) {
		super();
		value = aDate;
	}

	static void validate(LocalDate aDate) {
		if (aDate == null) {
			throw new IllegalArgumentException("[dateOfBirth] must not be 'null'.");

		}
		if (aDate.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("[dateOfBirth] must be in the past.");
		}
	}

	@Override
	public String toString() {
		return "" + value.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
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
		Birthday other = (Birthday) obj;
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