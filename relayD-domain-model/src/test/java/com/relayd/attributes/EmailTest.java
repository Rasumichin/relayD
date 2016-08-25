package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   29.03.2016
 * status   initial
 */
public class EmailTest {

	// @formatter:off
	private static final String VALID_MAIL_FROM_JUSTUS_JONAS 	= "Justus.Jonas@rockyBeach.com";
	private static final String INVALID_MAIL_FROM_JUSTUS_JONAS	= "Justus.JonasrockyBeach.com";
	private static final String VALID_MAIL_FROM_PETER_SHAW 		= "Peter.Shaw@rockyBeach.com";
	// @formatter:on

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateObject_WithEmptyValue() {
		String value = "";
		Email email = Email.newInstance(value);

		assertEquals(value, email.toString());
	}

	@Test
	public void testCreateObject_WithWihteSpaceValue() {
		String value = "   ";
		Email email = Email.newInstance(value);

		assertEquals(value, email.toString());
	}

	@Test
	public void testCreateObject_WithValidEmailAdress() {
		String value = VALID_MAIL_FROM_JUSTUS_JONAS;
		Email email = Email.newInstance(value);

		assertEquals(value, email.toString());
	}

	@Test
	public void testCreateObject_WithInvalidEmailAdress() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[email] not valid format!");

		String value = INVALID_MAIL_FROM_JUSTUS_JONAS;
		Email email = Email.newInstance(value);

		assertEquals(value, email.toString());
	}

	@Test
	public void testCreateObject_WithNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[email] must not be 'null'.");

		Email.newInstance(null);
	}

	@Test
	public void testGetHashCode() {
		Email sut = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);

		int hashCode = sut.hashCode();

		assertEquals(629424970, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals("[hashCode] not correct!", 31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Email sut = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);

		boolean result = sut.equals(sut);

		assertTrue("values should be equal!", result);
	}

	@Test
	public void testEqualsWithNull() {
		Email sut = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);

		boolean result = sut.equals(null);

		assertFalse("values should not be equal!", result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Email sut = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);

		boolean result = sut.equals(new String());

		assertFalse("values should not be equal!", result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Email sut = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);
		sut.value = null;
		Email secondName = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);

		boolean result = sut.equals(secondName);

		assertFalse("values should not be equal!", result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Email sut = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);
		sut.value = null;
		Email secondName = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue("values should be equal!", result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Email sut = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);
		Email secondName = Email.newInstance(VALID_MAIL_FROM_PETER_SHAW);

		boolean result = sut.equals(secondName);

		assertFalse("values should not be equal!", result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Email sut = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);
		Email secondName = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);

		boolean result = sut.equals(secondName);

		assertTrue("values should be equal!", result);
	}

	@Test
	public void testIsValid_WithValidValue() {
		boolean result = Email.isValid(VALID_MAIL_FROM_JUSTUS_JONAS);
		assertTrue("email should be valid!", result);
	}

	@Test
	public void testIsValid_WithInvalidValue() {
		boolean result = Email.isValid(INVALID_MAIL_FROM_JUSTUS_JONAS);
		assertFalse("email should be invalid!", result);
	}

	@Test
	public void testIsEmpty_True() {
		Email sut = Email.newInstance("");

		boolean result = sut.isEmpty();

		assertTrue("expect empty [EMail]!", result);
	}

	@Test
	public void testIsEmpty_False() {
		Email sut = Email.newInstance(VALID_MAIL_FROM_JUSTUS_JONAS);

		boolean result = sut.isEmpty();

		assertFalse("expect filled [Email]", result);
	}
}