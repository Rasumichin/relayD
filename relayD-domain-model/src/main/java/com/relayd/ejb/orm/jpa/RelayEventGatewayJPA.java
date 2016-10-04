package com.relayd.ejb.orm.jpa;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.10.2016
 *
 */
public class RelayEventGatewayJPA implements RelayEventGateway {

	// TODO -schmollc- Hier muss auf JPA umgestellt werden!!!!
	private static final String DUESSELDORF_MARATHON = "Metro Group Marathon Düsseldorf";
	private static final EventDay DUESSELDORF_DAY = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

	@Override
	public List<RelayEvent> getAll() {
		ArrayList<RelayEvent> eventsAsList = new ArrayList<RelayEvent>();

		eventsAsList.add(createEventForDuesseldorfMarathon());

		return eventsAsList;
	}

	private RelayEvent createEventForDuesseldorfMarathon() {
		Eventname eventName = Eventname.newInstance(DUESSELDORF_MARATHON);
		EventDay eventDay = DUESSELDORF_DAY;
		RelayEvent relayEvent = RelayEvent.newInstance(eventName, eventDay);
		return relayEvent;
	}
}