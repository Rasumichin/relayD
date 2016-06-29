package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.attributes.Shirtsize;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   29.06.2016
 * status   initial
 */
public class ShirtsizeValueObjectConverterTest {
	private ShirtsizeValueObjectConverter sut = new ShirtsizeValueObjectConverter();

	private final String size = "DamenS";

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, size);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Shirtsize.class, result.getClass());
		Shirtsize shirtsize = (Shirtsize) result;
		assertEquals("Attribute not correct.", size, shirtsize.toString());
	}

	@Test
	public void testGetAsString() {
		Shirtsize shirtsize = Shirtsize.Unknown;

		String result = sut.getAsString(null, null, shirtsize);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", size, result);
	}
}