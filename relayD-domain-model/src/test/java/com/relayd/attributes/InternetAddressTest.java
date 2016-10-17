package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Used this test class to get familiar with the class under test: javax.mail.internet.InternetAddress.
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   01.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InternetAddressTest {

	@Test
	public void testCreateInstance_usualAddress() throws AddressException {
		String expected = "peter.pan@mail.com";
		InternetAddress sut = new InternetAddress(expected, true);

		String result = sut.getAddress();
		assertEquals("Constructur does not work as expected.", expected, result);
	}

	@Test
	public void testCreateInstance_localPartContainsTwoDots() throws AddressException {
		String expected = "this..IsCorrect@mail.com";
		InternetAddress sut = new InternetAddress(expected, true);

		String result = sut.getAddress();
		assertEquals("Constructur does not work as expected.", expected, result);
	}

	@Test(expected = AddressException.class)
	public void testCreateFalseInstance_noAtSign() throws AddressException {
		String mail = "peter.pan";
		@SuppressWarnings("unused")
		InternetAddress sut = new InternetAddress(mail, true);
	}

	@Test(expected = AddressException.class)
	public void testCreateFalseInstance_containsWhitespace() throws AddressException {
		String mail = "Robert van Gember@canda.com";
		@SuppressWarnings("unused")
		InternetAddress sut = new InternetAddress(mail, true);
	}

	@Test(expected = AddressException.class)
	public void testCreateFalseInstance_containsWhitespaceOnly() throws AddressException {
		String mail = "  ";
		@SuppressWarnings("unused")
		InternetAddress sut = new InternetAddress(mail, true);
	}

	@Test(expected = AddressException.class)
	public void testCreateFalseInstance_emptyString() throws AddressException {
		String mail = "";
		@SuppressWarnings("unused")
		InternetAddress sut = new InternetAddress(mail, true);
	}

	@Test(expected = NullPointerException.class)
	public void testCreateFalseInstance_nullValue() throws AddressException {
		String mail = null;
		@SuppressWarnings("unused")
		InternetAddress sut = new InternetAddress(mail, true);
	}

	@Test
	public void testGetPersonal() throws AddressException, UnsupportedEncodingException {
		String expected = "Rocky Balboa";
		String mail = "rocky.balboa@boxing.com";
		InternetAddress sut = new InternetAddress(mail, true);

		sut.setPersonal(expected);
		String result = sut.getPersonal();
		assertEquals("Personal name is not correct.", expected, result);
	}
}
