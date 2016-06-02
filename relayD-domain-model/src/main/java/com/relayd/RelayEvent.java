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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		RelayEvent other = (RelayEvent) obj;
		if (eventDate == null) {
			if (other.eventDate != null) {
				return false;
			}
		} else if (!eventDate.equals(other.eventDate)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}