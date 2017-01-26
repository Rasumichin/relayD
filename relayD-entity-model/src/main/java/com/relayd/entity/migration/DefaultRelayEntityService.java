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

	private DefaultRelayEntityService(GenericJpaDao aJpaDao) {
		jpaDao = aJpaDao;
	}

	public static CountRelayEntityService newInstance(GenericJpaDao aJpaDao) {
		if (aJpaDao == null) {
			throw new IllegalArgumentException("[jpaDao] must not be 'null'.");
		}
		
		return new DefaultRelayEntityService(aJpaDao);
	}

	@Override
	public RelayCounter count() {
		List<RelayEntity> result = readRelays();
		relayCounter = countFetchRelayResult(result);
		
		return relayCounter;
	}

	@SuppressWarnings("unchecked")
	List<RelayEntity> readRelays() {
		String jpql = getJpqlStatement();
		List<?> result = getJpaDao().performSelectQuery(jpql);
		
		return (List<RelayEntity>) result;
	}

	String getJpqlStatement() {
		return "select r from RelayEntity r";
	}

	RelayCounter countFetchRelayResult(List<RelayEntity> relays) {
		RelayCounter result = RelayCounter.newInstance();
		result.setRelayCount(relays.size());
		result.setParticipantCount(Integer.valueOf(0));
		
		for (RelayEntity eachEntity : relays) {
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
