package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Eventname;

/**
 * Discipline is the best tool.
 * - Anonymous
 *
 * @author schmollc (Christian@relayd.de)
 * @since 22.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventNameValueObjectConverterTest {
	private EventNameValueObjectConverter sut = new EventNameValueObjectConverter();

	private final String name = "Düsseldorf Marathon";

	@Test
	public void testGetAsObject_ForEmptyValue() {
		String emptyValue = "";
		Object object = sut.getAsObject(null, null, emptyValue);

		assertNull("Expected valid instance.", object);
	}

	@Test
	public void testGetAsObject_ForValue() {
		Object object = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance.", object);
		assertEquals("Class not correct!", Eventname.class, object.getClass());
		Eventname eventName = (Eventname) object;
		assertEquals("Attribute not correct.", name, eventName.toString());
	}

	@Test
	public void testGetAsString() {
		Eventname eventName = Eventname.newInstance(name);

		String object = sut.getAsString(null, null, eventName);

		assertNotNull("Expected valid instance.", object);
		assertEquals("Attribute not correct.", name, object);
	}
}