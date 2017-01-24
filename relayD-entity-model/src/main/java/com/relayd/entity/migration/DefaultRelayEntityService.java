package com.relayd.entity.migration;

import javax.persistence.*;

import com.relayd.jpa.GenericJpaDao;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  17.01.2017
 *
 */
public class DefaultRelayEntityService implements CountRelayEntityService {
	private static final String PU_NAME = "dataSource";

	private RelayCounter relayCounter = RelayCounter.newInstance();

	private DefaultRelayEntityService() {
	}
	
	public static CountRelayEntityService newInstance() {
		return new DefaultRelayEntityService();
	}

	@Override
	public RelayCounter getRelayCounter() {
		return relayCounter;
	}

	private void count() {
		GenericJpaDao jpaDao = getJpaDao();
	}

	GenericJpaDao getJpaDao() {
		GenericJpaDao jpaDao = GenericJpaDao.newInstance(getEntityManager());
		
		return jpaDao;
	}
	
	EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU_NAME);
		
		return emf.createEntityManager();
	}
}
