package com.relayd;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
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

	private Integer MAX_NUMBER_OF_RELAYS = 18;
	private UUID uuid;
	private Eventname name;
	private EventDay eventDay;
	private List<Relay> relays = new ArrayList<Relay>(MAX_NUMBER_OF_RELAYS);

	private RelayEvent(Eventname anEventName, EventDay anEventDay) {
		super();
		uuid = UUID.randomUUID();
		name = anEventName;
		eventDay = anEventDay;
	}

	public static RelayEvent duesseldorf() {
		Eventname eventName = Eventname.newInstance("MetroGroup Marathon Düsseldorf");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		return new RelayEvent(eventName, eventDay);
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	static public RelayEvent newInstance(Eventname anEventName, EventDay anEventDay) {
		return new RelayEvent(anEventName, anEventDay);
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

	public Integer getMaxNumberOfRelays() {
		return MAX_NUMBER_OF_RELAYS;
	}

	public Integer getNumberOfRelays() {
		return relays.size();
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