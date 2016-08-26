package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   22.03.2016
 * status   initial
 */
public class ForenameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateInstance() {
		Forename forename = Forename.newInstance("Name");

		assertNotNull(forename);
	}

	@Test
	public void testCreateInstanceWithIllegalNullValue() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[forename] must not be 'null'.");

		Forename.newInstance(null);
	}

	@Test
	public void testToString() {
		final String NAME = "Marty";
		Forename forename = Forename.newInstance(NAME);

		String result = forename.toString();

		assertEquals(NAME, result);
	}

	@Test
	public void testGetHashCode() {
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
		Forename secondName = Forename.newInstance("Forename");

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Forename sut = Forename.newInstance("Forename");
		sut.value = null;
		Forename secondName = Forename.newInstance("Forename");
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Forename sut = Forename.newInstance("Forename");
		Forename secondName = Forename.newInstance("NotForename");

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Forename sut = Forename.newInstance("Forename");
		Forename secondName = Forename.newInstance("Forename");

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}
}