package com.relayd.ejb;

import java.util.List;
import java.util.UUID;

import com.relayd.RelayEvent;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 * status   initial
 */
public interface RelayEventGateway {
	void set(RelayEvent relayEvent);

	List<RelayEvent> getAll();

	RelayEvent get(UUID uuid);

	void remove(UUID uuid);
}