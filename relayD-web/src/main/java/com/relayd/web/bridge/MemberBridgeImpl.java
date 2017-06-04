package com.relayd.web.bridge;

import java.util.UUID;

import com.relayd.Member;
import com.relayd.Settings;
import com.relayd.ejb.GatewayType;
import com.relayd.web.pagebean.MemberBridge;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 03.06.2017
 *
 */
public class MemberBridgeImpl implements MemberBridge {

	@Override
	public GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

	@Override
	public Member get(UUID uuid) {
		return null;
	}

	@Override
	public void set(Member member) {
	}

	//
	//	private MemberGateway getGateway() {
	//		return MemberGatewayFactory.get(getGatewayType());
	//	}

}
