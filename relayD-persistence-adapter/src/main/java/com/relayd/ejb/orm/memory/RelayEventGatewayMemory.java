package com.relayd.ejb.orm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.relayd.RelayEvent;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 *
 */
public class RelayEventGatewayMemory implements RelayEventGateway {

	@Override
	public List<RelayEvent> getAll() {
		List<RelayEvent> eventsAsList = new ArrayList<>();

		eventsAsList.addAll(MemorySingleton.getInstance().getEvents().values());

		return eventsAsList;
	}

	@Override
	public void set(RelayEvent relayEvent) {
		MemorySingleton.getInstance().getEvents().put(relayEvent.getUuid(), relayEvent);
	}

	@Override
	public RelayEvent get(UUID uuid) {
		return MemorySingleton.getInstance().getEvents().get(uuid);
	}
}