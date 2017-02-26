package com.relayd.ejb.orm.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;
import com.relayd.entity.*;

/**
 * 
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   19.11.2016
 *
 */
public class RelayGatewayJPA extends GatewayJPA implements RelayGateway {
	private RelayToEntityMapper relayMapper = RelayToEntityMapper.newInstance();
	private EntityToRelay2Mapper entityMapper = EntityToRelay2Mapper.newInstance();

	@Override
	public void set(Relay relay) {
		if (relay == null) {
			throw new IllegalArgumentException("[relay] must not be 'null'.");
		}

		Relay2Entity relayEntity = getRelayEntity(relay);
//		getRelayMapper().mapRelayToEntity(relay, relayEntity);
//
//		getJpaDao().mergeEntity(relayEntity);
	}

	private Relay2Entity getRelayEntity(Relay relay) {
		Relay2Entity relayEntity = findById(relay.getUuid());
		if (relayEntity == null) {
			relayEntity = Relay2Entity.newInstance(relay.getUuid().toString());
			RelayEventEntity relayEventEntity = getJpaDao().findById(RelayEventEntity.class, relay.getRelayEvent().getUuid().toString());
			relayEntity.setRelayEventEntity(relayEventEntity);
		}
		
		return relayEntity;
	}

	Relay2Entity findById(UUID uuid) {
		Relay2Entity result = getJpaDao().findById(Relay2Entity.class, uuid.toString());

		return result;
	}

	private RelayToEntityMapper getRelayMapper() {
		return relayMapper;
	}

	@Override
	public List<Relay> getAll() {
		List<Relay2Entity> relayEntities = findAll();
		List<Relay> relays = mapPersonEntityListToPersonList(relayEntities);

		return relays;

	}

	private List<Relay> mapPersonEntityListToPersonList(List<Relay2Entity> relayEntities) {
		List<Relay> relays = new ArrayList<>();
		for (Relay2Entity eachEntity : relayEntities) {
			relays.add(getEntityMapper().mapToRelay(eachEntity));
		}
		
		return relays;
	}

	private EntityToRelay2Mapper getEntityMapper() {
		return entityMapper;
	}

	List<Relay2Entity> findAll() {
		@SuppressWarnings("unchecked")
		List<Relay2Entity> result = (List<Relay2Entity>) getJpaDao().performSelectQuery("SELECT p FROM Relay2Entity p");

		return result;
	}

	@Override
	public Relay get(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}

		Relay2Entity relayEntity = findById(uuid);
		Relay relay = getEntityMapper().mapToRelay(relayEntity);

		return relay;
	}
}