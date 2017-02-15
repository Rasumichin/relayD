package com.relayd.ejb.orm.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   23.06.2016
 *
 */
public class PersonGatewayMemory implements PersonGateway {

	static Map<UUID, Person> persons = new HashMap<UUID, Person>();

	@Override
	public Person get(UUID uuid) {
		return persons.get(uuid);
	}

	@Override
	public void set(Person person) {
		persons.put(person.getUuid(), person);
	}

	@Override
	public void remove(UUID uuid) {
		persons.remove(uuid);
	}

	@Override
	public List<Person> getAll() {
		ArrayList<Person> personsAsList = new ArrayList<Person>(persons.values());
		return personsAsList;
	}
}