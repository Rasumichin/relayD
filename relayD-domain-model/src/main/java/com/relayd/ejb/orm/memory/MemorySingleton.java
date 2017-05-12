package com.relayd.ejb.orm.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.RelayEvent;

/**
 * https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
 */
public class MemorySingleton {
	private static final String RELAY_EVENT = "RelayEvent";
	private static final String PERSON = "Person";
	private static final String RELAY = "Relay";

	private Map<String, Map> bigData = new HashMap<String, Map>();

	private static final class InstanceHolder {
		static final MemorySingleton INSTANCE = new MemorySingleton();
	}

	private MemorySingleton() {
		Map<UUID, RelayEvent> events = new HashMap<UUID, RelayEvent>();
		bigData.put(RELAY_EVENT, events);

		Map<UUID, Person> persons = new HashMap<UUID, Person>();
		bigData.put(PERSON, persons);

		Map<UUID, Relay> relays = new HashMap<UUID, Relay>();
		bigData.put(RELAY, relays);
	}

	public static MemorySingleton getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public Map<UUID, RelayEvent> getEvents() {
		return bigData.get(RELAY_EVENT);
	}

	public void setEvents(Map<UUID, RelayEvent> aEvents) {
		bigData.put(RELAY_EVENT, aEvents);
	}

	public Map<UUID, Person> getPersons() {
		return bigData.get(PERSON);
	}

	public void setPersons(Map<UUID, Person> aPersons) {
		bigData.put(PERSON, aPersons);
	}

	public Map<UUID, Relay> getRelays() {
		return bigData.get(RELAY);
	}

	public void setRelays(Map<UUID, Relay> aRelays) {
		bigData.put(RELAY, aRelays);
	}
}