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
		insertRelay2Entity(expectedId);
		
		Relay2Entity result = findById(expectedId);
		assertNotNull("Relay2Entity could not be found with 'id=" + expectedId + "'!", result);

		String resultId = result.getId();
		assertEquals("Relay2Entity could not be found with 'id=" + expectedId + "'!", expectedId, resultId);
	}
	

	private Relay2Entity findById(String anId) {
		return getEntityManager().find(Relay2Entity.class, anId);
	}

	@Test
	public void testRelationToRelayEvent() {
		RelayEventEntity expected = getRelayEventEntity();

		String id = UUID.randomUUID().toString();
		insertRelay2Entity(id);
		Relay2Entity relay2Entity = findById(id);
		
		RelayEventEntity actual = relay2Entity.getRelayEventEntity();
		
		assertEquals("Relation to 'RelayEvent' has not been correctly resolved!", expected, actual);
	}
	
	@Test
	public void testUpdateRelay2Entity_Set_Relayname() {
		String id = UUID.randomUUID().toString();
		insertRelay2Entity(id);
		Relay2Entity relay2Entity = findById(id);
		
		String expected = "New Relayname";
		relay2Entity.setRelayname(expected);
		
		Relay2Entity result = mergeRelay2Entity(relay2Entity);
		
		String actual = result.getRelayname();
		assertEquals("[relayName] has not been updated correctly!", expected, actual);
	}

	@Test
	public void testDeleteRelay2Entity() {
		String id = UUID.randomUUID().toString();
		insertRelay2Entity(id);
		Relay2Entity relay2Entity = findById(id);
		
		removeRelay2Entity(relay2Entity);
		
		Relay2Entity result = findById(id);
		assertNull("Relay2Entity has not been deleted correctly!", result);
	}

	private Relay2Entity insertRelay2Entity(String expectedId) {
		Relay2Entity relay2Entity = Relay2Entity.newInstance(expectedId);
		relay2Entity.setRelayname("Four Star Runners");
		relay2Entity.setRelayEventEntity(getRelayEventEntity());

		EntityTransaction tx = getEntityManager().getTransaction();

		tx.begin();
		getEntityManager().persist(relay2Entity);
		tx.commit();

		getEntityManager().clear();
		
		return relay2Entity;
	}

	private Relay2Entity mergeRelay2Entity(Relay2Entity relay2Entity) {
		EntityTransaction tx = getEntityManager().getTransaction();

		tx.begin();
		Relay2Entity result = getEntityManager().merge(relay2Entity);
		tx.commit();

		getEntityManager().clear();
		
		return result;
	}

	private void removeRelay2Entity(Relay2Entity relay2Entity) {
		EntityTransaction tx = getEntityManager().getTransaction();

		tx.begin();
		getEntityManager().remove(relay2Entity);
		tx.commit();

		getEntityManager().clear();
	}
}
