package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Surename;

/**
 * What makes a clean test?
 * Three things.
 * Readability, readability, and readability.
 *  - Robert C. Martin
 *
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   26.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SurenameValueObjectConverterTest extends NameValueObjectConverterTest {
	private SurenameValueObjectConverter sut = new SurenameValueObjectConverter();

	private final String name = "Jonas";

	@Override
	NameValueObjectConverter getSut() {
		return sut;
	}

	@Test
	public void testGetName() {
		Surename expected = Surename.newInstance(name);
		Surename actual = sut.getName(name);

		assertEquals("Surename has not been correctly created!", expected, actual);
	}

	@Test
	public void testGetAsObject_ForValue() {
		Object object = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not correct!", Surename.class, object.getClass());
		Surename surename = (Surename) object;
		assertEquals("Attribute not correct!", name, surename.toString());
	}

	@Test
	public void testGetAsString() {
		Surename surename = Surename.newInstance(name);

		String actual = sut.getAsString(null, null, surename);

		assertNotNull("Expected valid instance!", actual);
		assertEquals("Attribute not correct!", name, actual);
	}
}