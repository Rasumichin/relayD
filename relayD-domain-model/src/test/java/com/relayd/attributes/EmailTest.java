package com.relayd.attributes;

import static org.junit.Assert.*;
import static com.relayd.attributes.Email.AT_SIGN;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   29.03.2016
 * 
 */
public class EmailTest {

	// @formatter:off
	private static final String VALID_MAIL_OF_JUSTUS_JONAS 	 = "Justus.Jonas" + AT_SIGN + "rockyBeach.com";
	private static final String INVALID_MAIL_OF_JUSTUS_JONAS = "Justus.JonasrockyBeach.com";
	private static final String VALID_MAIL_OF_PETER_SHAW 	 = "Peter.Shaw" + AT_SIGN + "rockyBeach.com";
	// @formatter:on

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testIsValidWithValidValue() {
		boolean result = Email.isValidAddress(VALID_MAIL_OF_JUSTUS_JONAS);
		assertTrue("email should be valid!", result);
	}

	@Test
	public void testIsValidWithInvalidValue() {
		String eMailAddress = INVALID_MAIL_OF_JUSTUS_JONAS;
		boolean result = Email.isValidAddress(eMailAddress);

		assertFalse("email '" + eMailAddress + "' should be invalid.", result);
	}

	@Test
	public void testIsValidWithIllegalNullValue() {
		boolean result = Email.isValidAddress(null);
		assertFalse("email should be invalid.", result);
	}
	
	@Test
	public void testIsValidWithAddressThatContainsASpace() {
		String eMailAddress = "abraham.van helsing@stoker.com";
		boolean result = Email.isValidAddress(eMailAddress);
		
		assertFalse("email '" + eMailAddress + "' should be invalid.", result);
	}

	@Test
	public void testIsValidWithEmptyValue() {
		String eMailAddress = "";
		boolean result = Email.isValidAddress(eMailAddress);

		assertFalse("email '" + eMailAddress + "' should be invalid.", result);
	}

	@Test
	public void testIsValidWithWhitespaceOnly() {
		String eMailAddress = "   ";
		boolean result = Email.isValidAddress(eMailAddress);

		assertFalse("email '" + eMailAddress + "' should be invalid.", result);
	}

	@Test
	public void testCreateInstanceWithValidEmailAdress() {
		String value = VALID_MAIL_OF_JUSTUS_JONAS;
		Email email = Email.newInstance(value);

		assertEquals(value, email.toString());
	}

	@Test
	public void testCreateInstanceWithInvalidEmailAdress() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[email] has not a valid format!");

		String value = INVALID_MAIL_OF_JUSTUS_JONAS;
		Email email = Email.newInstance(value);

		assertEquals(value, email.toString());
	}

	@Test
	public void testCreateInstanceWithIllegalNullValue() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[email] must not be 'null'.");

		Email.newInstance(null);
	}

	@Test
	public void testGetHashCode() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		int hashCode = sut.hashCode();

		assertEquals(629424970, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals("[hashCode] not correct!", 31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean result = sut.equals(sut);

		assertTrue("values should be equal!", result);
	}

	@Test
	public void testEqualsWithNull() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean result = sut.equals(null);

		assertFalse("values should not be equal!", result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean result = sut.equals(new String());

		assertFalse("values should not be equal!", result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		sut.value = null;
		Email secondName = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean result = sut.equals(secondName);

		assertFalse("values should not be equal!", result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		sut.value = null;
		Email secondName = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue("values should be equal!", result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		Email secondName = Email.newInstance(VALID_MAIL_OF_PETER_SHAW);

		boolean result = sut.equals(secondName);

		assertFalse("values should not be equal!", result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		Email secondName = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean result = sut.equals(secondName);

		assertTrue("values should be equal!", result);
	}

	@Test
	public void testGetDomainPartWhenIsEmpty_False() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		String expected = "rockyBeach.com";
		
		String result = sut.getDomainPart();
		assertEquals("Domain part is not correct.", expected, result);
	}
	
	@Test
	public void testSetLocalPartWithIllegalNullValue() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Local part must not be 'null'.");

		sut.setLocalPart(null);
	}
	
	@Test
	public void testSetLocalPartWithIllegalEmptyValue() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Local part [] could not be part of a valid email address.");

		sut.setLocalPart("");
	}
	
	@Test
	public void testSetLocalPartToValidFullName() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		String newLocalPart = "bob.andrews";
		Email expected = Email.newInstance(newLocalPart + AT_SIGN + "rockyBeach.com");
		
		sut.setLocalPart(newLocalPart);
		assertEquals("Local part of email has not been set correctly.", expected, sut);
	}
	
	@Test
	public void testSetLocalPartToValidSingleName() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		String newLocalPart = "kent";
		Email expected = Email.newInstance(newLocalPart + AT_SIGN + sut.getDomainPart());
		
		sut.setLocalPart(newLocalPart);
		assertEquals("Local part of email has not been set correctly.", expected, sut);
	}
	
	@Test
	public void testCreateFromLocalAndDomainPartWithValidValues() {
		String localPart = "martin.fowler";
		String domainPart = "refactoring.com";
		Email expected = Email.newInstance(localPart + AT_SIGN + domainPart);
		
		Email result = Email.createFromLocalAndDomainPart(localPart, domainPart);
		assertEquals("Email has not been correctly composed.", expected, result);
	}
	
	@Test
	public void testCreateFromLocalAndDomainPartWithInvalidLocalPart_null () {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[localPart] must not be 'null'.");

		String domainPart = "refactoring.com";

		Email.createFromLocalAndDomainPart(null, domainPart);
	}

	@Test
	public void testCreateFromLocalAndDomainPartWithInvalidDomainPart_null () {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[domainPart] must not be 'null'.");

		String localPart = "martin.fowler";

		Email.createFromLocalAndDomainPart(localPart, null);
	}
	
	@Test
	public void testClone() {
		Email sut = Email.newInstance(VALID_MAIL_OF_PETER_SHAW);
		Email clone = sut.clone();
		
		assertNotSame("Cloning failed.", sut, clone);
		assertEquals("Cloning failed.", sut, clone);
	}
}