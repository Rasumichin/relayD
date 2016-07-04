package com.relayd.ejb;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.relayd.Relay;
import com.relayd.attributes.Relayname;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   03.07.2016
 * status   initial
 */
public abstract class RelayGatewayTest {

	public abstract RelayGateway getSut();

	@Test
	public void testGetAll() {
		getSut().set(createRelay("Staubwolke"));
		getSut().set(createRelay("FirstTimeRunners"));

		List<Relay> result = getSut().getAll();
		assertEquals(2, result.size());
	}

	private Relay createRelay(String relayName) {
		Relay relay = Relay.newInstance();
		relay.setRelayname(Relayname.newInstance(relayName));
		return relay;
	}

	@Test
	public void testGetForUUID() {
		Relay firstTimeRunners = createRelay("FirstTimeRunners");
		UUID uuid = firstTimeRunners.getUUID();

		getSut().set(createRelay("Staubwolke"));
		getSut().set(firstTimeRunners);

		Relay result = getSut().get(uuid);

		assertEquals(firstTimeRunners, result);
	}
}