package com.relayd.attributes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.relayd.FormatConstants;

/**
 * @author  Rasumichin (Erik@relayd.de)
 * @since   24.05.2016
 *
 */
public class EventDay implements Serializable {
	private static final long serialVersionUID = 1L;

	LocalDate value;

	private EventDay() {
		this(LocalDate.now());
	}

	private EventDay(LocalDate dateOfEvent) {
		value = dateOfEvent;
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	static public EventDay today() {
		return new EventDay();
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	static public EventDay newInstance(LocalDate dateOfEvent) {
		if (dateOfEvent == null) {
			return EventDayNullObject.instance();
		}
		return new EventDay(dateOfEvent);
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

	public boolean isEmpty() {
		return false;
	}

	@Override
	public String toString() {
		return value.format(DateTimeFormatter.ofPattern(FormatConstants.DATE_FORMAT_ISO));
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

	static final class EventDayNullObject extends EventDay {
		private static final long serialVersionUID = -6726731303311850956L;

		private static final EventDayNullObject SINGLETON = new EventDayNullObject();

		private static EventDayNullObject instance() {
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