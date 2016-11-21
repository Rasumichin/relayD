package com.relayd.ejb.orm.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   14.10.2016
 *
 */
public class RelayGatewayMemory implements RelayGateway {

	static Map<UUID, Relay> relays = new HashMap<UUID, Relay>();

	@Override
	public void set(Relay relay) {
		relays.put(relay.getUuid(), relay);
	}

	@Override
	public List<Relay> getAll() {
		ArrayList<Relay> relaysAsList = new ArrayList<Relay>(relays.values());
		return relaysAsList;
	}
}