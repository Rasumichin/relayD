package com.relayd.ejb;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.relayd.Member;
import com.relayd.MemberBuilder;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
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

	protected RelayEvent metroMarathon = RelayEvent.newInstance(Eventname.newInstance("Metro Marathon"), EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30)));

	public abstract RelayGateway getSut();

	@Test
	public void testGetForNonExistingEntry() {
		getSut().set(createRelayStaubwolke());
		getSut().set(createHotRunners());

		Relay result = getSut().get(UUID.randomUUID());

		assertNull("[result] must be null!", result);
	}

	@Test
	public void testSet_ForRelayWithoutMembers() {
		List<Relay> someRelays = getSut().getAll();
		assertNotNull("Expect valid instance!", someRelays);

		boolean actual = someRelays.isEmpty();

		assertTrue("The List of relays must be empty!", actual);

		Relay relay = Relay.newInstance(metroMarathon);

		getSut().set(relay);

		someRelays = getSut().getAll();
		assertNotNull("Expect valid instance!", someRelays);

		actual = someRelays.isEmpty();

		assertFalse("The List of relays should contain data!", actual);
	}

	@Test
	public void testSet_ForRelayWithMembers() {
		// ARRANGE
		List<Relay> someRelays = getSut().getAll();
		assertNotNull("Expect valid instance!", someRelays);

		boolean isEmpty = someRelays.isEmpty();

		assertTrue("The List of relays must be empty!", isEmpty);

		Relay relay = Relay.newInstance(metroMarathon);
		Relayname relayname = Relayname.newInstance("Die 4 ????");
		relay.setRelayname(relayname);

		Member justusJonas = new MemberBuilder().withForename("Justus").withSurename("Jonas").build();
		relay.addMember(justusJonas, Position.FIRST);

		Member peterShaw = new MemberBuilder().withForename("Peter").withSurename("Shaw").build();
		relay.addMember(peterShaw, Position.SECOND);

		// ACT
		getSut().set(relay);

		// ASSERT
		someRelays = getSut().getAll();
		assertNotNull("Expect valid instance!", someRelays);

		isEmpty = someRelays.isEmpty();

		assertFalse("The List of relays should contain data!", isEmpty);

		Relay relayInGateway = someRelays.get(0);
		assertEquals("[relayname] is not correct!", relayname, relayInGateway.getRelayname());

		assertEquals("[memberOne] is not correct!", justusJonas, relayInGateway.getMemberFor(Position.FIRST));
		assertEquals("[memberTwo] is not correct!", peterShaw, relayInGateway.getMemberFor(Position.SECOND));
		assertTrue("[memberThree] is not correct!", relayInGateway.getMemberFor(Position.THIRD).isEmpty());
		assertTrue("[memberFour] is not correct!", relayInGateway.getMemberFor(Position.FOURTH).isEmpty());
	}

	@Test
	public void testSet_ForExistingRelay() {
		// ARRANGE
		Relay firstRelay = Relay.newInstance(metroMarathon);
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
		Relay relay = Relay.newInstance(metroMarathon);
		relay.setRelayname(Relayname.newInstance("Staubwolke"));
		return relay;
	}

	private Relay createHotRunners() {
		Relay relay = Relay.newInstance(metroMarathon);
		relay.setRelayname(Relayname.newInstance("HotRunners"));
		return relay;
	}
}