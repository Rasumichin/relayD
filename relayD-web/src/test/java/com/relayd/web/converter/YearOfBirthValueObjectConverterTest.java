package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.YearOfBirth;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 22.09.2016
 * status initial
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class YearOfBirthValueObjectConverterTest {
	private YearOfBirthValueObjectConverter sut = new YearOfBirthValueObjectConverter();

	private static final String EXPECTED_YEAR = "1971";

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

		Object result = sut.getAsObject(null, null, EXPECTED_YEAR);

		assertNotNull("Expected valid instance.", result);
		assertEquals(YearOfBirth.class, result.getClass());
		YearOfBirth yearOfBirth = (YearOfBirth) result;
		assertEquals("Attribute not correct.", EXPECTED_YEAR, yearOfBirth.toString());
	}

	@Test
	public void testGetAsString() {
		Integer year = Integer.valueOf(EXPECTED_YEAR);
		YearOfBirth yearOfBirth = YearOfBirth.newInstance(year);

		String result = sut.getAsString(null, null, yearOfBirth);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", EXPECTED_YEAR, result);
	}
}