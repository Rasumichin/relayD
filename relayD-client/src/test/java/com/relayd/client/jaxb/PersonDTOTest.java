package com.relayd.client.jaxb;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.01.2018
 *
 */
public class PersonDTOTest {
	private PersonDTO sut = new PersonDTO();

	@Test
	public void testNewInstance() {
		PersonDTO sut = PersonDTO.newInstance();
		
		assertNotNull("Intance creation was not correct!", sut);
	}
	
	@Test
	public void testNew() {
		assertNotNull("Instance creation was not correct!", sut);
	}
	
	@Test
	public void testGetId() {
		String id = sut.getId();
		
		assertNull("Initial value of 'id' is not correct!", id);
	}

	@Test
	public void testSetId() {
		String expected = "4711";
		
		sut.setId(expected);
		
		String actual = sut.getId();
		assertEquals("Setting of 'id' was not correct!", expected, actual);
	}

	@Test
	public void testGetForename() {
		String forename = sut.getForename();
		
		assertNull("Initial value of 'forename' is not correct!", forename);
	}

	@Test
	public void testSetForename() {
		String expected = "Martin";
		
		sut.setForename(expected);
		
		String actual = sut.getForename();
		assertEquals("Setting of 'forename' was not correct!", expected, actual);
	}

	@Test
	public void testGetSurename() {
		String surename = sut.getSurename();
		
		assertNull("Initial value of 'surename' is not correct!", surename);
	}

	@Test
	public void testSetSurename() {
		String expected = "Fowler";
		
		sut.setSurename(expected);
		
		String actual = sut.getSurename();
		assertEquals("Setting of 'surename' was not correct!", expected, actual);
	}

	@Test
	public void testGetYearOfBirth() {
		Integer yearOfBirth = sut.getYearOfBirth();
		
		assertNull("Initial value of 'yearOfBirth' is not correct!", yearOfBirth);
	}

	@Test
	public void testSetYearOfBirth() {
		Integer expected = 1963;
		
		sut.setYearOfBirth(expected);
		
		Integer actual = sut.getYearOfBirth();
		assertEquals("Setting of 'yearOfBirth' was not correct!", expected, actual);
	}

	@Test
	public void testGetShirtSize() {
		String shirtSize = sut.getShirtSize();
		
		assertNull("Initial value of 'shirtSize' is not correct!", shirtSize);
	}

	@Test
	public void testSetShirtSize() {
		String expected = "Herren XL";
		
		sut.setShirtSize(expected);
		
		String actual = sut.getShirtSize();
		assertEquals("Setting of 'shirtSize' was not correct!", expected, actual);
	}

	@Test
	public void testGetEmail() {
		String eMail = sut.getEmail();
		
		assertNull("Initial value of 'eMail' is not correct!", eMail);
	}

	@Test
	public void testSetEmail() {
		String expected = "martin.fowler@mail.com";
		
		sut.setEmail(expected);
		
		String actual = sut.getEmail();
		assertEquals("Setting of 'email' was not correct!", expected, actual);
	}

	@Test
	public void testGetComment() {
		String comment = sut.getComment();
		
		assertNull("Initial value of 'comment' is not correct!", comment);
	}
	
	@Test
	public void testSetComment() {
		String expected = "none";
		
		sut.setComment(expected);
		
		String actual = sut.getComment();
		assertEquals("Setting of 'comment' was not correct!", expected, actual);
	}

	@Test
	public void testToString() {
		sut.setId("4711");
		sut.setForename("Martin");
		sut.setSurename("Fowler");
		String expected = "PersonDTO [id=4711, forename=Martin, surename=Fowler]";
		
		String actual = sut.toString();
		
		assertEquals("String representation is not correct!", expected, actual);
	}
}
