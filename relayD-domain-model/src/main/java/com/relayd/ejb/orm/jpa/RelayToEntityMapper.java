package com.relayd.ejb.orm.jpa;

import com.relayd.Participant;
import com.relayd.Relay;
import com.relayd.attributes.Position;
import com.relayd.entity.RelayEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   25.11.2016
 *
 */
public class RelayToEntityMapper {

	private RelayToEntityMapper() {
	}

	public static RelayToEntityMapper newInstance() {
		return new RelayToEntityMapper();
	}

	public void mapRelayToEntity(Relay relay, RelayEntity relayEntity) {
		if (relay == null) {
			throw new IllegalArgumentException("[relay] must not be 'null'!");
		}
		if (relayEntity == null) {
			throw new IllegalArgumentException("[relayEntity] must not be 'null'!");
		}

		relayEntity.setRelayname(relay.getRelayname().isEmpty() ? null : relay.getRelayname().toString());

		// TODO (Christian, Version 1.3): Ist aktuell so. 4 Teilnehmer also 4x "einfach" mappen
		Participant participantOne = relay.getParticipantFor(Position.FIRST);
		if (participantOne != null) {
			relayEntity.setParticipantOne(participantOne.getUuidPerson());
		} else {
			relayEntity.setParticipantOne(null);
		}

		Participant participantTwo = relay.getParticipantFor(Position.SECOND);
		if (participantTwo != null) {
			relayEntity.setParticipantTwo(participantTwo.getUuidPerson());
		} else {
			relayEntity.setParticipantTwo(null);
		}

		Participant participantThree = relay.getParticipantFor(Position.THIRD);
		if (participantThree != null) {
			relayEntity.setParticipantThree(participantThree.getUuidPerson());
		} else {
			relayEntity.setParticipantThree(null);
		}

		Participant participantFour = relay.getParticipantFor(Position.FOURTH);
		if (participantFour != null) {
			relayEntity.setParticipantFour(participantFour.getUuidPerson());
		} else {
			relayEntity.setParticipantFour(null);
		}
	}
}