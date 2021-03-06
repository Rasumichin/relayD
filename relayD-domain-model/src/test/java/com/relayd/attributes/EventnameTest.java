package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
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

	@Test
	public void testIsEmpty_ForValueEmpty() {
		Eventname sut = Eventname.newInstance("");

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		String comment = "Marathon Düsseldorf";
		Eventname sut = Eventname.newInstance(comment);

		boolean result = sut.isEmpty();

		assertFalse("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueFilledWithBlank() {
		Eventname sut = Eventname.newInstance("    ");

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		Eventname sut = Eventname.newInstance(null);

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

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

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == EventnameNullObject.class;

		assertTrue("Instance is not correct!", result);
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

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForEmptyValue() {
		Eventname sut = Eventname.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == EventnameNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForBlankValue() {
		Eventname sut = Eventname.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == EventnameNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testSamenessOfTwoNullEventname() {
		Eventname sut = Eventname.newInstance(null);
		Eventname otherNullEventname = Eventname.newInstance(null);

		assertSame("Two EventnameNullObjects are not the same!", sut, otherNullEventname);
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
		Eventname sut = Eventname.newInstance("   ");

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
	}

	@Test
	public void testToString_ForNullValue() {
		Eventname sut = Eventname.newInstance(null);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
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