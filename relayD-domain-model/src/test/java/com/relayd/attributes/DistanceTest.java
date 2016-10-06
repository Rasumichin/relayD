package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.math.BigDecimal;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

/**
 * Glück entsteht oft durch Aufmerksamkeit in kleinen Dingen, Unglück oft durch Vernachlässigung kleiner Dinge.
 *  - Wilhelm Busch
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   05.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DistanceTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testIsSerializable() {
		BigDecimal dummyBigDecimal = BigDecimal.ONE;
		Distance sut = Distance.newInstance(dummyBigDecimal);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance_For2Digits() {
		BigDecimal distance = new BigDecimal("10.12");
		Distance sut = Distance.newInstance(distance);

		assertNotNull(sut);
		assertEquals("10.12", sut.toString());
	}

	@Test
	public void testCreateInstance_For3Digits() {
		BigDecimal distance = new BigDecimal("10.127");
		Distance sut = Distance.newInstance(distance);

		assertNotNull(sut);
		assertEquals("10.13", sut.toString());
	}

	@Test
	public void testCreateInstanceWithIllegalNullValue() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[distance] must not be 'null'.");

		Distance.newInstance(null);
	}

	@Test
	public void testToString() {
		BigDecimal expected = new BigDecimal("11.23");
		Distance sut = Distance.newInstance(expected);

		String result = sut.toString();

		assertEquals(expected.toString(), result);
	}

	@Test
	public void testGetHashCode() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);

		int hashCode = sut.hashCode();

		assertEquals(31033, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);
		sut.value = null;
		Distance secondName = Distance.newInstance(distance);

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);
		sut.value = null;
		Distance secondName = Distance.newInstance(distance);
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		BigDecimal distanceOne = BigDecimal.ONE;
		Distance sut = Distance.newInstance(distanceOne);
		BigDecimal distanceTwo = BigDecimal.TEN;
		Distance secondName = Distance.newInstance(distanceTwo);

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);
		Distance secondName = Distance.newInstance(distance);

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}
}