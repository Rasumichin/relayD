package com.relayd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

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
 * @author  schmollc (Christian@relayD.de)
 * @since   23.03.2016
 * status   initial
 */
public class Relay extends ArrayList<Person> {

	private static final long serialVersionUID = -1655301147830819436L;

	private static final int MAX_MEMBER = 4;

	private UUID uuid = null;
	private Relayname relayname = null;

	private Integer year;

	private Relay() {
		uuid = UUID.randomUUID();
		year = new GregorianCalendar().get(Calendar.YEAR);
	}

	public Relay(Integer aYear) {
		uuid = UUID.randomUUID();
		year = aYear;
	}

	public static Relay newInstance() {
		return new Relay();
	}

	public static Relay newInstance(Integer aYear) {
		return new Relay(aYear);
	}

	public void setRelayname(Relayname aRelayname) {
		relayname = aRelayname;
	}

	public Relayname getRelayname() {
		return relayname;
	}

	public void addPerson(Person person) {
		if (isFull()) {
			throw new IndexOutOfBoundsException("No more than " + MAX_MEMBER + " participants possible!");
		}
		add(person);
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

	public UUID getUUID() {
		return uuid;
	}

	public Integer getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "Relay: " + getRelayname();
	}
}