package com.relayd;

import java.io.Serializable;
import java.util.UUID;

import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   11.06.2016
 *
 */
public class PersonRelay implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private Surename surename;
	private Forename forename;

	public static PersonRelay newInstance() {
		return new PersonRelay();
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

	@Override
	public String toString() {
		return getForename() + " " + getSurename();
	}
}