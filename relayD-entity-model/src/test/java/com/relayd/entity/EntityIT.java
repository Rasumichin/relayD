package com.relayd.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * To create quality software, the ability to say „no“ is usually far more important than the ability to say „yes“.
 *  - Michi Henning
 *
 * @author schmollc (Christian@relayd.de)
 * @since 20.11.2016
 *
 */
public abstract class EntityIT {
	private static EntityManagerFactory EMF;
	private EntityManager entityManager;

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
		setEntityManager(EMF.createEntityManager());
	}

	@After
	public void tearDown() {
		getEntityManager().close();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager aEntityManager) {
		entityManager = aEntityManager;
	}
}