package com.relayd.web.api.bridge;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.*;
import org.junit.runners.MethodSorters;

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
		List<PersonDTO> actual = sut.all();
		
		assertNull("Assertion not longer valid!", actual);
	}
	
	@Test
	public void testGetPersonGateway() {
		PersonGateway result = sut.getPersonGateway();
		
		assertNotNull("'personGateway' must not be 'null'!", result);
	}
}
