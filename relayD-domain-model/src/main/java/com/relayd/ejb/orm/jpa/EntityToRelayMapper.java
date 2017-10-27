package com.relayd.ejb.orm.jpa;

import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.entity.RelayEntity;
import com.relayd.entity.RelayEventEntity;

/**
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   20.02.2017
 *
 */
public class EntityToRelayMapper {

	private RelayCreator relayCreator = RelayCreator.newInstance();

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
		Relay relay = relayCreator.createFrom(relayEntity, relayEvent);

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