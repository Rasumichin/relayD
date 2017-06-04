package com.relayd.ejb.orm.file;

import java.util.UUID;

import com.relayd.Member;
import com.relayd.ejb.MemberGateway;

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