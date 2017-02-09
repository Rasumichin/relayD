package com.relayd.web.bridge;

import java.util.List;

import com.relayd.RelayEvent;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 09.02.2017
 *
 */
public interface RelayEventBridge {
	List<RelayEvent> all();

	void set(RelayEvent relayEvent);
}
