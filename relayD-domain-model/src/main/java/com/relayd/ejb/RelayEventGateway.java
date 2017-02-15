package com.relayd.ejb;

import java.util.List;
import java.util.UUID;

import com.relayd.RelayEvent;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 *
 */
public interface RelayEventGateway {
	List<RelayEvent> getAll();

	void set(RelayEvent relayEvent);

	RelayEvent get(UUID aUuid);
}