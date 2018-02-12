package com.relayd.web.api.bridge;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.Person;
import com.relayd.client.jaxb.PersonDTO;
import com.relayd.ejb.PersonGateway;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  28.01.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonDTOBridgeImplTest {
	private PersonGateway personGatewayMock = mock(PersonGateway.class);
	private PersonDTOBridgeImpl sut = PersonDTOBridgeImpl.newInstance(personGatewayMock);

	@Test
	public void testNewInstance() {
		assertNotNull("Intance creation is not correct!", sut);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewInstance_with_null() {
		PersonDTOBridgeImpl.newInstance(null);
	}
	
	@Test
	public void testAll() {
		List<Person> persons = new ArrayList<>();
		persons.add(Person.newInstance());
		persons.add(Person.newInstance());
		when(personGatewayMock.getAll()).thenReturn(persons);
		int expected = 2;
		
		List<PersonDTO> result = sut.all();
		
		int actual = result.size();
		assertEquals("Result of fetching all 'persons' is not correct!", expected, actual);
	}
	
	@Test
	public void testGetPersonGateway() {
		PersonGateway result = sut.getPersonGateway();
		
		assertNotNull("'personGateway' must not be 'null'!", result);
	}
}
