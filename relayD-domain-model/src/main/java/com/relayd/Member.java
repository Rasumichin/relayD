package com.relayd;

import java.io.Serializable;
import java.time.Duration;
import java.util.UUID;

import org.apache.commons.lang3.time.DurationFormatUtils;

import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 02.02.2017
 *
 */
public class Member implements Serializable {
	private static final long serialVersionUID = -1657222726828950264L;

	private UUID uuidPerson;
	private Forename forename = Forename.newInstance();
	private Surename surename = Surename.newInstance();
	private Email email = Email.newInstance();

	private Duration duration;

	private Member() {
	}

	private Member(Forename aForename, Surename aSurename, Email anEmail, UUID anUuid) {
		forename = aForename;
		surename = aSurename;
		email = anEmail;
		uuidPerson = anUuid;
	}

	public static Member newInstance() {
		return MemberNullObject.instance();
	}

	public static Member newInstance(Person person) {
		if (person == null) {
			return MemberNullObject.instance();
		}
		return new Member(person.getForename(), person.getSurename(), person.getEmail(), person.getUuid());
	}

	public boolean isEmpty() {
		return false;
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

	public Email getEmail() {
		return email;
	}

	public void setDuration(Duration aDuration) {
		duration = aDuration;
	}

	public Duration getDuration() {
		return duration;
	}

	public String getDurationFormatted() {
		if (duration == null) {
			return "00:00:00";
		}
		return DurationFormatUtils.formatDuration(duration.toMillis(), "HH:mm:ss");
	}

	public boolean hasThatPersonIdentity(UUID uuid) {
		return getUuidPerson().equals(uuid);
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
		Member other = (Member) obj;
		if (uuidPerson == null) {
			if (other.uuidPerson != null) {
				return false;
			}
		} else if (!uuidPerson.equals(other.uuidPerson)) {
			return false;
		}
		return true;
	}

	static final class MemberNullObject extends Member {
		private static final long serialVersionUID = 6577776791000840413L;

		private static final MemberNullObject SINGLETON = new MemberNullObject();

		private static MemberNullObject instance() {
			return SINGLETON;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public boolean hasThatPersonIdentity(@SuppressWarnings("unused") UUID uuid) {
			return false;
		}

		@Override
		public String toString() {
			return "";
		}
	}
}