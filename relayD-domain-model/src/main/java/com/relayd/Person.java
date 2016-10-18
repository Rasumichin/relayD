package com.relayd;

import java.io.Serializable;
import java.util.UUID;

import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
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
	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private Surename surename;
	private Forename forename;
	private YearOfBirth yearOfBirth;
	private Shirtsize shirtsize;
	private Email email;
	private Relayname relayname; // Refactor Dieses Attribut ist Jahresabhängig!
	private Position position; // Refactor Dieses Attribut ist Jahresabhängig!
	private Comment comment;
	Email lastCalculatedEmail;

	private Person() {
		uuid = UUID.randomUUID();
		email = getDefaultEmail();
		lastCalculatedEmail = email.clone();
	}

	public static Person newInstance() {
		return new Person();
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
		surename = aSurename;
	}

	public Forename getForename() {
		return forename;
	}

	public void setForename(Forename aForename) {
		forename = aForename;
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
		email = anEmail;
	}

	Email getDefaultEmail() {
		return Email.newInstance("forename.surename@canda.com");
	}

	/**
	 * Infers the 'email' attribute of the receiver by considering the current 'forename'
	 * and 'surename' values as well as the provided 'domain part' of the email.
	 *
	 * @param domainPart The desired domain part of the new email address. Must not be 'null'.
	 *
	 * @return The inferred new email address (might be 'null' in case 'forename' AND 'surename'
	 * 			are both currently 'null'.
	 *
	 */
	public Email inferEmailFromNameAnd(String domainPart) {
		if (domainPart == null) {
			throw new IllegalArgumentException("Provided domain part must not be 'null'.");
		}

		if (getForename() == null && getSurename() == null) {
			setEmail(null);
			return getEmail();
		}

		String localPart = (getForename() != null) ? getForename().toString() : "";
		if (getSurename() != null) {
			localPart += localPart.isEmpty() ? "" : ".";
			localPart += getSurename();
		}

		setEmail(Email.createFromLocalAndDomainPart(localPart, domainPart));

		return getEmail();
	}

	public Relayname getRelayname() {
		return relayname;
	}

	public void setRelayname(Relayname aRelayname) {
		relayname = aRelayname;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position aPosition) {
		position = aPosition;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment aComment) {
		comment = aComment;
	}

	public void setYearOfBirth(YearOfBirth aYearOfBirth) {
		yearOfBirth = aYearOfBirth;
	}

	public YearOfBirth getYearOfBirth() {
		return yearOfBirth;
	}

	public boolean hasEmail() {
		return getEmail() != null;
	}

	public boolean hasRelay() {
		return getRelayname() != null && !getRelayname().isEmpty();
	}

	public void nameValueChanged() {
		// TODO (Erik, 2016-10-10): Mit CS abstimmen, ob die Methode ihm so eher verständlich erscheint.
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

		String currentLocalPart = (currentForename == null) ? null : currentForename.toString();
		if (currentSurename != null) {
			currentLocalPart = (currentLocalPart == null) ? currentSurename.toString() : currentLocalPart + '.' + currentSurename;
		}

		if (currentLocalPart != null) {
			currentLocalPart = currentLocalPart.replaceAll("\\s+", "");
		}

		return currentLocalPart;
	}

	@Override
	public String toString() {
		return getForename() + " " + getSurename() + ", " + getYearOfBirth() + ", " + getShirtsize() + ", " + getEmail() + ", " + getRelayname() + ", " + getPosition();
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