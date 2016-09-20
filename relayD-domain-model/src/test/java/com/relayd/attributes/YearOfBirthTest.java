package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 18.09.2016
 */
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
		final Integer YEAR = 1971;
		YearOfBirth yearOfBirth = YearOfBirth.newInstance(YEAR);

		assertEquals(YEAR.toString(), yearOfBirth.toString());
	}

	@Test
	public void testCreateInstanceWithIllegalNullValue() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[year] must not be 'null'!");

		YearOfBirth.newInstance(null);
	}

	@Test
	public void testGetHashCode() {
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
		YearOfBirth secondYearOfBirth = YearOfBirth.newInstance(1971);

		boolean result = sut.equals(secondYearOfBirth);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);
		sut.value = null;
		YearOfBirth secondYearOfBirth = YearOfBirth.newInstance(1971);
		secondYearOfBirth.value = null;

		boolean result = sut.equals(secondYearOfBirth);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);
		YearOfBirth secondYearOfBirth = YearOfBirth.newInstance(1987);

		boolean result = sut.equals(secondYearOfBirth);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		YearOfBirth sut = YearOfBirth.newInstance(1971);
		YearOfBirth secondYearOfBirth = YearOfBirth.newInstance(1971);

		boolean result = sut.equals(secondYearOfBirth);

		assertTrue(result);
	}
}