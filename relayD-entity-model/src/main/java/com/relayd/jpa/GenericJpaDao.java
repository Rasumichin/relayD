package com.relayd.jpa;

import java.util.List;

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

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	protected void commitTransaction() {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.commit();
	}

	public <T> T findById(Class<T> entityClass, Object id) {
		startTransaction();
		
		T result = getEntityManager().find(entityClass, id);
		commitTransaction();
		
		return result;
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
	
	public List<?> performSelectQuery(String jpql) {
		if (jpql == null) {
			throw new IllegalArgumentException("[jpql] must not be 'null'.");
		}
		
		Query query = getEntityManager().createQuery(jpql);
		List<?> result = query.getResultList();
		
		return result;
	}
	
	public void close() {
		getEntityManager().close();
		resetEntityManager();
	}

	private void resetEntityManager() {
		entityManager = null;
	}
}
