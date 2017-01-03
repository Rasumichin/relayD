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
		UUID uuid = UUID.randomUUID();
		ParticipantEntity sut = ParticipantEntity.newInstance(uuid);
		
		String expected = uuid.toString();
		String actual = sut.getId();
		assertEquals("[id] has not been set correctly!", expected, actual);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNewInstance_forUuidWithNullValue() {
		ParticipantEntity.newInstance(null);
	}

	@Test
	public void testEquals_true() {
		UUID someId = UUID.randomUUID();
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
		UUID someId = UUID.randomUUID();
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

	@Ignore
	@Test
	public void testSetPersonEntity() {
		
	}
	
	@Ignore
	@Test
	public void testSetRelayId() {
		
	}
}
