package com.relayd.ejb.orm.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;
import com.relayd.entity.RelayEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   19.11.2016
 *
 */
public class RelayGatewayJPA extends GatewayJPA implements RelayGateway {
	private RelayToEntityMapper relayMapper = RelayToEntityMapper.newInstance();
	private EntityToRelayMapper entityMapper = EntityToRelayMapper.newInstance();

	@Override
	public void set(Relay relay) {
		if (relay == null) {
			throw new IllegalArgumentException("[relay] must not be 'null'.");
		}

		RelayEntity relayEntity = getRelayEntitiy(relay);

		getRelayMapper().mapRelayToEntity(relay, relayEntity);

		mergePersonEntity(relayEntity);
	}

	private RelayEntity getRelayEntitiy(Relay relay) {
		RelayEntity relayEntity = findById(relay.getUuid());
		if (relayEntity == null) {
			relayEntity = RelayEntity.newInstance(relay.getUuid());
		}
		return relayEntity;
	}

	RelayEntity findById(UUID uuid) {
		EntityManager em = getEntityManager();
		RelayEntity result = em.find(RelayEntity.class, uuid.toString());

		return result;
	}

	// TODO (Christian, Version 1.3): Gleicher Code wie in der PersonGatewayJPA Klasse
	void mergePersonEntity(RelayEntity relayEntity) {
		startTransaction();
		getEntityManager().merge(relayEntity);
		commitTransaction();
		endTransaction();
	}

	private RelayToEntityMapper getRelayMapper() {
		return relayMapper;
	}

	@Override
	public List<Relay> getAll() {
		List<RelayEntity> relayEntities = findAll();
		List<Relay> relays = mapPersonEntityListToPersonList(relayEntities);

		return relays;

	}

	private List<Relay> mapPersonEntityListToPersonList(List<RelayEntity> relayEntities) {

		List<Relay> relays = new ArrayList<>();
		for (RelayEntity eachEntity : relayEntities) {
			relays.add(getEntityMapper().mapToRelay(eachEntity));
		}
		return relays;
	}

	private EntityToRelayMapper getEntityMapper() {
		return entityMapper;
	}

	List<RelayEntity> findAll() {
		startTransaction();

		EntityManager em = getEntityManager();
		List<RelayEntity> result = em.createQuery("SELECT p FROM RelayEntity p", RelayEntity.class).getResultList();

		commitTransaction();
		endTransaction();

		return result;
	}

	@Override
	public Relay get(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}

		RelayEntity relayEntity = findById(uuid);
		Relay relay = getEntityMapper().mapToRelay(relayEntity);

		return relay;
	}
}