package com.relayd.web.converter;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.web.pagebean.RelayEventDisplay;

/**
 * The first rule of functions is that they should be small.
 * The second rule of functions is that they should be smaller than that.
 *  - Robert C. Martin
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   10.11.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventDisplayValueObjectConverterTest {
	private RelayEventDisplayValueObjectConverter sut = new RelayEventDisplayValueObjectConverter();

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
		String validString = new String("2697d710-8967-4b2d-9ab2-8fc50ddc6138");
		Object object = sut.getAsObject(null, null, validString);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not corret!", RelayEventDisplay.class, object.getClass());
		RelayEventDisplay relayEventDisplay = (RelayEventDisplay) object;
		assertEquals("Attribute not correct!", validString, relayEventDisplay.toString());
	}

	@Test
	public void testGetAsString() {
		String expected = new String("2697d710-8967-4b2d-9ab2-8fc50ddc6138");
		RelayEventDisplay email = RelayEventDisplay.newInstance(UUID.fromString(expected), "Test Event");

		String object = sut.getAsString(null, null, email);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Attribute not correct!", expected, object);
	}
}