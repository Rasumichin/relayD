package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.entity.RelayEventEntity;

/**
 * Wischen Sie Staub, bevor Sie Staub sehen.
 * Denken Sie nicht "Es ist ja schon sauber", sondern vielmehr: "Halte es sauber"
 *  - Philip Toshio Sudo
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   28.03.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventToEntityMapperTest {

	private RelayEventToEntityMapper sut = RelayEventToEntityMapper.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapRelayEventToEntity_whenRelayEventIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[source] must not be 'null'!");

		RelayEventEntity dummyRelayEventEntity = null;

		sut.mapRelayEventToEntity(null, dummyRelayEventEntity);
	}

	@Test
	public void testMapRelayEventToEntity_whenRelayEventEntityIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[target] must not be 'null'!");

		RelayEvent dummyRelayEvent = RelayEvent.newInstance();
		RelayEventEntity relayEventEntity = null;

		sut.mapRelayEventToEntity(dummyRelayEvent, relayEventEntity);
	}

	@Test
	public void testMapRelayEventToEntity_eventname() {
		String expected = "Metro Group Marathon";
		RelayEvent relayEvent = RelayEvent.newInstance();
		relayEvent.setName(Eventname.newInstance(expected));

		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();
		sut.mapRelayEventToEntity(relayEvent, relayEventEntity);

		String actual = relayEventEntity.getEventName();
		assertEquals("Mapping of [eventName] is not correct!", expected, actual);
	}

	@Test
	public void testMapRelayEventToEntity_day() {
		LocalDate expected = LocalDate.now();
		RelayEvent relayEvent = RelayEvent.newInstance();
		relayEvent.setEventDay(EventDay.newInstance(expected));

		RelayEventEntity relayEventEntity = RelayEventEntity.newInstance();

		sut.mapRelayEventToEntity(relayEvent, relayEventEntity);

		Date actual = relayEventEntity.getEventDay();
		assertEquals("Mapping of [eventDay] is not correct!", expected.toString(), actual.toString());
	}

}