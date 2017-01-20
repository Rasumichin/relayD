package com.relayd.web.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.Settings;
import com.relayd.attributes.Email;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayFactory;
import com.relayd.web.browse.PersonBrowse;

/**
 * Only a simple Wrapper for the Gateway.
 *
 * @author schmollc (Christian@relayD.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.06.2016
 *
 */
public class PersonBridgeImpl implements PersonBridge {

	@Override
	public List<Person> all() {
		return getGateway().getAll();
	}

	@Override
	public void persistPerson(Person aPerson) {
		getGateway().set(aPerson);
	}

	@Override
	public Person get(UUID uuid) {
		return getGateway().get(uuid);
	}

	@Override
	public void remove(Person person) {
		getGateway().remove(person.getUuid());
	}

	@Override
	public ValidationResult validateEMail(Person personToCheck) {
		for (Person person : all()) {
			if (!personToCheck.equals(person)) {
				if (emailsEqual(personToCheck, person)) {
					return new ValidationResultImpl("EMail does exist!");
				}
			}
		}
		return new ValidationResultImpl("");
	}

	private boolean emailsEqual(Person newPerson, Person person) {
		Email email = person.getEmail();
		return email != null && email.equals(newPerson.getEmail());
	}

	@Override
	public String getEmailList(List<Person> somePersons) {
		StringBuilder builder = new StringBuilder();
		for (Person person : somePersons) {
			if (person.hasEmail()) {
				builder.append(", " + person.getEmail());
			}
		}
		String output = builder.toString();
		output = output.replaceFirst(", ", "");

		return output;
	}

	@Override
	public GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

	@Override
	public List<PersonBrowse> allPersonBrowse() {
		List<PersonBrowse> result = new ArrayList<PersonBrowse>();
		for (Person person : all()) {
			PersonBrowse personBrowse = getPersonBrowseFor(person);
			result.add(personBrowse);
		}
		return result;
	}

	PersonBrowse getPersonBrowseFor(Person person) {
		//@formatter:off
		PersonBrowse personBrowse = new PersonBrowse.Builder()
													.withUuidPerson(person.getUuid())
													.withForename(person.getForename())
													.withSurename(person.getSurename())
													.withYearOfBirth(person.getYearOfBirth())
													.withShirtsize(person.getShirtsize())
													.withEmail(person.getEmail())
													.withComment(person.getComment())
													.build();
		//@formatter:on
		return personBrowse;
	}

	public PersonGateway getGateway() {
		return PersonGatewayFactory.get(getGatewayType());
	}
}