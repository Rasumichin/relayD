package com.relayd.web.bridge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;
import com.relayd.ejb.orm.memory.RelayGatewayMemory;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 * status initial
 */
public class RelayBridgeImpl implements RelayBridge {

	private RelayGateway gateway = null;

	public RelayBridgeImpl() {
		gateway = new RelayGatewayMemory();
	}

	@Override
	public List<RelayRow> all() {
		List<RelayRow> rows = new ArrayList<RelayRow>();
		for (Relay relay : gateway.getAll()) {
			RelayRow rowRelay = new RelayRow();

			rowRelay.setRelayname(relay.getRelayname());
			rowRelay.setUUID(relay.getUUID());
			rows.add(rowRelay);

			for (Iterator<Person> iter = relay.iterator(); iter.hasNext();) {
				Person person = iter.next();
				RelayRow rowPerson = new RelayRow();

				rowPerson.setForename(person.getForename());
				rowPerson.setSurename(person.getSurename());
				rowPerson.setUUID(person.getUUID());
				rows.add(rowPerson);
			}
		}

		return rows;
	}

	@Override
	public void update(Relay relay) {
		gateway.set(relay);
	}

	@Override
	public void create(Relay relay) {
		gateway.set(relay);
	}

	@Override
	public Relay get(UUID uuid) {
		return gateway.get(uuid);
	}

	@Override
	public void remove(Relay relay) {
		gateway.remove(relay.getUUID());
	}

	@Override
	public void remove(Person person) {
	}

	@Override
	public void add(RelayRow relayRow, Person person) {
		Relay relay = get(relayRow.getUUID());
		relay.add(person);
		gateway.set(relay);
	}
}