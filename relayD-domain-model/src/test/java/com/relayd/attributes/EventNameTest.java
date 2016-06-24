package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 19.05.2016
 * status initial
 */
public class EventNameTest {
	@Test
	public void testGetHashCode() {
		EventName sut = new EventName("Name");

		int hashCode = sut.hashCode();

		assertEquals(2420426, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);

	}

	@Test
	public void testEqualsWithMyself() {
		EventName sut = new EventName("Name");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		EventName sut = new EventName("Name");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		EventName sut = new EventName("Name");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithDiffrentValues() {
		EventName sut = new EventName("Name1");

		EventName secondName = new EventName("Name2");

		boolean result = sut.equals(secondName);

		assertFalse(result);

	}

	@Test
	public void testEqualsWithSameValues() {
		EventName sut = new EventName("Name");

		EventName secondName = new EventName("Name");

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithUUIDIsNull() {
		EventName sut = new EventName("dummy");
		sut.value = null;

		EventName secondName = new EventName("dummy");

		boolean result = sut.equals(secondName);

		assertFalse(result);

	}

	@Test
	public void testEqualsWithBothUUIDAreNull() {
		EventName sut = new EventName("dummy");
		sut.value = null;

		EventName secondName = new EventName("dummy");
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testToString() {
		String eventName = "MetroGroup Marathon Düsseldorf";

		EventName sut = new EventName(eventName);

		String result = sut.toString();

		assertEquals(eventName, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEventNameMustNotBeNull() {
		new EventName(null);
	}
}