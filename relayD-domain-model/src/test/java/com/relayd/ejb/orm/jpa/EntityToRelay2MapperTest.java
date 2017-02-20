package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Relay;
import com.relayd.attributes.Relayname;
import com.relayd.entity.*;

/**
 * 
 * @author  Rasumichin (Erik@relayd.de)
 * @since   20.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityToRelay2MapperTest {
	
	private EntityToRelay2Mapper sut = EntityToRelay2Mapper.newInstance();
	private Relay2Entity relayEntity = Relay2Entity.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMapToRelay_whenRelay2EntityIsNull() {
		sut.mapToRelay(null);
	}

	@Test
	public void testMapToRelay_check_id() {
		UUID expected = UUID.randomUUID();
		Relay2Entity relayEntity = Relay2Entity.newInstance(expected.toString());

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

	@Ignore
	@Test
	public void testMapToRelay_check_relayevent() {
		RelayEventEntity relayEventEntity = new RelayEventEntity.Builder("My Event").build();
		relayEntity.setRelayEventEntity(relayEventEntity);
		
		Relay relay = sut.mapToRelay(relayEntity);
		
		UUID expected = UUID.fromString(relayEventEntity.getId());
		UUID actual = relay.getUuid();
		assertEquals("Mapping of [relayEvent] is not correct!", expected, actual);
	}
	
	@Ignore
	@Test
	public void testMapToDomain_participantOne() {
	}
}