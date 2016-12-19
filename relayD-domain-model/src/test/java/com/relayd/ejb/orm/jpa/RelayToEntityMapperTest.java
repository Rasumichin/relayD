package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.ParticipantBuilder;
import com.relayd.Relay;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.entity.RelayEntity;

/**
 * If the code and the comments disagree, then both are probably wrong.
 *  - Norm Schryer
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   25.11.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayToEntityMapperTest {

	private RelayToEntityMapper sut = RelayToEntityMapper.newInstance();
	private Relay relay = Relay.newInstance();
	private RelayEntity relayEntity = RelayEntity.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	// TODO (Christian, Version 1.3): Name überprüfen. In PersonMapper hießt es anders...
	public void testMapDomainToEntity_whenDomainIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[relay] must not be 'null'!");

		sut.mapRelayToEntity(null, relayEntity);
	}

	@Test
	public void testMapDomainToEntity_whenEntityIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[relayEntity] must not be 'null'!");

		sut.mapRelayToEntity(relay, null);
	}

	@Test
	public void testMapDomainToEntity_id() {
		RelayEntity relayEntity = RelayEntity.newInstance(relay.getUuid());
		String expected = relay.getUuid().toString();

		sut.mapRelayToEntity(relay, relayEntity);

		String actual = relayEntity.getId();
		// TODO (Christian, Version 1.4): Wird doch gar nicht gemappt?! Dachte ich nun eigentlich....
		assertEquals("Mapping of [uuid] is not correct!", expected, actual);
	}

	@Test
	public void testMapDomainToEntity_relayname() {
		String expected = "Die 4 ????";
		relay.setRelayname(Relayname.newInstance(expected));

		sut.mapRelayToEntity(relay, relayEntity);

		String actual = relayEntity.getRelayname();
		assertEquals("Mapping of [relayname] is not correct!", expected, actual);
	}

	@Test
	@Ignore("Das ist immer gleich! Wie machen wir das am besten?")
	public void testMapDomainToEntity_relayevent() {
		// TODO (Christian, Version 1.4): Das ist immer gleich! Wie machen wir das am besten?
	}

	@Test
	public void testMapDomainToEntity_participants() {

		Participant participantOne = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").withUUIDPerson(UUID.randomUUID()).build();
		relay.addParticipant(participantOne, Position.FIRST);

		Participant participantTwo = new ParticipantBuilder().withForename("Peter").withSurename("Shaw").withUUIDPerson(UUID.randomUUID()).build();
		relay.addParticipant(participantTwo, Position.SECOND);

		Participant participantThree = new ParticipantBuilder().withForename("Bob").withSurename("Andrews").withUUIDPerson(UUID.randomUUID()).build();
		relay.addParticipant(participantThree, Position.THIRD);

		Participant participantFour = new ParticipantBuilder().withForename("Tante").withSurename("Mathilda").withUUIDPerson(UUID.randomUUID()).build();
		relay.addParticipant(participantFour, Position.FOURTH);

		sut.mapRelayToEntity(relay, relayEntity);

		UUID actualOne = relayEntity.getParticipantOne();
		assertEquals("Mapping of [participantFirst] is not correct!", participantOne.getUuidPerson(), actualOne);

		UUID actualTwo = relayEntity.getParticipantTwo();
		assertEquals("Mapping of [participantSecond] is not correct!", participantTwo.getUuidPerson(), actualTwo);

		UUID actualThree = relayEntity.getParticipantThree();
		assertEquals("Mapping of [participantThird] is not correct!", participantThree.getUuidPerson(), actualThree);

		UUID actualFour = relayEntity.getParticipantFour();
		assertEquals("Mapping of [participantFourth] is not correct!", participantFour.getUuidPerson(), actualFour);
	}
}