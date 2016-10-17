package com.relayd.web.converter;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.EventDay;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 22.06.2016
 * status initial
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventDayValueObjectConverterTest {
	private EventDayValueObjectConverter sut = new EventDayValueObjectConverter();

	private static final String EXPECTED_DAY = "2015-12-31";

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, EXPECTED_DAY);

		assertNotNull("Expected valid instance.", result);
		assertEquals(EventDay.class, result.getClass());
		EventDay eventDay = (EventDay) result;
		assertEquals("Attribute not correct.", EXPECTED_DAY, eventDay.toString());
	}

	@Test
	public void testGetAsString() {
		LocalDate localDate = LocalDate.of(2015, Month.DECEMBER, 31);
		EventDay eventDay = EventDay.newInstance(localDate);

		String result = sut.getAsString(null, null, eventDay);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", EXPECTED_DAY, result);
	}
}