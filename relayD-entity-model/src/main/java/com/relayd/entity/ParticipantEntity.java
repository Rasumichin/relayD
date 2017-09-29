package com.relayd.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.openjpa.persistence.jdbc.ForeignKey;

/**
 * @author schmollc (Christian@relayd.de)
 * @since  14.12.2016
 *
 */
@Entity
@Table(name = "participant")
public class ParticipantEntity {

	@Id
	@Column(length = 36)
	private String id;

	@Column(name = "info", length = 1024)
	private String comment;

	@ManyToOne
	@Column(name = "personId", nullable = false, length = 36)
	@ForeignKey
	private PersonEntity personEntity;

	@ManyToOne
	@Column(name = "relayEventId", nullable = false, length = 36)
	@ForeignKey
	private RelayEventEntity relayEventEntity;

	public static ParticipantEntity newInstance() {
		return ParticipantEntity.newInstance(UUID.randomUUID());
	}

	public static ParticipantEntity newInstance(UUID anUuid) {
		if (anUuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}

		ParticipantEntity participantEntity = new ParticipantEntity();
		participantEntity.setId(anUuid.toString());

		return participantEntity;
	}

	private void setId(String anId) {
		id = anId;
	}

	public String getId() {
		return id;
	}

	public void setComment(String aComment) {
		comment = aComment;
	}

	public String getComment() {
		return comment;
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

	public void setRelayEventEntity(RelayEventEntity aRelayEventEntity) {
		if (aRelayEventEntity == null) {
			throw new IllegalArgumentException("[aRelayEvent] must not be 'null'.");
		}

		relayEventEntity = aRelayEventEntity;
	}

	public RelayEventEntity getRelayEventEntity() {
		return relayEventEntity;
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