package com.relayd.entity;

import java.util.UUID;

import javax.persistence.*;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  14.12.2016
 *
 */
public class ParticipantEntity {
	
	@Id
	@Column
	private String id;

	public static ParticipantEntity newInstance(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}
		ParticipantEntity participantEntity = new ParticipantEntity();
		participantEntity.setId(uuid.toString());
		
		return participantEntity;
	}

	public static ParticipantEntity newInstance() {
		return ParticipantEntity.newInstance(UUID.randomUUID());
	}
	
	private void setId(String anId) {
		id = anId;
	}

	public String getId() {
		return id;
	}

	public void setPosition(Integer aPosition) {
	}

	public Integer getPosition() {
		return null;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipantEntity other = (ParticipantEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
