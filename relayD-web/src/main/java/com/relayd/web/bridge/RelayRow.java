package com.relayd.web.bridge;

import java.util.UUID;

import com.relayd.attributes.Forename;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;

public class RelayRow {
	private UUID uuid;
	private Relayname relayname;
	private Forename forename;
	private Surename surename;

	public UUID getUUID() {
		return uuid;
	}

	public void setUUID(UUID aUUID) {
		uuid = aUUID;
	}

	public Relayname getRelayname() {
		return relayname;
	}

	public void setRelayname(Relayname aRelayname) {
		relayname = aRelayname;
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
}