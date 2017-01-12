package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.*;

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
	private PersonEntity personEntity;
	
	private RelayEventEntity getRelayEventEntity() {
		return relayEventEntity;
	}
	
	private PersonEntity getPersonEntity() {
		return personEntity;
	}
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
		
		setUpRelayEventEntity();
		setUpPersonEntity();
	}

	private void setUpRelayEventEntity() {
		RelayEventEntity eventToBeInserted = new RelayEventEntity.Builder("Foo Event").build();
		persistEntity(eventToBeInserted);
		relayEventEntity = getEntityManager().find(RelayEventEntity.class, eventToBeInserted.getId());
	}

	private void setUpPersonEntity() {
		PersonEntity personToBeInserted = PersonEntity.newInstance();
		personToBeInserted.setForename("Peter");
		personToBeInserted.setSurename("Principle");
		persistEntity(personToBeInserted);
		personEntity = getEntityManager().find(PersonEntity.class, personToBeInserted.getId());
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
	
	@Test
	public void testInsertRelay2Entity_with_one_new_participant() {
		Relay2Entity sut = getDefaultRelay2Entity(UUID.randomUUID().toString());
		String expected = UUID.randomUUID().toString();
		ParticipantEntity participantEntity = getDefaultParticipantEntity(expected);
		sut.addParticipantEntity(participantEntity);
		
		persistEntity(sut);
		
		Relay2Entity result = findById(sut.getId());
		List<ParticipantEntity> participants = result.getParticipantEntities();
		assertTrue("Relation to 'ParticipantEntity' has not been correctly resolved!", (participants.size() == 1));
		
		String actual = participants.get(0).getId();
		assertEquals("Relation to 'ParticipantEntity' has not been correctly resolved!", expected, actual);
	}
	
	@Test
	public void testInsertRelay2Entity_with_multiple_new_participants() {
		Relay2Entity sut = getDefaultRelay2Entity(UUID.randomUUID().toString());
		
		ParticipantEntity participantEntity = getDefaultParticipantEntity(UUID.randomUUID().toString());
		sut.addParticipantEntity(participantEntity);
		
		participantEntity = getDefaultParticipantEntity(UUID.randomUUID().toString());
		participantEntity.setPosition(Integer.valueOf(2));
		sut.addParticipantEntity(participantEntity);
		
		participantEntity = getDefaultParticipantEntity(UUID.randomUUID().toString());
		participantEntity.setPosition(Integer.valueOf(3));
		sut.addParticipantEntity(participantEntity);

		persistEntity(sut);
		
		Relay2Entity result = findById(sut.getId());
		List<ParticipantEntity> participants = result.getParticipantEntities();
		assertTrue("Relation to 'ParticipantEntity' has not been correctly resolved!", (participants.size() == 3));
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
		
		Relay2Entity result = mergeEntity(relay2Entity);
		
		String actual = result.getRelayname();
		assertEquals("[relayName] has not been updated correctly!", expected, actual);
	}

	@Test
	public void testUpdateRelay2Entity_Set_RelayEventEntity() {
		String id = UUID.randomUUID().toString();
		insertRelay2Entity(id);
		Relay2Entity relay2Entity = findById(id);
		
		RelayEventEntity expected = new RelayEventEntity.Builder("New Event").build();
		persistEntity(expected);
		
		relay2Entity.setRelayEventEntity(expected);
		Relay2Entity result = mergeEntity(relay2Entity);
		
		RelayEventEntity actual = result.getRelayEventEntity();
		assertEquals("[relayEventEntity] has not been updated correctly!", expected, actual);
	}

	@Test
	public void testDeleteRelay2Entity() {
		String id = UUID.randomUUID().toString();
		insertRelay2Entity(id);
		Relay2Entity relay2Entity = findById(id);
		
		removeEntity(relay2Entity);
		
		Relay2Entity result = findById(id);
		assertNull("Relay2Entity has not been deleted correctly!", result);
	}

	private Relay2Entity insertRelay2Entity(String anId) {
		Relay2Entity relay2Entity = getDefaultRelay2Entity(anId);
		persistEntity(relay2Entity);
		
		return relay2Entity;
	}

	private Relay2Entity getDefaultRelay2Entity(String anId) {
		Relay2Entity relay2Entity = Relay2Entity.newInstance(anId);
		relay2Entity.setRelayname("Four Star Runners");
		relay2Entity.setRelayEventEntity(getRelayEventEntity());
		
		return relay2Entity;
	}

	private Relay2Entity findById(String anId) {
		return getEntityManager().find(Relay2Entity.class, anId);
	}
	
	private ParticipantEntity getDefaultParticipantEntity(String anId) {
		ParticipantEntity participantEntity = ParticipantEntity.newInstance(anId);
		participantEntity.setPosition(Integer.valueOf(1));
		participantEntity.setPersonEntity(getPersonEntity());
		
		return participantEntity;
	}
}
