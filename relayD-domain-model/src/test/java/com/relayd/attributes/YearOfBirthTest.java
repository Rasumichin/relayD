package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.YearOfBirth.YearOfBirthNullObject;

/**
 * Verantwortlich ist man nicht nur für das, was man tut, sondern auch für das, was man nicht tut.
 *  - Laotse
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 18.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class YearOfBirthTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testIsSerializable() {
		Integer dummyInteger = 1900;
		YearOfBirth sut = YearOfBirth.newInstance(dummyInteger);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance() {
		Integer expected = 1971;

		YearOfBirth sut = YearOfBirth.newInstance(expected);

		Integer actual = sut.getValue();
		assertEquals(expected, actual);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testCreateInstance_ForNullValue() {
		YearOfBirth sut = YearOfBirth.newInstance(null);
		assertNotNull("Instance creation was not correct!", sut);

		Class expected = YearOfBirthNullObject.class;
		Class actual = sut.getClass();
		assertEquals("Instance creation was not correct!", expected, actual);
	}

	@Test
	public void testSortByYearOfBirth() {
		YearOfBirth yearOfBirth1 = YearOfBirth.newInstance(1971);
		YearOfBirth yearOfBirth2 = YearOfBirth.newInstance(1973);

		int position = YearOfBirth.sortByYearOfBirth(yearOfBirth1, yearOfBirth2);

		assertEquals("[yearOfBirth] not correct!", -2, position);
	}

	@Test
	public void testToString() {
		Integer year = 1978;

		YearOfBirth yearOfBirth = YearOfBirth.newInstance(year);

		String actual = yearOfBirth.toString();

		assertEquals("1978", actual);
	}

	@Test
	public void testToString_ForNullValue() {
		YearOfBirth sut = YearOfBirth.newInstance(null);

		String actual = sut.toString();

		String expected = "";
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);

		int hashCode = sut.hashCode();

		assertEquals(2002, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);
		sut.value = null;
		YearOfBirth secondSut = YearOfBirth.newInstance(1971);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);
		sut.value = null;
		YearOfBirth secondSut = YearOfBirth.newInstance(1971);
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);
		YearOfBirth secondSut = YearOfBirth.newInstance(1987);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);
		YearOfBirth secondSut = YearOfBirth.newInstance(1971);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testGetValue() {
		Integer expected = Integer.valueOf(1965);
		YearOfBirth sut = YearOfBirth.newInstance(expected);

		Integer actual = sut.getValue();
		assertEquals("[value] is not correct.", expected, actual);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		YearOfBirth sut = YearOfBirth.newInstance(2001);

		boolean result = sut.isEmpty();
		assertFalse("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		YearOfBirth sut = YearOfBirth.newInstance(null);

		boolean result = sut.isEmpty();
		assertTrue("[result] for isEmpty is not correct!", result);
	}
}