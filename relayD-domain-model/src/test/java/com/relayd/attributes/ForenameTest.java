package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * Wissen und nichts tun ist wie nicht wissen.
 *  - Dalai Lama
 *
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   22.03.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ForenameTest {

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Forename sut = Forename.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class is not Serializable!", result);
	}

	@Test
	public void testToString_usualValue() {
		String expectedResult = "Marty";
		Forename sut = Forename.newInstance(expectedResult);

		String actualResult = sut.toString();

		assertEquals("String representation is not correct!", expectedResult, actualResult);
	}

	@Test
	public void testToString_emptyValue() {
		String expectedResult = "";
		Forename sut = Forename.newInstance(expectedResult);

		String actualResult = sut.toString();

		assertEquals("String representation is not correct!", expectedResult, actualResult);
	}

	@Test
	public void testToString_blankValue() {
		String blankForename = "   ";
		Forename sut = Forename.newInstance(blankForename);

		String expectedResult = "";
		String actualResult = sut.toString();

		assertEquals("String representation is not correct!", expectedResult, actualResult);
	}

	@Test
	public void testToString_nullValue() {
		Forename sut = Forename.newInstance(null);

		String expectedResult = "";
		String actualResult = sut.toString();

		assertEquals("String representation is not correct!", expectedResult, actualResult);
	}

	@Test
	public void testEquals_withMyself() {
		Forename sut = Forename.newInstance("Forename");

		boolean result = sut.equals(sut);

		assertTrue("Comparing equality is not correct!", result);
	}

	@Test
	public void testEquals_withNull() {
		Forename sut = Forename.newInstance("Forename");

		boolean result = sut.equals(null);

		assertFalse("Comparing equality is not correct!", result);
	}

	@Test
	public void testEquals_withIncompatibleClass() {
		Forename sut = Forename.newInstance("Forename");

		boolean result = sut.equals(new String());

		assertFalse("Comparing equality is not correct!", result);
	}

	@Test
	public void testEquals_withNullForename() {
		Forename sut = Forename.newInstance("Forename");
		Forename nullForename = Forename.newInstance(null);

		boolean result = sut.equals(nullForename);
		
		assertFalse("Comparing equality is not correct!", result);
	}

	@Test
	public void testEquals_withTwoNullForenames() {
		Forename sut = Forename.newInstance(null);
		Forename otherNullForename = Forename.newInstance(null);

		boolean result = sut.equals(otherNullForename);
		
		assertTrue("Comparing equality is not correct!", result);
	}

	@Test
	public void testEquals_withTwoDifferentInstances() {
		Forename sut = Forename.newInstance("Forename");
		Forename otherForename = Forename.newInstance("NotForename");

		boolean result = sut.equals(otherForename);

		assertFalse("Comparing equality is not correct!", result);
	}

	@Test
	public void testEquals_withEqualInstances() {
		Forename sut = Forename.newInstance("Forename");
		Forename otherForename = Forename.newInstance("Forename");

		boolean result = sut.equals(otherForename);

		assertTrue("Comparing equality is not correct!", result);
	}

	@Test
	public void testSamenessOfTwoNullForenames() {
		Forename sut = Forename.newInstance(null);
		Forename otherNullForename = Forename.newInstance(null);

		boolean result = (sut == otherNullForename);
		
		assertTrue("Two NullObjectForenames are not the same!", result);
	}
	
	@Test
	public void testIsEmpty_usualValue() {
		Forename sut = Forename.newInstance("Forename");
		
		boolean result = sut.isEmpty();
		
		assertFalse("'isEmpty' check is not correct!", result);
	}

	@Test
	public void testIsEmpty_nullValue() {
		Forename sut = Forename.newInstance(null);
		
		boolean result = sut.isEmpty();
		
		assertTrue("'isEmpty' check is not correct!", result);
	}
}