package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.attributes.Email;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 29.06.2016
 * status initial
 */
public class EmailValueObjectConverterTest {
	private EmailValueObjectConverter sut = new EmailValueObjectConverter();

	private final String name = "Justus.Jonas@rockyBeach.com";

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Email.class, result.getClass());
		Email email = (Email) result;
		assertEquals("Attribute not correct.", name, email.toString());
	}

	@Test
	public void testGetAsString() {
		Email email = Email.newInstance(name);

		String result = sut.getAsString(null, null, email);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", name, result);
	}
}