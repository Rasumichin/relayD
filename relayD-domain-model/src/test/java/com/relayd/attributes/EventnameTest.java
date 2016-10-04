package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Der Mann, der den Berg abtrug, war derselbe,
 * der anfing, kleine Steine wegzutragen.
 *  - Konfuzius
 *
 * @author schmollc (Christian@relayd.de)
 * @since 19.05.2016
 *
 */
public class EventnameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Eventname sut = Eventname.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance() {
		Eventname eventName = Eventname.newInstance("Name");

		assertNotNull(eventName);
	}

	@Test
	public void testEventNameMustNotBeNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[eventname] must not be 'null'.");

		Eventname.newInstance(null);
	}

	@Test
	public void testGetHashCode() {
		Eventname sut = Eventname.newInstance("Name");

		int hashCode = sut.hashCode();

		assertEquals(2420426, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);

	}

	@Test
	public void testEqualsWithMyself() {
		Eventname sut = Eventname.newInstance("Name");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		Eventname sut = Eventname.newInstance("Name");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Eventname sut = Eventname.newInstance("Name");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithDiffrentValues() {
		Eventname sut = Eventname.newInstance("Name1");

		Eventname secondName = Eventname.newInstance("Name2");

		boolean result = sut.equals(secondName);

		assertFalse(result);

	}

	@Test
	public void testEqualsWithSameValues() {
		Eventname sut = Eventname.newInstance("Name");

		Eventname secondName = Eventname.newInstance("Name");

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithUUIDIsNull() {
		Eventname sut = Eventname.newInstance("dummy");
		sut.value = null;

		Eventname secondName = Eventname.newInstance("dummy");

		boolean result = sut.equals(secondName);

		assertFalse(result);

	}

	@Test
	public void testEqualsWithBothUUIDAreNull() {
		Eventname sut = Eventname.newInstance("dummy");
		sut.value = null;

		Eventname secondName = Eventname.newInstance("dummy");
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testToString() {
		String eventName = "MetroGroup Marathon Düsseldorf";

		Eventname sut = Eventname.newInstance(eventName);

		String result = sut.toString();

		assertEquals(eventName, result);
	}
}