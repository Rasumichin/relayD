package com.relayd;

import java.util.Date;

import com.relayd.attributes.EventName;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 19.05.2016
 * status initial
 */
public class RelayEvent {

	private EventName name;
	private Date eventDate;

	public RelayEvent(EventName anEventName, Date anEventDate) {
		super();
		name = anEventName;
		eventDate = anEventDate;
	}

	public EventName getName() {
		return name;
	}

	public Date getEventDate() {
		return eventDate;
	}
}