package com.relayd.entity.migration;

import javax.persistence.*;

import com.relayd.jpa.GenericJpaDao;

/**
 * This class is intended to bridge between the REST API module and the entity model.
 * 
 * @author Rasumichin (Erik@relayd.de)
 * @since  29.01.2017
 *
 */
public class RelayMigrationServiceBridge {
	private GenericJpaDao jpaDao;
	
	public static RelayMigrationServiceBridge newInstance() {
		return new RelayMigrationServiceBridge();
	}

	private RelayMigrationServiceBridge() {
		initJpaDao();
	}

	private void initJpaDao() {
		jpaDao = GenericJpaDao.newInstance(getEntityManager());
	}

	EntityManager getEntityManager() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("dataSource");
		
		return emFactory.createEntityManager();
	}

	GenericJpaDao getJpaDao() {
		return jpaDao;
	}

	public RelayCounter countOldRelays() {
		CountRelayEntityService countOldRelaysService = DefaultRelayEntityService.newInstance(getJpaDao());
		
		return countOldRelaysService.count();
	}

	public RelayCounter countNewRelays() {
		CountRelayEntityService countNewRelaysService = Relay2EntityService.newInstance(getJpaDao());
		
		return countNewRelaysService.count();
	}
}
