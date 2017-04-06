package com.relayd.ejb.orm.memory;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.relayd.ParticipantBuilder;
import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 *
 */
public class RelayEventGatewayMemory implements RelayEventGateway {

	static Map<UUID, RelayEvent> events = new HashMap<UUID, RelayEvent>();

	@Override
	public List<RelayEvent> getAll() {
		List<RelayEvent> eventsAsList = new ArrayList<RelayEvent>();

		eventsAsList.add(createEventForDuesseldorfMarathon());
		eventsAsList.addAll(events.values());

		return eventsAsList;
	}

	private RelayEvent createEventForDuesseldorfMarathon() {
		Eventname eventName = Eventname.newInstance("Metro Group Marathon Düsseldorf");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent relayEvent = RelayEvent.newInstance(eventName, eventDay);

		relayEvent.addParticipant(new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build());
		relayEvent.addParticipant(new ParticipantBuilder().withForename("Peter").withSurename("Shaw").build());

		return relayEvent;
	}

	@Override
	public void set(RelayEvent relayEvent) {
		events.put(relayEvent.getUuid(), relayEvent);
	}

	@Override
	public RelayEvent get(UUID uuid) {
		return events.get(uuid);
	}
}