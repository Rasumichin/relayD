package com.relayd.ejb;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.relayd.Relay;
import com.relayd.attributes.Relayname;

/**
 * What makes a clean test?
 * Three things.
 * Readability, readability, and readability.
 *  - Robert C. Martin
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   14.10.2016
 *
 */
public abstract class RelayGatewayTest {

	public abstract RelayGateway getSut();

	@Test
	public void testGetForNonExistingEntry() {
		getSut().set(createRelayStaubwolke());
		getSut().set(createHotRunners());

		Relay result = getSut().get(UUID.randomUUID());

		assertNull("[result] must be null!", result);
	}

	@Test
	public void testSet() {
		List<Relay> someRelays = getSut().getAll();
		assertNotNull("Expect valid instance!", someRelays);

		boolean actual = someRelays.isEmpty();

		assertTrue("The List of relays must be empty!", actual);

		Relay relay = Relay.newInstance();

		getSut().set(relay);

		someRelays = getSut().getAll();
		assertNotNull("Expect valid instance!", someRelays);

		actual = someRelays.isEmpty();

		assertFalse("The List of relays should contain data!", actual);
	}

	//	@Test
	//	public void testSet_ForExistingRelay() {
	//		// ARRANGE
	//		Relay firstRelay = Relay.newInstance();
	//		firstRelay.setRelayname(Relayname.newInstance("Staffel 1"));
	//		UUID uuidFromFirstMember = firstRelay.getUuid();
	//
	//		getSut().set(firstRelay);
	//		getSut().set(createRelayStaubwolke());
	//
	//		Person updateMember = getSut().get(uuidFromFirstMember);
	//
	//		Forename newForename = Forename.newInstance("Bob");
	//		updateMember.setForename(newForename);
	//		Surename newSurename = Surename.newInstance("Andrews");
	//		updateMember.setSurename(newSurename);
	//		YearOfBirth newYearOfBirth = YearOfBirth.newInstance(1971);
	//		updateMember.setYearOfBirth(newYearOfBirth);
	//		Shirtsize newShirtsize = Shirtsize.DamenXS;
	//		updateMember.setShirtsize(newShirtsize);
	//		Email newEmail = Email.newInstance("Bob.Andrews@rockyBeach.com");
	//		updateMember.setEmail(newEmail);
	//		// ACT
	//		getSut().set(updateMember);
	//
	//		// ASSERT
	//		Person checkPerson = getSut().get(uuidFromFirstMember);
	//		assertNotNull(checkPerson);
	//
	//		assertEquals("[Forename] not correct.", newForename, checkPerson.getForename());
	//		assertEquals("[Surename] not correct.", newSurename, checkPerson.getSurename());
	//		assertEquals("[YearOfBirth] not correct.", newYearOfBirth, checkPerson.getYearOfBirth());
	//		assertEquals("[Shirtsize] not correct.", newShirtsize, checkPerson.getShirtsize());
	//		assertEquals("[Email] not correct.", newEmail, checkPerson.getEmail());
	//
	//	}
	//
	private Relay createRelayStaubwolke() {
		Relay relay = Relay.newInstance();
		relay.setRelayname(Relayname.newInstance("Staubwolke"));
		return relay;
	}

	private Relay createHotRunners() {
		Relay relay = Relay.newInstance();
		relay.setRelayname(Relayname.newInstance("HotRunners"));
		return relay;
	}

}