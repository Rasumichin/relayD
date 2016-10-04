package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.attributes.EventName;

/**
 * Discipline is the best tool.
 * - Anonymous
 *
 * @author schmollc (Christian@relayd.de)
 * @since 22.06.2016
 *
 */
public class EventNameValueObjectConverterTest {
	private EventNameValueObjectConverter sut = new EventNameValueObjectConverter();

	private final String name = "Düsseldorf Marathon";

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance.", result);
		assertEquals(EventName.class, result.getClass());
		EventName eventName = (EventName) result;
		assertEquals("Attribute not correct.", name, eventName.toString());
	}

	@Test
	public void testGetAsString() {
		EventName eventName = EventName.newInstance(name);

		String result = sut.getAsString(null, null, eventName);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", name, result);
	}
}