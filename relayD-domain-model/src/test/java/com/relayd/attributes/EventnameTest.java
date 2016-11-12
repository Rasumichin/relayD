package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Eventname.EventnameNullObject;

/**
 * Der Mann, der den Berg abtrug, war derselbe,
 * der anfing, kleine Steine wegzutragen.
 *  - Konfuzius
 *
 * @author schmollc (Christian@relayd.de)
 * @since 19.05.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventnameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Eventname sut = Eventname.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class is not Serializable!", result);
	}

	@Test
	public void testCreateInstance() {
		Eventname sut = Eventname.newInstance();

		assertNotNull(sut);

		boolean result = sut.getClass() == EventnameNullObject.class;

		assertTrue("Instance not correct!", result);
	}

	@Test
	public void testCreateInstance_ForParameter() {
		Eventname sut = Eventname.newInstance("eventname");

		assertNotNull(sut);

		boolean result = sut.getClass() == Eventname.class;

		assertTrue("Instance not correct!", result);
	}

	@Test
	public void testCreateInstance_ForNullValue() {
		Eventname sut = Eventname.newInstance(null);

		assertNotNull(sut);

		boolean actual = sut.getClass() == EventnameNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testIsEmpty_usualValue() {
		Eventname sut = Eventname.newInstance("Eventname");

		boolean result = sut.isEmpty();

		assertFalse("'isEmpty' check is not correct!", result);
	}

	@Test
	public void testIsEmpty_nullValue() {
		Eventname sut = Eventname.newInstance(null);

		boolean result = sut.isEmpty();

		assertTrue("'isEmpty' check is not correct!", result);
	}

	@Test
	public void testToString() {
		String expected = "MetroGroup Marathon Düsseldorf";

		Eventname sut = Eventname.newInstance(expected);

		String actual = sut.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void testToString_ForEmptyValue() {
		String expected = "";
		Eventname sut = Eventname.newInstance(expected);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForBlankValue() {
		String blankForename = "   ";
		Eventname sut = Eventname.newInstance(blankForename);

		String actual = sut.toString();

		String expected = "";
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForNullValue() {
		Eventname sut = Eventname.newInstance(null);

		String expected = "";
		String actual = sut.toString();

		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		Eventname sut = Eventname.newInstance("Name");

		int hashCode = sut.hashCode();

		assertEquals(2420426, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);

	}

	@Test
	public void testEquals_WithMyself() {
		Eventname sut = Eventname.newInstance("Name");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithNull() {
		Eventname sut = Eventname.newInstance("Name");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		Eventname sut = Eventname.newInstance("Name");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_WithDiffrentValues() {
		Eventname sut = Eventname.newInstance("Name1");

		Eventname secondSut = Eventname.newInstance("Name2");

		boolean result = sut.equals(secondSut);

		assertFalse(result);

	}

	@Test
	public void testEquals_WithSameValues() {
		Eventname sut = Eventname.newInstance("Name");

		Eventname secondSut = Eventname.newInstance("Name");

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		Eventname sut = Eventname.newInstance("dummy");
		sut.value = null;

		Eventname secondSut = Eventname.newInstance("dummy");

		boolean result = sut.equals(secondSut);

		assertFalse(result);

	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		Eventname sut = Eventname.newInstance("dummy");
		sut.value = null;

		Eventname secondSut = Eventname.newInstance("dummy");
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}