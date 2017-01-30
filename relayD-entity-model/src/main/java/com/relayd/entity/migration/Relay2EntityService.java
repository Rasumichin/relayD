package com.relayd.entity.migration;

import java.util.List;

import com.relayd.entity.*;
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

	@Override
	RelayCounter countFetchRelayResult(List<?> aRelay2EntityList) {
		RelayCounter result = RelayCounter.newInstance();
		result.setRelayCount(aRelay2EntityList.size());
		result.setParticipantCount(Integer.valueOf(0));
		
		@SuppressWarnings("unchecked")
		List<Relay2Entity> relay2EntityList = (List<Relay2Entity>) aRelay2EntityList;
		for (Relay2Entity eachEntity : relay2EntityList) {
			result.incrementParticipants(eachEntity.getParticipantEntities().size());
		}
		
		return result;
	}
}
