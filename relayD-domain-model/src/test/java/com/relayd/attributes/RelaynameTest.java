package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Relayname.RelaynameNullObject;

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

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Relayname sut = Relayname.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class is not Serializable!", result);
	}

	@Test
	public void testCreateInstance() {
		Relayname sut = Relayname.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == RelaynameNullObject.class;

		assertTrue("Instance is not correct!", result);
	}

	@Test
	public void testCreateInstance_ForNullValue() {
		Relayname sut = Relayname.newInstance(null);

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == RelaynameNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForEmptyValue() {
		Relayname sut = Relayname.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == RelaynameNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForBlankValue() {
		Relayname sut = Relayname.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == RelaynameNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForParameter() {
		String expected = "Die vier ????";
		Relayname sut = Relayname.newInstance(expected);

		String actual = sut.value;
		assertEquals(expected, actual);

		boolean result = sut.getClass() == Relayname.class;
		assertTrue("Instance not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		String relayName = "Die vier ????";
		Relayname sut = Relayname.newInstance(relayName);

		boolean result = sut.isEmpty();

		assertFalse("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueFilledWithBlank() {
		Relayname sut = Relayname.newInstance("    ");

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueEmpty() {
		Relayname sut = Relayname.newInstance("");

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		Relayname sut = Relayname.newInstance(null);

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
	public void testSamenessOfTwoNullRelayname() {
		Relayname sut = Relayname.newInstance();
		Relayname otherNullRelayname = Relayname.newInstance();

		assertSame("Two RelaynameNullObjects are not the same!", sut, otherNullRelayname);
	}

	@Test
	public void testToString() {
		String relayname = "Staubwolke";
		Relayname sut = Relayname.newInstance(relayname);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "Staubwolke", actual);
	}

	@Test
	public void testToString_ForEmptyValue() {
		String expected = "";
		Relayname sut = Relayname.newInstance(expected);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForBlankValue() {
		Relayname sut = Relayname.newInstance("   ");

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
	}

	@Test
	public void testToString_ForNullValue() {
		Relayname sut = Relayname.newInstance(null);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
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
	public void testEquals_WithMyself() {
		Relayname sut = Relayname.newInstance("Relayname");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithNull() {
		Relayname sut = Relayname.newInstance("Relayname");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		Relayname sut = Relayname.newInstance("Relayname");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		Relayname sut = Relayname.newInstance("Relayname");
		sut.value = null;
		Relayname secondSut = Relayname.newInstance("Relayname");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		Relayname sut = Relayname.newInstance("Relayname");
		sut.value = null;
		Relayname secondSut = Relayname.newInstance("Relayname");
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithTwoDiffrentValues() {
		Relayname sut = Relayname.newInstance("Relayname");
		Relayname secondSut = Relayname.newInstance("NotRelayname");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithSameValues() {
		Relayname sut = Relayname.newInstance("Relayname");
		Relayname secondSut = Relayname.newInstance("Relayname");

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}