package com.relayd;

import java.io.Serializable;
import java.util.UUID;

import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 19.05.2016
 * status initial
 *
 */
public class RelayEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private EventName name;
	private EventDay eventDay;

	public RelayEvent(EventName anEventName, EventDay anEventDay) {
		super();
		uuid = UUID.randomUUID();
		name = anEventName;
		eventDay = anEventDay;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID aUuid) {
		uuid = aUuid;
	}

	public EventName getName() {
		return name;
	}

	public EventDay getEventDay() {
		return eventDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		if (uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}
}