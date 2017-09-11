package com.relayd.ejb.orm.jpa;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.relayd.Member;
import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.entity.MemberEntity;

/**
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   23.02.2017
 *
 */
public class EntityToMemberMapper {

	private EntityToMemberMapper() {
	}

	public static EntityToMemberMapper newInstance() {
		return new EntityToMemberMapper();
	}

	public Member mapToMember(MemberEntity memberEntity) {
		if (memberEntity == null) {
			throw new IllegalArgumentException("[memberEntity] must not be 'null'.");
		}

		EntityToPersonMapper personMapper = EntityToPersonMapper.newInstance();
		Person person = personMapper.mapToPerson(memberEntity.getPersonEntity());
		Participant participant = Participant.newInstance(person);
		Member member = Member.newInstance(participant);
		member.setUuid(UUID.fromString(memberEntity.getId()));
		member.setDuration(Duration.of(memberEntity.getDuration(), ChronoUnit.MILLIS));
		return member;
	}
}
