package com.relayd;

import java.io.Serializable;
import java.time.Duration;
import java.util.UUID;

import org.apache.commons.lang3.time.DurationFormatUtils;

import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 02.02.2017
 *
 */
public class Member implements Serializable {
	private static final long serialVersionUID = -1657222726828950264L;

	private UUID uuid;
	private UUID uuidPerson;
	private Forename forename = Forename.newInstance();
	private Surename surename = Surename.newInstance();
	private Shirtsize shirtsize = Shirtsize.UNKNOWN;
	private YearOfBirth yearOfBirth = YearOfBirth.newInstance();
	private Email email = Email.newInstance();
	private Duration duration = Duration.ZERO;

	private Member() {
		uuid = UUID.randomUUID();
	}

	private Member(Forename aForename, Surename aSurename, Shirtsize aShirtsize, Email anEmail, UUID anUuid, YearOfBirth aYearOfBirth) {
		uuid = UUID.randomUUID();
		forename = aForename;
		surename = aSurename;
		shirtsize = aShirtsize;
		email = anEmail;
		uuidPerson = anUuid;
		yearOfBirth = aYearOfBirth;
	}

	public static Member newInstance() {
		return MemberNullObject.instance();
	}

	public static Member newInstance(Participant participant) {
		if (participant == null) {
			return MemberNullObject.instance();
		}
		return new Member(participant.getForename(), participant.getSurename(), participant.getShirtsize(), participant.getEmail(), participant.getUuidPerson(), participant.getYearOfBirth());
	}

	public boolean isEmpty() {
		return false;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID anUuid) {
		uuid = anUuid;
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

	public Shirtsize getShirtsize() {
		return shirtsize;
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

	public boolean hasThatPersonIdentity(UUID anUuid) {
		return getUuidPerson().equals(anUuid);
	}

	@Override
	public String toString() {
		return getForename() + " " + getSurename();
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
		Member other = (Member) obj;
		if (uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!uuid.equals(other.uuid)) {
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
		public boolean hasThatPersonIdentity(@SuppressWarnings("unused") UUID anUuid) {
			return false;
		}

		@Override
		public String toString() {
			return "";
		}
	}

	public boolean hasMail() {
		return !getEmail().isEmpty();
	}

	public YearOfBirth getYearOfBirth() {
		return yearOfBirth;
	}
}