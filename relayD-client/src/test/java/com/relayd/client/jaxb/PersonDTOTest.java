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
}
