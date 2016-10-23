package com.relayd.web.bridge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.attributes.Email;
import com.relayd.attributes.Relayname;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayFactory;
import com.relayd.web.browse.PersonBrowse;

/**
 * Only a simple Wrapper for the Gateway.
 *
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 *
 */
public class PersonBridgeImpl implements PersonBridge {

	private PersonGateway gateway = null;
	private GatewayType gatewayType = GatewayType.JPA;

	public PersonBridgeImpl() {
		gateway = PersonGatewayFactory.get(getGatewayType());
	}

	@Override
	public List<Person> all() {
		return gateway.getAll();
	}

	@Override
	public void persistPerson(Person aPerson) {
		gateway.set(aPerson);
	}

	@Override
	public Person get(UUID uuid) {
		return gateway.get(uuid);
	}

	@Override
	public void remove(Person person) {
		gateway.remove(person.getUuid());
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
	public List<Person> allWithoutRelay() {
		List<Person> result = new ArrayList<Person>();
		for (Person person : all()) {
			if (!person.hasRelay()) {
				result.add(person);
			}
		}
		return result;
	}

	@Override
	public List<Person> relaysWithSpace() {
		Map<Relayname, Integer> bundleRelay = new HashMap<Relayname, Integer>();
		List<Person> resultList = new ArrayList<Person>();
		for (Person person : all()) {
			if (person.hasRelay()) {
				int relayCount = 0;
				if (bundleRelay.get(person.getRelayname()) != null) {
					relayCount = bundleRelay.get(person.getRelayname());
				}
				relayCount++;
				if (relayCount == 4) {
					bundleRelay.remove(person.getRelayname());
				} else {
					bundleRelay.put(person.getRelayname(), relayCount);
				}
			}
		}
		for (Person person : all()) {
			if (bundleRelay.containsKey(person.getRelayname())) {
				resultList.add(person);
			}
		}
		return resultList;
	}

	@Override
	public GatewayType getGatewayType() {
		return gatewayType;
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
}