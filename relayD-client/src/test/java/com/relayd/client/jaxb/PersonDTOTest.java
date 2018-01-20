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
	public void testNew() {
		assertNotNull("Instance creation was not correct!", sut);
	}
	
	@Test
	public void testGetId() {
		String id = sut.getId();
		
		assertNull("Initial value of 'id' is not correct!", id);
	}

	@Test
	public void testGetForename() {
		String forename = sut.getForename();
		
		assertNull("Initial value of 'forename' is not correct!", forename);
	}

	@Test
	public void testGetSurename() {
		String surename = sut.getSurename();
		
		assertNull("Initial value of 'surename' is not correct!", surename);
	}

	@Test
	public void testGetYearOfBirth() {
		Integer yearOfBirth = sut.getYearOfBirth();
		
		assertNull("Initial value of 'yearOfBirth' is not correct!", yearOfBirth);
	}

	@Test
	public void testGetShirtSize() {
		String shirtSize = sut.getShirtSize();
		
		assertNull("Initial value of 'shirtSize' is not correct!", shirtSize);
	}

	@Test
	public void testGetEmail() {
		String eMail = sut.getEmail();
		
		assertNull("Initial value of 'eMail' is not correct!", eMail);
	}

	@Test
	public void testGetComment() {
		String comment = sut.getComment();
		
		assertNull("Initial value of 'comment' is not correct!", comment);
	}
}
