package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   29.03.2016
 * status   initial
 */
// TODO -schmollc- Hier muss noch mehr Musik rein.(Mail Pattern)
public class EmailTest {

	@Test
	public void testCreateValidObject() {
		String value = "Justus.Jonas@rockyBeach.com";
		Email email = Email.newInstance(value);

		assertEquals(value, email.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInvalidObject_Null() {
		Email.newInstance(null);
	}

	@Test
	public void testGetHashCode() {
		Email sut = Email.newInstance("Justus.Jonas@rockyBeach.com");

		int hashCode = sut.hashCode();

		assertEquals(629424970, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Email sut = Email.newInstance("Email");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		Email sut = Email.newInstance("Email");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Email sut = Email.newInstance("Email");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Email sut = Email.newInstance("Email");
		sut.value = null;
		Email secondName = Email.newInstance("Email");

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Email sut = Email.newInstance("Email");
		sut.value = null;
		Email secondName = Email.newInstance("Email");
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Email sut = Email.newInstance("Email");
		Email secondName = Email.newInstance("NotEmail");

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Email sut = Email.newInstance("Email");
		Email secondName = Email.newInstance("Email");

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

}