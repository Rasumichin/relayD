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
		assertEquals("[value] not correct!", "10.12", sut.toString());
	}

	@Test
	public void testCreateInstance_For3Digits() {
		BigDecimal distance = new BigDecimal("10.127");
		Distance sut = Distance.kilometers(distance);

		assertNotNull(sut);
		assertEquals("[value] not correct!", "10.13", sut.toString());
	}

	@Test
	public void testKilometers() {
		BigDecimal distance = new BigDecimal("12.34");
		Distance sut = Distance.kilometers(distance);

		assertNotNull(sut);
		assertEquals("12.34 km", sut.toStringWithUnity());
	}

	@Test
	public void testMeters() {
		BigDecimal distance = new BigDecimal("12.34");
		Distance sut = Distance.meters(distance);

		assertNotNull(sut);
		assertEquals("12.34 m", sut.toStringWithUnity());
	}

	@Test
	public void testCreateInstanceWithIllegalNullValue() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[distance] must not be 'null'.");

		Distance.newInstance(null);
	}

	@Test
	public void testAdd_WithSameUnity() {
		Distance sutFirst = Distance.kilometers(new BigDecimal("7.34"));
		Distance sutSecond = Distance.kilometers(new BigDecimal("3.57"));

		Distance actual = sutFirst.add(sutSecond);

		Distance expected = Distance.kilometers(new BigDecimal("10.91"));
		assertEquals("[add] not corret!", expected, actual);
	}

	@Test
	public void testAdd_WithDiffrentUnity() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Only Distances with same unity are supported!");

		Distance sutFirst = Distance.kilometers(new BigDecimal("7.34"));
		Distance sutSecond = Distance.meters(new BigDecimal("3.57"));

		sutFirst.add(sutSecond);
	}

	@Test
	public void testToString_ForCutZeros() {
		BigDecimal distance = new BigDecimal("12.400");

		Distance sut = Distance.kilometers(distance);

		String actual = sut.toString();

		assertEquals("[value] not correct!", "12.4", actual);
	}

	@Test
	public void testToString_ForUnity() {
		BigDecimal expected = new BigDecimal("11.23");
		Distance sut = Distance.newInstance(expected);

		String result = sut.toStringWithUnity();

		assertEquals("11.23 km", result);
	}

	@Test
	public void testToString() {
		BigDecimal expected = new BigDecimal("11.23");
		Distance sut = Distance.newInstance(expected);

		String actual = sut.toString();

		assertEquals(expected.toString(), actual);
	}

	@Test
	public void testHashCode() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);

		int hashCode = sut.hashCode();

		assertEquals(31033, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithNull() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);
		sut.value = null;
		Distance secondSut = Distance.newInstance(distance);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);
		sut.value = null;
		Distance secondSut = Distance.newInstance(distance);
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithTwoDiffrentValues() {
		BigDecimal distanceOne = BigDecimal.ONE;
		Distance sut = Distance.newInstance(distanceOne);
		BigDecimal distanceTwo = BigDecimal.TEN;
		Distance secondSut = Distance.newInstance(distanceTwo);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithSameValues() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.newInstance(distance);
		Distance secondSut = Distance.newInstance(distance);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}