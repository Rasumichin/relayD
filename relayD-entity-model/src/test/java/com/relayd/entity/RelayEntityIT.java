package com.relayd.entity;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
public class RelayEntityIT extends EntityIT {
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
		RelayEventEntity eventToBeInserted = RelayEventEntity.newInstance();
		eventToBeInserted.setEventName("Foo Event");
		eventToBeInserted.setEventDay(new Date(System.currentTimeMillis()));
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
	public void testInsertRelayEntity() {
		String expected = UUID.randomUUID().toString();
		insertRelayEntity(expected);

		RelayEntity result = findRelayEntityById(expected);
		assertNotNull("RelayEntity could not be found with 'id=" + expected + "'!", result);

		String actual = result.getId();
		assertEquals("RelayEntity could not be found with 'id=" + expected + "'!", expected, actual);

		String relaynameActual = result.getRelayname();
		String relaynameExpected = "Four Star Runners";

		assertEquals("[relayname] not correct!", relaynameExpected, relaynameActual);

		Long durationActual = result.getDuration();
		Long durationExpected = 17000L;

		assertEquals("[duration] not correct!", durationExpected, durationActual);
	}

	@Test
	public void testInsertRelayEntity_with_one_new_participant() {
		int expected = 1;
		RelayEntity sut = getRelayEntityWithParticipants(Integer.valueOf(expected));
		String expectedId = sut.getParticipantEntities().get(0).getId();

		persistEntity(sut);

		RelayEntity result = findRelayEntityById(sut.getId());
		List<ParticipantEntity> participants = result.getParticipantEntities();
		int actual = participants.size();
		assertEquals("Relation to 'ParticipantEntity' has not been inserted correctly!", expected, actual);

		String actualId = participants.get(0).getId();
		assertEquals("Relation to 'ParticipantEntity' has not been inserted correctly!", expectedId, actualId);
	}

	@Test
	public void testInsertRelayEntity_with_multiple_new_participants() {
		int expected = 3;
		RelayEntity sut = getRelayEntityWithParticipants(Integer.valueOf(expected));

		persistEntity(sut);

		RelayEntity result = findRelayEntityById(sut.getId());
		List<ParticipantEntity> participants = result.getParticipantEntities();
		int actual = participants.size();
		assertEquals("Relation to 'ParticipantEntity' has not been inserted correctly!", expected, actual);
	}

	@Test
	public void testUpdateRelayEntity_Set_Relayname() {
		String id = UUID.randomUUID().toString();
		insertRelayEntity(id);
		RelayEntity relayEntity = findRelayEntityById(id);

		String expected = "New Relayname";
		relayEntity.setRelayname(expected);

		RelayEntity result = mergeEntity(relayEntity);

		String actual = result.getRelayname();
		assertEquals("[relayName] has not been updated correctly!", expected, actual);
	}

	@Test
	public void testUpdateRelayEntity_Add_Participant() {
		String id = UUID.randomUUID().toString();
		insertRelayEntity(id);
		RelayEntity relayEntity = findRelayEntityById(id);

		assertTrue("Relation to 'ParticipantEntity' has not been updated correctly!", relayEntity.getParticipantEntities().isEmpty());

		ParticipantEntity participantEntity = getDefaultParticipantEntity(UUID.randomUUID().toString());
		relayEntity.addParticipantEntity(participantEntity);

		RelayEntity result = mergeEntity(relayEntity);

		List<ParticipantEntity> participants = result.getParticipantEntities();
		int expected = 1;
		int actual = participants.size();
		assertEquals("Relation to 'ParticipantEntity' has not been updated correctly!", expected, actual);
	}

	@Test
	public void testUpdateRelayEntity_Remove_Participant() {
		int initialParticipants = 3;
		RelayEntity sut = getRelayEntityWithParticipants(Integer.valueOf(initialParticipants));
		persistEntity(sut);

		sut = findRelayEntityById(sut.getId());
		ParticipantEntity participantToBeRemoved = sut.getParticipantEntities().get(0);
		sut.removeParticipantEntity(participantToBeRemoved);

		RelayEntity result = mergeEntity(sut);

		List<ParticipantEntity> participants = result.getParticipantEntities();
		int expected = 2;
		int actual = participants.size();
		assertEquals("Relation to 'ParticipantEntity' has not been removed correctly!", expected, actual);

		String removedId = participantToBeRemoved.getId();
		participants = result.getParticipantEntities()
				.stream()
				.filter(eachParticipant -> eachParticipant.getId().equals(removedId))
				.collect(Collectors.toList());
		assertTrue("Relation to 'ParticipantEntity' has not been removed correctly!", participants.isEmpty());
	}

