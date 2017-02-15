package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import com.relayd.RelayEvent;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 09.02.2017
 *
 */
public interface RelayEventBridge {
	List<RelayEvent> all();

	void set(RelayEvent relayEvent);

	RelayEvent get(UUID uuid);
}
