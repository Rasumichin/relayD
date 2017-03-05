package com.relayd.entity.initializer;

import com.relayd.entity.*;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  05.03.2017
 *
 */
public class Relay2EntityInitializer {
	
	public static Relay2Entity newRelay2EntityWithOneParticipant() {
		Relay2Entity relay2Entity = Relay2Entity.newInstance();
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		relay2Entity.addParticipantEntity(participantEntity);
		
		return relay2Entity;
	}
}
