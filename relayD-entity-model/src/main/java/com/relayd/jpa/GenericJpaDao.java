package com.relayd.jpa;

import javax.persistence.EntityManager;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  18.01.2017
 *
 */
public class GenericJpaDao {

	private GenericJpaDao(EntityManager anEntityManager) {

	}

	public static GenericJpaDao newInstance(EntityManager anEntityManager) {
		if (anEntityManager == null) {
			throw new IllegalArgumentException("[anEntityManager] must not be 'null'.");
		}

		return new GenericJpaDao(anEntityManager);
	}
}
