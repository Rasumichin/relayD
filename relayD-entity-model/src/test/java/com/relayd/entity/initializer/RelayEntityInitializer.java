package com.relayd.entity.initializer;

import com.relayd.entity.*;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  05.03.2017
 *
 */
public class RelayEntityInitializer {
	
	public static RelayEntity newRelayEntityWithOneParticipant() {
		RelayEntity relayEntity = RelayEntity.newInstance();
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		relayEntity.addParticipantEntity(participantEntity);
		
		return relayEntity;
	}
}
