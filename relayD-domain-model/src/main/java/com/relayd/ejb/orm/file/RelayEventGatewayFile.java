package com.relayd.ejb.orm.file;

import java.util.List;

import com.relayd.RelayEvent;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author schmollc
 * @since 10.02.2016
 *
 */
public class RelayEventGatewayFile implements RelayEventGateway {

	@Override
	public List<RelayEvent> getAll() {
		return null;
	}

	@Override
	public void set(RelayEvent aRelayEvent) {
	}
}