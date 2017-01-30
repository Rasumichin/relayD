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
public class DefaultRelayEntityService implements CountRelayEntityService {

	private RelayCounter relayCounter = RelayCounter.newInstance();
	private GenericJpaDao jpaDao;

	protected DefaultRelayEntityService(GenericJpaDao aJpaDao) {
		jpaDao = aJpaDao;
	}

	public static CountRelayEntityService newInstance(GenericJpaDao aJpaDao) {
		DefaultRelayEntityService.verifyJpaDao(aJpaDao);
		
		return new DefaultRelayEntityService(aJpaDao);
	}
	
	protected static void verifyJpaDao(GenericJpaDao aJpaDao) {
		if (aJpaDao == null) {
			throw new IllegalArgumentException("[jpaDao] must not be 'null'.");
		}
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
	
	GenericJpaDao getJpaDao() {
		return jpaDao;
	}
}
