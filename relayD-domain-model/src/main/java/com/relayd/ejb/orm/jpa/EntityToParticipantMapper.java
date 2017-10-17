package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.attributes.Comment;
import com.relayd.entity.ParticipantEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   17.10.2017
 *
 */
public class EntityToParticipantMapper {

	private EntityToParticipantMapper() {
		//restrict access
	}

	public static EntityToParticipantMapper newInstance() {
		return new EntityToParticipantMapper();
	}

	public Participant mapToParticipant(ParticipantEntity participantEntity) {
		if (participantEntity == null) {
			throw new IllegalArgumentException("[participantEntity] must not be 'null'.");
		}

		EntityToPersonMapper personMapper = EntityToPersonMapper.newInstance();
		Person person = personMapper.mapToPerson(participantEntity.getPersonEntity());
		Participant participant = Participant.newInstance(person);
		participant.setUuid(UUID.fromString(participantEntity.getId()));
		participant.setComment(Comment.newInstance(participantEntity.getComment()));

		return participant;
	}
}