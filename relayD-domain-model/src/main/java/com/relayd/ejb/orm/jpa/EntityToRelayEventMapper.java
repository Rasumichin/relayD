package com.relayd.ejb.orm.jpa;

import java.time.Duration;
import java.util.UUID;

import com.relayd.Member;
import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Comment;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.entity.MemberEntity;
import com.relayd.entity.ParticipantEntity;
import com.relayd.entity.PersonEntity;
import com.relayd.entity.RelayEntity;
import com.relayd.entity.RelayEventEntity;

/**
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   20.02.2017
 *
 */
public class EntityToRelayEventMapper {

	private EntityToRelayEventMapper() {
	}

	public static EntityToRelayEventMapper newInstance() {
		return new EntityToRelayEventMapper();
	}

	public RelayEvent mapToRelayEvent(RelayEventEntity relayEventEntity) {
		if (relayEventEntity == null) {
			throw new IllegalArgumentException("[relayEventEntity] must not be null!");
		}

		RelayEvent relayEvent = RelayEvent.newInstance();
		relayEvent.setUuid(UUID.fromString(relayEventEntity.getId()));
		relayEvent.setName(Eventname.newInstance(relayEventEntity.getEventName()));
		relayEvent.setEventDay(EventDay.newInstance(relayEventEntity.getEventDay().toLocalDate()));

		mapRelays(relayEventEntity, relayEvent);

		mapParticipants(relayEventEntity, relayEvent);

		return relayEvent;
	}

	private void mapRelays(RelayEventEntity relayEventEntity, RelayEvent relayEvent) {
		for (RelayEntity eachRelayEntity : relayEventEntity.getRelayEntities()) {
			Relay relay = mapToRelay(eachRelayEntity, relayEvent);
			relayEvent.addRelay(relay);
		}
	}

	private void mapParticipants(RelayEventEntity relayEventEntity, RelayEvent relayEvent) {
		EntityToPersonMapper entityToPersonMapper = EntityToPersonMapper.newInstance();
		for (ParticipantEntity eachParticipantEntity : relayEventEntity.getParticipantEntities()) {
			PersonEntity personEntity = eachParticipantEntity.getPersonEntity();

			Person person = entityToPersonMapper.mapToPerson(personEntity);

			Participant participant = Participant.newInstance(person);
			participant.setUuid(UUID.fromString(eachParticipantEntity.getId()));
			participant.setComment(Comment.newInstance(eachParticipantEntity.getComment()));

			relayEvent.addParticipant(participant);
		}
	}

	private Relay mapToRelay(RelayEntity relayEntity, RelayEvent relayEvent) {
		// TODO - REL-264 - Doppelter Code
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