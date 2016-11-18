package com.relayd.ejb.orm.jpa;

import java.util.ArrayList;
import java.util.List;

import com.relayd.RelayEvent;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.10.2016
 *
 */
public class RelayEventGatewayJPA implements RelayEventGateway {

	// TODO -schmollc- Hier muss auf JPA umgestellt werden!!!!
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