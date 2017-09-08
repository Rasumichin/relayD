package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Tests help us to shape our design to actual use.
 * 	  - Lasse Koskela (Effective Unit Testing)
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  14.12.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberEntityTest {

	@Test
	public void testConstructor() {
		MemberEntity sut = new MemberEntity();
		assertNull("[id] not correct!", sut.getId());
	}

	@Test
	public void testNewInstance() {
		MemberEntity sut = MemberEntity.newInstance();

		String result = sut.getId();
		assertNotNull("Instance has not been created correctly!", result);
	}

	@Test
	public void testNewInstance_withUuid() {
		String expected = UUID.randomUUID().toString();
		MemberEntity sut = MemberEntity.newInstance(expected);

		String actual = sut.getId();
		assertEquals("[id] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forUuidWithNullValue() {
		MemberEntity.newInstance(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forIncorrectEmptyUuid() {
		MemberEntity.newInstance("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forIncorrectBlankUuid() {
		MemberEntity.newInstance("   ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forIncorrectFalseUuid() {
		MemberEntity.newInstance("987-345-aw3-123");
	}

	@Test
	public void testEquals_true() {
		String someId = UUID.randomUUID().toString();
		MemberEntity bruce = MemberEntity.newInstance(someId);
		MemberEntity wayne = MemberEntity.newInstance(someId);

		assertEquals("Equality has not been tested correctly!", bruce, wayne);
		assertEquals("Equality has not been tested correctly!", bruce, bruce);

		MemberEntity sut1 = new MemberEntity();
		MemberEntity sut2 = new MemberEntity();
		assertEquals("Equality has not been tested correctly!", sut1, sut2);
	}

	@Test
	public void testEquals_false() {
		MemberEntity bruce = MemberEntity.newInstance();
		MemberEntity wayne = MemberEntity.newInstance();

		assertNotEquals("Equality has not been tested correctly!", bruce, wayne);
		assertNotEquals("Equality has not been tested correctly!", bruce, null);
		assertNotEquals("Equality has not been tested correctly!", bruce, Integer.valueOf(42));

		MemberEntity sut = new MemberEntity();
		assertNotEquals("Equality has not been tested correctly!", sut, bruce);
	}

	@Test
	public void testToString() {
		String someId = UUID.randomUUID().toString();
		MemberEntity sut = MemberEntity.newInstance(someId);

		String actual = sut.toString();

		String expected = MemberEntity.class.getSimpleName() + " [id=" + someId.toString() + "]";
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testSetPosition() {
		MemberEntity sut = MemberEntity.newInstance();
		Integer expected = Integer.valueOf(1);

		sut.setPosition(expected);

		Integer actual = sut.getPosition();
		assertEquals("[position] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPosition_withNull() {
		MemberEntity sut = MemberEntity.newInstance();

		sut.setPosition(null);
	}

	@Test
	public void testDuration() {
		Long expected = 0L;
		MemberEntity sut = MemberEntity.newInstance();

		sut.setDuration(expected);
		Long actual = sut.getDuration();

		assertEquals("[duration] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDuration_withNull() {
		MemberEntity sut = MemberEntity.newInstance();

		sut.setDuration(null);
	}

	@Test
	public void testSetPersonEntity() {
		MemberEntity sut = MemberEntity.newInstance();
		PersonEntity expected = PersonEntity.newInstance(UUID.randomUUID());

		sut.setPersonEntity(expected);

		PersonEntity actual = sut.getPersonEntity();
		assertEquals("[personEntity] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPersonEntity_withNull() {
		MemberEntity sut = MemberEntity.newInstance();

		sut.setPersonEntity(null);
	}

	@Test
	public void testSetRelayEntity() {
		MemberEntity sut = MemberEntity.newInstance();
		RelayEntity relayEntity = RelayEntity.newInstance();
		String expected = relayEntity.getId();

		sut.setRelayEntity(relayEntity);

		String actual = sut.getRelayEntity().getId();
		assertEquals("[relayId] has not been set correctly!", expected, actual);
	}

	@Test
	public void testSetRelayEntity_addMemberEntity() {
		MemberEntity sut = MemberEntity.newInstance();
		RelayEntity relayEntity = RelayEntity.newInstance();
		String expected = relayEntity.getId();

		relayEntity.addMemberEntity(sut);

		String actual = sut.getRelayEntity().getId();
		assertEquals("[relayId] has not been set correctly!", expected, actual);
	}

	@Test
	public void testSetRelayEntity_removeMemberEntity() {
		MemberEntity sut = MemberEntity.newInstance();
		RelayEntity relayEntity = RelayEntity.newInstance();
		relayEntity.addMemberEntity(sut);

		relayEntity.removeMemberEntity(sut);

		RelayEntity actual = sut.getRelayEntity();
		assertNull("[relayId] has not been set correctly!", actual);
	}

	@Test
	public void testGetUuidPerson() {
		MemberEntity sut = MemberEntity.newInstance();
		UUID expected = UUID.randomUUID();
		PersonEntity personEntity = PersonEntity.newInstance(expected);
		sut.setPersonEntity(personEntity);

		UUID actual = sut.getUuidPerson();

		assertEquals("[personUuid] is not correct!", expected, actual);
	}
}
