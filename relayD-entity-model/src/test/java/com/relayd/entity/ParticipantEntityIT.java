package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * What makes a clean test? Three things. Readability, readability and readability.
 *  - Robert C. Martin (Chapter 9 of "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  07.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantEntityIT extends EntityIT {
	private PersonEntity personEntity;
	private Relay2Entity relayEntity;
	
	private PersonEntity getPersonEntity() {
		return personEntity;
	}
	
	private Relay2Entity getRelayEntity() {
		return relayEntity;
	}
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
		
		setUpPersonEntity();
		RelayEventEntity relayEventEntity = setUpRelayEventEntity();
		setUpRelayEntity(relayEventEntity);
	}

	private void setUpPersonEntity() {
		PersonEntity personToBeInserted = PersonEntity.newInstance(UUID.randomUUID());
		persistEntity(personToBeInserted);
		personEntity = getEntityManager().find(PersonEntity.class, personToBeInserted.getId());
	}

	private RelayEventEntity setUpRelayEventEntity() {
		RelayEventEntity relayEventEntity = new RelayEventEntity.Builder("Foo Event").build();
		persistEntity(relayEventEntity);
		relayEventEntity = getEntityManager().find(RelayEventEntity.class, relayEventEntity.getId());
		
		return relayEventEntity;
	}
	
	private void setUpRelayEntity(RelayEventEntity relayEventEntity) {
		Relay2Entity relayToBeInserted = Relay2Entity.newInstance(UUID.randomUUID().toString());
		relayToBeInserted.setRelayname("Foo Relay");
		relayToBeInserted.setRelayEventEntity(relayEventEntity);
		persistEntity(relayToBeInserted);
		relayEntity = getEntityManager().find(Relay2Entity.class, relayToBeInserted.getId());
	}

	@Test
	public void testInsertParticipantEntity() {
		String expectedId = UUID.randomUUID().toString();
		persistEntity(getDefaultParticipantEntity(expectedId));
		
		ParticipantEntity result = findById(expectedId);
		assertNotNull("ParticipantEntity could not be found with 'id=" + expectedId + "'!", result);

		String resultId = result.getId();
		assertEquals("ParticipantEntity could not be found with 'id=" + expectedId + "'!", expectedId, resultId);
	}
	
	@Test
	public void testRelationToPersonEntity() {
		PersonEntity personEntity = getPersonEntity();
		String expected = personEntity.getId();
		
		String uuid = UUID.randomUUID().toString();
		persistEntity(getDefaultParticipantEntity(uuid));
		ParticipantEntity result = findById(uuid);
		
		String actual = result.getPersonEntity().getId();

		assertEquals("Relation to 'PersonEntity' has not been correctly resolved!", expected, actual);
	}
	
	private ParticipantEntity getDefaultParticipantEntity(String anId) {
		ParticipantEntity participantEntity = ParticipantEntity.newInstance(anId);
		participantEntity.setPosition(Integer.valueOf(1));
		participantEntity.setPersonEntity(getPersonEntity());
		participantEntity.setRelay2Entity(getRelayEntity());
		
		return participantEntity;
	}

	private ParticipantEntity findById(String anId) {
		return getEntityManager().find(ParticipantEntity.class, anId);
	}
}
