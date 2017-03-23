package com.relayd.ejb.orm.jpa;

import java.time.Duration;
import java.util.UUID;

import com.relayd.Participant;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.entity.ParticipantEntity;
import com.relayd.entity.RelayEntity;
import com.relayd.entity.RelayEventEntity;

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
		relay.setDuration(Duration.ofMillis(relayEntity.getDuration()));

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
