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
		List<?> result = readRelays();
		relayCounter = countFetchRelayResult(result);
		
		return relayCounter;
	}

	List<?> readRelays() {
		String jpql = getJpqlStatement();
		List<?> result = getJpaDao().performSelectQuery(jpql);
		
		return result;
	}

	String getJpqlStatement() {
		return "select r from RelayEntity r";
	}

	RelayCounter countFetchRelayResult(List<?> aRelayEntityList) {
		RelayCounter result = RelayCounter.newInstance();
		result.setRelayCount(aRelayEntityList.size());
		result.setParticipantCount(Integer.valueOf(0));
		
		@SuppressWarnings("unchecked")
		List<RelayEntity> relayEntityList = (List<RelayEntity>) aRelayEntityList;
		for (RelayEntity eachEntity : relayEntityList) {
			result.incrementParticipants((eachEntity.getParticipantOne() == null) ? 0 : 1);
			result.incrementParticipants((eachEntity.getParticipantTwo() == null) ? 0 : 1);
			result.incrementParticipants((eachEntity.getParticipantThree() == null) ? 0 : 1);
			result.incrementParticipants((eachEntity.getParticipantFour() == null) ? 0 : 1);
		}
		
		return result;
	}
}
