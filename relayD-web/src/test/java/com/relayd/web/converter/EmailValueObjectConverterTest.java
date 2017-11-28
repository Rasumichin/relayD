package com.relayd.web.converter;

import static org.junit.Assert.*;

import javax.faces.convert.ConverterException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Email;

/**
 * No matter what the problem is, it's always a people problem.
 *  - Gerald M. Weinberg
 *
 * @author schmollc (Christian@relayd.de)
 * @since 29.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailValueObjectConverterTest {
	private EmailValueObjectConverter sut = new EmailValueObjectConverter();

	private final String VALID_STRING = "Justus.Jonas@rockyBeach.com";
	private final String INVALID_STRING = "Justus.JonasrockyBeach.com";

	@Test
	public void testGetAsObject_ForNullValue() {
		String nullValue = null;
		Object object = sut.getAsObject(null, null, nullValue);

		assertNull("Expected valid instance!", object);
	}

	@Test
	public void testGetAsObject_ForEmptyValue() {
		String emptyValue = "";
		Object object = sut.getAsObject(null, null, emptyValue);

		assertNull("Expected valid instance!", object);
	}

	@Test
	public void testGetAsObject_ForValidValue() {
		Object object = sut.getAsObject(null, null, VALID_STRING);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not correct!", Email.class, object.getClass());
		Email email = (Email) object;
		assertEquals("Attribute not correct!", VALID_STRING, email.toString());
	}

	@Test(expected = ConverterException.class)
	public void testGetAsObject_ForInvalidValue() {
		sut.getAsObject(null, null, INVALID_STRING);
	}

	@Test
	public void testGetAsString() {
		Email email = Email.newInstance(VALID_STRING);

		String object = sut.getAsString(null, null, email);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Attribute not correct!", VALID_STRING, object);
	}
}