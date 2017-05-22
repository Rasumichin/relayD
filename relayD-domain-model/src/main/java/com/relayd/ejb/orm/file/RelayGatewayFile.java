package com.relayd.ejb.orm.file;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.ejb.RelayGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   21.11.2016
 *
 */
public class RelayGatewayFile implements RelayGateway {

	private RelayToRelayMapper relayToRelayMapper = RelayToRelayMapper.newInstance();

	public RelayGatewayFile() {
	}

	RelayGatewayFile(String aFileName) {
		FileSingleton.getInstance().setFileName(aFileName);
	}

	@Override
	public List<Relay> getAll() {
		List<RelayEvent> someRelayEvents = FileSingleton.getInstance().getRelayEvents();
		RelayEvent relayEvent = someRelayEvents.get(0);
		List<Relay> someRelays = new ArrayList<>();
		someRelays.addAll(relayEvent.getRelays());
		return someRelays;
	}

	@Override
	public void set(Relay updateRelay) {
		List<RelayEvent> someRelayEvents = FileSingleton.getInstance().getRelayEvents();

		RelayEvent relayEvent = getRelayEventFotRelay(someRelayEvents, updateRelay.getRelayEvent());

		Set<Relay> someRelays = relayEvent.getRelays();
		if (someRelays.contains(updateRelay)) {
			for (Relay eachRelay : someRelays) {
				if (updateRelay.equals(eachRelay)) {
					getRelayToRelayMapper().mapRelayToRelay(updateRelay, eachRelay);
					break;
				}
			}
		} else {
			relayEvent.addRelay(updateRelay);
		}
		FileSingleton.getInstance().setRelayEvents(someRelayEvents);
	}

	private RelayEvent getRelayEventFotRelay(List<RelayEvent> someRelayEvents, RelayEvent relayEvent) {
		for (RelayEvent eachRelayEvent : someRelayEvents) {
			if (eachRelayEvent.equals(relayEvent)) {
				return eachRelayEvent;
			}
		}
		throw new IllegalArgumentException("[relayEvent] Parameter must be element of [someRelayEvents] parameter");
	}

	@Override
	public Relay get(UUID uuid) {
		for (Relay relay : getAll()) {
			if (uuid.equals(relay.getUuid())) {
				return relay;
			}
		}
		return null;
	}

	private RelayToRelayMapper getRelayToRelayMapper() {
		return relayToRelayMapper;
	}
}