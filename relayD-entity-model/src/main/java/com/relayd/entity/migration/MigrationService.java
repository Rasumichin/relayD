package com.relayd.entity.migration;

import com.relayd.jpa.GenericJpaDao;

/**
*
* @author Rasumichin (Erik@relayd.de)
* @since  17.01.2017
*
*/
public abstract class MigrationService {
	private GenericJpaDao jpaDao;

	GenericJpaDao getJpaDao() {
		return jpaDao;
	}
	
	protected void setJpaDao(GenericJpaDao aJpaDao) {
		if (aJpaDao == null) {
			throw new IllegalArgumentException("[jpaDao] must not be 'null'.");
		}

		jpaDao = aJpaDao;
	}
}
