package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.Participant;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.attributes.RelayCount;
import com.relayd.entity.ParticipantEntity;
import com.relayd.entity.RelayEntity;
import com.relayd.entity.RelayEventEntity;

/**
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   20.02.2017
 *
 */
public class EntityToRelayEventMapper {

	private RelayCreator relayCreator = RelayCreator.newInstance();

	private EntityToRelayEventMapper() {
	}

	public static EntityToRelayEventMapper newInstance() {
		return new EntityToRelayEventMapper();
	}

	public RelayEvent mapToRelayEvent(RelayEventEntity relayEventEntity) {
		if (relayEventEntity == null) {
			throw new IllegalArgumentException("[relayEventEntity] must not be null!");
		}

		RelayEvent relayEvent = RelayEvent.newInstance();
		relayEvent.setUuid(UUID.fromString(relayEventEntity.getId()));
		relayEvent.setName(Eventname.newInstance(relayEventEntity.getEventName()));
		relayEvent.setEventDay(EventDay.newInstance(relayEventEntity.getEventDay().toLocalDate()));
		relayEvent.setMaxNumberOfRelays(RelayCount.newInstance(relayEventEntity.getMaxNumberOfRelays()));

		mapRelays(relayEventEntity, relayEvent);

		mapParticipants(relayEventEntity, relayEvent);

		return relayEvent;
	}

	private void mapRelays(RelayEventEntity relayEventEntity, RelayEvent relayEvent) {
		for (RelayEntity eachRelayEntity : relayEventEntity.getRelayEntities()) {
			Relay relay = relayCreator.createFrom(eachRelayEntity, relayEvent);
			relayEvent.addRelay(relay);
		}
	}

	private void mapParticipants(RelayEventEntity relayEventEntity, RelayEvent relayEvent) {
		EntityToParticipantMapper entityToParticipantMapper = EntityToParticipantMapper.newInstance();

		for (ParticipantEntity eachParticipantEntity : relayEventEntity.getParticipantEntities()) {
			Participant participant = entityToParticipantMapper.mapToParticipant(eachParticipantEntity);
			relayEvent.addParticipant(participant);
		}
	}
}