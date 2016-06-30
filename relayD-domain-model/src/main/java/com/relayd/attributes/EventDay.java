package com.relayd.attributes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author  Rasumichin (Erik@relayd.de)
 * @since   24.05.2016
 * status   ready-for-review
 */
public class EventDay implements Serializable {
	private static final long serialVersionUID = 1L;

	private String datePattern = "dd-MM-yyyy";

	private LocalDate value;

	public EventDay(LocalDate dateOfEvent) {
		if (dateOfEvent == null) {
			throw new IllegalArgumentException("[dateOfEvent] must not be 'null'.");
		}
		value = dateOfEvent;
	}

	public EventDay() {
		this(LocalDate.now());
	}

	public EventDay(LocalDate dateOfEvent, String aDatePattern) {
		this(dateOfEvent);
		datePattern = aDatePattern;

	}

	public LocalDate getValue() {
		return value;
	}

	public boolean isInThePast() {
		return getValue().isBefore(LocalDate.now());
	}

	public boolean isToday() {
		return getValue().isEqual(LocalDate.now());
	}

	public boolean isInTheFuture() {
		return getValue().isAfter(LocalDate.now());
	}

	@Override
	public String toString() {
		return "" + value.format(DateTimeFormatter.ofPattern(datePattern));
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
		EventDay other = (EventDay) obj;
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