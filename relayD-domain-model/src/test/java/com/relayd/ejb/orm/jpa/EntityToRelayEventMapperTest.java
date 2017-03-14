package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.UUID;

import org.junit.Test;

import com.relayd.*;
import com.relayd.attributes.*;
import com.relayd.entity.*;

/**
 * 
 * @author  Rasumichin (Erik@relayd.de)
 * @since   20.02.2017
 *
 */
public class EntityToRelayEventMapperTest {

	private EntityToRelayEventMapper sut = EntityToRelayEventMapper.newInstance();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance could not be created!", sut);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMapToRelayEvent_whenRelayEventEntityIsNull() {
		sut.mapToRelayEvent(null);
	}

	@Test
	public void testMapToRelayEvent_check_id() {
		UUID expected = UUID.randomUUID();
		RelayEventEntity relayEventEntity = new RelayEventEntity.Builder("My Event").withId(expected.toString()).build();

		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		UUID actual = relayEvent.getUuid();
		assertEquals("Mapping of [id] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelayEvent_check_eventName() {
		Eventname expected = Eventname.newInstance("My Event");
		RelayEventEntity relayEventEntity = new RelayEventEntity.Builder(expected.toString()).build();

		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		Eventname actual = relayEvent.getName();
		assertEquals("Mapping of [eventName] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelayEvent_check_eventDay() {
		Date today = new Date(System.currentTimeMillis());
		RelayEventEntity relayEventEntity = new RelayEventEntity.Builder("My Event").withEventDay(today).build();

		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		EventDay expected = EventDay.newInstance(today.toLocalDate());
		EventDay actual = relayEvent.getEventDay();
		
		assertEquals("Mapping of [eventDay] is not correct!", expected, actual);
	}
}
