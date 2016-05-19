package com.relayd;

import java.util.Date;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 19.05.2016
 * status initial
 */
public class RelayEvent {

	private String name;
	private Date eventDate;

	public RelayEvent(String aName, Date anEventDate) {
		super();
		name = aName;
		eventDate = anEventDate;
	}

	public String getName() {
		return name;
	}

	public Date getEventDate() {
		return eventDate;
	}
}