package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.attributes.Relayname;

public class RelaynameValueObjectConverterTest {
	private RelaynameValueObjectConverter sut = new RelaynameValueObjectConverter();

	private final String name = "Staubwolke";

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Relayname.class, result.getClass());
		Relayname relayname = (Relayname) result;
		assertEquals("Attribute not correct.", name, relayname.toString());
	}

	@Test
	public void testGetAsString() {
		Relayname relayname = Relayname.newInstance(name);

		String result = sut.getAsString(null, null, relayname);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", name, result);
	}
}