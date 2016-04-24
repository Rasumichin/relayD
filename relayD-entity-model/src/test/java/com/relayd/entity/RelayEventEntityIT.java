package com.relayd.entity;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Rasumichin (Erik@cloud.franke-net.com)
 * @since 22.04.2016
 * status initial
 */
public class RelayEventEntityIT {
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
		entityManager = EMF.createEntityManager();
	}
	
	@After
	public void tearDown() {
		entityManager.close();
	}
	
	@Test
	public void testInsertRelayEventEntity() {
		RelayEventEntity sut = new RelayEventEntity.Builder("title").build();
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.begin();
		entityManager.persist(sut);
		tx.commit();

		entityManager.clear();
		RelayEventEntity result = entityManager.find(RelayEventEntity.class, sut.getId());
		assertEquals("RelayEventEntity could not be found with 'id=" + sut.getId() + "'.", sut.getId(), result.getId());
	}
}
