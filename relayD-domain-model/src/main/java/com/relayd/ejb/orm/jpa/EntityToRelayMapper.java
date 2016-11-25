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

			Participant participantOne = Participant.newInstance();
			participantOne.setUuidPerson(person.getUuid());
			participantOne.setForename(person.getForename());
			participantOne.setSurename(person.getSurename());

			relay.addParticipant(participantOne, Position.FIRST);
		}
		if (relayEntity.getParticipantTwo() != null) {

			person = personGateway.get(relayEntity.getParticipantTwo());

			Participant participantTwo = Participant.newInstance();
			participantTwo.setUuidPerson(person.getUuid());
			participantTwo.setForename(person.getForename());
			participantTwo.setSurename(person.getSurename());

			relay.addParticipant(participantTwo, Position.SECOND);
		}
		if (relayEntity.getParticipantThree() != null) {

			person = personGateway.get(relayEntity.getParticipantThree());

			Participant participantThree = Participant.newInstance();
			participantThree.setUuidPerson(person.getUuid());
			participantThree.setForename(person.getForename());
			participantThree.setSurename(person.getSurename());

			relay.addParticipant(participantThree, Position.THIRD);
		}
		if (relayEntity.getParticipantFour() != null) {

			person = personGateway.get(relayEntity.getParticipantFour());

			Participant participantFour = Participant.newInstance();
			participantFour.setUuidPerson(person.getUuid());
			participantFour.setForename(person.getForename());
			participantFour.setSurename(person.getSurename());

			relay.addParticipant(participantFour, Position.FOURTH);
		}
		return relay;
	}
}
