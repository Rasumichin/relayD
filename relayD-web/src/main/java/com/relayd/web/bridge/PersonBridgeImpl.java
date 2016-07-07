package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayFactory;

/**
 * Only a simple Wrapper for the Gateway.
 *
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 * status initial
 */
public class PersonBridgeImpl implements PersonBridge {

	private PersonGateway gateway = null;

	public PersonBridgeImpl() {
		gateway = PersonGatewayFactory.get(GatewayType.FILE);
	}

	@Override
	public List<Person> all() {
		return gateway.getAll();
	}

	@Override
	public void update(Person person) {
		gateway.set(person);
	}

	@Override
	public void create(Person person) {
		gateway.set(person);
	}

	@Override
	public Person get(UUID uuid) {
		return gateway.get(uuid);
	}

	@Override
	public void remove(Person person) {
		gateway.remove(person.getUUID());
	}
}