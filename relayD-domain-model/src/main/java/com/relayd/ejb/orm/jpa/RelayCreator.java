package com.relayd.ejb.orm.jpa;

import java.time.Duration;
import java.util.UUID;

import com.relayd.Member;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.entity.MemberEntity;
import com.relayd.entity.RelayEntity;

public class RelayCreator {

	private RelayCreator() {
		//restrict access
	}

	public static RelayCreator newInstance() {
		return new RelayCreator();
	}

	Relay createFrom(RelayEntity relayEntity, RelayEvent relayEvent) {
		Relay relay = Relay.newInstance(relayEvent);
		relay.setUuid(UUID.fromString(relayEntity.getId()));
		relay.setRelayname(Relayname.newInstance(relayEntity.getRelayname()));
		relay.setDuration(Duration.ofMillis(relayEntity.getDuration()));

		EntityToMemberMapper memberMapper = EntityToMemberMapper.newInstance();
		for (MemberEntity eachMemberEntity : relayEntity.getMemberEntities()) {
			Member member = memberMapper.mapToMember(eachMemberEntity);
			Position position = Position.newInstance(eachMemberEntity.getPosition());
			relay.addMember(member, position);
		}
		return relay;
	}
}
