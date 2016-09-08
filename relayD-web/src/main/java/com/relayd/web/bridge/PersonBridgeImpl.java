package com.relayd.web.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.attributes.Email;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayFactory;

/**
 * Only a simple Wrapper for the Gateway.
 *
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 * status initial
 */
public class PersonBridgeImpl implements PersonBridge {

	private PersonGateway gateway = null;

	public PersonBridgeImpl() {
		gateway = PersonGatewayFactory.get(GatewayType.FILE);
	}

	@Override
	public List<Person> all() {
		return gateway.getAll();
	}

	@Override
	public void update(Person aPerson) {
		gateway.set(aPerson);
	}

	@Override
	public void create(Person person) {
		gateway.set(person);
	}

	@Override
	public Person get(UUID uuid) {
		return gateway.get(uuid);
	}

	@Override
	public void remove(Person person) {
		gateway.remove(person.getUUID());
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
	public String getEmailList() {
		return getEmailList(all());
	}

	@Override
	public String getEmailList(List<Person> somePersons) {
		StringBuilder builder = new StringBuilder();
		for (Person person : somePersons) {
			if (person.getEmail() != null) {
				builder.append(", " + person.getEmail());
			}
		}
		String output = builder.toString();
		output = output.replaceFirst(", ", "");

		return output;
	}

	@Override
	public List<Person> allWithoutRelay() {
		List<Person> result = new ArrayList<Person>();
		for (Person person : all()) {
			if (person.getRelayname() == null || person.getRelayname().toString().isEmpty()) {
				result.add(person);
			}
		}
		return result;
	}
}