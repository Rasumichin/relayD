package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
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
public class ForenameValueObjectConverterTest extends NameValueObjectConverterTest {
	private ForenameValueObjectConverter sut = new ForenameValueObjectConverter();

	private final String name = "Justus";

	@Override
	NameValueObjectConverter getSut() {
		return sut;
	}

	@Test
	public void testGetName() {
		Forename expected = Forename.newInstance(name);
		Forename result = sut.getName(name);

		assertEquals("Forename has not been correctly created.", expected, result);
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
	public void testGetAsString() {
		Forename forename = Forename.newInstance(name);

		String result = sut.getAsString(null, null, forename);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute is not correct.", name, result);
	}
}