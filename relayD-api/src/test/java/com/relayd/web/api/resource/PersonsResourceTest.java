package com.relayd.web.api.resource;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.client.jaxb.PersonsDTO;
import com.relayd.web.api.bridge.PersonDTOBridge;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   06.04.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonsResourceTest {
	private PersonsResource sut = new PersonsResource();

	@Test
	public void testPing() {
		String result = sut.ping();
		assertEquals("a great Ping response from class PersonsResource.", result);
	}
	
	@Test
	public void testGetPersons_not_null() {
		PersonsDTO personDTO = sut.getPersons();
		
		assertNotNull("The 'personsDTO' must not be 'null'!", personDTO);
	}
	
	@Test
	public void testGetPersonDTOBridge() {
		PersonDTOBridge personDTOBridge = sut.getPersonDTOBridge();
		
		assertNotNull("'personDTOBridge' must not be 'null'!", personDTOBridge);
	}
}