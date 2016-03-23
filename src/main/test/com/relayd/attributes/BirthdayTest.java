package com.relayd.attributes;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   22.03.2016
 * status   initial
 */
public class BirthdayTest {

	@Test
	public void testCreateValidObject() {
		final Date date = new GregorianCalendar(1978, Calendar.OCTOBER, 21).getTime();

		Birthday birthday = Birthday.newInstance(date);

		assertEquals("Geboren am: 21.10.1978", birthday.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInvalidObject_Null() {
		Birthday.newInstance(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInvalidObject_DateInFuture() {
		Date futureDate = new GregorianCalendar(9015, Calendar.OCTOBER, 21).getTime();

		Birthday.newInstance(futureDate);
	}
}