package com.relayd.web.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.relayd.RelayEvent;
import com.relayd.Settings;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayFactory;
import com.relayd.web.pagebean.RelayEventDisplay;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 09.02.2017
 *
 */
public class RelayEventBridgeImpl implements RelayEventBridge {

	RelayEventGateway getGateway() {
		return RelayEventGatewayFactory.get(getGatewayType());
	}

	private GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

	@Override
	public List<RelayEvent> all() {
		return getGateway().getAll();
	}

	@Override
	public void set(RelayEvent relayEvent) {
		getGateway().set(relayEvent);
	}

	@Override
	public RelayEvent get(UUID uuid) {
		return getGateway().get(uuid);
	}

	@Override
	public List<RelayEventDisplay> allDisplays() {
		List<RelayEventDisplay> someDisplays = new ArrayList<>();
		for (RelayEvent eachRelayEvent : all()) {
			RelayEventDisplay display = RelayEventDisplay.newInstance(eachRelayEvent.getUuid(), eachRelayEvent.toString());
			someDisplays.add(display);
		}
		return someDisplays;
	}
}