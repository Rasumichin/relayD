package com.relayd.ejb.orm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   14.10.2016
 *
 */
public class RelayGatewayMemory implements RelayGateway {

	@Override
	public void set(Relay relay) {
		MemorySingleton.getInstance().getRelays().put(relay.getUuid(), relay);
	}

	@Override
	public List<Relay> getAll() {
		ArrayList<Relay> relaysAsList = new ArrayList<Relay>(MemorySingleton.getInstance().getRelays().values());
		return relaysAsList;
	}

	@Override
	public Relay get(UUID uuid) {
		return MemorySingleton.getInstance().getRelays().get(uuid);
	}
}