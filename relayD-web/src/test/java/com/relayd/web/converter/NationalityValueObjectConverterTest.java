package com.relayd.web.converter;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author  schmollc (Christian@relayD.de)
 * @since   28.06.2016
 * status   initial
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NationalityValueObjectConverterTest {

	private NationalityValueObjectConverter sut = new NationalityValueObjectConverter();

	private final String name = "de_DE";

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Locale.class, result.getClass());
		Locale locale = (Locale) result;
		assertEquals("Attribute not correct.", name, locale.toString());
	}

	@Test
	public void testGetAsString() {
		Locale locale = new Locale(name);

		String result = sut.getAsString(null, null, locale);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", name.toUpperCase(), result.toUpperCase());
	}
}