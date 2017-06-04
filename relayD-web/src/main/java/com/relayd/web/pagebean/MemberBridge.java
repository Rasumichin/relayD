package com.relayd.web.pagebean;

import java.util.UUID;

import com.relayd.Member;
import com.relayd.ejb.GatewayType;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 03.06.2017
 *
 */
public interface MemberBridge {
	GatewayType getGatewayType();

	Member get(UUID uuid);

	void set(Member member);
}