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
	public void testGetAsObject_ForValue() {
		Object object = sut.getAsObject(null, null, value);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not correct!", RelayCount.class, object.getClass());
		RelayCount relayCount = (RelayCount) object;
		assertEquals("Attribute not correct!", value, relayCount.toString());
	}

	@Test
	public void testGetAsString() {
		RelayCount relayCount = RelayCount.newInstance(Integer.valueOf(value));

		String object = sut.getAsString(null, null, relayCount);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Attribute not correct!", value, object);
	}
}