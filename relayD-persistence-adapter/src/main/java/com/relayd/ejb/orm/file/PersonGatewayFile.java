package com.relayd.ejb.orm.file;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   26.03.2016
 *
 */
public class PersonGatewayFile implements PersonGateway {

	private PersonToPersonMapper personToPersonMapper = PersonToPersonMapper.newInstance();

	public PersonGatewayFile() {
	}

	PersonGatewayFile(String aFileName) {
		FileSingleton.getInstance().setFileName(aFileName);
	}

	@Override
	public Person get(UUID uuid) {
		for (Person person : getAll()) {
			if (uuid.equals(person.getUuid())) {
				return person;
			}
		}
		return null;
	}

	@Override
	public void set(Person updatePerson) {
		List<Person> somePersons = getAll();

		if (somePersons.contains(updatePerson)) {
			for (Person person : somePersons) {
				if (updatePerson.equals(person)) {
					getPersonToPersonMapper().mapPersonToPerson(updatePerson, person);
					break;
				}
			}
		} else {
			somePersons.add(updatePerson);
		}
		FileSingleton.getInstance().setPersons(somePersons);
	}

	@Override
	public void remove(UUID uuid) {
		Person dummyRelayEvent = Person.newInstance();
		dummyRelayEvent.setUuid(uuid);
		List<Person> all = getAll();
		all.remove(dummyRelayEvent);
		FileSingleton.getInstance().setPersons(all);
	}

	@Override
	public List<Person> getAll() {
		return FileSingleton.getInstance().getPersons();

	}

	private PersonToPersonMapper getPersonToPersonMapper() {
		return personToPersonMapper;
	}
}