package com.relayd.web.converter;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.relayd.attributes.Birthday;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 22.06.2016
 * status initial
 */
public class BirthdayValueObjectConverterTest {
	private BirthdayValueObjectConverter sut = new BirthdayValueObjectConverter();

	private static final String EXPECTED_DAY = "2015-12-31";

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

		Object result = sut.getAsObject(null, null, EXPECTED_DAY);

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