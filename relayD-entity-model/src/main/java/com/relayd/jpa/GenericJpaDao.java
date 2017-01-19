package com.relayd.jpa;

import javax.persistence.*;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  18.01.2017
 *
 */
public class GenericJpaDao {
	private EntityManager entityManager;
	
	private GenericJpaDao(EntityManager anEntityManager) {
		entityManager = anEntityManager;
	}

	public static GenericJpaDao newInstance(EntityManager anEntityManager) {
		if (anEntityManager == null) {
			throw new IllegalArgumentException("[anEntityManager] must not be 'null'.");
		}

		return new GenericJpaDao(anEntityManager);
	}

	EntityManager getEntityManager() {
		return entityManager;
	}

	public <T> void persistEntity(T anEntity) {
		startTransaction();

		getEntityManager().persist(anEntity);
		commitTransaction();

		getEntityManager().clear();
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

	public <T> T mergeEntity(T anEntity) {
		startTransaction();

		T result = getEntityManager().merge(anEntity);
		commitTransaction();

		getEntityManager().clear();
		
		return result;
	}

	public <T> void removeEntity(T anEntity) {
		startTransaction();

		getEntityManager().remove(anEntity);
		commitTransaction();

		getEntityManager().clear();
	}
	
	public void close() {
		getEntityManager().close();
		resetEntityManager();
	}

	private void resetEntityManager() {
		entityManager = null;
	}
}
