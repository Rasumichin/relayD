package com.relayd.ejb.orm.file;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.RelayEvent;

public class BigData implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<RelayEvent> relayEvents = new ArrayList<>();
	private List<Person> persons = new ArrayList<>();
	private List<Relay> relays = new ArrayList<>();

	private BigData() {
	}

	public static BigData newInstance() {
		return new BigData();
	}

	public List<RelayEvent> getRelayEvents() {
		return relayEvents;
	}

	public void setRelayEvents(List<RelayEvent> aRelayEvents) {
		relayEvents = aRelayEvents;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> aPersons) {
		persons = aPersons;
	}

	public List<Relay> getRelays() {
		if (relays == null) {
			relays = new ArrayList<>();
		}
		return relays;
	}

	public void setRelays(List<Relay> aRelays) {
		relays = aRelays;
	}
}