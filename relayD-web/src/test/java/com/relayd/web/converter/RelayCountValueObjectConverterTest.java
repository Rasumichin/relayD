package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.RelayCount;

/**
 * Wischen Sie Staub, bevor Sie Staub sehen.
 * Denken Sie nicht "Es ist ja schon sauber", sondern vielmehr: "Halte es sauber"
 *  - Philip Toshio Sudo
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   10.11.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayCountValueObjectConverterTest {
	private RelayCountValueObjectConverter sut = new RelayCountValueObjectConverter();

	private final String value = "10";

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
		Object result = sut.getAsObject(null, null, value);

		assertNotNull("Expected valid instance.", result);
		assertEquals(RelayCount.class, result.getClass());
		RelayCount relayCount = (RelayCount) result;
		assertEquals("Attribute not correct.", value, relayCount.toString());
	}

	@Test
	public void testGetAsString() {
		RelayCount relayCount = RelayCount.newInstance(Integer.valueOf(value));

		String result = sut.getAsString(null, null, relayCount);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", value, result);
	}
}