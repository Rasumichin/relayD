package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.attributes.Relayname;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 * status initial
 */
public class RelaynameValueObjectConverterTest {
	private RelaynameValueObjectConverter sut = new RelaynameValueObjectConverter();

	private final String name = "Staubwolke";

	@Test
	public void testGetAsObject_ForNullValue() {
		String nullValue = null;
		Object result = sut.getAsObject(null, null, nullValue);

		assertNull("Expected valid instance.", result);
	}

	@Test
	public void testGetAsObject_ForEmptyValue() {
		String emptyValue = "";
		Object result = sut.getAsObject(null, null, emptyValue);

		assertNull("Expected valid instance.", result);
	}

	@Test
	public void testGetAsObject_ForValue() {
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