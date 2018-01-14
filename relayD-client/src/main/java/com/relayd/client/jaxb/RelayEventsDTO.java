package com.relayd.client.jaxb;

import java.io.Serializable;
import java.util.*;

import javax.xml.bind.annotation.*;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 14.01.2018
 *
 */
@XmlRootElement(name = "relayEvents")
public class RelayEventsDTO implements Serializable {
	private static final long serialVersionUID = 2310439687670009459L;

	private List<RelayEventDTO> relayEvents = new ArrayList<>();

	public RelayEventsDTO() {
	}

	@XmlElement
	public List<RelayEventDTO> getRelayEvents() {
		return relayEvents;
	}

	public void setRelayEvents(List<RelayEventDTO> aRelayEventDTOList) {
		if (aRelayEventDTOList == null) {
			throw new NullPointerException("'aRelayEventDTOList' must not be 'null'.");
		}
		relayEvents = aRelayEventDTOList;
	}

	public void addAllRelayEvents(List<RelayEventDTO> aRelayEventDTOList) {
		getRelayEvents().addAll(aRelayEventDTOList);
	}

	@Override
	public String toString() {
		return "RelayEventsDTO [relayEventDTO elements=" + relayEvents.size() + "]";
	}
}
