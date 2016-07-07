package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.attributes.Surename;

/**
 * @author  schmollc (Christian@relayD.de)
 * @since   26.06.2016
 * status   initial
 */
public class SurenameValueObjectConverterTest {
	private SurenameValueObjectConverter sut = new SurenameValueObjectConverter();

	private final String name = "Jonas";

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Surename.class, result.getClass());
		Surename surename = (Surename) result;
		assertEquals("Attribute not correct.", name, surename.toString());
	}

	@Test
	public void testGetAsString() {
		Surename surename = Surename.newInstance(name);

		String result = sut.getAsString(null, null, surename);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", name, result);
	}
}