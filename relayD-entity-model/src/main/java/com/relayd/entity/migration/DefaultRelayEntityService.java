package com.relayd.entity.migration;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  17.01.2017
 *
 */
public class DefaultRelayEntityService implements CountRelayEntityService {

	private DefaultRelayEntityService() {
	}
	
	public static CountRelayEntityService newInstance() {
		return new DefaultRelayEntityService();
	}

	@Override
	public Integer countRelays() {
		return Integer.valueOf(0);
	}

	@Override
	public Integer countParticipants() {
		return Integer.valueOf(0);
	}
}
