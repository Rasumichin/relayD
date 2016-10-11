package com.relayd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import com.relayd.attributes.Distance;
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
	// TODO Ein Jahr ist ein Jahr, und kein Integer!!!!
	private Integer year;
	private List<Track> tracks = new ArrayList<>();

	private Relay(Integer aYear) {
		uuid = UUID.randomUUID();
		year = aYear;

		tracks.add(Track.newInstance(Distance.newInstance(new BigDecimal("11.3"))));
		tracks.add(Track.newInstance(Distance.newInstance(new BigDecimal("8.6"))));
		tracks.add(Track.newInstance(Distance.newInstance(new BigDecimal("9.2"))));
		tracks.add(Track.newInstance(Distance.newInstance(new BigDecimal("13.1"))));
	}

	public static Relay newInstance() {
		return new Relay(new GregorianCalendar().get(Calendar.YEAR));
	}

	public static Relay newInstance(Integer aYear) {
		return new Relay(aYear);
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

	public Integer getYear() {
		return year;
	}

	public Track getTrackFor(Position position) {
		int index = position.getValue() - 1;
		Track track = tracks.get(index);
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