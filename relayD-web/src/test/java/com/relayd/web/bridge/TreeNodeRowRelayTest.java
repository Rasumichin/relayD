package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.Duration;

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
		Relay dummyRelay = Relay.newInstance(RelayEvent.newInstance());

		TreeNodeRow sut = TreeNodeRow.newInstance(dummyRelay);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testCreateInstance_ForParameterRelay() {
		Relay expected = Relay.newInstance(RelayEvent.newInstance());
		TreeNodeRow sut = TreeNodeRow.newInstance(expected);

		assertNotNull("Expected valid instance!", sut);

		Relay actual = sut.getRelay();

		assertEquals("Relay not corret!", expected, actual);
	}

	@Test
	public void testIsRelay() {
		Relay expected = Relay.newInstance(RelayEvent.newInstance());
		TreeNodeRow sut = TreeNodeRow.newInstance(expected);

		boolean actual = sut.isRelay();

		assertTrue("row is not a relay!", actual);
	}

	@Test
	public void testGetRelayName() {
		Relay relay = Relay.newInstance(RelayEvent.newInstance());
		Relayname expected = Relayname.newInstance("Staubwolke");
		relay.setRelayname(expected);
		TreeNodeRow sut = TreeNodeRow.newInstance(relay);

		String actual = sut.getRelayname();

		assertEquals("relayName not correct!", expected + " [0/4]", actual);
	}

	@Test
	public void testGetDuration_ForValue() {
		Relay relay = Relay.newInstance(RelayEvent.newInstance());
		Duration duration = Duration.ofHours(3).plusMinutes(33).plusSeconds(12);
		relay.setDuration(duration);

		TreeNodeRow sut = TreeNodeRow.newInstance(relay);

		String actual = sut.getDuration();
		String expected = "03:33:12";

		assertEquals("[duration] not correct!", expected, actual);
	}

	@Test
	public void testGetDuration_ForNull() {
		Relay relay = Relay.newInstance(RelayEvent.newInstance());
		relay.setDuration(null);

		TreeNodeRow sut = TreeNodeRow.newInstance(relay);

		String actual = sut.getDuration();
		String expected = "00:00:00";

		assertEquals("[duration] not correct!", expected, actual);
	}
}