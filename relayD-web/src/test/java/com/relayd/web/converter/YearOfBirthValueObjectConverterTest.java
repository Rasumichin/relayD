package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.YearOfBirth;

/**
 * The unit tests are documents.
 * They describe the lowest-level design of the system.
 *  - Robert C. Martin
 *
 * @author schmollc (Christian@relayd.de)
 * @since 22.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class YearOfBirthValueObjectConverterTest {
	private YearOfBirthValueObjectConverter sut = new YearOfBirthValueObjectConverter();

	private static final String EXPECTED_YEAR = "1971";

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

		Object object = sut.getAsObject(null, null, EXPECTED_YEAR);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not correct!", YearOfBirth.class, object.getClass());

		YearOfBirth yearOfBirth = (YearOfBirth) object;
		assertEquals("Attribute not correct!", EXPECTED_YEAR, yearOfBirth.toString());
	}

	@Test
	public void testGetAsString() {
		Integer year = Integer.valueOf(EXPECTED_YEAR);
		YearOfBirth yearOfBirth = YearOfBirth.newInstance(year);

		String object = sut.getAsString(null, null, yearOfBirth);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Attribute not correct!", EXPECTED_YEAR, object);
	}
}