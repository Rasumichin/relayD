package com.relayd.entity;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 09.09.2016
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
		assertNull("[birthyear] not correct!", sut.getBirthyear());
		assertNull("[nationality] not correct!", sut.getNationality());
		assertNull("[relayname] not correct!", sut.getRelayname());
		assertNull("[pos] not correct!", sut.getPos());
		assertNull("[shirtsize] not correct!", sut.getShirtsize());
		assertNull("[nationality] not correct!", sut.getNationality());
		assertNull("[info] not correct!", sut.getInfo());
	}

	@Test
	public void testInstanceIsCreatedWithoutAnyFurtherInformation() {
		PersonEntity sut = new PersonEntity.Builder().build();
		assertNotNull("Id of PersonEntity must not be 'null' after creation.", sut.getId());
		assertNull("[forename] not correct!", sut.getForename());
		assertNull("[surename] not correct!", sut.getSurename());
		assertNull("[emailadress] not correct!", sut.getEmail());
		assertNull("[birthyear] not correct!", sut.getBirthyear());
		assertNull("[nationality] not correct!", sut.getNationality());
		assertNull("[relayname] not correct!", sut.getRelayname());
		assertNull("[pos] not correct!", sut.getPos());
		assertNull("[shirtsize] not correct!", sut.getShirtsize());
		assertNull("[nationality] not correct!", sut.getNationality());
		assertNull("[info] not correct!", sut.getInfo());
	}

	@Test
	public void testInstanceCreated_ForForename() {
		String forename = "Justus";
		PersonEntity sut = new PersonEntity.Builder().withForename(forename).build();
		assertEquals("[forename] has not been set correctly.", forename, sut.getForename());
	}

	@Test
	public void testInstanceCreated_ForSurename() {
		String surename = "Jonas";
		PersonEntity sut = new PersonEntity.Builder().withSurename(surename).build();
		assertEquals("[surename] has not been set correctly.", surename, sut.getSurename());
	}

	@Test
	public void testInstanceCreated_ForEmail() {
		String email = "Jonas.Jonas@RockyBeach.com";
		PersonEntity sut = new PersonEntity.Builder().withEmail(email).build();
		assertEquals("[email] has not been set correctly.", email, sut.getEmail());
	}

	@Test
	public void testInstanceCreated_ForBirthyear() {
		Integer year = 1971;
		PersonEntity sut = new PersonEntity.Builder().withBirthyear(year).build();
		assertEquals("[birthyear] has not been set correctly.", year, sut.getBirthyear());
	}

	@Test
	public void testInstanceCreated_ForShirtsize() {
		Integer shirtsize = 1;
		PersonEntity sut = new PersonEntity.Builder().withShirtsize(shirtsize).build();
		assertEquals("[shirtsize] has not been set correctly.", shirtsize, sut.getShirtsize());
	}

	@Test
	public void testInstanceCreated_ForNationality() {
		String nationality = "DE";
		PersonEntity sut = new PersonEntity.Builder().withNationality(nationality).build();
		assertEquals("[nationality] has not been set correctly.", nationality, sut.getNationality());
	}

	@Test
	public void testInstanceCreated_ForRelayname() {
		String relayname = "Die 4 ????";
		PersonEntity sut = new PersonEntity.Builder().withRelayname(relayname).build();
		assertEquals("[relayname] has not been set correctly.", relayname, sut.getRelayname());
	}

	@Test
	public void testInstanceCreated_ForPos() {
		Integer position = 1;
		PersonEntity sut = new PersonEntity.Builder().withPos(position).build();
		assertEquals("[pos] has not been set correctly.", position, sut.getPos());
	}

	@Test
	public void testInstanceCreated_ForInfo() {
		String comment = "a info";
		PersonEntity sut = new PersonEntity.Builder().withInfo(comment).build();
		assertEquals("[info] has not been set correctly.", comment, sut.getInfo());
	}

}