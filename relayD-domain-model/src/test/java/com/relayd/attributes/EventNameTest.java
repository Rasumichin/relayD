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
public class EventNameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		EventName sut = EventName.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance() {
		EventName eventName = EventName.newInstance("Name");

		assertNotNull(eventName);
	}

	@Test
	public void testEventNameMustNotBeNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[eventName] must not be 'null'.");

		EventName.newInstance(null);
	}

	@Test
	public void testGetHashCode() {
		EventName sut = EventName.newInstance("Name");

		int hashCode = sut.hashCode();

		assertEquals(2420426, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);

	}

	@Test
	public void testEqualsWithMyself() {
		EventName sut = EventName.newInstance("Name");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		EventName sut = EventName.newInstance("Name");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		EventName sut = EventName.newInstance("Name");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithDiffrentValues() {
		EventName sut = EventName.newInstance("Name1");

		EventName secondName = EventName.newInstance("Name2");

		boolean result = sut.equals(secondName);

		assertFalse(result);

	}

	@Test
	public void testEqualsWithSameValues() {
		EventName sut = EventName.newInstance("Name");

		EventName secondName = EventName.newInstance("Name");

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithUUIDIsNull() {
		EventName sut = EventName.newInstance("dummy");
		sut.value = null;

		EventName secondName = EventName.newInstance("dummy");

		boolean result = sut.equals(secondName);

		assertFalse(result);

	}

	@Test
	public void testEqualsWithBothUUIDAreNull() {
		EventName sut = EventName.newInstance("dummy");
		sut.value = null;

		EventName secondName = EventName.newInstance("dummy");
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testToString() {
		String eventName = "MetroGroup Marathon Düsseldorf";

		EventName sut = EventName.newInstance(eventName);

		String result = sut.toString();

		assertEquals(eventName, result);
	}
}