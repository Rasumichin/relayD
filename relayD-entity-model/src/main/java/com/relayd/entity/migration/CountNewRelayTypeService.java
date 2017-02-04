package com.relayd.entity.migration;

import java.util.List;

import com.relayd.entity.*;
import com.relayd.jpa.GenericJpaDao;

public class CountNewRelayTypeService extends DefaultCountRelayEntityService {
	
	private CountNewRelayTypeService(GenericJpaDao aJpaDao) {
		super(aJpaDao);
	}

	public static CountRelayEntityService newInstance(GenericJpaDao aJpaDao) {
		return new CountNewRelayTypeService(aJpaDao);
	}

	@Override
	String getJpqlStatement() {
		return READ_ALL_RELAY2_ENTITIES_SQL;
	}

	@Override
	void countParticipants(List<?> aRelay2EntityList, RelayCounter result) {
		@SuppressWarnings("unchecked")
		List<Relay2Entity> relay2EntityList = (List<Relay2Entity>) aRelay2EntityList;
		for (Relay2Entity eachEntity : relay2EntityList) {
			result.incrementParticipants(eachEntity.getParticipantEntities().size());
		}
	}
}
