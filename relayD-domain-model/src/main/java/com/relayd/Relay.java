package com.relayd;

import java.util.ArrayList;
import java.util.List;

import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   23.03.2016
 * status   initial
 */
public class Relay {

	private static final int MAX_MEMBER = 4;
	private Relayname relayname = null;
	private List<Person> persons = new ArrayList<Person>();

	public static Relay newInstance() {
		return new Relay();
	}

	public void setRelayname(Relayname aRelayname) {
		relayname = aRelayname;
	}

	public Relayname getRelayname() {
		return relayname;
	}

	public void addPerson(Person aPerson) {
		if (isFull()) {
			throw new IndexOutOfBoundsException("Nicht mehr als " + MAX_MEMBER + " Teilnehmer moeglich.");
		}
		persons.add(aPerson);
	}

	public boolean isFull() {
		return persons.size() == MAX_MEMBER;
	}

	public Person getPerson(Surename surename) {
		for (Person person : persons) {
			if (person.getSurename().equals(surename)) {
				return person;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Staffel: " + getRelayname();
	}
}