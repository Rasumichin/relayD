package com.relayd.ejb.orm.file;

import java.util.List;
import java.util.UUID;

import com.relayd.Member;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Position;
import com.relayd.ejb.MemberGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public class MemberGatewayFile implements MemberGateway {

	public MemberGatewayFile() {

	}

	MemberGatewayFile(String aFileName) {
		FileSingleton.getInstance().setFileName(aFileName);
	}

	@Override
	public Member get(UUID uuid) {
		List<RelayEvent> someRelayEvents = FileSingleton.getInstance().getRelayEvents();

		RelayEvent relayEvent = someRelayEvents.get(0);
		for (Relay eachRelay : relayEvent.getRelays()) {
			for (Member eachMember : eachRelay.getMembers()) {
				if (uuid.equals(eachMember.getUuid())) {
					return eachMember;
				}
			}
		}
		return null;
	}

	@Override
	public void set(Member aMember) {
		List<RelayEvent> someRelayEvents = FileSingleton.getInstance().getRelayEvents();

		RelayEvent relayEvent = someRelayEvents.get(0);
		boolean found = false;
		for (Relay eachRelay : relayEvent.getRelays()) {
			if (eachRelay.getMembers().contains(aMember)) {
				for (Position eachPosition : Position.values()) {
					Member member = eachRelay.getMemberFor(eachPosition);
					if (aMember.equals(member)) {
						mapMemberToMember(member, aMember);
						eachRelay.addMember(aMember, eachPosition);
						found = true;
					}
				}
			}
			if (found == false) {
				Relay relay = relayEvent.getRelays().iterator().next();
				relay.addMember(aMember);
			}
		}
		FileSingleton.getInstance().setRelayEvents(someRelayEvents);
	}

	private void mapMemberToMember(Member target, Member source) {
		target.setDuration(source.getDuration());
	}
}