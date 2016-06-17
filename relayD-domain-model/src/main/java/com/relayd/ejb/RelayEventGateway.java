package com.relayd.ejb;

import java.util.List;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 * status   initial
 */
public interface RelayEventGateway {
	void set(RelayEvent aRelayEvent);

	List<RelayEvent> getAll();

	/**
	 * Unfortunatly all attributes are the pk, so it seems wired to call a method to get information which i always have!
	 * So think about the RelayEvent as a big object with information like difficulty, coolness, costs etc. etc.
	 */
	RelayEvent get(EventName aRelayName, EventDay aRelayDay);
}