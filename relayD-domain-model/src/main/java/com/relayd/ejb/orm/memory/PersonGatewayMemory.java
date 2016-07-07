package com.relayd.ejb.orm.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@relayD.de)
 * @since   23.06.2016
 * status   initial
 */
public class PersonGatewayMemory implements PersonGateway {

	static Map<UUID, Person> events = new HashMap<UUID, Person>();

	@Override
	public Person get(UUID uuid) {
		return events.get(uuid);
	}

	@Override
	public void set(Person person) {
		events.put(person.getUUID(), person);
	}

	@Override
	public void remove(UUID uuid) {
		events.remove(uuid);
	}

	@Override
	public List<Person> getAll() {
		ArrayList<Person> personsAsList = new ArrayList<Person>(events.values());
		return personsAsList;
	}
}