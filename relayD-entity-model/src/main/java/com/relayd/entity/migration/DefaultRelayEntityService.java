package com.relayd.entity.migration;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  17.01.2017
 *
 */
public class DefaultRelayEntityService implements CountRelayEntityService {
	private RelayCounter relayCounter = RelayCounter.newIntance();

	private DefaultRelayEntityService() {
	}
	
	public static CountRelayEntityService newInstance() {
		return new DefaultRelayEntityService();
	}

	@Override
	public RelayCounter getRelayCounter() {
		return relayCounter;
	}
}
