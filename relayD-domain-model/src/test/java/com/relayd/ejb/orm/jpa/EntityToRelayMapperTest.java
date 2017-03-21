package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.*;
import com.relayd.attributes.*;
import com.relayd.entity.*;

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
		RelayEntity relayEntity = RelayEntity.newInstance(expected.toString());

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
	public void testMapRelayEventEntity_whenRelayEventIsNull() {
		RelayEvent actual = sut.mapRelayEventEntity(null);
		
		assertNull("Mapping of [relayEventEntity] is not correct!", actual);
	}
	
	@Test
	public void testMapRelayEventEntity_whenRelayEventIsNotNull() {
		RelayEventEntity relayEventEntity = new RelayEventEntity.Builder("My Event").build();
		
		RelayEvent actual = sut.mapRelayEventEntity(relayEventEntity);
		
		assertNotNull("Mapping of [relayEventEntity] is not correct!", actual);
	}
	
	@Test
	public void testMapToRelay_check_relayevent() {
		RelayEventEntity relayEventEntity = new RelayEventEntity.Builder("My Event").build();
		relayEntity.setRelayEventEntity(relayEventEntity);
		
		Relay relay = sut.mapToRelay(relayEntity);
		
		UUID expected = UUID.fromString(relayEventEntity.getId());
		UUID actual = relay.getRelayEvent().getUuid();
		assertEquals("Mapping of [relayEvent] is not correct!", expected, actual);
	}
	
	@Test
	public void testMapToRelay_check_participant_position_one() {
		ParticipantEntity participantEntity = getRandomParticipantEntityForPosition(Integer.valueOf(1));
		relayEntity.addParticipantEntity(participantEntity);
		
		Relay relay = sut.mapToRelay(relayEntity);
		
		UUID expected = UUID.fromString(participantEntity.getPersonEntity().getId());
		UUID actual = relay.getParticipantFor(Position.FIRST).getUuidPerson();
		assertEquals("Mapping of [participant] is not correct!", expected, actual);
	}
	
	@Test
	public void testMapToRelay_check_participant_position_two() {
		ParticipantEntity participantEntity = getRandomParticipantEntityForPosition(Integer.valueOf(1));
		relayEntity.addParticipantEntity(participantEntity);
		participantEntity = getRandomParticipantEntityForPosition(Integer.valueOf(2));
		relayEntity.addParticipantEntity(participantEntity);
		
		Relay relay = sut.mapToRelay(relayEntity);
		
		UUID expected = UUID.fromString(participantEntity.getPersonEntity().getId());
		UUID actual = relay.getParticipantFor(Position.SECOND).getUuidPerson();
		assertEquals("Mapping of [participant] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelay_check_participant_position_three() {
		ParticipantEntity participantEntity = getRandomParticipantEntityForPosition(Integer.valueOf(1));
		relayEntity.addParticipantEntity(participantEntity);
		participantEntity = getRandomParticipantEntityForPosition(Integer.valueOf(2));
		relayEntity.addParticipantEntity(participantEntity);
		participantEntity = getRandomParticipantEntityForPosition(Integer.valueOf(3));
		relayEntity.addParticipantEntity(participantEntity);
		
		Relay relay = sut.mapToRelay(relayEntity);
		
		UUID expected = UUID.fromString(participantEntity.getPersonEntity().getId());
		UUID actual = relay.getParticipantFor(Position.THIRD).getUuidPerson();
		assertEquals("Mapping of [participant] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelay_check_participant_position_four() {
		ParticipantEntity participantEntity = getRandomParticipantEntityForPosition(Integer.valueOf(1));
		relayEntity.addParticipantEntity(participantEntity);
		participantEntity = getRandomParticipantEntityForPosition(Integer.valueOf(2));
		relayEntity.addParticipantEntity(participantEntity);
		participantEntity = getRandomParticipantEntityForPosition(Integer.valueOf(3));
		relayEntity.addParticipantEntity(participantEntity);
		participantEntity = getRandomParticipantEntityForPosition(Integer.valueOf(4));
		relayEntity.addParticipantEntity(participantEntity);
		
		Relay relay = sut.mapToRelay(relayEntity);
		
		UUID expected = UUID.fromString(participantEntity.getPersonEntity().getId());
		UUID actual = relay.getParticipantFor(Position.FOURTH).getUuidPerson();
		assertEquals("Mapping of [participant] is not correct!", expected, actual);
	}

	private ParticipantEntity getRandomParticipantEntityForPosition(Integer position) {
		PersonEntity personEntity = PersonEntity.newInstance();
		ParticipantEntity participantEntity = ParticipantEntity.newInstance();
		participantEntity.setPersonEntity(personEntity);
		participantEntity.setPosition(position);
		
		return participantEntity;
	}
}