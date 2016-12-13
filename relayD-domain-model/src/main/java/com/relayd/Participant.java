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
	private Forename forename = Forename.newInstance();
	private Surename surename = Surename.newInstance();

	private Participant() {
	}

	private Participant(Forename aForename, Surename aSurename, UUID anUuid) {
		forename = aForename;
		surename = aSurename;
		uuidPerson = anUuid;
	}

	public boolean isEmpty() {
		return false;
	}

	public static Participant newInstance() {
		return ParticipantNullObject.instance();
	}

	/**
	 * @deprecated Please use newInstance(Person person)
	 */
	@Deprecated
	public static Participant newInstance(Forename forename, Surename surename, UUID uuid) {
		return new Participant(forename, surename, uuid);
	}

	public static Participant newInstance(Person person) {
		if (person == null) {
			return ParticipantNullObject.instance();
		}
		return new Participant(person.getForename(), person.getSurename(), person.getUuid());
	}

	public UUID getUuidPerson() {
		return uuidPerson;
	}

	public Forename getForename() {
		return forename;
	}

	public Surename getSurename() {
		return surename;
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

	static final class ParticipantNullObject extends Participant {
		private static final long serialVersionUID = 6577776791000840413L;

		private static final ParticipantNullObject SINGLETON = new ParticipantNullObject();

		private static ParticipantNullObject instance() {
			return SINGLETON;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public String toString() {
			return "";
		}
	}
}