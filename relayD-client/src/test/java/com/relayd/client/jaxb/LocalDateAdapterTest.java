package com.relayd.client.jaxb;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

/**
 * 
 * @author Rasumichin (Erik@relayd.de)
 * @since  11.06.2017
 *
 */
public class LocalDateAdapterTest {
	private LocalDateAdapter sut = new LocalDateAdapter();

	@Test
	public void testUnmarshal_success() throws Exception {
		LocalDate expected = LocalDate.of(2001, 11, 21);
		String stringToUnmarshal = expected.toString();
		
		LocalDate actual = sut.unmarshal(stringToUnmarshal);
		
		assertEquals("Unmarshalling was not correct!", expected, actual);
	}

	@Test
	public void testUnmarshal_nullValue() throws Exception {
		String stringToUnmarshal = null;
		
		LocalDate result = sut.unmarshal(stringToUnmarshal);
		
		assertNull("Unmarshalling was not correct!", result);
	}

	@Test(expected=Exception.class)
	public void testUnmarshal_failure() throws Exception {
		// Not a valid LocalDate string representation
		String stringToUnmarshal = "99 Feb 30";
		
		sut.unmarshal(stringToUnmarshal);
	}

	@Test
	public void testMarshal_success() throws Exception {
		LocalDate dateToMarshal = LocalDate.of(2001, 11, 21);
		String expected = dateToMarshal.toString();
		
		String actual = sut.marshal(dateToMarshal);
		
		assertEquals("Marshalling was not correct!", expected, actual);
	}

	@Test
	public void testMarshal_nullValue() {
		LocalDate dateToMarshal = null;
		String expected = "";
		
		String actual = sut.marshal(dateToMarshal);
		
		assertEquals("Marshalling was not correct!", expected, actual);
	}
}
