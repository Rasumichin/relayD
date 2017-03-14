package com.relayd.ejb.orm.jpa;

import com.relayd.*;
import com.relayd.entity.ParticipantEntity;

/**
 * 
 * @author  Rasumichin (Erik@relayd.de)
 * @since   23.02.2017
 *
 */
public class EntityToParticipantMapper {
	
	private EntityToParticipantMapper() {
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
		
		return participant;
	}
}
