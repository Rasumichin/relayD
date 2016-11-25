package com.relayd.ejb.orm.jpa;

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

	// TODO Gleicher Code wie in der PersonGatewayJPA Klasse
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
		throw new UnsupportedOperationException("not implemented yet!");
	}

	@Override
	public Relay get(UUID uuid) {
		throw new UnsupportedOperationException("not implemented yet!");
	}
}