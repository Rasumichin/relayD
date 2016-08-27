package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   29.03.2016
 * status   initial
 */
public class Email implements Serializable {
	private static final long serialVersionUID = 1L;

	private static String pattern = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

	// TODO (Erik, 2016-08-27): I am not sure whether we should use class InternetAddress from JavaMail:
	// http://docs.oracle.com/javaee/7/api/javax/mail/internet/InternetAddress.html
	String value;

	private Email(String email) {
		super();
		value = email;
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static Email newInstance(String email) {
		validate(email);
		return new Email(email);
	}

	private static void validate(String email) {
		if (email == null) {
			throw new IllegalArgumentException("[email] must not be 'null'.");
		}

		if (!isValid(email)) {
			throw new IllegalArgumentException("[email] not valid format!");
		}
	}

	public boolean isEmpty() {
		return value.isEmpty();
	}

	public static boolean isValid(String mail) {
		if (mail == null) {
			return false;
		}

		if (mail.trim().isEmpty()) {
			return true;
		}

		boolean result = mail.matches(pattern);
		return result;
	}

	/**
	 * Answers the domain part of the email address.
	 * 
	 * @return The so-called 'domain part' of a valid email address or an empty string in case the receiver itself is empty.
	 * 			Given the email consists of 'my.name@foo.com' the returned value would be 'foo.com'.
	 * 
	 */
	public String getDomainPart() {
		if (isEmpty()) {
			return value;
		}
		
		String[] emailParts = value.split("@");
		String domainPart = emailParts[emailParts.length-1];
		
		return domainPart;
	}
	
	/**
	 * Sets the so-called 'local part' of the email address to the given value.
	 * 
	 * @param newLocalPart The new local part of the email address. Can be a full name like 'kent.beck' or just a single
	 * 						name like 'vlissides' or 'adele'.
	 * @throws IllegalStateException when the receiver itself is empty.
	 * @throws IllegalArgumentException when the new local part is 'null' or empty or an invalid value (like 'my..name').
	 * 
	 */
	public void setLocalPart(String newLocalPart) {
		if (isEmpty()) {
			throw new IllegalStateException("Email value must not be empty when setting the local part.");
		}
		if (newLocalPart == null) {
			throw new IllegalArgumentException("Domain part must not be 'null'.");
		}
		if (newLocalPart.isEmpty()) {
			throw new IllegalArgumentException("Domain part must not be empty.");
		}
		
		String possiblyNewValue = newLocalPart + '@' + getDomainPart();
		if (!isValid(possiblyNewValue)) {
			throw new IllegalArgumentException("Domain part could not be part of a valid email address.");
		}
		
		value = possiblyNewValue;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Email other = (Email) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}