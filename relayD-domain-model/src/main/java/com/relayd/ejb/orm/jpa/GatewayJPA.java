package com.relayd.ejb.orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
	private EntityManager entityManager;

	protected GenericJpaDao getJpaDao() {
		if (jpaDao == null) {
			jpaDao = GenericJpaDao.newInstance(createEntityManager());
		}

		return jpaDao;
	}

	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = createEntityManager();
		}

		return entityManager;
	}

	protected EntityManager createEntityManager() {
		return EM_FACTORY.createEntityManager();
	}
	
	protected void startTransaction() {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
	}

	protected void commitTransaction() {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.commit();
	}

	protected void endTransaction() {
		getEntityManager().close();
		resetEntityManager();
	}

	private void resetEntityManager() {
		entityManager = null;
	}

	void mergeEntity(Object entity) {
		startTransaction();
		getEntityManager().merge(entity);
		commitTransaction();
		endTransaction();
	}

}