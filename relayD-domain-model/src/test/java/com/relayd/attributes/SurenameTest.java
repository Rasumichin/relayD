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
public class SurenameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateValidObject() {
		final String NAME = "McFly";
		Surename surename = Surename.newInstance(NAME);

		assertEquals(NAME, surename.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInvalidObject_Null_WithExpectedAssert() {
		Surename.newInstance(null);
	}

	@Test
	public void testCreateInvalidObject_Null_WithRuleAssert() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[surename] must not be 'null'.");
		Surename.newInstance(null);
	}

	@Test
	public void testGetHashCode() {
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
		Surename secondName = Surename.newInstance("Surename");

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Surename sut = Surename.newInstance("Surename");
		sut.value = null;
		Surename secondName = Surename.newInstance("Surename");
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Surename sut = Surename.newInstance("Surename");
		Surename secondName = Surename.newInstance("NotSurename");

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Surename sut = Surename.newInstance("Surename");
		Surename secondName = Surename.newInstance("Surename");

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

}