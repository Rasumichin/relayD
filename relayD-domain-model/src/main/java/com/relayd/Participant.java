package com.relayd;

import java.io.Serializable;
import java.util.UUID;

import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 20.10.2016
 *
 */
public class Participant implements Serializable {
	private static final long serialVersionUID = -2300361519146795905L;

	private UUID uuidPerson;
	private Forename forename;
	private Surename surename;

	private Participant() {
		super();
	}

	public static Participant newInstance() {
		return new Participant();
	}

	public UUID getUuidPerson() {
		return uuidPerson;
	}

	public void setUuidPerson(UUID anUuidPerson) {
		uuidPerson = anUuidPerson;
	}

	public Forename getForename() {
		return forename;
	}

	public void setForename(Forename aForename) {
		forename = aForename;
	}

	public Surename getSurename() {
		return surename;
	}

	public void setSurename(Surename aSurename) {
		surename = aSurename;
	}

	@Override
	public String toString() {
		return getForename() + " " + getSurename();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuidPerson == null) ? 0 : uuidPerson.hashCode());
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
		Participant other = (Participant) obj;
		if (uuidPerson == null) {
			if (other.uuidPerson != null) {
				return false;
			}
		} else if (!uuidPerson.equals(other.uuidPerson)) {
			return false;
		}
		return true;
	}

}