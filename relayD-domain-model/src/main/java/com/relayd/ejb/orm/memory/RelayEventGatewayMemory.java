package com.relayd.ejb.orm.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.relayd.RelayEvent;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 * status   initial
 */
public class RelayEventGatewayMemory implements RelayEventGateway {
	static Map<UUID, RelayEvent> events = new HashMap<UUID, RelayEvent>();

	@Override
	public void set(RelayEvent aRelayEventEntity) {
		events.put(aRelayEventEntity.getUuid(), aRelayEventEntity);
	}

	@Override
	public List<RelayEvent> getAll() {
		ArrayList<RelayEvent> eventsAsList = new ArrayList<RelayEvent>(events.values());
		return eventsAsList;
	}

	@Override
	public RelayEvent get(UUID uuid) {
		RelayEvent relayEvent = events.get(uuid);
		return relayEvent;
	}
}