package com.relayd.ejb.orm.memory;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 *
 */
public class RelayEventGatewayMemory implements RelayEventGateway {

	static Map<UUID, RelayEvent> events = new HashMap<UUID, RelayEvent>();

	public RelayEventGatewayMemory() {
		set(createEventForDuesseldorfMarathon());
	}

	@Override
	public List<RelayEvent> getAll() {
		List<RelayEvent> eventsAsList = new ArrayList<RelayEvent>();

		eventsAsList.addAll(events.values());

		return eventsAsList;
	}

	private RelayEvent createEventForDuesseldorfMarathon() {
		Eventname eventName = Eventname.newInstance("Metro Group Marathon Düsseldorf");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		RelayEvent relayEvent = RelayEvent.newInstance(eventName, eventDay);
		relayEvent.setUuid(UUID.fromString("5697d710-8967-4b2d-9ab2-8fc50ddc6138"));
		// Max stand 2017
		for (int i = 0; i < 36; i++) {
			Person firstPerson = Person.newInstance();
			firstPerson.setForename(Forename.newInstance("Justus"));
			firstPerson.setSurename(Surename.newInstance("Jonas"));
			Participant firstParticipant = Participant.newInstance(firstPerson);
			relayEvent.addParticipant(firstParticipant);

			Person secondPerson = Person.newInstance();
			secondPerson.setForename(Forename.newInstance("Peter"));
			secondPerson.setSurename(Surename.newInstance("Shaw"));
			Participant secondParticipant = Participant.newInstance(secondPerson);
			relayEvent.addParticipant(secondParticipant);
		}
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