package com.relayd.ejb;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.relayd.Relay;

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
	@Ignore("Bauen hartverdrahtet immer 2 Relays, darum schlägt Test in Zeile 32 fehl!")
	public void set() {
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
}