package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.*;

import org.junit.Test;

public class InternetAddressTest {

	@Test
	public void testCreateInternetAddressFromString() throws AddressException {
		String expected = "peter.pan@mail.com";
		InternetAddress sut = new InternetAddress(expected);
		
		String result = sut.getAddress();
		assertEquals("Constructur does not work as expected.", expected, result);
	}
	
	@Test(expected=AddressException.class)
	public void testCreateFalseInternetAddress_noAtSign() throws AddressException {
		String mail = "peter.pan";
		@SuppressWarnings("unused")
		InternetAddress sut = new InternetAddress(mail, true);
	}
	
	@Test(expected=AddressException.class)
	public void testCreateFalseInternetAddress_containsWhitespace() throws AddressException {
		String mail = "Robert van Gember@canda.com";
		@SuppressWarnings("unused")
		InternetAddress sut = new InternetAddress(mail, true);
	}
	
	@Test(expected=AddressException.class)
	public void testCreateFalseInternetAddress_containsWhitespaceOnly() throws AddressException {
		String mail = "  ";
		@SuppressWarnings("unused")
		InternetAddress sut = new InternetAddress(mail, true);
	}
	
	@Test(expected=AddressException.class)
	public void testCreateFalseInternetAddress_emptyString() throws AddressException {
		String mail = "";
		@SuppressWarnings("unused")
		InternetAddress sut = new InternetAddress(mail, true);
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreateFalseInternetAddress_null() throws AddressException {
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
