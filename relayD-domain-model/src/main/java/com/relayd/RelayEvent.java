package com.relayd;

import java.io.Serializable;
import java.util.UUID;

import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 19.05.2016
 *
 */
public class RelayEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private Eventname name;
	private EventDay eventDay;

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	static public RelayEvent newInstance(Eventname anEventName, EventDay anEventDay) {
		return new RelayEvent(anEventName, anEventDay);
	}

	private RelayEvent(Eventname anEventName, EventDay anEventDay) {
		super();
		uuid = UUID.randomUUID();
		name = anEventName;
		eventDay = anEventDay;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID anUuid) {
		uuid = anUuid;
	}

	public Eventname getName() {
		return name;
	}

	public void setName(Eventname eventName) {
		name = eventName;
	}

	public EventDay getEventDay() {
		return eventDay;
	}

	public void setEventDay(EventDay day) {
		eventDay = day;
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