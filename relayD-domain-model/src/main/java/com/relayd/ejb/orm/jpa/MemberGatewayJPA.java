package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.Member;
import com.relayd.ejb.MemberGateway;
import com.relayd.entity.MemberEntity;

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
		MemberEntity memberEntity = findById(uuid);
		Member member = getEntityToMemberMapper().mapToMember(memberEntity);
		return member;
	}

	MemberEntity findById(UUID uuid) {
		MemberEntity result = getJpaDao().findById(MemberEntity.class, uuid.toString());
		return result;
	}

	@Override
	public void set(Member member) {
		if (member == null) {
			throw new IllegalArgumentException("[member] must not be 'null'.");
		}
		MemberEntity memberEntity = findById(member.getUuid());

		getMemberToEntityMapper().mapMemberToEntity(member, memberEntity);

		getJpaDao().mergeEntity(memberEntity);
	}

	EntityToMemberMapper getEntityToMemberMapper() {
		return entityToMemberMapper;
	}

	MemberToEntityMapper getMemberToEntityMapper() {
		return memberToEntityMapper;
	}
}