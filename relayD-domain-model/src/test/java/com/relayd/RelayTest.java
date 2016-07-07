package com.relayd;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.attributes.Forename;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 23.03.2016
 * status initial
 */
public class RelayTest {

	private Relay sut = Relay.newInstance();

	@Test
	public void testCreateInitialRelay() {
		final String RELAYNAME = "Die vier ????";

		sut.setRelayname(Relayname.newInstance(RELAYNAME));

		assertEquals("Relay: " + RELAYNAME, sut.toString());

		assertNotNull("Expected valid UUID.", sut.getUUID());
	}

	@Test
	public void testChangeRelayname() {
		final Relayname FIRST_CHOICE = Relayname.newInstance("Die vier ????");
		final Relayname SECOND_CHOICE = Relayname.newInstance("The Walking Dead");

		Relayname relayname = sut.getRelayname();

		assertNull("Expected non valid instance.", relayname);

		sut.setRelayname(FIRST_CHOICE);
		relayname = sut.getRelayname();

		assertNotNull(relayname);
		assertEquals("Surename not corret.", FIRST_CHOICE, relayname);

		sut.setRelayname(SECOND_CHOICE);
		relayname = sut.getRelayname();

		assertNotNull(relayname);
		assertEquals("Changed Surename not correct.", SECOND_CHOICE, relayname);
	}

	/**
	 * Nicht als Unit-Test konzipiert sondern als Story.
	 * TODO -schmoll- Dieser Test ist ... seltsam.. :-)
	 */
	@Test
	public void testAddPersonAsStory() {
		Person person = new PersonBuilder().build();

		boolean isFull = sut.isFull();

		assertFalse("Expected non complete relay.", isFull);

		sut.addPerson(person);

		isFull = sut.isFull();

		assertFalse("Expected non complete relay.", isFull);

		sut.addPerson(person);

		isFull = sut.isFull();

		assertFalse("Expected non complete relay.", isFull);

		sut.addPerson(person);

		isFull = sut.isFull();

		assertFalse("Expected non complete relay.", isFull);

		sut.addPerson(person);

		isFull = sut.isFull();

		assertTrue("Expected complete relay.", isFull);
	}

	@Test
	public void testNoPersonInList() {
		boolean isFull = sut.isFull();

		assertFalse("Expected non complete relay.", isFull);
	}

	@Test
	public void testAddOnePerson() {
		addPersonsToSUT(personCount(1));

		boolean isFull = sut.isFull();

		assertFalse("Expected non complete relay.", isFull);
	}

	@Test
	public void testAddFourPersons() {
		addPersonsToSUT(personCount(4));

		boolean isFull = sut.isFull();

		assertTrue("Expected complete relay.", isFull);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddFivePersons() {
		addPersonsToSUT(personCount(5));
	}

	@Test
	public void testGetPersonForSurename() {
		//@formatter:off
		PersonBuilder builder = new PersonBuilder();
		Person justusJonas = builder
				.withForename(Forename.newInstance("Justus"))
				.withSurename(Surename.newInstance("Jonas"))
				.build();
		Person bobAndrews = builder
				.withForename(Forename.newInstance("Bob"))
				.withSurename(Surename.newInstance("Andrews"))
				.build();
		//@formatter:on
		sut.addPerson(justusJonas);
		sut.addPerson(bobAndrews);

		Person result = sut.getPerson(Surename.newInstance("Jonas"));

		assertEquals(justusJonas, result);
	}

	private void addPersonsToSUT(int count) {
		Person person = new PersonBuilder().build();
		for (int i = 0; i < count; i++) {
			sut.addPerson(person);
		}
	}

	private int personCount(int value) {
		return value;
	}
}