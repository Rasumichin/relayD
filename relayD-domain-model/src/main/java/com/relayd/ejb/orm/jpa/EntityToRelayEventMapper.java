package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.RelayEvent;
import com.relayd.attributes.*;
import com.relayd.entity.RelayEventEntity;

/**
 * 
 * @author  Rasumichin (Erik@relayd.de)
 * @since   20.02.2017
 *
 */
public class EntityToRelayEventMapper {

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
		
		return relayEvent;
	}
}
