package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Relayname;

/**
 * Test code is just as important as production code.
 * It is not a second-class citizen.
 * It requires thought, design, and care.
 * It must be kept as clean as production code.
 *  - Robert C. Martin
 *
 * @author schmollc (Christian@relayd.de)
 * @since 20.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelaynameValueObjectConverterTest {
	private RelaynameValueObjectConverter sut = new RelaynameValueObjectConverter();

	private final String name = "Staubwolke";

	@Test
	public void testGetAsObject_ForNullValue() {
		String nullValue = null;
		Object object = sut.getAsObject(null, null, nullValue);

		assertNotNull("Expected valid instance!", object);
	}

	@Test
	public void testGetAsObject_ForEmptyValue() {
		String emptyValue = "";
		Object object = sut.getAsObject(null, null, emptyValue);

		assertNotNull("Expected valid instance!", object);
	}

	@Test
	public void testGetAsObject_ForValue() {
		Object object = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not correct!", Relayname.class, object.getClass());

		Relayname relayname = (Relayname) object;

		assertEquals("Attribute not correct!", name, relayname.toString());
	}

	@Test
	public void testGetAsString() {
		Relayname relayname = Relayname.newInstance(name);

		String object = sut.getAsString(null, null, relayname);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Attribute not correct!", name, object);
	}
}