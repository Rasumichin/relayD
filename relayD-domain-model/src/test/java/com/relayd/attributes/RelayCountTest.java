package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.RelayCount.RelayCountNullObject;

/**
 * Ja, es ist seltsam: je mehr ich trainiere, desto mehr Glück habe ich!
 *  - Tiger Woods
 *
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   24.05.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayCountTest {

	@Test
	public void testIsSerializable() {
		Integer dummyInteger = Integer.valueOf(10);

		RelayCount sut = RelayCount.newInstance(dummyInteger);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testNewInstance() {
		RelayCount sut = RelayCount.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean condition = sut.getClass() == RelayCountNullObject.class;

		assertTrue("Instance is not correct!", condition);
	}

	@Test
	public void testNewInstance_ForNullValue() {
		RelayCount sut = RelayCount.newInstance(null);

		assertNotNull("Not a valid instance!", sut);

		boolean condition = sut.getClass() == RelayCountNullObject.class;

		assertTrue("Instance is not correct!", condition);
	}

	@Test
	public void testNewInstance_ForParameter() {
		Integer expected = 10;
		RelayCount sut = RelayCount.newInstance(expected);

		Integer actual = sut.value;
		assertEquals(expected, actual);

		boolean condition = sut.getClass() == RelayCount.class;
		assertTrue("Instance not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		Integer numberOfRelays = 10;
		RelayCount sut = RelayCount.newInstance(numberOfRelays);

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		RelayCount sut = RelayCount.newInstance(null);

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIntValue() {
		int expected = 10;
		RelayCount sut = RelayCount.newInstance(expected);

		int actual = sut.intValue();

		assertEquals("[intValue] not correct!", expected, actual);
	}

	@Test
	public void testIntValue_ForNOP() {
		RelayCount sut = RelayCount.newInstance();

		int actual = sut.intValue();

		int expected = 0;

		assertEquals("[intValue] not correct!", expected, actual);
	}

	@Test
	public void testToString() {
		Integer numberOfRelays = 10;
		RelayCount sut = RelayCount.newInstance(numberOfRelays);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "10", actual);
	}

	@Test
	public void testToString_ForNullValue() {
		RelayCount sut = RelayCount.newInstance(null);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "0", actual);
	}

	@Test
	public void testHashCode() {
		RelayCount sut = RelayCount.newInstance(10);

		int hashCode = sut.hashCode();

		assertEquals(41, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		RelayCount sut = RelayCount.newInstance(10);

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithNull() {
		RelayCount sut = RelayCount.newInstance(10);

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		RelayCount sut = RelayCount.newInstance(10);

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		RelayCount sut = RelayCount.newInstance(10);
		sut.value = null;
		RelayCount secondSut = RelayCount.newInstance(11);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		RelayCount sut = RelayCount.newInstance(10);
		sut.value = null;
		RelayCount secondSut = RelayCount.newInstance(11);
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithTwoDiffrentValues() {
		RelayCount sut = RelayCount.newInstance(10);
		RelayCount secondSut = RelayCount.newInstance(11);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithSameValues() {
		RelayCount sut = RelayCount.newInstance(10);
		RelayCount secondSut = RelayCount.newInstance(10);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}