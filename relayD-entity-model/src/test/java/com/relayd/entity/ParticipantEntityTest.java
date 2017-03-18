package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.*;
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
public class ParticipantEntityTest {

	@Test
	public void testConstructor() {
		ParticipantEntity sut = new ParticipantEntity();
		assertNull("[id] not correct!", sut.getId());
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

	@Test(expected=IllegalArgumentException.class)
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
	public void testSetPosition() {
		ParticipantEntity sut = ParticipantEntity.newInstance();
		Integer expected = Integer.valueOf(1);
		
		sut.setPosition(expected);
		
		Integer actual = sut.getPosition();
		assertEquals("[position] has not been set correctly!", expected, actual);
	}

	@Test
	public void testSetPersonEntity() {
		ParticipantEntity sut = ParticipantEntity.newInstance();
		PersonEntity expected = PersonEntity.newInstance(UUID.randomUUID());
		
		sut.setPersonEntity(expected);
		
		PersonEntity actual = sut.getPersonEntity();
		assertEquals("[personEntity] has not been set correctly!", expected, actual);
	}

	@Test
	public void testSetRelay2Entity() {
		ParticipantEntity sut = ParticipantEntity.newInstance();
		RelayEntity relay2Entity = RelayEntity.newInstance();
		String expected = relay2Entity.getId();
		
		sut.setRelay2Entity(relay2Entity);
		
		String actual = sut.getRelay2Entity().getId();
		assertEquals("[relayId] has not been set correctly!", expected, actual);
	}

	@Test
	public void testSetRelay2Entity_addParticipantEntity() {
		ParticipantEntity sut = ParticipantEntity.newInstance();
		RelayEntity relay2Entity = RelayEntity.newInstance();
		String expected = relay2Entity.getId();
		
		relay2Entity.addParticipantEntity(sut);
		
		String actual = sut.getRelay2Entity().getId();
		assertEquals("[relayId] has not been set correctly!", expected, actual);
	}

	@Test
	public void testSetRelay2Entity_removeParticipantEntity() {
		ParticipantEntity sut = ParticipantEntity.newInstance();
		RelayEntity relay2Entity = RelayEntity.newInstance();
		relay2Entity.addParticipantEntity(sut);
		
		relay2Entity.removeParticipantEntity(sut);
		
		RelayEntity actual = sut.getRelay2Entity();
		assertNull("[relayId] has not been set correctly!", actual);
	}
	
	@Test
	public void testGetUuidPerson() {
		ParticipantEntity sut = ParticipantEntity.newInstance();
		UUID expected = UUID.randomUUID();
		PersonEntity personEntity = PersonEntity.newInstance(expected);
		sut.setPersonEntity(personEntity);
		
		UUID actual = sut.getUuidPerson();

		assertEquals("[personUuid] is not correct!", expected, actual);
	}
}
