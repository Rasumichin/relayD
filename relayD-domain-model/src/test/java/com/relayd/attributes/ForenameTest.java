package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Wissen und nichts tun ist wie nicht wissen.
 *  - Dalai Lama
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   22.03.2016
 *
 */
public class ForenameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Forename sut = Forename.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance() {
		String expected = "Justus";

		Forename sut = Forename.newInstance(expected);

		String actual = sut.value;
		assertEquals(expected, actual);

	}

	@Test
	public void testCreateInstanceWithIllegalNullValue() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[forename] must not be 'null'.");
		Forename.newInstance(null);
	}

	@Test
	public void testToString() {
		String forename = "Marty";
		Forename sut = Forename.newInstance(forename);

		String result = sut.toString();

		assertEquals("Marty", result);
	}

	@Test
	public void testHashCode() {
		Forename sut = Forename.newInstance("Forename");

		int hashCode = sut.hashCode();

		assertEquals(531705222, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Forename sut = Forename.newInstance("Forename");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		Forename sut = Forename.newInstance("Forename");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Forename sut = Forename.newInstance("Forename");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Forename sut = Forename.newInstance("Forename");
		sut.value = null;
		Forename secondSut = Forename.newInstance("Forename");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Forename sut = Forename.newInstance("Forename");
		sut.value = null;
		Forename secondSut = Forename.newInstance("Forename");
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Forename sut = Forename.newInstance("Forename");
		Forename secondSut = Forename.newInstance("NotForename");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Forename sut = Forename.newInstance("Forename");
		Forename secondSut = Forename.newInstance("Forename");

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}