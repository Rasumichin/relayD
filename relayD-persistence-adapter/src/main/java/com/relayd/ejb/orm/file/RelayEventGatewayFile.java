package com.relayd.ejb.orm.file;

import java.util.List;
import java.util.UUID;

import com.relayd.RelayEvent;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author schmollc
 * @since 10.02.2016
 *
 */
public class RelayEventGatewayFile implements RelayEventGateway {

	private RelayEventToRelayEventMapper relayEventToRelayEventMapper = RelayEventToRelayEventMapper.newInstance();

	public RelayEventGatewayFile() {
	}

	public RelayEventGatewayFile(String aFilename) {
		FileSingleton.getInstance().setFileName(aFilename);
	}

	@Override
	public List<RelayEvent> getAll() {
		return FileSingleton.getInstance().getRelayEvents();
	}

	@Override
	public void set(RelayEvent updateRelayEvent) {
		List<RelayEvent> someRelayEvents = getAll();

		if (someRelayEvents.contains(updateRelayEvent)) {
			for (RelayEvent eachRelayEvent : someRelayEvents) {
				if (updateRelayEvent.equals(eachRelayEvent)) {
					getRelayEventToRelayEventMapper().mapRelayEventToRelayEvent(updateRelayEvent, eachRelayEvent);
					break;
				}
			}
		} else {
			someRelayEvents.add(updateRelayEvent);
		}

		FileSingleton.getInstance().setRelayEvents(someRelayEvents);
	}

	private RelayEventToRelayEventMapper getRelayEventToRelayEventMapper() {
		return relayEventToRelayEventMapper;
	}

	@Override
	public RelayEvent get(UUID uuid) {
		for (RelayEvent eachRelayEvent : getAll()) {
			if (uuid.equals(eachRelayEvent.getUuid())) {
				return eachRelayEvent;
			}
		}
		return null;
	}

	void clear() {
		FileSingleton.getInstance().clear();
	}
}