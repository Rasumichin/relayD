package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.*;
import com.relayd.attributes.*;
import com.relayd.entity.*;

/**
 * 
 * @author  Rasumichin (Erik@relayd.de)
 * @since   20.02.2017
 *
 */
public class EntityToRelay2Mapper {

	private EntityToRelay2Mapper() {
	}
	
	public static EntityToRelay2Mapper newInstance() {
		return new EntityToRelay2Mapper();
	}

	public Relay mapToRelay(Relay2Entity relay2Entity) {
		if (relay2Entity == null) {
			throw new IllegalArgumentException("[relay2Entity] must not be null!");
		}
		
		RelayEvent relayEvent = mapRelayEventEntity(relay2Entity.getRelayEventEntity());
		Relay relay = Relay.newInstance(relayEvent);
		relay.setUuid(UUID.fromString(relay2Entity.getId()));
		relay.setRelayname(Relayname.newInstance(relay2Entity.getRelayname()));
		
		EntityToParticipantMapper participantMapper = EntityToParticipantMapper.newInstance();
		for (ParticipantEntity eachParticipantEntity : relay2Entity.getParticipantEntities()) {
			Participant participant = participantMapper.mapToParticipant(eachParticipantEntity);
			Position position = Position.newInstance(eachParticipantEntity.getPosition());
			relay.addParticipant(participant, position);
		}
		
		return relay;
	}

	RelayEvent mapRelayEventEntity(RelayEventEntity relayEventEntity) {
		if (relayEventEntity == null) {
			return null;
		}
		EntityToRelayEventMapper relayEventMapper = EntityToRelayEventMapper.newInstance();
		RelayEvent result = relayEventMapper.mapToRelayEvent(relayEventEntity);
		
		return result;
	}
}
