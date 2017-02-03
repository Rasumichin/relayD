package com.relayd.entity.migration;

import java.util.List;

import com.relayd.entity.RelayEntity;
import com.relayd.jpa.GenericJpaDao;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  17.01.2017
 *
 */
public class DefaultCountRelayEntityService extends MigrationService implements CountRelayEntityService {
	private RelayCounter relayCounter = RelayCounter.newInstance();

	protected DefaultCountRelayEntityService(GenericJpaDao aJpaDao) {
		setJpaDao(aJpaDao);
	}

	public static CountRelayEntityService newInstance(GenericJpaDao aJpaDao) {
		return new DefaultCountRelayEntityService(aJpaDao);
	}
	
	@Override
	public RelayCounter count() {
		List<?> result = readRelays(getJpqlStatement());
		relayCounter = countFetchRelayResult(result);
		
		return relayCounter;
	}

	String getJpqlStatement() {
		return READ_ALL_RELAY_ENTITIES_SQL;
	}

	RelayCounter countFetchRelayResult(List<?> aRelayEntityList) {
		RelayCounter result = initializeCounter(aRelayEntityList);
		countParticipants(aRelayEntityList, result);
		
		return result;
	}

	RelayCounter initializeCounter(List<?> aRelayEntityList) {
		RelayCounter result = RelayCounter.newInstance();
		result.setRelayCount(aRelayEntityList.size());
		result.setParticipantCount(Integer.valueOf(0));
		
		return result;
	}

	void countParticipants(List<?> aRelayEntityList, RelayCounter result) {
		@SuppressWarnings("unchecked")
		List<RelayEntity> relayEntityList = (List<RelayEntity>) aRelayEntityList;
		for (RelayEntity eachEntity : relayEntityList) {
			result.incrementParticipants((eachEntity.getParticipantOne() == null) ? 0 : 1);
			result.incrementParticipants((eachEntity.getParticipantTwo() == null) ? 0 : 1);
			result.incrementParticipants((eachEntity.getParticipantThree() == null) ? 0 : 1);
			result.incrementParticipants((eachEntity.getParticipantFour() == null) ? 0 : 1);
		}
	}
}
