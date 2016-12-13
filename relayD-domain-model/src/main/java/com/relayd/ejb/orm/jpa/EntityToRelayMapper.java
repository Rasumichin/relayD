package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.entity.RelayEntity;

public class EntityToRelayMapper {

	private PersonGatewayJPA personGateway = new PersonGatewayJPA();

	private EntityToRelayMapper() {
	}

	public static EntityToRelayMapper newInstance() {
		return new EntityToRelayMapper();
	}

	public Relay mapToRelay(RelayEntity relayEntity) {
		if (relayEntity == null) {
			throw new IllegalArgumentException("[relayEntity] must not be 'null'.");
		}

		Relay relay = Relay.newInstance();

		relay.setUuid(UUID.fromString(relayEntity.getId()));

		// Again: Thank god for the 'NullObjectPattern'.
		relay.setRelayname(Relayname.newInstance(relayEntity.getRelayname()));
		Person person;
		if (relayEntity.getParticipantOne() != null) {
			person = personGateway.get(relayEntity.getParticipantOne());

			Participant participantOne = createParticipant(person);

			relay.addParticipant(participantOne, Position.FIRST);
		}
		if (relayEntity.getParticipantTwo() != null) {

			person = personGateway.get(relayEntity.getParticipantTwo());

			Participant participantTwo = createParticipant(person);

			relay.addParticipant(participantTwo, Position.SECOND);
		}
		if (relayEntity.getParticipantThree() != null) {

			person = personGateway.get(relayEntity.getParticipantThree());

			Participant participantThree = createParticipant(person);

			relay.addParticipant(participantThree, Position.THIRD);
		}
		if (relayEntity.getParticipantFour() != null) {

			person = personGateway.get(relayEntity.getParticipantFour());

			Participant participantFour = createParticipant(person);

			relay.addParticipant(participantFour, Position.FOURTH);
		}
		return relay;
	}

	private Participant createParticipant(Person person) {
		Participant participant = Participant.newInstance(person);
		return participant;
	}
}