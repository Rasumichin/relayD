package com.relayd.web.converter;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.relayd.attributes.EventDay;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 22.06.2016
 * status initial
 */
public class EventDayValueObjectConverterTest {
	private EventDayValueObjectConverter sut = new EventDayValueObjectConverter();

	// TODO -ALL- Problem with PrimeFaces. Calendar set format in GUI is dd.mm.yy but toString on DomainObject is dd-mm-yyyy
	private static final String EXPECTED_DAY = "31-12-2015";
	private String day = "31.12.15";

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, day);

		assertNotNull("Expected valid instance.", result);
		assertEquals(EventDay.class, result.getClass());
		EventDay eventDay = (EventDay) result;
		assertEquals("Attribute not correct.", EXPECTED_DAY, eventDay.toString());
	}

	@Test
	public void testGetAsString() {
		LocalDate localDate = LocalDate.of(2015, Month.DECEMBER, 31);
		EventDay eventDay = new EventDay(localDate);

		String result = sut.getAsString(null, null, eventDay);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", EXPECTED_DAY, result);
	}
}