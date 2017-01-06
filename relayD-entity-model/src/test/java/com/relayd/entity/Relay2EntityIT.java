package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.persistence.EntityTransaction;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * Classes should be small!
 *  - Jeff Langr (Chapter 10 of Robert C. Martin's "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  06.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Relay2EntityIT extends EntityIT {
	private RelayEventEntity relayEventEntity;
	
	private RelayEventEntity getRelayEventEntity() {
		return relayEventEntity;
	}
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
		
		RelayEventEntity eventToBeInserted = new RelayEventEntity.Builder("Foo Event").build();
		EntityTransaction tx = getEntityManager().getTransaction();

		tx.begin();
		getEntityManager().persist(eventToBeInserted);
		tx.commit();

		getEntityManager().clear();
		
		relayEventEntity = getEntityManager().find(RelayEventEntity.class, eventToBeInserted.getId());
	}

	@Test
	public void testInsertRelay2Entity() {
		String expectedId = UUID.randomUUID().toString();
		Relay2Entity sut = Relay2Entity.newInstance(expectedId);
		sut.setRelayname("Four Star Runners");
		sut.setRelayEventEntity(getRelayEventEntity());

		EntityTransaction tx = getEntityManager().getTransaction();

		tx.begin();
		getEntityManager().persist(sut);
		tx.commit();

		getEntityManager().clear();
		
		Relay2Entity result = getEntityManager().find(Relay2Entity.class, expectedId);
		assertNotNull("Relay2Entity could not be found with 'id=" + expectedId + "'!", result);

		String resultId = result.getId();
		assertEquals("Relay2Entity could not be found with 'id=" + expectedId + "'!", expectedId, resultId);
	}
}
