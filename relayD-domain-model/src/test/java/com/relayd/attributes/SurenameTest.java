package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Surename.SurenameNullObject;

/**
 * Jede lange Reise beginnt mit dem ersten Schritt
 *  - Laotse
 *
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   22.03.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SurenameTest {

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Surename sut = Surename.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;
		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testNewInstance() {
		Surename sut = Surename.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == SurenameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForValidValue() {
		String expected = "Jonas";
		Surename surename = Surename.newInstance(expected);

		String actual = surename.value;
		assertEquals(expected, actual);
	}

	@Test
	public void testNewInstance_ForBlankValue() {
		Surename sut = Surename.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == SurenameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForEmptyValue() {
		Surename sut = Surename.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == SurenameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForNullValue() {
		Surename sut = Surename.newInstance(null);

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == SurenameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testIsEmpty_ForValidValue() {
		Surename sut = Surename.newInstance("Jonas");

		boolean result = sut.isEmpty();

		assertFalse("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForNullObject() {
		Surename sut = Surename.newInstance();

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testSortBySurename() {
		Surename name1 = Surename.newInstance("Jonas");
		Surename name2 = Surename.newInstance("Shaw");

		int position = Surename.sortBySurename(name1, name2);

		assertEquals("[position] not correct!", -9, position);
	}

	@Test
	public void testSortBySurename_ForSameNames() {
		Surename name1 = Surename.newInstance("Jonas");
		Surename name2 = Surename.newInstance("jonas");

		int position = Surename.sortBySurename(name1, name2);

		assertEquals("[position] not correct!", 0, position);
	}

	@Test
	public void testToString_ForValidValue() {
		String expected = "Jonas";
		Surename surename = Surename.newInstance(expected);

		String actual = surename.toString();
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForNullObject() {
		String expected = "";
		Surename surename = Surename.newInstance();

		String actual = surename.toString();
		assertEquals("String representation is not correct!", expected, actual);
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