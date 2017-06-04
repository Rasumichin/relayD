package com.relayd.ejb.orm.memory;

import java.util.UUID;

import com.relayd.Member;
import com.relayd.ejb.MemberGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public class MemberGatewayMemory implements MemberGateway {

	@Override
	public Member get(UUID uuid) {
		return MemorySingleton.getInstance().getMembers().get(uuid);
	}

	@Override
	public void set(Member member) {
		MemorySingleton.getInstance().getMembers().put(member.getUuidPerson(), member);
	}
}