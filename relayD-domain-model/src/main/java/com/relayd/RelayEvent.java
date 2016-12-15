package com.relayd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.relayd.attributes.Distance;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.attributes.Position;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 19.05.2016
 *
 */
public class RelayEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	static Integer MAX_NUMBER_OF_RELAYS = 18;
	static Integer MAX_NUMBER_OF_TRACKS = 4;

	private UUID uuid;
	private Eventname name;
	private EventDay eventDay;
	private Set<Relay> relays = new HashSet<Relay>(MAX_NUMBER_OF_RELAYS);
	private List<Track> tracks = new ArrayList<Track>(MAX_NUMBER_OF_TRACKS);

	private RelayEvent() {

	}

	private RelayEvent(Eventname anEventName, EventDay anEventDay) {
		super();
		uuid = UUID.randomUUID();
		name = anEventName;
		eventDay = anEventDay;

		tracks.add(Track.newInstance(Distance.kilometers(new BigDecimal("11.3"))));
		tracks.add(Track.newInstance(Distance.kilometers(new BigDecimal("13.1"))));
		tracks.add(Track.newInstance(Distance.kilometers(new BigDecimal("8.6"))));
		tracks.add(Track.newInstance(Distance.kilometers(new BigDecimal("9.2"))));

	}

	public static RelayEvent duesseldorf() {
		return RelayEventDuesseldorf.instance();
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

	public Track getTrackForPosition(Position position) {
		int index = position.getValue() - 1;
		Track track = tracks.get(index);
		return track;
	}
	
	public String getTrackDistanceOne() {
		Track track = getTrackForPosition(Position.FIRST);
		Distance distance = track.getDistance();
		return distance.toStringWithUnity();
	}
	
	public String getTrackDistanceTwo() {
		Track track = getTrackForPosition(Position.SECOND);
		Distance distance = track.getDistance();
		return distance.toStringWithUnity();
	}
	
	public String getTrackDistanceThree() {
		Track track = getTrackForPosition(Position.THIRD);
		Distance distance = track.getDistance();
		return distance.toStringWithUnity();
	}
	
	public String getTrackDistanceFour() {
		Track track = getTrackForPosition(Position.FOURTH);
		Distance distance = track.getDistance();
		return distance.toStringWithUnity();
	}

	public void addRelay(Relay relay) {
		// Warum "<=" und nicht "==" ?
		// Wenn man irgendwann einen Fehler einbaut und es wären aus irgendwelchen Gründen 19 Relays
		// in der Liste würde diese Methode dann nicht mehr motzen, da ja "nur" bei size == 18 eine Exception fliegt!
		if (MAX_NUMBER_OF_RELAYS <= relays.size()) {
			throw new IllegalArgumentException();
		}
		relays.add(relay);
	}

	public Set<Relay> getRelays() {
		return Collections.unmodifiableSet(relays);
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

	static final class RelayEventDuesseldorf extends RelayEvent {
		private static final long serialVersionUID = -3419762542997706672L;
		private static final Eventname eventName = Eventname.newInstance("Metro Group Marathon Düsseldorf");
		private static final EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		private static final RelayEventDuesseldorf SINGLETON = new RelayEventDuesseldorf();

		RelayEventDuesseldorf() {
			super(eventName, eventDay);
		}

		private static RelayEventDuesseldorf instance() {
			return SINGLETON;
		}
	}
}