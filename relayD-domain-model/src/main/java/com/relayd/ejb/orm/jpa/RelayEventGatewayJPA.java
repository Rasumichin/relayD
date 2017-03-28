package com.relayd.ejb.orm.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.relayd.RelayEvent;
import com.relayd.ejb.RelayEventGateway;
import com.relayd.entity.RelayEventEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.10.2016
 *
 */
public class RelayEventGatewayJPA extends GatewayJPA implements RelayEventGateway {
	private EntityToRelayEventMapper relayEventEntityMapper = EntityToRelayEventMapper.newInstance();
	private RelayEventToEntityMapper relayEventMapper = RelayEventToEntityMapper.newInstance();

	private EntityToRelayEventMapper getRelayEventEntityMapper() {
		return relayEventEntityMapper;
	}

	private RelayEventToEntityMapper getRelayEventMapper() {
		return relayEventMapper;
	}

	@Override
	public List<RelayEvent> getAll() {
		List<RelayEventEntity> relayEventEntities = findAll();
		List<RelayEvent> eventsAsList = mapPersonEntityListToPersonList(relayEventEntities);

		return eventsAsList;
	}

	List<RelayEvent> mapPersonEntityListToPersonList(List<RelayEventEntity> personEntities) {
		List<RelayEvent> relayEvents = new ArrayList<>();
		for (RelayEventEntity eachEntity : personEntities) {
			relayEvents.add(getRelayEventEntityMapper().mapToRelayEvent(eachEntity));
		}

		return relayEvents;
	}

	List<RelayEventEntity> findAll() {
		@SuppressWarnings("unchecked")
		List<RelayEventEntity> result = (List<RelayEventEntity>) getJpaDao().performSelectQuery("SELECT p FROM RelayEventEntity p");

		return result;
	}

	@Override
	public void set(RelayEvent relayEvent) {
		if (relayEvent == null) {
			throw new IllegalArgumentException("[relayEvent] must not be 'null'.");
		}

		RelayEventEntity relayEventEntity = findById(relayEvent.getUuid());
		if (relayEventEntity == null) {
			relayEventEntity = RelayEventEntity.newInstance(relayEvent.getUuid());
		}
		getRelayEventMapper().mapRelayEventToEntity(relayEvent, relayEventEntity);

		getJpaDao().mergeEntity(relayEventEntity);
	}

	RelayEventEntity findById(UUID uuid) {
		RelayEventEntity result = getJpaDao().findById(RelayEventEntity.class, uuid.toString());

		return result;
	}

	@Override
	public RelayEvent get(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}

		RelayEventEntity relayEventEntity = findById(uuid);
		RelayEvent relayEvent = getRelayEventEntityMapper().mapToRelayEvent(relayEventEntity);

		return relayEvent;

	}
}