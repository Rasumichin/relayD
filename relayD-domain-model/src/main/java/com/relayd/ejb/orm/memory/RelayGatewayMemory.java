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
 * @since   03.07.2016
 * status   initial
 */
public class RelayGatewayMemory implements RelayGateway {

	static Map<UUID, Relay> relays = new HashMap<UUID, Relay>();

	@Override
	public void set(Relay relay) {
		relays.put(relay.getUUID(), relay);
	}

	@Override
	public List<Relay> getAll() {
		List<Relay> relaysAsList = new ArrayList<Relay>(relays.values());
		return relaysAsList;
	}

	@Override
	public Relay get(UUID uuid) {
		return relays.get(uuid);
	}
}