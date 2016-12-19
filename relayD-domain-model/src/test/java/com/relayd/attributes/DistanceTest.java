package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.math.BigDecimal;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Distance.DistanceNullObject;

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
		Distance sut = Distance.kilometers(dummyBigDecimal);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testCreateInstance() {
		Distance sut = Distance.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean condition = sut.getClass() == DistanceNullObject.class;

		assertTrue("Instance is not correct!", condition);
	}

	@Test
	public void testCreateInstance_For2Digits() {
		BigDecimal distance = new BigDecimal("10.12");
		Distance sut = Distance.kilometers(distance);

		assertNotNull("Not a valid instance!", sut);
		assertEquals("[value] not correct!", "10.12", sut.toString());
	}

	@Test
	public void testCreateInstance_For3Digits() {
		BigDecimal distance = new BigDecimal("10.127");
		Distance sut = Distance.kilometers(distance);

		assertNotNull("Not a valid instance!", sut);
		assertEquals("[value] not correct!", "10.13", sut.toString());
	}

	@Test
	public void testKilometers() {
		BigDecimal distance = new BigDecimal("12.34");
		Distance sut = Distance.kilometers(distance);

		assertNotNull("Not a valid instance!", sut);
		assertEquals("12.34 km", sut.toStringWithUnity());
	}

	@Test
	public void testMeters() {
		BigDecimal distance = new BigDecimal("12.34");
		Distance sut = Distance.meters(distance);

		assertNotNull("Not a valid instance!", sut);
		assertEquals("12.34 m", sut.toStringWithUnity());
	}

	@Test
	public void testKilometers_ForNullValue() {
		Distance sut = Distance.kilometers(null);

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == DistanceNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testMeters_ForNullValue() {
		Distance sut = Distance.meters(null);

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == DistanceNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		BigDecimal distance = new BigDecimal("12.34");
		Distance sut = Distance.kilometers(distance);

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		Distance sut = Distance.kilometers(null);

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testAdd_ForSameUnity() {
		Distance sutFirst = Distance.kilometers(new BigDecimal("7.34"));
		Distance sutSecond = Distance.kilometers(new BigDecimal("3.57"));

		Distance actual = sutFirst.add(sutSecond);

		Distance expected = Distance.kilometers(new BigDecimal("10.91"));
		assertEquals("[add] not corret!", expected, actual);
	}

	@Test
	public void testAdd_ForDiffrentUnity() {
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
		Distance sut = Distance.kilometers(expected);

		String actual = sut.toStringWithUnity();

		assertEquals("11.23 km", actual);
	}

	@Test
	public void testToString() {
		BigDecimal expected = new BigDecimal("11.23");
		Distance sut = Distance.kilometers(expected);

		String actual = sut.toString();

		assertEquals(expected.toString(), actual);
	}

	@Test
	public void testToString_ForNullValue() {
		Distance sut = Distance.kilometers(null);

		String actual = sut.toString();

		String expected = "";
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.kilometers(distance);

		int hashCode = sut.hashCode();

		assertEquals(31033, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_ForMyself() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.kilometers(distance);

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_ForNull() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.kilometers(distance);

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEquals_ForNotCompatibleClass() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.kilometers(distance);

		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEquals_ForValueIsNull() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.kilometers(distance);
		sut.value = null;
		Distance secondSut = Distance.kilometers(distance);

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEquals_ForBothValuesAreNull() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.kilometers(distance);
		sut.value = null;
		Distance secondSut = Distance.kilometers(distance);
		secondSut.value = null;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_ForTwoDiffrentValues() {
		BigDecimal distanceOne = BigDecimal.ONE;
		Distance sut = Distance.kilometers(distanceOne);
		BigDecimal distanceTwo = BigDecimal.TEN;
		Distance secondSut = Distance.kilometers(distanceTwo);

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEquals_ForSameValues() {
		BigDecimal distance = BigDecimal.TEN;
		Distance sut = Distance.kilometers(distance);
		Distance secondSut = Distance.kilometers(distance);

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}