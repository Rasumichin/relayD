package com.relayd.ejb.orm.jpa;

import java.util.*;

import com.relayd.*;
import com.relayd.attributes.Position;
import com.relayd.ejb.RelayGateway;
import com.relayd.entity.*;

/**
 * 
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   19.11.2016
 *
 */
public class RelayGatewayJPA extends GatewayJPA implements RelayGateway {
	private RelayToEntityMapper relayMapper = RelayToEntityMapper.newInstance();
	private EntityToRelay2Mapper entityMapper = EntityToRelay2Mapper.newInstance();

	@Override
	public void set(Relay relay) {
		if (relay == null) {
			throw new IllegalArgumentException("[relay] must not be 'null'.");
		}

		Relay2Entity relayEntity = getRelayEntity(relay);
		getRelayMapper().mapRelayToEntity2(relay, relayEntity);
		mapParticipantsToEntities(relay, relayEntity);
		
		getJpaDao().mergeEntity(relayEntity);
	}

	private Relay2Entity getRelayEntity(Relay relay) {
		Relay2Entity relayEntity = findById(relay.getUuid());
		if (relayEntity == null) {
			relayEntity = Relay2Entity.newInstance(relay.getUuid().toString());
			setRelayEventEntityFor(relay.getRelayEvent(), relayEntity);
		}
		
		return relayEntity;
	}

	Relay2Entity findById(UUID uuid) {
		Relay2Entity result = getJpaDao().findById(Relay2Entity.class, uuid.toString());

		return result;
	}

	void setRelayEventEntityFor(RelayEvent relayEvent, Relay2Entity relayEntity) {
		RelayEventEntity relayEventEntity = getJpaDao().findById(RelayEventEntity.class, relayEvent.getUuid().toString());
		relayEntity.setRelayEventEntity(relayEventEntity);
	}

	private RelayToEntityMapper getRelayMapper() {
		return relayMapper;
	}
	
	void mapParticipantsToEntities(Relay relay, Relay2Entity relayEntity) {
		for (int i = 0; i < relay.getParticipants().size(); i++) {
			Integer position = Integer.valueOf(i + 1);
			Participant participant = relay.getParticipantFor(Position.newInstance(position));
			Optional<ParticipantEntity> participantEntity = relayEntity.getParticipantEntityAtPosition(position);
			if (participant.isEmpty()) {
				if (participantEntity.isPresent()) {
					relayEntity.removeParticipantEntity(participantEntity.get());
				}
			} else {
				if (participantEntity.isPresent()) {
					ParticipantEntity currentParticipantEntity = participantEntity.get();
					if (!(participant.getUuidPerson().equals(currentParticipantEntity.getUuidPerson()))) {
						PersonEntity personEntity = findPersonEntityFor(participant.getUuidPerson());
						currentParticipantEntity.setPersonEntity(personEntity);
					}
				} else {
					ParticipantEntity newParticipantEntity = ParticipantEntity.newInstance();
					newParticipantEntity.setPosition(position);
					PersonEntity personEntity = findPersonEntityFor(participant.getUuidPerson());
					newParticipantEntity.setPersonEntity(personEntity);
					relayEntity.addParticipantEntity(newParticipantEntity);
				}
			}
		}
	}
	
	private PersonEntity findPersonEntityFor(UUID personUuid) {
		PersonEntity result = getJpaDao().findById(PersonEntity.class, personUuid.toString());
		if (result == null) {
			throw new IllegalStateException("PersonEntity with 'id=" + personUuid + "' is not stored in database. This must not happen here.");
		}
		
		return result;
		
	}

	@Override
	public List<Relay> getAll() {
		List<Relay2Entity> relayEntities = findAll();
		List<Relay> relays = mapPersonEntityListToPersonList(relayEntities);

		return relays;

	}

	private List<Relay> mapPersonEntityListToPersonList(List<Relay2Entity> relayEntities) {
		List<Relay> relays = new ArrayList<>();
		for (Relay2Entity eachEntity : relayEntities) {
			relays.add(getEntityMapper().mapToRelay(eachEntity));
		}
		
		return relays;
	}

	private EntityToRelay2Mapper getEntityMapper() {
		return entityMapper;
	}

	List<Relay2Entity> findAll() {
		@SuppressWarnings("unchecked")
		List<Relay2Entity> result = (List<Relay2Entity>) getJpaDao().performSelectQuery("SELECT p FROM Relay2Entity p");

		return result;
	}

	@Override
	public Relay get(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}

		Relay2Entity relayEntity = findById(uuid);
		Relay relay = getEntityMapper().mapToRelay(relayEntity);

		return relay;
	}
}