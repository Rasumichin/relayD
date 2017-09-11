package com.relayd.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public static ParticipantEntity newInstance() {
		return ParticipantEntity.newInstance(UUID.randomUUID().toString());
	}

	public static ParticipantEntity newInstance(String anId) {
		// TODO - REL-282 - Introduce a class to handle strings that should represent a UUID.
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
}