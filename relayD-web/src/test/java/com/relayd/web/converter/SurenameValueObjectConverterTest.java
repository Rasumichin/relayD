package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Surename;

/**
 * @author  schmollc (Christian@relayD.de)
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
		Surename result = sut.getName(name);

		assertEquals("Surename has not been correctly created.", expected, result);
	}

	@Test
	public void testGetAsObject_ForValue() {
		Object result = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Surename.class, result.getClass());
		Surename surename = (Surename) result;
		assertEquals("Attribute not correct.", name, surename.toString());
	}

	@Test
	public void testGetAsString() {
		Surename surename = Surename.newInstance(name);

		String result = sut.getAsString(null, null, surename);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", name, result);
	}
}