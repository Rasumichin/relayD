package com.relayd;

import org.junit.Test;

import com.relayd.attributes.Forename;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;

import static org.junit.Assert.*;

/**
 * @author schmollc (Christian@cloud.franke-net.com)
 * @since 23.03.2016 status initial
 */
public class RelayTest {

	private Relay sut = Relay.newInstance();

	@Test
	public void testCreateInitialRelay() {
		final String RELAYNAME = "Die vier ????";

		sut.setRelayname(Relayname.newInstance(RELAYNAME));

		assertEquals("Staffel: " + RELAYNAME, sut.toString());
	}

	@Test
	public void testChangeRelayname() {
		final Relayname FIRST_CHOICE = Relayname.newInstance("Die vier ????");
		final Relayname SECOND_CHOICE = Relayname.newInstance("The Walking Dead");

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
		Person person = createPersonJustusJonas();

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
		boolean isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);
	}

	@Test
	public void testAddOnePerson() {
		addPersonsToSUT(personCount(1));

		boolean isFull = sut.isFull();

		assertFalse("Erwarte das Staffel nicht voll ist.", isFull);
	}

	@Test
	public void testAddFourPerson() {
		addPersonsToSUT(personCount(4));

		boolean isFull = sut.isFull();

		assertTrue("Erwarte das Staffel voll ist.", isFull);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddFivePerson() {
		addPersonsToSUT(personCount(5));
	}

	@Test
	public void testGetPersonForSurename() {
		Person personJustusJonas = createPersonJustusJonas();
		sut.addPerson(personJustusJonas);
		Person personBobAndrews = createPersonBobAndrews();
		sut.addPerson(personBobAndrews);

		Person result = sut.getPerson(Surename.newInstance("Jonas"));

		assertEquals(personJustusJonas, result);
	}

	private void addPersonsToSUT(int count) {
		Person person = createPersonJustusJonas();
		for (int i = 0; i < count; i++) {
			sut.addPerson(person);
		}
	}

	private int personCount(int value) {
		return value;
	}

	private Person createPersonJustusJonas() {
		//@formatter:off
		return new PersonBuilder()
								.withSurename(Surename.newInstance("Jonas"))
								.withForename(Forename.newInstance("Justus"))
								.build();
		//@formatter:on
	}

	private Person createPersonBobAndrews() {
		//@formatter:off
		return new PersonBuilder()
								.withSurename(Surename.newInstance("Andrews"))
								.withForename(Forename.newInstance("Bob"))
								.build();
		//@formatter:on
	}
}