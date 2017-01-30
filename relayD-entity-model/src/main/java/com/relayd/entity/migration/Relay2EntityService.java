package com.relayd.entity.migration;

import com.relayd.jpa.GenericJpaDao;

public class Relay2EntityService extends DefaultRelayEntityService {
	
	private Relay2EntityService(GenericJpaDao aJpaDao) {
		super(aJpaDao);
	}

	public static CountRelayEntityService newInstance(GenericJpaDao aJpaDao) {
		DefaultRelayEntityService.verifyJpaDao(aJpaDao);
		
		return new Relay2EntityService(aJpaDao);
	}

	@Override
	String getJpqlStatement() {
		return "select r2 from Relay2Entity r2";
	}

}
