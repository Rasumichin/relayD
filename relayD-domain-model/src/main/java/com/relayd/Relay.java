package com.relayd;

import java.io.Serializable;
import java.util.UUID;

import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   23.03.2016
 *
 */
public class Relay implements Serializable {
	private static final long serialVersionUID = 1L;

	UUID uuid = null;
	private Relayname relayname = null;
	private RelayEvent relayEvent = null;

	private Relay(RelayEvent aRelayEvent) {
		uuid = UUID.randomUUID();
		relayEvent = aRelayEvent;
	}

	public static Relay newInstance() {
		return new Relay(null);
	}

	public static Relay newInstance(RelayEvent relayEvent) {
		return new Relay(relayEvent);
	}

	public RelayEvent getRelayEvent() {
		return relayEvent;
	}

	public void setRelayname(Relayname aRelayname) {
		relayname = aRelayname;
	}

	public Relayname getRelayname() {
		return relayname;
	}

	public UUID getUuid() {
		return uuid;
	}

	public Track getTrackFor(Position position) {
		Track track = relayEvent.getTrackForPosition(position);
		return track;
	}

	public void addPersonRelay(PersonRelay person, Position position) {
		Track track = getTrackFor(position);
		track.setPersonRelay(person);
	}

	@Override
	public String toString() {
		return "Relay: " + getRelayname();
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
		Relay other = (Relay) obj;
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