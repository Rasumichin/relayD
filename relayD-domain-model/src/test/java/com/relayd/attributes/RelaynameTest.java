package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

/**
 * Die Samen der Vergangenheit sind die Früchte der Zukunft.
 *  - Siddhartha Gautama
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   23.03.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelaynameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateInstance() {
		final String NAME = "Die vier ????";
		Relayname sut = Relayname.newInstance(NAME);

		assertEquals(NAME, sut.toString());
	}

	@Test
	public void testCreateInstanceWithIllegalNullValue() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[relayname] must not be 'null'.");
		Relayname.newInstance(null);
	}

	@Test
	public void testIsEmpty_WithFilledName() {
		String NAME = "Die vier ????";
		Relayname sut = Relayname.newInstance(NAME);

		boolean result = sut.isEmpty();

		assertFalse("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_WithNameFilledWithSpace() {
		String NAME = "  ";
		Relayname sut = Relayname.newInstance(NAME);

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testCompareToWithEqualsNames() {
		Relayname sut = Relayname.newInstance("A");
		Relayname secondName = Relayname.newInstance("A");

		int result = sut.compareTo(secondName);

		assertEquals(0, result);
	}

	@Test
	public void testCompareToWithFirstGreaterSecond() {
		Relayname sut = Relayname.newInstance("A");
		Relayname secondName = Relayname.newInstance("B");

		int result = sut.compareTo(secondName);

		assertEquals(-1, result);
	}

	@Test
	public void testCompareToWithSecondGreaterFirst() {
		Relayname sut = Relayname.newInstance("B");
		Relayname secondName = Relayname.newInstance("A");

		int result = sut.compareTo(secondName);

		assertEquals(1, result);
	}

	@Test
	public void testGetHashCode() {
		Relayname sut = Relayname.newInstance("Relayname");

		int hashCode = sut.hashCode();

		assertEquals(2001071259, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Relayname sut = Relayname.newInstance("Relayname");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		Relayname sut = Relayname.newInstance("Relayname");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Relayname sut = Relayname.newInstance("Relayname");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Relayname sut = Relayname.newInstance("Relayname");
		sut.value = null;
		Relayname secondName = Relayname.newInstance("Relayname");

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Relayname sut = Relayname.newInstance("Relayname");
		sut.value = null;
		Relayname secondName = Relayname.newInstance("Relayname");
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Relayname sut = Relayname.newInstance("Relayname");
		Relayname secondName = Relayname.newInstance("NotRelayname");

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Relayname sut = Relayname.newInstance("Relayname");
		Relayname secondName = Relayname.newInstance("Relayname");

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}
}