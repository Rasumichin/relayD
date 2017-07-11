package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.entity.initializer.RelayEntityInitializer;

/**
 * Don't pass 'Null'.
 *  - Michael Feathers (Chapter 7 of Robert C. Martin's "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  03.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEntityTest {

	@Test
	public void testNewInstance() {
		RelayEntity sut = RelayEntity.newInstance();

		String result = sut.getId();
		assertNotNull("Instance has not been created correctly!", result);
	}

	@Test
	public void testNewInstance_withUuid() {
		UUID expected = UUID.randomUUID();
		RelayEntity sut = RelayEntity.newInstance(expected);

		UUID actual = UUID.fromString(sut.getId());
		assertEquals("[id] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forUuidWithNullValue() {
		@SuppressWarnings("unused")
		RelayEntity sut = RelayEntity.newInstance(null);
	}

	@Test
	public void testRelayname() {
		String relayname = "Staubwolke";
		RelayEntity sut = RelayEntity.newInstance();

		sut.setRelayname(relayname);
		assertEquals("[relayname] has not been set correctly!", relayname, sut.getRelayname());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRelayname_withNull() {
		RelayEntity sut = RelayEntity.newInstance();
		sut.setRelayname(null);
	}

	@Test
	public void testDuration() {
		Long expected = 0L;
		RelayEntity sut = RelayEntity.newInstance();

		sut.setDuration(expected);
		Long actual = sut.getDuration();

		assertEquals("[duration] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDuration_withNull() {
		RelayEntity sut = RelayEntity.newInstance();

		sut.setDuration(null);
	}

	@Test
	public void testRelayEventEntity() {
		RelayEntity sut = RelayEntity.newInstance();
		RelayEventEntity expected = RelayEventEntity.newInstance();
		expected.setEventName("MyRelayEvent");

		sut.setRelayEventEntity(expected);
		RelayEventEntity actual = sut.getRelayEventEntity();

		assertEquals("[relayEventEntity] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRelayEventEntity_withNull() {
		RelayEntity sut = RelayEntity.newInstance();
		sut.setRelayEventEntity(null);
	}

	@Test
	public void testGetParticipants_initialSize() {
		RelayEntity sut = RelayEntity.newInstance();

		List<ParticipantEntity> result = sut.getParticipantEntities();

		assertTrue("Initialization of [participantEntities] is not correct!", result.isEmpty());
	}

	@Test
	public void testAddParticipantEntity() {
		RelayEntity sut = RelayEntityInitializer.newRelayEntityWithOneParticipant();

		List<ParticipantEntity> result = sut.getParticipantEntities();
		boolean actual = (result.size() == 1);
		assertTrue("Adding of a 'ParticipantEntity' was not successful!", actual);
	}

	@Test
	public void testRemoveParticipantEntity_element_is_present() {
		RelayEntity sut = RelayEntityInitializer.newRelayEntityWithOneParticipant();

		// Create another instance with the same 'id' and let the 'sut' remove this one.
		String uuid = sut.getParticipantEntities().get(0).getId();
		ParticipantEntity participantEntityToBeRemoved = ParticipantEntity.newInstance(uuid);

		sut.removeParticipantEntity(participantEntityToBeRemoved);

		List<ParticipantEntity> result = sut.getParticipantEntities();
		assertTrue("Removing of a 'ParticipantEntity' was not successful!", result.isEmpty());
	}

	@Test
	public void testRemoveParticipantEntity_element_is_not_present() {
		RelayEntity sut = RelayEntityInitializer.newRelayEntityWithOneParticipant();
		ParticipantEntity participantEntityToBeRemoved = ParticipantEntity.newInstance();

		sut.removeParticipantEntity(participantEntityToBeRemoved);

		List<ParticipantEntity> result = sut.getParticipantEntities();
		boolean actual = (result.size() == 1);
		assertTrue("Removing of a 'ParticipantEntity' was not successful!", actual);
	}

	@Test
	public void testToString() {
		RelayEntity sut = RelayEntity.newInstance();
		String relayname = "Staubwolke";
		sut.setRelayname(relayname);

		String expected = "RelayEntity [id=" + sut.getId() + ", relayname=" + relayname + "]";

		String actual = sut.toString();
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		RelayEntity sut = RelayEntity.newInstance();
		sut.setId("5697d710-8967-4b2d-9ab2-8fc50ddc6138");

		int hashCode = sut.hashCode();

		assertEquals(2031501961, hashCode);

		sut.setId(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		RelayEntity sut = RelayEntity.newInstance();

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithNull() {
		RelayEntity sut = RelayEntity.newInstance();

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		RelayEntity sut = RelayEntity.newInstance();

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_WithDiffrentValues() {
		RelayEntity sut = RelayEntity.newInstance();
		RelayEntity secondSut = RelayEntity.newInstance();

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithSameValues() {
		RelayEntity sut = RelayEntity.newInstance();
		RelayEntity secondSut = RelayEntity.newInstance();
		secondSut.setId(sut.getId());

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		RelayEntity sut = RelayEntity.newInstance();
		sut.setId(null);
		RelayEntity secondSut = RelayEntity.newInstance();

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		RelayEntity sut = RelayEntity.newInstance();
		sut.setId(null);

		RelayEntity secondSut = RelayEntity.newInstance();
		secondSut.setId(null);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testGetParticipantEntityAtPosition_find_pos_one_have_pos_one() {
		RelayEntity sut = RelayEntity.newInstance();
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPosition(Integer.valueOf(1));
		sut.addParticipantEntity(participantEntity);

		Optional<ParticipantEntity> actual = sut.getParticipantEntityAtPosition(Integer.valueOf(1));

		assertTrue("[participantEntity] was not searched correctly!", actual.isPresent());
	}

	@Test
	public void testGetParticipantEntityAtPosition_find_pos_one_have_pos_two() {
		RelayEntity sut = RelayEntity.newInstance();
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPosition(Integer.valueOf(2));
		sut.addParticipantEntity(participantEntity);

		Optional<ParticipantEntity> actual = sut.getParticipantEntityAtPosition(Integer.valueOf(1));

		assertFalse("[participantEntity] was not searched correctly!", actual.isPresent());
	}

	@Test
	public void testGetParticipantEntityAtPosition_find_pos_one_have_none() {
		RelayEntity sut = RelayEntity.newInstance();

		Optional<ParticipantEntity> actual = sut.getParticipantEntityAtPosition(Integer.valueOf(1));

		assertFalse("[participantEntity] was not searched correctly!", actual.isPresent());
	}

	@Test
	public void testPossiblyRemoveParticipantEntity_particpantEntityIsNotPresent() {
		RelayEntity sut = RelayEntityInitializer.newRelayEntityWithOneParticipant();
		Optional<ParticipantEntity> isNotAParticipantEntity = Optional.ofNullable(null);

		sut.possiblyRemoveParticipantEntity(isNotAParticipantEntity);

		int expected = 1;
		int actual = sut.getParticipantEntities().size();
		assertEquals("Possibly removing a [participantEntity] does not work correctly!", expected, actual);
	}

	@Test
	public void testPossiblyRemoveParticipantEntity_particpantEntityIsPresentAndContained() {
		RelayEntity sut = RelayEntityInitializer.newRelayEntityWithOneParticipant();
		ParticipantEntity participantEntity = ParticipantEntity.newInstance(sut.getParticipantEntities().get(0).getId());
		Optional<ParticipantEntity> isAParticipantEntity = Optional.of(participantEntity);

		sut.possiblyRemoveParticipantEntity(isAParticipantEntity);

		int expected = 0;
		int actual = sut.getParticipantEntities().size();
		assertEquals("Possibly removing a [participantEntity] does not work correctly!", expected, actual);
	}

	@Test
	public void testPossiblyRemoveParticipantEntity_particpantEntityIsPresentAndNotContained() {
		RelayEntity sut = RelayEntityInitializer.newRelayEntityWithOneParticipant();
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		Optional<ParticipantEntity> isAParticipantEntity = Optional.of(participantEntity);

		sut.possiblyRemoveParticipantEntity(isAParticipantEntity);

		int expected = 1;
		int actual = sut.getParticipantEntities().size();
		assertEquals("Possibly removing a [participantEntity] does not work correctly!", expected, actual);
	}
}