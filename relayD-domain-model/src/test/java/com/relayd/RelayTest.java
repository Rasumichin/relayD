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
 * @author schmollc (Christian@cloud.franke-net.com)
 * @since 23.03.2016 status initial
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
	public void testChangeRelayname() {
		final Relayname FIRST_CHOICE = Relayname.newInstance("Die vier ????");
		final Relayname SECOND_CHOICE = Relayname.newInstance("The Walking Dead");
		Relay sut = Relay.newInstance();

		Relayname relayname = sut.getRelayname();

		assertNull("Erwarte keine gueltige Instanz.", relayname);

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

		boolean isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);

		sut.addPerson(person);

		isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);

		sut.addPerson(person);

		isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);

		sut.addPerson(person);

		isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);

		sut.addPerson(person);

		isFull = sut.isFull();

		assertTrue("Erwarte eine volle Staffel.", isFull);
	}

	@Test
	public void testNoPersonInList() {
		Relay sut = Relay.newInstance();

		boolean isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);
	}

	@Test
	public void testAddOnePerson() {
		Relay sut = Relay.newInstance();

		addPersons(sut, personCount(1));

		boolean isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);
	}

	@Test
	public void testAddFourPerson() {
		Relay sut = Relay.newInstance();

		addPersons(sut, personCount(4));

		boolean isFull = sut.isFull();

		assertTrue("Erwarte das Staffel voll ist.", isFull);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddFivePerson() {
		Relay sut = Relay.newInstance();

		addPersons(sut, personCount(5));
	}

	private void addPersons(Relay sut, int count) {
		Person person = createPerson();
		for (int i = 0; i < count; i++) {
			sut.addPerson(person);
		}
	}

	private int personCount(int value) {
		return value;
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