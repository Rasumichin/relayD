package com.relayd.web.converter;

import static org.junit.Assert.*;

import javax.faces.convert.ConverterException;

import org.junit.Test;

import com.relayd.attributes.Email;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 29.06.2016
 * status initial
 */
public class EmailValueObjectConverterTest {
	private EmailValueObjectConverter sut = new EmailValueObjectConverter();

	private final String VALID_STRING = "Justus.Jonas@rockyBeach.com";
	private final String INVALID_STRING = "Justus.JonasrockyBeach.com";

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
	public void testGetAsObject_ForValidValue() {
		Object result = sut.getAsObject(null, null, VALID_STRING);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Email.class, result.getClass());
		Email email = (Email) result;
		assertEquals("Attribute not correct.", VALID_STRING, email.toString());
	}

	@Test(expected = ConverterException.class)
	public void testGetAsObject_ForInvalidValue() {
		sut.getAsObject(null, null, INVALID_STRING);
	}

	@Test
	public void testGetAsString() {
		Email email = Email.newInstance(VALID_STRING);

		String result = sut.getAsString(null, null, email);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", VALID_STRING, result);
	}
}