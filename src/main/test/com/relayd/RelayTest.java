package com.relayd;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Test;

import com.relayd.attributes.Birthday;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;

import static org.junit.Assert.*;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   23.03.2016
 * status   initial
 */
public class RelayTest {

	@Test
	public void testCreateInitialRelay() {
		final String RELAYNAME = "Die vier ????";
		Relay sut = Relay.newInstance();

		sut.setRelayname(Relayname.newInstance(RELAYNAME));

		assertEquals("Staffel: " + RELAYNAME, sut.toString());
	}

	@Test
	public void testChangeRealyname() {
		final Relayname FIRST_CHOICE = Relayname.newInstance("Die vier ????");
		final Relayname SECOND_CHOICE = Relayname.newInstance("The Walking Dead");
		Relay sut = Relay.newInstance();

		Relayname relayname = sut.getRelayname();

		assertNull("Erwarte keine gültige Instanz.", relayname);

		sut.setRelayname(FIRST_CHOICE);
		relayname = sut.getRelayname();

		assertNotNull(relayname);
		assertEquals("Erwarte den gesetzten Surenamen.", FIRST_CHOICE, relayname);

		sut.setRelayname(SECOND_CHOICE);
		relayname = sut.getRelayname();

		assertNotNull(relayname);
		assertEquals("Erwarte den neu gesetzten Surenamen.", SECOND_CHOICE, relayname);
	}

	/**
	 * Nicht als Unit-Test konzipiert sondern als Story.
	 */
	@Test
	public void testAddPerson() {
		Person person = createPerson();
		Relay sut = Relay.newInstance();

		boolean isEmpty = sut.isEmpty();
		boolean isFull = sut.isFull();

		assertTrue("Erwarte das Staffel leer ist.", isEmpty);
		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);

		sut.addPerson(person);

		isEmpty = sut.isEmpty();
		isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht leer ist.", isEmpty);
		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);

		sut.addPerson(person);

		isEmpty = sut.isEmpty();
		isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht leer ist.", isEmpty);
		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);

		sut.addPerson(person);

		isEmpty = sut.isEmpty();
		isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht leer ist.", isEmpty);
		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);

		sut.addPerson(person);

		isEmpty = sut.isFull();

		assertTrue("Erwarte eine volle Staffel.", sut.isFull());
	}

	@Test
	public void testNoPersonInList() {
		Relay sut = Relay.newInstance();

		boolean isEmpty = sut.isEmpty();
		boolean isFull = sut.isFull();

		assertTrue("Erwarte das Staffel leer ist.", isEmpty);
		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);
	}

	@Test
	public void testAddOnePerson() {
		Person person = createPerson();
		Relay sut = Relay.newInstance();

		sut.addPerson(person);

		boolean isEmpty = sut.isEmpty();
		boolean isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht leer ist.", isEmpty);
		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);
	}

	@Test
	public void testAddTwoPerson() {
		Person person = createPerson();
		Relay sut = Relay.newInstance();

		sut.addPerson(person);
		sut.addPerson(person);

		boolean isEmpty = sut.isEmpty();
		boolean isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht leer ist.", isEmpty);
		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);
	}

	@Test
	public void testAddThreePerson() {
		Person person = createPerson();
		Relay sut = Relay.newInstance();

		sut.addPerson(person);
		sut.addPerson(person);
		sut.addPerson(person);

		boolean isEmpty = sut.isEmpty();
		boolean isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht leer ist.", isEmpty);
		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);
	}

	@Test
	public void testAddFourPerson() {
		Person person = createPerson();
		Relay sut = Relay.newInstance();

		sut.addPerson(person);
		sut.addPerson(person);
		sut.addPerson(person);
		sut.addPerson(person);

		boolean isEmpty = sut.isEmpty();
		boolean isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht leer ist.", isEmpty);
		assertTrue("Erwarte das Staffel voll ist.", isFull);
	}

	private Person createPerson() {
		Person person = Person.newInstance();
		person.setSurename(Surename.newInstance("Jonas"));
		person.setForename(Forename.newInstance("Justus"));
		person.setBirthday(Birthday.newInstance(new GregorianCalendar(1956, Calendar.FEBRUARY, 6).getTime()));
		person.setShirtsize(Shirtsize.HerrenM);
		person.setNationality(Locale.GERMANY);
		return person;
	}
}