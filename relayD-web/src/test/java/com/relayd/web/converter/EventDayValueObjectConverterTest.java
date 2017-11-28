package com.relayd.web.converter;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.EventDay;

/**
 * Die Güte des Werkes ist nicht abhängig vom Werkzeug, sondern von demjenigen, der das Werkzeug bedient.
 *  - Unbekannt
 *
 * @author schmollc (Christian@relayd.de)
 * @since 22.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventDayValueObjectConverterTest {
	private EventDayValueObjectConverter sut = new EventDayValueObjectConverter();

	private static final String EXPECTED_DAY = "2015-12-31";

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
		Object object = sut.getAsObject(null, null, EXPECTED_DAY);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not correct!", EventDay.class, object.getClass());
		EventDay eventDay = (EventDay) object;
		assertEquals("Attribute not correct!", EXPECTED_DAY, eventDay.toString());
	}

	@Test
	public void testGetAsString() {
		LocalDate localDate = LocalDate.of(2015, Month.DECEMBER, 31);
		EventDay eventDay = EventDay.newInstance(localDate);

		String object = sut.getAsString(null, null, eventDay);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Attribute not correct!", EXPECTED_DAY, object);
	}
}