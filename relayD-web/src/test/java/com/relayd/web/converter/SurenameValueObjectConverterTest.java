package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.attributes.*;

/**
 * @author  schmollc (Christian@relayD.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   26.06.2016
 * status   initial
 * 
 */
// TODO (Erik, 2016-09-01): Contains tons of duplicate code, refer to ForenameValueObjectConverterTest.
//      Discuss with CS how to avoid this, maybe an abstract test class?
public class SurenameValueObjectConverterTest {
	private SurenameValueObjectConverter sut = new SurenameValueObjectConverter();

	private final String name = "Jonas";

	@Test
	public void testGetName() {
		Surename expected = Surename.newInstance(name);
		Surename result = sut.getName(name);
		
		assertEquals("Surename has not been correctly created.", expected, result);
	}

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Surename.class, result.getClass());
		Surename surename = (Surename) result;
		assertEquals("Attribute not correct.", name, surename.toString());
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
		Surename surename = Surename.newInstance(name);

		String result = sut.getAsString(null, null, surename);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", name, result);
	}
}