package com.relayd.ejb.orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.relayd.jpa.GenericJpaDao;

/**
 * Abstract super class for all JPA gateway types. Provides methods to handle the EntityManager and transactions
 * in the context of Java SE.
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   09.10.2016
 *
 */
public abstract class GatewayJPA {
	private static EntityManagerFactory EM_FACTORY = Persistence.createEntityManagerFactory("dataSource");

	private GenericJpaDao jpaDao;

	protected GenericJpaDao getJpaDao() {
		if (jpaDao == null) {
			jpaDao = GenericJpaDao.newInstance(createEntityManager());
		}

		return jpaDao;
	}

	protected EntityManager createEntityManager() {
		return EM_FACTORY.createEntityManager();
	}
}