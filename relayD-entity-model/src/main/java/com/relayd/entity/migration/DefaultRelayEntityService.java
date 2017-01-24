package com.relayd.entity.migration;

import com.relayd.jpa.GenericJpaDao;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  17.01.2017
 *
 */
public class DefaultRelayEntityService implements CountRelayEntityService {

	private RelayCounter relayCounter = RelayCounter.newInstance();
	private GenericJpaDao jpaDao;

	private DefaultRelayEntityService(GenericJpaDao aJpaDao) {
		jpaDao = aJpaDao;
	}

	public static CountRelayEntityService newInstance(GenericJpaDao aJpaDao) {
		if (aJpaDao == null) {
			throw new IllegalArgumentException("[jpaDao] must not be 'null'.");
		}
		
		return new DefaultRelayEntityService(aJpaDao);
	}

	@Override
	public RelayCounter count() {
		return relayCounter;
	}

	GenericJpaDao getJpaDao() {
		return jpaDao;
	}
}
