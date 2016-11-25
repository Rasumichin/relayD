package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Position;
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
	public void testCreateInstance_ForParameter() {
		UUID uuid = UUID.randomUUID();
		Participant expected = Participant.newInstance();
		expected.setUuidPerson(uuid);

		TreeNodeRow sut = TreeNodeRow.newInstance(expected, Position.FIRST);

		assertNotNull("Expected valid instance!", sut);

		Participant actual = sut.getParticipant();

		assertEquals("Participant not corret!", expected, actual);
		assertEquals("Position not correct!", Position.FIRST, sut.getPosition());
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
		Position dummyPosition = Position.UNKNOWN;
		UUID uuid = UUID.randomUUID();
		Participant personRelay = Participant.newInstance();
		personRelay.setUuidPerson(uuid);
		TreeNodeRow sut = TreeNodeRow.newInstance(personRelay, dummyPosition);

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
		Position dummyPosition = Position.UNKNOWN;
		UUID uuid = UUID.randomUUID();
		Participant personRelay = Participant.newInstance();
		personRelay.setUuidPerson(uuid);
		TreeNodeRow sut = TreeNodeRow.newInstance(personRelay, dummyPosition);

		Relayname actual = sut.getRelayname();
		// TODO mit Erik drüber sprechen. Ohne toString interessantes Phänomen
		assertEquals("relayName not correct!", "", actual.toString());
	}

}