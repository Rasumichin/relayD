package com.relayd.web.api.resource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.client.jaxb.*;
import com.relayd.web.api.bridge.*;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   06.04.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonsResourceTest {
	private PersonDTOBridge personDTOBridgeMock = mock(PersonDTOBridge.class);
	private PersonsResource sut = PersonsResource.newInstance(personDTOBridgeMock);

	@Test(expected=IllegalArgumentException.class)
	public void testNewInstance_with_null() {
		PersonsResource.newInstance(null);
	}
	
	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}
	
	@Test
	public void testGetPersons_when_result_size_equals_zero() {
		int expected = 0;
		
		PersonsDTO personDTO = sut.getPersons();
		
		int actual = personDTO.getPersons().size();
		assertEquals("Initial size of 'personDTOs' is not correct!", expected, actual);
	}
	
	@Test
	public void testGetPersons_when_result_size_equals_one() {
		List<PersonDTO> personDTOs = new ArrayList<>();
		personDTOs.add(PersonDTO.newInstance());
		when(personDTOBridgeMock.all()).thenReturn(personDTOs);
		int expected = 1;
		
		PersonsDTO personDTO = sut.getPersons();
		
		int actual = personDTO.getPersons().size();
		assertEquals("Result size of 'personDTOs' is not correct!", expected, actual);
	}
	
	@Test
	public void testGetPersonDTOBridge() {
		PersonDTOBridge personDTOBridge = sut.getPersonDTOBridge();
		
		assertNotNull("'personDTOBridge' must not be 'null'!", personDTOBridge);
	}

	@Test
	public void testPing() {
		String result = sut.ping();
		assertEquals("a great Ping response from class PersonsResource.", result);
	}
}