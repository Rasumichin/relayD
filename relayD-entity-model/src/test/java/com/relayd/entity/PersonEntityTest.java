package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since  09.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonEntityTest {

	@Test
	public void testConstructor() {
		PersonEntity sut = new PersonEntity();
		assertNull("[id] not correct!", sut.getId());
		assertNull("[forename] not correct!", sut.getForename());
		assertNull("[surename] not correct!", sut.getSurename());
		assertNull("[emailadress] not correct!", sut.getEmail());
		assertNull("[yearOfBirth] not correct!", sut.getYearOfBirth());
		assertNull("[relayname] not correct!", sut.getRelayname());
		assertNull("[pos] not correct!", sut.getPos());
		assertNull("[shirtsize] not correct!", sut.getShirtsize());
		assertNull("[comment] not correct!", sut.getComment());
	}

	@Test
	public void testNewInstance() {
		PersonEntity sut = PersonEntity.newInstance();
		
		String result = sut.getId();
		assertNotNull("Instance has not been created correctly!", result);
	}

	@Test
	public void testNewInstance_isCreatedWithoutAnyFurtherInformation() {
		PersonEntity sut = PersonEntity.newInstance();
		assertNotNull("Id of PersonEntity must not be 'null' after creation!", sut.getId());
		assertNull("[forename] not correct!", sut.getForename());
		assertNull("[surename] not correct!", sut.getSurename());
		assertNull("[emailadress] not correct!", sut.getEmail());
		assertNull("[yearOfBirth] not correct!", sut.getYearOfBirth());
		assertNull("[relayname] not correct!", sut.getRelayname());
		assertNull("[pos] not correct!", sut.getPos());
		assertNull("[shirtsize] not correct!", sut.getShirtsize());
		assertNull("[comment] not correct!", sut.getComment());
	}

	@Test
	public void testNewInstance_withUuid() {
		UUID expected = UUID.randomUUID();
		PersonEntity sut = PersonEntity.newInstance(expected);
		
		UUID actual = UUID.fromString(sut.getId());
		assertEquals("[id] has not been set correctly!", expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewInstance_forUuidWithNullValue() {
		@SuppressWarnings("unused")
		PersonEntity sut = PersonEntity.newInstance(null);
	}
	
	@Test
	public void testSetForename() {
		String forename = "Justus";
		PersonEntity sut = PersonEntity.newInstance();
		
		sut.setForename(forename);
		assertEquals("[forename] has not been set correctly!", forename, sut.getForename());
	}

	@Test
	public void testSetSurename() {
		String surename = "Jonas";
		PersonEntity sut = PersonEntity.newInstance();
		
		sut.setSurename(surename);
		assertEquals("[surename] has not been set correctly!", surename, sut.getSurename());
	}

	@Test
	public void testSetEmail() {
		String email = "Jonas.Jonas@RockyBeach.com";
		PersonEntity sut = PersonEntity.newInstance();

		sut.setEmail(email);
		assertEquals("[email] has not been set correctly!", email, sut.getEmail());
	}

	@Test
	public void testSetYearOfBirth() {
		Integer yearOfBirth = 1971;
		PersonEntity sut = PersonEntity.newInstance();

		sut.setYearOfBirth(yearOfBirth);
		assertEquals("[yearOfBirth] has not been set correctly!", yearOfBirth, sut.getYearOfBirth());
	}

	@Test
	public void testShirtsize() {
		Integer shirtsize = 1;
		PersonEntity sut = PersonEntity.newInstance();

		sut.setShirtsize(shirtsize);
		assertEquals("[shirtsize] has not been set correctly!", shirtsize, sut.getShirtsize());
	}


	@Test
	public void testSetRelayname() {
		String relayname = "Die 4 ????";
		PersonEntity sut = PersonEntity.newInstance();
		
		sut.setRelayname(relayname);
		assertEquals("[relayname] has not been set correctly!", relayname, sut.getRelayname());
	}

	@Test
	public void testSetPos() {
		Integer position = 1;
		PersonEntity sut = PersonEntity.newInstance();
		
		sut.setPos(position);
		assertEquals("[pos] has not been set correctly!", position, sut.getPos());
	}

	@Test
	public void testSetComment() {
		String comment = "a info";
		PersonEntity sut = PersonEntity.newInstance();
		
		sut.setComment(comment);
		assertEquals("[comment] has not been set correctly!", comment, sut.getComment());
	}

	@Test
	public void testToString() {
		PersonEntity sut = PersonEntity.newInstance();
		String forename = "Kent";
		sut.setForename(forename);
		String surename = "Beck";
		sut.setSurename(surename);

		String expectedResult = "PersonEntity [id=" + sut.getId() + ", surename=" + surename + ", forename=" + forename + "]";
		
		String actualResult = sut.toString();
		assertEquals("String representation is not correct!", expectedResult, actualResult);
	}

	@Test
	public void testEquals_true() {
		UUID someId = UUID.randomUUID();
		PersonEntity christian = PersonEntity.newInstance(someId);
		christian.setForename("Christian");
		
		PersonEntity erik = PersonEntity.newInstance(someId);
		erik.setForename("Erik");
		
		assertEquals("Equality has not been tested correctly!", christian, erik);
	}

	@Test
	public void testEquals_false() {
		PersonEntity christian = PersonEntity.newInstance();
		christian.setForename("Christian");
		
		PersonEntity erik = PersonEntity.newInstance();
		erik.setForename("Erik");
		
		assertNotEquals("Equality has not been tested correctly!", christian, erik);
	}
}
