package com.relayd.attributes;

import java.time.LocalDate;

/**
 * @author  Rasumichin (Erik@relayd.com)
 * @since   24.05.2016
 * status   initial
 * 
 */
public class EventDay {

	private LocalDate value;

	public EventDay(LocalDate dateOfEvent) {
		if (dateOfEvent == null) {
			throw new IllegalArgumentException("[dateOfEvent] must not be 'null'.");
		}
		value = dateOfEvent;
	}

	public LocalDate getValue() {
		return value;
	}

	public boolean isInThePast() {
		return getValue().isBefore(LocalDate.now());
	}
}
