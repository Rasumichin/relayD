package com.relayd;

import java.util.ArrayList;
import java.util.List;

import com.relayd.attributes.Relayname;

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
		// TODO -schmollc 23.03.2016- Verhindern das man mehr als die MAX_MEMBER hinzufuegen kann.
		if (isFull()) {
			throw new IndexOutOfBoundsException("Nicht mehr als " + MAX_MEMBER + " Tielnehmer moeglich.");
		}
		persons.add(aPerson);
	}

	public boolean isFull() {
		return persons.size() == MAX_MEMBER;
	}

	@Override
	public String toString() {
		return "Staffel: " + getRelayname();
	}
}