package com.relayd.ejb.orm.file;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.relayd.RelayEvent;

public class BigData implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<RelayEvent> relayEvents = new ArrayList<>();

	private BigData() {
	}

	public static BigData newInstance() {
		return new BigData();
	}

	public List<RelayEvent> getRelayEvents() {
		return relayEvents;
	}

	public void setRelayEvents(List<RelayEvent> aRelayEvents) {
		relayEvents = aRelayEvents;
	}
}