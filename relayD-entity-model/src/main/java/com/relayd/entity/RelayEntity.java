package com.relayd.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 18.11.2016
 *
 */
@Entity
@Table(name = "relay")
public class RelayEntity {

	@Id
	@Column
	private String id;

	@Column
	private String relayname;

	@Column
	private String participantOne;

	@Column
	private String participantTwo;

	@Column
	private String participantThree;

	@Column
	private String participantFour;

	public static RelayEntity newInstance() {
		RelayEntity relayEntity = new RelayEntity();
		relayEntity.setId(UUID.randomUUID().toString());

		return relayEntity;

	}

	public static RelayEntity newInstance(UUID anUuid) {
		if (anUuid == null) {
			throw new IllegalArgumentException("[anUuid] must not be 'null'.");
		}
		RelayEntity relayEntity = new RelayEntity();
		relayEntity.setId(anUuid.toString());

		return relayEntity;
	}

	public String getId() {
		return id;
	}

	void setId(String anId) {
		id = anId;
	}

	public String getRelayname() {
		return relayname;
	}

	public void setRelayname(String aRelayname) {
		relayname = aRelayname;
	}

	public UUID getParticipantOne() {
		return UUID.fromString(participantOne);
	}

	public void setParticipantOne(UUID anUUID) {
		participantOne = anUUID.toString();
	}

	public UUID getParticipantTwo() {
		return UUID.fromString(participantTwo);
	}

	public void setParticipantTwo(UUID anUUID) {
		participantTwo = anUUID.toString();
	}

	public UUID getParticipantThree() {
		return UUID.fromString(participantThree);
	}

	public void setParticipantThree(UUID anUUID) {
		participantThree = anUUID.toString();
	}

	public UUID getParticipantFour() {
		return UUID.fromString(participantFour);
	}

	public void setParticipantFour(UUID anUUID) {
		participantFour = anUUID.toString();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", relayname=" + relayname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		RelayEntity other = (RelayEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}