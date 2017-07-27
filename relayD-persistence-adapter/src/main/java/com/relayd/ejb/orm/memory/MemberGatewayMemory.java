package com.relayd.ejb.orm.memory;

import java.util.UUID;

import com.relayd.Member;
import com.relayd.Relay;
import com.relayd.ejb.MemberGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public class MemberGatewayMemory implements MemberGateway {

	@Override
	public Member get(UUID uuid) {
		for (Relay eachRelay : MemorySingleton.getInstance().getRelays().values()) {
			for (Member eachMember : eachRelay.getMembers()) {
				if (uuid.equals(eachMember.getUuid())) {
					return eachMember;
				}
			}
		}
		return MemorySingleton.getInstance().getMembers().get(uuid);
	}

	@Override
	public void set(Member member) {
		MemorySingleton.getInstance().getMembers().put(member.getUuid(), member);
	}
}