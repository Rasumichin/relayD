package com.relayd.ejb.orm.jpa;

import javax.persistence.*;

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
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = EM_FACTORY.createEntityManager();
		}
		
		return entityManager;
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
}