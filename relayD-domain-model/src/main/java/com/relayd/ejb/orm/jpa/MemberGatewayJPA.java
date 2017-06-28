package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.Member;
import com.relayd.ejb.MemberGateway;
import com.relayd.entity.ParticipantEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 * TODO -small- Tests schreiben. Leider keine da zu lange nix gemacht und nun die DB Änderungen schon durch sind! Mein Fehler!!
 */
public class MemberGatewayJPA extends GatewayJPA implements MemberGateway {

	@Override
	public Member get(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}
		ParticipantEntity participantEntity = findById(uuid);
		Member member = EntityToMemberMapper.newInstance().mapToMember(participantEntity);
		return member;
	}

	private ParticipantEntity findById(UUID uuid) {
		ParticipantEntity result = getJpaDao().findById(ParticipantEntity.class, uuid.toString());
		return result;
	}

	@Override
	public void set(Member member) {
		if (member == null) {
			throw new IllegalArgumentException("[member] must not be 'null'.");
		}
		ParticipantEntity participantEntity = findById(member.getUuid());

		MemberToEntityMapper.newInstance().mapMemberToEntity(member, participantEntity);

		getJpaDao().mergeEntity(participantEntity);
	}
}