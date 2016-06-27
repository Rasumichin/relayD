package com.relayd.web.converter;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.relayd.attributes.Birthday;

public class BirthdayValueObjectConverterTest {
	private BirthdayValueObjectConverter sut = new BirthdayValueObjectConverter();

	// TODO -ALL- Problem with PrimeFaces. Calendar set format in GUI is dd.mm.yy but toString on DomainObject is dd-mm-yyyy
	private static final String EXPECTED_DAY = "31-12-2015";
	private String day = "31.12.15";

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, day);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Birthday.class, result.getClass());
		Birthday birthday = (Birthday) result;
		assertEquals("Attribute not correct.", EXPECTED_DAY, birthday.toString());
	}

	@Test
	public void testGetAsString() {
		LocalDate localDate = LocalDate.of(2015, Month.DECEMBER, 31);
		Birthday birthday = Birthday.newInstance(localDate);

		String result = sut.getAsString(null, null, birthday);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", EXPECTED_DAY, result);
	}
}