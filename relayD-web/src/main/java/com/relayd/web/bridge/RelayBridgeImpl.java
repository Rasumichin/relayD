package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;
import com.relayd.ejb.orm.memory.RelayGatewayMemory;

public class RelayBridgeImpl implements RelayBridge {

	private RelayGateway gateway = null;

	public RelayBridgeImpl() {
		gateway = new RelayGatewayMemory();
	}

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
	public void add(Person person) {
	}
}