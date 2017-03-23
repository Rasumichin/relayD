package com.relayd.ejb.orm.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.relayd.RelayEvent;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.10.2016
 *
 */
public class RelayEventGatewayJPA implements RelayEventGateway {

	// TODO (Christian, Version 1.4): Hier muss auf JPA umgestellt werden!!!!
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

	@Override
	public void set(@SuppressWarnings("unused") RelayEvent relayEvent) {
		throw new UnsupportedOperationException("Muss noch gecodet werden");
	}

	@Override
	public RelayEvent get(@SuppressWarnings("unused") UUID uuid) {
		throw new UnsupportedOperationException("Muss noch gecodet werden");
	}
}