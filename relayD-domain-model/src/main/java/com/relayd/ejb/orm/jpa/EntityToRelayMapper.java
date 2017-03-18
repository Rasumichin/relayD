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
public class EntityToRelayMapper {

	private EntityToRelayMapper() {
	}
	
	public static EntityToRelayMapper newInstance() {
		return new EntityToRelayMapper();
	}

	public Relay mapToRelay(RelayEntity relayEntity) {
		if (relayEntity == null) {
			throw new IllegalArgumentException("[relayEntity] must not be null!");
		}
		
		RelayEvent relayEvent = mapRelayEventEntity(relayEntity.getRelayEventEntity());
		Relay relay = Relay.newInstance(relayEvent);
		relay.setUuid(UUID.fromString(relayEntity.getId()));
		relay.setRelayname(Relayname.newInstance(relayEntity.getRelayname()));
		
		EntityToParticipantMapper participantMapper = EntityToParticipantMapper.newInstance();
		for (ParticipantEntity eachParticipantEntity : relayEntity.getParticipantEntities()) {
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
