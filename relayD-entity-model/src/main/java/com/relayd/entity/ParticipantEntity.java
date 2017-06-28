package com.relayd.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.openjpa.persistence.jdbc.ForeignKey;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  14.12.2016
 *
 */
@Entity
@Table(name = "participant")
public class ParticipantEntity {

	@Id
	@Column(length = 36)
	private String id;

	@Column(name = "relayPosition", nullable = false)
	private Integer position;

	@Column(nullable = false)
	private Long duration = 0L;

	@ManyToOne
	@Column(name = "personId", nullable = false, length = 36)
	@ForeignKey
	private PersonEntity personEntity;

	@ManyToOne
	@Column(name = "relayId", nullable = false, length = 36)
	@ForeignKey
	private RelayEntity relayEntity;

	public static ParticipantEntity newInstance() {
		return ParticipantEntity.newInstance(UUID.randomUUID().toString());
	}

	public static ParticipantEntity newInstance(String anId) {
		// TODO EL (2017-01-07): Introduce a class to handle strings that should represent a UUID.
		if (anId == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}
		try {
			UUID.fromString(anId);
		} catch (IllegalArgumentException iAEx) {
			throw new IllegalArgumentException("[anId] is not a valid representation of an UUID.");
		}
		ParticipantEntity participantEntity = new ParticipantEntity();
		participantEntity.setId(anId);

		return participantEntity;
	}

	private void setId(String anId) {
		id = anId;
	}

	public String getId() {
		return id;
	}

	public void setPosition(Integer aPosition) {
		if (aPosition == null) {
			throw new IllegalArgumentException("[aPosition] must not be 'null'.");
		}
		position = aPosition;
	}

	public Integer getPosition() {
		return position;
	}

	public void setDuration(Long aDuration) {
		if (aDuration == null) {
			throw new IllegalArgumentException("[aDuration] must not be 'null'.");
		}
		duration = aDuration;

	}

	public Long getDuration() {
		return duration;
	}

	public void setPersonEntity(PersonEntity aPersonEntity) {
		if (aPersonEntity == null) {
			throw new IllegalArgumentException("[aPersonEntity] must not be 'null'.");
		}
		personEntity = aPersonEntity;
	}

	public PersonEntity getPersonEntity() {
		return personEntity;
	}

	public UUID getUuidPerson() {
		return UUID.fromString(getPersonEntity().getId());
	}

	void setRelayEntity(RelayEntity aRelayEntity) {
		relayEntity = aRelayEntity;
	}

	public RelayEntity getRelayEntity() {
		return relayEntity;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + getId() + "]";
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
		ParticipantEntity other = (ParticipantEntity) obj;
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