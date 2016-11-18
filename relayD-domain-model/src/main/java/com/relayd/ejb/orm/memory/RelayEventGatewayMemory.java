package com.relayd.ejb.orm.memory;

import java.util.ArrayList;
import java.util.List;

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
		ArrayList<RelayEvent> eventsAsList = new ArrayList<RelayEvent>();

		eventsAsList.add(createEventForDuesseldorfMarathon());

		return eventsAsList;
	}

	private RelayEvent createEventForDuesseldorfMarathon() {
		RelayEvent relayEvent = RelayEvent.duesseldorf();
		return relayEvent;
	}
}