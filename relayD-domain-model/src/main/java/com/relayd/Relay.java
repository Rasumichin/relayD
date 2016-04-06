package com.relayd;

import java.util.ArrayList;

import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;

/**
 * Eine Staffel ist ja eigentlich nichts anderes als eine Liste von Personen
 * (oder Etappen) mit einem Namen.<p/>
 *
 * Vorteil: Es müssen nun solche Methoden wie isEmpty(), add(), remove() usw nicht
 * mehr auf funktionalität getestet werden.<br/>
 * Es reicht den Fokus auf die fachlichen Beschränkungen zu legen wie z.B. das erreichen der Max-Grenze
 * (in diesem Fall 4)
 *
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   23.03.2016
 * status   initial
 */
public class Relay extends ArrayList<Person> {

	private static final int MAX_MEMBER = 4;
	private Relayname relayname = null;

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
		add(aPerson);
	}

	public boolean isFull() {
		return size() == MAX_MEMBER;
	}

	public Person getPerson(Surename surename) {
		for (Person person : this) {
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