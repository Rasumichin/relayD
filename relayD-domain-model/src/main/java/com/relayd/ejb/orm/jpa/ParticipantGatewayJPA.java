package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.Participant;
import com.relayd.ejb.ParticipantGateway;
import com.relayd.entity.ParticipantEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public class ParticipantGatewayJPA extends GatewayJPA implements ParticipantGateway {

	private EntityToParticipantMapper entityToParticipantMapper = EntityToParticipantMapper.newInstance();
	private ParticipantToEntityMapper participantToEntityMapper = ParticipantToEntityMapper.newInstance();

	@Override
	public Participant get(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}
		ParticipantEntity participantEntity = findById(uuid);
		Participant participant = getEntityToParticipantMapper().mapToParticipant(participantEntity);
		return participant;

	}

	ParticipantEntity findById(UUID uuid) {
		ParticipantEntity result = getJpaDao().findById(ParticipantEntity.class, uuid.toString());
		return result;
	}

	@Override
	public void set(Participant participant) {
		if (participant == null) {
			throw new IllegalArgumentException("[participant] must not be 'null'.");
		}
		ParticipantEntity memberEntity = findById(participant.getUuid());

		getParticipantToEntityMapper().mapParticipantToEntity(participant, memberEntity);

		getJpaDao().mergeEntity(memberEntity);

	}

	private EntityToParticipantMapper getEntityToParticipantMapper() {
		return entityToParticipantMapper;
	}

	private ParticipantToEntityMapper getParticipantToEntityMapper() {
		return participantToEntityMapper;
	}
}