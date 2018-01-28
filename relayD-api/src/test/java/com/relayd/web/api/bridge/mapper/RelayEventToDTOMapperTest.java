package com.relayd.web.api.bridge.mapper;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.*;
import com.relayd.attributes.*;
import com.relayd.client.jaxb.RelayEventDTO;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  11.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventToDTOMapperTest {
	private RelayEventToDTOMapper sut = RelayEventToDTOMapper.newInstance();

	@Test
	public void testNewInstance() {
		RelayEventToDTOMapper result = RelayEventToDTOMapper.newInstance();
		
		assertNotNull("Instance creation was not correct!", result);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testMapRelayEventToDTO_relayEventIsNull() {
		RelayEvent relayEvent = null;
		RelayEventDTO relayEventDTO = RelayEventDTO.newInstance();
		
		sut.mapRelayEventToDTO(relayEvent, relayEventDTO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMapRelayEventToDTO_relayEventDTOIsNull() {
		RelayEvent relayEvent = RelayEvent.newInstance();
		RelayEventDTO relayEventDTO = null;
		
		sut.mapRelayEventToDTO(relayEvent, relayEventDTO);
	}
	
	@Test
	public void testMapRelayEventToDTO_id() {
		RelayEvent relayEvent = RelayEvent.newInstance();
		String expected = relayEvent.getUuid().toString();
		RelayEventDTO relayEventDTO = RelayEventDTO.newInstance();

		sut.mapRelayEventToDTO(relayEvent, relayEventDTO);

		String actual = relayEventDTO.getId();
		assertEquals("Mapping of [uuid] is not correct!", expected, actual);
	}

	@Test
	public void testMapRelayEventToDTO_name() {
		RelayEvent relayEvent = RelayEvent.newInstance();
		Eventname eventName = Eventname.newInstance("Cologne Rodeo");
		relayEvent.setName(eventName);
		String expected = eventName.toString();
		RelayEventDTO relayEventDTO = RelayEventDTO.newInstance();

		sut.mapRelayEventToDTO(relayEvent, relayEventDTO);

		String actual = relayEventDTO.getName();
		assertEquals("Mapping of [name] is not correct!", expected, actual);
	}

	@Test
	public void testMapRelayEventToDTO_eventDay() {
		RelayEvent relayEvent = RelayEvent.newInstance();
		LocalDate expected = LocalDate.now();
		EventDay eventDay = EventDay.newInstance(expected);
		relayEvent.setEventDay(eventDay);
		RelayEventDTO relayEventDTO = RelayEventDTO.newInstance();

		sut.mapRelayEventToDTO(relayEvent, relayEventDTO);

		LocalDate actual = relayEventDTO.getEventDay();
		assertEquals("Mapping of [eventDay] is not correct!", expected, actual);
	}

	@Test
	public void testMapRelayEventToDTO_numberOfRelays() {
		RelayEvent relayEvent = RelayEvent.newInstance();
		Relay relay = Relay.newInstance(relayEvent);
		relayEvent.addRelay(relay);
		Integer expected = Integer.valueOf(1);
		RelayEventDTO relayEventDTO = RelayEventDTO.newInstance();

		sut.mapRelayEventToDTO(relayEvent, relayEventDTO);

		Integer actual = relayEventDTO.getNumberOfRelays();
		assertEquals("Mapping of [numberOfRelays] is not correct!", expected, actual);
	}
}
