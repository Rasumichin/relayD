package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

/**
 * Jede lange Reise beginnt mit dem ersten Schritt
 *  - Laotse
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   22.03.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SurenameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateInstance() {
		String expected = "Jonas";
		Surename surename = Surename.newInstance(expected);

		String actual = surename.value;
		assertEquals(expected, actual);
	}

	@Test
	public void testCreateInstanceWithIllegalNullValue() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[surename] must not be 'null'.");
		Surename.newInstance(null);
	}

	@Test
	public void testToString() {
		String name = "Jonas";
		Surename surename = Surename.newInstance(name);

		String actual = surename.toString();

		assertEquals("Jonas", actual);
	}

	@Test
	public void testHashCode() {
		Surename sut = Surename.newInstance("Surename");

		int hashCode = sut.hashCode();

		assertEquals(-1551509409, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Surename sut = Surename.newInstance("Surename");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		Surename sut = Surename.newInstance("Surename");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Surename sut = Surename.newInstance("Surename");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Surename sut = Surename.newInstance("Surename");
		sut.value = null;
		Surename secondSut = Surename.newInstance("Surename");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Surename sut = Surename.newInstance("Surename");
		sut.value = null;
		Surename secondSut = Surename.newInstance("Surename");
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Surename sut = Surename.newInstance("Surename");
		Surename secondSut = Surename.newInstance("NotSurename");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Surename sut = Surename.newInstance("Surename");
		Surename secondSut = Surename.newInstance("Surename");

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

}