package com.relayd.ejb.orm.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventName;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 * status   initial
 */
public class RelayEventGatewayMemory implements RelayEventGateway {
	// TODO -schmollc- 01.06.2016 Remove public when create User Story is implemented!
	public static Set<RelayEvent> events = new HashSet<RelayEvent>();

	@Override
	public void set(RelayEvent aRelayEventEntity) {
		events.add(aRelayEventEntity);
	}

	@Override
	public List<RelayEvent> getAll() {
		List<RelayEvent> eventsAsList = new ArrayList<RelayEvent>();
		for (RelayEvent relayEvent : events) {
			eventsAsList.add(relayEvent);
		}
		return eventsAsList;
	}

	@Override
	public RelayEvent get(EventName aRelayName, Date aRelayDay) {
		for (RelayEvent relayEvent : events) {
			if (relayEvent.getName().equals(aRelayName) && relayEvent.getEventDate().equals(aRelayDay)) {
				return relayEvent;
			}
		}
		return null;
	}
}