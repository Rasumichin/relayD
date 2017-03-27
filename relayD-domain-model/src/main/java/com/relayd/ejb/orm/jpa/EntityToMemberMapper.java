package com.relayd.ejb.orm.jpa;

import com.relayd.Member;
import com.relayd.Person;
import com.relayd.entity.ParticipantEntity;

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

	public Member mapToMember(ParticipantEntity participantEntity) {
		if (participantEntity == null) {
			throw new IllegalArgumentException("[memberEntity] must not be 'null'.");
		}

		EntityToPersonMapper personMapper = EntityToPersonMapper.newInstance();
		Person person = personMapper.mapToPerson(participantEntity.getPersonEntity());
		Member member = Member.newInstance(person);

		return member;
	}
}
