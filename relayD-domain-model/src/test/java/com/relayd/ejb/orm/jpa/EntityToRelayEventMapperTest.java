package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.UUID;

import org.junit.Test;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.entity.RelayEventEntity;

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
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance(expected);
		relayEventEntity.setEventName("My Event");
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));

		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		UUID actual = relayEvent.getUuid();
		assertEquals("Mapping of [id] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelayEvent_check_eventName() {
		Eventname expected = Eventname.newInstance("My Event");
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName(expected.toString());
		relayEventEntity.setEventDay(new Date(System.currentTimeMillis()));

		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		Eventname actual = relayEvent.getName();
		assertEquals("Mapping of [eventName] is not correct!", expected, actual);
	}

	@Test
	public void testMapToRelayEvent_check_eventDay() {
		Date today = new Date(System.currentTimeMillis());
		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		relayEventEntity.setEventName("My Event");
		relayEventEntity.setEventDay(today);

		RelayEvent relayEvent = sut.mapToRelayEvent(relayEventEntity);

		EventDay expected = EventDay.newInstance(today.toLocalDate());
		EventDay actual = relayEvent.getEventDay();

		assertEquals("Mapping of [eventDay] is not correct!", expected, actual);
	}
}
