package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.Duration;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.entity.MemberEntity;
import com.relayd.entity.PersonEntity;
import com.relayd.entity.RelayEntity;
import com.relayd.entity.RelayEventEntity;

/**
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   20.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityToRelayMapperTest {

	private EntityToRelayMapper sut = EntityToRelayMapper.newInstance();
	private RelayEntity relayEntity = RelayEntity.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMapToRelay_whenRelayEntityIsNull() {
		sut.mapToRelay(null);
	}

	@Test
	public void testMapToRelay_check_id() {
		UUID expected = UUID.randomUUID();
		RelayEntity relayEntity = RelayEntity.newInstance(expected);

		Relay relay = sut.mapToRelay(relayEntity);

		UUID actual = relay.getUuid();
		assertEquals("Mapping of [id] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelay_check_relayname() {
		relayEntity.setRelayname("Die 4 ????");
		Relayname expected = Relayname.newInstance(relayEntity.getRelayname());

		Relay relay = sut.mapToRelay(relayEntity);

		Relayname actual = relay.getRelayname();
		assertEquals("Mapping of [relayname] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelay_check_duration() {
		relayEntity.setDuration(7890L);
		Duration expected = Duration.ofMillis(relayEntity.getDuration());

		Relay relay = sut.mapToRelay(relayEntity);

		Duration actual = relay.getDuration();
		assertEquals("Mapping of [duration] is not correct!", expected, actual);
	}

	@Test
	public void testMapRelayEventEntity_whenRelayEventIsNull() {
		RelayEvent actual = sut.mapRelayEventEntity(null);

		assertNull("Mapping of [relayEventEntity] is not correct!", actual);
	}

	@Test
	public void testMapRelayEventEntity_whenRelayEventIsNotNull() {
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName("My Event");
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));

		RelayEvent actual = sut.mapRelayEventEntity(relayEventEntity);

		assertNotNull("Mapping of [relayEventEntity] is not correct!", actual);
	}

	@Test
	public void testMapToRelay_check_relayevent() {
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName("My Event");
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));

		relayEntity.setRelayEventEntity(relayEventEntity);

		Relay relay = sut.mapToRelay(relayEntity);

		UUID expected = UUID.fromString(relayEventEntity.getId());
		UUID actual = relay.getRelayEvent().getUuid();
		assertEquals("Mapping of [relayEvent] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelay_check_participant_position_one() {
		MemberEntity participantEntity = getRandomMemberEntityForPosition(Integer.valueOf(1));
		relayEntity.addMemberEntity(participantEntity);

		Relay relay = sut.mapToRelay(relayEntity);

		UUID expected = UUID.fromString(participantEntity.getPersonEntity().getId());
		UUID actual = relay.getMemberFor(Position.FIRST).getUuidPerson();
		assertEquals("Mapping of [participant] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelay_check_participant_position_two() {
		MemberEntity participantEntity = getRandomMemberEntityForPosition(Integer.valueOf(1));
		relayEntity.addMemberEntity(participantEntity);
		participantEntity = getRandomMemberEntityForPosition(Integer.valueOf(2));
		relayEntity.addMemberEntity(participantEntity);

		Relay relay = sut.mapToRelay(relayEntity);

		UUID expected = UUID.fromString(participantEntity.getPersonEntity().getId());
		UUID actual = relay.getMemberFor(Position.SECOND).getUuidPerson();
		assertEquals("Mapping of [participant] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelay_check_participant_position_three() {
		MemberEntity participantEntity = getRandomMemberEntityForPosition(Integer.valueOf(1));
		relayEntity.addMemberEntity(participantEntity);
		participantEntity = getRandomMemberEntityForPosition(Integer.valueOf(2));
		relayEntity.addMemberEntity(participantEntity);
		participantEntity = getRandomMemberEntityForPosition(Integer.valueOf(3));
		relayEntity.addMemberEntity(participantEntity);

		Relay relay = sut.mapToRelay(relayEntity);

		UUID expected = UUID.fromString(participantEntity.getPersonEntity().getId());
		UUID actual = relay.getMemberFor(Position.THIRD).getUuidPerson();
		assertEquals("Mapping of [participant] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelay_check_participant_position_four() {
		MemberEntity participantEntity = getRandomMemberEntityForPosition(Integer.valueOf(1));
		relayEntity.addMemberEntity(participantEntity);
		participantEntity = getRandomMemberEntityForPosition(Integer.valueOf(2));
		relayEntity.addMemberEntity(participantEntity);
		participantEntity = getRandomMemberEntityForPosition(Integer.valueOf(3));
		relayEntity.addMemberEntity(participantEntity);
		participantEntity = getRandomMemberEntityForPosition(Integer.valueOf(4));
		relayEntity.addMemberEntity(participantEntity);

		Relay relay = sut.mapToRelay(relayEntity);

		UUID expected = UUID.fromString(participantEntity.getPersonEntity().getId());
		UUID actual = relay.getMemberFor(Position.FOURTH).getUuidPerson();
		assertEquals("Mapping of [participant] is not correct!", expected, actual);
	}

	private MemberEntity getRandomMemberEntityForPosition(Integer position) {
		PersonEntity personEntity = PersonEntity.newInstance();
		MemberEntity participantEntity = MemberEntity.newInstance();
		participantEntity.setPersonEntity(personEntity);
		participantEntity.setPosition(position);

		return participantEntity;
	}
}