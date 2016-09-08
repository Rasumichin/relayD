package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Forename;

/**
 * @author schmollc (Christian@relayD.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.06.2016
 * status initial
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ForenameValueObjectConverterTest {
	private ForenameValueObjectConverter sut = new ForenameValueObjectConverter();

	private final String name = "Justus";

	@Test
	public void testGetName() {
		Forename expected = Forename.newInstance(name);
		Forename result = sut.getName(name);

		assertEquals("Forename has not been correctly created.", expected, result);
	}

	@Test
	@Ignore
	// TODO (Erik, 2016-09-08): Check whether we should test this case with regardo to JSF spec.
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
		Object result = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Forename.class, result.getClass());
		Forename forename = (Forename) result;
		assertEquals("Attribute is not correct.", name, forename.toString());
	}

	@Test
	public void testGetAsObjectWithEmptyName() {
		String emptyName = "";
		Object result = sut.getAsObject(null, null, emptyName);

		assertNull("Attribute is not correct.", result);
	}

	@Test
	public void testGetAsObjectWithNameContainingOnlySpaces() {
		String nameWithSpaces = "  ";
		Object result = sut.getAsObject(null, null, nameWithSpaces);

		assertNull("Attribute is not correct.", result);
	}

	@Test
	public void testGetAsString() {
		Forename forename = Forename.newInstance(name);

		String result = sut.getAsString(null, null, forename);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute is not correct.", name, result);
	}
}