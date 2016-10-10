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
		String expected = "Die vier ????";
		Relayname sut = Relayname.newInstance(expected);

		String actual = sut.value;
		assertEquals(expected, actual);
	}

	@Test
	public void testCreateInstanceWithIllegalNullValue() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[relayname] must not be 'null'.");
		Relayname.newInstance(null);
	}

	@Test
	public void testIsEmpty_ForFilledName() {
		String relayName = "Die vier ????";
		Relayname sut = Relayname.newInstance(relayName);

		boolean result = sut.isEmpty();

		assertFalse("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForNameFilledWithSpace() {
		String relayName = "  ";
		Relayname sut = Relayname.newInstance(relayName);

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testCompare_ForEqualsNames() {
		Relayname sut = Relayname.newInstance("A");
		Relayname secondSut = Relayname.newInstance("A");

		int result = sut.compareTo(secondSut);

		assertEquals(0, result);
	}

	@Test
	public void testCompare_ForFirstGreaterSecond() {
		Relayname sut = Relayname.newInstance("A");
		Relayname secondSut = Relayname.newInstance("B");

		int result = sut.compareTo(secondSut);

		assertEquals(-1, result);
	}

	@Test
	public void testCompare_ForSecondGreaterFirst() {
		Relayname sut = Relayname.newInstance("B");
		Relayname secondSut = Relayname.newInstance("A");

		int result = sut.compareTo(secondSut);

		assertEquals(1, result);
	}

	@Test
	public void testToString() {
		String relayName = "Staubwolke";
		Relayname sut = Relayname.newInstance(relayName);

		String actual = sut.toString();

		assertEquals("Staubwolke", actual);
	}

	@Test
	public void testHashCode() {
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
		Relayname secondSut = Relayname.newInstance("Relayname");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Relayname sut = Relayname.newInstance("Relayname");
		sut.value = null;
		Relayname secondSut = Relayname.newInstance("Relayname");
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Relayname sut = Relayname.newInstance("Relayname");
		Relayname secondSut = Relayname.newInstance("NotRelayname");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Relayname sut = Relayname.newInstance("Relayname");
		Relayname secondSut = Relayname.newInstance("Relayname");

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}