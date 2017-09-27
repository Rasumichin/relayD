package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Forename.ForenameNullObject;

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
	public void testCreateInstance() {
		Forename sut = Forename.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == ForenameNullObject.class;

		assertTrue("Instance is not correct!", result);
	}

	@Test
	public void testCreateInstance_ForParameter() {
		Forename forename = Forename.newInstance("Forename");

		assertNotNull(forename);

		boolean result = forename.getClass() == Forename.class;
		assertTrue("Instance not correct!", result);
		//Oder
		String toString = forename.toString();
		assertEquals("Forename", toString);
	}

	@Test
	public void testCreateInstance_ForNullValue() {
		Forename sut = Forename.newInstance(null);

		assertNotNull(sut);

		boolean actual = sut.getClass() == ForenameNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForEmptyValue() {
		Forename sut = Forename.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == ForenameNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForBlankValue() {
		Forename sut = Forename.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == ForenameNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testSortByForename() {
		Forename name1 = Forename.newInstance("Justus");
		Forename name2 = Forename.newInstance("Peter");

		int position = Forename.sortByForename(name1, name2);

		assertEquals("[position] not correct!", -6, position);
	}

	@Test
	public void testSortByForename_ForSameNames() {
		Forename name1 = Forename.newInstance("Justus");
		Forename name2 = Forename.newInstance("justus");

		int position = Forename.sortByForename(name1, name2);

		assertEquals("[position] not correct!", 0, position);
	}

	@Test
	public void testToString() {
		String expectedResult = "Marty";
		Forename sut = Forename.newInstance(expectedResult);

		String actualResult = sut.toString();

		assertEquals("String representation is not correct!", expectedResult, actualResult);
	}

	@Test
	public void testToString_ForEmptyValue() {
		String expected = "";
		Forename sut = Forename.newInstance(expected);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForBlankValue() {
		Forename sut = Forename.newInstance("   ");

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
	}

	@Test
	public void testToString_ForNullValue() {
		Forename sut = Forename.newInstance(null);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
	}

	@Test
	public void testSamenessOfTwoNullForenames() {
		Forename sut = Forename.newInstance(null);
		Forename otherNullForename = Forename.newInstance(null);

		assertSame("Two ForenameNullObjects are not the same!", sut, otherNullForename);
	}

	@Test
	public void testIsEmpty_ForValueEmpty() {
		Forename sut = Forename.newInstance("");

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		String comment = "Justus";
		Forename sut = Forename.newInstance(comment);

		boolean result = sut.isEmpty();

		assertFalse("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueFilledWithBlank() {
		Forename sut = Forename.newInstance("    ");

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		Forename sut = Forename.newInstance(null);

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
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
	public void testEquals_WithMyself() {
		Forename sut = Forename.newInstance("Name");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithNull() {
		Forename sut = Forename.newInstance("Name");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		Forename sut = Forename.newInstance("Name");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_WithDiffrentValues() {
		Forename sut = Forename.newInstance("Name1");

		Forename secondSut = Forename.newInstance("Name2");

		boolean result = sut.equals(secondSut);

		assertFalse(result);

	}

	@Test
	public void testEquals_WithSameValues() {
		Forename sut = Forename.newInstance("Name");

		Forename secondSut = Forename.newInstance("Name");

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		Forename sut = Forename.newInstance("dummy");
		sut.value = null;

		Forename secondSut = Forename.newInstance("dummy");

		boolean result = sut.equals(secondSut);

		assertFalse(result);

	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		Forename sut = Forename.newInstance("dummy");
		sut.value = null;

		Forename secondSut = Forename.newInstance("dummy");
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}