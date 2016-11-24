package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.PersonRelay;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Relayname;

/**
 * The time to write good code is at the time you are writing it.
 *  - Daniel Read
 *
 * @author schmollc (Christian@relayd.de)
 * @since 11.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeNodeRowTest {

	@Test
	public void testIsSerializable() {
		Relay dummyRelay = Relay.newInstance(RelayEvent.duesseldorf());

		TreeNodeRow sut = TreeNodeRow.newInstance(dummyRelay);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance_ForParameterRelay() {
		Relay expected = Relay.newInstance(RelayEvent.duesseldorf());
		TreeNodeRow sut = TreeNodeRow.newInstance(expected);

		assertNotNull("Expected valid instance!", sut);

		Relay actual = sut.getRelay();

		assertEquals("Relay not corret!", expected, actual);
	}

	@Test
	public void testCreateInstance_ForParameterPersonRelay() {
		UUID uuid = UUID.randomUUID();
		PersonRelay expected = PersonRelay.newInstance();
		expected.setUuid(uuid);

		TreeNodeRow sut = TreeNodeRow.newInstance(expected);

		assertNotNull("Expected valid instance!", sut);

		PersonRelay actual = sut.getParticipant();

		assertEquals("PersonRelay not corret!", expected, actual);
	}

	@Test
	public void testIsRelay_ForRelay() {
		Relay expected = Relay.newInstance(RelayEvent.duesseldorf());
		TreeNodeRow sut = TreeNodeRow.newInstance(expected);

		boolean actual = sut.isRelay();

		assertTrue("row is not a relay!", actual);
	}

	@Test
	public void testIsRelay_ForParticipant() {
		UUID uuid = UUID.randomUUID();
		PersonRelay personRelay = PersonRelay.newInstance();
		personRelay.setUuid(uuid);
		TreeNodeRow sut = TreeNodeRow.newInstance(personRelay);

		boolean actual = sut.isRelay();

		assertFalse("row is not a participant!", actual);
	}

	@Test
	public void testGetRelayName_ForRelay() {
		Relay relay = Relay.newInstance(RelayEvent.duesseldorf());
		Relayname expected = Relayname.newInstance("Staubwolke");
		relay.setRelayname(expected);
		TreeNodeRow sut = TreeNodeRow.newInstance(relay);

		Relayname actual = sut.getRelayname();

		assertEquals("relayName not correct!", expected, actual);
	}

	@Test
	public void testGetRelayname_ForParticipant() {
		UUID uuid = UUID.randomUUID();
		PersonRelay personRelay = PersonRelay.newInstance();
		personRelay.setUuid(uuid);
		TreeNodeRow sut = TreeNodeRow.newInstance(personRelay);

		Relayname actual = sut.getRelayname();
		// TODO mit Erik drüber sprechen. Ohne toString interessantes Phänomen
		assertEquals("relayName not correct!", "", actual.toString());
	}

}