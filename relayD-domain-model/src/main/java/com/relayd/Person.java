package com.relayd;

import java.io.Serializable;
import java.util.Locale;
import java.util.UUID;

import com.relayd.attributes.Birthday;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;

/**
 * @author  schmollc (Christian@relayD.de)
 * @since   22.03.2016
 * status   initial
 */
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID uuid = null;
	private Surename surename = null;
	private Forename forename = null;
	private Birthday birthday = null;
	private Shirtsize shirtsize = null;
	private Locale nationality = null; // TODO -schmollc- Anderen Attribute sind Fachobjekte, dies hier eine "API" Klasse. Warum kein Decorator einführen?
	private Email email = null;

	private Person() {
		uuid = UUID.randomUUID();
	}

	public static Person newInstance() {
		return new Person();
	}

	public UUID getUUID() {
		return uuid;
	}

	public void setUUID(UUID anUuid) {
		uuid = anUuid;
	}

	public Surename getSurename() {
		return surename;
	}

	public void setSurename(Surename aSurename) {
		surename = aSurename;
	}

	public Birthday getBirthday() {
		return birthday;
	}

	public void setBirthday(Birthday aBirthday) {
		birthday = aBirthday;
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

	public Locale getNationality() {
		return nationality;
	}

	public String getDisplayNationality() {
		if (nationality == null) {
			return null;
		}
		return nationality.getDisplayCountry();
	}

	public void setNationality(Locale aNationality) {
		nationality = aNationality;
	}

	public void setEmail(Email anEmail) {
		email = anEmail;
	}

	public Email getEmail() {
		return email;
	}

	private String getDisplayCountry() {
		if (getNationality() != null) {
			return getNationality().getDisplayCountry(Locale.ENGLISH);
		}
		return null;
	}

	@Override
	public String toString() {
		return getForename() + " " + getSurename() + ", " + getBirthday() + ", " + getShirtsize() + ", " + getDisplayCountry() + ", " + getEmail();
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