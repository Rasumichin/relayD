package com.relayd.entity.migration;

import java.util.List;

import com.relayd.jpa.GenericJpaDao;

/**
*
* @author Rasumichin (Erik@relayd.de)
* @since  17.01.2017
*
*/
public abstract class MigrationService {
	public static final String READ_ALL_RELAY_ENTITIES_SQL = "select r from RelayEntity r";
	public static final String READ_ALL_RELAY2_ENTITIES_SQL = "select r2 from Relay2Entity r2";
	
	private GenericJpaDao jpaDao;
	
	
	public static CountRelayEntityService newDefaultCountRelayEntityService(GenericJpaDao aJpaDao) {
		return DefaultCountRelayEntityService.newInstance(aJpaDao);
	}

	public static CountRelayEntityService newCountNewRelayTypeService(GenericJpaDao aJpaDao) {
		return CountNewRelayTypeService.newInstance(aJpaDao);
	}

	GenericJpaDao getJpaDao() {
		return jpaDao;
	}
	
	protected void setJpaDao(GenericJpaDao aJpaDao) {
		if (aJpaDao == null) {
			throw new IllegalArgumentException("[jpaDao] must not be 'null'.");
		}

		jpaDao = aJpaDao;
	}

	protected List<?> readRelays(String aSelectSqlStatement) {
		if (aSelectSqlStatement == null) {
			throw new IllegalArgumentException("[aSelectSqlStatement] must not be 'null'.");
		}
		
		List<?> result = getJpaDao().performSelectQuery(aSelectSqlStatement);
		
		return result;
	}
}
