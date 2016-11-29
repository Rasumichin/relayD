package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.attributes.Position;

/**
 * The only way to learn a new programming language is by writing tests in it.
 *  - Dennis Ritchie
 *
 * @author schmollc (Christian@relayd.de)
 * @since 26.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeNodeRowParticipantTest {

	@Test
	public void testIsSerializable() {
		Position dummyPosition = Position.UNKNOWN;
		Participant dummyParticipant = Participant.newInstance();

		TreeNodeRow sut = TreeNodeRow.newInstance(dummyParticipant, dummyPosition);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance_ForParticipant() {
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
	public void testIsRelay() {
		Position dummyPosition = Position.UNKNOWN;
		Participant personRelay = Participant.newInstance();
		TreeNodeRow sut = TreeNodeRow.newInstance(personRelay, dummyPosition);

		boolean actual = sut.isRelay();

		assertFalse("row is not a participant!", actual);
	}

	@Test
	public void testGetRelayname_ForParticipant() {
		Position dummyPosition = Position.UNKNOWN;
		Participant dummyParticipant = Participant.newInstance();
		TreeNodeRow sut = TreeNodeRow.newInstance(dummyParticipant, dummyPosition);

		String actual = sut.getRelayname();
		// TODO (Christian, Version 1.3): mit Erik drüber sprechen. Ohne toString interessantes Phänomen (wenn es ein Relayname Domain Objekt ist)
		assertEquals("relayName not correct!", "", actual.toString());
	}
}