package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import com.relayd.RelayEvent;
import com.relayd.Settings;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayFactory;

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
}