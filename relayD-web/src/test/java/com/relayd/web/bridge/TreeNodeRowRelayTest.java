package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Relayname;

/**
 * First think about what the ultimate would be. Then take a step away from that, and another, until you get something you can build.
 *  - Matt Reedy
 *
 * @author schmollc (Christian@relayd.de)
 * @since 26.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeNodeRowRelayTest {

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
	public void testIsRelay() {
		Relay expected = Relay.newInstance(RelayEvent.duesseldorf());
		TreeNodeRow sut = TreeNodeRow.newInstance(expected);

		boolean actual = sut.isRelay();

		assertTrue("row is not a relay!", actual);
	}

	@Test
	public void testGetRelayName() {
		Relay relay = Relay.newInstance(RelayEvent.duesseldorf());
		Relayname expected = Relayname.newInstance("Staubwolke");
		relay.setRelayname(expected);
		TreeNodeRow sut = TreeNodeRow.newInstance(relay);

		String actual = sut.getRelayname();

		assertEquals("relayName not correct!", expected + " [0/4]", actual);
	}
}