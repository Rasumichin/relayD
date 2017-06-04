package com.relayd.ejb;

import java.util.UUID;

import com.relayd.Member;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public class MemberGatewayFile implements MemberGateway {

	@Override
	public Member get(UUID uuid) {
		return null;
	}

	@Override
	public void set(Member member) {
	}
}