	@Test
	public void testUpdateRelayEntity_Update_Participant() {
		int initialParticipants = 1;
		RelayEntity sut = getRelayEntityWithParticipants(Integer.valueOf(initialParticipants));
		persistEntity(sut);

		sut = findRelayEntityById(sut.getId());
		ParticipantEntity participantEntity = sut.getParticipantEntities().get(0);
		int expected = 4;
		participantEntity.setPosition(Integer.valueOf(expected));

		RelayEntity result = mergeEntity(sut);

		participantEntity = result.getParticipantEntities().get(0);
		int actual = participantEntity.getPosition().intValue();
		assertEquals("Relation to 'ParticipantEntity' has not been updated correctly!", expected, actual);
	}

	@Test
	public void testUpdateRelayEntity_Set_RelayEventEntity() {
		String id = UUID.randomUUID().toString();
		insertRelayEntity(id);
		RelayEntity relayEntity = findRelayEntityById(id);

		RelayEventEntity expected = RelayEventEntity.newInstance();
		expected.setEventName("New Event");
		expected.setEventDay(new Date(System.currentTimeMillis()));
		persistEntity(expected);

		relayEntity.setRelayEventEntity(expected);
		RelayEntity result = mergeEntity(relayEntity);

		RelayEventEntity actual = result.getRelayEventEntity();
		assertEquals("[relayEventEntity] has not been updated correctly!", expected, actual);
	}

	@Test
	public void testDeleteRelayEntity() {
		String id = UUID.randomUUID().toString();
		insertRelayEntity(id);
		RelayEntity relayEntity = findRelayEntityById(id);

		removeEntity(relayEntity);

		RelayEntity result = findRelayEntityById(id);
		assertNull("RelayEntity has not been deleted correctly!", result);
	}

	@Test
	public void testDeleteRelayEntity_with_one_participant() {
		int initialParticipants = 1;
		RelayEntity sut = getRelayEntityWithParticipants(Integer.valueOf(initialParticipants));
		persistEntity(sut);

		sut = findRelayEntityById(sut.getId());
		ParticipantEntity participantEntity = sut.getParticipantEntities().get(0);
		String participantIdNotToBeFound = participantEntity.getId();

		removeEntity(sut);

		ParticipantEntity result = getEntityManager().find(ParticipantEntity.class, participantIdNotToBeFound);
		assertNull("RelayEntity has not been deleted correctly!", result);
	}

	@Test
	public void testRelationToRelayEvent() {
		RelayEventEntity expected = getRelayEventEntity();

		String id = UUID.randomUUID().toString();
		insertRelayEntity(id);
		RelayEntity relayEntity = findRelayEntityById(id);

		RelayEventEntity actual = relayEntity.getRelayEventEntity();

		assertEquals("Relation to 'RelayEventEntity' has not been resolved correctly!", expected, actual);
	}

	private RelayEntity insertRelayEntity(String anId) {
		RelayEntity relayEntity = getDefaultRelayEntity(anId);
		persistEntity(relayEntity);

		return relayEntity;
	}

	private RelayEntity getDefaultRelayEntity(String anId) {
		RelayEntity relayEntity = RelayEntity.newInstance(UUID.fromString(anId));
		relayEntity.setRelayname("Four Star Runners");
		relayEntity.setRelayEventEntity(getRelayEventEntity());
		relayEntity.setDuration(Duration.ofSeconds(17).toMillis());

		return relayEntity;
	}

	private RelayEntity findRelayEntityById(String anId) {
		return getEntityManager().find(RelayEntity.class, anId);
	}

	private ParticipantEntity getDefaultParticipantEntity(String anId) {
		ParticipantEntity participantEntity = ParticipantEntity.newInstance(anId);
		participantEntity.setPosition(Integer.valueOf(1));
		participantEntity.setPersonEntity(getPersonEntity());

		return participantEntity;
	}

	private RelayEntity getRelayEntityWithParticipants(Integer numberOfParticipants) {
		RelayEntity relayEntity = getDefaultRelayEntity(UUID.randomUUID().toString());

		for (int i = 0; i < numberOfParticipants.intValue(); i++) {
			ParticipantEntity participantEntity = getDefaultParticipantEntity(UUID.randomUUID().toString());
			participantEntity.setPosition(Integer.valueOf(i + 1));
			relayEntity.addParticipantEntity(participantEntity);
		}

		return relayEntity;
	}
}
