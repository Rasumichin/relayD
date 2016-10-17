package com.relayd.attributes;

import java.io.Serializable;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   29.03.2016
 *
 */
public class Email implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	public static Character AT_SIGN = '@';
	private InternetAddress value;

	private Email(String email) {
		super();
		setValue(email);
	}

	private void setValue(String email) {
		try {
			value = new InternetAddress(email, true);
		} catch (AddressException adrEx) {
			// This cannot happen here.
		}
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

		if (!isValidAddress(email)) {
			throw new IllegalArgumentException("[email] has not a valid format!");
		}
	}

	public static boolean isValidAddress(String email) {
		if (email == null) {
			return false;
		}

		try {
			new InternetAddress(email, true);
		} catch (AddressException adrEx) {
			return false;
		}

		return true;
	}

	public static Email createFromLocalAndDomainPart(String localPart, String domainPart) {
		if (localPart == null) {
			throw new IllegalArgumentException("[localPart] must not be 'null'.");
		}

		if (domainPart == null) {
			throw new IllegalArgumentException("[domainPart] must not be 'null'.");
		}

		String newEmailValue = localPart + AT_SIGN + domainPart;

		return newInstance(newEmailValue);
	}

	/**
	 * Answers the domain part of the email address.
	 *
	 * @return The so-called 'domain part' of a valid email address or an empty string in case the receiver itself is empty.
	 * 			Given the email consists of 'my.name@foo.com' the returned value would be 'foo.com'.
	 *
	 */
	public String getDomainPart() {
		String[] emailParts = value.getAddress().split(AT_SIGN.toString());
		String domainPart = emailParts[emailParts.length - 1];

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
		if (newLocalPart == null) {
			throw new IllegalArgumentException("Local part must not be 'null'.");
		}

		String possiblyNewValue = newLocalPart + AT_SIGN + getDomainPart();
		if (!isValidAddress(possiblyNewValue)) {
			throw new IllegalArgumentException("Local part [" + newLocalPart + "] could not be part of a valid email address.");
		}

		setValue(possiblyNewValue);
	}

	@Override
	public Email clone() {
		try {
			return (Email) super.clone();
		} catch (CloneNotSupportedException cnsEx) {
			throw new AssertionError("Cannot happen here.");
		}
	}

	@Override
	public String toString() {
		return value.getAddress();
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