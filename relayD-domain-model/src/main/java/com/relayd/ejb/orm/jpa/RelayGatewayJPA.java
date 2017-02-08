package com.relayd.ejb.orm.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;
import com.relayd.entity.RelayEntity;
import com.relayd.jpa.GenericJpaDao;

/**
 * 
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   19.11.2016
 *
 */
public class RelayGatewayJPA extends GatewayJPA implements RelayGateway {
	private GenericJpaDao jpaDao;

	private RelayToEntityMapper relayMapper = RelayToEntityMapper.newInstance();
	private EntityToRelayMapper entityMapper = EntityToRelayMapper.newInstance();

	@Override
	public void set(Relay relay) {
		if (relay == null) {
			throw new IllegalArgumentException("[relay] must not be 'null'.");
		}

		RelayEntity relayEntity = getRelayEntity(relay);
		getRelayMapper().mapRelayToEntity(relay, relayEntity);

		getJpaDao().mergeEntity(relayEntity);
	}

	private RelayEntity getRelayEntity(Relay relay) {
		RelayEntity relayEntity = findById(relay.getUuid());
		if (relayEntity == null) {
			relayEntity = RelayEntity.newInstance(relay.getUuid());
		}
		
		return relayEntity;
	}

	RelayEntity findById(UUID uuid) {
		RelayEntity result = getJpaDao().findById(RelayEntity.class, uuid.toString());

		return result;
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
		@SuppressWarnings("unchecked")
		List<RelayEntity> result = (List<RelayEntity>) getJpaDao().performSelectQuery("SELECT p FROM RelayEntity p");

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

	@Override
	protected EntityManager getEntityManager() {
		return getJpaDao().getEntityManager();
	}
	
	protected GenericJpaDao getJpaDao() {
		if (jpaDao == null) {
			jpaDao = GenericJpaDao.newInstance(createEntityManager());
		}

		return jpaDao;
	}
}