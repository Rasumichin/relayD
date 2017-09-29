package com.relayd.ejb.orm.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.relayd.Participant;
import com.relayd.RelayEvent;
import com.relayd.ejb.RelayEventGateway;
import com.relayd.entity.ParticipantEntity;
import com.relayd.entity.PersonEntity;
import com.relayd.entity.RelayEventEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.10.2016
 *
 */
public class RelayEventGatewayJPA extends GatewayJPA implements RelayEventGateway {
	private EntityToRelayEventMapper relayEventEntityMapper = EntityToRelayEventMapper.newInstance();
	private RelayEventToEntityMapper relayEventMapper = RelayEventToEntityMapper.newInstance();
	private ParticipantToEntityMapper participantMapper = ParticipantToEntityMapper.newInstance();

	private EntityToRelayEventMapper getRelayEventEntityMapper() {
		return relayEventEntityMapper;
	}

	private RelayEventToEntityMapper getRelayEventMapper() {
		return relayEventMapper;
	}

	private ParticipantToEntityMapper getParticipantMapper() {
		return participantMapper;
	}

	@Override
	public List<RelayEvent> getAll() {
		List<RelayEventEntity> relayEventEntities = findAll();
		List<RelayEvent> eventsAsList = mapRelayEventEntityListToRelayEventList(relayEventEntities);

		return eventsAsList;
	}

	private List<RelayEvent> mapRelayEventEntityListToRelayEventList(List<RelayEventEntity> relayEventEntities) {
		List<RelayEvent> relayEvents = new ArrayList<>();
		for (RelayEventEntity eachEntity : relayEventEntities) {
			relayEvents.add(getRelayEventEntityMapper().mapToRelayEvent(eachEntity));
		}

		return relayEvents;
	}

	private List<RelayEventEntity> findAll() {
		@SuppressWarnings("unchecked")
		List<RelayEventEntity> result = (List<RelayEventEntity>) getJpaDao().performSelectQuery("SELECT p FROM RelayEventEntity p");

		return result;
	}

	@Override
	public void set(RelayEvent relayEvent) {
		if (relayEvent == null) {
			throw new IllegalArgumentException("[relayEvent] must not be 'null'.");
		}

		RelayEventEntity relayEventEntity = findById(relayEvent.getUuid());
		if (relayEventEntity == null) {
			relayEventEntity = RelayEventEntity.newInstance(relayEvent.getUuid());
		}
		getRelayEventMapper().mapRelayEventToEntity(relayEvent, relayEventEntity);

		mapParticipants(relayEvent, relayEventEntity);

		mergeEntity(relayEventEntity);
	}

	void mergeEntity(RelayEventEntity relayEventEntity) {
		getJpaDao().mergeEntity(relayEventEntity);
	}

	private void mapParticipants(RelayEvent relayEvent, RelayEventEntity relayEventEntity) {

		checkForExistingEntites(relayEvent, relayEventEntity);

		removeEntites(relayEvent, relayEventEntity);
	}

	private void checkForExistingEntites(RelayEvent relayEvent, RelayEventEntity relayEventEntity) {
		for (Participant each : relayEvent.getParticipants()) {
			Optional<ParticipantEntity> participantEntity = relayEventEntity.getParticipantEntity(each.getUuid());
			if (participantEntity.isPresent()) {
				ParticipantEntity currentParticipantEntity = participantEntity.get();
				getParticipantMapper().mapParticipantToEntity(each, currentParticipantEntity);
			} else {
				ParticipantEntity newParticipantEntity = ParticipantEntity.newInstance(each.getUuid());
				PersonEntity personEntity = findPersonEntityById(each.getUuidPerson());
				newParticipantEntity.setPersonEntity(personEntity);
				newParticipantEntity.setRelayEventEntity(relayEventEntity);
				getParticipantMapper().mapParticipantToEntity(each, newParticipantEntity);
				relayEventEntity.addParticipant(newParticipantEntity);
			}
		}
	}

	private void removeEntites(RelayEvent relayEvent, RelayEventEntity relayEventEntity) {
		List<ParticipantEntity> entitesToRemove = new ArrayList<>();
		for (ParticipantEntity eachParticipantEntity : relayEventEntity.getParticipantEntities()) {
			boolean exist = false;
			for (Participant eachParticipant : relayEvent.getParticipants()) {
				if (eachParticipant.getUuid().toString().equals(eachParticipantEntity.getId())) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				entitesToRemove.add(eachParticipantEntity);
			}
		}
		for (ParticipantEntity eachParticipantEntity : entitesToRemove) {
			relayEventEntity.removeParticipant(eachParticipantEntity);
		}
	}

	PersonEntity findPersonEntityById(UUID uuid) {
		PersonEntity result = getJpaDao().findById(PersonEntity.class, uuid.toString());

		return result;
	}

	RelayEventEntity findById(UUID uuid) {
		RelayEventEntity result = getJpaDao().findById(RelayEventEntity.class, uuid.toString());

		return result;
	}

	@Override
	public RelayEvent get(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}

		RelayEventEntity relayEventEntity = findById(uuid);
		RelayEvent relayEvent = getRelayEventEntityMapper().mapToRelayEvent(relayEventEntity);

		return relayEvent;

	}
}