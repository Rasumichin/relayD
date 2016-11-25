package com.relayd.ejb;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.relayd.Participant;
import com.relayd.ParticipantBuilder;
import com.relayd.Relay;
import com.relayd.attributes.Position;
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
	public void testSet_ForRelayWithoutParticipants() {
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

	@Test
	public void testSet_ForRelayWithParticipants() {
		// ARRANGE
		List<Relay> someRelays = getSut().getAll();
		assertNotNull("Expect valid instance!", someRelays);

		boolean isEmpty = someRelays.isEmpty();

		assertTrue("The List of relays must be empty!", isEmpty);

		Relay relay = Relay.newInstance();
		Relayname relayname = Relayname.newInstance("Die 4 ????");
		relay.setRelayname(relayname);

		Participant justusJonas = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		relay.addParticipant(justusJonas, Position.FIRST);

		Participant peterShaw = new ParticipantBuilder().withForename("Peter").withSurename("Shaw").build();
		relay.addParticipant(peterShaw, Position.SECOND);

		// ACT
		getSut().set(relay);

		// ASSERT
		someRelays = getSut().getAll();
		assertNotNull("Expect valid instance!", someRelays);

		isEmpty = someRelays.isEmpty();

		assertFalse("The List of relays should contain data!", isEmpty);

		Relay relayInGateway = someRelays.get(0);
		assertEquals("[relayname] is not correct!", relayname, relayInGateway.getRelayname());

		assertEquals("[participantOne] is not correct!", justusJonas, relayInGateway.getParticipantFor(Position.FIRST));
		assertEquals("[participantTwo] is not correct!", peterShaw, relayInGateway.getParticipantFor(Position.SECOND));
		assertNull("[participantThree] is not correct!", relayInGateway.getParticipantFor(Position.THIRD));
		assertNull("[participantFour] is not correct!", relayInGateway.getParticipantFor(Position.FOURTH));
	}

	@Test
	public void testSet_ForExistingRelay() {
		// ARRANGE
		Relay firstRelay = Relay.newInstance();
		firstRelay.setRelayname(Relayname.newInstance("Staffel 1"));
		UUID uuidFromFirstMember = firstRelay.getUuid();

		getSut().set(firstRelay);
		getSut().set(createRelayStaubwolke());

		Relay updateRelay = getSut().get(uuidFromFirstMember);

		Relayname newRelayname = Relayname.newInstance("Wuestenwind");
		updateRelay.setRelayname(newRelayname);

		// ACT
		getSut().set(updateRelay);

		// ASSERT
		Relay checkRelay = getSut().get(uuidFromFirstMember);
		assertNotNull(checkRelay);
		assertEquals("[Relayname] not correct.", newRelayname, checkRelay.getRelayname());
	}

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