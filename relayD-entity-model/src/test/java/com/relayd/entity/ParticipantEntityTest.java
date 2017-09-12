package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Böses Gewerbe bringt bösen Lohn.
 *  - Friedrich Schiller
 *
 * @author schmollc (Christian@relayd.de)
 * @since  11.09.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantEntityTest {

	@Test
	public void testConstructor() {
		ParticipantEntity sut = new ParticipantEntity();
		assertNull("[id] not correct!", sut.getId());
		assertNull("[comment] not correct!", sut.getComment());
	}

	@Test
	public void testNewInstance() {
		ParticipantEntity sut = ParticipantEntity.newInstance();

		String result = sut.getId();
		assertNotNull("Instance has not been created correctly!", result);
	}

	@Test
	public void testNewInstance_withUuid() {
		String expected = UUID.randomUUID().toString();
		ParticipantEntity sut = ParticipantEntity.newInstance(expected);

		String actual = sut.getId();
		assertEquals("[id] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forUuidWithNullValue() {
		ParticipantEntity.newInstance(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forIncorrectEmptyUuid() {
		ParticipantEntity.newInstance("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forIncorrectBlankUuid() {
		ParticipantEntity.newInstance("   ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forIncorrectFalseUuid() {
		ParticipantEntity.newInstance("987-345-aw3-123");
	}

	@Test
	public void testSetComment() {
		String expected = "a info";
		ParticipantEntity sut = ParticipantEntity.newInstance();

		sut.setComment(expected);

		String actual = sut.getComment();

		assertEquals("[comment] has not been set correctly!", expected, actual);
	}

	@Test
	public void testEquals_true() {
		String someId = UUID.randomUUID().toString();
		ParticipantEntity bruce = ParticipantEntity.newInstance(someId);
		ParticipantEntity wayne = ParticipantEntity.newInstance(someId);

		assertEquals("Equality has not been tested correctly!", bruce, wayne);
		assertEquals("Equality has not been tested correctly!", bruce, bruce);

		ParticipantEntity sut1 = new ParticipantEntity();
		ParticipantEntity sut2 = new ParticipantEntity();
		assertEquals("Equality has not been tested correctly!", sut1, sut2);
	}

	@Test
	public void testEquals_false() {
		ParticipantEntity bruce = ParticipantEntity.newInstance();
		ParticipantEntity wayne = ParticipantEntity.newInstance();

		assertNotEquals("Equality has not been tested correctly!", bruce, wayne);
		assertNotEquals("Equality has not been tested correctly!", bruce, null);
		assertNotEquals("Equality has not been tested correctly!", bruce, Integer.valueOf(42));

		ParticipantEntity sut = new ParticipantEntity();
		assertNotEquals("Equality has not been tested correctly!", sut, bruce);
	}

	@Test
	public void testToString() {
		String someId = UUID.randomUUID().toString();
		ParticipantEntity sut = ParticipantEntity.newInstance(someId);

		String actual = sut.toString();

		String expected = ParticipantEntity.class.getSimpleName() + " [id=" + someId.toString() + "]";
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testSetPersonEntity() {
		ParticipantEntity sut = ParticipantEntity.newInstance();
		PersonEntity expected = PersonEntity.newInstance(UUID.randomUUID());

		sut.setPersonEntity(expected);

		PersonEntity actual = sut.getPersonEntity();
		assertEquals("[personEntity] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPersonEntity_withNull() {
		ParticipantEntity sut = ParticipantEntity.newInstance();

		sut.setPersonEntity(null);
	}

	@Test
	public void testSetRelayEventEntity() {
		ParticipantEntity sut = ParticipantEntity.newInstance();
		RelayEventEntity expected = RelayEventEntity.newInstance(UUID.randomUUID());

		sut.setRelayEventEntity(expected);

		RelayEventEntity actual = sut.getRelayEventEntity();
		assertEquals("[relayEventEntity] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetRelayEventEntity_withNull() {
		ParticipantEntity sut = ParticipantEntity.newInstance();

		sut.setRelayEventEntity(null);
	}
}