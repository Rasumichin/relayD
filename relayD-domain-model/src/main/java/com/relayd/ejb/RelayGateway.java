package com.relayd.ejb;

import java.util.List;
import java.util.UUID;

import com.relayd.Relay;

public interface RelayGateway {

	void set(Relay relay);

	List<Relay> getAll();

	Relay get(UUID aUuid);

	void remove(UUID uuid);

}