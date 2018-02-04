package com.relayd.web.api.bridge;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.client.jaxb.PersonDTO;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  28.01.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonDTOBridgeImplTest {
	private PersonDTOBridgeImpl sut = PersonDTOBridgeImpl.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Intance creation is not correct!", sut);
	}
	
	@Test
	public void testAll() {
		List<PersonDTO> actual = sut.all();
		
		assertNull("Assertion not longer valid!", actual);
	}
}
