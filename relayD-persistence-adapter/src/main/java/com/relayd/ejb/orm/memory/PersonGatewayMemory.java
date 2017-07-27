package com.relayd.ejb.orm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   23.06.2016
 *
 */
public class PersonGatewayMemory implements PersonGateway {

	@Override
	public Person get(UUID uuid) {
		return MemorySingleton.getInstance().getPersons().get(uuid);
	}

	@Override
	public void set(Person person) {
		MemorySingleton.getInstance().getPersons().put(person.getUuid(), person);
	}

	@Override
	public void remove(UUID uuid) {
		MemorySingleton.getInstance().getPersons().remove(uuid);
	}

	@Override
	public List<Person> getAll() {
		ArrayList<Person> personsAsList = new ArrayList<Person>(MemorySingleton.getInstance().getPersons().values());
		return personsAsList;
	}
}