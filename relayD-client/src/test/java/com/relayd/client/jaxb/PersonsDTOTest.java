package com.relayd.client.jaxb;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.01.2018
 *
 */
public class PersonsDTOTest {
	PersonsDTO sut = new PersonsDTO();
	
	@Test
	public void testToString() {
		String expected = "PersonsDTO []";
		
		String actual = sut.toString();
		
		assertEquals("String representation is not correct!", expected, actual);
	}
}
