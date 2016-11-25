package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.Relay;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.entity.RelayEntity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityToRelayMapperTest {

	private EntityToRelayMapper sut = EntityToRelayMapper.newInstance();
	private RelayEntity relayEntity = RelayEntity.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMapToPerson_whenPersonIsNull() {
		sut.mapToRelay(null);
	}

	@Test
	public void testMapToDomain_id() {
		UUID expected = UUID.randomUUID();
		RelayEntity relayEntity = RelayEntity.newInstance(expected);

		Relay relay = sut.mapToRelay(relayEntity);

		UUID actual = relay.getUuid();
		assertEquals("Mapping of [id] is not correct!", expected, actual);
	}

	@Test
	public void testMapToDomain_relayname() {
		relayEntity.setRelayname("Die 4 ????");
		Relayname expected = Relayname.newInstance(relayEntity.getRelayname());

		Relay relay = sut.mapToRelay(relayEntity);

		Relayname actual = relay.getRelayname();
		assertEquals("Mapping of [relayname] is not correct!", expected, actual);
	}

	@Test
	@Ignore
	public void testMapToDomain_participantOne() {
		UUID expected = UUID.randomUUID();
		relayEntity.setParticipantOne(expected);

		Relay relay = sut.mapToRelay(relayEntity);

		Participant actual = relay.getParticipantFor(Position.FIRST);
		assertEquals("Mapping of [relayname] is not correct!", expected, actual);
	}
}