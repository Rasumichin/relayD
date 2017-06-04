package com.relayd.web.pagebean;

import java.util.UUID;

import com.relayd.Member;
import com.relayd.ejb.GatewayType;

public class MemberBridgeFake implements MemberBridge {

	private Member member = Member.newInstance();

	@Override
	public Member get(UUID aUuid) {
		return member;
	}

	@Override
	public void set(Member aWorkingMember) {
		member = aWorkingMember;
	}

	@Override
	public GatewayType getGatewayType() {
		return null;
	}
}