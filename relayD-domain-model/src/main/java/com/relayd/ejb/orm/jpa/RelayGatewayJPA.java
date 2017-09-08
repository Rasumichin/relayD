package com.relayd.ejb.orm.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.relayd.Member;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Position;
import com.relayd.ejb.RelayGateway;
import com.relayd.entity.MemberEntity;
import com.relayd.entity.PersonEntity;
import com.relayd.entity.RelayEntity;
import com.relayd.entity.RelayEventEntity;

/**
 *
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   19.11.2016
 *
 */
public class RelayGatewayJPA extends GatewayJPA implements RelayGateway {
	private RelayToEntityMapper relayMapper = RelayToEntityMapper.newInstance();
	private EntityToRelayMapper entityMapper = EntityToRelayMapper.newInstance();

	@Override
	public void set(Relay relay) {
		if (relay == null) {
			throw new IllegalArgumentException("[relay] must not be 'null'.");
		}

		RelayEntity relayEntity = getRelayEntity(relay);
		getRelayMapper().mapRelayToEntity(relay, relayEntity);
		mapMembersToEntities(relay, relayEntity);

		getJpaDao().mergeEntity(relayEntity);
	}

	private RelayEntity getRelayEntity(Relay relay) {
		RelayEntity relayEntity = findById(relay.getUuid());
		if (relayEntity == null) {
			relayEntity = RelayEntity.newInstance(relay.getUuid());
			setRelayEventEntityFor(relay.getRelayEvent(), relayEntity);
		}

		return relayEntity;
	}

	RelayEntity findById(UUID uuid) {
		RelayEntity result = getJpaDao().findById(RelayEntity.class, uuid.toString());

		return result;
	}

	void setRelayEventEntityFor(RelayEvent relayEvent, RelayEntity relayEntity) {
		RelayEventEntity relayEventEntity = getJpaDao().findById(RelayEventEntity.class, relayEvent.getUuid().toString());
		relayEntity.setRelayEventEntity(relayEventEntity);
	}

	private RelayToEntityMapper getRelayMapper() {
		return relayMapper;
	}

	void mapMembersToEntities(Relay relay, RelayEntity relayEntity) {
		for (int i = 0; i < relay.getMembers().size(); i++) {
			Integer position = Integer.valueOf(i + 1);
			Member member = relay.getMemberFor(Position.newInstance(position));
			Optional<MemberEntity> memberEntity = relayEntity.getMemberEntityAtPosition(position);
			if (member.isEmpty()) {
				relayEntity.possiblyRemoveMemberEntity(memberEntity);
			} else {
				if (memberEntity.isPresent()) {
					MemberEntity currentMemberEntity = memberEntity.get();
					if (!(member.hasThatPersonIdentity(currentMemberEntity.getUuidPerson()))) {
						setNewPersonEntityById(currentMemberEntity, member.getUuidPerson());
					}
				} else {
					MemberEntity newMemberEntity = getNewMemberEntity(position, member.getUuidPerson());
					relayEntity.addMemberEntity(newMemberEntity);
				}
			}
		}
	}

	void setNewPersonEntityById(MemberEntity memberEntity, UUID personId) {
		PersonEntity personEntity = findPersonEntityFor(personId);
		memberEntity.setPersonEntity(personEntity);
	}

	PersonEntity findPersonEntityFor(UUID personUuid) {
		PersonEntity result = getJpaDao().findById(PersonEntity.class, personUuid.toString());
		if (result == null) {
			throw new IllegalStateException("PersonEntity with 'id=" + personUuid + "' is not stored in database. This must not happen here.");
		}

		return result;
	}

	MemberEntity getNewMemberEntity(Integer position, UUID personId) {
		MemberEntity memberEntity = MemberEntity.newInstance();
		memberEntity.setPosition(position);
		PersonEntity personEntity = findPersonEntityFor(personId);
		memberEntity.setPersonEntity(personEntity);

		return memberEntity;
	}

	@Override
	public List<Relay> getAll() {
		List<RelayEntity> relayEntities = findAll();
		List<Relay> relays = mapPersonEntityListToPersonList(relayEntities);

		return relays;
	}

	private List<Relay> mapPersonEntityListToPersonList(List<RelayEntity> relayEntities) {
		List<Relay> relays = new ArrayList<>();
		for (RelayEntity eachEntity : relayEntities) {
			relays.add(getEntityMapper().mapToRelay(eachEntity));
		}

		return relays;
	}

	private EntityToRelayMapper getEntityMapper() {
		return entityMapper;
	}

	List<RelayEntity> findAll() {
		@SuppressWarnings("unchecked")
		List<RelayEntity> result = (List<RelayEntity>) getJpaDao().performSelectQuery("SELECT p FROM RelayEntity p");

		return result;
	}

	@Override
	public Relay get(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}

		RelayEntity relayEntity = findById(uuid);
		Relay relay = getEntityMapper().mapToRelay(relayEntity);

		return relay;
	}
}