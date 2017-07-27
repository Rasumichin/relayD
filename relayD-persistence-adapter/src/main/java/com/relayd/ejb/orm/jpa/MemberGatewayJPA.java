package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.Member;
import com.relayd.ejb.MemberGateway;
import com.relayd.entity.ParticipantEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public class MemberGatewayJPA extends GatewayJPA implements MemberGateway {

	private EntityToMemberMapper entityToMemberMapper = EntityToMemberMapper.newInstance();
	private MemberToEntityMapper memberToEntityMapper = MemberToEntityMapper.newInstance();

	@Override
	public Member get(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}
		ParticipantEntity participantEntity = findById(uuid);
		Member member = getEntityToMemberMapper().mapToMember(participantEntity);
		return member;
	}

	ParticipantEntity findById(UUID uuid) {
		ParticipantEntity result = getJpaDao().findById(ParticipantEntity.class, uuid.toString());
		return result;
	}

	@Override
	public void set(Member member) {
		if (member == null) {
			throw new IllegalArgumentException("[member] must not be 'null'.");
		}
		ParticipantEntity participantEntity = findById(member.getUuid());

		getMemberToEntityMapper().mapMemberToEntity(member, participantEntity);

		getJpaDao().mergeEntity(participantEntity);
	}

	EntityToMemberMapper getEntityToMemberMapper() {
		return entityToMemberMapper;
	}

	MemberToEntityMapper getMemberToEntityMapper() {
		return memberToEntityMapper;
	}
}