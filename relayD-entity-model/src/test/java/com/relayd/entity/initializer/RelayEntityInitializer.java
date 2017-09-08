package com.relayd.entity.initializer;

import com.relayd.entity.MemberEntity;
import com.relayd.entity.RelayEntity;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  05.03.2017
 *
 */
public class RelayEntityInitializer {

	public static RelayEntity newRelayEntityWithOneMember() {
		RelayEntity relayEntity = RelayEntity.newInstance();
		MemberEntity memberEntity = MemberEntity.newInstance();
		relayEntity.addMemberEntity(memberEntity);

		return relayEntity;
	}
}
