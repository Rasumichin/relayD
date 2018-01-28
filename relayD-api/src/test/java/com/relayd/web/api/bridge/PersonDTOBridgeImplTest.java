package com.relayd.web.api.bridge;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  28.01.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonDTOBridgeImplTest {

	@Test
	public void testNewInstance() {
		PersonDTOBridgeImpl sut = PersonDTOBridgeImpl.newInstance();
		
		assertNotNull("Intance creation is not correct!", sut);
	}
}
