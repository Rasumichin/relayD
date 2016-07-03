package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;

public class RelayBridgeImpl implements RelayBridge {

	private RelayGateway gateway = null;

	@Override
	public List<Relay> all() {
		return gateway.getAll();
	}

	@Override
	public void update(Relay relay) {
		gateway.set(relay);
	}

	@Override
	public void create(Relay relay) {
	}

	@Override
	public Person get(UUID uuid) {
		return null;
	}

	@Override
	public void remove(Relay relay) {
	}

	@Override
	public void remove(Person person) {
	}

	@Override
	public void add(Person person) {
	}
}