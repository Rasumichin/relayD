package com.relayd.ejb.orm.jpa;

import java.util.List;
import java.util.UUID;

import com.relayd.Relay;
import com.relayd.ejb.RelayGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   19.11.2016
 *
 */
public class RelayGatewayJPA implements RelayGateway {

	@Override
	public List<Relay> getAll() {
		throw new UnsupportedOperationException("not implemented yet!");
	}

	@Override
	public void set(Relay aRelay) {
		throw new UnsupportedOperationException("not implemented yet!");
	}

	@Override
	public Relay get(UUID aUuid) {
		throw new UnsupportedOperationException("not implemented yet!");
	}
}