package com.relayd.ejb.orm.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.relayd.PersonRelay;
import com.relayd.Relay;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;
import com.relayd.ejb.RelayGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   14.10.2016
 *
 */
public class RelayGatewayMemory implements RelayGateway {

	static Map<UUID, Relay> relays = new HashMap<UUID, Relay>();

	@Override
	public void set(Relay relay) {
		relays.put(relay.getUuid(), relay);
	}

	@Override
	public List<Relay> getAll() {
		ArrayList<Relay> relaysAsList = new ArrayList<Relay>(relays.values());
		relaysAsList.addAll(getAllMock());
		return relaysAsList;
	}

	public List<Relay> getAllMock() {
		List<Relay> result = new ArrayList<Relay>();

		result.add(createDie4());
		result.add(createDieFanta4());

		return result;
	}

	private Relay createDie4() {
		Relay relay = Relay.newInstance();

		relay.setRelayname(Relayname.newInstance("Die 4 ????"));

		PersonRelay person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Justus"));
		person.setSurename(Surename.newInstance("Jonas"));
		relay.addPersonRelay(person, Position.FIRST);

		person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Peter"));
		person.setSurename(Surename.newInstance("Shaw"));
		relay.addPersonRelay(person, Position.SECOND);

		person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Bob"));
		person.setSurename(Surename.newInstance("Andrews"));
		relay.addPersonRelay(person, Position.THIRD);

		person = PersonRelay.newInstance();
		relay.addPersonRelay(person, Position.FOURTH);

		return relay;
	}

	private Relay createDieFanta4() {
		Relay relay = Relay.newInstance();

		relay.setRelayname(Relayname.newInstance("Die Fanta 4"));

		PersonRelay person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Smudo"));
		relay.addPersonRelay(person, Position.FIRST);

		person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Michi"));
		person.setSurename(Surename.newInstance("Beck"));
		relay.addPersonRelay(person, Position.SECOND);

		person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Thomas"));
		person.setSurename(Surename.newInstance("D."));
		relay.addPersonRelay(person, Position.THIRD);

		person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Andy"));
		person.setSurename(Surename.newInstance("Epsilon"));
		relay.addPersonRelay(person, Position.FOURTH);

		return relay;
	}
}