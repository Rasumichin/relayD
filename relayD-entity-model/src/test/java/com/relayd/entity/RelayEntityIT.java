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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Gesundheit erhalten statt Krankheit zu bekämpfen.
 * Guten Code schreiben statt unsauberen zu refactoren.
 *  - Tibetische Weisheit
 *
 * @author schmollc (Christian@relayd.de)
 * @since 18.11.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEntityIT {
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
	public void testInsertRelayEntity() {
		RelayEntity sut = RelayEntity.newInstance();
		sut.setRelayname("Staubwolke");

		EntityTransaction tx = entityManager.getTransaction();

		tx.begin();
		entityManager.persist(sut);
		tx.commit();

		entityManager.clear();
		RelayEntity result = entityManager.find(RelayEntity.class, sut.getId());
		assertEquals("RelayEntity could not be found with 'id=" + sut.getId() + "'.", sut.getId(), result.getId());
	}
}
