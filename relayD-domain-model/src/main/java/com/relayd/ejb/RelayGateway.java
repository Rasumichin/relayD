package com.relayd.ejb;

import java.util.List;
import java.util.UUID;

import com.relayd.Relay;

/**
 * @author  schmollc (Christian@relayD.de)
 * @since   23.06.2016
 * status   initial
 */
public interface RelayGateway {

	void set(Relay relay);

	List<Relay> getAll();

	Relay get(UUID uuid);

	void remove(UUID uuid);

}