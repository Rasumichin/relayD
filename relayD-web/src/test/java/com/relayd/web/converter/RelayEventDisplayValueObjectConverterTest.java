package com.relayd.web.converter;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.web.pagebean.RelayEventDisplay;

/**
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventDisplayValueObjectConverterTest {
	private RelayEventDisplayValueObjectConverter sut = new RelayEventDisplayValueObjectConverter();

	@Test
	public void testGetAsObject_ForNullValue() {
		String nullValue = null;
		Object actual = sut.getAsObject(null, null, nullValue);

		assertNull("Expected valid instance.", actual);
	}

	@Test
	public void testGetAsObject_ForEmptyValue() {
		String emptyValue = "";
		Object actual = sut.getAsObject(null, null, emptyValue);

		assertNull("Expected valid instance.", actual);
	}

	@Test
	public void testGetAsObject_ForValidValue() {
		String validString = new String("2697d710-8967-4b2d-9ab2-8fc50ddc6138");
		Object actual = sut.getAsObject(null, null, validString);

		assertNotNull("Expected valid instance.", actual);
		assertEquals(RelayEventDisplay.class, actual.getClass());
		RelayEventDisplay relayEventDisplay = (RelayEventDisplay) actual;
		assertEquals("Attribute not correct.", validString, relayEventDisplay.toString());
	}

	@Test
	public void testGetAsString() {
		String expected = new String("2697d710-8967-4b2d-9ab2-8fc50ddc6138");
		RelayEventDisplay email = RelayEventDisplay.newInstance(UUID.fromString(expected), "Test Event");

		String actual = sut.getAsString(null, null, email);

		assertNotNull("Expected valid instance.", actual);
		assertEquals("Attribute not correct.", expected, actual);
	}
}