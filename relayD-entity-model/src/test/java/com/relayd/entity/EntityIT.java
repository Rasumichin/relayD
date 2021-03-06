package com.relayd.entity;

import javax.persistence.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.relayd.jpa.GenericJpaDao;

/**
 * To create quality software, the ability to say „no“ is usually far more important than the ability to say „yes“.
 *  - Michi Henning
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.11.2016
 *
 */
public abstract class EntityIT {
	private static EntityManagerFactory EMF;
	private GenericJpaDao jpaDao;

	@BeforeClass
	public static void setUpBeforeClass() {
		EMF = Persistence.createEntityManagerFactory("dataSource");
	}

	@AfterClass
	public static void tearDownAfterClass() {
		EMF.close();
	}

	@Before
	public void setUp() {
		jpaDao = GenericJpaDao.newInstance(EMF.createEntityManager());
	}

	@After
	public void tearDown() {
		getJpaDao().close();
	}

	protected EntityManager getEntityManager() {
		return getJpaDao().getEntityManager();
	}
	
	private GenericJpaDao getJpaDao() {
		return jpaDao;
	}

	protected <T> void removeEntity(T anEntity) {
		getJpaDao().removeEntity(anEntity);
	}

	protected <T> T mergeEntity(T anEntity) {
		T result = getJpaDao().mergeEntity(anEntity);
		
		return result;
	}

	protected <T> void persistEntity(T anEntity) {
		getJpaDao().persistEntity(anEntity);
	}
}