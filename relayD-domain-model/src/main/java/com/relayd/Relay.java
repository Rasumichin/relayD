package com.relayd;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.time.DurationFormatUtils;

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
	private Relayname relayname = Relayname.newInstance();
	private RelayEvent relayEvent = RelayEvent.newInstance();
	private List<Member> members = new ArrayList<Member>();

	private Duration duration = Duration.ZERO;

	private Relay(RelayEvent aRelayEvent) {
		uuid = UUID.randomUUID();
		relayEvent = aRelayEvent;
		initMembers();
	}

	private void initMembers() {
		for (int i = 0; i < RelayEvent.MAX_NUMBER_OF_TRACKS; i++) {
			members.add(Member.newInstance());
		}
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

	public void setDuration(Duration aDuration) {
		duration = aDuration;
	}

	public Duration getDuration() {
		return duration;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID anUuid) {
		uuid = anUuid;
	}

	public Member getMemberFor(Position position) {
		if (Position.UNKNOWN.equals(position)) {
			return Member.newInstance();
		}
		Member person = members.get(position.getValue() - 1);
		return person;
	}

	public void addMember(Member member, Position position) {
		members.set(position.getValue() - 1, member);
	}

	public Integer memberCount() {
		int count = 0;
		for (Member member : members) {
			if (!member.isEmpty()) {
				count++;
			}
		}
		return count;
	}

	public List<Member> getMembers() {
		return Collections.unmodifiableList(members);
	}

	public String getEmailList() {
		StringBuilder builder = new StringBuilder();

		for (Member each : members) {
			if (each.hasMail()) {
				builder.append(", " + each.getEmail());
			}
		}
		String output = builder.toString();
		output = output.replaceFirst(", ", "");

		return output;

	}

	public boolean isMember(Participant aParticipant) {
		for (Member each : members) {
			if (aParticipant.getUuidPerson().equals(each.getUuidPerson())) {
				return true;
			}
		}
		return false;
	}

	// TODO - REL-294 - Wie reagieren wir bei einer vollen Relay und dem Versuch einen Member hinzuzufügen?
	public void addMember(Member aMember) {
		for (int index = 0; index < RelayEvent.MAX_NUMBER_OF_TRACKS; index++) {
			if (members.get(index).isEmpty()) {
				members.set(index, aMember);
				break;
			}
		}
	}

	public String getDurationFormatted() {
		if (duration == null) {
			return "00:00:00";
		}
		return DurationFormatUtils.formatDuration(duration.toMillis(), "HH:mm:ss");
	}

	public boolean isEmpty() {
		for (Member eachMember : members) {
			if (!eachMember.isEmpty()) {
				return false;
			}
		}
		return true;
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

	@Override
	public String toString() {
		return getRelayname() + " [" + memberCount() + "/" + RelayEvent.MAX_NUMBER_OF_TRACKS + "]";
	}
}