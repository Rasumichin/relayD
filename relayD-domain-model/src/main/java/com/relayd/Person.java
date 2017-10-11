package com.relayd;

import java.io.Serializable;
import java.util.UUID;

import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   22.03.2016
 *
 */
public class Person implements Serializable {
	private static final long serialVersionUID = -7937105233874151745L;

	UUID uuid;
	private Surename surename = Surename.newInstance();
	private Forename forename = Forename.newInstance();
	private YearOfBirth yearOfBirth = YearOfBirth.newInstance();
	private Shirtsize shirtsize = Shirtsize.UNKNOWN;
	private Email email;
	private Comment comment = Comment.newInstance();
	Email lastCalculatedEmail;

	private Person() {
		// TODO - REL-293 - Discuss. When do we do initialization here and when directly on field declaration level (see above)?
		uuid = UUID.randomUUID();
		email = getDefaultEmail();
		lastCalculatedEmail = email.clone();
	}

	public static Person newInstance() {
		return new Person();
	}

	Email getDefaultEmail() {
		return Email.newInstance("forename.surename@canda.com");
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID anUuid) {
		uuid = anUuid;
	}

	public Surename getSurename() {
		return surename;
	}

	public void setSurename(Surename aSurename) {
		if (aSurename == null) {
			surename = Surename.newInstance();
		} else {
			surename = aSurename;
		}
	}

	public Forename getForename() {
		return forename;
	}

	public void setForename(Forename aForename) {
		if (aForename == null) {
			forename = Forename.newInstance();
		} else {
			forename = aForename;
		}
	}

	public Shirtsize getShirtsize() {
		return shirtsize;
	}

	public void setShirtsize(Shirtsize aShirtsize) {
		shirtsize = aShirtsize;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email anEmail) {
		if (anEmail == null) {
			email = Email.newInstance();
		} else {
			email = anEmail;
		}
	}

	/**
	 * Infers the 'email' attribute of the receiver by considering the current 'forename'
	 * and 'surename' values as well as the provided 'domain part' of the email.
	 *
	 * @param domainPart The desired domain part of the new email address. Must not be 'null'.
	 *
	 * @return The inferred new email address (might be 'null' in case 'forename' AND 'surename'
	 * 			are both currently empty.
	 *
	 */
	public Email inferEmailFromNameAnd(String domainPart) {
		if (domainPart == null) {
			throw new IllegalArgumentException("Provided domain part must not be 'null'.");
		}

		if (getForename().isEmpty() && getSurename().isEmpty()) {
			setEmail(null);
			return getEmail();
		}

		String localPart = getForename().toString();
		if (!getSurename().isEmpty()) {
			localPart += localPart.isEmpty() ? "" : ".";
			localPart += getSurename();
		}

		setEmail(Email.createFromLocalAndDomainPart(localPart, domainPart));

		return getEmail();
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment aComment) {
		if (aComment == null) {
			comment = Comment.newInstance();
		} else {
			comment = aComment;
		}
	}

	public void setYearOfBirth(YearOfBirth aYearOfBirth) {
		if (aYearOfBirth == null) {
			yearOfBirth = YearOfBirth.newInstance();
		} else {
			yearOfBirth = aYearOfBirth;
		}
	}

	public YearOfBirth getYearOfBirth() {
		return yearOfBirth;
	}

	public boolean hasEmail() {
		return !(getEmail().isEmpty());
	}

	public void nameValueChanged() {
		// TODO - REL-292 - Mit CS abstimmen, ob die Methode ihm so eher verständlich erscheint.
		if (currentEmailHasBeenCalculated()) {
			recalculateEmail();
		}
	}

	boolean currentEmailHasBeenCalculated() {
		Email currentEmail = getEmail();

		return currentEmail.equals(lastCalculatedEmail);
	}

	void recalculateEmail() {
		Email currentEmail = getEmail();
		String currentLocalPart = getCurrentLocalPart();
		currentEmail.setLocalPart(currentLocalPart);

		lastCalculatedEmail = Email.newInstance(currentEmail.toString());
	}

	String getCurrentLocalPart() {
		Forename currentForename = getForename();
		Surename currentSurename = getSurename();

		String currentLocalPart = currentForename.toString();
		if (!currentSurename.isEmpty()) {
			currentLocalPart = (currentLocalPart.isEmpty()) ? currentSurename.toString() : currentLocalPart + '.' + currentSurename;
		}

		// Replace all whitespace with empty strings
		currentLocalPart = currentLocalPart.replaceAll("\\s+", "");

		return currentLocalPart;
	}

	public boolean isExternal() {
		return email.isExternal();
	}

	@Override
	public String toString() {
		return getForename() + " "
				+ getSurename() + ", "
				+ getYearOfBirth() + ", "
				+ getShirtsize() + ", "
				+ getEmail();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		Person other = (Person) obj;
		if (uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}
}