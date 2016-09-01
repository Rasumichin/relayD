package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.attributes.Forename;

/**
 * @author schmollc (Christian@relayD.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.06.2016
 * status initial
 * 
 */
public class ForenameValueObjectConverterTest {
	private ForenameValueObjectConverter sut = new ForenameValueObjectConverter();

	private final String name = "Justus";

	@Test
	public void testGetAsObject() {
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