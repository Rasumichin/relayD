package com.relayd.entity.migration;

import com.relayd.jpa.GenericJpaDao;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  04.02.2017
 *
 */
public class CopyRelayService extends MigrationService {

	private CopyRelayService(GenericJpaDao aJpaDao) {
		setJpaDao(aJpaDao);
	}
	
	public static CopyRelayService newInstance(GenericJpaDao aJpaDao) {
		return new CopyRelayService(aJpaDao);
	}
	
	public RelayCounter copyAllRelayToRelay2() {
		return getRelayCounter();
	}
}
